package OBJECT_MASKING.ui;

import OBJECT_MASKING.functions.UnsortedToSorted;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class ResultWindow extends JFrame {
    static DefaultTableModel resultModel = new javax.swing.table.DefaultTableModel();

    ArrayList<Double> result;
    ArrayList<Double> intervals;

    JTable resultTable = new JTable(resultModel);

    private final JScrollPane tableScrollPanel = new JScrollPane(resultTable);

    public ResultWindow (ArrayList<Double> result, ArrayList<Double> intervals){
        super("Таблица результатов");
        setSize(550, 200);

        this.result = result;
        this.intervals = intervals;

        Container cp = getContentPane();

        cp.setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        UnsortedToSorted objectSorted = new UnsortedToSorted(result, intervals);

        objectSorted.fullSort();

        Vector<Integer> integers = ResultWindow.toVector(objectSorted.getNumVector());
        Vector<Integer> priorities = ResultWindow.toVector(objectSorted.getPriorities());
        Vector<Double> sumVector = ResultWindow.toVector(objectSorted.getSumVector());
        System.out.println(sumVector);

       for (int i = 0; i < sumVector.size(); i++){
           resultModel.addColumn(" ");
       }

        resultModel.addRow(integers);
        resultModel.addRow(priorities);
        resultModel.addRow(sumVector);

        resultTable.setRowHeight(30);

        cp.add(tableScrollPanel);

        resultTable.setEnabled(false);
        setVisible(true);

    }


    public static Vector<Double> toVector(double[] doubles){
        Vector vector = new Vector(doubles.length);

        for (double aDouble : doubles) {
            vector.add(aDouble);
        }
        return vector;
    }

    public static Vector<Integer> toVector(int[] ints){
        Vector vector = new Vector(ints.length);

        for (int anInt : ints) {
            vector.add(anInt);
        }
        return vector;
    }






}
