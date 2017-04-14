package model;

/**
 * Created by Batbara on 04.04.2017.
 */
public class Student {
    private String studentName;
    private String studentSurname;
    private String studentFatherName;
    private Parent studentParent;

    public Student(){
        studentName ="";
        studentSurname ="";
        studentFatherName="";
        studentParent = new Parent();
    }

    public String getFullName(){
        return getStudentSurname()+" "+getStudentName()+" "+getStudentFatherName();
    }
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentFatherName() {
        return studentFatherName;
    }

    public void setStudentFatherName(String studentFatherName) {
        this.studentFatherName = studentFatherName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    public Parent getStudentParent() {
        return studentParent;
    }

    public void setStudentParent(Parent studentParent) {
        this.studentParent = studentParent;
    }
}
