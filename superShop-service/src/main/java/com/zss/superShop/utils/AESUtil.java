package com.zss.superShop.utils;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.zss.superShop.api.entity.RspCode;
import com.zss.superShop.exception.SuperShopException;

import lombok.extern.slf4j.Slf4j;

/**
 * AES加密工具类
 * @author zhuss
 * 2016年9月29日下午2:29:35
 */
@Slf4j
public class AESUtil {

	/**
	 * 密钥算法 java6支持56位密钥，bouncycastle支持64位
	 * */
	public static final String KEY_ALGORITHM = "AES";

	/**
	 * 加密/解密算法/工作模式/填充方式
	 * 
	 * JAVA6 支持PKCS5PADDING填充方式 Bouncy castle支持PKCS7Padding填充方式
	 * */
	public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

	public static final String SECRET_KEY = "1234567890123456";
	
	/**
     * 加密
     * @param content 需要加密的内容
     * @param password  加密密码
     * @return
	 * @throws Exception 
     */
	public static byte[] encrypt(String content,String secretKey){
		try {
			SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), KEY_ALGORITHM);
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
		} catch (Exception e) {
			log.error(">>>>>>{}加密  error :{}",content,e);
			throw new SuperShopException(RspCode.DATA_ERROR);
		}
	}
	
	/**
	 * AES加密后用Base64进行二次加密
	 * @param str
	 * @return
	 * @throws Exception 
	 */
	public static String encrypt(String str){
		byte[] data; // 加密数据
		try {
			data = encrypt(str,SECRET_KEY);
			return parseByte2HexStr(data);
		} catch (Exception e) {
			log.error(">>>>>>{}加密  error :{}",str,e);
			throw new SuperShopException(RspCode.DATA_ERROR);
		}
	}
    
    /**解密
     * @param content  待解密内容
     * @return
     * @throws Exception 
     */
	public static byte[] decrypt(byte[] content){
		try {
			SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), KEY_ALGORITHM);
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; 
		} catch (Exception e) {
			throw new SuperShopException(RspCode.DATA_ERROR);
		}
	}
	
	/**
	 *  解密数据
	 * @param value：base加密后的数据
	 * @return
	 * @throws Exception 
	 */
	public static String decrypt(String value){
		byte[] hex2Data; 
		byte[] aes128Data; 
		try {
			hex2Data = parseHexStr2Byte(value);
			aes128Data = decrypt(hex2Data);
			return new String(aes128Data);
		} catch (Exception e) {
			log.error(">>>>>>{}解密  error :{}",value,e);
			throw new SuperShopException(RspCode.DATA_ERROR);
		}
	}
	
	private static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}
