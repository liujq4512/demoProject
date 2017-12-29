package com.common.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liujq on 17-10-19.
 */
public abstract class LogTest {
    private static final Logger logger = LoggerFactory.getLogger(LogTest.class);

    public static void main(String[] args) {
        MDC.put("JobName","testJob");
        logger.info("==========={}{}===========","main"," method");
        logger.error("something wrong happened !");

        Map<String, String> mdcMap = MDC.getCopyOfContextMap();
        ExecutorService pool = Executors.newCachedThreadPool();
        for(int i=0;i<5;i++){
            Thread t = new Thread(()->{
                MDC.setContextMap(mdcMap);
                UseByLogTest.logJobName();
            });
            pool.submit(t);
        }

        LogTest logTest = new SubLogTest();
        logTest.showSomething();

    }

    abstract void showSomething();

}
