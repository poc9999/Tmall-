package tmall.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//MD5√‹¬Îº”√‹π§æﬂ
public class MD5Util {
	public static String getMD5(String str){
		MessageDigest digest;
		String s=null;
		try {
			digest = MessageDigest.getInstance("MD5");
			
			digest.update(str.getBytes());
			
			s=new BigInteger(1,digest.digest()).toString(16);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
}
