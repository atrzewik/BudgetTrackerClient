package com.trzewik.budgettrackerclient.adapter;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import javax.inject.Inject;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Agnieszka Trzewik
 */
@SpringBootTest
class GetSpendingsTest {

    private WireMockServer wireMockServer;

    @Inject
    private GetSpendings getSpendings;

    @BeforeEach
    void set_up() {
        wireMockServer = new WireMockServer(8091);
        wireMockServer.start();
        set_up_stub();
    }

    @AfterEach
    void tear_down() {
        wireMockServer.stop();
    }

    void set_up_stub() {
        String uri = "/spendings";

        wireMockServer.stubFor(get(urlEqualTo(uri))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody("[" +
                                "    {\n" +
                                "        \"description\": \"czereśnie\",\n" +
                                "        \"price\": 2.00\n" +
                                "    },\n" +
                                "    {\n" +
                                "        \"description\": \"czereśnie\",\n" +
                                "        \"price\": 2.00\n" +
                                "    }\n" +
                                "]")));
    }

    @Test
    void when_get_spendings_then_return_status_ok() {
        //Given
        //When
        //Then
        assertThat(getSpendings.doRequest().getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}