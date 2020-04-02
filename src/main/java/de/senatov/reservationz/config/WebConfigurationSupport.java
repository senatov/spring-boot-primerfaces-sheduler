package de.senatov.reservationz.config;



import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.concurrent.TimeUnit;



/**
 * @author Iakov Senatov
 * @since 03.2020
 */
@Configuration
public class WebConfigurationSupport extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        CacheControl cacheControl = CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic();
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/styles/", "classpath:/images/", "classpath:/conf/", "classpath:/layouts/", "classpath:/ui/")
                .setCacheControl(cacheControl);
    }

}