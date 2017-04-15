package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;


/**
 * Created by Batbara on 08.04.2017.
 */
public class StudentDataBase extends Observable {
    private List <Student> students;
    public StudentDataBase(){
        this.students= new ArrayList<Student>();
    }

    public void addStudent(Student newStudent){
        students.add(newStudent);
        studentAdded(newStudent);
    }

    public void removeStudent(Student studentToRemove){
        for (Iterator<Student> iterator = students.listIterator(); iterator.hasNext();){
            Student student = iterator.next();
            if (studentToRemove==student){
                iterator.remove();
            }
        }
    }
    public void reinitDataBase(List<Student> studentsList){
        //students = studentsList;
        removeAllStudents();
        for(Student student: studentsList){
            addStudent(student);
        }
    }
    public void removeAllStudents(){
        students.clear();
        allStudentsRemoved();
    }
    public void allStudentsRemoved(){
        setChanged();
        notifyObservers();
    }

    public void studentAdded(Student student){
        setChanged();
        notifyObservers(student);
    }
    public List <Student> getStudents(){
        return students;
    }

}
