package de.senatov.reservatio.config;



import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@Slf4j
@ToString
@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

    /**
     * default security with default spring login
     *
     * @param  http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {

        log.debug("configure(HttpSecurity)");
        http.authorizeRequests().anyRequest().authenticated().and().formLogin().defaultSuccessUrl("/reservation");
    }



    /**
     * Spring Security 5 requires specifying the password storage format!
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        log.debug("configure(AuthenticationManagerBuilder)");
        auth.inMemoryAuthentication().withUser("user").password("{noop}aaa").roles("USER", "ADMIN");
    }

}
