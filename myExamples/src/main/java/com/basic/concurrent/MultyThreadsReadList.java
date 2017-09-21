package com.basic.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liujq on 17-9-15.
 */
public class MultyThreadsReadList {
    private  static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        List list = new ArrayList();

        for(int i=0;i<10;i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int n=0;n<10;n++){
                        getIndexAndAdd1(list, Thread.currentThread().getName());
                    }
                }
            },"Thread-"+i);
            thread.start();
        }
    }


    public static void getIndexAndAdd1(List list,String threadName){
        lock.lock();
        try{
            int size = list.size();
            System.out.println(threadName + " get size is："+size);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(size);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void getIndexAndAdd0(List list,String threadName){
        synchronized (list){
            int size = list.size();
            System.out.println(threadName + " get size is："+size);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(size);
        }
    }
}
