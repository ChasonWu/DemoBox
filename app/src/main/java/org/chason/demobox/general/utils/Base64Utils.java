package org.chason.demobox.general.utils;

import android.util.Base64;

/**
 * Base64加密解密工具
 * @author Chason
 *
 */
public class Base64Utils {

	/**
	 * Base64编码
	 * @param text
	 * @return
	 */
	public static String encode(String text) {
		byte[] encode = Base64.encode(text.getBytes(), Base64.DEFAULT);
		return new String(encode);
	}

	/**
	 * Base64解码
	 * @param text
	 * @return
	 */
	public static String decode(String text) {
		byte[] encode = Base64.decode(text.getBytes(), Base64.DEFAULT);
		return new String(encode);
	}
	
}
