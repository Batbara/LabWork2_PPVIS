package view.dialogs;

import controller.DataController;
import model.Student;
import view.Paging;
import view.TableRecord;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Batbara on 16.04.2017.
 */
public class SearchRecordDialog extends JDialog{
    private final SearchAndDeleteView view;
    private final JButton okButton;
    private final ButtonGroup buttonGroup;

    private final DataController dataController;

    public SearchRecordDialog(JFrame ownerFrame, DataController dataController){
        super(ownerFrame,  "Поиск записей", Dialog.ModalityType.DOCUMENT_MODAL);
        view = new SearchAndDeleteView("Поиск записей", ownerFrame);
        okButton = view.getOkButton();
        buttonGroup = view.getGroupRadioButtons();
        this.dataController = dataController;
        addControlButtonsListeners();
    }
    public void addControlButtonsListeners (){
        okButton.addActionListener(e -> {
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

            List<Student> result = new ArrayList<>();
            Student studentToSearch;
            Map <String,JTextField> studentNameField = view.getStudentNameField();
            Map <String,JTextField> parentNameField = view.getParentNameField();
            Map <String,JTextField> addressField = view.getWorkingAddressField();
            Set<String> allKeys;
            switch (buttonKey){
                case "studentNameOption":
                    studentToSearch = view.searchData(studentNameField);
                    result = dataController.studentsSearch(studentToSearch, studentNameField.keySet());
                    break;
                case "parentNameOrAddressOption":
                    studentToSearch = view.searchData(parentNameField, addressField);
                    Set<String> keys = parentNameField.keySet();
                    allKeys = new HashSet<>();
                    allKeys.addAll(keys);
                    allKeys.addAll(addressField.keySet());
                    result = dataController.studentsSearch(studentToSearch, allKeys);
                    break;
                case "parentExpOrAddressOption":
                    studentToSearch = view.searchData(addressField);
                    Set<String> addressKeys = addressField.keySet();
                    Double minExp = view.calculateExp("fromYears", "fromMonths");
                    Double maxExp = view.calculateExp("toYears", "toMonths");
                    result = dataController.studentsSearch(studentToSearch, addressKeys, minExp, maxExp);
                    break;
                case "studentNameOrAddressOption":
                    studentToSearch = view.searchData(studentNameField, addressField);
                    allKeys = new HashSet<>();
                    allKeys.addAll(studentNameField.keySet());
                    allKeys.addAll(addressField.keySet());
                    result = dataController.studentsSearch(studentToSearch, allKeys);
                    break;
            }
            if(result.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Таких записей нет :(");
                return;
            }
            view.getContentPane().removeAll();
            Paging resultTable = searchTableResult(result);
            JPanel buttonsPanel = resultTable.makeControlButtonsPanel();
            view.add(resultTable.getHoldingTable(), BorderLayout.CENTER);
            view.add(buttonsPanel, BorderLayout.PAGE_END);

            resultTable.showCurrentPage();
            view.revalidate();
        });

    }

    private Paging searchTableResult(List<Student> result){
        Paging resultTable =  new Paging(3);
        List<TableRecord> resultedRecords = getListOfSearchedRows(result);

        for (TableRecord record : resultedRecords)
            resultTable.addRecordToTable(record);
        resultTable.getHoldingTable().setVisible(true);

        return resultTable;
    }
    private List<TableRecord > getListOfSearchedRows(List <Student> studentsResult){
        List<TableRecord> rowsResult = new ArrayList<>();
        for (Student student : studentsResult){
            TableRecord studentRecord = new TableRecord(student);
            rowsResult.add(studentRecord);
        }
        return rowsResult;

    }

    public SearchAndDeleteView getView() {
        return view;
    }
}
