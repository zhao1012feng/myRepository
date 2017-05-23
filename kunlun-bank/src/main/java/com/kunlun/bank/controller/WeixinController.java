package com.kunlun.bank.controller;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.kunlun.bank.model.OrderInfo;
import com.kunlun.bank.util.CommonTool;
import com.kunlun.bank.vo.ReturnVo;

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
		OrderInfo orderInfo = null;
		JSONObject js = JSONObject.parseObject(wholeStr);
		String serialNum = CommonTool.getUUID();
		Integer orgNo = js.getInteger("orgNo");
		String bank = js.getString("area");
		
		String orgName = js.getString("team");
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
		
		JSONArray jCommon = js.getJSONArray("productList");
		/*JSONArray jImportant = js.getJSONArray("important");
		JSONArray jOther = js.getJSONArray("other");*/
		try {
			if(!"".equals(jCommon)&&jCommon!=null){
				for(int i = 0 ; i < jCommon.size() ; i ++){
					JSONObject jj = jCommon.getJSONObject(i);
					if(!"".equals(jj.get("productCount"))&&!"".equals(jj.get("productName"))&&!"".equals(jj.get("productSales"))&&
							jj.get("productCount")!=null&&jj.get("productSales")!=null&&!"0".equals(jj.get("productCount"))){
						orderInfo = new OrderInfo();
						commonProductId = 			jj.get("productId")+"";
						commonProduct = (String)			jj.get("productName");
						commonSalesVolume = 		jj.get("productSales").toString();
						productAttr = (String) jj.get("productAttr");
						commonCount = (String)			jj.get("productCount");
						//封装
						orderInfo.setId(CommonTool.getUUID());
						orderInfo.setOrgNo(orgNo);
						orderInfo.setOrgName(orgName);
						orderInfo.setBank(bank);
						orderInfo.setName(name);
						orderInfo.setProductLine(productLine);
						//orderInfo.setCreateUser(createUser);
						orderInfo.setUserHead(userHead);
						orderInfo.setStatus(1);
						orderInfo.setOpenid(openid);
						orderInfo.setProductAttr(productAttr);
						orderInfo.setCommonProductId(commonProductId);
						orderInfo.setCommonProduct(commonProduct);
						orderInfo.setCommonCount(commonCount);
						orderInfo.setCommonSalesVolume(commonSalesVolume);
						orderInfo.setCreateTime(new Date());
						orderInfo.setSerialNum(serialNum);
						orderInfo.save();
					}
				}
				returnVo = new ReturnVo(true,"成功");
			}else{
				returnVo = new ReturnVo(false,"产品不能都为空");
			}
			if(orderInfo==null||"".equals(orderInfo)){
				returnVo = new ReturnVo(false,"产品不能都为空");
			}
		} catch (Exception e) {
			System.out.println(e);
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
		orderInfo.setImportantProductId(importantProductId);
		orderInfo.setImportantProduct(importantProduct);
		orderInfo.setImportantCount(importantCount);
		orderInfo.setImportantSalesVolume(importantSalesVolume);
		
		orderInfo.setOtherProductId(otherProductId);
		orderInfo.setOtherProduct(otherProduct);
		orderInfo.setOtherCount(otherCount);
		orderInfo.setOtherSalesVolume(otherSalesVolume);*/
		
		return returnVo;
	}

	/**
	 * 
	 */
	public void query(){
		Integer orgNo = null;
		String bank = null;
		String name = null;
		String orgName = null;
		String openid = getPara("openids");
		JSONObject jsonObject = new JSONObject();
		System.out.println(openid);
		if(openid!=null&&!"".equals(openid)){
			String sql = "select *  from order_info where status=1 AND openid="+openid+" ORDER BY create_time DESC";
			List<OrderInfo> find = OrderInfo.dao.find(sql);
			if(find.size()>0){
				OrderInfo orderInfo = find.get(0);
				if(!"".equals(orderInfo)&&orderInfo!=null){
					orgNo = orderInfo.getOrgNo();
					bank = orderInfo.getBank();//父级机构
					name = orderInfo.getName();
					orgName = orderInfo.getOrgName();
				}
			}
		}
		String sqlOrg = "select * from org_info where org_grade='分行'" ;
		String sqlOrg1 = "select * from org_info where org_grade='支行' order by org_father";
		
		String sqlPrice = "select * from price_info ";
		
		List<Record> findOrg1 = Db.find(sqlOrg1);
		List<Record> findOrg = Db.find(sqlOrg);
		List<Record> findPrice = Db.find(sqlPrice);
		String []orgs = new String[findOrg.size()];
		for(int i = 0 ; i < findOrg.size() ; i++){
			orgs[i] = findOrg.get(i).get("org_name").toString().trim();
		}
		int j = 0;
		JSONObject jsonOrg = new JSONObject();
		
		while( j<findOrg.size()){
			String aaa = "";
			for (int i = j ; i < findOrg1.size() ; i ++){
				if(findOrg1.get(i).get("org_father").toString().trim().equals(orgs[j].trim())){
					if("".equals(aaa)){
						aaa = findOrg1.get(i).get("org_name").toString().trim();
					}else{
						aaa = aaa+","+findOrg1.get(i).get("org_name").toString().trim();
					}
				}
			}
			
			jsonOrg.put(orgs[j], aaa.split(","));
			//jaOrg.add(jsonOrg);
			j++;
		}
		
		JSONArray ja = new JSONArray();
		JSONArray ja1 = new JSONArray();
		for (int i = 0 ; i < findPrice.size() ; i++){
			if(findPrice.get(i).get("product_attr").equals("001")){
				JSONObject json = new JSONObject();
				json.put("productId", findPrice.get(i).get("id"));
				json.put("productAttr", "001");
				json.put("productName", findPrice.get(i).get("product_name")+" / "+findPrice.get(i).get("wight").toString()+" / "+findPrice.get(i).get("gold"));
				json.put("sell_price", findPrice.get(i).get("sell_price"));
				ja.add(json);
			}else{
				JSONObject json = new JSONObject();
				json.put("productId", findPrice.get(i).get("id"));
				json.put("productAttr", "002");
				json.put("productName", findPrice.get(i).get("product_name")+" / "+findPrice.get(i).get("wight").toString()+" / "+findPrice.get(i).get("gold"));
				json.put("sell_price", findPrice.get(i).get("sell_price"));
				ja1.add(json);
			}
		}
		jsonObject.put("orgs", orgs);
		jsonObject.put("jsonOrg", jsonOrg);
		jsonObject.put("findPrice", findPrice);
		//jsonObject.put("bank", bank);
		jsonObject.put("data", ja);
		jsonObject.put("data1", ja1);
		jsonObject.put("name", name);
		jsonObject.put("orgName", orgName);
		jsonObject.put("orgNo", orgNo);
		jsonObject.put("area", bank);
		jsonObject.put("team", orgName);
		renderJson(JSONObject.toJSON(jsonObject));
	}
	public void queryVersion(){
		String sql = "select max(version) version from version ";
		Record record = Db.findFirst(sql);
		Integer version = 0;
		if(record!=null&&!"".equals(record)){
			version = record.get("version");
		}
		renderJson(JSONObject.toJSON(version));
	}
	
}