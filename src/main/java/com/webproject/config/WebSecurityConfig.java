package com.webproject.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;

@SuppressWarnings("deprecation")
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .headers()
                .referrerPolicy(ReferrerPolicyHeaderWriter.ReferrerPolicy.SAME_ORIGIN).and()

                .frameOptions().sameOrigin().and()

                .headers().defaultsDisabled()
                .contentTypeOptions().and()

                .httpStrictTransportSecurity()
                .includeSubDomains(true)
                .maxAgeInSeconds(31536000);
    }
}