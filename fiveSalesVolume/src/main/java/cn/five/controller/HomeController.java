package cn.five.controller;

import java.util.Map;

import cn.five.cache.EhcacheOperation;

import com.jfinal.core.Controller;

public class HomeController extends Controller{

	public void index(){
		
		Map<String,Object> map = EhcacheOperation.get("dictionary");
	
		System.out.println("-----------------------------------------");
		/*System.out.println("appId:"+map.get("appId"));
		System.out.println("appSecret:"+map.get("appSecret"));*/
		System.out.println("-----------------------------------------");
		
		renderText("登录成功");
	}
}
