package basic;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by liujq on 17-8-7.
 */
public class DemoStarter {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(DemoConfiguration.class);
        context.refresh();
    }
}
