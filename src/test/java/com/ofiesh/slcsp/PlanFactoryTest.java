package com.ofiesh.slcsp;

import org.junit.Test;

import java.math.BigDecimal;

import static com.ofiesh.slcsp.MetalLevel.Silver;
import static org.junit.Assert.*;


public class PlanFactoryTest {

    PlanFactory planFactory = new PlanFactory();

    @Test
    public void testValidPlanColumns() {
        Plan plan = planFactory.createPlan(new String[]{"74449NR9870320", "GA", "Silver", "298.62", "7"});
        assertEquals("Plan id didn't match", "74449NR9870320", plan.getPlanId());
        assertEquals("State didn't match", "GA", plan.getState());
        assertEquals("Metal level didn't match", Silver, plan.getMetalLevel());
        assertEquals("Rate didn't match", BigDecimal.valueOf(298.62), plan.getRate());
        assertEquals("Area didn't match", "7", plan.getRateArea());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPlanColumns() {
        planFactory.createPlan(new String[]{"74449NR9870320", "GA", "Silver", "298.62"});
    }
}