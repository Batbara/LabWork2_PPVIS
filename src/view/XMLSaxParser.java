package view;

import model.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by Batbara on 14.04.2017.
 */
public class XMLSaxParser extends DefaultHandler{
    private List<Student> studentsList;
    private String currentElement;
    private Student lastStudent;
    private Parent lastStudentParent;
    private Worker lastParentWorker;
    private StringBuilder content;
    private boolean parentName;
    public XMLSaxParser(){
        studentsList = new ArrayList<Student>();
        currentElement = "";
        parentName = false;
    }
    public List<Student> getResult(){
       System.out.println("students num:"+ studentsList.size());
        return studentsList;
    }
    private Student getCurrentStudent(){
        Student lastStudent = studentsList.get(studentsList.size()-1);
        return lastStudent;
    }
    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start parse XML...");
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        currentElement = qName;
        System.out.println("Тег:"+currentElement);
        if (currentElement.equals(XMLConst.STUDENT)) {
            lastStudent = new Student();
            studentsList.add(lastStudent);
            System.out.println("new student created!!");
        } if (currentElement.equals(XMLConst.PARENT)) {
            lastStudentParent = lastStudent.getStudentParent();
            parentName =true;
        }
        if(currentElement.equals(XMLConst.JOB_POSITION)){
            parentName = false;
            lastParentWorker = lastStudentParent.getWorkerData();
        }
       // super.startElement(namespaceURI, localName, qName, atts);
    }
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {


        //if (content == null)
        //{
            content = new StringBuilder(new String(ch, start, length));
      //  } else {
          //  content.append(new String(ch, start, length));
      //  }
      //  System.out.println("content is: "+content);
    }
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        if (currentElement.equals(XMLConst.SURNAME) && !parentName) {

            lastStudent.setStudentSurname(content.toString());
            //lastStudent.setStudentSurname(tagValue);
            return;
        }
        if (currentElement.equals(XMLConst.NAME) && !parentName) {
            lastStudent.setStudentName(content.toString());
            return;
        }
        if (currentElement.equals(XMLConst.FATHER_NAME)&& !parentName) {
            lastStudent.setStudentFatherName(content.toString());
            return;
        }

        if (currentElement.equals(XMLConst.SURNAME) && parentName) {
            lastStudentParent.setParentSurname(content.toString());
            return;
        }
        if (currentElement.equals(XMLConst.NAME) && parentName) {
            lastStudentParent.setParentName(content.toString());
            return;
        }
        if (currentElement.equals(XMLConst.FATHER_NAME) && parentName) {
            lastStudentParent.setParentFatherName(content.toString());
            return;
        }

        if (currentElement.equals(XMLConst.JOB_POSITION)) {
            lastParentWorker.setJobPosition(content.toString());
            return;
        }

        if (currentElement.equals(XMLConst.CITY)) {
            lastParentWorker.setCityOfWork(content.toString());
            return;
        }
        if (currentElement.equals(XMLConst.STREET)) {
            lastParentWorker.setStreetOfWork(content.toString());
            return;
        }
        if (currentElement.equals(XMLConst.BUILDING_NUMBER)) {
            if(content.toString().contains("\t") || content.toString().contains("\n"))
                return;
            lastParentWorker.setBuildingNumberOfWork(Integer.parseInt(content.toString()));
            return;
        }

        if (currentElement.equals(XMLConst.WORK_YEARS)) {
            if(content.toString().contains("\t") || content.toString().contains("\n"))
                return;
            lastParentWorker.setWorkingYears(Integer.parseInt(content.toString()));
            return;
        }
        if (currentElement.equals(XMLConst.WORK_MONTHS)) {
            if(content.toString().contains("\t") || content.toString().contains("\n"))
                return;
            lastParentWorker.setWorkingMonths(Integer.parseInt(content.toString()));
            return;
        }
        if(qName.equals(XMLConst.PARENT)) {
            parentName = false;
        }
        if(qName.equals(XMLConst.STUDENT)){
            parentName = false;
            lastStudentParent.setWorkerData(lastParentWorker);
            lastStudent.setStudentParent(lastStudentParent);
            //studentsList.add(lastStudent);
            System.out.println("student added to list!");

            System.out.println("Current student num:"+studentsList.size());
        }
//        System.out.println("Тег разобран: "+qName);
//        System.out.println(localName);
       // super.endElement(uri,localName, qName);
    }
    @Override
    public void endDocument() {
        System.out.println("Stop parse XML...");
    }

}
