package view.dialogs;

import controller.DataController;
import model.Student;
import view.Paging;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Batbara on 13.04.2017.
 */
public class DeleteRecordDialog extends JDialog{
    private final DataController dataController;
    private final JButton okButton;
    private final SearchAndDeleteView view;
    private final ButtonGroup buttonGroup;
    public DeleteRecordDialog(JFrame ownerFrame, DataController dataController){
        super(ownerFrame,  "Удаление записей", Dialog.ModalityType.DOCUMENT_MODAL);

        this.dataController = dataController;
        view = new SearchAndDeleteView("Удалить записи",ownerFrame);
        this.okButton = view.getOkButton();
        buttonGroup = view.getGroupRadioButtons();
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

            int studentsRemoved = dataController.removeStudents(result);
            if(result.isEmpty()) {
                JOptionPane.showMessageDialog(null, "А таких записей нет :)");
                return;
            }
            JOptionPane.showMessageDialog(null, "Удалено записей: "+studentsRemoved);
            dataController.getPagedView().showCurrentPage();
        });

        view.getCancelButton().addActionListener(e -> {
            //view.clearAllPanelsTextFields();
            setVisible(false);
        });
    }

    public SearchAndDeleteView getView() {
        return view;
    }
}
