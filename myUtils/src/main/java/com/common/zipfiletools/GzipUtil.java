package com.common.zipfiletools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by liujq on 18-1-25.
 */
public class GzipUtil {

  public static String uncompress(String str)throws IOException{
    if (null == str || str.length() <= 0) {
      return str;
    }
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes("UTF-8"));
    GZIPInputStream gzip = new GZIPInputStream(in);
    byte[] buffer = new byte[256];
    int n = 0;
    while ((n = gzip.read(buffer)) >= 0) {
      out.write(buffer, 0, n);
    }
    return out.toString("UTF-8");
  }

  public static String compress(String str) throws IOException{
    if (null == str || str.length() <= 0) {
      return str;
    }
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    GZIPOutputStream gzip =null;
    try{
      gzip = new GZIPOutputStream(out);
      gzip.write(str.getBytes("UTF-8"));
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
    return out.toString("UTF-8");
  }

  public static void main() throws Exception{

  }
}
