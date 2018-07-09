package de.senatov.drill.jsf_springboot.util;



import de.senatov.drill.jsf_springboot.annotations.Loggable;
import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.springframework.core.env.MutablePropertySources;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

import static java.lang.String.format;



/**
 * run default browser
 */
public class BrowserUtl extends Application {

    public static final String CTX_PATH = "senatov/index.xhtml";
    @Loggable
    private Logger log;



    @Override
    public void start(Stage primaryStage) {

        log.info("nothing todo");
    }



    /**
     * Start default browser
     *
     * @param props
     * @throws UnknownHostException
     */
    public void start(MutablePropertySources props) throws UnknownHostException {

        Optional<String> oPort = Optional.of(props) // @formatter:off
                                               .map(o->o.get("server.ports"))
                                               .map(o->o.getProperty("local.server.port").toString());// @formatter:on

        if (oPort.isPresent()) {
            getHostServices().showDocument(format("http://%s:%s/%s", InetAddress.getLocalHost().getHostAddress(), oPort.get(),
                            CTX_PATH));
        }

    }
}


