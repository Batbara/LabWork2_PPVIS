package controller;
import model.Student;
import model.StudentDataBase;
import view.TableRecord;
import view.TableView;
import java.util.Observer;
import java.util.Observable;
import javax.swing.table.DefaultTableModel;

/**
 * Created by Batbara on 03.04.2017.
 */
public class DataController implements Observer{
    TableView tableView;
    StudentDataBase studentDataBase;

    public DataController(StudentDataBase model, TableView view) {
        this.studentDataBase = model;
        this.tableView=view;
        this.studentDataBase.addObserver(this);
    }


    public void addStudentData(Student newStudent) {
        studentDataBase.addStudent(newStudent);

    }
    public void removeStudentData (Student studentToRemove){
        studentDataBase.removeStudent(studentToRemove);
    }

    public void update(Observable observable, Object data){
        if(observable instanceof StudentDataBase){
            StudentDataBase dataBase = (StudentDataBase)observable;
            Student addedStudent = (Student)data;
            TableRecord newRow = new TableRecord(addedStudent);
            addRowToTable(newRow);
        }
    }
    public void addRowToTable(TableRecord record){
        DefaultTableModel defaultTableModel = (DefaultTableModel)tableView.getModel();
        defaultTableModel.addRow(record.getRecordData());
    }


}
