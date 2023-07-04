package com.br.monitoring.observability.adapters.out.clients;

import com.br.monitoring.observability.adapters.out.clients.responseDTO.AddressResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "FindAddressByZipCodeClient",
    url = "${clients.address.url}")
public interface FindAddressByZipCodeClient {

    @GetMapping("/{zipCode}/json/")
    AddressResponseDTO findByZipCodeCorreios(@PathVariable("zipCode") String zipCode);
}
