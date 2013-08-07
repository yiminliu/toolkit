package com.tscp.toolkit.util.encryption;

import java.security.MessageDigest;

import org.springframework.security.core.codec.Hex;

public class MD5Encoder {

	public static String md5Encryption(String text) { //throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md;
        byte[] md5 = null;
        try{
           md = MessageDigest.getInstance("MD5");
           md5 = new byte[64];
           md.update(text.getBytes("iso-8859-1"), 0, text.length());
           md5 = md.digest();
        }
        catch(Exception e){
	      e.printStackTrace();	    	
        }
        return toHex(md5);  		  
     }
	
     private static final String toHex(byte[] digest) {
         String hexString = new String(Hex.encode(digest));
         return hexString;
     }
}
