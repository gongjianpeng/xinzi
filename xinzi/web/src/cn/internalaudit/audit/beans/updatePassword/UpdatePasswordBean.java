package cn.internalaudit.audit.beans.updatePassword;

import java.util.Locale;
import java.util.Map;

import org.operamasks.faces.annotation.Action;
import org.operamasks.faces.annotation.Bind;
import org.operamasks.faces.annotation.Inject;
import org.operamasks.faces.annotation.LocalString;
import org.operamasks.faces.annotation.ManagedBean;
import org.operamasks.faces.annotation.ManagedBeanScope;
import org.operamasks.faces.annotation.ManagedProperty;
import org.operamasks.faces.annotation.ValidateLength;
import org.operamasks.faces.user.util.Browser;

import cn.internalaudit.audit.bo.IPersonBo;
import cn.internalaudit.audit.entitys.Person;
import cn.internalaudit.audit.utils.Utils;

@ManagedBean(name = "pages.updatePassword.updatePasswordBean", scope = ManagedBeanScope.REQUEST)
public class UpdatePasswordBean {	
	@Inject("#{PersonBo}")
	private IPersonBo personBo;
	@LocalString(basename = "messages.messages_audthoize")
	private Map<String, String> messages;
	@Bind
	private String oldPassword;
	
	@Bind
	@ManagedProperty
	@ValidateLength(minimum=6,maximum=18)
	private String newPassword;
	
	@ManagedProperty("#{loginInfo.locale}")
	private Locale locale;
	
	@Bind
	@ManagedProperty
	@ValidateLength(minimum=6,maximum=18)
	private String repeatNewPassword;
	
	@ManagedProperty("#{loginInfo.loginPerson.loginName}")
	private String loginName;
	
	@ManagedProperty("#{loginInfo.loginPerson}")
	private Person person;
	
	@ManagedProperty("#{loginInfo.loginPerson.password}")
	private String loginPassword;
	
	@Action
	public String checkPassword(){
		if(oldPassword.trim()!=null&&oldPassword.trim()!=""){
			String oldPassword1=Utils.encodeSHA(oldPassword,loginName);
			if(oldPassword1.equals(loginPassword)){
				if(newPassword.trim()!=null&&newPassword.trim()!=""&&repeatNewPassword.trim()!=null&&repeatNewPassword.trim()!=""){
					if(newPassword.trim().equals(repeatNewPassword.trim())){
					    Person person1= personBo.find(person.getId());
						person1.setPassword(Utils.encodeSHA(newPassword,loginName));
						personBo.save(person1);
						this.newPassword="";
						this.oldPassword="";
						this.repeatNewPassword="";
						Browser.execClientScript("alert('"+messages.get("updatePassword.success")+"')");
						return "";
					}else{
						Browser.execClientScript("alert('"+messages.get("updatePassword.confirmPasswordSame")+"')");
						return "";
					}
				}else{
					Browser.alert(messages.get("updatePassword.enterPassword"));
					return "";
				}
			}else{
				Browser.alert(messages.get("updatePassword.confirmPasswordSame"));
				return "";
			}
		}else{
			Browser.alert(messages.get("updatePassword.enterOldPassword"));
			return "";
		}
	}
	@Action
	public void reset(){
		oldPassword="";
		newPassword="";
		repeatNewPassword="";
	}
}
