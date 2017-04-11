package controller;
import javafx.scene.control.Tab;
import model.Student;
import model.StudentDataBase;
import view.MainTableModel;
import view.TableRecord;
import view.TableView;

import javax.swing.event.TableModelListener;

/**
 * Created by Batbara on 03.04.2017.
 */
public class DataController {
    MainTableModel tableModel;
    TableView tableView;
    StudentDataBase studentDataBase;

    public DataController(StudentDataBase model, TableView view) {
        this.studentDataBase =model;
        this.tableView=view;
    }

    public void addListener(TableModelListener listener){
        tableModel.addTableModelListener(listener);
    }
    public void removeListener(TableModelListener listener) {
        tableModel.removeTableModelListener(listener);
    }

    public void addStudentData(Student newStudent) {
        studentDataBase.addStudent(newStudent);
    }
    public void removeStudentData (Student studentToRemove){
        studentDataBase.removeStudent(studentToRemove);
    }
    public void update( String studentName, String parentName, String workingAddress,
             String jobPosition, Double workingYears){
        TableRecord newRow = new TableRecord(studentName, parentName, workingAddress,
                jobPosition, workingYears);

    }
    public void addRowToTable(TableRecord row){
        tableModel.add(row);
    }


}
