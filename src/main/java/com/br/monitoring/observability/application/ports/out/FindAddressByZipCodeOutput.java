package com.br.monitoring.observability.application.ports.out;

import com.br.monitoring.observability.application.core.model.Address;

public interface FindAddressByZipCodeOutput {
    Address findByZipCode(String zipCode);
}
