package de.senatov.reservationz.config;



import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;



@ToString
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;



    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }



    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {

        return authenticationManager();
    }



    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage("/ui/login")
                .loginProcessingUrl("/ui/loginProcess")
                .defaultSuccessUrl("/ui/main")
                .failureUrl("/ui/login?login_error=1")
                .and()
                .logout()
                .logoutUrl("/ui/logout")
                .logoutSuccessUrl("/ui/logoutSuccess")
                .and()
                .requestCache()
                .requestCache(new HttpSessionRequestCache());
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("aaa")
                .roles("USER", "SUPERVISOR")
                .and()
                .withUser("user")
                .password("aaa")
                .roles("USER", "SUPERVISOR");
    }

}