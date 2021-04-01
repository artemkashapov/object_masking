package OBJECT_MASKING.ui;

import OBJECT_MASKING.exceptions.WrongIntervalsException;
import OBJECT_MASKING.exceptions.WrongNumberOfElementsException;

import javax.swing.*;
import java.awt.*;

public class ErrorWindow extends JDialog {

    ErrorWindow(Component parent, Exception e) {
        getErrorWindow(parent, e);
        setModal(true);
    }

    public void getErrorWindow(Component parent, Exception e) {
        String message = MessageForException(e);
        JOptionPane.showMessageDialog(parent, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

    private String MessageForException(Exception e) {
        if (e instanceof NumberFormatException) {
            return "Неверный формат данных!";
        }
        if (e instanceof WrongNumberOfElementsException) {
            return "Вы ввели неверное значение!";
        }

        if (e instanceof WrongIntervalsException) {
            return "Неверные интервалы!";
        }

        if (e instanceof NullPointerException) {
            return "Что-то пошло не так...";
        }
        return "Неверные данные!";
    }
}