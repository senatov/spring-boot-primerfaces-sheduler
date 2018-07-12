package de.senatov.drill.jsf_springboot.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class PagesConfig implements WebMvcConfigurer {


        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/resources/**")
                    .addResourceLocations("/resources/")
                    .setCachePeriod(0);
            registry.addResourceHandler("/error/**")
                    .addResourceLocations("/resources/public/error/")
                    .setCachePeriod(0);
        }


}
