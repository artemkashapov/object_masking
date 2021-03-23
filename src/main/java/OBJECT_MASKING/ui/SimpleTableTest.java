package OBJECT_MASKING.ui;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class SimpleTableTest extends JFrame {
    static DefaultTableModel model = new javax.swing.table.DefaultTableModel();
    int countOfParameteres;
    int countOfObject;
    JButton ok = new JButton("Ok");


    public SimpleTableTest(int countOfObject, int countOfParametres) {
        super("Простой пример с JTable");
        this.countOfObject = countOfObject;
        this.countOfParameteres = countOfParametres;

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        for (int i = 0; i < countOfParametres; i++){
            model.addColumn("k " + (i+1));
        }

        for (int i = 0; i < countOfObject; i++){
            Vector<String> object = new Vector<>();
            for (int j = 0; j < countOfParametres; j++){
                object.add(" ");
            }
            model.addRow(object);

        }


        // Таблица с настройками
        JTable table2 = new JTable(model);
        // Настройка таблицы
        table2.setRowHeight(30);
        //table2.setRowHeight(1, 20);
        table2.setIntercellSpacing(new Dimension(10, 10));
        table2.setGridColor(Color.blue);
        table2.setShowVerticalLines(true);

        addButtonListeners();
        Box contents = new Box(BoxLayout.X_AXIS);
        contents.add(new JScrollPane(table2));
        contents.add(ok);

        // Настройка таблицы table3 - цвет фона, цвет выделения
        // Вывод окна на экран
        setContentPane(contents);
        setSize(500, 400);
        setVisible(true);
    }
    private void addButtonListeners() {
        ok.addActionListener(evt ->{
            ArrayList<String> numdata = new ArrayList();
            for (int count = 0; count < model.getRowCount(); count++){
                numdata.add(model.getValueAt(count, 0).toString());
            }
            System.out.println(numdata);
        });

    }

    public static void main(String[] args) {
        new SimpleTableTest(3, 5);
    }
}
