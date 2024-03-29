package de.senatov.reservatio.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.faces.mvc.JsfView;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.UrlBasedViewResolver;


@Configuration
@EnableWebMvc
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public UrlBasedViewResolver faceletsViewResolver() {

        log.debug("faceletsViewResolver()");
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setViewClass(JsfView.class);
        resolver.setPrefix("/WEB-INF/ui/");
        resolver.setSuffix(".xhtml");
        return resolver;
    }


    @Bean
    public DispatcherServlet dispatcherServlet() {

        log.debug("dispatcherServlet()");
        return new DispatcherServlet();
    }

}



