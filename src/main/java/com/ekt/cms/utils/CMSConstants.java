package com.ekt.cms.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 * 
 * @author wanglan
 *2017-09-26
 */
public class CMSConstants {

	public static final Integer ROOT = 1;// 超级管理员

	public static final Integer ADMIN = 66;// 管理员

	



	/**
	 * 根据指定日期获取大于该指定日期的日期列表
	 * @param days
	 * @return
	 */
	public static ArrayList<String> getDates(int days){
		ArrayList<String> data=null	;
		if(days>0){
			data = new ArrayList<String>();	
			Date date=new Date();
			for(int i=0;i<days;i++){
				try {
					data.add(subDate(date, -i));
				} catch (Exception e) {
					return null;
				}
			}
			return data;
		}else{
			return data;
		}
		
		
	
	}
	
	
	/**
	 * 日期加減
	 * @param date
	 * @param days
	 * @return
	 * @throws Exception
	 */
	public static String subDate(Date date,int days)throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date(date.getTime() + (long)days * 24 * 60 * 60 * 1000))+" 00:00:00"	;
	}
	
	








}
