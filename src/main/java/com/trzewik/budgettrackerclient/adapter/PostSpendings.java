package com.trzewik.budgettrackerclient.adapter;

import com.trzewik.budgettrackerclient.domain.SpendingDTO;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.inject.Named;
import java.math.BigDecimal;

/**
 * @author Agnieszka Trzewik
 */
@Named
class PostSpendings {

    private final String url;
    private final RestTemplate restTemplate;
    @Value("${param.description}")
    private String description;
    @Value("${param.price}")
    private String price;

    PostSpendings(@Value("${host}") String host, @Value("${hostport}") int hostport, RestTemplate restTemplate) {
        this.url = "http://" + host + ":" + hostport + "/spendings";
        this.restTemplate = restTemplate;
    }

    ResponseEntity<String> doRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> entity = new HttpEntity<>(new SpendingDTO(description, new BigDecimal(price)), headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        LoggerFactory
                .getLogger(PostSpendings.class)
                .info(String.valueOf(response.getStatusCode()));
        return response;
    }
}
