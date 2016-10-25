package com.talpro213.mindvalley_tamim_maaz_android_test;

import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Tamim Maaz on 9/19/2016.
 */
public class MD5UnitTest {

    @Test
    public  void workMD5() throws Exception{
        String str_1 = "https://www.google.com.tr/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8#q=%D8%B9%D8%B1%D8%A8%D9%8A%20%D8%A8%D8%AD%D8%AB";
        String str_2 = "https://www.google.com.tr/we_bhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8#q=%D8%B9%D8%B1%D8%A8%D9%8A%20%D8%A8%D8%AD%D8%AB";
        String str_3 = "https://www.google.com.tr/we!bhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8#q=%D8%B9%D8%B1%D8%A8%D9%8A%20%D8%A8%D8%AD%D8%AB";
        String str_4 = "https://www.google.com.tr/we bhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8#q=%D8%B9%D8%B1%D8%A8%D9%8A%20%D8%A8%D8%AD%D8%AB";


        String md5 = md5(str_1);
        assertEquals(md5, md5(str_1));
        assertEquals(md5, md5(str_2));
        assertEquals(md5, md5(str_3));
        assertEquals(md5, md5(str_4));
    }

    public static final String md5(final String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
