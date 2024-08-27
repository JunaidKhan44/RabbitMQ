package jk.rabbitmqueue.demo.consumer;


import jk.rabbitmqueue.demo.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitMQJsonConsumer {

    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void consume(User user) {
        log.info(String.format("Json message received : %s", user.toString()));
    }

}
