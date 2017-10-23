package com.common.log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.rolling.RollingFileAppender;

/**
 * Created by liujq on 17-10-20.
 */
public class SetFileNameRollingFileAppender extends RollingFileAppender<ILoggingEvent> {

    @Override
    protected void subAppend(ILoggingEvent event) {
        String fileName = "";
        if(event.getMDCPropertyMap()!= null){
            if(event.getMDCPropertyMap().get("jobName")!=null){
                fileName = event.getMDCPropertyMap().get("jobName") +".log";
            }
        }

        setFile(fileName);
        start();
        super.subAppend(event);
    }
}
