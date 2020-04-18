package de.senatov.reservationz.config;



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

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .authorizeRequests()
                .antMatchers("/ui/*", "/ui/resources/**", "/ui/login/**", "/", "/ui/login/javax.faces.resource/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/login.xhtml?error")
                .and()
                .sessionManagement()
                .invalidSessionUrl("/login.xhtml")
                .sessionFixation()
                .newSession()
                .and()
                .logout()
                .logoutUrl("/j_spring_security_logout")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/logoutSuccess.xhtml")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/reservation")
                .failureUrl("/error")
                .and()
                .logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logoutSuccess");
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}aaa")
                .roles("ADMIN")
                .and()
                .withUser("user")
                .password("{noop}aaa")
                .roles("USER");
    }

}