package view.listeners;

import controller.DataController;
import view.dialogs.DeleteRecordDialog;
import view.dialogs.SearchAndDeleteView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Batbara on 18.04.2017.
 */
public class DeleteRecordListener implements ActionListener {

    private final DeleteRecordDialog deleteRecordDialog;
    public DeleteRecordListener(JFrame ownerFrame, DataController dataController){
        deleteRecordDialog = new DeleteRecordDialog(ownerFrame, dataController);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        SearchAndDeleteView view = deleteRecordDialog.getView();
        view.centerOnScreen();
        view.setVisible(true);
        view.clearAllPanelsTextFields();
        view.hideComponents();
        view.setPanelsVisibility();
    }
}
