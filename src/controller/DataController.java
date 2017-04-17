package controller;
import model.Parent;
import model.Student;
import model.StudentDataBase;
import model.Worker;
import view.Paging;
import view.TableRecord;
import view.TableView;

import java.util.*;
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

    public List<Student> studentNameSearch(Map<String, String> data){
        List<Student> result = new ArrayList<>();

       for (Student student : getDataBase())
           if (student.getStudentSurname().equals(data.get("Фамилия")))
               if (student.getStudentName().equals(data.get("Имя")))
                   if (student.getStudentFatherName().equals(data.get("Отчество")))
                       result.add(student);

        return result;
    }
    public List<Student> parentNameOrAddressSearch(List< Map<String, String> > data){
        List<Student> result = new ArrayList<>();
        Map<String, String> parentNameData = data.get(0);
        Map<String, String> addressData = data.get(1);

        for (Student student : getDataBase()) {
            Parent parent = student.getStudentParent();
            if (parent.getParentSurname().equals(parentNameData.get("Фамилия")))
                if (parent.getParentName().equals(parentNameData.get("Имя")))
                    if (parent.getParentFatherName().equals(parentNameData.get("Отчество")))
                        result.add(student);
        }

            for (Student student : getDataBase()) {
                Worker worker = student.getStudentParent().getWorkerData();
                if (worker.getCityOfWork().equals(addressData.get("Город")))
                    if (worker.getStreetOfWork().equals(addressData.get("Улица")))
                        if (worker.getBuildingNumberOfWork().equals(Integer.parseInt(addressData.get("Номер дома"))))
                            if(!result.contains(student))
                             result.add(student);
        }

        return result;
    }
    public List<Student> parentExpOrAddressSearch(List< Map<String, String> > data){
        List<Student> result = new ArrayList<>();
        Map<String, String> parentExpData = data.get(0);
        Map<String, String> addressData = data.get(1);

        for (Student student : getDataBase()) {
            Worker worker = student.getStudentParent().getWorkerData();
            if ( (worker.getWorkingYears() >= Integer.parseInt(parentExpData.get("fromYears")))
                    && (worker.getWorkingMonths() >= Integer.parseInt(parentExpData.get("fromMonths"))))
                if ( (worker.getWorkingYears() <= Integer.parseInt(parentExpData.get("toYears")))
                        && (worker.getWorkingMonths() <= Integer.parseInt(parentExpData.get("toMonths"))))
                    result.add(student);
        }
        for (Student student : getDataBase()) {
            Worker worker = student.getStudentParent().getWorkerData();
            if (worker.getCityOfWork().equals(addressData.get("Город")))
                if (worker.getStreetOfWork().equals(addressData.get("Улица")))
                    if (worker.getBuildingNumberOfWork().equals(Integer.parseInt(addressData.get("Номер дома"))))
                        if(!result.contains(student))
                            result.add(student);
        }

        return result;
    }
    public List<Student> studentNameOrAddressSearch(List< Map<String, String> > data){
        List<Student> result = new ArrayList<>();
        Map<String, String> studentNameData = data.get(0);
        Map<String, String> addressData = data.get(1);

        for (Student student : getDataBase()) {
            if (student.getStudentSurname().equals(studentNameData.get("Фамилия")))
                if (student.getStudentName().equals(studentNameData.get("Имя")))
                    if (student.getStudentFatherName().equals(studentNameData.get("Отчество")))
                        result.add(student);
        }

        for (Student student : getDataBase()) {
            Worker worker = student.getStudentParent().getWorkerData();
            if (worker.getCityOfWork().equals(addressData.get("Город")))
                if (worker.getStreetOfWork().equals(addressData.get("Улица")))
                    if (worker.getBuildingNumberOfWork().equals(Integer.parseInt(addressData.get("Номер дома"))))
                        if(!result.contains(student))
                            result.add(student);
        }

        return result;
    }

    public List<Vector<String> > getListOfSearchedRows(List <Student> studentsResult){
        List<Vector<String>> rowsResult = new ArrayList<>();
        for (Student student : studentsResult){
            Vector<String> studentAsRow = new TableRecord(student).getRecordData();
            rowsResult.add(studentAsRow);
        }
        return rowsResult;

    }
}
