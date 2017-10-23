package com.common.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * Created by liujq on 17-10-19.
 */
public abstract class LogTest {
    private static final Logger logger = LoggerFactory.getLogger(LogTest.class);

    public static void main(String[] args) {

        logger.info("===========");
        logger.error("something wrong happened !");

        for(int i=0;i<5;i++){
            Thread t = new Thread(()->UseByLogTest.logJobName());
            t.start();
        }
        MDC.put("JobName","testJob");
        LogTest logTest = new SubLogTest();
        logTest.showSomething();

    }

    abstract void showSomething();

}
