package client;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import processer.ProcessMessage;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * Created by liujq on 17-8-3.
 */
public class HelloWorld {
    private final static Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    private final static String QUEUE_NAME = "testQueueWithDurable";
    public final static ExecutorService executor = new ThreadPoolExecutor(4,10,30,
            TimeUnit.SECONDS, new LinkedBlockingDeque<>());
    //private final static String QUEUE_NAME = "topicQ2";


    public static void main(String[] args)throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        channel.basicConsume(QUEUE_NAME, new Consumer() {
            @Override
            public void handleConsumeOk(String consumerTag) {
                logger.info("==========handleConsumeOk");
            }

            @Override
            public void handleCancelOk(String consumerTag) {
                logger.info("==========handleCancelOk");
            }

            @Override
            public void handleCancel(String consumerTag) throws IOException {
                logger.info("==========handleCancel");
            }

            @Override
            public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
                logger.info("==========handleShutdownSignal");
            }

            @Override
            public void handleRecoverOk(String consumerTag) {
                logger.info("==========handleRecoverOk");
            }

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                ProcessMessage processMessage = new ProcessMessage(new String(body,"UTF-8"),channel,envelope.getDeliveryTag());

                try {
                    Future future = executor.submit(processMessage);


                    //future.get();
                }catch (Exception e){
                    logger.error(">>>>>>>>>>>>"+e.getMessage()+"<<<<<<<<<<<<<<");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }


                /*logger.info("=====start to processor message: " + new String(body,"UTF-8"));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                logger.info("=====finished processor message: " + new String(body,"UTF-8"));*/


                //channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });

        //connection.close();
    }
}
