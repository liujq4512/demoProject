package com.common.zipfiletools;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * Created by liujq on 18-1-22.
 */
public class deflateTest {
  public static String file = "/opt/logs/gc.log";
  public static String destFile = "/opt/logs/deflate_gc.log";
  public static String uncompressFile = "/opt/logs/deflate_uncompress_gc.log";


  public static void compress() throws IOException{
    Deflater deflater = new Deflater();
    FileInputStream inputStream = new FileInputStream(file);
    FileOutputStream outputStream = new FileOutputStream(destFile);
    byte[] inputBytes = new byte[1024];
    byte[] outputBytes = new byte[1024];
    while (inputStream.read(inputBytes)!=-1){
      deflater.setInput(inputBytes);
      int deflate = deflater.deflate(outputBytes);
      outputStream.write(outputBytes,0,deflate);
    }
    deflater.end();
    inputStream.close();
    outputStream.close();

  }

  public static void uncompress()throws Exception{
    FileInputStream fileInputStream = new FileInputStream(destFile);
    FileOutputStream fileOutputStream = new FileOutputStream(uncompressFile);
    ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
    byte[] inputBytes = new byte[1024];
    byte[] outputBytes;

    while (fileInputStream.read(inputBytes)!=-1){
      out.write(inputBytes);
    }
    outputBytes = decompress(out.toByteArray());
    fileOutputStream.write(outputBytes);
    /*while (fileInputStream.read(inputBytes)!=-1){
      Inflater inflater = new Inflater();
      inflater.reset();
      inflater.setInput(inputBytes);
      while (!inflater.finished()){
        int inflate = inflater.inflate(outputBytes);
        fileOutputStream.write(outputBytes,0,inflate);
      }
      inflater.end();
    }*/

    /*fileInputStream.read(inputBytes);
    inflater.reset();
    inflater.setInput(inputBytes);
    int inflate = inflater.inflate(outputBytes);
    fileOutputStream.write(outputBytes,0,inflate);*/

    fileInputStream.close();
    fileOutputStream.close();

  }


  public static void main(String[] args) throws Exception{
//    compress();
    uncompress();

//    testSimpleByte();
  }







  public static void testSimpleByte(){
    byte[] compress = compress(destFile.getBytes());
    System.out.println(">>>>>>>>>>>>>"+ new String(compress));

    byte[] decompress = decompress(compress);
    System.out.println(">>>>>>>>>>>>>"+ new String(decompress));
  }
  /**
   * 压缩
   *
   * @param data 待压缩数据
   * @return byte[] 压缩后的数据
   */
  public static byte[] compress(byte[] data) {
    byte[] output = new byte[0];

    Deflater compresser = new Deflater();

    compresser.reset();
    compresser.setInput(data);
    compresser.finish();
    ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);
    try {
      byte[] buf = new byte[1024];
      while (!compresser.finished()) {
        int i = compresser.deflate(buf);
        bos.write(buf, 0, i);
      }
      output = bos.toByteArray();
    } catch (Exception e) {
      output = data;
      e.printStackTrace();
    } finally {
      try {
        bos.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    compresser.end();
    return output;
  }

  /**
   * 解压缩
   *
   * @param data 待压缩的数据
   * @return byte[] 解压缩后的数据
   */
  public static byte[] decompress(byte[] data) {
    byte[] output = new byte[0];

    Inflater decompresser = new Inflater();
    decompresser.reset();
    decompresser.setInput(data);

    ByteArrayOutputStream o = new ByteArrayOutputStream(data.length);
    try {
      byte[] buf = new byte[1024];
      while (!decompresser.finished()) {
        int i = decompresser.inflate(buf);
        o.write(buf, 0, i);
      }
      output = o.toByteArray();
    } catch (Exception e) {
      output = data;
      e.printStackTrace();
    } finally {
      try {
        o.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    decompresser.end();
    return output;
  }
}
