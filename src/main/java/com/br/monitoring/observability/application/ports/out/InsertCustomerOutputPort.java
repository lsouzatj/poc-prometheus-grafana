package com.br.monitoring.observability.application.ports.out;

import com.br.monitoring.observability.application.core.model.Customer;

public interface InsertCustomerOutputPort {
    Customer save(Customer customer);
}
