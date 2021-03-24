package OBJECT_MASKING.functions;

import org.testng.annotations.Test;

import java.util.Arrays;

public class UnsortedToSortedTest {

    private final double[] s1 = {1, 4, 2, 5, 7};
    private final double[] s2 = {1, 7, 88, 5, 7, 1, 1, 1};
    private final double[] s3 = {6, 4, 1, 59, 7, 12};

    private final double[] Ko = {2.6, 2.6, 2.8, 2.7, 3.1, 1.3, 3.3, 3.4, 0.6, 0.9};
    private final double[] intervals = {3.1, 4.0, 2.1, 3.0, 1.1, 2.0, 0, 1.0};


    private final double[] i1 = {1, 3, 6, 7, 12, 59};

    private UnsortedToSorted getElem(double[] s) {
        return new UnsortedToSorted(s);
    }

    //private UnsortedToSorted getElems1(double[] s, double[] ints) {
    //return new UnsortedToSorted(s, ints);
    // }

    @Test
    public void testBubbleSort() {
        UnsortedToSorted t1 = getElem(s1);
        UnsortedToSorted t2 = getElem(s2);
        UnsortedToSorted t3 = getElem(s3);
        t1.bubbleSort();
        t2.bubbleSort();
        t3.bubbleSort();

        System.out.println(Arrays.toString(t1.getNumVector()));
        System.out.println(Arrays.toString(t1.getSumVector()));

        System.out.println(Arrays.toString(t2.getNumVector()));
        System.out.println(Arrays.toString(t2.getSumVector()));

        System.out.println(Arrays.toString(t3.getNumVector()));
        System.out.println(Arrays.toString(t3.getSumVector()));

    }

    @Test
    public void testGetSumVector() {
        UnsortedToSorted t1 = getElem(s1);
        UnsortedToSorted t2 = getElem(s2);
        UnsortedToSorted t3 = getElem(s3);

        System.out.println(Arrays.toString(t1.getNumVector()));
        System.out.println(Arrays.toString(t1.getSumVector()));

        System.out.println(Arrays.toString(t2.getNumVector()));
        System.out.println(Arrays.toString(t2.getSumVector()));

        System.out.println(Arrays.toString(t3.getNumVector()));
        System.out.println(Arrays.toString(t3.getSumVector()));

    }

    //  @Test
    //public void testIntervalSort() {
    //UnsortedToSorted ints = getElems1(s1, i1);

    //System.out.println(Arrays.toString(ints.getIntervalVector()));

    //ints.intervalSort();
    //System.out.println(Arrays.toString(ints.getIntervalVector()));
    //}

    //@Test
    //public void testFullTest() {
    // UnsortedToSorted ex1 = getElems1(Ko, intervals);
    //System.out.println(Arrays.toString(ex1.getNumVector()));
    //System.out.println(Arrays.toString(ex1.getSumVector()));
    //System.out.println(Arrays.toString(ex1.getIntervalVector()));

    //ex1.fullSort();
    // System.out.println(Arrays.toString(ex1.getNumVector()));
    // System.out.println(Arrays.toString(ex1.getPriorities()));
    //.out.println(Arrays.toString(ex1.getSumVector()));

    //}
}