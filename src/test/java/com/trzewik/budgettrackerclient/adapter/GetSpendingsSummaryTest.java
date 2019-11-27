package com.trzewik.budgettrackerclient.adapter;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import ru.lanwen.wiremock.ext.WiremockResolver;
import ru.lanwen.wiremock.ext.WiremockUriResolver;

import javax.inject.Inject;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Agnieszka Trzewik
 */
@SpringBootTest
class GetSpendingsSummaryTest {

    private WireMockServer wireMockServer;

    @Inject
    private GetSpendingsSummary getSpendingsSummary;

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
        String uri = "/summary";

        wireMockServer.stubFor(get(urlEqualTo(uri))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody("{\n" +
                                "    \"summary\": 123.30,\n" +
                                "    \"timestamp\": \"2019-10-20T11:01:14.783377+01:00\"\n" +
                                "}")));
    }

    @Test
    void when_get_spending_summary_then_return_status_ok() {
        //Given
        //When
        //Then
        assertThat(getSpendingsSummary.doRequest().getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}