package com.br.monitoring.observability.configs;

import com.br.monitoring.observability.adapters.out.FindAddressByZipCodeOutputAdapter;
import com.br.monitoring.observability.adapters.out.FindByIdCustomerOutputAdapater;
import com.br.monitoring.observability.application.core.usecase.FindByIdCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindByIdCustomerConfig {
    @Bean
    public FindByIdCustomerUseCase findByIdCustomerUseCase(FindByIdCustomerOutputAdapater findByIdCustomerOutputAdapater,
                                                           FindAddressByZipCodeOutputAdapter findAddressByZipCodeOutputAdapter){
        return new FindByIdCustomerUseCase(findByIdCustomerOutputAdapater, findAddressByZipCodeOutputAdapter);
    }
}
