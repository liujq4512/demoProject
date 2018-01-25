package com.common.zipfiletools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Clob;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by liujq on 18-1-24.
 */
public class GzipTest {
  public static String file = "/opt/logs/gc.log";
  public static String destFile = "/opt/logs/gc.gzip.log";
  public static String uncompressFile = "/opt/logs/gc.ungzip.log";
  private static String s = "{\"1\":[{\"p\":103,\"a\":100,\"i\":10,\"f\":5}],\"2\":[{\"p\":102,\"a\":-100,\"i\":15,\"f\":0}]}";

  public static void main(String[] args) throws Exception{
    String ss = "{\"101\":[{\"p\":20004,\"a\":7.25,\"i\":0,\"f\":0}],\"102\":[{\"p\":20004,\"a\":-7.25,\"i\":0,\"f\":0}]}";
    String compress = compress(ss);
    System.out.println(compress);
    System.out.println(unCompress(compress));
  }

  /**
   * 字符串的解压
   *
   * @param str
   *            对字符串解压
   * @return    返回解压缩后的字符串
   * @throws IOException
   */
  public static String unCompress(String str) throws IOException {
    if (null == str || str.length() <= 0) {
      return str;
    }
    // 创建一个新的 byte 数组输出流
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    // 创建一个 ByteArrayInputStream，使用 buf 作为其缓冲区数组
    ByteArrayInputStream in = new ByteArrayInputStream(str
      .getBytes());
    // 使用默认缓冲区大小创建新的输入流
    GZIPInputStream gzip = new GZIPInputStream(in);
    byte[] buffer = new byte[256];
    int n = 0;
    while ((n = gzip.read(buffer)) >= 0) {// 将未压缩数据读入字节数组
      // 将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此 byte数组输出流
      out.write(buffer, 0, n);
    }
    // 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串
    return out.toString();
  }


  private static String compress(String str) {
    if (null == str || str.length() <= 0) {
      return str;
    }
    // 创建一个新的 byte 数组输出流
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    GZIPOutputStream gzip =null;
    try{
      gzip = new GZIPOutputStream(out);
      gzip.write(str.getBytes());

    }catch (Exception e){
    }finally {
      if(gzip!=null){
        try {
          gzip.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return out.toString();
  }

  private String toStringByGzip(Clob clob){
    InputStream inputStream = null;
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    GZIPInputStream gzipInputStream = null;
    try{
      int size = (int)clob.length();
      inputStream = clob.getAsciiStream();

      gzipInputStream = new GZIPInputStream(inputStream);
      byte[] bytes = new byte[size];
      while (-1 != (gzipInputStream.read(bytes,0,size))){
        out.write(bytes,0,size);
      }
    }catch (Exception e){
    }finally {
      try {
        if(gzipInputStream!=null) {
          gzipInputStream.close();
        }

        if(inputStream!=null){
          inputStream.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return out.toString();
  }


  public static void ungzip()throws Exception{
    FileInputStream inputStream = new FileInputStream(destFile);
    GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);
    FileOutputStream outputStream = new FileOutputStream(uncompressFile);
    byte[] bytes = new byte[1024];
    int i =0;
    while ((i=gzipInputStream.read(bytes))!=-1){
      outputStream.write(bytes,0,i);
    }
    outputStream.close();
    gzipInputStream.close();
    inputStream.close();
  }

  public static void gzip()throws Exception{
    FileInputStream inputStream = new FileInputStream(file);
    byte[] bytes = new byte[inputStream.available()];
    inputStream.read(bytes);

    FileOutputStream outputStream = new FileOutputStream(destFile);
    GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);
    gzipOutputStream.write(bytes);
    gzipOutputStream.close();
    inputStream.close();
  }
}
