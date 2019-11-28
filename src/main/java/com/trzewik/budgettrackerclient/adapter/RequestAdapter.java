package com.trzewik.budgettrackerclient.adapter;

import com.trzewik.budgettrackerclient.domain.NoRequestFoundException;
import com.trzewik.budgettrackerclient.domain.Request;
import com.trzewik.budgettrackerclient.domain.port.spi.RequestPort;
import org.springframework.beans.factory.annotation.Value;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Agnieszka Trzewik
 */
@Named
class RequestAdapter implements RequestPort {

    @Value("${operation}")
    private String operation;

    private GetSpendings getSpendings;
    private GetSpendingsSummary getSpendingsSummary;
    private PostSpendings postSpendings;

    @Inject
    RequestAdapter(GetSpendings getSpendings, GetSpendingsSummary getSpendingsSummary, PostSpendings postSpendings) {
        this.getSpendings = getSpendings;
        this.getSpendingsSummary = getSpendingsSummary;
        this.postSpendings = postSpendings;
    }

    @Override
    public void sendRequest() {
        Request request = Request.matchRequest(operation);
        switch (request) {
            case GET_SPENDINGS:
                getSpendings.doRequest();
                break;
            case GET_SPENDINGS_SUMMARY:
                getSpendingsSummary.doRequest();
                break;
            case POST_SPENDING:
                postSpendings.doRequest();
                break;
            default:
                throw new NoRequestFoundException("Wrong request given!");
        }
    }
}
