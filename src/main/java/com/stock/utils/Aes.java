package com.stock.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Hex;

import com.stock.constants.AESConstants;

public class Aes {
	private static String aesPrivateKey = AESConstants.KOREA_INVESTMENT_AES_KEY;
	
	public static String aesCBCEndoce(String plainText) throws Exception{
		
		SecretKeySpec secretKey = new SecretKeySpec(aesPrivateKey.getBytes("UTF-8"), "AES" );
		IvParameterSpec IV = new IvParameterSpec(aesPrivateKey.substring(0, 16).getBytes());
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, IV);
		
		byte[] encrpytionByte = cipher.doFinal(plainText.getBytes("UTF-8"));
		
		return Hex.encodeHexString(encrpytionByte);
		
	}
	
	public static String aesCBCDedoce(String encodeText) throws Exception{
		
		SecretKeySpec secretKey = new SecretKeySpec(aesPrivateKey.getBytes("UTF-8"), "AES" );
		IvParameterSpec IV = new IvParameterSpec(aesPrivateKey.substring(0, 16).getBytes());
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		cipher.init(Cipher.DECRYPT_MODE, secretKey, IV);
		
		byte[] decodeByte = Hex.decodeHex(encodeText.toCharArray());
		
		return new String(cipher.doFinal(decodeByte), "UTF-8");
		
	}
}

