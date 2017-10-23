package com.common.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liujq on 17-10-20.
 */
public class UseByLogTest {
    private static final Logger logger = LoggerFactory.getLogger(UseByLogTest.class);

    public static void logJobName(){
        logger.error(">>>>>>>>>>> <<<<<<<<<<<<<<");
        try {
            throw new IllegalArgumentException("=============");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }

    }

}
