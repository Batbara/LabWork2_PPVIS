package model;

import java.util.*;


/**
 * Created by Batbara on 08.04.2017.
 */
public class StudentDataBase extends Observable {
    private final List <Student> students;
    public StudentDataBase(){
        this.students= new ArrayList<>();
    }

    public void addStudent(Student newStudent){
        students.add(newStudent);
        studentAdded(newStudent);
    }

    public void removeStudent(Student studentToRemove){
//        Predicate<Student> studentPredicate = (Student student) -> Objects.equals(student, studentToRemove);
//                students.removeIf(studentPredicate);
//        studentRemoved(true);
        int index = 0;

        Map<String, Student> removingStudent = new HashMap<>();
        for (Iterator<Student> iterator = students.listIterator(); iterator.hasNext();){
            Student student = iterator.next();
            if (studentToRemove==student){

                removingStudent.put("remove", student);

                iterator.remove();
                studentRemoved(removingStudent);
                return;
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
    private void allStudentsRemoved(){
        setChanged();
        notifyObservers();
    }
    private void studentRemoved(Map<String, Student> student){
        setChanged();
        notifyObservers(student);
    }

    private void studentAdded(Student student){
        setChanged();
        notifyObservers(student);
    }

    public List <Student> getStudents(){
        return students;
    }

}
