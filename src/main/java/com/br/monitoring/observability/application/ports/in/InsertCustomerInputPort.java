package com.br.monitoring.observability.application.ports.in;

import com.br.monitoring.observability.application.core.model.Customer;

public interface InsertCustomerInputPort {
    Customer save(Customer customer, String zipCode);
}
