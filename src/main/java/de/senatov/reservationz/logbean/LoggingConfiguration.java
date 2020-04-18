package de.senatov.reservationz.logbean;



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
    
    public static final String OCCURED_S_N = "slf4j autowired Exception occured : %s%n";
    
    
    
    @Bean
    @Scope("prototype")
    public Logger LOG(InjectionPoint ip) {

        try {
            return getLogger(of(ip.getMember()).map(Member::getDeclaringClass).orElseThrow(IllegalArgumentException::new));
        }
        catch (Exception e) {
            System.err.printf(OCCURED_S_N, e.getMessage());
            throw e;
        }
    }

}
