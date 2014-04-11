package cn.internalaudit.audit.beans.filemanager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.operamasks.faces.annotation.Accessible;
import org.operamasks.faces.annotation.Action;
import org.operamasks.faces.annotation.Bind;
import org.operamasks.faces.annotation.DataModel;
import org.operamasks.faces.annotation.Inject;
import org.operamasks.faces.annotation.ManagedBean;
import org.operamasks.faces.annotation.ManagedBeanScope;
import org.operamasks.faces.annotation.SaveState;
import org.operamasks.faces.component.grid.impl.UIDataGrid;
import org.operamasks.faces.component.layout.impl.UIWindow;
import org.operamasks.faces.component.misc.UIFileDownload;
import org.operamasks.faces.component.tree.base.TreeDataProvider2;
import org.operamasks.faces.component.tree.base.TreeDataProviderAdapter;
import org.operamasks.faces.component.tree.impl.UITree;
import org.operamasks.faces.component.tree.impl.UITreeNode;
import org.operamasks.faces.component.widget.fileupload.impl.UIFileUploadDialog;
import org.operamasks.faces.user.util.Browser;
import org.operamasks.org.apache.commons.fileupload.FileItemIterator;
import org.operamasks.org.apache.commons.fileupload.FileItemStream;
import org.operamasks.org.apache.commons.fileupload.FileUploadException;
import org.operamasks.org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.internalaudit.audit.bo.IDepartmentBo;
import cn.internalaudit.audit.bo.IFileUploadManagerBo;
import cn.internalaudit.audit.constant.Constant;
import cn.internalaudit.audit.constant.FileType;
import cn.internalaudit.audit.entitys.FileUploadManager;
import cn.internalaudit.audit.utils.Utils;


@ManagedBean(name = "pages.fileManager.fileManagerBean", scope = ManagedBeanScope.REQUEST)
public class FileUploadManagerBean {
	@Inject("#{DoccumentRootPathConfig}")
	private DoccumentRootPathConfig doccumentRootPathConfig;

	@Inject("#{FileUploadManagerBo}")
	private IFileUploadManagerBo fileUploadManagerBo;

//	@Inject("#{DepartmentBo}")
//	private IDepartmentBo departmentBo;

	@Bind(id = "folderDialog", attribute = "binding")
	private UIWindow folderDialog;

	@Bind(id = "folderRenameDialog", attribute = "binding")
	private UIWindow folderRenameDialog;

	@Bind(id = "folderTree", attribute = "binding")
	private UITree folderTree;

	@Bind(id = "folderTree")
	private TreeDataProvider2 treeData = new TreeDataProviderAdapter() {
		public Object[] getChildren(Object node) {
			if (node == null) {
				return new Object[] { Constant.FOLDERROOTNAME };
			}
			if (node == Constant.FOLDERROOTNAME) {
				List<FileUploadManager> list = fileUploadManagerBo
						.findByParentId(null);
				return list.toArray();
			}
			if (node instanceof FileUploadManager) {
				FileUploadManager temp = (FileUploadManager) node;
				List<FileUploadManager> list = fileUploadManagerBo
						.findByParentId(temp.getId());
				return list.toArray();
			}
			return null;
		}

		public Boolean isChecked(Object node) {
			return null;
		}

		public boolean isCascade(Object node) {
			return false;
		}

		public String getIcon(Object node) {
			if (node instanceof String) {
				return "../../images/icons/company.gif";
			}
			if (node instanceof FileUploadManager) {
				FileUploadManager file = (FileUploadManager) node;
				if (file.getType() == FileType.Folder) {// 文件夹的图标
					return "../../images/icons/folderopen.gif";
				} else {// 文件的图标
					return "../../images/icons/filetype/"
							+ Utils.fileExtension(file.getFilename()) + ".gif";
				}
			}
			return "";
		}

		public String getText(Object node) {
			if (node instanceof String) {
				return node.toString();
			}
			if (node instanceof FileUploadManager) {
				return ((FileUploadManager) node).getFilename();
			}
			return null;
		}

		public String getHref(Object node) {
			return null;
		}

		public String getHrefTarget(Object node) {
			return null;
		}

		public boolean isExpanded(Object arg0) {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public String getId(Object userData) {
			if (userData instanceof String) {
				return userData.toString();
			}
			if (userData instanceof FileUploadManager) {
				((FileUploadManager) userData).getId();
			}
			return null;
		}

	};

	@Bind(id = "foldername", attribute = "value")
	private String foldername;
	@Bind(id = "folderername", attribute = "value")
	private String folderername;

	@Bind(id = "fileGrid", attribute = "binding")
	private UIDataGrid fileGrid;

	@SaveState
	private UITreeNode selectTreeNode;

	@Action
	public void folderTree_onselect() {
		this.selectedParentFolder = getFolderTreeSelected();
		this.selectTreeNode = folderTree.getSelectedNode();
		this.fileGrid.reload();
	}

	@Action
	public void folderTree_oncheck() {
	}

	@Action
	public void folderTree_oncollapsenode() {
	}

	@Action
	public void folderTree_onexpandnode() {
	}

	/****
	 * 取得选中的节点
	 * 
	 * @return
	 */
	private FileUploadManager getFolderTreeSelected() {
		if (folderTree == null) {
			return null;
		}
		if (folderTree.getEventNode() != null) {
			UITreeNode selectTreeNode = folderTree.getSelectedNode();
			if (selectTreeNode != null) {
				Object object = selectTreeNode.getUserData();
				if (object != null && object instanceof FileUploadManager) {
					return (FileUploadManager) object;
				}
			}
		}
		return null;
	}

	/****
	 * 获取上传文件的存放路径，如果選擇的是文件，就返回文件當前所在的文件夾路徑
	 * 
	 * @return 上传文件真实的存放路径
	 */
	private String getSavePath() {
		if (selectedParentFolder != null) {
			if (selectedParentFolder.getType() == FileType.Folder) {
				savePath = selectedParentFolder.getPath();
			} else {
				if (selectedParentFolder.getParent() != null) {
					savePath = selectedParentFolder.getParent().getPath();
				}

			}
		} else {
			savePath = "";
		}
		return savePath;
	}

	@Action(id = "createFolder")
	public void createFolder() {
		if (this.foldername != null && !"".equals(this.foldername.trim())) {// 文件名称不能为空
			try {
				FileUploadManager selectfolder = this.getFolderTreeSelected();
				if (selectfolder == null) { // 没有选择文件夹,创建的文件夹在根目录

					// TODO检查文件夹是否有重名，如果重名，提示
					FileUploadManager folder = new FileUploadManager();
					folder.setParent(null);
					folder.setFilename(this.foldername);
					folder.setPath(System.getProperty("file.separator")
							+ this.foldername);
					folder.setType(FileType.Folder);
					folder.setTitle(this.foldername);
					if (!fileUploadManagerBo.isRepeatByFileName(folder,
							this.foldername)) {
						Browser.alert("该文件夹已经存在！请重新输入新的文件夹名称");
						return;
					}
					this.fileUploadManagerBo.createFolder(folder);

				} else {// 选择了文件夹，创建的文件夹在选择的目录下
					FileUploadManager folder = new FileUploadManager();
					if (selectfolder.getType() == FileType.File) {
						folder.setParent(selectfolder.getParent());
					} else {
						folder.setParent(selectfolder);
					}
					folder.setFilename(this.foldername);
					folder.setPath(selectfolder.getPath()
							+ System.getProperty("file.separator")
							+ this.foldername);
					folder.setType(FileType.Folder);
					folder.setTitle(this.foldername);
					if (!fileUploadManagerBo.isRepeatByFileName(folder,
							this.foldername)) {
						Browser.alert("该文件夹已经存在！请重新输入新的文件夹名称");
						return;
					}
					this.fileUploadManagerBo.createFolder(folder);
				}
			} catch (IOException ex) {
				Browser.alert("未能成功创建文件夹");
				ex.printStackTrace();
				return;
			}
		} else {
			Browser.alert("请输入文件夹的名称");
			return;
		}
		this.folderTree.repaint();
//		this.folderTree.reload();
		this.folderDialog.close();
	}

	@Action(id = "removerFolder")
	public void removerFolder() {
		FileUploadManager selectFolder = getFolderTreeSelected();
		if (selectFolder != null) {
			try {
				this.fileUploadManagerBo.removeFolder(selectFolder);
			} catch (IOException e) {
				Browser.alert("未能成功删除文件夹");
				return;
			}
			this.folderTree.repaint();
//			this.folderTree.reload();
			this.fileGrid.reload();
		}
	}

	@Action(id = "renameFolder")
	public void renameFolder() {
		if (this.folderername != null && !"".equals(this.folderername.trim())) {
			FileUploadManager selectFolder = getFolderTreeSelected();
			// TODO检查文件夹是否有重名，如果重名，提示
			if (!fileUploadManagerBo.isRepeatByFileName(selectFolder,
					this.folderername)) {
				Browser.alert("该文件夹已经存在！请重新输入新的文件夹名称");
				return;
			}
			this.fileUploadManagerBo.reNameFolder(selectFolder,
					this.folderername);
			this.folderTree.repaint();
//			this.folderTree.reload();
			this.fileGrid.reload();
			folderRenameDialog.close();
		} else {
			Browser.alert("请输入文件夹的名称");
			return;
		}
	}

	@Action(id = "showFolderDialog")
	public void showCreateFolderDialog() {
		this.folderDialog.show();
	}

	@Action(id = "showRenameFolder")
	public void showRenameFolder() {
		FileUploadManager selectFolder = getFolderTreeSelected();
		if (selectFolder != null) {
			this.folderername = selectFolder.getFilename();
			this.folderRenameDialog.show();
		}
	}

	@Action(id = "refreshFolder")
	public void refreshFolder() {
		this.folderTree.repaint();
//		this.folderTree.reload();
	}

	/************
	 * 文件列表数据或取
	 * 
	 * @return
	 */
	/***
	 * 在文件夹树中选中的文件夹
	 */
	@SaveState
	private FileUploadManager selectedParentFolder;

	private List<FileUploadManager> files;
	@SaveState
	private FileUploadManager selectFile;
	/***
	 * 上传文件的存储路径
	 */
	private String savePath;

	@DataModel(id = "fileGrid")
	private List<FileUploadManager> getFileGridValues() {
		if (selectedParentFolder != null) {// 有选选择树中的文件夹或文件

			if (selectedParentFolder.getType() == FileType.Folder) {// 如果选中的是文件夹类型的
				// 在文件列表中显示子文件及文件夹
				files = fileUploadManagerBo.findByParentId(selectedParentFolder
						.getId());
			} else {
				// 如果选选择的是文件类型
				if (selectedParentFolder.getParent() != null) {// 有父文件夹
					// 文件列表中显示与选中文件同级的文件
					files = fileUploadManagerBo
							.findByParentId(selectedParentFolder.getParent()
									.getId());
				} else {
					files = fileUploadManagerBo.findByParentId(null);
				}
			}
		} else {
			files = fileUploadManagerBo.findByParentId(null);
		}
		return files;
	}

	@Action
	public void fileGrid_onrowselect() {
		Object[] selectFile = fileGrid.getSelectedValues();
		if (selectFile != null && selectFile.length > 0) {
			this.selectFile = (FileUploadManager) selectFile[0];
		}
	}

	/***
	 * 双击文件列表表格，如果是文件，就下载，如果文件夹，就打开文件夹
	 */
	@Action
	public void fileGrid_ondblclick() {
		Object[] selectFileObj = fileGrid.getSelectedValues();
		if (selectFileObj != null && selectFileObj.length > 0) {
			this.selectFile = (FileUploadManager) selectFileObj[0];
			if (selectFile.getType() == FileType.File) {
				if (download != null) {
					this.download.download();
				}else{
					Browser.alert("您目前没有下载文件的权限！请于管理员联系");
				}
			} else {
				this.selectedParentFolder = selectFile;
				this.fileGrid.reload();
			}
		}
	}

	/***
	 * 获取列表选中的文件
	 * 
	 * @return
	 */
	private FileUploadManager getSelectedFile() {
		Object[] selectFile = fileGrid.getSelectedValues();
		if (selectFile != null && selectFile.length > 0) {
			return (FileUploadManager) selectFile[0];
		}
		return null;
	}

	@Action(id = "showAddFileDialog")
	public void showAddFileDialog() {
		fileUploadDialog.show();
	}

	@Action(id = "refreshFile")
	public void refreshFile() {
		this.fileGrid.reload();
	}

	@Accessible
	@Bind(id = "fileUploadDialog")
	private UIFileUploadDialog fileUploadDialog;

	/******
	 * 上传成功完成时执行事件
	 */
	@Action
	public void fileUploadDialog_onuploadcomplete() {
		this.fileGrid.reload();
		this.folderTree.repaint();
//		this.folderTree.reload();
	}

	public void processFileUpload(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ServletFileUpload upload = new ServletFileUpload();
		InputStream stream = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			if (ServletFileUpload.isMultipartContent(request)) {
				if (fileUploadDialog.getFileSizeMax() != null) {
					upload.setSizeMax(fileUploadDialog.getFileSizeMax());
				}
				FileItemIterator iter = upload.getItemIterator(request);
				while (iter.hasNext()) {
					FileItemStream item = iter.next();
					stream = item.openStream();
					if (!item.isFormField()) {
						String name = item.getFieldName();
						String filename = new File(item.getName()).getName();

						FileUploadManager file = new FileUploadManager();
						if (selectedParentFolder != null) {
							if (this.selectedParentFolder.getType() == FileType.File) {
								file.setParent(this.selectedParentFolder
										.getParent());
							} else {
								file.setParent(this.selectedParentFolder);
							}
						} else {
							file.setParent(null);
						}
						file.setPath(getSavePath()
								+ System.getProperty("file.separator")
								+ filename);
						file.setFilename(filename);
						file.setTitle(filename);
						file.setType(FileType.File);
						if (!fileUploadManagerBo.isRepeatByFileName(file,
								filename)) {// 文件中在当前目录下重复
							int fileindex = fileUploadManagerBo
									.findByFileNameIndex(file.getParent(),
											filename);
							filename = Utils.fileNameNotExtension(filename)
									+ "(" + String.valueOf(fileindex) + ")."
									+ Utils.fileExtension(filename);
							file.setPath(getSavePath()
									+ System.getProperty("file.separator")
									+ filename);
							file.setFilename(filename);
							file.setTitle(filename);
						} else {
							file.setPath(getSavePath()
									+ System.getProperty("file.separator")
									+ filename);
							file.setFilename(filename);
							file.setTitle(filename);
						}
						bis = new BufferedInputStream(stream);
						bos = new BufferedOutputStream(new FileOutputStream(
								new File(doccumentRootPathConfig
										.getRealRootPath()
										+ getSavePath()
										+ System.getProperty("file.separator")
										+ filename)));
						fileUploadManagerBo.uploadFile(bis, bos, file);
					}
				}
				response.getWriter().write("{success:true}");
			}
		} catch (FileUploadException e) {
			response.getWriter().write(
					String.format("{success:false,message:'%s'}", e
							.getMessage()));
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (Exception e) {
				}
			}
			if (bis != null) {
				try {
					bis.close();
				} catch (Exception e) {
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (Exception e) {
				}
			}
		}
	}

	@Bind(id = "download")
	private UIFileDownload download;

	@Bind(id = "download", attribute = "src")
	private InputStream getDownload() {
		String path = doccumentRootPathConfig.getRealRootPath()
				+ selectFile.getPath();
		File file = new File(path);
		if (file.exists() && file.isFile()) {
			try {
				FileInputStream fis = new FileInputStream(file);
				return fis;
			} catch (FileNotFoundException ex) {
				Browser.alert("文件已不存在！，不能下载！");
				return null;
			}
		}
		return null;
	}

	@Bind(id = "download", attribute = "saveName")
	public String getSaveName() {
		// FileUploadManager selectedFile = getSelectedFile();
		if (this.selectFile != null) {
			if (selectFile.getType() == FileType.File) {// 文件，下载
				return selectFile.getFilename();
			}
		}
		return null;
	}

	@Action(id = "goUp")
	public void goUp() {
		if (this.selectedParentFolder != null) {
			if (selectedParentFolder.getType() == FileType.File) {// 如果是当前选中的是文件，父级文件夹就该
				// 是上两层
				this.selectedParentFolder = this.selectedParentFolder
						.getParent().getParent();
			} else {
				this.selectedParentFolder = this.selectedParentFolder
						.getParent();
			}
			this.fileGrid.reload();
		} else {
			Browser.alert("已经是最顶层了！");
		}
	}

	@Action(id = "downloadButton")
	public void downloadButton() {
		if (this.selectFile != null) {
			if (selectFile.getType() == FileType.File) {// 文件，下载
				String path = doccumentRootPathConfig.getRealRootPath()
						+ selectFile.getPath();
				File file = new File(path);
				if (file.exists() && file.isFile()) {
					if (download != null) {
						this.download.download();
					}else{
						Browser.alert("您目前没有下载文件的权限！请于管理员联系");
					}
				} else {
					Browser.alert("文件已丢失！，不能下载！");
				}
			} else {
				Browser.alert("不能下载文件夹！请选择择文件下载！");
			}
		}
	}
}
