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

    public List<Student> studentNameSearch(Map<String, String> data){
        List<Student> result = new ArrayList<>();

       for (Student student : getDataBase())
           if ( (student.getStudentSurname().equals(data.get("Фамилия"))) ||
                (student.getStudentName().equals(data.get("Имя"))) ||
                    (student.getStudentFatherName().equals(data.get("Отчество"))))
                       if (!result.contains(student))
                           result.add(student);

        return result;
    }
    public List<Student> parentNameOrAddressSearch(List< Map<String, String> > data){
        List<Student> result = new ArrayList<>();
        Map<String, String> parentNameData = data.get(0);
        Map<String, String> addressData = data.get(1);

        for (Student student : getDataBase()) {
            Parent parent = student.getStudentParent();
            if ( parent.getParentSurname().equals(parentNameData.get("Фамилия")) ||
            (parent.getParentName().equals(parentNameData.get("Имя"))) ||
            (parent.getParentFatherName().equals(parentNameData.get("Отчество"))))
                if (!result.contains(student))
                    result.add(student);
        }
        try {
            for (Student student : getDataBase()) {
                Worker worker = student.getStudentParent().getWorkerData();
                if ( (worker.getCityOfWork().equals(addressData.get("Город"))) ||
                        (worker.getStreetOfWork().equals(addressData.get("Улица"))) ||
                (worker.getBuildingNumberOfWork().equals(Integer.parseInt(addressData.get("Номер дома")))))
                            if (!result.contains(student))
                                result.add(student);
            }
        }
        catch (NumberFormatException e){

            System.err.println("Caught NumberFormatException: "+e.getMessage());
        }

        return result;
    }
    public List<Student> parentExpOrAddressSearch(List< Map<String, String> > data){
        List<Student> result = new ArrayList<>();
        Map<String, String> parentExpData = data.get(0);
        Map<String, String> addressData = data.get(1);
        try {
            for (Student student : getDataBase()) {
                Worker worker = student.getStudentParent().getWorkerData();
                if ((worker.getWorkingYears() >= Integer.parseInt(parentExpData.get("fromYears")))
                        && (worker.getWorkingMonths() >= Integer.parseInt(parentExpData.get("fromMonths"))))
                    if ((worker.getWorkingYears() <= Integer.parseInt(parentExpData.get("toYears")))
                            && (worker.getWorkingMonths() <= Integer.parseInt(parentExpData.get("toMonths"))))
                        if (!result.contains(student))
                            result.add(student);
            }
        }
        catch (NumberFormatException e){

                System.err.println("Caught NumberFormatException: "+e.getMessage());
        }
        try {
            for (Student student : getDataBase()) {
                Worker worker = student.getStudentParent().getWorkerData();
                if ( (worker.getCityOfWork().equals(addressData.get("Город")))||
                        (worker.getStreetOfWork().equals(addressData.get("Улица"))) ||
                (worker.getBuildingNumberOfWork().equals(Integer.parseInt(addressData.get("Номер дома")))))
                            if (!result.contains(student))
                                result.add(student);
            }
        }
        catch (NumberFormatException e){

            System.err.println("Caught NumberFormatException: "+e.getMessage());
        }

        return result;
    }
    public List<Student> studentNameOrAddressSearch(List< Map<String, String> > data){
        List<Student> result = new ArrayList<>();
        Map<String, String> studentNameData = data.get(0);
        Map<String, String> addressData = data.get(1);

        for (Student student : getDataBase()) {
            if (student.getStudentSurname().equals(studentNameData.get("Фамилия")) ||
            (student.getStudentName().equals(studentNameData.get("Имя"))) ||
            (student.getStudentFatherName().equals(studentNameData.get("Отчество"))))
                if (!result.contains(student))
                    result.add(student);
        }

        try {
            for (Student student : getDataBase()) {
                Worker worker = student.getStudentParent().getWorkerData();
                if ( (worker.getCityOfWork().equals(addressData.get("Город"))) ||
                (worker.getStreetOfWork().equals(addressData.get("Улица"))) ||
                (worker.getBuildingNumberOfWork().equals(Integer.parseInt(addressData.get("Номер дома")))))
                            if (!result.contains(student))
                                result.add(student);
            }
        }
        catch (NumberFormatException e){

            System.err.println("Caught NumberFormatException: "+e.getMessage());
        }

        return result;
    }
    public List<Student> studentsSearch(Student studentToSearch, Set<String> searchKeys){
        List<Student> result = new ArrayList<>();
        for (Student student : studentDataBase.getStudents()){
            for (String key : searchKeys){
//                if(key.equals("from")) {
//                    if(student.getExpByKey(key))
//                }
                try {
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

}
