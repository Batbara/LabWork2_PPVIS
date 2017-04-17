package view.dialogs;

import controller.DataController;
import model.Student;
import view.Paging;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by Batbara on 16.04.2017.
 */
public class SearchRecordDialog extends JDialog{
    private SearchAndDeleteView view;
    JButton okButton;
    ButtonGroup buttonGroup;
    private Map<String, JTextField> studentNameField;
    private Map <String, JTextField> parentNameField;
    private Map <String, JTextField> workingAddressField;
    private Map <String, JTextField> workExperienceField;
    
    private DataController dataController;

    public SearchRecordDialog(String dialogTitle,  JFrame ownerFrame, DataController dataController){
        super(ownerFrame,  "Поиск записей", Dialog.ModalityType.DOCUMENT_MODAL);
        view = new SearchAndDeleteView(dialogTitle, ownerFrame);
        okButton = view.getOkButton();
        buttonGroup = view.getGroupRadioButtons();
        studentNameField = view.getStudentNameField();
        parentNameField = view.getParentNameField();
        workExperienceField = view.getWorkExperienceField();
        workingAddressField = view.getWorkingAddressField();
        this.dataController = dataController;
        addControlButtonsListeners();
    }
    private void addControlButtonsListeners (){
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonKey="";
                Set<String> allButtonsKeys = view.getOptionButtons().keySet();
                JRadioButton selectedButton = new JRadioButton();
                for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
                    AbstractButton button = buttons.nextElement();
                    if (button.isSelected()) {
                        selectedButton = (JRadioButton)button;
                    }
                }
                for(String checkingButtonKey : allButtonsKeys) {
                    if(Objects.equals(selectedButton, view.getOptionButtons().get(checkingButtonKey)))
                        buttonKey = checkingButtonKey;
                }
                System.out.println("pressed button key is"+buttonKey);

                switch (buttonKey){
                    case "studentNameOption":
                        List<Student> result = new ArrayList<>();


                            result = dataController.studentNameSearch(studentNameSearchData());
                            if(result.isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Таких записей нет :(");
                                return;
                            }
                            JOptionPane.showMessageDialog(null, "Найдено записей: "+result.size());
                            createResultDialog(result).setVisible(true);



                        break;
                    case "parentNameOrAddressOption":
                        result = dataController.parentNameOrAddressSearch(parentNameOrAddressSearchData());
                        if(result.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Таких записей нет :(");
                            return;
                        }
                        JOptionPane.showMessageDialog(null, "Найдено записей: "+result.size());
                        createResultDialog(result).setVisible(true);
                        break;
                    case "parentExpOrAddressOption":
                        result = dataController.parentExpOrAddressSearch(parentExpOrAddressSearchData());
                        if(result.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Таких записей нет :(");
                            return;
                        }
                        JOptionPane.showMessageDialog(null, "Найдено записей: "+result.size());
                        createResultDialog(result).setVisible(true);
                        break;
                    case "studentNameOrAddressOption":
                        result = dataController.studentNameOrAddressSearch(studentNameOrAddressSearchData());
                        if(result.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Таких записей нет :(");
                            return;
                        }
                        JOptionPane.showMessageDialog(null, "Найдено записей: "+result.size());
                        createResultDialog(result).setVisible(true);
                        break;
                }
            }
        });

        view.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.clearAllPanelsTextFields();
                setVisible(false);
            }
        });
    }

    private JDialog createResultDialog(List<Student> result){
        JDialog resultDialog = new JDialog(this, "Результат поиска", ModalityType.DOCUMENT_MODAL);
        resultDialog.setSize(new Dimension(750, 205));
        resultDialog.setLayout(new BorderLayout());
        Paging resultTable = new Paging();
        JPanel holdingPanel = resultTable.getHoldingTable();
        resultTable.setRECORDS_ON_PAGE(3);
        List<Vector<String>> resultedRows = dataController.getListOfSearchedRows(result);

        for (Vector<String> row : resultedRows)
            resultTable.addRowToTable(row);
        resultTable.showCurrentPage();
        resultDialog.add(holdingPanel, BorderLayout.CENTER);
        resultDialog.add(resultTable.makeControlButtonsPanel(), BorderLayout.PAGE_END);
        return resultDialog;
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

    public SearchAndDeleteView getView() {
        return view;
    }
}
