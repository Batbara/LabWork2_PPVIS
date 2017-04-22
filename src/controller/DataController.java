package controller;
import model.Parent;
import model.Student;
import model.StudentDataBase;
import model.Worker;
import view.Paging;
import view.TableRecord;
import java.util.*;

/**
 * Created by Batbara on 03.04.2017.
 */
public class DataController implements Observer{
    private final Paging pagedView;
    private final StudentDataBase studentDataBase;

    public DataController(StudentDataBase model, Paging view) {
        this.studentDataBase = model;
        this.pagedView =view;
        this.studentDataBase.addObserver(this);
    }


    public void addStudentData(Student newStudent) {
        studentDataBase.addStudent(newStudent);

    }
    private void removeStudentData (Student studentToRemove){
        studentDataBase.removeStudent(studentToRemove);
    }

    public void update(Observable observable, Object data){
        if(observable instanceof StudentDataBase){
            if(data instanceof Student) {
                Student addedStudent = (Student) data;
                TableRecord newRow = new TableRecord(addedStudent);
                addRowToTable(newRow);
            }else

            if(data instanceof HashMap){
                List<Student> base = getDataBase();
                Student studentToRemove = (Student)((HashMap) data).get("remove");
                TableRecord deleteRecord = new TableRecord(studentToRemove);
                deleteRecordFromTable(deleteRecord);
            }
            else
                clearAllRows();
        }
    }
    public void clearDataBase(){
        studentDataBase.removeAllStudents();
        clearAllRows();
    }
    public void loadDataBase(List<Student> base){
        studentDataBase.reinitDataBase(base);
    }
    private void clearAllRows(){
        pagedView.clearAllRows();
    }
    private int getNumberOfStudents(){
        return getDataBase().size();
    }
    private void addRowToTable(TableRecord record){
       // pagedView.addRowToTable(record.getRecordData());
        pagedView.addRecordToTable(record);
    }
    private void deleteRecordFromTable(TableRecord record){
        pagedView.deleteRecordFromTable(record);
    }
    public List<Student> getDataBase(){
        return studentDataBase.getStudents();
    }

    public Paging getPagedView() {
        return pagedView;
    }
    public int removeStudents(List<Student> studentsToRemove){
        int numBeforeDeletion = getNumberOfStudents();
        for (Student student : studentsToRemove){
            removeStudentData(student);
        }
        return numBeforeDeletion - getNumberOfStudents();
    }


    public List<Student> studentsSearch(Student studentToSearch, Set<String> searchKeys){
        boolean isStudentFullName = false;
        boolean isParentFullName = false;
        List<Student> result = new ArrayList<>();
        for (Student student : studentDataBase.getStudents()) {
            if (searchKeys.contains("Фамилия") && searchKeys.contains("Имя")) {
                if (student.getByKey("Фамилия").equals(studentToSearch.getByKey("Фамилия")) &&
                        student.getByKey("Имя").equals(studentToSearch.getByKey("Имя"))) {
                    if (!result.contains(student)) {
                        result.add(student);
                        isStudentFullName = true;
                    }
                }
            } else if (searchKeys.contains("Фамилия родителя") && searchKeys.contains("Имя родителя")) {
                if (student.getByKey("Фамилия родителя").equals(studentToSearch.getByKey("Фамилия родителя")) &&
                        student.getByKey("Имя родителя").equals(studentToSearch.getByKey("Имя родителя"))) {
                    if (!result.contains(student)) {
                        result.add(student);
                        isParentFullName = true;
                    }
                }
            }
        }
        for (Student student : studentDataBase.getStudents()){
            for (String key : searchKeys){
                try {
                    if (isStudentFullName){
                        if(key.equals("Фамилия") || key.equals("Имя"))
                            break;
                    } else
                    if (isParentFullName){
                        if(key.equals("Фамилия родителя") || key.equals("Имя родителя"))
                            break;
                    }else
                    if (student.getByKey(key).equals(studentToSearch.getByKey(key))) {
                        if (!result.contains(student))
                            result.add(student);
                    }
                }catch (NumberFormatException e){
                    System.err.println("Exception caught!");
                    continue;
                }
            }
        }
        return result;
    }
    public List<Student> studentsSearch(Student studentToSearch, Set<String> searchKeys, Double minExp, Double maxExp){
        List<Student> result = new ArrayList<>();

        for (Student student : studentDataBase.getStudents()){
            for (String key : searchKeys){
                if(student.getByKey(key).isEmpty())
                    continue;
                try {

                    if (student.getByKey(key).equals(studentToSearch.getByKey(key))) {
                        if (!result.contains(student))
                            result.add(student);
                    }
                    if(minExp != null && maxExp != null)
                        if (student.getExpInDouble()>=minExp && student.getExpInDouble()<=maxExp)
                            if (!result.contains(student))
                                result.add(student);
                }catch (NumberFormatException e){
                    System.err.println("Exception caught!");
                    continue;
                }
            }
        }
        return result;
    }



}
