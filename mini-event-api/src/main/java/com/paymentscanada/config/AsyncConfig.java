package com.paymentscanada.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Toying with spring boot/Java 8 async capabilities
 */
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    public static final String ASYNC_EXECUTOR_SERVICE_LAYER = "serviceAsyncExecutor";
    public static final String ASYNC_EXECUTOR_REPOSITORY_LAYER = "repositoryAsyncExecutor";

    private static final String ASYNC_EXECUTOR_PREFIX_SERVICE = "serviceExecutor: - ";


    @Bean(name = ASYNC_EXECUTOR_REPOSITORY_LAYER)
    public ExecutorService getRepositoryAsyncExecutor() {
        //cpu bound, assuming quad core
        return Executors.newFixedThreadPool(4);
    }

    @Bean(name = ASYNC_EXECUTOR_SERVICE_LAYER)
    public Executor getServiceAsyncExecutor() {
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //todo these could go into application properties, not bothering with this for now
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix(ASYNC_EXECUTOR_PREFIX_SERVICE);
        return executor;
    }
}
