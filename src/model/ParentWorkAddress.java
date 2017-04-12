package model;

/**
 * Created by Batbara on 04.04.2017.
 */
public class ParentWorkAddress {
    String cityOfWork;
    String streetOfWork;
    Integer buildingNumberOfWork;

    public ParentWorkAddress(){
        this.cityOfWork="";
        this.streetOfWork="";
        this.buildingNumberOfWork=null;
    }
    public ParentWorkAddress(String city, String street, String buildingNumber){
        this.cityOfWork=city;
        this.streetOfWork=street;
        this.buildingNumberOfWork=Integer.parseInt(buildingNumber);
    }
    public String getWorkAddress(){
        String workAddress = "г. "+cityOfWork+", " + streetOfWork +" д. " + Integer.toString(buildingNumberOfWork);
        return workAddress;
    }
    public ParentWorkAddress(String rawData){

    }

}
