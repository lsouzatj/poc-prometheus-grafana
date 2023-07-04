package com.br.monitoring.observability.adapters.out;

import com.br.monitoring.observability.adapters.out.clients.FindAddressByZipCodeClient;
import com.br.monitoring.observability.application.core.model.Address;
import com.br.monitoring.observability.application.ports.out.FindAddressByZipCodeOutput;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class FindAddressByZipCodeOutputAdapter implements FindAddressByZipCodeOutput {

    private final FindAddressByZipCodeClient findAddressByZipCodeClient;

    @Override
    public Address findByZipCode(String zipCode) {
        try {
            log.info("Calling correios api");
            var responseAddressDTO = findAddressByZipCodeClient.findByZipCodeCorreios(zipCode);
            Address address = new Address();
            BeanUtils.copyProperties(responseAddressDTO, address);
            log.info("Address found");
            return address;
        } catch (FeignException e) {
            log.error("Error calling correios api");
            if (e.status() == HttpStatus.BAD_REQUEST.value()){
                return null;
            }
        }
        log.info("Address not found");
        return null;
    }
}
