package com.basictype.example;

/**
 * Created by liujq on 17-9-27.
 */
public abstract class AbstractMustHasDefaultConstructor {
    //如果没有这个无参函数，子类继承需要显示实现super
    //public AbstractMustHasDefaultConstructor(){}
    public AbstractMustHasDefaultConstructor(int i){

    }
}

class Test extends AbstractMustHasDefaultConstructor{


    public Test(int i){
        super(i);
    }

}