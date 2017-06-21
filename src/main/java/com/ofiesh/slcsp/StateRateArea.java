package com.ofiesh.slcsp;

import org.jetbrains.annotations.NotNull;

public class StateRateArea {

    private final String state;
    private final String rateArea;

    public StateRateArea(@NotNull String state, @NotNull String rateArea) {
        this.state = state;
        this.rateArea = rateArea;
    }

    @NotNull
    public String getState() {
        return state;
    }

    @NotNull
    public String getRateArea() {
        return rateArea;
    }
}
