package jk.rabbitmqueue.demo.publisher;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Junaid.Khan
 */

@Slf4j
@Service
public class RabbitMQProducer {

    @Value("${rabbitmq.exchange.name}")
    private String topicExchangeName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    private RabbitTemplate template;

    public RabbitMQProducer(RabbitTemplate template) {
        this.template = template;
    }
    public void sendMessage(String message) {
        log.info(String.format("Messsage sent : %s", message));
        template.convertAndSend(topicExchangeName, routingKey, message);
    }
}
