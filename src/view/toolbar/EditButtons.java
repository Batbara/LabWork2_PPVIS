package view.toolbar;

import controller.TableController;
import view.dialogs.AddRecordDialog;

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
   public JButton findRecord;

    AddRecordDialog addRecordDialog;

    public EditButtons(JFrame ownerFrame, TableController tableController){
        addRecord = initButton("plus", "plushover", "pluspressed","Добавить");
        deleteRecord = initButton("minus", "minushover", "minuspressed",
                "Удалить");
        findRecord = initButton("search", "searchhover", "searchpressed",
                "Найти");

        addRecordDialog = new AddRecordDialog(ownerFrame, tableController);
        callAddDialogFromButton();

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
    public void callAddDialogFromButton (){
        addRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRecordDialog.centerOnScreen();
                addRecordDialog.setVisible(true);
            }
        });
    }
}
