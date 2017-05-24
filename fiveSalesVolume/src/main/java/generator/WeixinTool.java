package generator;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import cn.five.weixin.WeixinUtil;

/**
 * 微信URL生成
 * @author WH
 *
 */
public class WeixinTool {
	public static void main(String[] args) throws UnsupportedEncodingException {
		//公众号的唯一标识 - 金银岛
		String appid = "wx15069e85062d9ae3";
		//自己
		appid = "wxa8cae7fb97f88331";
		//河
		//appid="wx162987fd5cfefa98";
		//田
		//appid="wx3b535071ab8722b8";
		
		
		//授权后重定向的回调链接地址，请使用urlencode对链接进行处理
		
		String redirect_uri = "http://xiaowu.tunnel.qydev.com/weixin";
		redirect_uri = "http://xiaowu.tunnel.qydev.com/greetingCard/weixin?id=1";
		
		redirect_uri = URLEncoder.encode(redirect_uri,"UTF-8");
		
		//返回类型，请填写code
		String response_type = "code";
		//应用授权作用域，snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
		String scope = "snsapi_base";
		//重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
		String state = "";
		state = WeixinUtil.wxState;
		String url ="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri="+redirect_uri+"&response_type="+response_type+"&scope="+scope+"&state="+state+"#wechat_redirect";
		
		System.out.println(url);
		
	}

}
