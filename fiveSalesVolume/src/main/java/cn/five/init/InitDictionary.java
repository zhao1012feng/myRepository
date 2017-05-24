package cn.five.init;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.ehcache.CacheKit;

import cn.five.cache.EhcacheOperation;
import cn.five.common.model.CommonInfo;
import cn.five.weixin.WeixinUtil;

/**
 * 初始化字典
 * @author WH
 *
 */
public class InitDictionary {

	/*public static Map<String, Object> getDictionary(){
		
		List<Dictionary> lists =  Dictionary.dao.find("select * from common_info");
		
		Map<String, Object> map = new HashMap<String,Object>();
		for (Dictionary dictionary : lists) {
			map.put(dictionary.getName(), dictionary.getContent());
		}
		WeixinUtil.APPID = map.get("appId")+"";
		WeixinUtil.APPSECRET = map.get("appSecret")+"";
		EhcacheOperation.put("dictionary", map);
		
		return map;
	}*/
	
}
