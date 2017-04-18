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
        Map<String, JTextField> studentNameField = view.getStudentNameField();
        Map<String, JTextField> parentNameField = view.getParentNameField();
        Map<String, JTextField> workExperienceField = view.getWorkExperienceField();
        Map<String, JTextField> workingAddressField = view.getWorkingAddressField();
        this.dataController = dataController;
        addControlButtonsListeners();
    }
    private void addControlButtonsListeners (){
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

            switch (buttonKey){
                case "studentNameOption":
                    List<Student> result;


                        result = dataController.studentNameSearch(view.studentNameSearchData());
                        if(result.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Таких записей нет :(");
                            return;
                        }
                        JOptionPane.showMessageDialog(null, "Найдено записей: "+result.size());
                        showResult(result);



                    break;
                case "parentNameOrAddressOption":
                    result = dataController.parentNameOrAddressSearch(view.parentNameOrAddressSearchData());
                    if(result.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Таких записей нет :(");
                        return;
                    }
                    JOptionPane.showMessageDialog(null, "Найдено записей: "+result.size());
                    showResult(result);
                    break;
                case "parentExpOrAddressOption":
                    result = dataController.parentExpOrAddressSearch(view.parentExpOrAddressSearchData());
                    if(result.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Таких записей нет :(");
                        return;
                    }
                    JOptionPane.showMessageDialog(null, "Найдено записей: "+result.size());
                    showResult(result);
                    break;
                case "studentNameOrAddressOption":
                    result = dataController.studentNameOrAddressSearch(view.studentNameOrAddressSearchData());
                    if(result.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Таких записей нет :(");
                        return;
                    }
                    JOptionPane.showMessageDialog(null, "Найдено записей: "+result.size());
                    showResult(result);
                    break;
            }
        });

        view.getCancelButton().addActionListener(e -> {
            view.clearAllPanelsTextFields();
            setVisible(false);
        });
    }

    private void showResult(List<Student> result){
        //JDialog resultDialog = new JDialog(this, "Результат поиска", ModalityType.DOCUMENT_MODAL);;
        Paging resultTable = new Paging();
        JPanel holdingPanel = resultTable.getHoldingTable();
        resultTable.setRECORDS_ON_PAGE();
        List<TableRecord> resultedRecords = getListOfSearchedRows(result);

        for (TableRecord record : resultedRecords)
            resultTable.addRecordToTable(record);
        resultTable.showCurrentPage();

        view.hideComponents();
        this.add(holdingPanel, BorderLayout.CENTER);
        //okButton.

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
