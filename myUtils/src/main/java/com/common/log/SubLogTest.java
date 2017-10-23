package com.common.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liujq on 17-10-23.
 */
public class SubLogTest extends LogTest {

    private static final Logger logger = LoggerFactory.getLogger(SubLogTest.class);
    @Override
    void showSomething() {
        logger.info(" something in sub logTest");
    }
}
