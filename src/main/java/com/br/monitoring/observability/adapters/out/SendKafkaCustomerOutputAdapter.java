package com.br.monitoring.observability.adapters.out;

import com.br.monitoring.observability.adapters.out.producer.SendKafkaCustomer;
import com.br.monitoring.observability.application.core.model.Customer;
import com.br.monitoring.observability.application.ports.out.SendKafkaCustomerOuputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendKafkaCustomerOutputAdapter implements SendKafkaCustomerOuputPort {

    private final SendKafkaCustomer sendKafkaCustomer;

    @Override
    public void send(Customer customer) {
        sendKafkaCustomer.send(customer);
    }
}
