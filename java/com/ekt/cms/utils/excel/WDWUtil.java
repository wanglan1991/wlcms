package com.ekt.cms.utils.excel;

public class WDWUtil {
	  
	  
    /** 
     *  
     * @描述：是否是2003的excel，返回true是2003 
     *  
     * @作者：建宁 
     *  
     * @时间：2012-08-29 下午16:29:11 
     *  
     * @参数：@param filePath　文件完整路径 
     *  
     * @参数：@return 
     *  
     * @返回值：boolean 
     */  
  
    public static boolean isExcel2003(String filePath)  
    {  
  
        return filePath.matches("^.+\\.(?i)(xls)$");  
  
    }  
  
    /** 
     *  
     * @描述：是否是2007的excel，返回true是2007 
     *  
     * @作者：建宁 
     *  
     * @时间：2012-08-29 下午16:28:20 
     *  
     * @参数：@param filePath　文件完整路径 
     *  
     * @参数：@return 
     *  
     * @返回值：boolean 
     */  
  
    public static boolean isExcel2007(String filePath)  
    {  
  
        return filePath.matches("^.+\\.(?i)(xlsx)$");  
  
    }  

}
