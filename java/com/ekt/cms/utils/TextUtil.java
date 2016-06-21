package com.ekt.cms.utils;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 * 字符串操作通用类
 * @author IceX
 *
 */
@SuppressWarnings("rawtypes")
public class TextUtil {

	/**
	 * 对中文进行处理
	 * 
	 * @param chinese
	 *            可能包含中文的字符串
	 * @return 处理后的字符串
	 */
	public static String processChinese(String chinese) {

		try {
			byte[] byteArray = chinese.getBytes("GBK");
			chinese = new String(byteArray, "ISO8859_1");
		} catch (Exception e) {

		}
		return chinese;
	}

	public static String processChineseOther(String chinese) {

		try {
			byte[] byteArray = chinese.getBytes("ISO-8859-1");
			chinese = new String(byteArray, "GB2312");
		} catch (Exception e) {

		}
		return chinese;
	}
	
	public static String dropLastPercentSign(String rate) {
		if (isNull(rate)) {
			return "";
		} else {
			int index = rate.lastIndexOf('%');
			if (index > -1 && index == rate.length() - 1) {
				return rate.substring(0, index);
			}
			return rate;
		}
	}
	
	public static String transNull(String input) {
        if (input == null)
            return "";
        else
            return input;
    }

	/**
	 * 转换中文为ISO表准编码
	 */
	public static String processISO(String chinese) {

		try {
			byte[] byteArray = chinese.getBytes("GB2312");
			chinese = new String(byteArray, "ISO-8859-1");
		} catch (Exception e) {

		}
		return chinese;
	}

	/**
	 * 判断字符串是否不为空
	 * 
	 * @param sstring
	 *            判断的字符串
	 * @return boolean :返回值为boolean
	 */
	public static boolean isNotNull(final String sstring) {
		boolean bolret = true;
		if (isNull(sstring)) {
			bolret = false;
		}
		return bolret;
	}

	/**
	 * 判断数字是否不为０
	 * 
	 * @param iint
	 *            判断的字符串
	 * @return boolean :返回值为boolean
	 */
	public static boolean isNotNull(int iint) {
		boolean bolret = true;
		if (iint <= 0) {
			bolret = false;
		}
		return bolret;
	}
	
	/**
	 * 判断浮点数>0
	 * 
	 * @param float
	 *            判断的字符
	 * @return boolean :返回值为boolean
	 */
	public static boolean isNotNull(float iint) {
		boolean bolret = true;
		if (iint <= 0) {
			bolret = false;
		}
		return bolret;
	}

	/**
	 * 判断数字是否不为０
	 * 
	 * @param iint
	 *            判断的字符串
	 * @return boolean :返回值为boolean
	 */
	public static boolean isNotNull(Integer iint) {
		boolean bolret = true;
		if (iint == null) {
			bolret = false;
		}
		return bolret;
	}

	/**
	 * 判断数字是否不为０
	 * 
	 * @param iint
	 *            判断的字符串
	 * @return boolean :返回值为boolean
	 */
	public static boolean isNull(Integer iint) {
		boolean ret = false;
		if (iint == null) {
			ret = true;
		}

		return ret;
	}

	/**
	 * 判断对象是否为null
	 * 
	 * @param iint
	 *            判断的字符串
	 * @return boolean :返回值为boolean
	 */

	public static boolean isNull(Object obj) {
		boolean ret = false;
		if (obj == null) {
			ret = true;
		}

		return ret;
	}

	/**
	 * 判断字符串是否不为空
	 * 
	 * @param sstring
	 *            判断的字符串
	 * @return boolean :返回值为boolean
	 */
	public static boolean isNull(final String sstring) {
		boolean bolret = false;
		if (sstring == null || sstring.trim().equals("null")
				|| sstring.trim().equals("")) {
			bolret = true;
		}
		return bolret;
	}

	/**
	 * 判断列表是否为空
	 * 
	 * @param iint
	 *            判断的字符串
	 * @return boolean :返回值为boolean
	 */
	public static boolean isNull(List lst) {
		boolean bolret = false;
		if (lst == null) {
			bolret = true;
		}
		if (lst != null) {
			if (lst.size() == 0)
				bolret = true;
		}
		return bolret;
	}

	public static boolean isNotNull(List lst) {
		boolean bolret = false;
		if (lst != null) {
			if (lst.size() > 0)
				bolret = true;
		}
		return bolret;
	}

	/**
	 * 判断数字是否不为０
	 * 
	 * @param iint
	 *            判断的字符串
	 * @return boolean :返回值为boolean
	 */
	public static boolean isNull(int iint) {
		boolean bolret = false;
		if (iint > 0) {
			bolret = true;
		}
		return bolret;
	}

	/**
	 * 判断字符串是否为空,并赋上默认值
	 * 
	 * @param sstring
	 *            判断的字符串
	 * @param sdefault
	 *            默认值
	 * @return String :返回值为String
	 */
	public static String replaceNullWith(String sstring, String sdefault) {
		String sret = null;
		if (isNotNull(sstring)) {
			sret = sstring;
		} else {
			sret = sdefault;
		}

		return sret;
	}

	/**
	 * 替换一段文字中的某个字符
	 * 
	 * @param content
	 *            一段文字
	 * @param oldWord
	 *            要替换的字符
	 * @param newWord
	 *            替换后的字符
	 * @return 替换后的文字
	 */
	public static String replace(String content, String oldWord, String newWord) {
		String tempString = new String(content);
		int position = tempString.indexOf(oldWord);
		while (position > -1) {
			tempString = tempString.substring(0, position) + newWord
					+ tempString.substring(position + oldWord.length());
			position = tempString.indexOf(oldWord, position + newWord.length());
		}
		return tempString;
	}

	/**
	 * 替换空格和回车，对<table /table>中的html内容不做处理
	 * 
	 * @param ls_content
	 *            原内容文本
	 * @return 处理后的内容文本
	 */
	public static String escapeHtmlTag(String ls_content) {
		if (ls_content == null || ls_content.length() == 0) {
			return ls_content;
		}
		int li_len = ls_content.length();
		int i = 0;
		String ls_newcon = "";
		String ls_token = "";

		int theTable = 0;
		for (i = 0; i < li_len; i++) {
			ls_token = "";
			char lc = ls_content.charAt(i);
			if (lc == '<') {
				String ls_temp = ls_content.substring(i, i + 6);

				if (ls_temp.equalsIgnoreCase("<TABLE")) {
					theTable = theTable + 1;
				}
				if (ls_temp.equalsIgnoreCase("</TABL")) {
					theTable = theTable - 1;
				}

			}
			if (theTable > 0) {
				ls_newcon = ls_newcon + ls_content.charAt(i);
				continue;
			}
			if (lc == ' ') {
				ls_token = ls_token + "&nbsp;";
			} else if (lc == '\r') {
				ls_token = ls_token + "<br>";
			} else if (lc == '\t') {
				ls_token = ls_token + "&nbsp;&nbsp;";
			} else if (lc == '\n') {
			} else {
				ls_token = ls_token + lc;
			}
			ls_newcon = ls_newcon + ls_token;
		}
		return ls_newcon;
	}

	private static long UIDCounter = System.currentTimeMillis();

	// 产生唯一的ID
	public static synchronized String generateUID() {
		TextUtil.UIDCounter++;
		return String.valueOf(System.currentTimeMillis())
				+ String.valueOf(UIDCounter);
	}

	/**
	 * 在指定的字符串前加0以达到指定的长度
	 * 
	 * @param id
	 *            待检验字符串
	 * @param leng
	 *            填充长度
	 * @return 经过填充后的字符串
	 * @deprecated use the pad(String ,int length)
	 */
	public static String addZero(int id, int leng) {
		String sid = String.valueOf(id);
		if (sid.length() != leng) {
			int pack = leng - sid.length();
			for (int i = 0; i < pack; i++) {
				sid = "0" + sid;
			}
			return sid;
		} else
			return sid;
	}

	/**
	 * 替换文本中的特殊字符为显示的xml格式，遇到换行，每行作为一个元素
	 * 
	 * @param text
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public static ArrayList xmlEncode(String text) {
		if (text == null)
			return null;
		StringBuffer results = new StringBuffer();
		ArrayList list = new ArrayList();
		int len = text.length();
		for (int i = 0; i < len; i++) {
			char c = text.charAt(i);
			switch (c) {
			case '&':
				results.append("&amp;");
				break;
			case '<':
				results.append("&lt;");
				break;
			case '>':
				results.append("&gt;");
				break;
			case '"':
				results.append("&quot;");
				break;
			case ' ':
				results.append("&#9;");
				break;
			case '\n': {
				list.add(results.toString());
				results.delete(0, results.capacity());
			}
				;
				break;
			default:
				results.append(String.valueOf(c));
				continue;
			}

		} // for i
		if (results == null)
			return list;

		list.add(results.toString());

		return list;
	} // HTMLEncode

	/**
	 * 去处时间后面的0
	 * 
	 * @param timestamp
	 * @return
	 */
	public static String formatTime(String timestamp) {
		if (isNotNull(timestamp) && timestamp.length() > 19)
			return timestamp.substring(0, timestamp.length() - 2);
		return timestamp;
	}

	/**
	 * 压缩输入字符中的重复的数据
	 * 
	 * @param inputstr
	 * @return outstr change log: 1.created and fix the bug 2003-12 Towncarl
	 */
	public static String compress(String inputstr, String delimiter) {
		StringBuffer outstr = new StringBuffer();
		String[] arrtemp = inputstr.split(delimiter);
		int length = arrtemp.length;
		for (int i = 0; i < length; i++) {
			if (arrtemp[i] != "") {
				outstr.append(arrtemp[i] + delimiter);
				for (int j = i + 1; j < length; j++) {
					if (arrtemp[i].equals(arrtemp[j]))
						arrtemp[j] = "";
				}
			}
		}
		if (!inputstr.endsWith(delimiter))
			return outstr.substring(0, outstr.length() - 1);
		else
			return outstr.toString();
	}

	/**
	 * 加密字符串
	 * 
	 * @param pwd
	 *            要加密的字符串
	 * @return 加密后的32位字符串
	 */
	public static String encode(String pwd) {
		byte buf[] = pwd.getBytes();
		StringBuffer hexString = new StringBuffer();
		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(buf);
			byte[] digest = algorithm.digest();
			for (int i = 0; i < digest.length; i++) {
				hexString.append(pad(Integer.toHexString(0xFF & digest[i]), 2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hexString.toString();
	}

	/**
	 * 带签名加密字符串
	 * 
	 * @param userid
	 *            签名
	 * @param pwd
	 *            要加密的字符串
	 * @return 加密后的32位字符串
	 */
	public static String encodeWithKey(String userid, String pwd) {
		byte buf[] = pwd.getBytes();
		byte key[] = userid.getBytes();
		StringBuffer hexString = new StringBuffer();
		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(buf);
			byte[] digest = algorithm.digest(key);
			for (int i = 0; i < digest.length; i++) {
				hexString.append(pad(Integer.toHexString(0xFF & digest[i]), 2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hexString.toString();
	}

	/**
	 * 在字符串之前补0
	 * 
	 * @param i
	 * @param l
	 * @return
	 */
	public static String pad(String i, int l) {
		while (i.length() < l) {
			i = '0' + i;
		}
		return i;
	}

	/**
	 * 1
	 * 
	 * @author Towncarl 2003-12-6
	 */
	/**
	 * 2进制字符串到10进制的转换
	 * 
	 * @param binstr
	 *            可以是以逗号分隔"1,1,1,1,1"或"11111"
	 * @return int 转换后的10进制数据
	 */
	public static int binstrToInt(String binstr) {
		String temp = binstr;
		if (temp.indexOf(',') != -1)
			temp = temp.replaceAll(",", "");
		return Integer.valueOf(temp, 2).intValue();
	}

	/**
	 * int to binstr
	 */
	public static String intToBinstr(int i) {
		return pad(Integer.toBinaryString(i), 7);
	}

	/**
	 * 把一个单一字符转换为以,号分割的字符串
	 * 
	 * @param csv
	 * @return
	 */
	public static String stringToCSS(String csv) {
		StringBuffer sboutstr = new StringBuffer();
		char[] tempca = csv.toCharArray();
		int length = tempca.length;
		if (length > 0)
			sboutstr.append(tempca[0]);
		for (int i = 1; i < length; i++)
			sboutstr.append("," + tempca[i]);
		return sboutstr.toString();
	}

	/**
	 * 比较二个对象值是否相等，传入的二个对象可以为null.
	 * 
	 * @param fromObj
	 *            比较对象
	 * @param toObj
	 *            被比较的对象
	 * @return boolean 比较结果.
	 */
	public static boolean compareObj(Object fromObj, Object toObj) {
		boolean result = false;
		if (fromObj == null) {
			if (toObj == null) {
				result = true;
			}
		} else {
			if (toObj != null) {
				result = fromObj.equals(toObj) ? true : false;
			}
		}

		return result;
	}

	/**
	 * 取得将给定字符串中的<b>\n</b>全部转换成<b>&lt;br&gt;</b>,并且去掉所有的<b>\r</b>的字符串.
	 * 
	 * @param sStr
	 *            字符串参数
	 * @return String 经过处理后的字符串.
	 */
	public static String returnToBr(String sStr) {
		if (sStr == null || sStr.equals("")) {
			return sStr;
		}

		String sTmp = new String();
		int i = 0;

		while (i <= sStr.length() - 1) {
			if (sStr.charAt(i) == '\n') {
				sTmp = sTmp.concat("<br>");
			} else if (sStr.charAt(i) == '\r') {
			} else {
				sTmp = sTmp.concat(sStr.substring(i, i + 1));
			}
			i++;
		}
		return sTmp;
	}
}
