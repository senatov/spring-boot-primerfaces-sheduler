package de.senatov.reservationz.config;



import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;



@Configuration
@ComponentScan(basePackages = "de.senatov.reservationz")
@Import({WebMvcConfig.class, WebFlowConfig.class})
public class AppConfig {

}
