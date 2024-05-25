package br.com.alura.adopetstore.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class AsyncConfiguration {

    @Bean
    public Executor asyncExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);//O minimo de threads criadas
        executor.setMaxPoolSize(3);//O máximo de threads criadas
        executor.setQueueCapacity(100);//Quantidade de threads que podem fica na fila
        executor.setThreadNamePrefix("AsynchThread-");//Nomeando threads
        executor.initialize();
        return executor;
    }
}
