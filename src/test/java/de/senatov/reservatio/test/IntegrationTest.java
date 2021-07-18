package de.senatov.reservatio.test;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;


@Slf4j
@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Integration Test")
@Disabled
public class IntegrationTest {

    @Autowired
    private WebTestClient webClient;


    @Test
    @DisplayName("Test Reservation")
    public void testReservation() {

        log.debug("Part 1.");
        webClient.get()
                .uri("/ui/reservation.xhtml")
                .exchange()
                .expectStatus()
                .isOk();
    }


    @Test
    @DisplayName("Test Create")
    public void testCreate() {

        log.debug("Part 2.");
        webClient.get()
                .uri("/ui/create.xhtml")
                .exchange()
                .expectStatus()
                .isOk();
    }


    @Test
    @DisplayName("Test List")
    public void testList() {

        log.debug("Part 3.");
        webClient.get()
                .uri("/ui/list.xhtml")
                .exchange()
                .expectStatus()
                .isOk();
    }

}
