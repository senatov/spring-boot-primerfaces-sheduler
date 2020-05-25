package de.senatov.reservatio.config;



import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
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
@Configuration
@EnableWebMvc
@Slf4j
public class WebMvcConfig {

    @Autowired
    private WebFlowConfig webFlowConfig;



    @Bean
    public FlowHandlerMapping flowHandlerMapping() {

        log.debug("flowHandlerMapping()");
        FlowHandlerMapping mapping = new FlowHandlerMapping();
        mapping.setOrder(1);
        mapping.setFlowRegistry(webFlowConfig.flowRegistry());
        mapping.setDefaultHandler(new UrlFilenameViewController());
        return mapping;
    }



    @Bean
    public FlowHandlerAdapter flowHandlerAdapter() {

        log.debug("flowHandlerAdapter()");
        JsfFlowHandlerAdapter adapter = new JsfFlowHandlerAdapter();
        adapter.setFlowExecutor(webFlowConfig.flowExecutor());
        return adapter;
    }



    @Bean
    public UrlBasedViewResolver faceletsViewResolver() {

        log.debug("UrlBasedViewResolver()");
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

}