package controller;
import model.Student;
import model.StudentDataBase;
import view.TableRecord;
import view.TableView;
import java.util.Observer;
import java.util.Observable;
import java.util.List;
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
            if(data instanceof Student) {
               // StudentDataBase dataBase = (StudentDataBase) observable;
                Student addedStudent = (Student) data;
                TableRecord newRow = new TableRecord(addedStudent);
                addRowToTable(newRow);
            }
            else
            clearAllRows();
        }
    }
    public void clearDataBase(){
        studentDataBase.removeAllStudents();
    }
    public void loadDataBase(List<Student> base){
        studentDataBase.reinitDataBase(base);
    }
    public void clearAllRows(){
        DefaultTableModel model = (DefaultTableModel)tableView.getModel();
        int tableRows = model.getRowCount();
        for(int iterator = tableRows - 1; iterator >=0; iterator--) {
            model.removeRow(iterator);
        }

    }
    public void addRowToTable(TableRecord record){
        DefaultTableModel defaultTableModel = (DefaultTableModel)tableView.getModel();
        defaultTableModel.addRow(record.getRecordData());
    }
    public List<Student> getDataBase(){
        return studentDataBase.getStudents();
    }


}
