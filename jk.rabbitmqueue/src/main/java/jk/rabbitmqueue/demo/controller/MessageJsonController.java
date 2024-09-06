package jk.rabbitmqueue.demo.controller;


import jk.rabbitmqueue.demo.dto.User;
import jk.rabbitmqueue.demo.publisher.RabbitMQJsonProducer;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Junaid.Khan
 */

@RestController
@RequestMapping("/api/v2")
@AllArgsConstructor
public class MessageJsonController {

    private RabbitMQJsonProducer rabbitMQJsonProducer;

    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user){
        rabbitMQJsonProducer.sendJsonMessage(user);
        return ResponseEntity.ok("Json message sent to RabbitMQ");
    }


}
