package OBJECT_MASKING.ui;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class SimpleTableTest extends JFrame {
    static DefaultTableModel model1 = new javax.swing.table.DefaultTableModel();
    static DefaultTableModel model2 = new javax.swing.table.DefaultTableModel();
    int countOfParameters;
    int countOfObject;
    JButton ok = new JButton("Ok");
    JTable parameters = new JTable(model1);
    JTable criterion = new JTable(model2);
    private final JScrollPane tableScrollPane1 = new JScrollPane(parameters);
    private final JScrollPane tableScrollPane2 = new JScrollPane(criterion);


    public SimpleTableTest(int countOfObject, int countOfParameters) {
        super("Простой пример с JTable");
        this.countOfObject = countOfObject;
        this.countOfParameters = countOfParameters;


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        model1.addColumn("  Объекты  ");

        for (int i = 0; i < countOfParameters; i++) {
            model1.addColumn("      k " + (i + 1) + "     ");
        }

        for (int i = 0; i < countOfObject; i++) {
            Vector<String> object = new Vector<>();
            for (int j = 0; j < countOfParameters; j++) {
                object.add(" ");
            }
            model1.addRow(object);

        }


        Vector<String> vectorCtriteries = new Vector<>();
        for (int i = 0; i < countOfParameters; i++) {
            vectorCtriteries.add(" ");
        }
        model2.addColumn("Коэффициент значимости", vectorCtriteries);


        parameters.setRowHeight(30);

        criterion.setRowHeight(30);
        SimpleTableTest.setColumnsWidth(parameters);
        SimpleTableTest.setColumnsWidth(criterion);
        parameters.setIntercellSpacing(new Dimension(10, 10));
        parameters.setGridColor(Color.blue);
        parameters.setShowVerticalLines(true);

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
                        .addGap(20)
                        .addComponent(tableScrollPane2)
                )
                .addComponent(ok)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(tableScrollPane1)
                        .addComponent(tableScrollPane2))
                .addComponent(ok)
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

    private void addButtonListeners() {
        ok.addActionListener(evt -> {
            ArrayList<Double> numdata = new ArrayList();
            ArrayList<Double> koeff = new ArrayList<>();
            ArrayList<Double> result = new ArrayList<>();
            double resOneObject = 0;
            for (int i = 0; i < model1.getRowCount(); i++) {
                for (int j = 1; j < model1.getColumnCount(); j++) {
                    numdata.add(Double.parseDouble(model1.getValueAt(i, j).toString()));
                    koeff.add(Double.parseDouble(model2.getValueAt(j - 1, 0).toString()));
                    resOneObject += (numdata.get(j - 1) * koeff.get(j - 1));
                }
                numdata.clear();
                koeff.clear();
                result.add(resOneObject);
                resOneObject = 0;
            }
            System.out.println(result);
        });

    }

    public static void main(String[] args) {
        new SimpleTableTest(3, 5);
    }
}
