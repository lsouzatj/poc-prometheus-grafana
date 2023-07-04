package com.br.monitoring.observability.configs;

import com.br.monitoring.observability.adapters.out.DeleteByIdCustomerOutputAdapter;
import com.br.monitoring.observability.application.core.usecase.DeleteByIdCustomerUserCase;
import com.br.monitoring.observability.application.core.usecase.FindByIdCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeleteByIdCustomerConfig {
    @Bean
    public DeleteByIdCustomerUserCase deleteByIdCustomerUserCase(DeleteByIdCustomerOutputAdapter deleteByIdCustomerOutputAdapter,
                                                                 FindByIdCustomerUseCase findByIdCustomerUseCase){
        return new DeleteByIdCustomerUserCase(deleteByIdCustomerOutputAdapter, findByIdCustomerUseCase);
    }
}
