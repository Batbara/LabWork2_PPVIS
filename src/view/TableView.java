package view;
import controller.MainTableModelListener;
import model.MainTableModel;
import model.TableRecord;
import model.MainTableModel;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

/**
 * Created by Batbara on 25.03.2017.
 */
public class TableView{
    JTable mainTable;
    JScrollPane tableScroll;
    public JPanel holdingTable;
    public MainTableModel mainTableModel;

    public TableView(MainTableModel model){
        mainTableModel = model;
        initTable();
        setUpHeader();
        setUpTableColor();
        tableScroll = new JScrollPane(mainTable);

        holdingTable = new JPanel();
        holdingTable.setLayout(new BorderLayout());
        holdingTable.setPreferredSize(new Dimension(850, 300));
        holdingTable.add(tableScroll);

        //mainTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    private void initTable() {
        mainTable = new JTable(mainTableModel);

        String []tableHeaders ={"ФИО студента:", "ФИО родителя:", "Адрес работы родителя:",
                "Должность родителя:", "Стаж работы родителя:"};
        

        for (int iterator=0; iterator<tableHeaders.length; iterator++ ){
            mainTable.getColumnModel().getColumn(iterator).setHeaderValue(tableHeaders[iterator]);
        }

        mainTable.setRowSelectionAllowed(false);
    }
    private void setUpHeader(){
        JTableHeader tableHeader = mainTable.getTableHeader();
        tableHeader.setBackground(new Color(173,216,230));
        tableHeader.setReorderingAllowed(false);
        tableHeader.setResizingAllowed(true);
    }
    private void setUpTableColor(){
        mainTable.setBackground(new Color(240,248,255));
        mainTable.setGridColor(new Color(25,25,112));
    }
}
