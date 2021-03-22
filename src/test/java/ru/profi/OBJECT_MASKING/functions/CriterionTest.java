package ru.profi.OBJECT_MASKING.functions;

import org.junit.Test;

class CriterionTest {
    private final double[] c1 = new double[]{1, 0.4, 0.9, 1.6};
    private Criterion v1() {
        return new Criterion(c1.length, c1);
    }

    @Test
    public void getSum() {
    }
}