package com.basic.concurrent;

/**
 * Created by liujq on 17-9-11.
 */
public class HowToInterrupt {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    System.out.println("--------get interrupt signal------------------");
                    e.printStackTrace();
                }
                System.out.println("--------------thread wake up------------");
            }
        });

        thread.start();
        System.out.println("------------try to interrupt thread----------");
        thread.interrupt();
        try {
            System.out.println(" wait thread completed ");
            thread.join();
            System.out.println("finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
