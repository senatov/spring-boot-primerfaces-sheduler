package de.senatov.drill.jsf_springboot.util;

import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
import com.sun.javafx.application.HostServicesDelegate;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * run browser
 */
public class BrowserUtl extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public void start(String url) throws IOException, URISyntaxException {
        HostServicesDelegate hostServices = HostServicesFactory.getInstance(this);
        getHostServices().showDocument("http://www.yahoo.com");
    }
}


