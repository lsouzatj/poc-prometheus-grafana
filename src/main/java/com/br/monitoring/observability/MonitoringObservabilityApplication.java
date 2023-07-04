package com.br.monitoring.observability;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MonitoringObservabilityApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitoringObservabilityApplication.class, args);
	}

}
