package controller;
import model.MainTableModel;
import model.TableRecord;
import view.TableView;

import javax.swing.event.*;
import javax.swing.event.TableModelListener;

/**
 * Created by Batbara on 03.04.2017.
 */
public class TableController {
    MainTableModel tableModel;
    TableView tableView;

    public TableController(MainTableModel model, TableView view) {
        this.tableModel=model;
        this.tableView=view;
    }

    public void addListener(TableModelListener listener){
        tableModel.addTableModelListener(listener);
    }
    public void removeListener(TableModelListener listener) {
        tableModel.removeTableModelListener(listener);
    }

    public void addRow(TableRecord row) {
        tableModel.add(row);

    }
    public void update( String studentName, String parentName, String workingAddress,
             String jobPosition, Double workingYears){
        TableRecord newRow = new TableRecord(studentName, parentName, workingAddress,
                jobPosition, workingYears);

    }
}
