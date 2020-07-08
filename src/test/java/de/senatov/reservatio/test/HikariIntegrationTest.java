package de.senatov.reservatio.test;



import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;



@Slf4j
@ToString
@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient
@SpringBootTest(properties = "spring.datasource.type=com.zaxxer.hikari.HikariDataSource")
public class HikariIntegrationTest {

	@Autowired
	private DataSource dataSource;



	@Test
	public void hikariConnectionPoolIsConfigured() {

		assertEquals("com.zaxxer.hikari.HikariDataSource", dataSource.getClass()
		                                                             .getName());
	}

}