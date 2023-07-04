package com.br.monitoring.observability.application.core.usecase;

import com.br.monitoring.observability.application.core.model.Customer;
import com.br.monitoring.observability.application.ports.in.FindByIdCustomerInputPort;
import com.br.monitoring.observability.application.ports.out.FindAddressByZipCodeOutput;
import com.br.monitoring.observability.application.ports.out.FindByIdOutputPort;

import java.util.Optional;

public class FindByIdCustomerUseCase implements FindByIdCustomerInputPort {
    private final FindByIdOutputPort findByIdOutputPort;
    private final FindAddressByZipCodeOutput findAddressByZipCodeOutput;

    public FindByIdCustomerUseCase(FindByIdOutputPort findByIdOutputPort, FindAddressByZipCodeOutput findAddressByZipCodeOutput) {
        this.findByIdOutputPort = findByIdOutputPort;
        this.findAddressByZipCodeOutput = findAddressByZipCodeOutput;
    }

    @Override
    public Optional<Customer> findById(Long id) {
         var customerOptional = findByIdOutputPort.findById(id);
        customerOptional.map(customer -> {
            var addressResponse = findAddressByZipCodeOutput.findByZipCode(customer.getCep());
            customer.setAddress(addressResponse);
            return customer;
        });
         return customerOptional;
    }
}
