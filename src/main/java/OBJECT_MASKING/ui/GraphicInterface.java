package OBJECT_MASKING.ui;

import OBJECT_MASKING.exceptions.WrongNumberOfElementsException;

import javax.swing.*;


public class GraphicInterface extends JFrame {

    // n - будем называть количество объектов ({i}n = 1, 2, ..., n)
    // k - будем называть количество критериев ({Ck} = C1, C2, ..., Ck)

    private final JButton nextButton = new JButton("Далее");
    private final JLabel nLabel = new JLabel("Введите количество Объектов");
    private final JLabel kLabel = new JLabel("Введите количество Критериев");
    private final JTextField nGet = new JTextField(10);
    private final JTextField kGet = new JTextField(10);

    public GraphicInterface() {
        super("Главное окно");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        compose();
        addButtonListeners();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addButtonListeners() {
        //nextButton.addActionListener(evt -> new CountWindow());
        nextButton.addActionListener(evt -> {
            try {
                int objectNumber = Integer.parseInt(nGet.getText());
                int criterionNumber = Integer.parseInt(kGet.getText());
                if (objectNumber < 1 || criterionNumber < 1) {
                    throw new WrongNumberOfElementsException();
                }
                this.dispose();
                new SimpleTableTest(objectNumber, criterionNumber);
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
                        .addComponent(kLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(kGet)
                        .addComponent(nGet)
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
                        .addComponent(nextButton))

        );

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GraphicInterface::new);
    }
}

