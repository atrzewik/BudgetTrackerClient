package com.trzewik.budgettrackerclient.adapter;

import com.trzewik.budgettrackerclient.domain.NoRequestFoundException;
import com.trzewik.budgettrackerclient.domain.SpendingDTO;
import com.trzewik.budgettrackerclient.domain.SpendingSummary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class RequestAdapterTest {

    @Mock
    private GetSpendings getSpendings;
    @Mock
    private GetSpendingsSummary getSpendingsSummary;
    @Mock
    private PostSpendings postSpendings;


    @Test
    void should_invoke_get_spendings_once_when_requested() throws NoSuchFieldException, IllegalAccessException {
        //Given
        when(getSpendings.doRequest()).thenReturn(new ResponseEntity<>(new SpendingDTO[0], HttpStatus.OK));
        RequestAdapter requestAdapter = new RequestAdapter(getSpendings, getSpendingsSummary, postSpendings);
        changeOperationField("getList", requestAdapter);
        //When
        requestAdapter.sendRequest();
        //Then
        verify(getSpendings, times(1)).doRequest();

    }

    @Test
    void should_invoke_post_spending_once_when_requested() throws NoSuchFieldException, IllegalAccessException {
        //Given
        when(postSpendings.doRequest()).thenReturn(new ResponseEntity<>("", HttpStatus.OK));
        RequestAdapter requestAdapter = new RequestAdapter(getSpendings, getSpendingsSummary, postSpendings);
        changeOperationField("send", requestAdapter);
        //When
        requestAdapter.sendRequest();
        //Then
        verify(postSpendings, times(1)).doRequest();

    }

    @Test
    void should_invoke_get_spendings_summary_once_when_requested() throws NoSuchFieldException, IllegalAccessException {
        //Given
        when(getSpendingsSummary.doRequest()).thenReturn(new ResponseEntity<>(new SpendingSummary(), HttpStatus.OK));
        RequestAdapter requestAdapter = new RequestAdapter(getSpendings, getSpendingsSummary, postSpendings);
        changeOperationField("getSummary", requestAdapter);
        //When
        requestAdapter.sendRequest();
        //Then
        verify(getSpendingsSummary, times(1)).doRequest();

    }


    @ParameterizedTest
    @ValueSource(strings = {"postSummary", "getSend"})
    void should_return_no_request_found_exception_when_wrong_request(String request) throws NoSuchFieldException, IllegalAccessException {
        //Given
        RequestAdapter requestAdapter = new RequestAdapter(getSpendings, getSpendingsSummary, postSpendings);
        changeOperationField(request, requestAdapter);
        //When
        //Then
        assertThatThrownBy(requestAdapter::sendRequest)
                .isExactlyInstanceOf(NoRequestFoundException.class)
                .hasMessage("Wrong request given!");

    }

    private void changeOperationField(String request, RequestAdapter requestAdapter) throws NoSuchFieldException, IllegalAccessException {
        Field operation = requestAdapter.getClass().getDeclaredField("operation");
        operation.setAccessible(true);
        operation.set(requestAdapter, request);
        operation.setAccessible(false);
    }


}