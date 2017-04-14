package view.dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Created by Batbara on 13.04.2017.
 */
public class SearchAndDeleteView extends JDialog {
    ButtonGroup groupRadioButtons;

    Hashtable<String, JRadioButton> optionButtons;
    Hashtable<String, JPanel> optionPanels;

    private JTextField []studentNameField;
    private JTextField []parentNameField;
    private JTextField []workingAddressField;
    private JTextField []workExperienceField;

    JButton okButton;
    JButton cancelButton;

    public SearchAndDeleteView(String dialogTitle, String captionTitle, JFrame ownerFrame){
        super(ownerFrame, dialogTitle, Dialog.ModalityType.DOCUMENT_MODAL);

        initDialogWindow();
        initOptionButtons();
        initTextFields();
        initControlButtons();

        addButtonsToGroup();
        addButtonsToDialogWindow(captionTitle);

        createOptionPanels();
        addPanelsToFrame();
        fillWindowWithButtons();
        addOptionListeners();
        addControlButtonsListeners();

    }
    private void initDialogWindow(){
        final int DIALOG_WIDTH = 1020;
        final int DIALOG_HEIGHT = 255;

        this.setSize(new Dimension(DIALOG_WIDTH,DIALOG_HEIGHT));
        this.setPreferredSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
        this.setMaximumSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));

        this.setLayout(new FlowLayout());
        this.pack();
        this.setResizable(false);
        centerOnScreen();
    }
    public void centerOnScreen() {
        final int width = this.getWidth();
        final int height = this.getHeight();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width / 2) - (width / 2);
        int y = (screenSize.height / 2) - (height / 2);

        this.setLocation(x, y);
    }
    private void initTextFields(){

        studentNameField = new JTextField[3];
        parentNameField = new JTextField[3];
        workingAddressField = new JTextField[3];
        workExperienceField = new JTextField[4];

        for(int iterator=0; iterator<studentNameField.length; iterator++) {
            studentNameField[iterator] = new JTextField(10);
        }

        for(int iterator=0; iterator<parentNameField.length; iterator++) {
            parentNameField[iterator] = new JTextField(10);
        }

        for(int iterator=0; iterator<workingAddressField.length; iterator++)
            workingAddressField[iterator] = new JTextField(10);

        for(int iterator=0; iterator<workExperienceField.length; iterator++)
            workExperienceField[iterator] = new JTextField(5);
    }
    private void initOptionButtons(){
        optionButtons = new Hashtable<>();

        JRadioButton studentNameOption = new JRadioButton("ФИО студента");
        JRadioButton parentNameOrAddressOption = new JRadioButton("ФИО или место работы родителя");
        JRadioButton parentExpOrAddressOption = new JRadioButton("Стаж или место работы родителя");
        JRadioButton studentNameOrAddressOption = new JRadioButton("ФИО студента или место работы родителя");

        optionButtons.put("studentNameOption", studentNameOption);
        optionButtons.put("parentNameOrAddressOption", parentNameOrAddressOption);
        optionButtons.put("parentExpOrAddressOption", parentExpOrAddressOption);
        optionButtons.put("studentNameOrAddressOption", studentNameOrAddressOption);
    }
    private void initControlButtons () {
        okButton = new JButton("Добавить");
        cancelButton = new JButton("Отмена");
    }
    private void createOptionPanels(){
        optionPanels = new Hashtable<>();

        String []nameLabels = {"Фамилия", "Имя", "Отчество"};
        String []addressLabels = {"Город", "Улица", "Номер дома"};

        JPanel studentNamePanel = makePanelContainer("ФИО студента", studentNameField, nameLabels );
        JPanel parentNamePanel = makePanelContainer("ФИО родителя", parentNameField, nameLabels);
        JPanel addressPanel = makePanelContainer("Адрес работы", workingAddressField, addressLabels);
        JPanel workExperiencePanel = makePanelContainer("Cтаж", workExperienceField);

        optionPanels.put("studentNamePanel", studentNamePanel);
        optionPanels.put("parentNamePanel", parentNamePanel);
        optionPanels.put("addressPanel", addressPanel);
        optionPanels.put("workExperiencePanel", workExperiencePanel);

    }
    private void addButtonsToGroup(){
        groupRadioButtons = new ButtonGroup();
        Collection<JRadioButton> buttons = optionButtons.values();
        for(JRadioButton element : buttons){
            groupRadioButtons.add(element);
        }
    }
    private void addButtonsToDialogWindow(String labelTitle){
        JLabel captionLabel = new JLabel(labelTitle);

        //JPanel buttonsPanel =  new JPanel(new GridLayout(1,4));
        JPanel buttonsPanel =  new JPanel(new FlowLayout());
        Collection<JRadioButton> buttons = optionButtons.values();
        for (JRadioButton element : buttons){
            buttonsPanel.add(element);
        }
        addToDialogFrame(captionLabel);
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
    private void addPanelsToFrame (){
        Collection<JPanel> panels = optionPanels.values();
        for (JPanel element : panels){
            addToDialogFrame(element);
        }
        setPanelsVisibility(false);
    }
    private void fillWindowWithButtons(){
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);

        addToDialogFrame(buttonsPanel);
    }
    private void determinePanelToShow(String buttonKey){
        Enumeration<String> panelKeys = optionPanels.keys();
        clearAllTextFields();
        switch (buttonKey){
            case "studentNameOption":
                for (Enumeration<String> element = panelKeys; element.hasMoreElements();) {
                    String keyValue = element.nextElement();
                    JPanel panel= optionPanels.get(keyValue);
                    panel.setVisible(false);
                    if(keyValue.equals("studentNamePanel")){
                        panel.setVisible(true);
                    }
                }
                break;
            case "parentNameOrAddressOption":
                System.out.println("parent name or addr called");
                for (Enumeration<String> element = panelKeys; element.hasMoreElements();) {
                    String keyValue = element.nextElement();
                    JPanel panel= optionPanels.get(keyValue);
                    panel.setVisible(false);
                    if(keyValue.equals("parentNamePanel") || keyValue.equals("addressPanel")){
                        panel.setVisible(true);
                    }
                }
                break;
            case "parentExpOrAddressOption":
                System.out.println("parent exp or addr called");
                for (Enumeration<String> element = panelKeys; element.hasMoreElements();) {
                    String keyValue = element.nextElement();
                    JPanel panel= optionPanels.get(keyValue);
                    panel.setVisible(false);
                    if(keyValue.equals("addressPanel") || keyValue.equals("workExperiencePanel")){
                        panel.setVisible(true);
                    }
                }
                break;
            case "studentNameOrAddressOption":
                System.out.println("student name or addr called");
                System.out.println("parent exp or addr called");
                for (Enumeration<String> element = panelKeys; element.hasMoreElements();) {
                    String keyValue = element.nextElement();
                    JPanel panel= optionPanels.get(keyValue);
                    panel.setVisible(false);
                    if(keyValue.equals("addressPanel") || keyValue.equals("studentNamePanel")){
                        panel.setVisible(true);
                    }
                }
                break;
        }

    }

    private void addOptionListeners(){
        Enumeration<String> buttonKeys = optionButtons.keys();
        for (Enumeration<String> element = buttonKeys; element.hasMoreElements();) {
            String keyValue = element.nextElement();
            JRadioButton button = optionButtons.get(keyValue);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    determinePanelToShow(keyValue);
                }
            });
        }
    }
    private void addControlButtonsListeners (){
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAllTextFields();
                setVisible(false);
            }
        });
    }
    private void clearTextFields(JTextField []textFields){
        for(int iterator=0; iterator<textFields.length; iterator++)
            textFields[iterator].setText(null);
    }
    public void setPanelsVisibility(boolean flag){
        Collection<JPanel> panels = optionPanels.values();
        for (JPanel element : panels){
            element.setVisible(flag);
        }
    }
    public void clearAllTextFields(){
        for(int iterator=0; iterator<studentNameField.length; iterator++)
            studentNameField[iterator].setText(null);
        for(int iterator=0; iterator<parentNameField.length; iterator++)
            parentNameField[iterator].setText(null);
        for(int iterator=0; iterator<workingAddressField.length; iterator++)
            workingAddressField[iterator].setText(null);
        for(int iterator=0; iterator<workExperienceField.length; iterator++)
            workExperienceField[iterator].setText(null);
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
    private JPanel makePanelContainer(String panelTitle, JTextField []dataFields) {
        JPanel panelContainer = new JPanel();
        panelContainer.setBorder(BorderFactory.createTitledBorder
                (BorderFactory.createLineBorder(Color.lightGray), panelTitle));

        panelContainer.setLayout(new GridLayout(2, 3));
        panelContainer.add(new JLabel("От"));
        panelContainer.add(dataFields[0]);
        panelContainer.add(dataFields[1]);
        panelContainer.add(new JLabel("До"));
        panelContainer.add(dataFields[2]);
        panelContainer.add(dataFields[3]);

        return panelContainer;
    }
    public void noneSelected(){

        groupRadioButtons.clearSelection();

    }
    private void addToDialogFrame(Component componentToAdd){
        this.add(componentToAdd);
    }
}
