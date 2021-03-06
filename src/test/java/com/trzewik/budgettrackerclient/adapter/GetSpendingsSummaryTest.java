package com.trzewik.budgettrackerclient.adapter;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.trzewik.budgettrackerclient.domain.SpendingSummary;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import javax.inject.Inject;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

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

    @Test
    void when_get_spending_summary_then_return_proper_spending_summary() {
        //Given
        //When
        SpendingSummary spendingSummary = getSpendingsSummary.doRequest().getBody();
        OffsetDateTime data = OffsetDateTime.of(2019,10,20,10,1,14,783377000, ZoneOffset.UTC);
        //Then
        assertThat(Objects.requireNonNull(spendingSummary).getSummary()).isEqualByComparingTo("123.30");
        assertThat(Objects.requireNonNull(spendingSummary).getTimestamp()).isEqualTo(data);
    }

}