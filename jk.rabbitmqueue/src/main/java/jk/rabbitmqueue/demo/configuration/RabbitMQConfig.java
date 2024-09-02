package jk.rabbitmqueue.demo.configuration;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Junaid.Khan
 */


@Configuration
public class RabbitMQConfig {


    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Value("${rabbitmq.queue.json.name}")
    private String jsonQueue;

    @Value("${rabbitmq.exchange.name}")
    private String topicExchangeName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.routing.json.key}")
    private String jsonRoutingKey;

    //bean config
    @Bean
    public Queue queue(){
        return new Queue(queue);
    }


    @Bean
    public Queue jsonQueue(){
        return new Queue(jsonQueue);
    }

    @Bean
    public TopicExchange topicExchange(){
        return  new TopicExchange(topicExchangeName);
    }

    //bind b/w queue and exchange
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue())
                .to(topicExchange())
                .with(routingKey);
    }


    @Bean
    public Binding jsonBinding(){
        return BindingBuilder.bind(jsonQueue())
                .to(topicExchange())
                .with(jsonRoutingKey);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return  rabbitTemplate;
    }
}
