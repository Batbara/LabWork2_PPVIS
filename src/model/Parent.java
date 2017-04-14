package model;

/**
 * Created by Batbara on 14.04.2017.
 */
public class Parent {
    private String parentName;
    private String parentSurname;
    private String parentFatherName;
    private Worker workerData;

    public Parent(){
        parentFatherName ="";
        parentName ="";
        parentSurname ="";
        workerData = new Worker();
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentSurname() {
        return parentSurname;
    }

    public void setParentSurname(String parentSurname) {
        this.parentSurname = parentSurname;
    }

    public String getParentFatherName() {
        return parentFatherName;
    }

    public void setParentFatherName(String parentFatherName) {
        this.parentFatherName = parentFatherName;
    }

    public String getFullName(){
        return getParentSurname()+" "+getParentName()+" "+getParentFatherName();
    }

    public Worker getWorkerData() {
        return workerData;
    }

    public void setWorkerData(Worker workerData) {
        this.workerData = workerData;
    }
}
