package de.senatov.reservationz.config;



import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.faces.config.AbstractFacesFlowConfiguration;
import org.springframework.faces.webflow.FlowFacesContextLifecycleListener;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.security.SecurityFlowExecutionListener;



/**
 * @author Iakov Senatov
 * @since 03.2020
 */
@Configuration
public class WebFlowConfig extends AbstractFacesFlowConfiguration {

    @Autowired
    private Logger LOG;



    @Bean
    public FlowExecutor flowExecutor() {

        LOG.debug("flowExecutor() ");
        return getFlowExecutorBuilder(flowRegistry())
                .addFlowExecutionListener(new FlowFacesContextLifecycleListener())
                .addFlowExecutionListener(new SecurityFlowExecutionListener())
                .build();
    }



    @Bean
    public FlowDefinitionRegistry flowRegistry() {

        LOG.debug("flowRegistry() ");
        return getFlowDefinitionRegistryBuilder(flowBuilderServices())
                .setBasePath("/WEB-INF/flows")
                .addFlowLocationPattern("/**/*-flow.xml")
                .build();
    }



    @Bean
    public FlowBuilderServices flowBuilderServices() {

        LOG.debug("flowBuilderServices() ");
        return getFlowBuilderServicesBuilder()
                .setDevelopmentMode(true)
                .build();
    }

}
