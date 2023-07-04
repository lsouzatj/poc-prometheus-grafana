package com.br.monitoring.observability.adapters.out;

import com.br.monitoring.observability.application.core.model.Customer;
import com.br.monitoring.observability.application.ports.out.FindByIdOutputPort;
import com.br.monitoring.observability.adapters.out.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Log4j2
@Component
@RequiredArgsConstructor
public class FindByIdCustomerOutputAdapater implements FindByIdOutputPort {
    private final CustomerRepository customerRepository;
    @Override
    public Optional<Customer> findById(Long id) {
        log.info("Finding customer by id");
        var optionalCustomerEntity = customerRepository.findById(id);
        if (optionalCustomerEntity.isPresent()){
            Customer customer = new Customer();
            BeanUtils.copyProperties(optionalCustomerEntity.get(), customer);
            log.info("Customer found");
            return Optional.of(customer);
        }
        log.info("Customer not found");
        return Optional.empty();
    }
}
