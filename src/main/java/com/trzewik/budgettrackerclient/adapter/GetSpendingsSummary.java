package com.trzewik.budgettrackerclient.adapter;

import com.trzewik.budgettrackerclient.domain.SpendingSummary;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.inject.Named;
import java.util.Objects;

/**
 * @author Agnieszka Trzewik
 */
@Named
class GetSpendingsSummary {

    private final String url;
    private final RestTemplate restTemplate;

    GetSpendingsSummary(@Value("${host}") String host, @Value("${hostport}") int hostport, RestTemplate restTemplate) {
        this.url = "http://" + host + ":" + hostport + "/summary";
        this.restTemplate = restTemplate;
    }

    ResponseEntity<SpendingSummary> doRequest() {
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<SpendingSummary> response = restTemplate.exchange(url, HttpMethod.GET, entity, SpendingSummary.class);

        LoggerFactory
                .getLogger(GetSpendingsSummary.class)
                .info(String.valueOf(Objects.requireNonNull(response.getBody()).getSummary()));
        return response;
    }
}
