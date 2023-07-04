package com.br.monitoring.observability.application.core.usecase;

import com.br.monitoring.observability.application.core.model.Customer;
import com.br.monitoring.observability.application.ports.in.DeleteByIdCustomerInputPort;
import com.br.monitoring.observability.application.ports.in.FindByIdCustomerInputPort;
import com.br.monitoring.observability.application.ports.out.DeleteByIdCustomOutputPort;

import java.util.Optional;

public class DeleteByIdCustomerUserCase implements DeleteByIdCustomerInputPort {
    private final DeleteByIdCustomOutputPort deleteByIdCustomOutputPort;
    private final FindByIdCustomerInputPort findByIdCustomerInputPort;

    public DeleteByIdCustomerUserCase(DeleteByIdCustomOutputPort deleteByIdCustomOutputPort, FindByIdCustomerInputPort findByIdCustomerInputPort) {
        this.deleteByIdCustomOutputPort = deleteByIdCustomOutputPort;
        this.findByIdCustomerInputPort = findByIdCustomerInputPort;
    }

    @Override
    public Optional<Customer> delete(Long id) {
        return findByIdCustomerInputPort.findById(id).map(customer ->{
            deleteByIdCustomOutputPort.delete(customer);
            return Optional.of(customer);
        }).orElse(Optional.empty());

    }
}
