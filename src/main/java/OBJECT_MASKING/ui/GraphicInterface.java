package OBJECT_MASKING.ui;

import OBJECT_MASKING.exceptions.WrongNumberOfElementsException;

import javax.swing.*;


public class GraphicInterface extends JFrame {

    // n - будем называть количество объектов ({i}n = 1, 2, ..., n)
    // k - будем называть количество критериев ({Ck} = C1, C2, ..., Ck)
    // p - будем называть количество приоритеов (В массиве интервалов 2p элементов)

    private final JButton nextButton = new JButton("Далее");
    private final JLabel nLabel = new JLabel("Введите количество объектов");
    private final JLabel kLabel = new JLabel("Введите количество критериев");
    private final JLabel pLabel = new JLabel("Введите количество приоритетов");
    private final JTextField nGet = new JTextField(10);
    private final JTextField kGet = new JTextField(10);
    private final JTextField pGet = new JTextField(10);

    public GraphicInterface() {
        super("Окно конфигурации");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 170);
        compose();
        addButtonListeners();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addButtonListeners() {
        nextButton.addActionListener(evt -> {
            try {
                int objectNumber = Integer.parseInt(nGet.getText());
                int criterionNumber = Integer.parseInt(kGet.getText());
                int priorityNumber = Integer.parseInt(pGet.getText());
                if (objectNumber < 1 || criterionNumber < 1 || priorityNumber < 1) {
                    throw new WrongNumberOfElementsException();
                }
                this.dispose();
                new TableWindow(objectNumber, criterionNumber, priorityNumber);
            } catch (Exception exception) {
                new ErrorWindow(this, exception);
            }
        });
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(nLabel)
                        .addComponent(kLabel)
                        .addComponent(pLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(kGet)
                        .addComponent(nGet)
                        .addComponent(pGet)
                        .addComponent(nextButton, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(nLabel)
                        .addComponent(nGet, 0, GroupLayout.DEFAULT_SIZE, 20))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(kLabel)
                        .addComponent(kGet, 0, GroupLayout.DEFAULT_SIZE, 20))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(pLabel)
                        .addComponent(pGet, 0, GroupLayout.DEFAULT_SIZE, 20))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(nextButton))
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GraphicInterface::new);
    }
}

