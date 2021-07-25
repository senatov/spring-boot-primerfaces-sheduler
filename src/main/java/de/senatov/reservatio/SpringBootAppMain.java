package de.senatov.reservatio;


import com.sun.faces.config.ConfigureListener;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.ServletContextAware;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


@SpringBootApplication
@Slf4j
@ToString
public class SpringBootAppMain implements ServletContextAware {


    public static void main(String... args) {
        SpringApplication.run(SpringBootAppMain.class, args);
    }


    @Bean
    public static CustomScopeConfigurer viewScope() {

        return new CustomScopeConfigurer();
    }


    @Bean
    public ServletRegistrationBean<FacesServlet> servletRegistraiton() {

        ServletRegistrationBean bean = new ServletRegistrationBean(new FacesServlet(), "*.xhtml");
        bean.setLoadOnStartup(1);
        return bean;
    }


    @Override
    public void setServletContext(ServletContext sc) {
        sc.addListener(ConfigureListener.class);
        sc.setInitParameter("com.sun.faces.compressJavaScript", FALSE.toString());
        sc.setInitParameter("com.sun.faces.enableClientStateDebugging", TRUE.toString());
        sc.setInitParameter("com.sun.faces.expressionFactory", "org.apache.el.ExpressionFactoryImpl");
        sc.setInitParameter("com.sun.faces.forceLoadConfiguration", TRUE.toString());
        sc.setInitParameter("com.sun.faces.sendPoweredByHeader", TRUE.toString());
        sc.setInitParameter("facelets.DEVELOPMENT", TRUE.toString());
        sc.setInitParameter("Javax.faces.CONFIG_FILES", "/WEB-INF/faces-config.xml");
        sc.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "1");
        sc.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", FALSE.toString());
        sc.setInitParameter("javax.faces.PARTIAL_STATE_SAVING_METHOD", TRUE.toString());
        sc.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
        sc.setInitParameter("javax.faces.STATE_SAVING_METHOD", "server");
        sc.setInitParameter("primefaces.THEME", "saga");

    }

}
