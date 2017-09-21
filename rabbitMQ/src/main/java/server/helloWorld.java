package server;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by liujq on 17-8-3.
 */
public class helloWorld {
    private final static Logger logger = LoggerFactory.getLogger(helloWorld.class);
    private final static String QUEUE_NAME = "testQueueWithDurable";
    //private final static String QUEUE_NAME = "autoDeleteQueue";

    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);

        while (true){
            String message = " hello world rabbitMQ ==========" + new Date().getTime();
            channel.basicPublish("",QUEUE_NAME, MessageProperties.PERSISTENT_BASIC,message.getBytes());
            logger.info(" [x] Sent '" + message + "'");
            Thread.sleep(10);
        }

    }
}
