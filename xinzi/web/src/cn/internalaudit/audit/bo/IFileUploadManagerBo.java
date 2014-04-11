package cn.internalaudit.audit.bo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.internalaudit.audit.base.IBo;
import cn.internalaudit.audit.entitys.FileUploadManager;


public interface IFileUploadManagerBo extends IBo<FileUploadManager> {
	/****
	 * 按标题，或都文件名模糊查询文件
	 * 
	 * @param titleOrFileName
	 *            标题或文件名
	 * @return
	 */
	public List<FileUploadManager> findByTitle(String titleOrFileName);

	/****
	 * 查询指定时间以后的工作建议
	 * 
	 * @param createDate
	 * @return
	 */
	public List<FileUploadManager> findByParentId(Long parentId);

	/****
	 * 创建文件及文件夹
	 * 
	 * @param folder
	 * @return
	 * @throws IOException
	 */
	@Transactional(readOnly = false)
	public FileUploadManager createFolder(FileUploadManager folder)
			throws IOException;

	/*******
	 * 重命名文件及文件夹
	 * 
	 * @param folder
	 * @param newName
	 */
	@Transactional(readOnly = false)
	public void reNameFolder(FileUploadManager folder, String newName);

	/*******
	 * 删除文件及文件夹
	 * 
	 * @param folder
	 */
	@Transactional(readOnly = false)
	public void removeFolder(FileUploadManager folder) throws IOException;

	/*****
	 * 上传文件，并何存文件路径径信息到数据库
	 * @param bis
	 * @param bos
	 * @param file
	 * @throws IOException
	 */
	@Transactional(readOnly = false)
	public void uploadFile(BufferedInputStream bis, BufferedOutputStream bos,
			FileUploadManager file) throws IOException;
	/****
	 * 文件名是否重复
	 * @param file
	 * @param fileName
	 * @return
	 */
	public boolean isRepeatByFileName(FileUploadManager file, String fileName) ;
	
	/****
     * 查询当前父文件夹下此文件名的索引号,
     * @param parentFile
     * @param filename
     * @return
     */
    public int findByFileNameIndex(FileUploadManager parentFile,String filename);
//	/****
//	 * 删 除文件并删除数据库文件中的文件信
//	 * @param file
//	 */
//	@Transactional(readOnly = false)
//	public void removeFile(FileUploadManager file);
}
