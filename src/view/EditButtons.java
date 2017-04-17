package view;

import controller.DataController;
import view.dialogs.AddRecordDialog;
import view.dialogs.DeleteRecordDialog;
import view.dialogs.SearchAndDeleteView;
import view.dialogs.SearchRecordDialog;
import view.listeners.AddRecordListener;
import view.listeners.DeleteRecordListener;
import view.listeners.SearchRecordsListener;

import javax.swing.*;

/**
 * Created by Batbara on 01.04.2017.
 */
class EditButtons {
   private final JButton addRecord;
   private final JButton deleteRecord;
   private final JButton searchRecord;


    public EditButtons(JFrame ownerFrame, DataController dataController){
        addRecord = initButton("plus", "plushover", "pluspressed","Добавить");
        deleteRecord = initButton("minus", "minushover", "minuspressed",
                "Удалить");
        searchRecord = initButton("search", "searchhover", "searchpressed",
                "Найти");

        AddRecordListener addRecordListener = new AddRecordListener(ownerFrame, dataController);
        addRecord.addActionListener(addRecordListener);

        DeleteRecordListener deleteRecordListener = new DeleteRecordListener(ownerFrame, dataController);
        deleteRecord.addActionListener(deleteRecordListener);

        SearchRecordsListener searchRecordsListener = new SearchRecordsListener(ownerFrame, dataController);
        searchRecord.addActionListener(searchRecordsListener);


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

        button.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if (model.isRollover()) {
                button.setIcon(hoverImage);
            } else {
                button.setIcon(image);
            }
            if (model.isPressed())
                button.setIcon(pressedImage);
        });

        return button;
    }


    public JButton getAddRecord() {
        return addRecord;
    }

    public JButton getDeleteRecord() {
        return deleteRecord;
    }

    public JButton getSearchRecord() {
        return searchRecord;
    }
}
