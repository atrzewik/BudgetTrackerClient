package com.trzewik.budgettrackerclient.adapter;

import com.trzewik.budgettrackerclient.domain.NoRequestFoundException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RequestAdapterTest {

    @Mock
    private GetSpendings getSpendings;
    @Mock
    private GetSpendingsSummary getSpendingsSummary;
    @Mock
    private PostSpendings postSpendings;

    @ParameterizedTest
    @ValueSource(strings = {"postSummary", "getSend"})
    void should_return_no_request_found_exception_when_wrong_request(String request) throws NoSuchFieldException, IllegalAccessException {
        //Given
        RequestAdapter requestAdapter = new RequestAdapter(getSpendings, getSpendingsSummary, postSpendings);
        changeOperationField(request, requestAdapter);
        //When
        NoRequestFoundException noRequestFoundException = assertThrows(NoRequestFoundException.class,
                requestAdapter::sendRequest);
        //Then
        assertThat(noRequestFoundException.getMessage()).isEqualTo("Wrong request given!");

    }

    private void changeOperationField(String request, RequestAdapter requestAdapter) throws NoSuchFieldException, IllegalAccessException {
        Field operation = requestAdapter.getClass().getDeclaredField("operation");
        operation.setAccessible(true);
        operation.set(requestAdapter, request);
        operation.setAccessible(false);
    }


}