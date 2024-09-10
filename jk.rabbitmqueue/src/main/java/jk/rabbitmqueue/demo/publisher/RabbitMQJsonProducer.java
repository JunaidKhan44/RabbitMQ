package jk.rabbitmqueue.demo.publisher;


import jk.rabbitmqueue.demo.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Junaid.Khan
 */

@Slf4j
@Service
public class RabbitMQJsonProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.json.key}")
    private String jsonRoutingKey;

    private RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void sendJsonMessage(User user){
        log.info(String.format("Json message sent : %s",user));
        rabbitTemplate.convertAndSend(exchange,jsonRoutingKey,user);
    }
}
