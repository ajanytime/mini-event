package com.paymentscanada.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Toying with spring boot/Java 8 async capabilities
 */
@Configuration
@EnableAsync
public class AsyncConfig {

    public static final String ASYNC_EXECUTOR_SERVICE_LAYER = "asyncExecutor";

    @Bean(ASYNC_EXECUTOR_SERVICE_LAYER)
    public Executor asyncExecutor()
    {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(3);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("Async-Service: - ");
        executor.initialize();
        return executor;
    }
}
