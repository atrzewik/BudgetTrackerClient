package com.trzewik.budgettrackerclient.adapter;

import com.trzewik.budgettrackerclient.domain.SpendingSummary;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import javax.inject.Named;

/**
 * @author Agnieszka Trzewik
 */
@Named
class GetSpendingsSummary {

    private final String url;
    private final RestTemplate restTemplate;

    GetSpendingsSummary(@Value("${host}") String host, RestTemplate restTemplate) {
        this.url = "http://" + host + ":8080/summary";
        this.restTemplate = restTemplate;
    }

    void doRequest() {
        SpendingSummary response = restTemplate.getForObject(url, SpendingSummary.class);

        if (response != null) LoggerFactory
                .getLogger(GetSpendingsSummary.class)
                .info(String.valueOf(response.getSummary()));
    }
}
