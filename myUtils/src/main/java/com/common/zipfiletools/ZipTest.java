package com.common.zipfiletools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by liujq on 18-1-24.
 */
public class ZipTest {
  public static String file = "/opt/logs/gc.log";
  public static String destFile = "/opt/logs/gc.zip.log";
  public static String uncompressFile = "/opt/logs/gc.unzip.log";

  public static void main(String[] args) throws Exception {

  }

  public static void unzipFile(){

  }


  public static void zipFile()throws  Exception{
    File inputFile = new File(file);

    ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(destFile));
    BufferedOutputStream outputStream = new BufferedOutputStream(zipOutputStream);
    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
    zipOutputStream.putNextEntry(new ZipEntry(destFile));
    int tag;
    while ((tag=bis.read())!=-1){
      outputStream.write(tag);
    }
    bis.close();
    outputStream.close();
  }



  public static final String decompress(byte[] paramArrayOfByte) {
    if (paramArrayOfByte == null)
      return null;
    ByteArrayOutputStream byteArrayOutputStream = null;
    ByteArrayInputStream byteArrayInputStream = null;
    ZipInputStream zipInputStream = null;
    String str;
    try {
      byteArrayOutputStream = new ByteArrayOutputStream();
      byteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
      zipInputStream = new ZipInputStream(byteArrayInputStream);
      ZipEntry localZipEntry = zipInputStream.getNextEntry();
      byte[] arrayOfByte = new byte[1024];
      int i = -1;
      while ((i = zipInputStream.read(arrayOfByte)) != -1)
        byteArrayOutputStream.write(arrayOfByte, 0, i);
      str = byteArrayOutputStream.toString();
    } catch (IOException localIOException7) {
      str = null;
    } finally {
      if (zipInputStream != null)
        try {
          zipInputStream.close();
        } catch (IOException localIOException8) {
        }
      if (byteArrayInputStream != null)
        try {
          byteArrayInputStream.close();
        } catch (IOException localIOException9) {
        }
      if (byteArrayOutputStream != null)
        try {
          byteArrayOutputStream.close();
        } catch (IOException localIOException10) {
        }
    }
    return str;
  }

  public static final byte[] compress(String paramString) {
    if (paramString == null)
      return null;
    ByteArrayOutputStream byteArrayOutputStream = null;
    ZipOutputStream zipOutputStream = null;
    byte[] arrayOfByte;
    try {
      byteArrayOutputStream = new ByteArrayOutputStream();
      zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
      zipOutputStream.putNextEntry(new ZipEntry("0"));
      zipOutputStream.write(paramString.getBytes());
      zipOutputStream.closeEntry();
      arrayOfByte = byteArrayOutputStream.toByteArray();
    } catch (IOException localIOException5) {
      arrayOfByte = null;
    } finally {
      if (zipOutputStream != null)
        try {
          zipOutputStream.close();
        } catch (IOException localIOException6) {
        }
      if (byteArrayOutputStream != null)
        try {
          byteArrayOutputStream.close();
        } catch (IOException localIOException7) {
        }
    }
    return arrayOfByte;
  }
}
