package view;

import model.Parent;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

/**
 * Created by Batbara on 25.03.2017.
 */
public class TableView extends JTable{

    private final JPanel holdingTable;


    TableView(){
        super();
      //  this.setModel(mainTableModel);
        initTable();
        setUpHeader();
        setUpTableColor();
        JScrollPane tableScroll = new JScrollPane(this);

        holdingTable = new JPanel();
        holdingTable.setLayout(new BorderLayout());
        holdingTable.add(tableScroll);
//        tableScroll.setBorder(BorderFactory.createLineBorder(Color.yellow));
//        holdingTable.setBorder(BorderFactory.createLineBorder(Color.green));

        //this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
       /* this.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {

            }
        });*/
    }

    private void initTable() {
        String []tableHeaders ={"ФИО студента:", "ФИО родителя:", "Адрес работы родителя:",
                "Должность родителя:", "Стаж работы родителя:"};

        DefaultTableModel tableModel = new DefaultTableModel(tableHeaders,0) {
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        this.setModel(tableModel);

        for (int iterator=0; iterator<tableHeaders.length; iterator++ ){
            this.getColumnModel().getColumn(iterator).setHeaderValue(tableHeaders[iterator]);
        }
        this.setRowSelectionAllowed(false);
        this.setFont(new Font("Helvetica", Font.PLAIN, 14));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        this.setDefaultRenderer(String.class, centerRenderer);

        this.setRowHeight(30);
    }
    private void setUpHeader(){
        JTableHeader tableHeader = this.getTableHeader();
        tableHeader.setBackground(new Color(173,216,230));
        tableHeader.setFont(new Font("Helvetica", Font.BOLD, 13) );
        tableHeader.setReorderingAllowed(false);
        tableHeader.setResizingAllowed(true);
    }
    private void setUpTableColor(){
        this.setBackground(new Color(240,248,255));
        //this.setGridColor(new Color(25,25,112));
    }

    public JPanel getHoldingTable() {
        return holdingTable;
    }

}
