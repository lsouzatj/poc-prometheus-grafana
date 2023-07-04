package com.br.monitoring.observability.application.ports.out;

import com.br.monitoring.observability.application.core.model.Customer;

import java.util.List;

public interface FindAllCustomerOutputPort {
    List<Customer> findAll();
}
