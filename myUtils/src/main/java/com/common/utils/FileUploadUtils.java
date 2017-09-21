package com.common.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by liujq on 17-8-11.
 */
public class FileUploadUtils {


    /*protected void saveToLocal(MultipartFile file){
        File file1 = new File("/opt/middleTest.xlsx");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file1);
            byte[] buffer = new byte[1024];
            while ((file.getInputStream().read(buffer))!=-1){
                fileOutputStream.write(buffer);
            }
            file.getInputStream().close();
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void sendFileWithMultipart(String uri, MethodType methodType, MultipartFile file){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(systemConfig.getAccountingEndpoint() + uri);
            FileBody fileBody = new FileBody((File)file);
            MultipartEntity reqEntity = new MultipartEntity();
            reqEntity.addPart("voucherFile", fileBody);
            httpPost.setEntity(reqEntity);
            httpClient.execute(httpPost);

        }catch (Exception e){
            log.error("sendFile error :" + e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected void sendFileInEntity(String uri, MethodType methodType, MultipartFile file){

        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(systemConfig.getAccountingEndpoint() + uri);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setCharset(Charset.forName("UTF-8"));
            builder.addBinaryBody("voucherFile", file.getInputStream(), ContentType.MULTIPART_FORM_DATA, file.getOriginalFilename());
            builder.addTextBody("filename", file.getOriginalFilename());// 类似浏览器表单提交，对应input的name和value

            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);

        }catch (Exception e){
            log.error("sendFile error :" + e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                file.getInputStream().close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    protected void urlUploadFile(String uri, MethodType methodType, InputStream inputStream){
        DataOutputStream dataOutputStream = null;
        try {
            URL url = new URL(systemConfig.getAccountingEndpoint()+uri);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod(methodType.name());
            //httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Charset","UTF-8");
            httpURLConnection.setRequestProperty("Content-Type","multipart/form-data");
            dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            log.info("---------start to upload file------------");
            byte[] buffer = new byte[1024];
            while (inputStream.read(buffer) != -1){
                dataOutputStream.write(buffer);
            }

            inputStream.close();
            dataOutputStream.flush();

            if(httpURLConnection.getResponseCode()>=300){
                throw new AccountingRemoteException( "HTTP Request is not success, Response code is "
                        + httpURLConnection.getResponseCode());
            }
            log.info("---------finished to upload file------------");
        } catch (Exception e) {
            log.error("uploadFile error :" + e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                dataOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/

}
