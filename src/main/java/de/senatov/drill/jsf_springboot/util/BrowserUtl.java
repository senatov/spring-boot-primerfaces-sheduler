package de.senatov.drill.jsf_springboot.util;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.core.env.MutablePropertySources;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Optional;

/**
 * run default browser
 */
public class BrowserUtl extends Application {

    @Override
    public void start(Stage primaryStage) {

    }

    /**
     * Start default browser
     *
     * @param props
     * @throws IOException
     */
    public void start(MutablePropertySources props) throws IOException {
        String port = Optional.of(props) // @formatter:off
                                    .map(o->o.get("server.ports"))
                                    .map(o->o.getProperty("local.server.port").toString())
                                    .get();

        getHostServices().showDocument(String.format("http://%s:%s/%s",
                        InetAddress.getLocalHost().getHostAddress(),
                        port,
                        "senatov/index.xhtml"));// @formatter:on

    }
}


