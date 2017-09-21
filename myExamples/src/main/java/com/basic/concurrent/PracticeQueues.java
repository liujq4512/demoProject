package com.basic.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Created by liujq on 17-9-20.
 */
public class PracticeQueues {


    private static ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(100);

    //是线程安全的，实现了先进先出等特性，是作为生产者消费者的首选;LinkedBlockingQueue 可以指定容量，也可以不指定，不指定的话，
    // 默认最大是Integer.MAX_VALUE;其中主要用到put和take方法，put方法在队列满的时候会阻塞直到有队列成员被消费，
    // take方法在队列空的时候会阻塞，直到有队列成员被放进来。
    private static LinkedBlockingDeque<String> linkedBlockingDeque = new LinkedBlockingDeque<>(100);


    static void useArrayBlockingQueue(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    //System.out.println("------------"+arrayBlockingQueue.peek());//获取但不移除队列头
                    //System.out.println("------------"+arrayBlockingQueue.poll());//获取并移除队列头
                    try {
                        System.out.println("------------"+arrayBlockingQueue.take());//获取并移除队列头，并一直等待
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        for(int i=0;i<1000;i++){
            //arrayBlockingQueue.add(""+i);//如果队列满会抛出异常
            arrayBlockingQueue.offer(""+i); //return false if full
            try {
                //arrayBlockingQueue.offer(""+i,5, TimeUnit.SECONDS);//return false if full after 5 seconds
                System.out.println("========="+arrayBlockingQueue.size());

                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        useArrayBlockingQueue();
    }

}
