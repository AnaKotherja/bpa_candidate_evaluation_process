package io.camunda;

import io.camunda.zeebe.util.health.MemoryHealthIndicatorProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZeebeConfiguration {

    @Bean
    public MemoryHealthIndicatorProperties memoryHealthIndicatorProperties() {
        // Create and return your desired MemoryHealthIndicatorProperties bean
        return new MemoryHealthIndicatorProperties();
    }
}
