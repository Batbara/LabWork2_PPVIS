package view.dialogs;

import controller.TableController;
import view.TableRecord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;

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
    private TableController dialogController;

    public AddRecordDialog(JFrame ownerFrame, TableController tableController){
        super(ownerFrame,  "Добавить запись", Dialog.ModalityType.DOCUMENT_MODAL);
        dialogController = tableController;

        initDialog(ownerFrame);
        initTextFields();
        initButtons();

       cancelButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               clearTextFields();
               setVisible(false);
           }
       });

        createFieldPanel("ФИО студента:", studentNameField,73);
        createFieldPanel("ФИО родителя:", parentNameField,73);
        createFieldPanel("Адрес работы родителя:", workingAddressField, 19);
        createFieldPanel("Должность родителя:", jobPositionField, 38);
        createFieldPanel("Стаж работы родителя:", workingYearsField,25);


        addToDialogFrame(okButton);
        addToDialogFrame(cancelButton);

        this.addWindowListener( new WindowAdapter() {
            public void windowOpened( WindowEvent e ){
                studentNameField.requestFocus();
            }
        });

        setOkButtonListener();
    }
    public void initDialog(JFrame owner){
        final int DIALOG_WIDTH = 320;
        final int DIALOG_HEIGHT = 205;

        this.setSize(new Dimension(DIALOG_WIDTH,DIALOG_HEIGHT));
        this.setPreferredSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
        this.setMaximumSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));

        this.setLayout(new FlowLayout());
        centerOnScreen();
    }
    public void initTextFields(){

        studentNameField = new JTextField(10);
        parentNameField = new JTextField(10);
        workingAddressField = new JTextField(10);
        jobPositionField = new JTextField(10);
        workingYearsField = new JTextField(10);
    }
    public void initButtons () {
        okButton = new JButton("Добавить");
        cancelButton = new JButton("Отмена");
    }

    private void createFieldPanel(String labelTitle, JTextField fieldToAdd, int gap){
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.X_AXIS));
       // fieldPanel.setLayout(new BorderLayout(15,0));

        JLabel nameLabel = new JLabel(labelTitle);
        nameLabel.setLayout(new FlowLayout(FlowLayout.LEFT));
        nameLabel.setFont(new Font("Helvetica", Font.PLAIN, 13));

        fieldPanel.add(nameLabel, Component.LEFT_ALIGNMENT);
        fieldPanel.add(Box.createRigidArea(new Dimension(gap, 20)));
        fieldPanel.add(fieldToAdd, Component.RIGHT_ALIGNMENT);

        addToDialogFrame(fieldPanel);

    }
    public void addToDialogFrame(Component componentToAdd) {
        this.add(componentToAdd);
    }
    private void clearTextFields(){
         studentNameField.setText(null);
         parentNameField.setText(null);
         workingAddressField.setText(null);
         jobPositionField.setText(null);
         workingYearsField.setText(null);
    }
    public void centerOnScreen() {
        final int width = this.getWidth();
        final int height = this.getHeight();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width / 2) - (width / 2);
        int y = (screenSize.height / 2) - (height / 2);

        this.setLocation(x, y);
    }
    public void setOkButtonListener(){
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 String studentName = studentNameField.getText();
                 String parentName = parentNameField.getText();
                 String workingAddress = workingAddressField.getText();
                 String jobPosition = jobPositionField.getText();
                 Double workingYears = Double.parseDouble(workingYearsField.getText());
                TableRecord rowToAdd = new TableRecord(studentName, parentName, workingAddress,
                        jobPosition, workingYears);
                dialogController.addStudentData(rowToAdd);
            }
        });
    }
}
