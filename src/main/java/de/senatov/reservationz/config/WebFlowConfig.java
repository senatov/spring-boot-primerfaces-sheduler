package de.senatov.reservationz.config;



import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.faces.config.AbstractFacesFlowConfiguration;
import org.springframework.faces.webflow.FlowFacesContextLifecycleListener;
import org.springframework.faces.webflow.JsfFlowHandlerAdapter;
import org.springframework.web.servlet.mvc.UrlFilenameViewController;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;
import org.springframework.webflow.security.SecurityFlowExecutionListener;


/**
 * @author Iakov Senatov
 * @since 03.2020
 */
@Configuration
public class WebFlowConfig extends AbstractFacesFlowConfiguration {


    private final Logger LOG;



    @Autowired
    public WebFlowConfig(Logger logger) {

        LOG = logger;
    }



    @Bean
    public FlowExecutor flowExecutor() {

        this.LOG.debug("flowExecutor()");
        return this.getFlowExecutorBuilder(this.flowRegistry())
                .addFlowExecutionListener(new FlowFacesContextLifecycleListener())
                .addFlowExecutionListener(new SecurityFlowExecutionListener())
                .build();
    }



    @Bean
    public FlowDefinitionRegistry flowRegistry() {

        this.LOG.debug("flowRegistry()");
        return this.getFlowDefinitionRegistryBuilder(this.flowBuilderServices())
                .setBasePath("/WEB-INF/flows")
                .addFlowLocationPattern("/**/*-flow.xml")
                .build();
    }



    @Bean
    public FlowBuilderServices flowBuilderServices() {

        this.LOG.debug("flowBuilderServices()");
        return this.getFlowBuilderServicesBuilder().setDevelopmentMode(true).build();
    }



    @Bean
    public FlowHandlerMapping flowHandlerMapping() {

        this.LOG.debug("flowHandlerMapping()");
        FlowHandlerMapping mapping = new FlowHandlerMapping();
        mapping.setOrder(1);
        mapping.setFlowRegistry(this.flowRegistry());
        mapping.setDefaultHandler(new UrlFilenameViewController());
        return mapping;
    }



    @Bean
    public FlowHandlerAdapter flowHandlerAdapter() {

        this.LOG.debug("flowHandlerAdapter()");
        JsfFlowHandlerAdapter adapter = new JsfFlowHandlerAdapter();
        adapter.setFlowExecutor(this.flowExecutor());
        return adapter;
    }

}
