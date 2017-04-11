package model;

/**
 * Created by Batbara on 04.04.2017.
 */
public class Student {
    PersonName studentName;
    PersonName parentName;
    ParentWorkAddress parentWorkAddress;
    ParentJobPosition parentJobPosition;
    ParentWorkExperience workExperience;

    public Student(PersonName studentName, PersonName parentName, ParentWorkAddress workAddress,
                   ParentJobPosition jobPosition, ParentWorkExperience workExperience){
        this.studentName= studentName;
        this.parentName=parentName;
        this.parentWorkAddress = workAddress;
        this.parentJobPosition = jobPosition;
        this.workExperience = workExperience;
    }
    public Student(String stName, String prntName, String workAdd, String job, double wYears){
        PersonName studentName= new PersonName(stName);
        this.studentName = studentName;

        PersonName parentName = new PersonName(prntName);
        this.parentName = parentName;

        ParentWorkAddress workAddress = new ParentWorkAddress(workAdd);
        this.parentWorkAddress = workAddress;
    }
    public Student(){
    }

}
