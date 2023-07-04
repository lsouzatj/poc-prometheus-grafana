package com.br.monitoring.observability.configs;

import com.br.monitoring.observability.adapters.out.FindAddressByZipCodeOutputAdapter;
import com.br.monitoring.observability.adapters.out.FindAllCustomerOutputAdapter;
import com.br.monitoring.observability.application.core.usecase.FindAllCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindAllCustomerConfig {
    @Bean
    public FindAllCustomerUseCase findAllCustomerUseCase(FindAllCustomerOutputAdapter findAllCustomerOutputAdapter,
                                                         FindAddressByZipCodeOutputAdapter findAddressByZipCodeOutputAdapter){
        return new FindAllCustomerUseCase(findAllCustomerOutputAdapter, findAddressByZipCodeOutputAdapter);
    }
}
