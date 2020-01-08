package util;
import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * RSA安全编码组件
 * 
 */
public class RSAUtils {

	public static final String KEY_ALGORITHM = "RSA";
	
	//58公钥
	public static final String platform_publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCJ4uUQ5+38BlYrOf2iK6ZMVX1YdShZcD1qAL2n1/uUCCJbtHbeKVhM0w8tXTJSPZeT0X4+4xX0HXjG70j5pgDsXcMwfvHSaP9/94EtsIv6f/cfgT28MECUdyaMh4FBem4nBSfmPP8XnwhqwCExIwNhQ8s48xf9lyfrDU83JbbpEwIDAQAB";

	/**
     * RSA最大加密明文大小 
     */  
    private static final int MAX_ENCRYPT_BLOCK = 117;  


	/**
	 * 用公钥加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, String key) throws Exception {
		// 对公钥解密
		byte[] keyBytes = decryptBASE64(key);

		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicKey = keyFactory.generatePublic(x509KeySpec);

		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		int inputLen = data.length;  
        ByteArrayOutputStream out = new ByteArrayOutputStream(); 
		int offSet = 0;  
        byte[] cache;  
        int i = 0;  
        // 对数据分段加密  
        while (inputLen - offSet > 0) {  
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {  
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);  
            } else {  
                cache = cipher.doFinal(data, offSet, inputLen - offSet);  
            }  
            out.write(cache, 0, cache.length);  
            i++;  
            offSet = i * MAX_ENCRYPT_BLOCK;  
        }  
        byte[] encryptedData = out.toByteArray();  
        out.close();  
        return encryptedData; 
	}
	
	/**
	 * BASE64加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(byte[] key) throws Exception {
		return (new BASE64Encoder()).encodeBuffer(key);
	}

	/**
	 * BASE64解密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptBASE64(String key) throws Exception {
		return (new BASE64Decoder()).decodeBuffer(key);
	}
	
	/**
	 * RSA公钥加密后再用base64加密
	 * @param src
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String src,String publicKey) throws Exception {
		String res = null;
		byte[] mid  = encryptByPublicKey(src.getBytes(), publicKey);
		res = encryptBASE64(mid);
		res = res.replaceAll("\r", "").replaceAll("\n", "");
		return res;
	}
	
	/**
	 * 加密测试
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String content = "要测试的数据要测试的数据要测试的数据要测试的数据要测试的数据要测试的数据要测试的数据要测试的数据";
		//用58公钥加密
		String encryptData = RSAUtils.encrypt(content,platform_publicKey);
		System.out.println(encryptData);
	}
}
