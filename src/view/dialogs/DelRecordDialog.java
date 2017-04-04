package view.dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Batbara on 02.04.2017.
 */
public class DelRecordDialog extends JDialog{

    ButtonGroup groupRadioButtons;

    JRadioButton studentNameOption;
    JRadioButton parentNameOrAddressOption;
    JRadioButton parentExpOrYearsOption;
    JRadioButton studentNameOrAddressOption;

    JTextField firstCriterionField;
    JTextField secondCriterionField;

    JLabel firstLabel;
    JLabel secondLabel;

    Font mainFont;

    public DelRecordDialog(JFrame ownerFrame){
        super(ownerFrame, "Удалить записи",Dialog.ModalityType.DOCUMENT_MODAL);
        mainFont =  new Font("Helvetica", Font.PLAIN, 13);
        groupRadioButtons = new ButtonGroup();
        initDialog();
        initTextFieldItems();
        initRadioButtons();
        addToGroup();

        JLabel titleLabel = new JLabel("Выберете удаление записей по:");
        titleLabel.setFont(mainFont);
        addToDialogFrame(titleLabel);
        addButtons();

        addFieldPanel(firstLabel, firstCriterionField, 75);
        addFieldPanel(secondLabel, secondCriterionField, 75);

        addButtonListeners();


    }
    private void initRadioButtons(){
        studentNameOption = new JRadioButton("ФИО студента");
        studentNameOption.setFont(mainFont);

        parentNameOrAddressOption = new JRadioButton("ФИО родителя или месту работы");
        parentNameOrAddressOption.setFont(mainFont);

        parentExpOrYearsOption = new JRadioButton("Стажу или месту работы");
        parentExpOrYearsOption.setFont(mainFont);

        studentNameOrAddressOption = new JRadioButton("ФИО студента или месту работы");
        studentNameOrAddressOption.setFont(mainFont);
    }
    private void addToGroup(){
        groupRadioButtons.add(studentNameOption);
        groupRadioButtons.add(parentNameOrAddressOption);
        groupRadioButtons.add(parentExpOrYearsOption);
        groupRadioButtons.add(studentNameOrAddressOption);
    }
    private void initDialog(){
        final int DIALOG_WIDTH = 320;
        final int DIALOG_HEIGHT = 355;

        this.setSize(new Dimension(DIALOG_WIDTH,DIALOG_HEIGHT));
        this.setPreferredSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
        this.setMaximumSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));

        this.setLayout(new FlowLayout());
        this.pack();
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
    private void initTextFieldItems(){
        firstCriterionField = new JTextField(10);
        secondCriterionField = new JTextField(10);

        firstLabel = new JLabel();
        firstLabel.setLayout(new FlowLayout(FlowLayout.LEFT));
        firstLabel.setFont(new Font("Helvetica", Font.PLAIN, 13));

        secondLabel = new JLabel();
        secondLabel.setLayout(new FlowLayout(FlowLayout.LEFT));
        secondLabel.setFont(new Font("Helvetica", Font.PLAIN, 13));
        setVisibleTextFieldItems(false);
    }
    private void addButtons(){
        JPanel verticalButtonsPanel = new JPanel();
        verticalButtonsPanel.setLayout(new BoxLayout(verticalButtonsPanel, BoxLayout.Y_AXIS));

        verticalButtonsPanel.add(studentNameOption);
        verticalButtonsPanel.add(parentNameOrAddressOption);
        verticalButtonsPanel.add(parentExpOrYearsOption);
        verticalButtonsPanel.add(studentNameOrAddressOption);

        addToDialogFrame(verticalButtonsPanel);
    }

    private void addToDialogFrame(Component componentToAdd){
        this.add(componentToAdd);
    }
    private void addFieldPanel(JLabel nameLabel, JTextField fieldToAdd, int gap){
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.X_AXIS));
        // fieldPanel.setLayout(new BorderLayout(15,0));


        fieldPanel.add(nameLabel, Component.LEFT_ALIGNMENT);
        fieldPanel.add(Box.createRigidArea(new Dimension(gap, 20)));
        fieldPanel.add(fieldToAdd, Component.RIGHT_ALIGNMENT);

        addToDialogFrame(fieldPanel);
    }
    private void addButtonListeners(){
        studentNameOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstLabel.setVisible(true);
                firstCriterionField.setVisible(true);

                secondCriterionField.setVisible(false);
                secondLabel.setVisible(false);
                firstLabel.setText("ФИО студента:");
            }
        });
        parentNameOrAddressOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisibleTextFieldItems(true);
                firstLabel.setText("ФИО родителя:");
                secondLabel.setText("Место работы родителя:");
            }
        });
        studentNameOrAddressOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisibleTextFieldItems(true);
                firstLabel.setText("ФИО студента:");
                secondLabel.setText("Место работы родителя:");
            }
        });
    }
    public void setVisibleTextFieldItems(boolean mode){
        firstCriterionField.setVisible(mode);
        secondCriterionField.setVisible(mode);

        firstLabel.setVisible(mode);
        secondLabel.setVisible(mode);
    }
    public void noneSelected(){
        groupRadioButtons.clearSelection();
    }
}
