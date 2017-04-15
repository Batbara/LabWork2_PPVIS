package view;

import java.util.List;

import controller.DataController;
import model.Parent;
import model.Student;
import model.Worker;
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
                Parent studentParent = student.getStudentParent();
                Worker parentWorker = studentParent.getWorkerData();
                Element studentNode = document.createElement(XMLConst.STUDENT);
                studentDataBase.appendChild(studentNode);

                Element studentSurname = document.createElement(XMLConst.SURNAME);
                studentSurname.setTextContent(student.getStudentSurname());
                studentNode.appendChild(studentSurname);

                Element name = document.createElement(XMLConst.NAME);
                name.setTextContent(student.getStudentName());
                studentNode.appendChild(name);

                Element fatherName = document.createElement(XMLConst.FATHER_NAME);
                fatherName.setTextContent(student.getStudentFatherName());
                studentNode.appendChild(fatherName);

                Element parent = document.createElement(XMLConst.PARENT);
                studentNode.appendChild(parent);

                Element parentSurname = document.createElement(XMLConst.SURNAME);
                parentSurname.setTextContent(studentParent.getParentSurname());
                parent.appendChild(parentSurname);

                Element parentName = document.createElement(XMLConst.NAME);
                parentName.setTextContent(studentParent.getParentName());
                parent.appendChild(parentName);

                Element parentFatherName = document.createElement(XMLConst.FATHER_NAME);
                parentFatherName.setTextContent(studentParent.getParentFatherName());
                parent.appendChild(parentFatherName);


                Element job = document.createElement(XMLConst.JOB_POSITION);
                job.setTextContent(parentWorker.getJobPosition());
                parent.appendChild(job);

                Element address = document.createElement(XMLConst.WORK_ADDRESS);
                parent.appendChild(address);

                Element city = document.createElement(XMLConst.CITY);
                city.setTextContent(parentWorker.getCityOfWork());
                address.appendChild(city);

                Element street = document.createElement(XMLConst.STREET);
                street.setTextContent(parentWorker.getStreetOfWork());
                address.appendChild(street);

                Element buildingNumber = document.createElement(XMLConst.BUILDING_NUMBER);
                buildingNumber.setTextContent(parentWorker.getBuildingNumberOfWork().toString());
                address.appendChild(buildingNumber);

                Element experience = document.createElement(XMLConst.WORK_EXPERIENCE);
                parent.appendChild(experience);

                Element years = document.createElement(XMLConst.WORK_YEARS);
                years.setTextContent(parentWorker.getWorkingYears().toString());
                experience.appendChild(years);

                Element months = document.createElement(XMLConst.WORK_MONTHS);
                months.setTextContent(parentWorker.getWorkingMonths().toString());
                experience.appendChild(months);

            }
            return document;
        }
        catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLDomParser.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
