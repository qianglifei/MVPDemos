package com.bksx.mobile.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	
	public static String md5(String str){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(str.getBytes());
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < digest.length; offset++) {
				i = digest[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				String hexString = Integer.toHexString(i);
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
}
