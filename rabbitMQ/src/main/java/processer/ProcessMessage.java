package processer;


import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by liujq on 17-8-7.
 */
public class ProcessMessage implements Runnable{
    private final static Logger logger = LoggerFactory.getLogger(ProcessMessage.class);

    private String message;
    private Channel channel;
    private long deliveryTag;

    public ProcessMessage(String message, Channel channel, long deliveryTag){
        this.message = message;
        this.channel = channel;
        this.deliveryTag = deliveryTag;
    }


    @Override
    public void run() {
        logger.info("=====start to processor message: " + message);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("=====finished processor message: " + message);
        try {
            logger.info("=========ack to message:"+ message);
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
