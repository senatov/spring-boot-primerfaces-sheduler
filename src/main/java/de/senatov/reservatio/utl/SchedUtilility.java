package de.senatov.reservatio.utl;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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
import java.util.Locale;
import java.util.Map;
import java.util.Properties;


@Component
@Slf4j
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SchedUtilility implements InitializingBean {

    private static final String DATASOURCE_PASSWORD = "spring.datasource.password";
    private static final String APP_PROPS_KEY = "applicationConfig: [classpath:/application.properties]";
    private static final String DB_PASSWORD_PROPERTIES = "c:/Development/jee/db-password.properties";
    @Autowired
    ConfigurableEnvironment env;


    private static String readDBPasswordFromHomePC() {

        String result;
        Properties props = new Properties();
        try {
            FileReader fileReader = new FileReader(new File(DB_PASSWORD_PROPERTIES));
            props.load(fileReader);
            result = props.getProperty(DATASOURCE_PASSWORD);
        } catch (IOException e) {
            //cannot find the pwd from outer file. Try use default. So-o, it's only demo, and not real security solution!
            log.error("cannot read db password: {}. Use Default", e.getMessage());
            // defaul pawd if extern password file not exists
            result = "admin";
        }
        return result;
    }


    /**
     * is String value 'true' or 'false' and could be convert to Boolean
     *
     * @param strValue
     * @return
     */
    private static boolean isStrBool(String strValue) {

        return "true".equals(strValue.toLowerCase(Locale.ENGLISH)) || "false".equals(strValue.toLowerCase(Locale.ENGLISH));
    }


    @Override
    public void afterPropertiesSet() {
        // get DB-Password from external (local) place
        setDBPasswordFromLocalPlace();
    }


    /**
     * add/replace already saved in environment secret DB-Password to real one from project-external source.
     */
    private void setDBPasswordFromLocalPlace() {

        Properties properties = new Properties();
        Map<String, Object> unmodifiableMap = getMapSource();
        for (Map.Entry<String, Object> entry : unmodifiableMap.entrySet()) {
            String key = entry.getKey();
            String strValue = entry.getValue()
                    .toString();
            if (isStrBool(strValue)) {
                // it is important - just "true" / "false" as simple strings, don't works anymore here.
                properties.put(key, Boolean.valueOf(strValue));
            } else {
                properties.put(key, strValue);
            }
        }
        properties.replace(DATASOURCE_PASSWORD, readDBPasswordFromHomePC());
        PropertiesPropertySource propertySource = new PropertiesPropertySource(APP_PROPS_KEY, properties);
        env.getPropertySources()
                .replace(APP_PROPS_KEY, propertySource);
    }


    private Map<String, Object> getMapSource() {

        Map<String, Object> ret = new HashMap<>(8);
        try {
            ret = (Map<String, Object>) env.getPropertySources()
                    .get(APP_PROPS_KEY)
                    .getSource();
        } catch (NullPointerException npe) {
            log.error(npe.getMessage());
        }
        return ret;
    }

}
