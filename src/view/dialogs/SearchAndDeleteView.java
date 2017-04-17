package view.dialogs;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Batbara on 13.04.2017.
 */
public class SearchAndDeleteView extends JDialog {
    private ButtonGroup groupRadioButtons;

    private Map<String, JRadioButton> optionButtons;
    private Map<String, JPanel> optionPanels;

    private Map <String, JTextField> studentNameField;
    private Map <String, JTextField> parentNameField;
    private Map <String, JTextField> workingAddressField;
    private Map <String, JTextField> workExperienceField;

    private JButton okButton;
    private JButton cancelButton;
    private JPanel buttonsPanel;

    private String status;

    public SearchAndDeleteView(String dialogTitle, JFrame ownerFrame){
        super(ownerFrame, dialogTitle, Dialog.ModalityType.DOCUMENT_MODAL);

        status = "";
        initDialogWindow();
        initOptionButtons();
        initTextFields();
        initControlButtons();

        addButtonsToGroup();
        addButtonsToDialogWindow();

        createOptionPanels();
        addPanelsToFrame();
        fillWindowWithButtons();
        firePressedButtonEvent();
        addControlButtonsListeners();

    }
    private void initDialogWindow(){
        final int DIALOG_WIDTH = 1020;
        final int DIALOG_HEIGHT = 195;

        this.setSize(new Dimension(DIALOG_WIDTH,DIALOG_HEIGHT));
        this.setPreferredSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
        this.setMaximumSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));

       // this.setLayout(new FlowLayout());
        this.setLayout(new BorderLayout());
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

        studentNameField = new LinkedHashMap<>();
        parentNameField = new LinkedHashMap<>();
        workingAddressField = new LinkedHashMap<>();
        workExperienceField = new LinkedHashMap<>();

        String []nameKeys = {"Фамилия", "Имя", "Отчество"};
        String []addressKeys = {"Город", "Улица", "Номер дома"};
        String []experienceKeys = {"fromYears", "fromMonths", "toYears", "toMonths"};

        for (String nameKey1 : nameKeys) {
            studentNameField.put(nameKey1, new JTextField(10));
        }
        for (String nameKey : nameKeys) {
            parentNameField.put(nameKey, new JTextField(10));
        }
        for (String addressKey : addressKeys) {
            workingAddressField.put(addressKey, new JTextField(10));
        }
        for (String experienceKey : experienceKeys) {
            workExperienceField.put(experienceKey, new JTextField(5));
        }
    }
    private void initOptionButtons(){
        optionButtons = new LinkedHashMap<>();

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
        okButton = new JButton("ОК");
        cancelButton = new JButton("Отмена");
    }
    private void createOptionPanels(){
        optionPanels = new LinkedHashMap<>();


        JPanel studentNamePanel = makePanelContainer("ФИО студента", studentNameField);
        JPanel parentNamePanel = makePanelContainer("ФИО родителя", parentNameField);
        JPanel addressPanel = makePanelContainer("Адрес работы", workingAddressField);
        JPanel workExperiencePanel = makeExperiencePanel();


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
    private void addButtonsToDialogWindow(){
        JPanel buttonsPanel =  new JPanel(new FlowLayout());
        Collection<JRadioButton> buttons = optionButtons.values();
        for (JRadioButton element : buttons){
            buttonsPanel.add(element);
        }
        this.add(buttonsPanel, BorderLayout.NORTH);
    }

    private void addPanelsToFrame (){
        Collection<JPanel> panels = optionPanels.values();
        JPanel holder = new JPanel(new FlowLayout());
        for (JPanel element : panels){
            holder.add(element);
        }
        this.add(holder);
        setPanelsVisibility();
    }
    private void fillWindowWithButtons(){
        buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);
        buttonsPanel.setVisible(false);
        this.add(buttonsPanel, BorderLayout.PAGE_END);
    }
    private void determinePanelToShow(String buttonKey){
        Set<String> panelKeys = optionPanels.keySet();
        clearAllPanelsTextFields();
        switch (buttonKey){
            case "studentNameOption":
                for (String keyValue : panelKeys) {
                    JPanel panel= optionPanels.get(keyValue);
                    panel.setVisible(false);
                    if(keyValue.equals("studentNamePanel")){
                        panel.setVisible(true);
                    }

                }
                break;
            case "parentNameOrAddressOption":
                System.out.println("parent name or addr called");
                for (String keyValue : panelKeys) {
                    JPanel panel= optionPanels.get(keyValue);
                    panel.setVisible(false);
                    if(keyValue.equals("parentNamePanel") || keyValue.equals("addressPanel")){
                        panel.setVisible(true);
                    }
                }
                break;
            case "parentExpOrAddressOption":
                System.out.println("parent exp or addr called");
                for(String keyValue : panelKeys) {
                    JPanel panel= optionPanels.get(keyValue);
                    panel.setVisible(false);
                    if(keyValue.equals("addressPanel") || keyValue.equals("workExperiencePanel")){
                        panel.setVisible(true);
                    }
                }
                break;
            case "studentNameOrAddressOption":
                System.out.println("student name or addr called");
                for (String keyValue : panelKeys) {
                    JPanel panel= optionPanels.get(keyValue);
                    panel.setVisible(false);
                    if(keyValue.equals("addressPanel") || keyValue.equals("studentNamePanel")){
                        panel.setVisible(true);
                    }
                }
                break;
        }

    }

    private void firePressedButtonEvent(){
        Set<String> buttonKeys = optionButtons.keySet();

        for (String keyValue: buttonKeys){
            JRadioButton button = optionButtons.get(keyValue);
            button.addActionListener(e -> {
                determinePanelToShow(keyValue);
                buttonsPanel.setVisible(true);
            });
        }
    }
    private void addControlButtonsListeners (){
        okButton.addActionListener(e -> {
            String buttonKey="";
            Set<String> allButtonsKeys = optionButtons.keySet();
            JRadioButton selectedButton = new JRadioButton();
            for (Enumeration<AbstractButton> buttons = groupRadioButtons.getElements(); buttons.hasMoreElements();) {
                AbstractButton button = buttons.nextElement();
                if (button.isSelected()) {
                    selectedButton = (JRadioButton)button;
                }
            }
            for(String checkingButtonKey : allButtonsKeys) {
               if(Objects.equals(selectedButton, optionButtons.get(checkingButtonKey)))
                   buttonKey = checkingButtonKey;
            }
            System.out.println("pressed button key is"+buttonKey);

            switch (buttonKey){
                case "studentNameOption":
                    status = "studentNameOption";
                    break;
                case "parentNameOrAddressOption":
                    status = "parentNameOrAddressOption";
                    break;
                case "parentExpOrAddressOption":
                    status = "parentExpOrAddressOption";
                    break;
                case "studentNameOrAddressOption":
                   status = "studentNameOrAddressOption";
                    break;
            }
        });

        cancelButton.addActionListener(e -> {
            clearAllPanelsTextFields();
            setVisible(false);
        });
    }

    public void setPanelsVisibility(){
        Collection<JPanel> panels = optionPanels.values();
        for (JPanel element : panels){
            element.setVisible(false);
        }
    }
    private void clearPanelTextFields(Map<String, JTextField> textFields){
        Set<String> fieldKeys = textFields.keySet();
        for (String key : fieldKeys){
            textFields.get(key).setText(null);
        }
    }
    public void clearAllPanelsTextFields(){
        clearPanelTextFields(studentNameField);
        clearPanelTextFields(parentNameField);
        clearPanelTextFields(workExperienceField);
        clearPanelTextFields(workingAddressField);
    }

    private JPanel makePanelContainer(String panelTitle, Map<String, JTextField> dataFields){
        JPanel panelContainer = new JPanel();
        Set<String> fieldKeys = dataFields.keySet();
        panelContainer.setBorder(BorderFactory.createTitledBorder
                (BorderFactory.createLineBorder(Color.lightGray), panelTitle));
        int numOfFields = fieldKeys.size();

        panelContainer.setLayout(new GridLayout(numOfFields, 2));
        for (String key : fieldKeys){
            JLabel keyLabel = new JLabel(key);
            panelContainer.add(keyLabel);
            panelContainer.add(dataFields.get(key));
        }
        return panelContainer;
    }

    private JPanel makeExperiencePanel(){
        JPanel panelContainer = new JPanel();
        String panelTitle = "Стаж";
        panelContainer.setBorder(BorderFactory.createTitledBorder
                (BorderFactory.createLineBorder(Color.lightGray), panelTitle));

        panelContainer.setLayout(new GridLayout(2, 3));
        panelContainer.add(new JLabel("От"));
        panelContainer.add(workExperienceField.get("fromYears"));
        panelContainer.add(workExperienceField.get("fromMonths"));
        panelContainer.add(new JLabel("До"));
        panelContainer.add(workExperienceField.get("toYears"));
        panelContainer.add(workExperienceField.get("toMonths"));
        return panelContainer;
    }
    public void hideComponents(){
        groupRadioButtons.clearSelection();
        buttonsPanel.setVisible(false);
    }
    public Map<String, String> studentNameSearchData(){
        Set<String> studentNameKeys = studentNameField.keySet();
        Map<String, String> dataToSearch = new HashMap<>();
        for (String key : studentNameKeys){
            dataToSearch.put(key, studentNameField.get(key).getText());
        }
        return dataToSearch;
    }
    public List< Map<String, String> > parentNameOrAddressSearchData(){
        Set<String> parentNameKeys = parentNameField.keySet();
        Set<String> addressKeys = workingAddressField.keySet();
        //allKeys.addAll(addressKeys);
        Map<String, String> nameDataToSearch = new HashMap<>();
        Map<String, String> addressDataToSearch = new HashMap<>();
        for (String key : parentNameKeys){
            nameDataToSearch.put(key, parentNameField.get(key).getText());
        }
        for (String key : addressKeys){
            addressDataToSearch.put(key, workingAddressField.get(key).getText());
        }
        List< Map<String, String> > dataToSearch = new ArrayList<>();
        dataToSearch.add(nameDataToSearch);
        dataToSearch.add(addressDataToSearch);
        return dataToSearch;
    }
    public List< Map<String, String> > parentExpOrAddressSearchData(){
        Set<String> parentExpKeys = workExperienceField.keySet();
        Set<String> addressKeys = workingAddressField.keySet();
        //allKeys.addAll(addressKeys);
        Map<String, String> expDataToSearch = new HashMap<>();
        Map<String, String> addressDataToSearch = new HashMap<>();
        for (String key : parentExpKeys){
            expDataToSearch.put(key, workExperienceField.get(key).getText());
        }
        for (String key : addressKeys){
            addressDataToSearch.put(key, workingAddressField.get(key).getText());
        }
        List< Map<String, String> > dataToSearch = new ArrayList<>();
        dataToSearch.add(expDataToSearch);
        dataToSearch.add(addressDataToSearch);
        return dataToSearch;
    }
    public List< Map<String, String> > studentNameOrAddressSearchData(){
        Set<String> studentNameKeys = studentNameField.keySet();

        Set<String> addressKeys = workingAddressField.keySet();
        //allKeys.addAll(addressKeys);
        Map<String, String> nameDataToSearch = new HashMap<>();
        Map<String, String> addressDataToSearch = new HashMap<>();
        for (String key : studentNameKeys){
            nameDataToSearch.put(key, studentNameField.get(key).getText());
        }
        for (String key : addressKeys){
            addressDataToSearch.put(key, workingAddressField.get(key).getText());
        }
        List< Map<String, String> > dataToSearch = new ArrayList<>();
        dataToSearch.add(nameDataToSearch);
        dataToSearch.add(addressDataToSearch);
        return dataToSearch;
    }
    public Map<String, JTextField> getStudentNameField() {
        return studentNameField;
    }

    public Map<String, JTextField> getParentNameField() {
        return parentNameField;
    }

    public Map<String, JTextField> getWorkingAddressField() {
        return workingAddressField;
    }

    public Map<String, JTextField> getWorkExperienceField() {
        return workExperienceField;
    }

    public JButton getOkButton() {
        return okButton;
    }

    public ButtonGroup getGroupRadioButtons() {
        return groupRadioButtons;
    }

    public Map<String, JRadioButton> getOptionButtons() {
        return optionButtons;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }
}
