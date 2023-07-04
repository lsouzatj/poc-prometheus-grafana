package com.br.monitoring.observability.adapters.out.producer;

import com.br.monitoring.observability.application.core.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class SendKafkaCustomer {

    @Value("${topic.name.producer}")
    private String topicName;
    private final KafkaTemplate<String, Customer> kafkaTemplate;

    public void send(Customer customer) {
        log.info("SendKafka Payload enviado: {}", customer);
        kafkaTemplate.send(topicName, customer).addCallback(
                success -> log.info("Messaged send sucessfuly:{}", success.getProducerRecord().value()),
                failure -> log.error("Error call kafka:{}", failure.getMessage())
        );
    }
}