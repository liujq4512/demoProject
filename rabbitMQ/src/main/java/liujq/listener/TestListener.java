package liujq.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * Created by liujq on 17-8-7.
 */
@Component
public class TestListener implements MessageListener {
    private final static Logger logger = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onMessage(Message message) {

        logger.info("start =========="+ message);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("end =========="+ message);
    }
}
