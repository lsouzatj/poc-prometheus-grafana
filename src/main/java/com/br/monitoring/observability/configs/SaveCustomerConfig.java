package com.br.monitoring.observability.configs;

import com.br.monitoring.observability.adapters.out.FindAddressByZipCodeOutputAdapter;
import com.br.monitoring.observability.adapters.out.InsertCustomerOutputAdapter;
import com.br.monitoring.observability.application.core.usecase.InsertCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaveCustomerConfig {
    @Bean
    InsertCustomerUseCase saveCustomerUseCase(InsertCustomerOutputAdapter saveCustomerOutputAdapter,
                                              FindAddressByZipCodeOutputAdapter FindAddressByZipCodeOutputAdapter){
        return new InsertCustomerUseCase(saveCustomerOutputAdapter, FindAddressByZipCodeOutputAdapter);
    }
}
