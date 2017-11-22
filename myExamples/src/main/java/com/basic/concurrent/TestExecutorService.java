package com.basic.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liujq on 17-11-15.
 */
public class TestExecutorService {
  public static ExecutorService executorService = Executors.newSingleThreadExecutor();
  public static ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(10);



  public static void main(String[] args) {


    for(int i=0;i<10;i++){
      blockingQueue.offer("init data :"+i);
    }
    consumer();
    System.out.println(">>>>>>>>>>>>>");
    try {
      Thread.sleep(15000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    for(int i=0; i<10;i++){
      blockingQueue.offer(" second add data :"+i);
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }

    consumer();
  }


  public static void consumer(){
    executorService.submit(()->{
      while (!blockingQueue.isEmpty()){
        System.out.println(blockingQueue.poll());
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      System.out.println(">>>>>>>>>> end <<<<<<<<<<<<<<");
    });
  }

}
