package client;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by liujq on 17-8-3.
 */
public class ReceiveMessageFromTopic {
    private static final Logger logger = LoggerFactory.getLogger(ReceiveMessageFromTopic.class);
    private static final String EXCHANGE_NAME = "testExchange";


    public static void main(String[] args)throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"topic");

        //String queueName = channel.queueDeclare().getQueue();  //可以通过这种方法获取 自动产生的queue
        //logger.info("queueDeclare name is : " + queueName);

        channel.queueBind("topicQ2","testExchange", "#test");

        channel.basicConsume("topicQ2", new Consumer() {
            @Override
            public void handleConsumeOk(String consumerTag) {

            }

            @Override
            public void handleCancelOk(String consumerTag) {

            }

            @Override
            public void handleCancel(String consumerTag) throws IOException {

            }

            @Override
            public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {

            }

            @Override
            public void handleRecoverOk(String consumerTag) {

            }

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                logger.info("get message from topicQ2: " + new String(body,"UTF-8"));
            }
        });

        connection.close();
    }
}
