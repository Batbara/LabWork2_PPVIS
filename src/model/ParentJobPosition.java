package model;

/**
 * Created by Batbara on 08.04.2017.
 */
public class ParentJobPosition {
    String positionName;

    public ParentJobPosition(){
        this.positionName="";
    }
    public ParentJobPosition(String jobName){
        this.positionName=jobName;
    }
    public void setJobPosition(String jobName){
        this.positionName=jobName;
    }
}
