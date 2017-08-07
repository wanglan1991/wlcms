package com.ekt.cms.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtil {
	/** 日志开关 **/
	public static final boolean SWITCH = true;
	/** 时间格式化参数 **/
	public static final SimpleDateFormat SDF =new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
	
	/**
	 * 日志输出
	 * @param msg
	 */
	public static void print(String msg){
		if(SWITCH){
			System.out.println("TYPE:CMS >>>>>>>>>>> TIME:"+SDF.format(new Date())+" >>>>>>>>>>> MESSAGE:“"+msg+"”");
		}
	}
	
	
	

}
