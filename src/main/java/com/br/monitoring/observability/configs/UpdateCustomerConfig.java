package com.br.monitoring.observability.configs;

import com.br.monitoring.observability.adapters.out.SendKafkaCustomerOutputAdapter;
import com.br.monitoring.observability.adapters.out.UpdateCustomerOutputAdapter;
import com.br.monitoring.observability.application.core.usecase.FindByIdCustomerUseCase;
import com.br.monitoring.observability.application.core.usecase.UpdateCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateCustomerConfig {
    @Bean
    public UpdateCustomerUseCase updateCustomerUseCase(UpdateCustomerOutputAdapter updateCustomerOutputAdapter,
                                                       FindByIdCustomerUseCase findByIdCustomerUseCase,
                                                       SendKafkaCustomerOutputAdapter sendKafkaCustomerOutputAdapter) {
        return new UpdateCustomerUseCase(updateCustomerOutputAdapter, findByIdCustomerUseCase, sendKafkaCustomerOutputAdapter);
    }
}
