package com.codenotfound.primefaces;

import com.codenotfound.primefaces.util.BrowserUtl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootApplication
public class SpringPrimeFacesApplication {

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
        System.out.println("Application started ... launching browser now");
        try {
            new BrowserUtl().start("/");
        }
        catch (IOException | URISyntaxException e) {
            e.printStackTrace();

        }
    }
}

