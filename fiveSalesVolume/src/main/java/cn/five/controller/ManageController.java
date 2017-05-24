package cn.five.controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;

/**
 * 后台管理
 * @author WH
 *
 */
public class ManageController extends Controller{

	/** 
	 * 进入登录页面
	 */
	public void index(){
		render("tUser/login.jsp");
	}
	
	//首页
	public void home(){
		System.out.println("首页。。。");
		render("tUser/index.jsp");
	}
	
	/**
	 * 用户登录
	 */
	public void login(){
		String account = getPara("account");
		String password = getPara("password");
		
		String administratorAccount = PropKit.get("administratorAccount");
		String administratorPassword = PropKit.get("administratorPassword");
		
		if(administratorAccount.equals(account)&&administratorPassword.equals(password)){
			setSessionAttr("loginUser", "login");
			//render("backstage/index.jsp");
			redirect("/manage/home");
		}else{
			if(account == null || password ==null || "".equals(account) || "".equals(password)){
				setAttr("errorMessage", "请输入账号密码");
			}else{
				setAttr("errorMessage", "账号密码错误");
			}
			render("tUser/login.jsp");
		}
	}
	
	/**
	 * 退出
	 */
	public void exit(){
		
		removeSessionAttr("loginUser");
		render("tUser/login.jsp");
	}
	

}
