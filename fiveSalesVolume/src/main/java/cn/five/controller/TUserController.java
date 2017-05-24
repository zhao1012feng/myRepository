package cn.five.controller;

import java.io.IOException;
import java.io.OutputStream;
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

import cn.five.common.model.CommonInfo;
import cn.five.util.CommonTool;
import cn.five.util.ExportExcel;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class TUserController extends Controller{
	private static final String[] headers1 = { "提交时间","姓名","战队名称","战区","银行","金鸡贺岁2017鸡年生肖贺岁邮品金大全套", ""
			+ "金鸡贺岁2017鸡年生肖贺岁邮品金四方连1.3克",""
			+ "火猴金大全套14.5克","火候迎新2016猴年生肖贺岁邮票金1.5克","火猴银大全套16克",""
			+ "2016年熊猫金币套装(PDGS封装评级特别纪念版)",""
			+ "2016年版中国熊猫普制银币PCGS封装评级满分套装","鸿运招财八宝珠金3克","顺心如意健康宝银66克",""
			+ "百岁核桃银50克*2","真心爱","六字真言转运珠念珠版金2.7克","朵朵金玫瑰2克含托帕石",""
			+ "鸿运金卡2克","鸿运招财转经筒金1.5克","2017PCGS熊猫银币套装(5枚单套)","2017PCGS熊猫银币套装(小箱装)","2017PCGS熊猫银币套装(大箱装)","心经金环","其他产品","合计"};
	private static final String[] headers2 = { "提交时间","姓名","战队名称","战区","银行","普通产品总件数"
			+ "","普通产品总销量","心经件数","心经销量","其他产品总件数","其他产品总销量"
			+ "","合计总件数","合计总销量"};
	
	public void listUserInfo() throws ParseException{
		String beginDate = getPara("beginDate")==null||"".equals(getPara("endDate")) ? "" : getPara("beginDate")+" 17:31:00";
		String endDate = getPara("endDate")==null||"".equals(getPara("endDate")) ? "" : getPara("endDate")+" 17:30:59";
		Integer flag = getParaToInt("flag");
		if(!"".equals(beginDate)){
			beginDate = getSpecifiedDayBefore(beginDate);
		}
		String sql = "";
		if(flag==0){
			sql=sql();
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
		setAttr("commonInfoList", map);
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
	public void listUserInfo0() throws ParseException{
		Integer flag = getParaToInt("flag");
		/*String beginDate = getPara("beginDate")==null||"".equals(getPara("endDate")) ? "" : getPara("beginDate")+" 17:31:00";
		String endDate = getPara("endDate")==null||"".equals(getPara("endDate")) ? "" : getPara("endDate")+" 17:30:59";
		if(!"".equals(beginDate)){
			beginDate = getSpecifiedDayBefore(beginDate);
		}
		String sql = "";
		if(flag==0){
			sql=sql();
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
		setAttr("commonInfoList", map);*/
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
		CommonInfo bean = getBean(CommonInfo.class);
		String serialNum = bean.getSerialNum();
		try {
			Map<String, String[]> paraMap = getParaMap();
			String beginDate = getPara("beginDate")==null||"".equals(getPara("beginDate")) ? "" : getPara("beginDate").toString()+" 17:31:00";
			String endDate = getPara("endDate")==null||"".equals(getPara("endDate")) ? "" : getPara("endDate").toString()+" 17:30:59";
			Integer flag = getParaToInt("flag");
			if(!"".equals(beginDate)){
				beginDate = getSpecifiedDayBefore(beginDate);
			}
			String sql = "";
			if(flag==0){
				sql=sql();
			}else if(flag==1){
				sql = sqlCount();
			}else{
				sql = sqlSum();
			}
			if(!"".equals(beginDate)&&!"".equals(endDate)){
				sql += " and create_time BETWEEN "
						+ "'"+beginDate+ "'"
						+ " and "
						+ "'"+endDate+ "'" ;
			}
			if(flag!=1&&flag!=0&&flag!=2){
				sql += " group by serial_num,area,team,name order by ct ";
			}else{
				sql += " group by area,team,name order by ct";
			}
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
			List<CommonInfo> commonInfoList = CommonInfo.dao.find(sql);
			List<List<Object>> excelList = new ArrayList<List<Object>>();
			
			List<Object> ll ;
			for (int i = 0 ; i < commonInfoList.size() ; i ++){
				ll = new ArrayList<Object>();
				ll.add(commonInfoList.get(i).get("ct"));
				ll.add(commonInfoList.get(i).get("name"));
				ll.add(commonInfoList.get(i).get("team"));
				ll.add(commonInfoList.get(i).get("area"));
				ll.add(commonInfoList.get(i).get("bank"));
				ll.add(commonInfoList.get(i).get("aa"));
				ll.add(commonInfoList.get(i).get("bb"));
				ll.add(commonInfoList.get(i).get("cc"));
				ll.add(commonInfoList.get(i).get("dd"));
				ll.add(commonInfoList.get(i).get("ee"));
				ll.add(commonInfoList.get(i).get("ff"));
				ll.add(commonInfoList.get(i).get("gg"));
				ll.add(commonInfoList.get(i).get("hh"));
				if(flag!=2&&flag!=3){
					ll.add(commonInfoList.get(i).get("ii"));
					ll.add(commonInfoList.get(i).get("jj"));
					ll.add(commonInfoList.get(i).get("kk"));
					ll.add(commonInfoList.get(i).get("ll"));
					ll.add(commonInfoList.get(i).get("mm"));
					ll.add(commonInfoList.get(i).get("nn"));
					ll.add(commonInfoList.get(i).get("oo"));
					ll.add(commonInfoList.get(i).get("xx"));
					ll.add(commonInfoList.get(i).get("yy"));
					ll.add(commonInfoList.get(i).get("zz"));
					ll.add(commonInfoList.get(i).get("pp"));
					ll.add(commonInfoList.get(i).get("qq"));
					ll.add(commonInfoList.get(i).get("rr"));
				}
				
				excelList.add(ll);
			}
			String[] headers = {};
			if(flag==2||flag==3){
				headers = headers2;
			}else{
				headers = headers1;
			}
			out = getResponse().getOutputStream();
			
			ExportExcel<CommonInfo> ex = new ExportExcel<CommonInfo>();
			String sheetName = "";
			if(flag==0){
				sheetName = "销量列表";
			}else if(flag==1){
				sheetName = "件数列表";
			}else if(flag==2){
				sheetName = "总件数销量列表";
			}else{
				sheetName = "批次销量件数刘表";
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
				+ "sum(case common_product_id when '0016' then common_count else 0 end) as 'xx',"
				+ "sum(case common_product_id when '0017' then common_count else 0 end) as 'yy',"
				+ "sum(case common_product_id when '0018' then common_count else 0 end) as 'zz',"
				+ "sum(case product_attr when '002' then common_count else 0 end) as 'pp',"
				+ "sum(case product_attr when '003' then common_count else 0 end) as 'qq',"
				+ "sum(common_count) as 'rr'"
				+ " from common_info "
				+ " where status=1 " ;
				return sql;
	}

	private String sql() {
		String sql = "select max(create_time) ct, name,team , area, bank,"
		+ "sum(case common_product_id when '0001' then common_sales_volume else 0 end) as 'aa',"
		+ "sum(case common_product_id when '0002' then common_sales_volume else 0 end) as 'bb', "
		+ "sum(case common_product_id when '0003' then common_sales_volume else 0 end) as 'cc',"
		+ "sum(case common_product_id when '0004' then common_sales_volume else 0 end) as 'dd',"
		+ "sum(case common_product_id when '0005' then common_sales_volume else 0 end) as 'ee',"
		+ "sum(case common_product_id when '0006' then common_sales_volume else 0 end) as 'ff',"
		+ "sum(case common_product_id when '0007' then common_sales_volume else 0 end) as 'gg',"
		+ "sum(case common_product_id when '0008' then common_sales_volume else 0 end) as 'hh',"
		+ "sum(case common_product_id when '0009' then common_sales_volume else 0 end) as 'ii',"
		+ "sum(case common_product_id when '0010' then common_sales_volume else 0 end) as 'jj',"
		+ "sum(case common_product_id when '0011' then common_sales_volume else 0 end) as 'kk',"
		+ "sum(case common_product_id when '0012' then common_sales_volume else 0 end) as 'll',"
		+ "sum(case common_product_id when '0013' then common_sales_volume else 0 end) as 'mm',"
		+ "sum(case common_product_id when '0014' then common_sales_volume else 0 end) as 'nn',"
		+ "sum(case common_product_id when '0015' then common_sales_volume else 0 end) as 'oo',"
		+ "sum(case common_product_id when '0016' then common_sales_volume else 0 end) as 'xx',"
		+ "sum(case common_product_id when '0017' then common_sales_volume else 0 end) as 'yy',"
		+ "sum(case common_product_id when '0018' then common_sales_volume else 0 end) as 'zz',"
		+ "sum(case product_attr when '002' then common_sales_volume else 0 end) as 'pp',"
		+ "sum(case product_attr when '003' then common_sales_volume else 0 end) as 'qq',"
		+ "sum(common_sales_volume) as 'rr'"
		+ " from common_info "
		+ " where status=1 " ;
		return sql;
	}
	private String sqlSum() {
		String sql = "select max(create_time) ct,id, name,team , area,bank, "
				+ "sum(case product_attr when '001' then common_count else 0 end) as 'aa',"
				+ "sum(case product_attr when '001' then common_sales_volume else 0 end) as 'bb',"
				+ "sum(case product_attr when '002' then common_count else 0 end) as 'cc',"
				+ "sum(case product_attr when '002' then common_sales_volume else 0 end) as 'dd',"
				+ "sum(case product_attr when '003' then common_count else 0 end) as 'ee',"
				+ "sum(case product_attr when '003' then common_sales_volume else 0 end) as 'ff',"
				+ "sum(common_count) as 'gg',"
				+ "sum(common_sales_volume) as 'hh'"
				+ " from common_info "
				+ " where status=1 " ;
		return sql;
	}
	
	
	private String sqlSingleSerialNone(){
		String sql = "select create_time ct, name,team , area,bank, "
				+ "sum(case product_attr when '001' then common_count else 0 end) as 'aa',"
				+ "sum(case product_attr when '001' then common_sales_volume else 0 end) as 'bb',"
				+ "sum(case product_attr when '002' then common_count else 0 end) as 'cc',"
				+ "sum(case product_attr when '002' then common_sales_volume else 0 end) as 'dd',"
				+ "sum(case product_attr when '003' then common_count else 0 end) as 'ee',"
				+ "sum(case product_attr when '003' then common_sales_volume else 0 end) as 'ff',"
				+ "sum(common_count) as 'gg',"
				+ "sum(common_sales_volume) as 'hh'"
				+ " from common_info "
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
    	int update = Db.update("update common_info set status = 0 where id = "+"\'"+ids+"\'");
    	JSONObject js = new JSONObject();
    	if(update>0){
    		js.put("result", "true");
    	}else{
    		js.put("result", "true");
    	}
    	renderJson(js);
    }
   
}
