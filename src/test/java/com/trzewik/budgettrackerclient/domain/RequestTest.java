package com.trzewik.budgettrackerclient.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author Agnieszka Trzewik
 */
class RequestTest {

    @ParameterizedTest
    @MethodSource("matching_string_and_enums")
    void should_return_enum_when_proper_request(String stringRequest, Request enumRequest) {
        //Given
        //When
        Request request = Request.matchRequest(stringRequest);
        //Then
        assertThat(request).isEqualTo(enumRequest);

    }

    private static Stream<Arguments> matching_string_and_enums() {
        return Stream.of(
                Arguments.of("getList", Request.GET_SPENDINGS),
                Arguments.of("getSummary", Request.GET_SPENDINGS_SUMMARY),
                Arguments.of("send", Request.POST_SPENDING));
    }

    @ParameterizedTest
    @ValueSource(strings = {"postSummary", "getSend"})
    void should_return_no_request_found_exception_when_wrong_request(String request) {
        //Given
        //When
        //Then
        assertThatThrownBy(() -> Request.matchRequest(request))
                .isExactlyInstanceOf(NoRequestFoundException.class)
                .hasMessage("Wrong request given!");
    }

}