package controller;
import model.Student;
import model.StudentDataBase;
import view.Paging;
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
    private Paging pagedView;
    private StudentDataBase studentDataBase;

    public DataController(StudentDataBase model, Paging view) {
        this.studentDataBase = model;
        this.pagedView =view;
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
        pagedView.clearAllRows();
    }
    public int getNumberOfStudents(){
        return getDataBase().size();
    }
    public void addRowToTable(TableRecord record){
        pagedView.addRowToTable(record.getRecordData());
    }
    public List<Student> getDataBase(){
        return studentDataBase.getStudents();
    }

    public Paging getPagedView() {
        return pagedView;
    }
}
