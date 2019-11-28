package com.trzewik.budgettrackerclient.adapter;

import com.trzewik.budgettrackerclient.domain.SpendingDTO;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

    GetSpendings(@Value("${host}") String host, @Value("${hostport}") int hostport, RestTemplate restTemplate) {
        this.url = "http://" + host + ":" + hostport + "/spendings";
        this.restTemplate = restTemplate;
    }

    ResponseEntity<SpendingDTO[]> doRequest() {
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<SpendingDTO[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, SpendingDTO[].class);

        String spendings = Arrays.toString(response.getBody());
        LoggerFactory
                .getLogger(GetSpendings.class)
                .info("{}", spendings);

        return response;
    }
}
