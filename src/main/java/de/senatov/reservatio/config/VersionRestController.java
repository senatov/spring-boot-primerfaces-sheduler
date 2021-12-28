package de.senatov.reservatio.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.Properties;


@RestController
@RequestMapping("/version")
public class VersionRestController {

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String versionInformation() throws Exception {

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(getProperties());
    }


    private static Properties getProperties() throws Exception {

        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("git.properties");
        prop.load(stream);
        return prop;
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleCustomException(Exception ce) {

        Error error = new Error("shit_happens", ce);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}

