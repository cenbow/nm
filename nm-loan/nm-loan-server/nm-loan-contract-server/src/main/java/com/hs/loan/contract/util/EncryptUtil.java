package com.hs.loan.contract.util;

import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class EncryptUtil
{
  public static String encryptStr(String passWord)
    throws Exception
  {
    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");

    KeySpec keySpec = new PBEKeySpec("123123123".toCharArray());

    SecretKey secretKey = keyFactory.generateSecret(keySpec);

    PBEParameterSpec parameterSpec = new PBEParameterSpec(new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }, 1000);

    Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");

    cipher.init(1, secretKey, parameterSpec);

    byte[] passEn = cipher.doFinal(passWord.getBytes());

    return bytesToHexString(passEn);
  }

  public static String decryptStr(String passWordEn)
    throws Exception
  {
    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");

    KeySpec keySpec = new PBEKeySpec("123123123".toCharArray());

    SecretKey secretKey = keyFactory.generateSecret(keySpec);

    PBEParameterSpec parameterSpec = new PBEParameterSpec(new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }, 1000);

    Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");

    cipher.init(2, secretKey, parameterSpec);

    byte[] passDec = cipher.doFinal(hexStringToBytes(passWordEn));

    return new String(passDec);
  }

  public static String bytesToHexString(byte[] src)
  {
    StringBuilder stringBuilder = new StringBuilder("");
    if ((src == null) || (src.length <= 0))
    {
      return null;
    }
    for (int i = 0; i < src.length; i++)
    {
      int v = src[i] & 0xFF;
      String hv = Integer.toHexString(v);
      if (hv.length() < 2)
      {
        stringBuilder.append(0);
      }
      stringBuilder.append(hv);
    }
    return stringBuilder.toString();
  }

  public static byte[] hexStringToBytes(String hexString)
  {
    if ((hexString == null) || (hexString.equals("")))
    {
      return null;
    }
    hexString = hexString.toUpperCase();
    int length = hexString.length() / 2;
    char[] hexChars = hexString.toCharArray();
    byte[] d = new byte[length];
    for (int i = 0; i < length; i++)
    {
      int pos = i * 2;
      d[i] = ((byte)(charToByte(hexChars[pos]) << 4 | charToByte(hexChars[(pos + 1)])));
    }
    return d;
  }

  private static byte charToByte(char c)
  {
    return (byte)"0123456789ABCDEF".indexOf(c);
  }
}
