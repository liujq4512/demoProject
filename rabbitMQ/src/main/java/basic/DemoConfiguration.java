package basic;


import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.ClassPathResource;


/**
 * Created by liujq on 17-8-7.
 */

@Configuration
@ComponentScan(basePackages = "liujq")
@org.springframework.context.annotation.PropertySource("classpath:application.yaml")
public class DemoConfiguration {


    @Bean
    @Qualifier("mqConnectionFactory")
    public ConnectionFactory getConnectionFactory(
            @Value("${rabbitMQ.host}") String host
    ){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        return connectionFactory;
    }

    @Bean
    @Qualifier("testQueueWithDurable")
    public Queue testQueueWithDurable(){
        Queue queue = new Queue("testQueueWithDurable",true,false,false,null);
        return queue;
    }

    @Bean
    public SimpleMessageListenerContainer getSimpleMessageListenerContainer(
            @Qualifier("mqConnectionFactory")ConnectionFactory connectionFactory,
            @Qualifier("testQueueWithDurable")Queue queue,
            @Autowired MessageListener messageListener){

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(queue);
        container.setConcurrentConsumers(4);
        container.setAcknowledgeMode(AcknowledgeMode.AUTO); //设置确认模式自动确认，抛出异常则认为消费失败，否则认为成功
        container.setMaxConcurrentConsumers(10);
        container.setMessageListener(messageListener);
        return container;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        yamlPropertiesFactoryBean.setResources(new ClassPathResource("application.yaml"));

        MutablePropertySources propertySource = new MutablePropertySources();
        propertySource.addFirst(new PropertiesPropertySource("yamlProperties", yamlPropertiesFactoryBean.getObject()));
        PropertySourcesPlaceholderConfigurer placeholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        placeholderConfigurer.setPropertySources(propertySource);

        return placeholderConfigurer;
    }

}
