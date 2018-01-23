package com.common.zipfiletools;

import org.xerial.snappy.Snappy;
import org.xerial.snappy.SnappyFramedInputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by liujq on 18-1-23.
 */
public class SnappyTest {
  public static String file = "/opt/logs/gc.log";
  public static String compressFile = "/opt/logs/snappy_gc.log";
  public static String uncompressFile = "/opt/logs/snappy_uncompress_gc.log";

  public static void main(String[] args) throws Exception{
    //compress();
    uncompress();

  }


  public static void testSimpleBytes(){
    byte[] bytes = compressHtml(uncompressFile);
    System.out.println(bytes);

    String s = decompressHtml(bytes);
    System.out.println(s);
  }

  public static  byte[] compressHtml(String html){
    try {
      return Snappy.compress(html.getBytes("UTF-8"));
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
  public static  String decompressHtml(byte[] bytes){
    try {
      return new String(Snappy.uncompress(bytes));
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }





  public static void uncompress()throws Exception{
    FileInputStream inputStream = new FileInputStream(compressFile);
    FileOutputStream outputStream = new FileOutputStream(uncompressFile);

    ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
    byte[] inputBytes = new byte[1024];
    int i=0;
    while ((i=inputStream.read(inputBytes))!=-1){
      out.write(inputBytes,0,i);
    }

    byte[] data = out.toByteArray();

    SnappyFramedInputStream snappyFramedInputStream = new SnappyFramedInputStream(new ByteArrayInputStream(data));
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(Snappy.uncompressedLength(data));
    snappyFramedInputStream.transferTo(byteArrayOutputStream);


//    byte[] outputBytes = Snappy.uncompress(data);
//    System.out.println(">>>>>>>>>>>>" + new String(outputBytes));
    outputStream.write(byteArrayOutputStream.toByteArray());
    out.close();
    inputStream.close();
    outputStream.close();
  }

  public static void compress()throws Exception{
    FileInputStream inputStream = new FileInputStream(file);
    FileOutputStream outputStream = new FileOutputStream(compressFile);
    byte[] inputBytes = new byte[1024];
    byte[] outputBytes = new byte[1024];
    while (inputStream.read(inputBytes)!=-1){
      System.out.println(">>>" + new String(inputBytes));
      outputBytes = Snappy.compress(inputBytes);
      outputStream.write(outputBytes);
    }
    inputStream.close();
    outputStream.close();
  }
}
