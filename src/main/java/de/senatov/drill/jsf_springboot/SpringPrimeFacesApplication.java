package de.senatov.drill.jsf_springboot;

import de.senatov.drill.jsf_springboot.annotations.Loggable;
import de.senatov.drill.jsf_springboot.util.BrowserUtl;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.MutablePropertySources;

import java.io.IOException;

@SpringBootApplication
public class SpringPrimeFacesApplication {

    @Loggable
    private Logger log;

    /**
     * start point of App.
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringPrimeFacesApplication.class, args);
    }

    /**
     * start point of browser.
     */
    @EventListener
    public void onApplicationReadyEvent(ApplicationReadyEvent event) {
        log.info("Application started ... launching browser now");
        try {
            MutablePropertySources propertySources = event.getApplicationContext().getEnvironment().getPropertySources();
            new BrowserUtl().start(propertySources);
        }
        catch (IOException e) {
            log.error("onApplicationReadyEvent", e);
        }
    }
}
