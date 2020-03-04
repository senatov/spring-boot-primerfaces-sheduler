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



@Configuration
public class WebFlowConfig extends AbstractFacesFlowConfiguration {

    private Logger LOG;



    @Autowired
    public WebFlowConfig(Logger log) {

        this.LOG = log;
    }



    @Bean
    public FlowExecutor flowExecutor() {

        LOG.debug("flowExecutor()");
        return getFlowExecutorBuilder(flowRegistry()).addFlowExecutionListener(new FlowFacesContextLifecycleListener())
                .addFlowExecutionListener(new SecurityFlowExecutionListener())
                .build();
    }



    @Bean
    public FlowDefinitionRegistry flowRegistry() {

        LOG.debug("flowRegistry()");
        return getFlowDefinitionRegistryBuilder(flowBuilderServices()).setBasePath("/WEB-INF/flows")
                .addFlowLocationPattern("/**/*-flow.xml")
                .build();
    }



    @Bean
    public FlowBuilderServices flowBuilderServices() {

        LOG.debug("flowBuilderServices()");
        return getFlowBuilderServicesBuilder().setDevelopmentMode(true)
                .build();
    }



    @Bean
    public FlowHandlerMapping flowHandlerMapping() {

        LOG.debug("flowHandlerMapping()");
        FlowHandlerMapping mapping = new FlowHandlerMapping();
        mapping.setOrder(1);
        mapping.setFlowRegistry(flowRegistry());
        mapping.setDefaultHandler(new UrlFilenameViewController());
        return mapping;
    }



    @Bean
    public FlowHandlerAdapter flowHandlerAdapter() {

        LOG.debug("flowHandlerAdapter()");
        JsfFlowHandlerAdapter adapter = new JsfFlowHandlerAdapter();
        adapter.setFlowExecutor(flowExecutor());
        return adapter;
    }

}
