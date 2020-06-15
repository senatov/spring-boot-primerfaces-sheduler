package de.senatov.reservatio.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;



@Configuration
@Slf4j
public class CustomWebMvcConfigurationSupport extends WebMvcConfigurationSupport {

    @Override
    protected PathMatchConfigurer getPathMatchConfigurer() {
        PathMatchConfigurer pathMatchConfigurer = super.getPathMatchConfigurer();
        log.debug(pathMatchConfigurer.toString());
        return pathMatchConfigurer;
    }
}
