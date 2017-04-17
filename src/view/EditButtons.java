package view;

import controller.DataController;
import view.dialogs.AddRecordDialog;
import view.dialogs.DeleteRecordDialog;
import view.dialogs.SearchAndDeleteView;
import view.dialogs.SearchRecordDialog;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Batbara on 01.04.2017.
 */
public class EditButtons {
   public JButton addRecord;
   public JButton deleteRecord;
   public JButton searchRecord;

    private AddRecordDialog addRecordDialog;
    private DeleteRecordDialog deleteRecordDialog;
    private SearchRecordDialog searchRecordDialog;

    public EditButtons(JFrame ownerFrame, DataController dataController){
        addRecord = initButton("plus", "plushover", "pluspressed","Добавить");
        deleteRecord = initButton("minus", "minushover", "minuspressed",
                "Удалить");
        searchRecord = initButton("search", "searchhover", "searchpressed",
                "Найти");

        addRecordDialog = new AddRecordDialog(ownerFrame, dataController);
        deleteRecordDialog = new DeleteRecordDialog("Удалить записи", ownerFrame, dataController);
        searchRecordDialog = new SearchRecordDialog("Поиск записей", ownerFrame, dataController);
        callAddDialogFromButton(dataController);

    }
    private JButton initButton (String fileName, String hoverFileName, String pressedFileName,
                                String tipText){

        String mainImgLocation = "/img/"+fileName+".png";
        String hoverImgLocation = "/img/"+hoverFileName+".png";
        String pressedImgLocation = "/img/"+pressedFileName+".png";

        ImageIcon image = new ImageIcon(this.getClass().getResource(mainImgLocation));
        ImageIcon hoverImage = new ImageIcon(this.getClass().getResource(hoverImgLocation));
        ImageIcon pressedImage = new ImageIcon(this.getClass().getResource(pressedImgLocation));


        JButton button = new JButton();
        button.setIcon(image);
        button.setBorder(null);
        // button.setMargin(new Insets(0, 0, 0, 0));
        button.setBackground(null);
        button.setContentAreaFilled(false);
        button.setToolTipText(tipText);

        button.getModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) e.getSource();
                if (model.isRollover()) {
                    button.setIcon(hoverImage);
                } else {
                    button.setIcon(image);
                }
                if (model.isPressed())
                    button.setIcon(pressedImage);
            }
        });

        return button;
    }
    public void callAddDialogFromButton (DataController dataController){
        addRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRecordDialog.centerOnScreen();
                addRecordDialog.setVisible(true);
            }
        });
        deleteRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchAndDeleteView view = deleteRecordDialog.getView();
                view.centerOnScreen();
                view.setVisible(true);
                view.clearAllPanelsTextFields();
                view.hideComponents();
                view.setPanelsVisibility(false);
            }
        });
        searchRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchAndDeleteView view = searchRecordDialog.getView();
                view.centerOnScreen();
                view.setVisible(true);
                view.clearAllPanelsTextFields();
                view.hideComponents();
                view.setPanelsVisibility(false);
            }
        });

    }
}
