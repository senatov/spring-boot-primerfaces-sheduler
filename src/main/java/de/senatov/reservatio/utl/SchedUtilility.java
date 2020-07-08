package de.senatov.reservatio.utl;



import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;



@Component
@Slf4j
@ToString
public class SchedUtilility implements InitializingBean {

	public static final String DATASOURCE_PASSWORD = "spring.datasource.password";
	private static final String APP_PROPS_KEY = "applicationConfig: [classpath:/application.properties]";
	public static final String DB_PASSWORD_PROPERTIES = "c:/Development/db-password.properties";
	@Autowired
	ConfigurableEnvironment env;

	@Override
	public void afterPropertiesSet()
	{
		// get DB-Password from external (local) place
		setDBPasswordFromLocalPlace();
	}

	private static String readDBPasswordFromHomePC() {

		String result;
		Properties props = new Properties();
		try {
			File file;
			FileReader fileReader = new FileReader(new File(DB_PASSWORD_PROPERTIES));
			props.load(fileReader);
			result = props.getProperty(DATASOURCE_PASSWORD);
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
	public void setDBPasswordFromLocalPlace() {

		Properties properties = new Properties();
		Map<String, Object> unmodifiableMap = getMapSource();
		for (Map.Entry<String, Object> entry : unmodifiableMap.entrySet()) {
			String key = entry.getKey();
			String strValue = entry.getValue()
			                       .toString();
			if (isStrBool(strValue)) {
				// it is important - just "true" / "false" as simple strings, don't works anymore here.
				properties.put(key, Boolean.valueOf(strValue));
			}
			else {
				properties.put(key, strValue);
			}
		}
		properties.replace(DATASOURCE_PASSWORD, readDBPasswordFromHomePC());
		PropertiesPropertySource propertySource = new PropertiesPropertySource(APP_PROPS_KEY, properties);
		env.getPropertySources()
		   .replace(APP_PROPS_KEY, propertySource);
	}



	/**
	 * is String value 'true' or 'false' and could be convert to Boolean
	 * @param strValue
	 * @return
	 */
	private boolean isStrBool(String strValue) {

		return "true".equals(strValue.toLowerCase()) || "false".equals(strValue.toLowerCase());
	}



	private Map<String, Object> getMapSource() {

		Map<String, Object> ret = new HashMap<>();
		try {
			ret = (Map<String, Object>) env.getPropertySources()
			                               .get(APP_PROPS_KEY)
			                               .getSource();
		}
		catch (NullPointerException npe) {
			log.error(npe.getMessage());
		}
		return ret;
	}

}
