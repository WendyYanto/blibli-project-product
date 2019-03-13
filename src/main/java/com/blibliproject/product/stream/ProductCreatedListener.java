package com.blibliproject.product.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductCreatedListener {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public void send(String stream){
        kafkaTemplate.send("products_created",stream);
    }
}
