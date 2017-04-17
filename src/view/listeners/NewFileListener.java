package view.listeners;

import controller.DataController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Batbara on 16.04.2017.
 */
public class NewFileListener  implements ActionListener{
    private final DataController dataController;
    public NewFileListener(DataController dataController){
        this.dataController = dataController;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        String title = "Новая таблица";
        String message = "Все несохраненные данные будут потеряны. Вы уверены?";
        int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION){
            dataController.clearDataBase();
        }
        //if (reply == JOptionPane.)
    }
}
