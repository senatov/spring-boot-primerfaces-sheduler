package de.senatov.reservatio.test;



import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;



@Slf4j
@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

	@Autowired
	private WebTestClient webClient;



	@Test
	public void testReservation() {

		log.debug("Begin Test");
		log.debug("Part I");
		webClient.get()
		         .uri("/ui/reservation.xhtml")
		         .exchange()
		         .expectStatus()
		         .isOk();
	}



	@Test
	public void testCreate() {

		log.debug("Part II");
		webClient.get()
		         .uri("/ui/create.xhtml")
		         .exchange()
		         .expectStatus()
		         .isOk();
	}



	@Test
	public void testList() {

		log.debug("Part III");
		webClient.get()
		         .uri("/ui/list.xhtml")
		         .exchange()
		         .expectStatus()
		         .isOk();
	}

}
