package com.br.monitoring.observability.application.ports.out;

import com.br.monitoring.observability.application.core.model.Customer;

import java.util.Optional;

public interface FindByIdOutputPort {
    Optional<Customer> findById(Long id);
}
