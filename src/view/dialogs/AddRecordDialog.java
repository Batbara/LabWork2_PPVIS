package view.dialogs;

import controller.DataController;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;

/**
 * Created by student on 31.03.2017.
 */
public class AddRecordDialog extends JDialog{

    private JTextField []studentNameField;
    private JTextField []parentNameField;
    private JTextField []workingAddressField;
    private JTextField []workExperienceField;

    private JButton okButton;
    private JButton cancelButton;
    private DataController dataController;

    public AddRecordDialog(JFrame ownerFrame, DataController dataController){
        super(ownerFrame,  "Добавить запись", Dialog.ModalityType.DOCUMENT_MODAL);
        this.dataController = dataController;

        initDialog();
        initTextFields();
        initButtons();

        fillWindowWithTextFieldPanels();
        fillWindowWithButtons();


        this.addWindowListener( new WindowAdapter() {
            public void windowOpened( WindowEvent e ){
                studentNameField[0].requestFocus();
            }
        });

        setButtonListeners();
    }
    private void initDialog(){
        final int DIALOG_WIDTH = 520;
        final int DIALOG_HEIGHT = 255;

        this.setSize(new Dimension(DIALOG_WIDTH,DIALOG_HEIGHT));
        this.setPreferredSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
        this.setMaximumSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));

        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        centerOnScreen();
    }
    private void initTextFields(){

        studentNameField = new JTextField[3];
        parentNameField = new JTextField[3];
        workingAddressField = new JTextField[3];
        workExperienceField = new JTextField[3];
        
        for(int iterator=0; iterator<studentNameField.length; iterator++) {
            studentNameField[iterator] = new JTextField(10);
        }

        for(int iterator=0; iterator<parentNameField.length; iterator++) {
            parentNameField[iterator] = new JTextField(10);
        }

        for(int iterator=0; iterator<workingAddressField.length; iterator++)
            workingAddressField[iterator] = new JTextField(10);

        for(int iterator=0; iterator<workExperienceField.length; iterator++)
            workExperienceField[iterator] = new JTextField(10);;
    }
    private void initButtons () {
        okButton = new JButton("Добавить");
        cancelButton = new JButton("Отмена");
    }
    private JPanel makePanelContainer(String panelTitle, JTextField []dataFields, String [] textLabels){
        JPanel panelContainer = new JPanel();
        panelContainer.setBorder(BorderFactory.createTitledBorder
                (BorderFactory.createLineBorder(Color.lightGray), panelTitle));
        int numOfFields = dataFields.length;

        panelContainer.setLayout(new GridLayout(numOfFields, 2));
        for (int iterator = 0; iterator<numOfFields; iterator++){
            JLabel textLabel = new JLabel(textLabels[iterator]);
            panelContainer.add(textLabel);
            panelContainer.add(dataFields[iterator]);
        }
        return panelContainer;
    }

    private void addToDialogFrame(Component componentToAdd) {
        this.add(componentToAdd);
    }
    private void clearTextFields(){
        for(int iterator=0; iterator<studentNameField.length; iterator++)
         studentNameField[iterator].setText(null);

        for(int iterator=0; iterator<parentNameField.length; iterator++)
         parentNameField[iterator].setText(null);

        for(int iterator=0; iterator<workingAddressField.length; iterator++)
         workingAddressField[iterator].setText(null);


        for(int iterator=0; iterator<workExperienceField.length; iterator++)
         workExperienceField[iterator].setText(null);
    }
    public void centerOnScreen() {
        final int width = this.getWidth();
        final int height = this.getHeight();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width / 2) - (width / 2);
        int y = (screenSize.height / 2) - (height / 2);

        this.setLocation(x, y);
    }
    private void fillWindowWithTextFieldPanels(){
        String []nameLabels = {"Фамилия", "Имя", "Отчество"};
        String []addressLabels = {"Город", "Улица", "Номер дома"};
        String []workExperienceLabels = {"Должность", "Годы работы", "Месяцы работы"};

        JPanel studentNamePanel = makePanelContainer("ФИО студента", studentNameField, nameLabels );
        JPanel parentNamePanel = makePanelContainer("ФИО родителя", parentNameField, nameLabels);
        JPanel addressPanel = makePanelContainer("Адрес работы", workingAddressField, addressLabels);
        JPanel workExperiencePanel = makePanelContainer("Должность и стаж", workExperienceField, workExperienceLabels );

        addToDialogFrame(studentNamePanel);
        addToDialogFrame(parentNamePanel);
        addToDialogFrame(addressPanel);
        addToDialogFrame(workExperiencePanel);
    }
    private void fillWindowWithButtons(){
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);

        addToDialogFrame(buttonsPanel);
    }
    public String[] getTextFromField (JTextField []dataFields){
        int numberOfFields = dataFields.length;
        String []textFromFields = new String[numberOfFields];
        for(int iterator=0; iterator<numberOfFields; iterator++){
            textFromFields[iterator] = dataFields[iterator].getText();
        }
        return textFromFields;
    }
    private void setButtonListeners(){
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String []studentNameData = getTextFromField(studentNameField);
                PersonName studentName = new PersonName(studentNameData[0], studentNameData[1], studentNameData[2]);

                String []parentNameData = getTextFromField(parentNameField);
                PersonName parentName = new PersonName(parentNameData[0], parentNameData[1], parentNameData[2]);

                String []workingAddressData = getTextFromField(workingAddressField);
                ParentWorkAddress workAddress = new ParentWorkAddress(workingAddressData[0], workingAddressData[1],
                        workingAddressData[2]);

                String []workExperienceData = getTextFromField(workExperienceField);
                ParentJobPosition jobPosition = new ParentJobPosition(workExperienceData[0]);

                ParentWorkExperience workingExperience = new ParentWorkExperience(workExperienceData[1],
                        workExperienceData[2]);


                Student newStudent = new Student(studentName, parentName, workAddress, jobPosition,workingExperience);
                dataController.addStudentData(newStudent);

                clearTextFields();
                setVisible(false);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTextFields();
                setVisible(false);
            }
        });
    }

}
