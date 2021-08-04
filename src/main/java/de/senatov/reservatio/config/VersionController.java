package de.senatov.reservatio.config;



import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.springframework.web.bind.annotation.RequestMethod.GET;



@RestController
public class VersionController {

	@RequestMapping(value = "/version", method = GET)
	public String versionInformation() throws Exception {

		return readGitProperties();
	}

	private String readGitProperties() throws Exception {

		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("git.properties");
		return readFromInputStream(inputStream);
	}


	private String readFromInputStream(InputStream inputStream) throws IOException {

		StringBuilder resultStringBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = br.readLine()) != null) {
				resultStringBuilder.append(line).append("\n");
			}
		}
		return resultStringBuilder.toString();
	}

	@ExceptionHandler(Exception.class)
	public Exception handleCustomException(Exception ce) {

		return ce;
	}
}
