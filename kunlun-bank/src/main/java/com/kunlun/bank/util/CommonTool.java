package com.kunlun.bank.util;

import java.util.UUID;

/**
 * 常用工具
 * @author WH
 *
 */
public class CommonTool {


    /**
     * 得到UUID
     * @return
     */
    public static String getUUID(){
    	return  UUID.randomUUID().toString().replace("-", "");
    }
	
}
