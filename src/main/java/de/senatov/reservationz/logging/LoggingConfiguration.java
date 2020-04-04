package de.senatov.reservationz.logging;



import org.slf4j.Logger;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.lang.reflect.Member;

import static java.util.Optional.of;
import static org.slf4j.LoggerFactory.getLogger;



@Configuration
public class LoggingConfiguration {

    @Bean
    @Scope("prototype")
    public Logger LOG(InjectionPoint ip) {

        try {
            return getLogger(of(ip.getMember()).map(Member::getDeclaringClass).orElseThrow(IllegalArgumentException::new));
        } catch (Exception e) {
            System.err.printf("slf4j autowired Exception occured : %s%n", e.getMessage());
            throw e;
        }
    }

}
