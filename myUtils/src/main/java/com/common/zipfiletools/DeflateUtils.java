package com.common.zipfiletools;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Base64;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * Created by liujq on 18-1-23.
 */
public class DeflateUtils {

  private static final int BUFFER_SIZE = 4 * 1024;
  public static String file = "/opt/logs/gc.log";
  public static String destFile = "/opt/logs/deflate_dictionary_gc.log";
  public static String uncompressFile = "/opt/logs/deflate_dictionary_uncompress_gc.log";


  public static byte[] getDictionary(){
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    try{
      ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream);
      oos.writeChars("HeapDumpOnOutOfMemoryError");
      oos.writeChars("concurrent");
      oos.writeChars("[GC");
    }catch (Exception e){
      e.printStackTrace();
    }
    return byteArrayOutputStream.toByteArray();
  }
  /**
   * compress data by {@linkplain Level}
   *
   * @author lichengwu
   * @created 2013-02-07
   *
   * @param data
   * @param level
   *            see {@link Level}
   * @return
   * @throws IOException
   */
  public static byte[] compress(byte[] data, Level level) throws IOException {


    Deflater deflater = new Deflater();
    // set compression level
    deflater.setLevel(level.getLevel());
    deflater.setDictionary(getDictionary());
    deflater.setInput(data);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

    deflater.finish();
    byte[] buffer = new byte[BUFFER_SIZE];
    while (!deflater.finished()) {
      int count = deflater.deflate(buffer); // returns the generated
      // code... index
      outputStream.write(buffer, 0, count);
    }
    byte[] output = outputStream.toByteArray();
    outputStream.close();
    return output;
  }

  /**
   * decompress data
   *
   * @author lichengwu
   * @created 2013-02-07
   *
   * @param data
   * @return
   * @throws IOException
   * @throws DataFormatException
   */
  public static byte[] decompress(byte[] data) throws IOException, DataFormatException {


    Inflater inflater = new Inflater();
    inflater.setDictionary(getDictionary());
    inflater.setInput(data);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
    byte[] buffer = new byte[BUFFER_SIZE];
    while (!inflater.finished()) {
      int count = inflater.inflate(buffer);
      outputStream.write(buffer, 0, count);
    }
    byte[] output = outputStream.toByteArray();
    outputStream.close();
    return output;
  }



  public static String compress(String data) throws IOException{

    if(data==null){
      return data;
    }
    String output = "";

    Deflater compress = new Deflater();

    compress.reset();
    compress.setInput(data.getBytes("UTF-8"));
    compress.finish();
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    try {
      byte[] buf = new byte[1024];
      while (!compress.finished()) {
        int i = compress.deflate(buf);
        bos.write(buf, 0, i);
      }
      output = new String(Base64.getEncoder().encode(bos.toByteArray()),"UTF-8");
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
    compress.end();
    return output;
  }


  public static String uncompress(String data) throws IOException{
    if(data==null){
      return data;
    }
    String output = "";

    Inflater decompress = new Inflater();
    decompress.reset();
    decompress.setInput(Base64.getDecoder().decode(data));

    ByteArrayOutputStream o = new ByteArrayOutputStream();
    try {
      byte[] buf = new byte[1024];
      while (!decompress.finished()) {
        int i = decompress.inflate(buf);
        o.write(buf, 0, i);
      }
      output = new String(o.toByteArray(),"UTF-8");
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
    decompress.end();
    return output;
  }

  /**
   * Compression level
   */
  public static enum Level {

    /**
     * Compression level for no compression.
     */
    NO_COMPRESSION(0),

    /**
     * Compression level for fastest compression.
     */
    BEST_SPEED(1),

    /**
     * Compression level for best compression.
     */
    BEST_COMPRESSION(9),

    /**
     * Default compression level.
     */
    DEFAULT_COMPRESSION(-1);

    private int level;

    Level(
      int level) {
      this.level = level;
    }
    public int getLevel() {
      return level;
    }
  }


  public static void testCompress() throws Exception {

    BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
    ByteArrayOutputStream out = new ByteArrayOutputStream(1024);

    byte[] temp = new byte[1024];
    int size = 0;
    while ((size = in.read(temp)) != -1) {
      out.write(temp, 0, size);
    }
    in.close();
    byte[] data = out.toByteArray();
    byte[] output = compress(data, Level.BEST_COMPRESSION);
    System.out.println("before : " + (data.length / 1024) + "k");
    System.out.println("after : " + (output.length / 1024) + "k");

    FileOutputStream fos = new FileOutputStream(destFile);
    fos.write(output);
    out.close();
    fos.close();
  }

  public static void testDecompress() throws Exception {

    BufferedInputStream in = new BufferedInputStream(new FileInputStream(
      destFile));
    ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
    byte[] temp = new byte[1024];
    int size = 0;
    while ((size = in.read(temp)) != -1) {
      out.write(temp, 0, size);
    }
    in.close();
    byte[] data = out.toByteArray();
    byte[] output = decompress(data);
    System.out.println("before : " + (data.length / 1024) + "k");
    System.out.println("after : " + (output.length / 1024) + "k");

    FileOutputStream fos = new FileOutputStream(uncompressFile);
    fos.write(output);
    out.close();
    fos.close();
  }


  public static void main(String[] args) throws Exception{
    testCompress();
    testDecompress();
  }
}
