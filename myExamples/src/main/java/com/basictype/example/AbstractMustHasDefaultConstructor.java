package com.basictype.example;

/**
 * Created by liujq on 17-9-27.
 */
public abstract class AbstractMustHasDefaultConstructor {
    //如果没有这个无参函数，子类继承需要显示实现super
    //public AbstractMustHasDefaultConstructor(){}
    public AbstractMustHasDefaultConstructor(int i){

    }

    protected void showSelf(){
        System.out.println(">>>>>>>>>show my shelf<<<<<<<<");
    }

    public void display(String s){
        System.out.println(">>>>>>>>>>>>>>>>>>" + s);
    }

    public void act(String s){
        display(s);
        showSelf();
    }
}

class Test extends AbstractMustHasDefaultConstructor{


    public Test(int i){
        super(i);
    }

    public void display(String s ){

        System.out.println("---------" + s );
    }

    public void showSelf(){
        System.out.println("---------show my self------------");
    }

    public static void main(String[] args) {
        Test t = new Test(0);
        t.act("hello " );
    }
}