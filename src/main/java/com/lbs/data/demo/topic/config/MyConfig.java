package com.lbs.data.demo.topic.config;

import org.springframework.context.annotation.Bean;

public class MyConfig {
    @Bean
    DemoBeanFactoryPostProcessor myConfigBean () {
        return new DemoBeanFactoryPostProcessor();
    }
}
