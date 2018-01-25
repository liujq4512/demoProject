package com.common.zipfiletools;

import com.sun.deploy.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by liujq on 18-1-25.
 */
public class GzipUtils {

  public static final String GZIP_ENCODE_UTF_8 = "UTF-8";

  public static final String GZIP_ENCODE_ISO_8859_1 = "ISO-8859-1";

  /**
   * 字符串压缩为GZIP字节数组
   *
   * @param str
   * @return
   */
  public static byte[] compress(String str) {
    return compress(str, GZIP_ENCODE_UTF_8);
  }

  /**
   * 字符串压缩为GZIP字节数组
   *
   * @param str
   * @param encoding
   * @return
   */
  public static byte[] compress(String str, String encoding) {
    if (str == null || str.length() == 0) {
      return null;
    }
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    GZIPOutputStream gzip;
    try {
      gzip = new GZIPOutputStream(out);
      gzip.write(str.getBytes(encoding));
      gzip.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return out.toByteArray();
  }

  /**
   * GZIP解压缩
   *
   * @param bytes
   * @return
   */
  public static byte[] uncompress(byte[] bytes) {
    if (bytes == null || bytes.length == 0) {
      return null;
    }
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ByteArrayInputStream in = new ByteArrayInputStream(bytes);
    try {
      GZIPInputStream ungzip = new GZIPInputStream(in);
      byte[] buffer = new byte[256];
      int n;
      while ((n = ungzip.read(buffer)) >= 0) {
        out.write(buffer, 0, n);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return out.toByteArray();
  }

  /**
   *
   * @param bytes
   * @return
   */
  public static String uncompressToString(byte[] bytes) {
    return uncompressToString(bytes, GZIP_ENCODE_UTF_8);
  }

  /**
   *
   * @param bytes
   * @param encoding
   * @return
   */
  public static String uncompressToString(byte[] bytes, String encoding) {
    if (bytes == null || bytes.length == 0) {
      return null;
    }
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ByteArrayInputStream in = new ByteArrayInputStream(bytes);
    try {
      GZIPInputStream ungzip = new GZIPInputStream(in);
      byte[] buffer = new byte[256];
      int n;
      while ((n = ungzip.read(buffer)) >= 0) {
        out.write(buffer, 0, n);
      }
      return out.toString(encoding);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static void main(String[] args)throws Exception {
    String str =
      "{\"101\":[{\"p\":20004,\"a\":7.25,\"i\":0,\"f\":0}],\"102\":[{\"p\":20004,\"a\":-7.25,\"i\":0,\"f\":0}]}";
//    System.out.println(new String(str.getBytes("ISO-8859-1"),"UTF-8"));
//

    String co = new String(compress(str,"ISO-8859-1"),"ISO-8859-1") ;
    System.out.println(new String(uncompress(co.getBytes("ISO-8859-1"))));


    /*System.out.println("原长度：" + str.length());
    System.out.println("压缩后字符串：" + compress(str).toString().length());
    System.out.println("解压缩后字符串：" + new String(uncompress(compress(str))));
    System.out.println("解压缩后字符串：" + uncompressToString(compress(str)));*/
  }
}
