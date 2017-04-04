package model;
import controller.TableController;

import javax.swing.table.*;
import javax.swing.event.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Batbara on 30.03.2017.
 */
public class MainTableModel extends AbstractTableModel {

    private List<TableRecord> dataRecord;

    private List<TableModelListener> listeners;

    public MainTableModel(){
        dataRecord = new ArrayList<TableRecord>();
        listeners = new ArrayList<TableModelListener>();
    }
    public Class<?> getColumnClass(int columnIndex){

        return getValueAt(0,columnIndex).getClass();
    }
    public List<TableModelListener> getListeners(){
        return listeners;
    }

    public void addTableModelListener(TableModelListener listener) {
        listeners.add(listener);
    }

    public void removeTableModelListener(TableModelListener listener) {
        listeners.remove(listener);
    }
    public int getColumnCount(){
        return 5;
    }
    public String getColumnName(int columnIndex){
        switch (columnIndex){
            case 0:
                return "ФИО студента";
            case 1:
                return "ФИО родителя";
            case 2:
                return "Адрес работы родителя";
            case 3:
                return "Должность родителя";
            case 4:
                return "Стаж работы родителя";
        }
        return "";
    }
    public int getRowCount(){
        return dataRecord.size();
    }
    public Object getValueAt(int rowIndex, int columnIndex) {
        TableRecord recordAtIndex = dataRecord.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return recordAtIndex.getStudentName();
            case 1:
                return recordAtIndex.getParentName();
            case 2:
                return recordAtIndex.getJobPosition();
            case 3:
                return recordAtIndex.getWorkingAddress();
            case 4:
                return recordAtIndex.getWorkingYears();
        }

        return "";
    }
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void setValueAt(Object cellToAdd, int rowIndex, int columnIndex) {
        TableRecord recordAtIndex = dataRecord.get(rowIndex);

        String stringToAdd = (String) cellToAdd;
        Double yearsToAdd = (Double) cellToAdd;
        switch (columnIndex) {
            case 0:
                recordAtIndex.setStudentName(stringToAdd);
                break;
            case 1:
                 recordAtIndex.setParentName(stringToAdd);
                 break;
            case 2:
                 recordAtIndex.setJobPosition(stringToAdd);
                 break;
            case 3:
                recordAtIndex.setWorkingAddress(stringToAdd);
                break;
            case 4:
                recordAtIndex.setWorkingYears(yearsToAdd);
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);


}

    public void insertRow (int rowIndex) {
        TableRecord newRow = new TableRecord();
        dataRecord.add(rowIndex, newRow);
        fireTableRowsInserted(rowIndex,rowIndex);
    }
     public void add(TableRecord row){
         TableRecord newRow = row;
         dataRecord.add(newRow);
         fireTableRowsInserted(dataRecord.size()-1, dataRecord.size()-1);
     }
    public void deleteRow (int rowIndex) {
        dataRecord.remove(rowIndex);
        fireTableRowsDeleted(rowIndex,rowIndex);
    }


}
