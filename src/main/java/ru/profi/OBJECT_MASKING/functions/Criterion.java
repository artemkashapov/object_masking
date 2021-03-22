package ru.profi.OBJECT_MASKING.functions;

public class Criterion {
    private int length;
    private double[] vector;

    public Criterion(int length, double[] vector) {
        this.length = length;
        this.vector = vector;
    }

    public double getSum() {
        double sum = 0;
        for (int i = 0; i < length; i++) {
            sum += vector[i];
        }
        return sum;
    }

}
