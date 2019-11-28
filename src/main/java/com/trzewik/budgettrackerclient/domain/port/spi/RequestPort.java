package com.trzewik.budgettrackerclient.domain.port.spi;

import javax.inject.Named;

/**
 * @author Agnieszka Trzewik
 */
@Named
public interface RequestPort {

    void sendRequest();
}
