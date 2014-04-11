package cn.internalaudit.audit.security;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import cn.internalaudit.audit.bo.IPersonBo;
import cn.internalaudit.audit.entitys.Person;
import cn.internalaudit.audit.entitys.Roles;
import cn.internalaudit.audit.utils.Utils;

/*******************************************************************************
 * *
 * 
 * @author Administrator *
 */
@SuppressWarnings("all")
public class LoginSuccessListener implements ApplicationListener {
	@Autowired
	@Qualifier("PersonBo")
	private IPersonBo personBo;
	@Autowired
	@Qualifier("loginInfo")
	private LoginInfo loginInfo;

	private static Logger logger = Logger.getLogger(LoginSuccessListener.class
			.getName());

	public void onApplicationEvent(ApplicationEvent event) {
		/***********************************************************************
		 * 
		 */
		if (event instanceof AuthenticationSuccessEvent) {
			AuthenticationSuccessEvent authEvent = (AuthenticationSuccessEvent) event;
			Authentication auth = authEvent.getAuthentication();
			String username = auth.getName();
			List<Person> loginPersons = personBo.findByLoginName(username);
			if (loginPersons != null && loginPersons.size() > 0) {
				Person loginPerson = loginPersons.get(0);
				String  enddate=loginPerson.getEndDate();
				System.out.println("..."+enddate);
				
//				Date dateStart = new Date("2014/01/1");//admin设定的有效开始时间（DB）
//				Date dateEnd = new Date("2014/03/1");//admin设定的有效结束时间（DB）
//				
//				int m = dateEnd.getMonth()-dateStart.getMonth();
//				
//				Date currentDate=new Date();//当前时间
//				SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");//yyyy-DD-dd
//				
//				String current_DateStr=sdf.format(currentDate);
//				
//				Date current_Date=new Date(current_DateStr);//获取当前系统
//				
//				int n=current_Date.getMonth()-dateStart.getMonth();
//				
//				if(n>1){
//					System.err.println("有效期时间失效"+n+"");
//				}
				 Date date = new Date();
			        Date lastDate;
			    	try {
					lastDate = Utils.dateFormat.parse(enddate);
					if(date.compareTo(lastDate) > 0){
						System.out.println("-----测试时间  ------");
						
			        }else{
			        	
			        	System.out.println("-----测试时间  2------");
						
			        	
			        	if (loginPersons != null && loginPersons.size() > 0) {
						//	Person loginPerson = loginPersons.get(0);
							loginPerson.setName(loginPerson.getName());
				
							loginInfo.setLoginPerson(loginPerson);
							loginInfo.setDataLevelSet(getDataLevelSet(loginPerson));
						}
						// HGS ADD 20120703
						loginInfo.setLoginTime(new Date());
						org.springframework.security.web.authentication.WebAuthenticationDetails details = (WebAuthenticationDetails) auth
								.getDetails();
						String info = "登陆用户: " + username + " ,登陆IP："
								+ details.getRemoteAddress();
						logger.info(info);
			        }
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			

		}
	}
	public Set<String> getDataLevelSet(Person loginPerson){
		Set<String> dataLevelSet=new HashSet<String>();
		List<Roles> roles = loginPerson.getRoless();
		for(Roles r:roles){
			if(r.getDataAuthorLevel()!=null){
			   String[] levels=r.getDataAuthorLevel().split(":");
			   dataLevelSet.addAll(Arrays.asList(levels));
			}
			
		}
		return dataLevelSet;
	}
}
