package com.ofiesh.slcsp;

import org.jetbrains.annotations.NotNull;

/**
 * ADT for zips csv
 */
public class Zip {
    final private String zipcode;
    final private String state;
    final private String countyCode;
    final private String name;
    final private String rateArea;

    public Zip(@NotNull String zipcode, @NotNull String state, @NotNull String countyCode, @NotNull String name,
            @NotNull String rateArea) {
        this.zipcode = zipcode;
        this.state = state;
        this.countyCode = countyCode;
        this.name = name;
        this.rateArea = rateArea;
    }

    @NotNull
    public String getZipcode() {
        return zipcode;
    }

    @NotNull
    public String getState() {
        return state;
    }

    @NotNull
    public String getCountyCode() {
        return countyCode;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public String getRateArea() {
        return rateArea;
    }
}
