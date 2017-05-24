package cn.five.common.config;

import cn.five.common.model._MappingKit;
import cn.five.controller.HomeController;
import cn.five.controller.ManageController;
import cn.five.controller.TUserController;
import cn.five.controller.WeixinController;
import cn.five.init.InitDictionary;
import cn.five.init.InitWeixin;
import cn.five.interceptor.LoginInterceptor;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.ViewType;

/**
 * 核心配置文件
 * @author WH
 *
 */
public class Config  extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		//静态配置文件
		PropKit.use("static.properties");

		me.setDevMode(PropKit.getBoolean("devMode", false));

		//设置默认的视图类型
		me.setViewType(ViewType.JSP);
		//设置默认视图层路径
		me.setBaseViewPath("/WEB-INF/view");
	}

	@Override
	public void configRoute(Routes me) {
		
		//微信
		me.add("/" , WeixinController.class,"weixin");	
		
		me.add("/manage",ManageController.class,"/");
		
		me.add("/tUser",TUserController.class,"tUser");
	}
	
	
	public static C3p0Plugin createC3p0Plugin() {
		return new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
	}

	@Override
	public void configPlugin(Plugins me) {
		// 配置C3p0数据库连接池插件
		C3p0Plugin C3p0Plugin = createC3p0Plugin();
		me.add(C3p0Plugin);
		
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(C3p0Plugin);
		me.add(arp);
		
		// 所有配置在 MappingKit 中设置
		_MappingKit.mapping(arp);
		
		//缓存
		me.add(new EhCachePlugin());
	}

	/**
	 * 配置全局拦截器
	 */
	@Override
	public void configInterceptor(Interceptors me) {
		//登录拦截器
		me.add(new LoginInterceptor());
		
	}


	@Override
	public void configHandler(Handlers me) {
		
	}

	@Override
	public void afterJFinalStart() {

		System.out.println("=============项目启动===============");
		//初始化字典表数据
		//InitDictionary.getDictionary();

		//开启微信数据获取
		//new Thread(new InitWeixin()).start();
	}
	
}
