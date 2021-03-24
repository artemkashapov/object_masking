package OBJECT_MASKING.functions;

import java.util.ArrayList;

public class UnsortedToSorted {
    private final int[] numVector;
    private final double[] sumVector;
    private double[] intervalVector;
    private int[] priorities;

    public UnsortedToSorted(ArrayList<Double> sumVector, ArrayList<Double> intervalVector) {

        this.sumVector = new double[sumVector.size()];
        this.numVector = new int[sumVector.size()];
        this.intervalVector = new double[intervalVector.size()];
        for (int i = 0; i < sumVector.size(); i++) {
            this.numVector[i] = i + 1;
            this.sumVector[i] = sumVector.get(i);
        }

        for (int i = 0; i < intervalVector.size(); i++) {
            this.intervalVector[i] = intervalVector.get(i);
        }
    }

    public UnsortedToSorted(double[] sumVector) {
        this.sumVector = new double[sumVector.length];
        this.numVector = new int[sumVector.length];
        for (int i = 0; i < sumVector.length; i++) {
            this.numVector[i] = i + 1;
            this.sumVector[i] = sumVector[i];
        }
    }

    public void bubbleSort() {
        boolean sorted = false;
        int tempInd;
        double temp;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < sumVector.length - 1; i++) {
                if (sumVector[i] > sumVector[i + 1]) {
                    temp = sumVector[i];
                    tempInd = numVector[i];
                    sumVector[i] = sumVector[i + 1];
                    numVector[i] = numVector[i + 1];
                    numVector[i + 1] = tempInd;
                    sumVector[i + 1] = temp;
                    sorted = false;
                }
            }
        }

        double[] tempInt1 = new double[sumVector.length];
        int[] tempInt2 = new int[numVector.length];
        System.arraycopy(sumVector, 0, tempInt1, 0, sumVector.length);
        System.arraycopy(numVector, 0, tempInt2, 0, numVector.length);
        for (int i = 0; i < tempInt1.length; i++) {
            sumVector[i] = tempInt1[tempInt1.length - (i + 1)];
            numVector[i] = tempInt2[tempInt2.length - (i + 1)];
        }
    }

    public void intervalSort() {
        boolean sorted = false;
        int tempInd;
        double temp;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < intervalVector.length - 1; i++) {
                if (intervalVector[i] > intervalVector[i + 1]) {
                    temp = intervalVector[i];
                    intervalVector[i] = intervalVector[i + 1];
                    intervalVector[i + 1] = temp;
                    sorted = false;
                }
            }
        }

        double[] tempInt = new double[intervalVector.length];
        System.arraycopy(intervalVector, 0, tempInt, 0, intervalVector.length);
        for (int i = 0; i < tempInt.length; i++) {
            intervalVector[i] = tempInt[tempInt.length - (i + 1)];
        }
    }

    public void fullSort() {
        bubbleSort();
        intervalSort();
        int o = 1;
        int k = 0;
        int[] priorityVector = new int[sumVector.length];
        while (o <= intervalVector.length / 2) {
            for (int i = 0; i < sumVector.length; i++) {
                if (sumVector[i] <= intervalVector[2 * o - 2] && sumVector[i] >= intervalVector[2 * o - 1]) {
                    priorityVector[i] = o;
                }
            }
            o++;
        }
        this.priorities = new int[priorityVector.length];
        System.arraycopy(priorityVector, 0, this.priorities, 0, priorityVector.length);
    }


    public double[] getSumVector() {
        return sumVector;
    }

    public double[] getIntervalVector() {
        return intervalVector;
    }

    public int[] getPriorities() {
        return priorities;
    }

    public int[] getNumVector() {
        return numVector;
    }
}
