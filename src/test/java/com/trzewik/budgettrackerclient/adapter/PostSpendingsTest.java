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
class PostSpendingsTest {

    private WireMockServer wireMockServer;

    @Inject
    private PostSpendings postSpending;

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

        wireMockServer.stubFor(post(urlEqualTo(uri))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withStatus(200)));
    }

    @Test
    void when_post_spending_then_return_status_ok() {
        assertThat(postSpending.doRequest().getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}