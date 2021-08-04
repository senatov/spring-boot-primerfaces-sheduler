package de.senatov.reservatio.config;



import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.LF;



@RestController
@RequestMapping("/version")
public class VersionRestController {

	@GetMapping(value ="/", produces = MediaType.APPLICATION_JSON_VALUE)
	public String versionInformation() throws Exception {

		return readGitProperties();
	}

	private String readGitProperties() throws Exception {

		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("git.properties");
		return readFromInputStream(inputStream);
	}


	private String readFromInputStream(InputStream inputStream) throws IOException {

		String resultStringBuilder;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			resultStringBuilder = br.lines().map(line -> line + LF).collect(Collectors.joining());
		}
		resultStringBuilder.replace("\\:", ":");
		return ResponseEntity.ok(resultStringBuilder).toString();
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Error>  handleCustomException(Exception ce) {
		Error error = new Error("shit_happens", ce);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);

	}
}

