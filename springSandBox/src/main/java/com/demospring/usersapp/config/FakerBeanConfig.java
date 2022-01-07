package com.demospring.usersapp.config;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FakerBeanConfig {
    /**
     * Para registrar un Bean en el contexto de Spring se debe anotar con un StereoType.
     * Como Java Faker es una clase externa, no puedo anotarla con un StereoType,
     * entonces se lo define de manera explicita.
     */
    @Bean
    public Faker getFaker(){
        return new Faker();
    }
}
