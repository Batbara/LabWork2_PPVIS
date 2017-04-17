package view.dialogs;

import controller.DataController;
import model.Student;

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
            switch (buttonKey){
                case "studentNameOption":
                    result = dataController.studentNameSearch(view.studentNameSearchData());
                    break;
                case "parentNameOrAddressOption":
                    result = dataController.parentNameOrAddressSearch(view.parentNameOrAddressSearchData());
                    break;
                case "parentExpOrAddressOption":
                    result = dataController.parentExpOrAddressSearch(view.parentExpOrAddressSearchData());
                    break;
                case "studentNameOrAddressOption":
                    result = dataController.studentNameOrAddressSearch(view.studentNameOrAddressSearchData());
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
            view.clearAllPanelsTextFields();
            setVisible(false);
        });
    }

    public SearchAndDeleteView getView() {
        return view;
    }
}
