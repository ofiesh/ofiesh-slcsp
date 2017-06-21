package com.ofiesh.slcsp;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

/**
 * ADT for the plans csv
 */
public class Plan {
    private final String planId;
    private final String state;
    private final MetalLevel metalLevel;
    private final BigDecimal rate;
    private final String rateArea;


    public Plan(@NotNull String planId, @NotNull String state, @NotNull MetalLevel metalLevel, @NotNull BigDecimal rate,
            @NotNull String rateArea) {
        this.planId = planId;
        this.state = state;
        this.metalLevel = metalLevel;
        this.rate = rate;
        this.rateArea = rateArea;
    }

    @NotNull
    public String getPlanId() {
        return planId;
    }

    @NotNull
    public String getState() {
        return state;
    }

    @NotNull
    public MetalLevel getMetalLevel() {
        return metalLevel;
    }

    @NotNull
    public BigDecimal getRate() {
        return rate;
    }

    public String getRateArea() {
        return rateArea;
    }
}
