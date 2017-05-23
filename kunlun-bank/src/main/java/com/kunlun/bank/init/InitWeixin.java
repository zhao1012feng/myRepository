package com.kunlun.bank.init;

import java.io.IOException;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

/*public class InitWeixin implements Runnable {

	@Override
	public void run() {
		System.out.println("开始定时。。。");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}
		while (true) {
			try {
				// 获取token
				getToken();
			} catch (InterruptedException e) {
				try {
					Thread.sleep(60 * 1000);
				} catch (InterruptedException e1) {
				}
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void getToken() throws org.apache.http.ParseException, IOException,
	InterruptedException{
		// TODO Auto-generated method stub
		
	}

	public void getToken() throws org.apache.http.ParseException, IOException,
			InterruptedException {
		AccessToken accessToken = WeixinUtil.getAccessToken();
		if (null != accessToken) {

			// System.out.println("=================accessToken==============");

			EhcacheOperation.put("accessToken", accessToken);

			// 获取ticket
			JSONObject json = WeixinUtil.getTicket(accessToken.getToken());

			// System.out.println("=================ticket==============");
			System.out.println(json);

			EhcacheOperation.put("ticket", json.getString("ticket"));

			// 休眠6700秒
			Thread.sleep((accessToken.getExpiresIn() - 500) * 1000);
		} else {
			// 如果access_token为null，60秒后再获取
			Thread.sleep(60 * 1000);
		}
	}
}
*/