// Source File Name:   EncryptUtil.java

package com.ctgu.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncryptUtil
{

    public EncryptUtil()
    {
    }

    private static String messageDigest(String encryptType, byte input[])
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance(encryptType);
            md.update(input);
            byte out[] = md.digest();
            return byte2hex(out);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private static String messageDigest(String encryptType, InputStream inputStream)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance(encryptType);
            byte input[] = new byte[0xfa000];
            int length;
            while((length = inputStream.read(input)) > 0) 
                md.update(input, 0, length);
            byte out[] = md.digest();
            return byte2hex(out);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private static String messageDigest(String encryptType, String string, String encoding)
    {
        if(encoding == null)
            encoding = "utf-8";
        try
        {
            return messageDigest(encryptType, string.getBytes(encoding));
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String byte2hex(byte b[])
    {
        String hex = "";
        String stmp = "";
        for(int i = 0; i < b.length; i++)
        {
            stmp = Integer.toHexString(b[i] & 0xff);
            if(stmp.length() == 1)
                hex = (new StringBuilder(String.valueOf(hex))).append("0").append(stmp).toString();
            else
                hex = (new StringBuilder(String.valueOf(hex))).append(stmp).toString();
        }

        return hex.toLowerCase();
    }

    public static byte[] hex2byte(String string)
    {
        byte b[] = string.getBytes();
        byte bytes[] = new byte[b.length / 2];
        for(int i = 0; i < b.length; i += 2)
        {
            String item = new String(b, i, 2);
            bytes[i / 2] = (byte)Integer.parseInt(item, 16);
        }

        return bytes;
    }

    public static byte[] string2byte(String string)
    {
        try
        {
            return string.getBytes("utf-8");
        }
        catch(UnsupportedEncodingException e)
        {
            return null;
        }
    }

    public static String byte2string(byte bytes[])
    {
        try
        {
            return new String(bytes, "utf-8");
        }
        catch(UnsupportedEncodingException e)
        {
            return null;
        }
    }

    public static final String md5(String string, String encoding)
    {
        return messageDigest("MD5", string, encoding);
    }

    public static final String md5(String string)
    {
        return messageDigest("MD5", string, "utf-8");
    }

    public static final String md5(InputStream inputStream)
    {
        return messageDigest("MD5", inputStream);
    }

    public static final String sha1(String string, String encoding)
    {
        return messageDigest("SHA1", string, encoding);
    }

    public static final String sha1(String string)
    {
        return messageDigest("SHA1", string, "utf-8");
    }

    public static final String sha1(InputStream inputStream)
    {
        return messageDigest("SHA1", inputStream);
    }

    public static final String base64Encoding(byte bytes[], boolean isRemoveLineSeparator)
    {
        String string = null;
        if(bytes != null)
        {
            BASE64Encoder encoder = new BASE64Encoder();
            string = encoder.encode(bytes);
        }
        return string != null ? isRemoveLineSeparator ? string.replace(System.getProperty("line.separator"), "") : string : null;
    }

    public static final String base64Encoding(String string, String encoding)
    {
        try
        {
            return base64Encoding(string.getBytes(encoding), false);
        }
        catch(UnsupportedEncodingException e)
        {
            return null;
        }
    }

    public static final String base64EncodingWithUtf8(String string)
    {
        return base64Encoding(string, "utf-8");
    }

    public static final byte[] base64Decoding(String string, boolean isHaveLineSeparator)
    {
        BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            if(isHaveLineSeparator)
            {
                int num = string.length() / 76;
                String tmp = string;
                string = "";
                for(int i = 0; i < num; i++)
                    string = (new StringBuilder(String.valueOf(string))).append(tmp.substring(i * 76, (i + 1) * 76)).append("\r\n").toString();

                string = (new StringBuilder(String.valueOf(string))).append(tmp.substring(num * 76)).toString();
            }
            return decoder.decodeBuffer(string);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static final String base64Decoding(String string, String encoding)
    {
        byte bytes[] = base64Decoding(string, false);
        try
        {
            return new String(bytes, encoding);
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static final String base64DecodingWithUtf8(String string)
    {
        return base64Decoding(string, "utf-8");
    }

    private static Key getKey(byte bytes[], String type)
    {
        Key key = new SecretKeySpec(bytes, type);
        return key;
    }

    private static Key getKey(String string, String type)
    {
        type = type.toUpperCase();
        String s = md5(string);
        byte bytes[] = s.getBytes();
        byte b[] = (byte[])null;
        if("DES".equals(type))
        {
            b = new byte[8];
            for(int i = 0; i < b.length; i += 2)
                b[i] = (byte)(bytes[i] & bytes[i + 1] ^ bytes[31 - i] & bytes[30 - i]);

        } else
        if("AES".equals(type))
        {
            b = new byte[16];
            for(int i = 0; i < b.length; i++)
                b[i] = (byte)(bytes[i] ^ bytes[31 - i]);

        }
        return getKey(b, type);
    }

    private static byte[] cipher(byte data[], int mode, String type, Key key)
    {
        byte target[] = (byte[])null;
        try
        {
            Cipher cipher = Cipher.getInstance(type);
            cipher.init(mode, key);
            target = cipher.doFinal(data);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return target;
    }

    public static final byte[] desEncrypt(String string, byte key[])
    {
        byte data[] = string2byte(string);
        return cipher(data, 1, "DES", getKey(key, "DES"));
    }

    public static final byte[] desEncrypt(String string, String key)
    {
        byte data[] = string2byte(string);
        return cipher(data, 1, "DES", getKey(key, "DES"));
    }

    public static final String desDecrypt(String string, byte key[])
    {
        byte data[] = hex2byte(string);
        byte source[] = cipher(data, 2, "DES", getKey(key, "DES"));
        return byte2string(source);
    }

    public static final String desDecrypt(String string, String key)
    {
        byte data[] = hex2byte(string);
        byte source[] = cipher(data, 2, "DES", getKey(key, "DES"));
        return byte2string(source);
    }

    public static final byte[] aesEncrypt(String string, byte key[])
    {
        byte data[] = string2byte(string);
        return cipher(data, 1, "AES", getKey(key, "AES"));
    }

    public static final byte[] aesEncrypt(String string, String key)
    {
        byte data[] = string2byte(string);
        return cipher(data, 1, "AES", getKey(key, "AES"));
    }

    public static final String aesDecrypt(String string, byte key[])
    {
        byte data[] = hex2byte(string);
        byte source[] = cipher(data, 2, "AES", getKey(key, "AES"));
        return byte2string(source);
    }

    public static final String aesDecrypt(String string, String key)
    {
        byte data[] = hex2byte(string);
        byte source[] = cipher(data, 2, "AES", getKey(key, "AES"));
        return byte2string(source);
    }
    
    public static void main(String[] args) {
    	/*String str1 = sha1(md5("123"));
    	System.out.println(str1);*/
    	
    	String key = "CTGU";
    	String source = "root";
    	String result = byte2hex(desEncrypt(source,key));
    	System.out.println(result);
    	System.out.println(desDecrypt(result,key));
    	
	}
    
}
