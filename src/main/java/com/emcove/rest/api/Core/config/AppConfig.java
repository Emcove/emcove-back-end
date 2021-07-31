package com.emcove.rest.api.Core.config;

import com.emcove.rest.api.Core.repository.EntrepreneurshipRepositoryCustom;
import com.emcove.rest.api.Core.repository.EntrepreneurshipRepositoryCustomImpl;
import com.emcove.rest.api.Core.repository.UserRepositoryCustom;
import com.emcove.rest.api.Core.repository.UserRepositoryCustomImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public EntrepreneurshipRepositoryCustom entrepreneurshipRepositoryCustom(){
        return new EntrepreneurshipRepositoryCustomImpl();
    }

    @Bean
    public UserRepositoryCustom userRepositoryCustom(){
        return new UserRepositoryCustomImpl();
    }
}
