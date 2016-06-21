package com.ekt.cms.openapi.sdk.util;

import org.apache.http.HttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.*;

public class LetvApiUtil {

    private LetvApiUtil() {
    }

//
//	public static String getAddress(String url, Map<String, String> params) {
//		if (params == null || params.size() == 0) {
//			return url;
//		}
//		Set<Entry<String, String>> entries = params.entrySet();
//		StringBuilder address = new StringBuilder(url);
//		address.append("?");
//		for (Iterator<Entry<String, String>> iterator = entries.iterator(); iterator.hasNext();) {
//			Entry<String, String> entry = iterator.next();
//			address.append(entry.getKey());
//			address.append("=");
//			try {
//				address.append(java.net.URLEncoder.encode(entry.getValue(), "UTF-8"));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			if (iterator.hasNext()) {
//				address.append("&");
//			}
//		}
//		return address.toString();
//	}
//
//	public static Map<String, String> describe(Object bean) {
//		try {
//			@SuppressWarnings("unchecked")
//			Map<String, String> map = PropertyUtils.describe(bean);
//			List<String> remove = new ArrayList<String>();
//			map.remove("class");
//
//			// 查找删除的key
//			for (Entry<String, String> entry : map.entrySet()) {
//				if (entry.getValue() == null) {
//					remove.add(entry.getKey());
//				}
//			}
//			// 开始删除
//			for (Iterator<String> iterator = remove.iterator(); iterator.hasNext();) {
//				String key = iterator.next();
//				map.remove(key);
//			}
//			return map;
//		} catch (Exception e) {
//			throw new RuntimeException("bean转换map异常", e);
//		}
//	}

    public static String digest(Map<String, String> params, String secret) {
        StringBuilder result = new StringBuilder();
        List<String> keys = new ArrayList<String>(params.keySet());// map中的参数是区分大小写的
        Collections.sort(keys);
        for (Iterator<String> iterator = keys.iterator(); iterator.hasNext(); ) {
            String key = iterator.next();
            if (!"sign".equalsIgnoreCase(key)) { // 必须这么写，set里调用remove方法，无法忽略大小写;request里的map无法remove
                result.append(key).append(params.get(key));
            }
        }
        result.append(secret);
        System.out.println("result------->  " + result);
        return LetvApiUtil.MD5(result.toString());
    }

//	/**
//	 * generate call-id
//	 * 
//	 * @return
//	 */
//	public static String generateCallId() {
//		try {
//			return LetvApiUtil.MD5(UUID.randomUUID().toString());
//		} catch (Exception e) {
//			throw new RuntimeException();
//		}
//	}
//
//	/**
//	 * get Greenwich second-time since 1970
//	 * 
//	 * @return
//	 */
//	public static String generateTimestamp() {
//		long y1970 = Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTimeInMillis() / 1000;
//		return String.valueOf(y1970);
//	}

    /**
     * generate MD5
     *
     * @param src
     * @return
     * @throws Exception
     */
    public static String MD5(String src) {
        try {
            if (src == null) {
                return "";
            }
            byte[] result = null;
            MessageDigest alg = MessageDigest.getInstance("MD5");
            result = alg.digest(src.getBytes("utf-8"));
            return byte2hex(result);
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    /**
     * generate MD5
     *
     * @param src
     * @return
     * @throws Exception
     */
    public static String MD5(byte[] src) {
        try {
            if (src == null) {
                return "";
            }
            byte[] result = null;
            MessageDigest alg = MessageDigest.getInstance("MD5");
            result = alg.digest(src);
            return byte2hex(result);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private static String byte2hex(byte[] b) {
        if (b == null) {
            return "";
        }
        StringBuffer hs = new StringBuffer();
        String stmp = null;
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1) {
                hs.append("0");
            }
            hs.append(stmp);
        }
        return hs.toString();
    }

//	private static byte[] MD5Bytes(byte[] src) throws Exception {
//		byte[] result = null;
//		if (src != null) {
//			MessageDigest alg = MessageDigest.getInstance("MD5");
//			result = alg.digest(src);
//		}
//		return result;
//	}

    public static String getResponseBody(HttpResponse response) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }
}
