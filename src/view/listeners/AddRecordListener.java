package view.listeners;

import controller.DataController;
import view.dialogs.AddRecordDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Batbara on 18.04.2017.
 */
public class AddRecordListener implements ActionListener {
    private final AddRecordDialog addRecordDialog;
    public AddRecordListener(JFrame ownerFrame, DataController dataController){
        addRecordDialog = new AddRecordDialog(ownerFrame, dataController);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        addRecordDialog.centerOnScreen();
        addRecordDialog.setVisible(true);
    }
}
