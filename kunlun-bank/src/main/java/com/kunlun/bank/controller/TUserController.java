package com.kunlun.bank.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.DateUtil;





















import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.kunlun.bank.model.OrderInfo;
import com.kunlun.bank.model.TUser;
import com.kunlun.bank.util.ExportExcel;

public class TUserController extends Controller{
	private static final String[] headers1 = {"提交时间","姓名","分行","支行"};
	private static final String[] headers2 = { "提交时间","姓名","分行","总行","普通产品总件数"
			+ "","普通产品总销量","心经件数","心经销量","其他产品总件数","其他产品总销量"
			+ "","合计总件数","合计总销量"};
	
	public void listUserInfo() throws ParseException{
		String beginDate = getPara("beginDate")==null||"".equals(getPara("endDate")) ? "" : getPara("beginDate")+" 00:00:00";
		String endDate = getPara("endDate")==null||"".equals(getPara("endDate")) ? "" : getPara("endDate")+" 23:59:59";
		String bank = getPara("bank")==null||"".equals(getPara("bank")) ? "": getPara("bank");
		String orgName = getPara("orgName")==null||"".equals(getPara("orgName")) ? "": getPara("orgName");;
		Integer flag = getParaToInt("flag");
		
		
		Record sessionAttr = getSessionAttr("userLogin");	
		String sqlOrg = "select org_name from org_info where org_code = "+ sessionAttr.getStr("org_code");
		Record fatherOrg= Db.findFirst(sqlOrg);
		if(!"昆仑银行总行".equals(fatherOrg.getStr("org_name").trim())){
			bank = fatherOrg.getStr("org_name").trim();
		}
		
		/*String lastSql = "select id from order_info order by id desc LIMIT 1";
		Record findFirst = Db.findFirst(lastSql);
		Integer id = findFirst.get("id");
		if(!"".equals(beginDate)){
			beginDate = getSpecifiedDayBefore(beginDate);
		}
		String sql = "";
		if(flag==0){
			sql=sql(id);
		}else if(flag==1){
			sql=sqlCount();
		}else{
			sql=sqlSum();
		}
		if(!"".equals(beginDate)&&!"".equals(endDate)){
			sql += " and create_time BETWEEN "
					+ "'"+beginDate+ "'"
					+ " and "
					+ "'"+endDate+ "'" ;
		}
		if(flag!=1&&flag!=0&&flag!=2){
			sql += " group by serial_num ,area,team,name order by ct ";
			//sql += " group by serial_num,area,team,name order by ct ";
		}else{
			sql += " group by area,team,name order by ct";
		}
		Map<Integer, Object> map = new LinkedHashMap<Integer , Object>();
		List<Record> list = Db.find(sql);
		int count = 0 ; 
		for (Record record : list) {
			count++;
			Map<String, Object> m = record.getColumns();
			map.put(count, record);
		}
		setAttr("OrderInfoList", map);*/
		if(flag==0){
			List<Record> l = Db.find(sql_common_sales_volume_title());
			List<Record> l1 = Db.find(sql_common_sales_volume(beginDate,endDate,bank,orgName));
			List<String[]> list = new ArrayList<String[]>();
			String[] str;
			for(Record record1 : l1){
				
				str = new String[6+l.size()];
				str[0] = record1.get("create_time").toString();
				str[1] = record1.getStr("name");
				str[2] = record1.getStr("bank");
				str[3] = record1.getStr("org_name");
				
				/*json.put("name", record1.getStr("name"));
				json.put("org_name", record1.getStr("org_name"));
				json.put("bank", record1.getStr("bank"));*/
				
				String [] common_product_id_s = record1.getStr("common_product_id_s").split(",");
				String [] common_sales_volume_s = record1.getStr("common_sales_volume_s").split(",");
				/*for (int i = 0; i < common_product_id_s.length; i++) {
					
					for (Record record : l) {
						if(record.getStr("id").equals(common_product_id_s[i])){
							json.put("jg", common_sales_volume_s[i]);
							break;
						}
					}
					
				}*/
				BigDecimal count = BigDecimal.ZERO;
				BigDecimal countOther = BigDecimal.ZERO;
				for (int j = 0; j<l.size(); j++) {
					Record record = l.get(j);
					str[j+4] = "0";
					for (int i = 0; i < common_product_id_s.length; i++) {
						if((record.get("id")+"").equals(common_product_id_s[i]+"")){
							str[j+4] = common_sales_volume_s[i];
							count = count.add(new BigDecimal(common_sales_volume_s[i]));
						}
					}
				}
				for (int i = 0; i < common_product_id_s.length; i++) {
					if((common_product_id_s[i]+"").indexOf("9999") != -1){
						countOther = countOther.add(new BigDecimal(common_sales_volume_s[i]));
						
					}
				}
				str[str.length-2] = countOther.toString();
				str[str.length-1] = count.add(countOther).toString();
				System.out.println(str[str.length-1]);
				list.add(str);
			}
			setAttr("lists",  l);
			setAttr("listContent",  list);
			render("userWinningListPage.jsp");
		}else if(flag==1){
			List<Record> l = Db.find(sql_common_sales_volume_title());
			List<Record> l1 = Db.find(sql_common_sales_count(beginDate,endDate,bank,orgName));
			List<String[]> list = new ArrayList<String[]>();
			String[] str;
			for(Record record1 : l1){
				
				str = new String[6+l.size()];
				str[0] = record1.get("create_time").toString();
				str[1] = record1.getStr("name");
				str[2] = record1.getStr("bank");
				str[3] = record1.getStr("org_name");
				
				/*json.put("name", record1.getStr("name"));
				json.put("org_name", record1.getStr("org_name"));
				json.put("bank", record1.getStr("bank"));*/
				
				String [] common_product_id_s = record1.getStr("common_product_id_s").split(",");
				String [] common_sales_volume_s = record1.getStr("common_sales_count_s").split(",");
				/*for (int i = 0; i < common_product_id_s.length; i++) {
					
					for (Record record : l) {
						if(record.getStr("id").equals(common_product_id_s[i])){
							json.put("jg", common_sales_volume_s[i]);
							break;
						}
					}
					
				}*/
				BigDecimal count = BigDecimal.ZERO;
				BigDecimal countOther = BigDecimal.ZERO;
				for (int j = 0; j<l.size(); j++) {
					Record record = l.get(j);
					str[j+4] = "0";
					for (int i = 0; i < common_product_id_s.length; i++) {
						if((record.get("id")+"").equals(common_product_id_s[i]+"")){
							str[j+4] = common_sales_volume_s[i];
							count = count.add(new BigDecimal(common_sales_volume_s[i]));
						}
					}
				}
				for (int i = 0; i < common_product_id_s.length; i++) {
					if((common_product_id_s[i]+"").indexOf("9999") != -1){
						countOther = countOther.add(new BigDecimal(common_sales_volume_s[i]));
						
					}
				}
				str[str.length-2] = countOther.toString();
				str[str.length-1] = count.add(countOther).toString();
				System.out.println(str[str.length-1]);
				list.add(str);
			}
			setAttr("lists",  l);
			setAttr("listContent",  list);
			render("userCount.jsp");
		}else if(flag==2){
			Map<Integer, Object> map = new LinkedHashMap<Integer , Object>();
			List<Record> list = Db.find(sql_all(beginDate,endDate,bank,orgName));
			int count = 0 ; 
			for (Record record : list) {
				count++;
				Map<String, Object> m = record.getColumns();
				map.put(count, record);
			}
			setAttr("commonInfoList", map);
			render("userSumCount.jsp");
		}else {
			Map<Integer, Object> map = new LinkedHashMap<Integer , Object>();
			List<Record> list = Db.find(sql_single_all(beginDate,endDate,bank,orgName));
			int count = 0 ; 
			for (Record record : list) {
				count++;
				Map<String, Object> m = record.getColumns();
				map.put(count, record);
			}
			setAttr("commonInfoList", map);
			render("userSingleSumCount.jsp");
		}
	}
	String sql_common_sales_volume_title(){
		String title = "select id,CONCAT(product_name,'/',wight,'/',gold) as content,'001' code from price_info order by product_attr ";
		return title;
	}
	String sql_common_sales_volume(String create_time,String end_time,String bank,String orgName){
		String sqlWhere = " status=1 ";
		
		if(!"".equals(create_time)&&!"".equals(end_time)){
			sqlWhere += " and create_time between '"+create_time+"' and '"+ end_time +"'";
		}
		if(!"".equals(bank)){
			sqlWhere += " and bank like '%"+bank+"%'";
		}
		if(!"".equals(orgName)){
			sqlWhere += " and org_name like '%"+orgName+"%'";
		}
		
		String sql = "select (create_time) create_time ,name,bank,org_name,GROUP_CONCAT(common_product_id) common_product_id_s,GROUP_CONCAT(common_sales_volume) common_sales_volume_s from (select create_time,name,org_name,bank,common_product_id,sum(common_sales_volume) common_sales_volume from order_info where "+sqlWhere+" group by name,org_name,bank,common_product_id order by product_attr ) as a group by name,org_name,bank  ";
		return sql;
	}
	String sql_common_sales_count(String create_time,String end_time,String bank,String orgName){
		String sqlWhere = " status=1 ";
		
		if(!"".equals(create_time)&&!"".equals(end_time)){
			sqlWhere += " and create_time between '"+create_time+"' and '"+ end_time +"'";
		}
		if(!"".equals(bank)){
			sqlWhere += " and bank like '%"+bank+"%'";
		}
		if(!"".equals(orgName)){
			sqlWhere += " and org_name like '%"+orgName+"%'";
		}
		
		String sql = "select (create_time) create_time ,name,bank,org_name,GROUP_CONCAT(common_product_id) common_product_id_s,GROUP_CONCAT(common_count) common_sales_count_s from (select create_time,name,org_name,bank,common_product_id,sum(common_count) common_count from order_info where "+sqlWhere+" group by name,org_name,bank,common_product_id order by product_attr ) as a group by name,org_name,bank  ";
		return sql;
	}
	String sql_all(String create_time,String end_time,String bank,String orgName){
		String sqlWhere = " status=1 ";
		
		if(!"".equals(create_time)&&!"".equals(end_time)){
			sqlWhere += " and create_time between '"+create_time+"' and '"+ end_time +"'";
		}
		if(!"".equals(bank)){
			sqlWhere += " and bank like '%"+bank+"%'";
		}
		if(!"".equals(orgName)){
			sqlWhere += " and org_name like '%"+orgName+"%'";
		}
		
		String sql = "select max(create_time) create_time,name,bank,org_name, "
				+ "sum(case product_attr when '001' then common_count else 0 end) as 'aa',"
				+ "sum(case product_attr when '001' then common_sales_volume else 0 end) as 'bb',"
				+ "sum(case product_attr when '002' then common_count else 0 end) as 'cc',"
				+ "sum(case product_attr when '002' then common_sales_volume else 0 end) as 'dd',"
				+ "sum(case product_attr when '003' then common_count else 0 end) as 'ee',"
				+ "sum(case product_attr when '003' then common_sales_volume else 0 end) as 'ff',"
				+ "sum(common_count) as 'gg',"
				+ "sum(common_sales_volume) as 'hh'"
				+ " from order_info where"+sqlWhere+" group by name,bank,org_name";
		return sql;
	}
	String sql_single_all(String create_time,String end_time,String bank,String orgName){
		String sqlWhere = " status=1 ";
		
		if(!"".equals(create_time)&&!"".equals(end_time)){
			sqlWhere += " and create_time between '"+create_time+"' and '"+ end_time +"'";
		}
		if(!"".equals(bank)){
			sqlWhere += " and bank like '%"+bank+"%'";
		}
		if(!"".equals(orgName)){
			sqlWhere += " and org_name like '%"+orgName+"%'";
		}
		
		String sql = "select id,serial_num, max(create_time) create_time,name,bank,org_name, "
				+ "sum(case product_attr when '001' then common_count else 0 end) as 'aa',"
				+ "sum(case product_attr when '001' then common_sales_volume else 0 end) as 'bb',"
				+ "sum(case product_attr when '002' then common_count else 0 end) as 'cc',"
				+ "sum(case product_attr when '002' then common_sales_volume else 0 end) as 'dd',"
				+ "sum(case product_attr when '003' then common_count else 0 end) as 'ee',"
				+ "sum(case product_attr when '003' then common_sales_volume else 0 end) as 'ff',"
				+ "sum(common_count) as 'gg',"
				+ "sum(common_sales_volume) as 'hh'"
				+ " from order_info where"+sqlWhere+" group by name,bank,org_name,serial_num";
		return sql;
	}
	public void listUserInfo0() throws ParseException{
		Integer flag = getParaToInt("flag");
		
		List<Record> l = Db.find("select CONCAT(product_name,'/',wight,'/',gold) as content,'001' code from price_info order by product_attr ");
		

		
		/*
		JSONArray ja = new JSONArray();
		JSONObject json;
		for (int i = 0 ; i < findPrice.size() ; i++){
			json = new JSONObject();
			json.put("productId", findPrice.get(i).get("id"));
			json.put("productAttr", "001");
			json.put("productName", findPrice.get(i).get("product_name")+" / "+findPrice.get(i).get("wight").toString()+" / "+findPrice.get(i).get("gold"));
			ja.add(json);
		}
		setAttr("ja", ja);*/
		setAttr("lists",  l);
		if(flag==0){
			render("userWinningListPage.jsp");
		}else if(flag==1){
			render("userCount.jsp");
		}else if(flag==2){
			render("userSumCount.jsp");
		}else {
			render("userSingleSumCount.jsp");
		}
	}
	
	/**
	 * 
	* @Title: exportPartakeData 
	* @Description: 导出excel
	* @param @throws IOException  
	* @return void 
	* @throws
	 */
	public void exportPartakeData() throws IOException{
		OutputStream out = null;
		OrderInfo bean = getBean(OrderInfo.class);
		String serialNum = bean.getSerialNum();
		try {
			Map<String, String[]> paraMap = getParaMap();
			String beginDate = getPara("beginDate")==null||"".equals(getPara("endDate")) ? "" : getPara("beginDate")+" 00:00:00";
			String endDate = getPara("endDate")==null||"".equals(getPara("endDate")) ? "" : getPara("endDate")+" 23:59:59";
			String bank = getPara("bank")==null||"".equals(getPara("bank")) ? "": getPara("bank");
			String orgName = getPara("orgName")==null||"".equals(getPara("orgName")) ? "": getPara("orgName");;
			Integer flag = getParaToInt("flag");
			String sql = "";
			Record sessionAttr = getSessionAttr("userLogin");	
			String sqlOrg = "select org_name from org_info where org_code = "+ sessionAttr.getStr("org_code");
			Record fatherOrg= Db.findFirst(sqlOrg);
			if(!"昆仑银行总行".equals(fatherOrg.getStr("org_name").trim())){
				bank = fatherOrg.getStr("org_name").trim();
			}
			List<Record> l = Db.find(sql_common_sales_volume_title());
			List<Record> l1;
			String titles = "提交时间,姓名,分行,支行,";
			for(int i = 0 ; i < l.size() ; i ++){
				if(i!=l.size()-1){
					titles += l.get(i).get("content")+",";
				}else{
					titles += l.get(i).get("content")+",其它产品,合计";
				}
			}//TODO
			String[] headers = titles.split(",");
			List<List<Object>> excelList = new ArrayList<List<Object>>();
			if(flag==0){
				l1 = Db.find(sql_common_sales_volume(beginDate,endDate,bank,orgName));
				List<String[]> list = new ArrayList<String[]>();
				String[] str;
				for(Record record1 : l1){
					
					str = new String[6+l.size()];
					str[0] = record1.get("create_time").toString();
					str[1] = record1.getStr("name");
					str[2] = record1.getStr("bank");
					str[3] = record1.getStr("org_name");
					String [] common_product_id_s = record1.getStr("common_product_id_s").split(",");
					String [] common_sales_volume_s = record1.getStr("common_sales_volume_s").split(",");
					BigDecimal count = BigDecimal.ZERO;
					BigDecimal countOther = BigDecimal.ZERO;
					for (int j = 0; j<l.size(); j++) {
						Record record = l.get(j);
						str[j+4] = "0";
						for (int i = 0; i < common_product_id_s.length; i++) {
							if((record.get("id")+"").equals(common_product_id_s[i]+"")){
								str[j+4] = common_sales_volume_s[i];
								count = count.add(new BigDecimal(common_sales_volume_s[i]));
							}
						}
					}
					for (int i = 0; i < common_product_id_s.length; i++) {
						if((common_product_id_s[i]+"").indexOf("9999") != -1){
							countOther = countOther.add(new BigDecimal(common_sales_volume_s[i]));
							
						}
					}
					str[str.length-2] = countOther.toString();
					str[str.length-1] = count.add(countOther).toString();
					System.out.println(str[str.length-1]);
					list.add(str);
					/*for(int i = 0 ; i < str.length ; i ++){
						lll = new ArrayList<Object>();
						lll.add(str[i]);
					}*/	
					for(int i = 0 ; i < list.size() ; i ++){
						List<Object> lll = new ArrayList<Object>();
						String[] strings = list.get(i);
						for(int j = 0 ; j < strings.length ; j ++){
							
							lll.add(strings[j]);
						}
						excelList.add(lll);
					}
				}
			}else if(flag==1){
				l1 = Db.find(sql_common_sales_count(beginDate,endDate,bank,orgName));
				List<String[]> list = new ArrayList<String[]>();
				String[] str;
				for(Record record1 : l1){
					
					str = new String[6+l.size()];
					str[0] = record1.get("create_time").toString();
					str[1] = record1.getStr("name");
					str[2] = record1.getStr("bank");
					str[3] = record1.getStr("org_name");
					String [] common_product_id_s = record1.getStr("common_product_id_s").split(",");
					String [] common_sales_volume_s = record1.getStr("common_count_s").split(",");
					BigDecimal count = BigDecimal.ZERO;
					BigDecimal countOther = BigDecimal.ZERO;
					for (int j = 0; j<l.size(); j++) {
						Record record = l.get(j);
						str[j+4] = "0";
						for (int i = 0; i < common_product_id_s.length; i++) {
							if((record.get("id")+"").equals(common_product_id_s[i]+"")){
								str[j+4] = common_sales_volume_s[i];
								count = count.add(new BigDecimal(common_sales_volume_s[i]));
							}
						}
					}
					for (int i = 0; i < common_product_id_s.length; i++) {
						if((common_product_id_s[i]+"").indexOf("9999") != -1){
							countOther = countOther.add(new BigDecimal(common_sales_volume_s[i]));
							
						}
					}
					str[str.length-2] = countOther.toString();
					str[str.length-1] = count.add(countOther).toString();
					System.out.println(str[str.length-1]);
					list.add(str);
					/*for(int i = 0 ; i < str.length ; i ++){
						lll = new ArrayList<Object>();
						lll.add(str[i]);
					}*/	
					for(int i = 0 ; i < list.size() ; i ++){
						List<Object> lll = new ArrayList<Object>();
						String[] strings = list.get(i);
						for(int j = 0 ; j < strings.length ; j ++){
							
							lll.add(strings[j]);
						}
						excelList.add(lll);
					}
				}
			}else if(flag==2){
				Map<Integer, Object> map = new LinkedHashMap<Integer , Object>();
				List<Record> list = Db.find(sql_all(beginDate,endDate,bank,orgName));
				int count = 0 ; 
				for (Record record : list) {
					count++;
					Map<String, Object> m = record.getColumns();
					map.put(count, record);
				}
				List<Object> ll ;
				for (int i = 0 ; i < list.size() ; i ++){
					ll = new ArrayList<Object>();
					ll.add(list.get(i).get("create_time"));
					ll.add(list.get(i).get("name"));
					ll.add(list.get(i).get("bank"));
					ll.add(list.get(i).get("org_name"));
					ll.add(list.get(i).get("aa"));
					ll.add(list.get(i).get("bb"));
					ll.add(list.get(i).get("cc"));
					ll.add(list.get(i).get("dd"));
					ll.add(list.get(i).get("ee"));
					ll.add(list.get(i).get("ff"));
					ll.add(list.get(i).get("gg"));
					ll.add(list.get(i).get("hh"));
					excelList.add(ll);
				}
			}else{
				Map<Integer, Object> map = new LinkedHashMap<Integer , Object>();
				List<Record> list = Db.find(sql_single_all(beginDate,endDate,bank,orgName));
				int count = 0 ; 
				for (Record record : list) {
					count++;
					Map<String, Object> m = record.getColumns();
					map.put(count, record);
				}
				List<Object> ll ;
				for (int i = 0 ; i < list.size() ; i ++){
					ll = new ArrayList<Object>();
					ll.add(list.get(i).get("create_time"));
					ll.add(list.get(i).get("name"));
					ll.add(list.get(i).get("bank"));
					ll.add(list.get(i).get("org_name"));
					ll.add(list.get(i).get("aa"));
					ll.add(list.get(i).get("bb"));
					ll.add(list.get(i).get("cc"));
					ll.add(list.get(i).get("dd"));
					ll.add(list.get(i).get("ee"));
					ll.add(list.get(i).get("ff"));
					ll.add(list.get(i).get("gg"));
					ll.add(list.get(i).get("hh"));
					excelList.add(ll);
				}
			}
			/*if(!"".equals(beginDate)&&!"".equals(endDate)){
				sql += " and create_time BETWEEN "
						+ "'"+beginDate+ "'"
						+ " and "
						+ "'"+endDate+ "'" ;
			}
			if(flag!=1&&flag!=0&&flag!=2){
				sql += " group by serial_num,area,team,name order by ct ";
			}else{
				sql += " group by area,team,name order by ct";
			}*/
			HttpServletResponse response = getResponse();
//			response.setContentType("octets/stream");
			response.setHeader("Content-Type","application/force-download");   
	        response.setHeader("Content-Type","application/vnd.ms-excel");   
			Integer paraToInt = getParaToInt("pageNumber");
			response.addHeader("Content-Disposition", "attachment;filename=userBuyData"+new Date().getTime()+".xls");
			JSONObject data = new JSONObject();
			data.put("producer", getPara("producer"));
			data.put("greetings", getPara("greetings"));
			//当前页
			//data.put("pageNumber", getParaToInt("pageNumber")==null?1:paraToInt);
			//从第一页开始
			data.put("pageNumber", 1);
			//每页条数
			data.put("pageSize", 999999999);
			/*List<OrderInfo> OrderInfoList = OrderInfo.dao.find(sql);
			
			
			List<Object> ll ;
			for (int i = 0 ; i < OrderInfoList.size() ; i ++){
				ll = new ArrayList<Object>();
				ll.add(OrderInfoList.get(i).get("ct"));
				ll.add(OrderInfoList.get(i).get("name"));
				ll.add(OrderInfoList.get(i).get("team"));
				ll.add(OrderInfoList.get(i).get("area"));
				ll.add(OrderInfoList.get(i).get("bank"));
				ll.add(OrderInfoList.get(i).get("aa"));
				ll.add(OrderInfoList.get(i).get("bb"));
				ll.add(OrderInfoList.get(i).get("cc"));
				ll.add(OrderInfoList.get(i).get("dd"));
				ll.add(OrderInfoList.get(i).get("ee"));
				ll.add(OrderInfoList.get(i).get("ff"));
				ll.add(OrderInfoList.get(i).get("gg"));
				ll.add(OrderInfoList.get(i).get("hh"));
				if(flag!=2&&flag!=3){
					ll.add(OrderInfoList.get(i).get("ii"));
					ll.add(OrderInfoList.get(i).get("jj"));
					ll.add(OrderInfoList.get(i).get("kk"));
					ll.add(OrderInfoList.get(i).get("ll"));
					ll.add(OrderInfoList.get(i).get("mm"));
					ll.add(OrderInfoList.get(i).get("nn"));
					ll.add(OrderInfoList.get(i).get("oo"));
					ll.add(OrderInfoList.get(i).get("pp"));
					ll.add(OrderInfoList.get(i).get("qq"));
					ll.add(OrderInfoList.get(i).get("rr"));
				}
				
				excelList.add(ll);
			}
			*/
			
			out = getResponse().getOutputStream();
			
			ExportExcel<OrderInfo> ex = new ExportExcel<OrderInfo>();
			String sheetName = "";
			if(flag==0){
				sheetName = "销量列表";
			}else if(flag==1){
				sheetName = "件数列表";
			}else if(flag==2){
				sheetName = "总件数销量列表";
				headers = headers2;
			}else{
				sheetName = "批次销量件数刘表";
				headers = headers2;
			}
		
			ex.exportExcel(sheetName,headers, excelList, out, "yyyy-MM-dd");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		renderNull();
	}

	private String sqlCount() {
		String sql = "select max(create_time) ct, name,team , area, bank,"
				+ "sum(case common_product_id when '0001' then common_count else 0 end) as 'aa',"
				+ "sum(case common_product_id when '0002' then common_count else 0 end) as 'bb', "
				+ "sum(case common_product_id when '0003' then common_count else 0 end) as 'cc',"
				+ "sum(case common_product_id when '0004' then common_count else 0 end) as 'dd',"
				+ "sum(case common_product_id when '0005' then common_count else 0 end) as 'ee',"
				+ "sum(case common_product_id when '0006' then common_count else 0 end) as 'ff',"
				+ "sum(case common_product_id when '0007' then common_count else 0 end) as 'gg',"
				+ "sum(case common_product_id when '0008' then common_count else 0 end) as 'hh',"
				+ "sum(case common_product_id when '0009' then common_count else 0 end) as 'ii',"
				+ "sum(case common_product_id when '0010' then common_count else 0 end) as 'jj',"
				+ "sum(case common_product_id when '0011' then common_count else 0 end) as 'kk',"
				+ "sum(case common_product_id when '0012' then common_count else 0 end) as 'll',"
				+ "sum(case common_product_id when '0013' then common_count else 0 end) as 'mm',"
				+ "sum(case common_product_id when '0014' then common_count else 0 end) as 'nn',"
				+ "sum(case common_product_id when '0015' then common_count else 0 end) as 'oo',"
				+ "sum(case product_attr when '002' then common_count else 0 end) as 'pp',"
				+ "sum(case product_attr when '003' then common_count else 0 end) as 'qq',"
				+ "sum(common_count) as 'rr'"
				+ " from order_info "
				+ " where status=1 " ;
				return sql;
	}
	
	private String sqlSingleSerialNone(){
		String sql = "select id,serial_num, create_time ct, name,team , area,bank, "
				+ "sum(case product_attr when '001' then common_count else 0 end) as 'aa',"
				+ "sum(case product_attr when '001' then common_sales_volume else 0 end) as 'bb',"
				+ "sum(case product_attr when '002' then common_count else 0 end) as 'cc',"
				+ "sum(case product_attr when '002' then common_sales_volume else 0 end) as 'dd',"
				+ "sum(case product_attr when '003' then common_count else 0 end) as 'ee',"
				+ "sum(case product_attr when '003' then common_sales_volume else 0 end) as 'ff',"
				+ "sum(common_count) as 'gg',"
				+ "sum(common_sales_volume) as 'hh'"
				+ " from order_info "
				+ " where status=1 " ;
		return sql;
	}
	/** 
     * 获得指定日期的前一天 
     *  
     * @param specifiedDay 
     * @return 
     * @throws Exception 
     */  
    public static String getSpecifiedDayBefore(String specifiedDay) {//可以用new Date().toLocalString()传递参数  
        Calendar c = Calendar.getInstance();  
        Date date = null;  
        try {  
            date = new SimpleDateFormat("yy-MM-dd HH:mm:ss").parse(specifiedDay);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        c.setTime(date);  
        int day = c.get(Calendar.DATE);  
        c.set(Calendar.DATE, day - 1);  
  
        String dayBefore = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c  
                .getTime());  
        return dayBefore;  
    }  
    
    public void del(){
    	String ids = getPara("ids");
    	int update = Db.update("update order_info set status = 0 where serial_num = "+"\'"+ids+"\'");
    	JSONObject js = new JSONObject();
    	if(update>0){
    		js.put("result", "true");
    	}else{
    		js.put("result", "false");
    	}
    	renderJson(js);
    }
   
}
