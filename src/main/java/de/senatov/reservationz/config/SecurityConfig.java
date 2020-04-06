package de.senatov.reservationz.config;



import lombok.ToString;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;



@ToString
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .formLogin()
                .loginPage("/ui/login")
                .loginProcessingUrl("/ui/loginProcess")
                .defaultSuccessUrl("/ui/reservation")
                .failureUrl("/ui/login?login_error=true")
                .and()
                .logout()
                .logoutUrl("/ui/logout")
                .logoutSuccessUrl("/ui/logoutSuccess")
                .and()
                .requestCache()
                .requestCache(new HttpSessionRequestCache())
        ;
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}aaa")
                .roles("USER", "SUPERVISOR")
                .and()
                .withUser("user")
                .password("{noop}aaa")
                .roles("USER", "SUPERVISOR");
    }

}