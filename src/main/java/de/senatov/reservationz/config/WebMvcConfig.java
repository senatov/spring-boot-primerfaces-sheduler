package de.senatov.reservationz.config;



import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.faces.mvc.JsfView;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter;
import org.springframework.web.servlet.view.UrlBasedViewResolver;



@Configuration
public class WebMvcConfig {

    @Autowired
    private Logger log;



    @Bean
    public UrlBasedViewResolver faceletsViewResolver() {

        log.debug("faceletsViewResolver()");
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setViewClass(JsfView.class);
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".xhtml");
        return resolver;
    }



    @Bean
    public SimpleControllerHandlerAdapter simpleControllerHandlerAdapter() {

        log.debug("simpleControllerHandlerAdapter()");
        return new SimpleControllerHandlerAdapter();
    }



    @Bean
    public DispatcherServlet dispatcherServlet() {

        log.debug("dispatcherServlet()");
        return new DispatcherServlet();
    }



    @Bean
    public ServletRegistrationBean dispatcherServletRegistration() {

        log.debug("dispatcherServletRegistration()");
        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet(), "/ui/*");
        registration.setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME);
        return registration;
    }

}
