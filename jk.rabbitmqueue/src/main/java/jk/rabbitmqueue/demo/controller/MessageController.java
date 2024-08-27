package jk.rabbitmqueue.demo.controller;


import jk.rabbitmqueue.demo.publisher.RabbitMQProducer;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class MessageController {

    private RabbitMQProducer  rabbitMQProducer;


    //  localhost:8080/api/v1/publish?message=hello junaid
    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
        rabbitMQProducer.sendMessage(message);
        return ResponseEntity.ok("message sent to RabbitMQ");
    }

}
