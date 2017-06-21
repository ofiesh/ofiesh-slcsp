package com.ofiesh.slcsp;

import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class SlcspRateReducerTest {

    private SlcspRateReducer slcspRateReducer = new SlcspRateReducer(new CsvParser());

    @Test
    public void threeShouldReturnSecondLowestSilver() throws IOException, URISyntaxException {
        assertEquals("Second lowest silver for StateRateArea(C, 1) should be 2",
                new BigDecimal(2), slcspRateReducer.getSlcspRate(new StateRateArea("C", "1"),
                        "plans.csv"));
    }
}