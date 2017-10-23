package com.common.annotations;

/**
 * Created by liujq on 17-10-17.
 */
public class AnnotationExample {

    @MethodInfo(author = "for test",version = 2,comments = "some Comments")
    @Override
    public String toString(){
        return "";
    }
}
