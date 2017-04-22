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
    public String getByKey(String key){
        switch (key){
            case "Имя":
                return studentName;
            case "Фамилия":
                return studentSurname;
            case "Отчество":
                return studentFatherName;
            case "Отчество родителя":
                return studentParent.getParentFatherName();
            case "Имя родителя":
                return studentParent.getParentName();
            case "Фамилия родителя":
                return studentParent.getParentSurname();
            case "Город":
                return studentParent.getWorkerData().getCityOfWork();
            case "Улица":
                return studentParent.getWorkerData().getStreetOfWork();
            case "Номер дома":
                return Integer.toString(studentParent.getWorkerData().getBuildingNumberOfWork());
        }
        return "";
    }
    public Double getExpInDouble (){
        return studentParent.getWorkerData().getWorkExperience();
    }
    public void setByKey(String key, String data){
        switch (key){
            case "Имя":
                studentName = data;
                break;
            case "Фамилия":
                studentSurname = data;
                break;
            case "Отчество":
                studentFatherName = data;
                break;
            case "Отчество родителя":
                studentParent.setParentFatherName(data);
                break;
            case "Имя родителя":
                studentParent.setParentName(data);
                break;
            case "Фамилия родителя":
                studentParent.setParentSurname(data);
                break;
            case "Город":
                studentParent.getWorkerData().setCityOfWork(data);
                break;
            case "Улица":
                studentParent.getWorkerData().setStreetOfWork(data);
                break;
            case "Номер дома":
                try {
                    studentParent.getWorkerData().setBuildingNumberOfWork(Integer.parseInt(data));
                }catch (NumberFormatException e){
                    System.err.println("NumberFormatException caught!");
                    break;
                }
               break;
            case "Годы":
                studentParent.getWorkerData().setWorkingYears(Integer.parseInt(data));
                break;
            case "Месяцы":
                studentParent.getWorkerData().setWorkingMonths(Integer.parseInt(data));
               break;
        }
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
