package OBJECT_MASKING.ui;


import OBJECT_MASKING.functions.UnsortedToSorted;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class TableWindow extends JFrame {
    static DefaultTableModel model1 = new javax.swing.table.DefaultTableModel();
    static DefaultTableModel model2 = new javax.swing.table.DefaultTableModel();
    static DefaultTableModel model3 = new javax.swing.table.DefaultTableModel();

    int countOfParameters;
    int countOfObject;
    int countOfPriorities;
    int maxNumber;


    JButton okButton = new JButton("Далее");
    JButton setButton = new JButton("Задать");
    JTable parameters = new JTable(model1);
    JTable criterion = new JTable(model2);
    JTable intervals = new JTable(model3);

    private final JLabel kLabel = new JLabel("Введите количество параметров для максимизации");
    JTextField maxParameters = new JTextField();


    private final JScrollPane tableScrollPane1 = new JScrollPane(parameters);
    private final JScrollPane tableScrollPane2 = new JScrollPane(criterion);
    private final JScrollPane tableScrollPane3 = new JScrollPane(intervals);


    public TableWindow(int countOfObject, int countOfParameters, int countOfPriorities) {
        super("Параметры задачи");
        this.countOfObject = countOfObject;
        this.countOfParameters = countOfParameters;
        this.countOfPriorities = countOfPriorities;

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        model1.addColumn("  Объекты  ");


        for (int i = 0; i < countOfObject; i++) {
            Vector<String> object = new Vector<>();
            for (int j = 0; j < countOfParameters; j++) {
                object.add(" ");
            }
            model1.addRow(object);
        }

        Vector<String> vectorCriteria = new Vector<>();
        for (int i = 0; i < countOfParameters; i++) {
            vectorCriteria.add(" ");
        }
        model2.addColumn("Значимости критериев", vectorCriteria);


        Vector<String> vectorIntervals = new Vector<>();
        for (int i = 0; i < countOfPriorities; i++) {
            vectorIntervals.add(" ");
        }
        model3.addColumn("Левая граница", vectorIntervals);
        model3.addColumn("Правая граница", vectorIntervals);


        parameters.setRowHeight(30);
        criterion.setRowHeight(30);
        intervals.setRowHeight(30);


        parameters.setIntercellSpacing(new Dimension(10, 10));
        parameters.setGridColor(Color.blue);
        parameters.setShowVerticalLines(true);

        parameters.setEnabled(false);
        criterion.setEnabled(false);
        intervals.setEnabled(false);
        okButton.setEnabled(false);

        compose();
        addButtonListeners();

        setSize(650, 300);
        setVisible(true);
    }


    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(tableScrollPane1)
                        .addComponent(tableScrollPane2)
                        .addComponent(tableScrollPane3))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(kLabel)
                        .addComponent(maxParameters)
                        .addComponent(setButton))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(okButton))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(tableScrollPane1)
                        .addComponent(tableScrollPane2)
                        .addComponent(tableScrollPane3))
                .addGroup(layout.createParallelGroup()
                        .addComponent(kLabel)
                        .addComponent(maxParameters)
                        .addComponent(setButton))
                .addGroup(layout.createParallelGroup()
                        .addComponent(okButton))
        );


    }

    public static void setColumnsWidth(JTable table) {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JTableHeader th = table.getTableHeader();
        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            int prefWidth =
                    Math.round(
                            (float) th.getFontMetrics(
                                    th.getFont()).getStringBounds(th.getTable().getColumnName(i),
                                    th.getGraphics()
                            ).getWidth()
                    );
            column.setPreferredWidth(prefWidth + 10);
        }
    }

    public static ArrayList<Double> maximize(ArrayList<Double> array, int maxNumber) {
        for (int i = maxNumber; i < array.size(); i++) {
            array.set(i, -1 / array.get(i));
        }
        return array;
    }


    private void addButtonListeners() {
        okButton.addActionListener(evt -> {
            ArrayList<Double> numdata = new ArrayList();
            ArrayList<Double> koeff = new ArrayList<>();
            ArrayList<Double> result = new ArrayList<>();
            double resOneObject = 0;
            for (int i = 0; i < model1.getRowCount(); i++) {
                for (int j = 1; j < model1.getColumnCount(); j++) {
                    numdata.add(Double.parseDouble(model1.getValueAt(i, j).toString()));
                    koeff.add(Double.parseDouble(model2.getValueAt(j - 1, 0).toString()));
                }
                numdata = TableWindow.maximize(numdata, maxNumber);
                for (int count = 1; count < model1.getColumnCount(); count++) {
                    resOneObject += (numdata.get(count - 1) * koeff.get(count - 1));
                }
                numdata.clear();
                koeff.clear();
                result.add(resOneObject);
                resOneObject = 0;
            }
            ArrayList<Double> intervalsArray = new ArrayList<>();
            for (int k = 0; k < countOfPriorities; k++){
                intervalsArray.add(Double.parseDouble(model3.getValueAt(k, 0).toString()));
                intervalsArray.add(Double.parseDouble(model3.getValueAt(k, 1).toString()));
            }
            System.out.println(intervalsArray);
            System.out.println(result);

            UnsortedToSorted data = new UnsortedToSorted(result, intervalsArray);

            data.fullSort();
            System.out.println(Arrays.toString(data.getNumVector()));
            System.out.println(Arrays.toString(data.getPriorities()));
            System.out.println(Arrays.toString(data.getSumVector()));

        });
        setButton.addActionListener(evt -> {
            parameters.setEnabled(true);
            criterion.setEnabled(true);
            intervals.setEnabled(true);
            okButton.setEnabled(true);

            maxNumber = Integer.parseInt(maxParameters.getText());
            for (int i = 0; i < maxNumber; i++) {
                model1.addColumn("      K " + (i + 1) + "     " + "(max)");
            }
            for (int i = maxNumber; i < countOfParameters; i++) {
                model1.addColumn("      K " + (i + 1) + "     " + "(min)");
            }
            TableWindow.setColumnsWidth(parameters);
            TableWindow.setColumnsWidth(criterion);
            TableWindow.setColumnsWidth(intervals);

            setButton.setEnabled(false);




        });
        /*
        parameters.getModel().addTableModelListener(e -> {
            if (parameters.getSelectedRow()>=0) {
                try {
                    if (parameters.getSelectedColumn()==1){

                        parameters.setValueAt("VALUE", parameters.getSelectedRow(), 2);
                    }
                } catch (ArrayIndexOutOfBoundsException ee){
                    ee.printStackTrace();
                }
            }
        });

         */

    }

}
