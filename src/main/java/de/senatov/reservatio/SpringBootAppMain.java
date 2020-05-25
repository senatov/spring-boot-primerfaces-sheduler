package de.senatov.reservatio;



import com.sun.faces.config.ConfigureListener;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.ServletContextAware;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;



@ToString
@Slf4j
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class SpringBootAppMain implements ServletContextAware {

	public static final String FORMAT1 = "  %s)  [ %s ]  ";
	@Autowired
	private ApplicationContext appContext;



	public static void main(String... args) {

		SpringApplication.run(SpringBootAppMain.class, args);
	}



	@Bean
	public ServletRegistrationBean<FacesServlet> facesServletRegistraiton() {

		ServletRegistrationBean servletRegistration = new ServletRegistrationBean(new FacesServlet(), "*.xhtml");
		servletRegistration.setLoadOnStartup(1);
		return servletRegistration;
	}



	@Override
	public void setServletContext(ServletContext sc) {

		sc.addListener(ConfigureListener.class);
		sc.setInitParameter("com.sun.faces.expressionFactory", "org.apache.el.ExpressionFactoryImpl");
		sc.setInitParameter("com.sun.faces.forceLoadConfiguration", TRUE.toString());
		sc.setInitParameter("facelets.DEVELOPMENT", TRUE.toString());
		sc.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "1");
		sc.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", FALSE.toString());
		sc.setInitParameter("javax.faces.PARTIAL_STATE_SAVING_METHOD", TRUE.toString());
		sc.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
		sc.setInitParameter("javax.faces.STATE_SAVING_METHOD", "server");
		sc.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", TRUE.toString());
		sc.setInitParameter("primefaces.FONT_AWESOME", TRUE.toString());
		sc.setInitParameter("primefaces.THEME", "bootstrap");
		sc.setInitParameter("com.sun.faces.compressJavaScript", FALSE.toString());
		sc.setInitParameter("Javax.faces.CONFIG_FILES", "/WEB-INF/faces-config.xml");
	}



	@Bean
	public ServletRegistrationBean<FacesServlet> servletRegistrationBean() {

		ServletRegistrationBean<FacesServlet> bean = new ServletRegistrationBean<>(new FacesServlet(), "*.xhtml");
		bean.setLoadOnStartup(1);
		return bean;
	}

}
