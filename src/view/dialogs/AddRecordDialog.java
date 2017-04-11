package view.dialogs;

import controller.DataController;
import view.TableRecord;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.util.Hashtable;

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

//        createFieldPanel("ФИО студента:", studentNameField,73);
//        createFieldPanel("ФИО родителя:", parentNameField,73);
//        createFieldPanel("Адрес работы родителя:", workingAddressField, 19);
//        createFieldPanel("Должность родителя:", jobPositionField, 38);
//        createFieldPanel("Стаж работы родителя:", workingYearsField,25);
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


        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);
//        addToDialogFrame(okButton);
//        addToDialogFrame(cancelButton);
        addToDialogFrame(buttonsPanel);

        this.addWindowListener( new WindowAdapter() {
            public void windowOpened( WindowEvent e ){
                studentNameField[0].requestFocus();
            }
        });

        setOkButtonListener();
    }
    public void initDialog(JFrame owner){
        final int DIALOG_WIDTH = 520;
        final int DIALOG_HEIGHT = 255;

        this.setSize(new Dimension(DIALOG_WIDTH,DIALOG_HEIGHT));
        this.setPreferredSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
        this.setMaximumSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));

        this.setLayout(new FlowLayout(FlowLayout.CENTER));
       // this.setLayout(new GridLayout(3,2));
       // this.setLayout(new BoxLayout(this, Box.));
        centerOnScreen();
    }
    public void initTextFields(){

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
    public void initButtons () {
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
            System.out.println(textLabels[iterator]);
            JLabel textLabel = new JLabel(textLabels[iterator]);
            panelContainer.add(textLabel);
            panelContainer.add(dataFields[iterator]);
        }
        return panelContainer;
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
    public void setOkButtonListener(){
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               /*  String studentName = studentNameField.getText();
                 String parentName = parentNameField.getText();
                 String workingAddress = workingAddressField.getText();
                 String jobPosition = jobPositionField.getText();
                 Double workingYears = Double.parseDouble(workExperience.getText());

               //  Student newStudent = new Student()

                TableRecord rowToAdd = new TableRecord(studentName, parentName, workingAddress,
                        jobPosition, workingYears);
                dataController.addRowToTable(rowToAdd);
               // dataController.addStudentData(Student);*/
            }
        });
    }

}
