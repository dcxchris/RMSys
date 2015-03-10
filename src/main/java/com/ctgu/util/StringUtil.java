// Source File Name:   StringUtil.java

package com.ctgu.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class StringUtil
{

    public StringUtil()
    {
    }

    public static boolean isEmpty(String string)
    {
        return string == null || "".equals(string.trim());
    }

    public static boolean isNotEmpty(String string)
    {
        return !isEmpty(string);
    }

    public static String replaceForSql(String string)
    {
        return string.replaceAll("['\",;%*]", "").trim();
    }

    public static String urlEncode(String url, String encoding)
    {
        try
        {
            return URLEncoder.encode(url, encoding);
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String urlDecode(String url, String encoding)
    {
        try
        {
            return URLDecoder.decode(url, encoding);
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String first(String string)
    {
        return left(string, 1);
    }

    public static String left(String string, int count)
    {
        if(string.length() < count)
            return string;
        else
            return string.substring(0, count);
    }

    public static String right(String string, int count)
    {
        return string.substring(string.length() - count);
    }

    public static String last(String string)
    {
        return right(string, 1);
    }

    public static String removeLast(String string, int count)
    {
        if(string.length() <= count)
            return "";
        else
            return string.substring(0, string.length() - count);
    }

    public static String removeLast(String string)
    {
        return removeLast(string, 1);
    }

    public static String trim(String string, String remove)
    {
        if(string != null && string.length() > 0)
        {
            boolean b = string.indexOf(remove) == 0;
            boolean e = string.lastIndexOf(remove) == string.length() - 1;
            if(b && string.length() > 1)
                string = string.substring(1);
            if(e)
                string = string.substring(0, string.length() - 1);
            if(b || e)
                string = trim(string, remove);
        }
        return string;
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

    public static boolean contains(String string, String s, String split)
    {
        if(string == null || s == null)
        {
            return false;
        } else
        {
            string = (new StringBuilder(String.valueOf(split))).append(trim(string, split)).append(split).toString();
            s = (new StringBuilder(String.valueOf(split))).append(trim(s, split)).append(split).toString();
            return string.contains(s);
        }
    }

    public static boolean contains(String string, String s)
    {
        return contains(string, s, ",");
    }

    public static String toUnicode(String string)
    {
        StringBuilder sb = new StringBuilder();
        char cs[] = string.toCharArray();
        char ac[];
        int j = (ac = cs).length;
        for(int i = 0; i < j; i++)
        {
            char c = ac[i];
            String tmp = Integer.toHexString(c);
            sb.append(tmp.length() != 4 ? ((Object) (Character.valueOf(c))) : ((Object) ((new StringBuilder("\\u")).append(tmp).toString())));
        }

        return sb.toString();
    }
}
