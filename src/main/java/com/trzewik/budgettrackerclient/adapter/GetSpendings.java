package com.trzewik.budgettrackerclient.adapter;

import com.trzewik.budgettrackerclient.domain.SpendingDTO;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import javax.inject.Named;
import java.util.Arrays;

/**
 * @author Agnieszka Trzewik
 */
@Named
class GetSpendings {

    private final String url;
    private final RestTemplate restTemplate;

    GetSpendings(@Value("${host}") String host, RestTemplate restTemplate) {
        this.url = "http://" + host + ":8080/spendings";
        this.restTemplate = restTemplate;
    }

    void doRequest() {
        SpendingDTO[] response = restTemplate.getForObject(url, SpendingDTO[].class);

        if (response != null) LoggerFactory
                .getLogger(GetSpendings.class)
                .info(Arrays.toString(response));
    }
}
