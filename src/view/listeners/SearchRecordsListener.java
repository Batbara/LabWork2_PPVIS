package view.listeners;

import controller.DataController;
import view.dialogs.SearchAndDeleteView;
import view.dialogs.SearchRecordDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Batbara on 18.04.2017.
 */
public class SearchRecordsListener implements ActionListener {

    private final SearchRecordDialog searchRecordDialog;
    public SearchRecordsListener(JFrame ownerFrame, DataController dataController){
        searchRecordDialog = new SearchRecordDialog(ownerFrame, dataController);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        SearchAndDeleteView view = searchRecordDialog.getView();
        view.centerOnScreen();
        view.setVisible(true);
        view.clearAllPanelsTextFields();
        view.hideRadioButtons();
        view.setPanelsVisibility();
    }
}
