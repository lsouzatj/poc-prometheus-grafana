package com.br.monitoring.observability.application.ports.in;

import com.br.monitoring.observability.application.core.model.Customer;

import java.util.List;

public interface FindAllCustomerInputPort {
    List<Customer> findAll();
}
