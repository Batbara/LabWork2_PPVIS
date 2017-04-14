package view;

import java.util.List;

import controller.DataController;
import model.Student;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
/**
 * Created by Batbara on 14.04.2017.
 */
public class XMLDomParser {
    String fileName;
    DataController dataController;
    public XMLDomParser(DataController dataController, String fileName){
        this.dataController = dataController;
        this.fileName = fileName+".sdb";
    }
    public void parseIntoFile(){
        Document document = createDocument();
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            File fileToSave = new File(fileName);
            StreamResult result = new StreamResult(fileToSave);
            transformer.transform(source, result);

            System.out.println("Документ сохранен в: "+fileToSave.getAbsolutePath());
        }
        catch ( TransformerConfigurationException ex) {
            Logger.getLogger(XMLDomParser.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        catch (TransformerException ex) {
            Logger.getLogger(XMLDomParser.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

    }
    private Document createDocument(){
        List<Student> dataBase = dataController.getDataBase();
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            Element studentDataBase = document.createElement(XMLConst.STUDENT_DATA_BASE);
            document.appendChild(studentDataBase);

            for (Student student : dataBase){
                Element studentNode = document.createElement(XMLConst.STUDENT);
                studentDataBase.appendChild(studentNode);

                Element surname = document.createElement(XMLConst.SURNAME);
                surname.setTextContent(student.getStudentName().getPersonSurname());
                studentNode.appendChild(surname);

                Element name = document.createElement(XMLConst.NAME);
                name.setTextContent(student.getStudentName().getPersonName());
                studentNode.appendChild(name);

                Element fatherName = document.createElement(XMLConst.FATHER_NAME);
                fatherName.setTextContent(student.getStudentName().getPersonFatherName());
                studentNode.appendChild(fatherName);

                Element parent = document.createElement(XMLConst.PARENT);
                studentNode.appendChild(parent);

                surname.setTextContent(student.getParentName().getPersonSurname());
                parent.appendChild(surname);

                name.setTextContent(student.getParentName().getPersonName());
                parent.appendChild(name);

                fatherName.setTextContent(student.getParentName().getPersonFatherName());
                parent.appendChild(fatherName);

                Element job = document.createElement(XMLConst.JOB_POSITION);
                job.setTextContent(student.getParentJobPosition().getParentJobPosition());
                parent.appendChild(job);

                Element address = document.createElement(XMLConst.WORK_ADDRESS);
                parent.appendChild(address);

                Element city = document.createElement(XMLConst.CITY);
                city.setTextContent(student.getParentWorkAddress().getCityOfWork());
                address.appendChild(city);

                Element street = document.createElement(XMLConst.STREET);
                street.setTextContent(student.getParentWorkAddress().getStreetOfWork());
                address.appendChild(street);

                Element buildingNumber = document.createElement(XMLConst.BUILDING_NUMBER);
                buildingNumber.setTextContent(student.getParentWorkAddress().getBuildingNumberOfWork().toString());
                address.appendChild(buildingNumber);

                Element experience = document.createElement(XMLConst.WORK_EXPERIENCE);
                parent.appendChild(experience);

                Element years = document.createElement(XMLConst.WORK_YEARS);
                years.setTextContent(student.getWorkExperience().getWorkingYears().toString());
                experience.appendChild(years);

                Element months = document.createElement(XMLConst.WORK_MONTHS);
                months.setTextContent(student.getWorkExperience().getWorkingMonths().toString());
                experience.appendChild(months);
                return document;
            }
        }
        catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLDomParser.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
