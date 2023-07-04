package com.br.monitoring.observability.adapters.in.consumer;

import com.br.monitoring.observability.application.core.model.Customer;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class ReceiveKafkaCustomer {

    @Value("${topic.name.consumer}")
    private String topicName;

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id", containerFactory = "customerListener")
    public void consume(Customer customer) {
        log.info("Tópico: {}", topicName);
        log.info("ReceiveKafkaCustomer. Enviando mensagem de notificaçao para o cliente {}", customer.getName());
    }
}
