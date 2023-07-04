package com.br.monitoring.observability.adapters.out;

import com.br.monitoring.observability.adapters.out.repository.CustomerRepository;
import com.br.monitoring.observability.adapters.out.repository.entity.CustomerEntity;
import com.br.monitoring.observability.application.core.model.Customer;
import com.br.monitoring.observability.application.ports.out.DeleteByIdCustomOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class DeleteByIdCustomerOutputAdapter implements DeleteByIdCustomOutputPort {
    private final CustomerRepository customerRepository;
    @Override
    public void delete(Customer customer) {
        log.info("Deleting customer");
        CustomerEntity customerEntity = new CustomerEntity();
        BeanUtils.copyProperties(customer, customerEntity);
       customerRepository.delete(customerEntity);
       log.info("Customer deleted");
    }
}
