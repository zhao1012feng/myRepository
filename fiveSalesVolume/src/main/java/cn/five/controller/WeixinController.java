package cn.five.controller;

import java.io.BufferedReader;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.five.common.model.CommonInfo;
import cn.five.util.CommonTool;
import cn.five.vo.ReturnVo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.mchange.lang.StringUtils;

/**
 * 微信
 * @author WH
 *
 */
public class WeixinController extends Controller{
	public void index(){
		render("success.jsp");
	}
	
	public void saveInfo() throws Exception{
	/*	String data = getPara();*/
		
		String wholeStr = getPara("wuzhou");
		System.out.println(wholeStr);
		ReturnVo returnVo;
		if(wholeStr!=null&&!"".equals(wholeStr)){
			returnVo = parseJsonObject(wholeStr);
		}else{
			returnVo=new ReturnVo(false,"失败");
		}
		renderJson(JSONObject.toJSON(returnVo));
		//
		
		
		
		/*HttpServletRequest request2 = getRequest();
		BufferedReader br = request2.getReader();

		String str, wholeStr = "";
		while((str = br.readLine()) != null){
			wholeStr += str;
		}
		
		//解析传递过来的参数封装到实体类病保存
		*/
	}
	private ReturnVo parseJsonObject(String wholeStr) {
		ReturnVo returnVo = null;
		CommonInfo commonInfo = null;
		JSONObject js = JSONObject.parseObject(wholeStr);
		String serialNum = CommonTool.getUUID();
		String area = js.getString("area");
		String bank = js.getString("bank");
		String name = js.getString("name");
		String openid = js.getString("openid");
		String productLine = js.getString("productLine");
		String createUser = js.getString("createUser");
		String userHead = js.getString("userHead");
		String commonProductId="";
		String commonProduct="";
		String commonCount="";
		String commonSalesVolume="";
		String productAttr="";
		/*
		String importantProductId="";
		String importantProduct="";
		String importantCount="";
		String importantSalesVolume="";
		
		String otherProductId="";
		String otherProduct="";
		String otherCount="";
		String otherSalesVolume="";*/
		
		String team = (String) js.get("team");
		JSONArray jCommon = js.getJSONArray("productList");
		/*JSONArray jImportant = js.getJSONArray("important");
		JSONArray jOther = js.getJSONArray("other");*/
		try {
			if(!"".equals(jCommon)&&jCommon!=null){
				for(int i = 0 ; i < jCommon.size() ; i ++){
					JSONObject jj = jCommon.getJSONObject(i);
					if(!"".equals(jj.get("productCount"))&&!"".equals(jj.get("productName"))&&!"".equals(jj.get("productSales"))){
						commonInfo = new CommonInfo();
						commonProductId = (String)			jj.get("productId");
						commonProduct = (String)			jj.get("productName");
						commonSalesVolume = (String)		jj.get("productSales");
						productAttr = (String) jj.get("productAttr");
						commonCount = (String)			jj.get("productCount");
						//封装
						commonInfo.setId(CommonTool.getUUID());
						commonInfo.setArea(area);
						commonInfo.setTeam(team);
						commonInfo.setBank(bank);
						commonInfo.setName(name);
						commonInfo.setProductLine(productLine);
						//commonInfo.setCreateUser(createUser);
						commonInfo.setUserHead(userHead);
						commonInfo.setStatus(1);
						commonInfo.setOpenid(openid);
						commonInfo.setProductAttr(productAttr);
						commonInfo.setCommonProductId(commonProductId);
						commonInfo.setCommonProduct(commonProduct);
						commonInfo.setCommonCount(commonCount);
						commonInfo.setCommonSalesVolume(commonSalesVolume);
						commonInfo.setCreateTime(new Date());
						commonInfo.setSerialNum(serialNum);
						commonInfo.save();
					}
				}
				returnVo = new ReturnVo(true,"成功");
			}else{
				returnVo = new ReturnVo(false,"产品不能都为空");
			}
			if(commonInfo==null||"".equals(commonInfo)){
				returnVo = new ReturnVo(false,"产品不能都为空");
			}
		} catch (Exception e) {
			returnVo = new ReturnVo(false, "保存失败");
		}
		/*if(!"".equals(jImportant)&&jImportant!=null){
			for(int i = 0 ; i < jImportant.size() ; i ++){
				JSONObject jj = jImportant.getJSONObject(i);
				importantProductId += (String)		jj.get("productId");   
				importantProduct += (String) 		jj.get("productName"); 
				importantCount += (String) 			jj.get("productSales");
				importantSalesVolume += (String) 	jj.get("productCount");
				if((i+1)<jImportant.size()){
					importantProductId += ",";
					importantProduct += ",";
					importantCount += ",";
					importantSalesVolume += ",";
				}
			}
		}
		if(!"".equals(jOther)&&jOther!=null){
			for(int i = 0 ; i < jOther.size() ; i ++){
				JSONObject jj = jOther.getJSONObject(i);
				otherProductId += (String) 			jj.get("productId");   
				otherProduct += (String) 			jj.get("productName"); 
				otherCount += (String) 				jj.get("productSales");
				otherSalesVolume += (String) 		jj.get("productCount");
				if((i+1)<jOther.size()){
					otherProductId += ",";
					otherProduct += ",";
					otherCount += ",";
					otherSalesVolume += ",";
				}
			}
		}
		commonInfo.setImportantProductId(importantProductId);
		commonInfo.setImportantProduct(importantProduct);
		commonInfo.setImportantCount(importantCount);
		commonInfo.setImportantSalesVolume(importantSalesVolume);
		
		commonInfo.setOtherProductId(otherProductId);
		commonInfo.setOtherProduct(otherProduct);
		commonInfo.setOtherCount(otherCount);
		commonInfo.setOtherSalesVolume(otherSalesVolume);*/
		
		return returnVo;
	}

	/**
	 * 
	 */
	public void query(){
		String area = null;
		String bank = null;
		String name = null;
		String team = null;
		String openid = getPara("openids");
		JSONObject jsonObject = new JSONObject();
		System.out.println(openid);
		if(openid!=null&&!"".equals(openid)){
			String sql = "select *  from common_info where status=1 AND openid="+openid+" ORDER BY create_time DESC";
			List<CommonInfo> find = CommonInfo.dao.find(sql);
			if(find.size()>0){
				CommonInfo commonInfo = find.get(0);
				if(!"".equals(commonInfo)&&commonInfo!=null){
					area = commonInfo.getArea();
					bank = commonInfo.getBank();
					name = commonInfo.getName();
					team = commonInfo.getTeam();
				}
			}
		}
		jsonObject.put("area", area);
		jsonObject.put("bank", bank);
		jsonObject.put("name", name);
		jsonObject.put("team", team);
		renderJson(JSONObject.toJSON(jsonObject));
	}
	
	
}
