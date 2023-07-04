package com.br.monitoring.observability.application.core.usecase;

import com.br.monitoring.observability.application.core.model.Customer;
import com.br.monitoring.observability.application.ports.in.FindByIdCustomerInputPort;
import com.br.monitoring.observability.application.ports.in.UpdateCustomerInputPort;
import com.br.monitoring.observability.application.ports.out.SendKafkaCustomerOuputPort;
import com.br.monitoring.observability.application.ports.out.UpdateCustomerOutputPort;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

public class UpdateCustomerUseCase implements UpdateCustomerInputPort {
    private final UpdateCustomerOutputPort updateCustomerOutputPort;
    private final FindByIdCustomerInputPort findByIdCustomerInputPort;
    private final SendKafkaCustomerOuputPort sendKafkaCustomerOuputPort;

    public UpdateCustomerUseCase(UpdateCustomerOutputPort updateCustomerOutputPort, FindByIdCustomerInputPort findByIdCustomerInputPort, SendKafkaCustomerOuputPort sendKafkaCustomerOuputPort) {
        this.updateCustomerOutputPort = updateCustomerOutputPort;
        this.findByIdCustomerInputPort = findByIdCustomerInputPort;
        this.sendKafkaCustomerOuputPort = sendKafkaCustomerOuputPort;
    }

    @Override
    public Optional<Customer> update(Customer customer, Long id) {
        return findByIdCustomerInputPort.findById(id).map(customerOld -> {
            customer.setId(customerOld.getId());
            var newCustomer = updateCustomerOutputPort.update(customer);
            BeanUtils.copyProperties(newCustomer.get(), customerOld);
            sendKafkaCustomerOuputPort.send(customerOld);
            return Optional.of(customerOld);
        }).orElse(Optional.empty());
    }
}
