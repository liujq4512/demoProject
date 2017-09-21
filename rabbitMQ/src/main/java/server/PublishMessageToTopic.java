package server;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by liujq on 17-8-3.
 */
public class PublishMessageToTopic {
    private static final String EXCHANGE_NAME = "testExchange";

    public static void main(String[] args)throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"topic");

        channel.basicPublish(EXCHANGE_NAME,"hello.test",null,"hello 1, this is a test message send by liujq".getBytes());
        channel.basicPublish(EXCHANGE_NAME,"hello.test.over",null,"hello 1, this is a test message send by liujq".getBytes());
        channel.basicPublish(EXCHANGE_NAME,"hello.test.end",null,"hello 1, this is a test message send by liujq".getBytes());

        connection.close();
    }
}
