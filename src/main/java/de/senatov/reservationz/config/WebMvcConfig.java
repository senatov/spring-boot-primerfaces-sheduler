package de.senatov.reservationz.config;



import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.faces.mvc.JsfView;
import org.springframework.faces.webflow.JsfFlowHandlerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter;
import org.springframework.web.servlet.mvc.UrlFilenameViewController;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;



/**
 * @author Iakov Senatov
 * @since 03.2020
 */
@ToString
@Slf4j
@Configuration
@EnableWebMvc
public class WebMvcConfig {

    @Autowired
    private Logger LOG;
    @Autowired
    private WebFlowConfig webFlowConfig;



    @Bean
    public FlowHandlerMapping flowHandlerMapping() {

        LOG.debug("flowHandlerMapping()");
        FlowHandlerMapping mapping = new FlowHandlerMapping();
        mapping.setOrder(1);
        mapping.setFlowRegistry(webFlowConfig.flowRegistry());
        mapping.setDefaultHandler(new UrlFilenameViewController());
        return mapping;
    }



    @Bean
    public FlowHandlerAdapter flowHandlerAdapter() {

        LOG.debug("flowHandlerAdapter()");
        JsfFlowHandlerAdapter adapter = new JsfFlowHandlerAdapter();
        adapter.setFlowExecutor(webFlowConfig.flowExecutor());
        return adapter;
    }



    @Bean
    public UrlBasedViewResolver faceletsViewResolver() {

        LOG.debug("UrlBasedViewResolver()");
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

}