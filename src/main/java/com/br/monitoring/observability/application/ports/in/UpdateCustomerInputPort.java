package com.br.monitoring.observability.application.ports.in;

import com.br.monitoring.observability.application.core.model.Customer;

import java.util.Optional;

public interface UpdateCustomerInputPort {
    Optional<Customer> update(Customer customer, Long id);
}
