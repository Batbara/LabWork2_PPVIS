package view.dialogs;

import controller.DataController;
import model.*;
import view.Paging;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

/**
 * Created by student on 31.03.2017.
 */
public class AddRecordDialog extends JDialog{

    //
    private Map<String, JTextField> studentFields;
    private final Student studentToAdd;
    private JButton okButton;
    private JButton cancelButton;
    private final DataController dataController;

    public AddRecordDialog(JFrame ownerFrame, DataController dataController){
        super(ownerFrame,  "Добавить запись", Dialog.ModalityType.DOCUMENT_MODAL);
        this.dataController = dataController;
        studentToAdd = new Student();

        initDialog();
        initTextFields();
        initButtons();

        fillWindowWithTextFieldPanels();
        fillWindowWithButtons();

        this.addWindowListener( new WindowAdapter() {
            public void windowOpened( WindowEvent e ){
                studentFields.get("Фамилия1").requestFocus();
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
        this.setResizable(false);
        centerOnScreen();
    }
    private void initTextFields(){

        studentFields = new LinkedHashMap<>();
        String []fieldKeys = {"Фамилия1", "Имя1", "Отчество1", "Фамилия", "Имя", "Отчество", "Город", "Улица", "Номер дома",
                "Должность", "Годы работы", "Месяцы работы"};
        for (String fieldKey : fieldKeys) {
            studentFields.put(fieldKey, new JTextField(10));
        }

    }
    private void initButtons () {
        okButton = new JButton("Добавить");
        cancelButton = new JButton("Отмена");
    }
    private JPanel makePanelContainer(String panelTitle, Set<String> fieldKeys){
        JPanel panelContainer = new JPanel();
        panelContainer.setBorder(BorderFactory.createTitledBorder
                (BorderFactory.createLineBorder(Color.lightGray), panelTitle));
        int numOfFields = fieldKeys.size();

        panelContainer.setLayout(new GridLayout(numOfFields, 2));
        for (String key: fieldKeys){
            JLabel textLabel;
            if(key.equals("Фамилия1") || key.equals("Имя1") || key.equals("Отчество1")){
                int stringLength = key.length();
                String changedKey = key.substring(0,stringLength-1);
                textLabel = new JLabel(changedKey);
            }
            else {
                textLabel = new JLabel(key);
            }
            panelContainer.add(textLabel);
            panelContainer.add(studentFields.get(key));
        }
        return panelContainer;
    }

    private void addToDialogFrame(Component componentToAdd) {
        this.add(componentToAdd);
    }
    private void clearTextFields(){
        Set<String> fieldKeys = studentFields.keySet();
        for (String key: fieldKeys){
            studentFields.get(key).setText(null);
        }

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

        Set<String> studentNameKeys = new LinkedHashSet<>();
        studentNameKeys.add("Фамилия1");
        studentNameKeys.add("Имя1");
        studentNameKeys.add("Отчество1");

        Set<String> nameKeys = new LinkedHashSet<>();
        nameKeys.add("Фамилия");
        nameKeys.add("Имя");
        nameKeys.add("Отчество");

        Set<String> addressKeys = new LinkedHashSet<>();
        addressKeys.add("Город");
        addressKeys.add("Улица");
        addressKeys.add("Номер дома");

        Set<String> workExpKeys = new LinkedHashSet<>();
        workExpKeys.add("Должность");
        workExpKeys.add("Годы работы");
        workExpKeys.add("Месяцы работы");

        JPanel studentNamePanel = makePanelContainer("ФИО студента", studentNameKeys);
        JPanel parentNamePanel = makePanelContainer("ФИО родителя", nameKeys);
        JPanel addressPanel = makePanelContainer("Адрес работы", addressKeys);
        JPanel workExperiencePanel = makePanelContainer("Должность и стаж", workExpKeys );

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
    private String getTextFromField(String fieldKey){
        return studentFields.get(fieldKey).getText();
    }
    private boolean isSafeToAdd(){
        List<String> dataResult = new ArrayList<>();
        dataResult.add(getTextFromField("Фамилия1"));
        dataResult.add(getTextFromField("Имя1"));
        dataResult.add(getTextFromField("Отчество1"));
        dataResult.add(getTextFromField("Фамилия"));
        dataResult.add(getTextFromField("Имя"));
        dataResult.add(getTextFromField("Отчество"));
        dataResult.add(getTextFromField("Город"));
        dataResult.add(getTextFromField("Улица"));
        dataResult.add(getTextFromField("Номер дома"));
        dataResult.add(getTextFromField("Должность"));
        dataResult.add(getTextFromField("Годы работы"));
        dataResult.add(getTextFromField("Месяцы работы"));

        for(String field : dataResult){
            if (field.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Заполните все поля!");
                return false;
            }
        }
        try {
            if( (Integer.parseInt(getTextFromField("Номер дома")) <0) ||
                    (Integer.parseInt(getTextFromField("Годы работы")) <0) ||
                    (Integer.parseInt(getTextFromField("Месяцы работы")) <0)
                    ){
                JOptionPane.showMessageDialog(this, "Введите корректные данные!");
                return false;
            }
        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Введите корректные данные!");
            return false;
        }
        return true;
    }
    private void setButtonListeners(){
        okButton.addActionListener(e -> {
            if(!isSafeToAdd()){
               return;
            }
            studentToAdd.setStudentSurname(getTextFromField("Фамилия1"));
            studentToAdd.setStudentName(getTextFromField("Имя1"));
            studentToAdd.setStudentFatherName(getTextFromField("Отчество1"));

            Parent parentToAdd = studentToAdd.getStudentParent();
            parentToAdd.setParentSurname(getTextFromField("Фамилия"));
            parentToAdd.setParentName(getTextFromField("Имя"));
            parentToAdd.setParentFatherName(getTextFromField("Отчество"));


            Worker parentWorker = parentToAdd.getWorkerData();

            parentWorker.setCityOfWork(getTextFromField("Город"));
            parentWorker.setStreetOfWork(getTextFromField("Улица"));
            parentWorker.setBuildingNumberOfWork(Integer.parseInt(getTextFromField("Номер дома")));
            parentWorker.setJobPosition(getTextFromField("Должность"));
            parentWorker.setWorkingYears(Integer.parseInt(getTextFromField("Годы работы")));
            parentWorker.setWorkingMonths(Integer.parseInt(getTextFromField("Месяцы работы")));

            parentToAdd.setWorkerData(parentWorker);
            studentToAdd.setStudentParent(parentToAdd);

            dataController.addStudentData(studentToAdd);

            Paging tableView = dataController.getPagedView();
            tableView.setCurrentPageNum(tableView.getNumberOfPages()-1);
            tableView.showCurrentPage();

            clearTextFields();
            setVisible(false);
        });

        cancelButton.addActionListener(e -> {
            clearTextFields();
            setVisible(false);
        });
    }

}
