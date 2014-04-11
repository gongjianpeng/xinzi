package cn.internalaudit.audit.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.operamasks.faces.annotation.Action;
import org.operamasks.faces.annotation.Bind;
import org.operamasks.faces.annotation.SaveState;
import org.operamasks.faces.component.layout.impl.UIWindow;
import org.operamasks.faces.component.widget.UIFileUpload;
import org.operamasks.faces.component.widget.fileupload.FileUploadItem;

public class UploadBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * processListener必须绑定在一个参数为FileUploadItem的无返回值方法上，通过FileUploadItem参数，我们可以拿到
	 * file input框的客户端文件名，输入框的id等信息，并可以通过FileUploadItem.openStream()方法，获取上传的文件流。
	 * 这个例子里，我们简单的将文件流令存为一个服务器上的文件，在实际的应用中，这里可以将文件数据流持久到数据库中，或者 做任何你想要的处理。
	 * 
	 * @param fileUploadItem
	 */

	public void processUpload(FileUploadItem fileUploadItem) {
		InputStream input = null;
		OutputStream output = null;
		try {
			input = fileUploadItem.openStream();
			if (input != null && UIFileUpload.END_UPLOADING == -1) {
				// this.setByteContext(Utils.InputStreamToByte(input));
				String path = fileUploadItem.getName();
				this.setFileName(this.getLongFileName(path.substring(path.lastIndexOf(System
						.getProperty("file.separator")) + 1)));
				this
						.setSavePath("/" + this.getFile_path()
								+ this.getFileName());
				File file = new File(this.getSavePath(this.getFile_path(), this
						.getFileName()));
				output = new FileOutputStream(file);
				byte[] buf = new byte[4096];
				// UIFileUpload.END_UPLOADING为-1，这里表示数据输入流已经读取完毕
				int length = UIFileUpload.END_UPLOADING;
				while ((length = input.read(buf)) != UIFileUpload.END_UPLOADING) {
					output.write(buf, 0, length);
				}
				this.setSize(Utils.fileSize(file.length()));
			}
		} catch (IOException e) {
			throw new FacesException(e);
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {

				}
			}
		}

	}

	/**
	 * 可以在这里处理其它非file input的form元素
	 */
	public void action() {
		System.out.println("You can process other non-fileInput fields here");
	}

	/***************************************************************************
	 * 获得服务器真实路径
	 * 
	 * @param file_path
	 *            目标目录
	 * @param fileName
	 *            文件名
	 * @return
	 */
	protected String getSavePath(String file_path, String fileName) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext context = (ServletContext) facesContext
				.getExternalContext().getContext();
		String folder = context.getRealPath("/") + file_path;
		File dir = new File(folder);
		if (!dir.exists()) {
			dir.mkdir();
		}

		String path = context.getRealPath("/") + file_path + fileName;
		return path;
	}

	public String getLongFileName(String fileName) {
		String longFileName = "_"
				+ fileName.substring(0, fileName.indexOf(".")).toString()
				+ System.currentTimeMillis()
				+ fileName.substring(fileName.indexOf("."),
						fileName.length()).toString();

		return longFileName;
	}

	private byte[] byteContext;
	/***************************************************************************
	 * 文件名
	 */
	@SaveState
	private String fileName;
	/***************************************************************************
	 * 存入数据库的文件路径
	 */
	@SaveState
	private String savePath;
	/***************************************************************************
	 * 保存文件的目标目录
	 */
	@SaveState
	private String file_path;
	@SaveState
	private String size;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getByteContext() {
		return byteContext;
	}

	public void setByteContext(byte[] tempbyte) {
		this.byteContext = tempbyte;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	@Bind
	protected UIWindow uploaddialog;

	@Action
	public void showupload() {
		this.uploaddialog.show();
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
}
