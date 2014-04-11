package cn.internalaudit.audit.beans.filemanager;

import java.io.Serializable;
/****
 * 文档管理实际路径配置
 * @author zhang yong
 *
 */
public class DoccumentRootPathConfig implements Serializable{
	private String realRootPath;

	public String getRealRootPath() {
		return realRootPath;
	}

	public void setRealRootPath(String realRootPath) {
		this.realRootPath = realRootPath;
	}
}
