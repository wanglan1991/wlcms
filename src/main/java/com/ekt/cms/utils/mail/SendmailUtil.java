package com.ekt.cms.utils.mail;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * 邮件发送工具类
 * @author wanglan
 *
 */
public class SendmailUtil {
	
	
	public static String CONNECT_KEY ="";//连接key
	// 设置服务器
    private static String KEY_SMTP = "";
    private static String VALUE_SMTP = "";
    // 服务器验证
    private static String KEY_PROPS = "";
    
    private static boolean VALUE_PROPS = true;
    // 发件人用户名、密码
    private  String SEND_USER = "";
    private  String SEND_UNAME = "";
    private  String SEND_PWD = "";
    // 建立会话
    private  MimeMessage message;
    private   Session s;
 
    /*
     * 初始化方法
     */
    public SendmailUtil() {
        Properties props = System.getProperties();
        props.setProperty(KEY_SMTP, VALUE_SMTP);
        props.put(KEY_PROPS, "true");
        //props.put("mail.smtp.auth", "true");
        s =  Session.getDefaultInstance(props, new Authenticator(){
              protected PasswordAuthentication getPasswordAuthentication() {
                  return new PasswordAuthentication(SEND_UNAME, SEND_PWD);
              }});
        s.setDebug(true);
        message = new MimeMessage(s);
    }
 
    /**
     * 发送邮件
     * 
     * @param headName
     *            邮件头文件名
     * @param sendHtml
     *            邮件内容
     * @param receiveUser
     *            收件人地址
     */
    public  void doSendHtmlEmail(String headName, String sendHtml,
            String receiveUser)throws Exception {
      
            // 发件人
            InternetAddress from = new InternetAddress(SEND_USER,"你大爷");
            message.setFrom(from);
            // 收件人
            InternetAddress to = new InternetAddress(receiveUser);
            message.setRecipient(Message.RecipientType.TO, to);
            // 邮件标题
            message.setSubject(headName);
            String content = sendHtml.toString();
            // 邮件内容,也可以使纯文本"text/plain"
            message.setContent(content, "text/html;charset=UTF-8");
            message.saveChanges();
            Transport transport = s.getTransport("smtp");
            // smtp验证，就是你用来发邮件的邮箱用户名密码
            transport.connect(VALUE_SMTP, SEND_USER, SEND_PWD);
            // 发送
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("send success!");
       
    }
 

}
