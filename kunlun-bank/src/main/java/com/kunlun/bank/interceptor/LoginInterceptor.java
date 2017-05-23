package com.kunlun.bank.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class LoginInterceptor implements Interceptor {
	
	@Override
	public void intercept(Invocation ai) {
		Controller controller = ai.getController();
		String methodName = ai.getMethodName();
		String key = ai.getActionKey();
		/*String ip = IpKit.getRealIp(controller.getRequest());
		Log log = new Log();
		log.setIp(ip);
		log.setTime(new Date());
		log.setUrl(key);
		log.setType(methodName);
		log.setDescribe(StaticParameter.methodDescribe.get(key));
		new Thread(new LogInfo(log)).start();*/
		
		//后台权限需要登录后才能操作
		if(key.contains("tUser")){
			
			String loginUser = controller.getSessionAttr("loginUser");
			if(loginUser !=null ){
				ai.invoke();
			}else{
				controller.redirect("/manage/login");
			}
		}else{
			ai.invoke();
		}
	}
}

/*class LogInfo implements Runnable{

	private Log log ;
	
	public LogInfo(Log logData) {
		log = logData;
	}
	
	@Override
	public void run() {
		Log.dao.addLog(log);
	}
	
}*/