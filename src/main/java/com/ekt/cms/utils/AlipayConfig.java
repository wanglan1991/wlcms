package com.ekt.cms.utils;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.4
 *修改日期：2016-03-08
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	//支付宝分配给开发者的应用ID
	public static String app_id="2016120804033817"; //app支付申请应用的appId

	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String partner = "2088421191459895";//支付宝账号对应的支付宝唯一用户号。
	
	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public static String seller_id = partner;

	//商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
	public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAPRe8YIp9Ig3Q1pwDzKAN5N6ebP0a0Ja3rIoueDbsOZDoXBdo6OlrkeH/QxMsduo66j1hoARVjXFbD53JktZYGySnlY7ALP9oxk67ua20XZtwk015Fco0rFgzfqydOM9Ymz6Efu+phihFU4zweaTtoh/bgh36TvEi15LUa5dOropAgMBAAECgYBznBtLxkeOzf5KYet+q9QfLHk5XrAD03O5+7R8odLeDS7qNxK80BxUcQyRbhNS5WagJ5A7NHiBteXQ45O1YBJSPpDiFuLLha3xBEQPGVEQT5cSZtd+mgZUz2PctZNCv2nJCqKzmGyoY5+zpm+2lW4y15fv3MXDRMhFioqowQxRhQJBAP9hPjxCUL9pSZtFFdKAKW52WKlTfow/k4Qxq6u4Ivx9eIfAL68gaNJklUfwQVZZwRMBbnMsQ3nDAzwf2L5RDf8CQQD09ttG6JOCDRH/cvansf+Z+8H4E9w9g/VqEVBksOAJTj2wRZu3Z35Qb5UtN66X6FUA4dGWtkgWxN5iXPLcOgfXAkEA1/PoR9agJ6ymBLnf4ThlULac9NrZCUn9uWyzSauVUkPbt5ep+wKCAV/y6HlfFuEhJqwwNQ56JK3mv2ICy7UJCwJBAOBGiqg3E6ZEFTBNryoFu8DdUM+yTzg68pvz0ij+D4A1lCEo+dJG/Mzj/C50BnOqEPlHxr43kXTXNcvU0b1zm+0CQCfte2IKWsbrr50T8uhPLFGQX99RQ/St1nufitFgck9AAEKb4UaEUp8pZuyhSLh/zU8cNdyJYS2VWcgoJ57NSu8=";			
	public static String private_appKey="MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAJ/88p/xpnYqzrwHiCKKgu9ZINpmBs1+S/lfNI0WgLtx5LaXz+94h7/Zy608g3PhjVOrqADG1uiHG7bME7waS2VESvcN59lis1w/48WC9ibfY4YMcweeVkwpEzDXX/AeU/8diEpQFCLJFcyeD3KcDFI1Y6s5z2NKKTrsAjHYsEwHAgMBAAECgYAS27EJs2VItoEJ3uVoFOWcKuhZdf9FR6bS7Sr3mnhwH9uyvjBPbOirb5O3htOD3ElZXHmAJe/9FrfokyOcX4kVZgA9xSnIHHRnZvOtLjZDMc5Ytbkwtnmm+jjpuJZFV15uSGrKpMIyRh+6MqMOOmnBG1+HGiXHRvI7c0b6fzOqqQJBAM5ZvC7yusWeRNvCYmpd0g7hGd6oa0TFU61VP5Trkb1o8yYbmtw47OQ2LMK7LVA5uWO+2lLZhSi1JGDejLYEn5UCQQDGe35+moqKPgXhwiQJNCvaKwz3vqqLZT6+FxawtXTTY2tyCXb3F7i3gYgd9rCvRhpSkTcMPvVnSz9apHbmfAYrAkEAxsTHsc2cvelz2OBI3r7bLfwFaGaBVbBipy/b3D6Cl5p1xXqH1KR9BGNvAHE+9+1yuDNKqM3hJ9I9ZZZaoqaXdQJBAI4Htwf/rJ2qLmlTlJfldm/jGJ98F+wyIgjC2uDOqG/QGAX9kdzVY8Ak9/OBIQP7pUBqW7runosxBH/napIaNAsCQQCp8as9aDDvv2UyKonD+JbokgKqXp4xtHEM9vxg/Oj/Yjqrwb/Hikdww+5J4XLz8dV68S6CKFOd/Vpac5D2zKDs";
	// 支付宝的公钥,查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String alipay_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
	public static String alipay_public_appKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
	
	//沙箱环境的公私密钥 appId
//	public static String app_id="2016072900113844";//沙箱环境
//	public static String private_appKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAL03G6pATmE8VjfeEmXSXb7RTYt8+liil6QqH4425BMwbSqUfipnVHJuxIjkQrOqyYmubrNxPYII8V0Sxrs1zpgbe+Y2DcVV06Xb0yM/57Kq/46frP2zN9akODkV3v9hPR6yAdNRcMQIgkDlT/pR/8P9t9/qiGKzCR4wHrv1z8PXAgMBAAECgYAA4ir85oP3LELyy9SmKQ9K/ssv5Yt9snSmzZOMJHF4LXe1BTB5njXkuuOsB/TPxfJe9DOMHvE8lDIcTyQiZ6PpS9lMvvMGV4vWAvYmNn0Xa2MITryDTN7S43YoNeTu2icmP7eEFhfOANc3zRMLtX6EFcBdeQnVSCaioeKmx0maIQJBAOCMwy2CoSdT9g2e/c3gw5zkPEY8ecDD30JxcGcze7URf4byc+sGkLxw2XuJTsWhqGxuDwusqYHPELbGPPpUivkCQQDXt2xyJas7vEq/AqQTPI2b82ukDBe0cNJVMHhTXD/V/8hGByvboVXk+x2MNHI1LEOALgU9I2c3/Sl7njcV7ylPAkEAy2m4RTnYkq/UbXJhqGa7zRxUHawa/yxwytfIYzSYE+bOB4kKyRUheBMicG5qFU3kLJVmu8SlqRnSoj/ibUK4wQJAQuTDgWZDAmQyfacxADIf68OBlr6VP0RZRUehmKAbv53xGh4xMIuIWliw4gCFhg2y5PXgdAQdhxQ/n/EBPhELKwJAKnAmPdkUfi0wjjEnjqqcEd7fppzXlCV51eQmNbezffGauvFDgjTDahtnkLUtDrhF+nN6grrwd7yqN109s7lanA==";
//	public static String alipay_public_appKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB";
//	public static String seller_id_app ="2088102168926300";
	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//	public static String notify_url = "http://zyq.tunnel.qydev.com/EKTAPI/alipay/alipayNotify";
	public static String notify_url = "http://www.aiekt.com/API/EKTAPI/alipay/alipayNotify";

	
	
	
	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//	public static String return_url = "http://zyq.tunnel.qydev.com/EKTAPI/alipay/alipayReturn";
	public static String return_url = "http://www.aiekt.com/API/EKTAPI/alipay/alipayReturn";
	
	

	// 签名方式
	public static String sign_type = "RSA";
	//支付能力
	public static String trade_type_app = "APP";
	public static String trade_type_pc = "NATIVE";	
	
	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public static String log_path = "C:\\";
		
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
		
	// 支付类型 ，无需修改
	public static String payment_type = "1";
		
	// 调用的接口名，无需修改
	public static String service = "create_direct_pay_by_user";
	//app同步结果通知code
	public static String resultStatus_success="9000";

//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	
//↓↓↓↓↓↓↓↓↓↓ 请在这里配置防钓鱼信息，如果没开通防钓鱼功能，为空即可 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	
	// 防钓鱼时间戳  若要使用请调用类文件submit中的query_timestamp函数
	public static String anti_phishing_key = "";
	
	// 客户端的IP地址 非局域网的外网IP地址，如：221.0.0.1
	public static String exter_invoke_ip = "";
		
//↑↑↑↑↑↑↑↑↑↑请在这里配置防钓鱼信息，如果没开通防钓鱼功能，为空即可 ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	
}

