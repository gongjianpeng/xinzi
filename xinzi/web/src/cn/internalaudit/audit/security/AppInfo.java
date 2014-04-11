package cn.internalaudit.audit.security;

import java.io.Serializable;
/***
 * 配置程序的名称及Logo
 * @author zhangyong
 *
 */
@SuppressWarnings("all")
public class AppInfo implements Serializable {

	
	private String appName;
	
	private String iconUrl;
	

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	
}
