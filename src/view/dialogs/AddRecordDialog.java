package view.dialogs;

import view.View;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by student on 31.03.2017.
 */
public class AddRecordDialog extends JDialog{




    private JTextField studentNameField;
    private JTextField parentNameField;
    private JTextField workingAddressField;
    private JTextField jobPositionField;
    private JTextField workingYearsField;

    private JButton okButton;
    private JButton cancelButton;

    public AddRecordDialog(JFrame ownerFrame){
        super(ownerFrame,  "Добавить запись...");
        initDialog(ownerFrame);
        initTextFields();
        addToDialogFrame();
        initButtons();

       cancelButton.addActionListener(new ActionListener() {
                                       @Override
                                       public void actionPerformed(ActionEvent e) {
                                           setVisible(false);
                                       }
                                   }
        );
    }
    public void initDialog(JFrame owner){
        final int DIALOG_WIDTH = 150;
        final int DIALOG_HEIGHT = 300;

        this.setSize(new Dimension(DIALOG_WIDTH,DIALOG_HEIGHT));
        this.setPreferredSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
        this.setMaximumSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));

        this.setLayout(new FlowLayout());
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
        okButton = new JButton("Добавить");
        cancelButton = new JButton("Отмена");
    }
    public void addToDialogFrame() {
        this.add(new JLabel("ФИО студента"));
        this.add(studentNameField);
    }
}
