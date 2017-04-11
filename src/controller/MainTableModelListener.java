package controller;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 * Created by Batbara on 04.04.2017.
 */
public class MainTableModelListener implements TableModelListener {
    JTable table;
    DataController tableController;
    public MainTableModelListener (JTable inputTable, DataController controller){
        this.table = inputTable;
        this.tableController = controller;
        this.tableController.addListener(this);
    }
    public void tableChanged(TableModelEvent event){
        int firstRow = event.getFirstRow();
        int lastRow = event.getLastRow();
        int columnIndex = event.getColumn();

        switch (event.getType()){
            case TableModelEvent.INSERT: {

            }
        }
    }

}
