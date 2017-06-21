package com.ofiesh.slcsp;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Iterator;

import static com.ofiesh.slcsp.MetalLevel.Silver;

/**
 * Returns the second highest silver rate for a given {@link StateRateArea}. If there is no second highest silver rate,
 * returns null even if there is a first highest rate.
 */
public class SlcspRateReducer {

    private final Logger logger = LoggerFactory.getLogger(SlcspRateReducer.class);
    private final PlanFactory planFactory = new PlanFactory();
    private final CsvParser csvParser;

    public SlcspRateReducer(CsvParser csvParser) {
        this.csvParser = csvParser;
    }

    /**
     * Returns the second highest silver rate for a given {@link StateRateArea}. If there is no second highest silver rate,
     * returns null even if there is a first highest rate.
     * *
     * @param stateRateArea The {@link StateRateArea} for which to compare plans from.
     * @param file File path containing plan csv data
     * @return Returns the rate for the secnd highest silver rate, or null if not found.
     */
    @Nullable
    public BigDecimal getSlcspRate(@NotNull StateRateArea stateRateArea, @NotNull String file) {
        Plan lowestPlan = null;
        Plan secondLowestPlan = null;
        Iterator<String[]> iterator = csvParser.parse(file);
        while (iterator.hasNext()) {
            Plan plan = planFactory.createPlan(iterator.next());

            // if plan is silver and the state and area match the StateRateArea
            if (plan.getMetalLevel().equals(Silver)
                    && plan.getState().equals(stateRateArea.getState())
                    && plan.getRateArea().equals(stateRateArea.getRateArea())) {

                // if there is no lowest plan, this is the lowest plan
                if (lowestPlan == null) {
                    lowestPlan = plan;
                    continue;
                }

                // if the lowest plan has the same rate, skip it
                if (lowestPlan.getRate().compareTo(plan.getRate()) == 0) {
                    continue;
                }

                // if second lowest plan has a higher rate than this plan, bump current lowest plan to second
                // and set this plan to lowest
                if (lowestPlan.getRate().compareTo(plan.getRate()) > 0) {
                    secondLowestPlan = lowestPlan;
                    lowestPlan = plan;
                    continue;
                }

                // this isn't the lowest; if second lowest plan is null, this is second lowest
                if (secondLowestPlan == null) {
                    secondLowestPlan = plan;
                }
                // if second lowest plan has a higher rate than this plan, this plan is second lowest
                if (secondLowestPlan.getRate().compareTo(plan.getRate()) > 0) {
                    secondLowestPlan = plan;
                }
            }
        }
        if (secondLowestPlan == null && lowestPlan != null) {
            logger.info("Found a plan for " + stateRateArea.getState() + " " + stateRateArea.getRateArea()
                    + " but not a second lowest");
        }
        return secondLowestPlan == null ? null : secondLowestPlan.getRate();
    }
}
