package com.vip.qa.autov.core.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Decoder;

/**
 * MD5的算法在RFC1321 中定�?在RFC 1321中，给出了Test suite用来�?��你的实现是否正确�?MD5 ("") =
 * d41d8cd98f00b204e9800998ecf8427e MD5 ("a") = 0cc175b9c0f1b6a831c399e269772661
 * MD5 ("abc") = 900150983cd24fb0d6963f7d28e17f72 MD5 ("message digest") =
 * f96b697d7cb7938d525a2f31aaf161d0 MD5 ("abcdefghijklmnopqrstuvwxyz") =
 * c3fcd3d76192e4007dfb496cca67e13b
 * 
 * @author haogj
 * 
 *         传入参数：一个字节数�?传出参数：字节数组的 MD5 结果字符�?
 */
public class MD5 {
	public static String getMD5(byte[] source) {
		String s = null;
		char hexDigits[] = { // 用来将字节转换成 16 进制表示的字�?
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest(); // MD5 的计算结果是�?�� 128 位的长整数，
										// 用字节表示就�?16 个字�?
			char str[] = new char[16 * 2]; // 每个字节�?16 进制表示的话，使用两个字符，
											// �?��表示�?16 进制�?�� 32 个字�?
			int k = 0; // 表示转换结果中对应的字符位置
			for (int i = 0; i < 16; i++) { // 从第�?��字节�?��，对 MD5 的每�?��字节
											// 转换�?16 进制字符的转�?
				byte byte0 = tmp[i]; // 取第 i 个字�?
				str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中�?4 位的数字转换,
															// >>>
															// 为�?辑右移，将符号位�?��右移
				str[k++] = hexDigits[byte0 & 0xf]; // 取字节中�?4 位的数字转换
			}
			s = new String(str); // 换后的结果转换为字符�?

		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public static String convertMD5(String inStr) {

		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;

	}

	public static String encrypt(String content, String key) {
		// 产生1000-100000的随机数
		// String random = String.valueOf(1000 + (int) (Math.random() * (100000
		// - 1000)));
		String random = "1";
		random = MD5.getMD5(random.getBytes());

		key = MD5.getMD5(key.getBytes());
		int len = content.length();
		String encrypt = "";
		char[] randomChar = random.toCharArray();
		char[] keyChar = key.toCharArray();
		char[] contentChar = content.toCharArray();
		for (int i = 0; i < len; i++) {
			int k = i >= 32 ? 0 : i;
			encrypt = encrypt + randomChar[k] + (char) (contentChar[i] ^ randomChar[k]);
		}
		// 二次加密
		char[] encryptChar = encrypt.toCharArray();
		len = encrypt.length();
		String contentSec = "";
		for (int i = 0; i < len; i++) {
			int k = i >= 32 ? 0 : i;
			contentSec = contentSec + (char) (encryptChar[i] ^ keyChar[k]);
		}
		return getBASE64(contentSec).replace("\r\n", "");
	}

	// java 解密
	public static String decrypt(String content, String key) {
		key = MD5.getMD5(key.getBytes());
		content = getFromBASE64(content);
		int len = content.length();

		char[] keyChar = key.toCharArray();
		char[] contentChar = content.toCharArray();
		String decrypt = "";
		for (int i = 0; i < len; i++) {
			int k = i >= 32 ? 0 : i;
			decrypt = decrypt + (char) (contentChar[i] ^ keyChar[k]);
		}
		// 二次解密
		char[] decryptChar = decrypt.toCharArray();
		len = decrypt.length();
		String contentSec = "";
		for (int i = 0; i < len; i++) {
			char keyCh = decryptChar[i];
			i++;
			contentSec = contentSec + (char) (decryptChar[i] ^ keyCh);
		}
		return contentSec;

	}

	// $content = '这是明文';
	// $key = '加密和解密共用密�?;
	//
	// $encrypt = encrypt($content, $key); //加密
	// $decrypt = decrypt($encrypt, $key); //解密
	public static String getBASE64(String s) {
		if (s == null)
			return null;
		return (new sun.misc.BASE64Encoder()).encode(s.getBytes());
	}

	// �?BASE64 编码的字符串 s 进行解码
	public static String getFromBASE64(String s) {

		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b);
		} catch (Exception e) {
			return null;
		}

	}

	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes("UTF-8"));
			byte b[] = md.digest();
			int i;
			StringBuilder buf = new StringBuilder();
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			return null;
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	public static String getMd5(String str) {
		return MD5.getMD5(str.getBytes());
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(md5("哈哈"));
	}
}
