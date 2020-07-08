package de.senatov.reservatio;



import com.sun.faces.config.ConfigureListener;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.web.context.ServletContextAware;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.apache.commons.lang3.StringUtils.containsAny;



@SpringBootApplication
@Slf4j
@ToString
public class SpringBootAppMain implements ServletContextAware {

	public static final String APP_PROPS_KEY = "applicationConfig: [classpath:/application.properties]";
	@Autowired
	ConfigurableEnvironment env;



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

		setDBPasswordFromLocalPlace();
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
		sc.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", TRUE.toString());
		sc.setInitParameter("primefaces.FONT_AWESOME", TRUE.toString());
		sc.setInitParameter("primefaces.THEME", "redmond");

	}



	private static String readDBPasswordFromHomePC() {

		String result;
		Properties props = new Properties();
		try {
			File file;
			FileReader fileReader = new FileReader(new File("c:/Development/db-password.properties"));
			props.load(fileReader);
			result = props.getProperty("spring.datasource.password");
		}
		catch (IOException e) {
			log.error("cannot read db password: {}", e.getMessage());
			result = "cannot read path";
		}
		return result;
	}



	/**
	 * add/replace already saved in environment secret DB-Password to real one from project-external source.
	 */
	private void setDBPasswordFromLocalPlace() {

		Properties properties = new Properties();
		Map<String, Object> unmodifiableMap = (Map<String, Object>) env.getPropertySources()
		                                                               .get(APP_PROPS_KEY)
		                                                               .getSource();
		for (String key : unmodifiableMap.keySet()) {
			String strValue = unmodifiableMap.get(key)
			                                 .toString();
			if (containsAny(strValue, "true", "false", "TRUE", "FALSE")) {
				// it is important - just "true" / "false" as simple strings, don't works anymore here.
				properties.put(key, Boolean.valueOf(strValue));
			}
			properties.put(key, strValue);
		}
		properties.replace("spring.datasource.password", readDBPasswordFromHomePC());
		PropertiesPropertySource propertySource = new PropertiesPropertySource(APP_PROPS_KEY, properties);
		env.getPropertySources()
		   .replace(APP_PROPS_KEY, propertySource);
	}

}
