package com.lbs.data.demo.topic.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;


@Configuration
public class AnnotatedClasses {
    private HashMap<String, String> annotatedClasses = new HashMap<String, String>();

    @Bean("myString")
    public HashMap<String, String> getAnnotatedClasses() {
        return annotatedClasses;
    }

    public void setAnnotatedClasses(HashMap<String, String> annotatedClasses) {
        this.annotatedClasses = annotatedClasses;
    }
}
