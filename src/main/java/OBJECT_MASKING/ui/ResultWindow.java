package OBJECT_MASKING.ui;

import OBJECT_MASKING.functions.UnsortedToSorted;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class ResultWindow extends JDialog {
    private final DefaultTableModel resultModel = new javax.swing.table.DefaultTableModel();

    ArrayList<Double> result;
    ArrayList<Double> intervals;

    JTable resultTable = new JTable(resultModel);
    JScrollPane tableScrollPanel = new JScrollPane(resultTable);

    public ResultWindow(ArrayList<Double> result, ArrayList<Double> intervals) {
        setSize(550, 200);

        this.result = result;
        this.intervals = intervals;

        //Container cp = getContentPane();

        //cp.setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        UnsortedToSorted objectSorted = new UnsortedToSorted(result, intervals);

        objectSorted.fullSort();

        Vector<String> integers = ResultWindow.toVector(objectSorted.getNumVector());
        Vector<String> priorities = ResultWindow.toVector(objectSorted.getPriorities());
        Vector<String> sumVector = ResultWindow.toVector(objectSorted.getSumVector());
        System.out.println(sumVector);
        Vector<String> names = new Vector<>();
        integers.insertElementAt("Объекты", 0);
        priorities.insertElementAt("Приоритеты", 0);
        sumVector.insertElementAt("Значения", 0);


        for (int i = 0; i < sumVector.size(); i++) {
            resultModel.addColumn(" ");
        }

        resultModel.addRow(integers);
        resultModel.addRow(priorities);
        resultModel.addRow(sumVector);


        resultTable.setRowHeight(30);
        compose();

        //cp.add(tableScrollPanel);
        setModal(true);
        resultTable.setEnabled(false);
        setVisible(true);

    }

    public static Vector<String> toVector(double[] doubles) {
        Vector vector = new Vector(doubles.length);

        for (double aDouble : doubles) {
            vector.add(String.valueOf(aDouble));
        }
        return vector;
    }

    public static Vector<String> toVector(int[] ints) {
        Vector vector = new Vector(ints.length);

        for (int anInt : ints) {
            vector.add(String.valueOf(anInt));
        }
        return vector;
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(tableScrollPanel));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(tableScrollPanel));

    }


}
