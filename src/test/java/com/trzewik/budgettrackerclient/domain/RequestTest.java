package com.trzewik.budgettrackerclient.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Agnieszka Trzewik
 */
class RequestTest {

    private static Stream<Arguments> matching_string_and_enums() {
        return Stream.of(
                Arguments.of("getList", Request.GET_SPENDINGS),
                Arguments.of("getSummary", Request.GET_SPENDINGS_SUMMARY),
                Arguments.of("send", Request.POST_SPENDING));
    }

    @ParameterizedTest
    @MethodSource("matching_string_and_enums")
    void should_return_enum_when_proper_string_given(String stringRequest, Request enumRequest) {
        //Given
        //When
        Request request = Request.matchRequest(stringRequest);
        //Then
        assertThat(request).isEqualTo(enumRequest);

    }

}