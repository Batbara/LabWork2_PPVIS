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
    public ParentWorkAddress(String city, String street, Integer buildingNumber){
        this.cityOfWork=city;
        this.streetOfWork=street;
        this.buildingNumberOfWork=buildingNumber;
    }
    public ParentWorkAddress(String rawData){

    }

}
