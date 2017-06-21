package com.ofiesh.slcsp;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Creates a {@link Plan} from a line in the plans csv
 */
public class PlanFactory {

    /**
     * Creates a {@link Plan} from a line in the plans.csv
     * @param csvColumns Array of String columns where columns are [ plan_id, state, metal_level, rate,rate_area ]
     * @return The {@link Plan} constructed from the csv line.
     */
    @NotNull
    public Plan createPlan(@NotNull String[] csvColumns) {
        if (csvColumns.length != 5) {
            throw new IllegalArgumentException(Arrays.toString(csvColumns) + " not of expected length, 5");
        }
        return new Plan(csvColumns[0], csvColumns[1], MetalLevel.valueOf(csvColumns[2]), new BigDecimal(csvColumns[3]),
                csvColumns[4]);
    }
}
