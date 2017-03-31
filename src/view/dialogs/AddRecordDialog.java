package view.dialogs;

import view.View;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;

/**
 * Created by student on 31.03.2017.
 */
public class AddRecordDialog {

    private JDialog mainAddingDialog;


    private JTextField studentNameField;
    private JTextField parentNameField;
    private JTextField workingAddressField;
    private JTextField jobPositionField;
    private JTextField workingYearsField;

    private JButton addingButton;
    private JButton cancelButton;

    public AddRecordDialog(JFrame ownerFrame){

        initDialog(ownerFrame);
        initTextFields();
        addToDialogFrame();
        initButtons();
    }
    public void initDialog(JFrame owner){
        final int DIALOG_WIDTH = 150;
        final int DIALOG_HEIGHT = 300;

        mainAddingDialog = new JDialog(owner, "Добавить запись...");
        mainAddingDialog.setSize(new Dimension(DIALOG_WIDTH,DIALOG_HEIGHT));
        mainAddingDialog.setPreferredSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
        mainAddingDialog.setMaximumSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));

        mainAddingDialog.setLayout(new FlowLayout());
    }
    public void initTextFields(){
        final int  FIELD_SIZE = 30;
        studentNameField = new JTextField(FIELD_SIZE);
        parentNameField = new JTextField(FIELD_SIZE);
        workingAddressField = new JTextField(FIELD_SIZE);
        jobPositionField = new JTextField(FIELD_SIZE);
        workingYearsField = new JTextField(FIELD_SIZE);
    }
    public void initButtons () {
        addingButton = new JButton("Добавить");
        cancelButton = new JButton("Отмена");
    }
    public void addToDialogFrame() {
        mainAddingDialog.add(new JLabel("ФИО студента"));
        mainAddingDialog.add(studentNameField);
    }
}
