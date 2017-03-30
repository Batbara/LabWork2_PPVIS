package view;
import model.MainTableModel;

import javax.swing.*;
import javax.swing.table.*;
/**
 * Created by Batbara on 25.03.2017.
 */
public class TableView {
    JTable mainTable;

    public void initTable() {
        TableModel model = new MainTableModel();
        mainTable = new JTable(model);
    }
}
