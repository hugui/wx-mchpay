package com.github.hugui.wxmchpay.core.util;

import java.security.MessageDigest;

/**
 * 
 * @author zcb
 * @2017年12月20日
 */
public class MD5Util {

	public static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 静态方法，便于作为工具类
	public static String getMd5(String plainText) throws Exception {

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(plainText.getBytes());
		byte b[] = md.digest();

		int i;

		StringBuffer buf = new StringBuffer("");
		for (int offset = 0; offset < b.length; offset++) {
			i = b[offset];
			if (i < 0) {
				i += 256;
			}
			if (i < 16) {
				buf.append("0");
			}
			buf.append(Integer.toHexString(i));
		}
		// 32位加密
		return buf.toString();
		// 16位的加密
		// return buf.toString().substring(8, 24);

	}

	/*
	 * 和上面结果一样，加密过程不同
	 */
	public static String string2MD5(String inStr) throws Exception {
		MessageDigest md5 = null;
		md5 = MessageDigest.getInstance("MD5");

		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++) {
			byteArray[i] = (byte) charArray[i];
		}
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}
	public static String compute(String var0) throws Exception {
		MessageDigest var1 = MessageDigest.getInstance("MD5");
		char[] var2 = var0.toCharArray();
		byte[] var3 = new byte[var2.length];

		for(int var4 = 0; var4 < var2.length; ++var4) {
			var3[var4] = (byte)var2[var4];
		}

		byte[] var8 = var1.digest(var3);
		StringBuffer var5 = new StringBuffer();

		for(int var6 = 0; var6 < var8.length; ++var6) {
			int var7 = var8[var6] & 255;
			if (var7 < 16) {
				var5.append("0");
			}

			var5.append(Integer.toHexString(var7));
		}

		return var5.toString();
	}

	public final static String MD5Encoder(String s, String charset) {
		try {
			byte[] btInput = s.getBytes(charset);
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < md.length; i++) {
				int val = ((int) md[i]) & 0xff;
				if (val < 16){
					sb.append("0");
				}
				sb.append(Integer.toHexString(val));
			}
			return sb.toString();
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] args) {
		//MD5Util m = new MD5Util();
		//System.out.println(m.MD5("appId=7SlHOj1MGtOXVkS6YkPDJPcPAs4Sv7&bizContent=%7B%22phone%22%3A%2215110326475%22%2C%22merchantUserId%22%3A%2292dc656fe81f46f8abd8bd33ff672a8e%22%7D&charset=UTF8&format=JSON&serviceName=openAccount&signType=MD5&timestamp=20180423153044&version=1.0jMFMTsjaJfD6bvt1H7ZFUDvbsKjZym"));
		String str = "";
		try {
			str = MD5Util.MD5Encoder("U20EJDYDQ180R5M110CS53K832RD25M4","UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("-----"+str);
	}
}
