package com.ofiesh.slcsp;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

public class RateAreaReducerTest {

    RateAreaReducer rateAreaReducer = new RateAreaReducer(new CsvParser());

    @Test
    public void oneInMoreThanOneArea() throws IOException, URISyntaxException {
        assertNull("zip `1` is in more than one area and should return null",
                rateAreaReducer.getStateRateArea("1", "zips.csv"));
    }

    @Test
    public void twoInMoreThanOneState() throws IOException, URISyntaxException {
        assertNull("zip `2` is in more than one state and should return null",
                rateAreaReducer.getStateRateArea("2", "zips.csv"));
    }

    @Test
    public void threeInDuplicateAreaAndState() throws IOException, URISyntaxException {
        assertThat("zip `3` has two records with the same state and area, should return StateRateArea(C, 1)",
                rateAreaReducer.getStateRateArea("3", "zips.csv"),
                allOf(hasProperty("state", is("C")),
                        hasProperty("rateArea", is("1"))));
    }

    @Test
    public void fourHasUniqueRateArea() throws IOException, URISyntaxException {
        assertThat("zip `4` has a unique state and area, should return StateRateArea(D, 1)",
                rateAreaReducer.getStateRateArea("4", "zips.csv"),
                allOf(hasProperty("state", is("D")),
                        hasProperty("rateArea", is("1"))));
    }

    @Test
    public void fiveHasNoRateArea() throws IOException, URISyntaxException {
        assertNull("zip `5` has no matching records and should return null",
                rateAreaReducer.getStateRateArea("5", "zips.csv"));
    }
}