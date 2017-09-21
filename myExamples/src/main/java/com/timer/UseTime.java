package com.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by liujq on 17-9-19.
 */
public class UseTime {
    public static void main(String[] args) {

        Timer timer = new Timer("batchSaveExceptionInfoTimer");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("------------------");

                try{
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                }
            }
        },300000,300000);
    }
}
