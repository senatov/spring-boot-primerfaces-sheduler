package de.senatov.reservationz.config;



import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.faces.mvc.JsfView;
import org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter;
import org.springframework.web.servlet.view.UrlBasedViewResolver;



@Configuration
public class WebMvcConfig {

    @Autowired
    private Logger LOG;



    @Bean
    public UrlBasedViewResolver faceletsViewResolver() {

        LOG.debug("faceletsViewResolver()");
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setViewClass(JsfView.class);
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".xhtml");
        return resolver;
    }



    @Bean
    public SimpleControllerHandlerAdapter simpleControllerHandlerAdapter() {

        LOG.debug("simpleControllerHandlerAdapter()");
        return new SimpleControllerHandlerAdapter();
    }


    /*
    @Bean
    public ServletRegistrationBean dispatcherServletRegistration() {

        LOG.debug("dispatcherServletRegistration()");
        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet(), "/ui/*");
        registration.setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME);
        return registration;
    }*/

}
