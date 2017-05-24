package cn.five.weixin;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import cn.five.util.HttpUtil;
import cn.five.weixin.po.AccessToken;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.PropKit;


/**
 * 微信工具
 *
 */
public class WeixinUtil {
	public static  String APPID = PropKit.get("appId");
	public static  String APPSECRET = PropKit.get("appSecret");

	//微信授权标识״̬
	public final static String wxState = "1d18efad-b664-42f7-6253-c93dc13c91a11d18efad-b664-42f7-9464-c93dc13c91a11d18efad-b664-42f7-9464-c93dc13c91a1";

	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	private static final String TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	
	private static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	
	private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	private static final String QUERY_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	
	private static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	
	
	// 通过code换取网页授权access_token
	public final static String CODE_GET_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		
	/**
	 * get请求
	 * @param url
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static JSONObject doGetStr(String url) throws ParseException, IOException{
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		JSONObject jsonObject = null;
		HttpResponse httpResponse = client.execute(httpGet);
		HttpEntity entity = httpResponse.getEntity();
		if(entity != null){
			String result = EntityUtils.toString(entity,"UTF-8");
			jsonObject = JSON.parseObject(result);
		}
		return jsonObject;
	}
	
	/**
	 * POST请求
	 * @param url
	 * @param outStr
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static JSONObject doPostStr(String url,String outStr) throws ParseException, IOException{
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(url);
		JSONObject jsonObject = null;
		httpost.setEntity(new StringEntity(outStr,"UTF-8"));
		HttpResponse response = client.execute(httpost);
		String result = EntityUtils.toString(response.getEntity(),"UTF-8");
		jsonObject = JSON.parseObject(result);
		return jsonObject;
	}
	

	/**
	 * 根据code获取token
	 * @param code
	 * @return
	 */
	public static JSONObject getAccessToken(String code) {
	
		String requestUrl = CODE_GET_TOKEN_URL.replace("APPID", APPID).replace("SECRET", APPSECRET).replace("CODE", code);
		JSONObject jsonObject =null;
		try {
			jsonObject = HttpUtil.doGetStr(requestUrl);
			// 如果请求成功
			if (null != jsonObject) {
				return jsonObject;
			}
		} catch (Exception e) {
		}
		return null;
	}
	
	/**
	 * 获取accessToken
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static AccessToken getAccessToken() throws ParseException, IOException{
		AccessToken token = new AccessToken();
		String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
		JSONObject jsonObject = doGetStr(url);
		if(jsonObject!=null){
			token.setToken(jsonObject.getString("access_token"));
			token.setExpiresIn(jsonObject.getInteger("expires_in"));
		}
		return token;
	}
	
	
	/**
	 * 获取 jsapi_ticket
	 * @param token
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static JSONObject getTicket(String token) throws ParseException, IOException{
		String url = TICKET_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = doGetStr(url);
		return jsonObject;
	}

	
}

