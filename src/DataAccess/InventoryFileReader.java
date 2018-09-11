
package DataAccess;

import Model.Car;
import Model.CarType;
import Model.Engine;
import Model.FuelType;
import Model.Interior;
import Model.Trunk;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import org.w3c.dom.*;

public class InventoryFileReader {

    XMLStreamWriter writer;
    public InventoryFileReader() {
    }
    public void addCar(Car car) {
    }

    public void removeCar(ArrayList<Car> cars, Car car) {
    cars.remove(car);
    saveInventory(cars);
    }
    
public void saveInventory(ArrayList<Car> cars) {
        try {
            FileWriter fileWriter = new FileWriter("Inventory.xml");
            XMLOutputFactory factory = XMLOutputFactory.newInstance();

            writer = factory.createXMLStreamWriter(fileWriter);

            writer.writeStartDocument("1.0");
            writer.writeComment("Car inventory");
            writer.writeStartElement("Inventory");
            for (Car car: cars) {
                writer.writeStartElement("Car");
                
                writeElement("make", car.getMake());
                writeElement("model", car.getModel());
                writeIntElement("year", car.getYear());
                writeIntElement("price", car.getPrice());
                writeElement("color", car.getColor());
                writeElement("type", car.getType().toString());
                writeIntElement("miles", car.getMiles());
                //Write Engine
                writeEngine(car.getEngine());
                //Write Interior
                writeInterior(car.getInterior());
                //Write Trunk
                writeTrunk(car.getTrunk());
                //Wtite ImagePath
                writeElement("ImagePath", car.getImage());

                writer.writeEndElement(); // for the Car element
            }
            writer.writeEndElement();     // for the Inventory element
            writer.flush();
            writer.close();
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    } 

    private void writeElement(String name, String innerText) throws XMLStreamException {
        writer.writeStartElement(name);
        writer.writeCharacters(innerText);
        writer.writeEndElement();
    }
    
    private void writeIntElement(String name, int value) throws XMLStreamException{
        writeElement(name, value+"");
    }

    public ArrayList<Car> getAllCars() {
        ArrayList<Car> cars = new ArrayList<>();
        Document docsDoc = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            File file = new File("Inventory.xml");
            docsDoc = db.parse(file);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.xml.sax.SAXException ex) {
            ex.printStackTrace();
        }
//retrieve document elements
        NodeList carNodes = docsDoc.getElementsByTagName("Car");
        for (int i = 0; i < carNodes.getLength(); i++) {
            {
                Element CarElement = (Element)carNodes.item(i);
                //Make
                String make = getSubElementText(CarElement, "make");
                //Model
                String model = getSubElementText(CarElement, "model");
                //Year
                String yearText = getSubElementText(CarElement, "year");
                int year = Integer.parseInt(yearText);
                //Price
                String priceText = getSubElementText(CarElement, "price");
                int price = Integer.parseInt(priceText);
                //Color
                String color = getSubElementText(CarElement, "color");
                //Type
                String typeString =  getSubElementText(CarElement, "type");
                CarType type = CarType.valueOf(typeString);
               
              String milesText = getSubElementText(CarElement, "miles");
                int miles = Integer.parseInt(milesText);
                
                String userPath = System.getProperty("user.dir") +"/";
                
                String imagePath = userPath + getSubElementText(CarElement, "ImagePath");
               
                //Engine
                
                
                Car car = new Car(
                        make, year, model, price, 
                        color, miles, type);
              
                Engine engine = getEngine(getSubElement(CarElement, "Engine"));
                 car.SetEngine(engine);
                //Interior 
                Interior interior = getInterior(getSubElement(CarElement, "Interior"));
                car.SetInterior(interior);
                 //Trunk
                Trunk trunk = getTrunk(getSubElement(CarElement, "Trunk"));
              
               
               car.SetTrunk(trunk);
                
                 car = new Car(
                        make, year, model, price, 
                        color, miles, type, engine, interior, trunk, imagePath);
                //create the corresponding objects, and add them to the car object
                cars.add(car);
            }
        }
        return cars;
    }
    
    private String getSubElementText(Element parent, String subElementName){
        Element subElement = getSubElement(parent, subElementName);
        return subElement.getTextContent();
    }
    
    private Element getSubElement(Element parent, String subElementName){
        return (Element) parent.getElementsByTagName(subElementName).item(0);
    }

    private Engine getEngine(Element engineElement) {
        //Cylinders
        String cylindersText = getSubElementText(engineElement, "Cylinders");
        int cylinders = Integer.parseInt(cylindersText);
        
        //Capacity
        String capacityText = getSubElementText(engineElement, "capacity");
        int capacity = Integer.parseInt(capacityText);
        
        //HorsePower
        String HorsePowerText = getSubElementText(engineElement, "HorsePower");
        int horsePower = Integer.parseInt(HorsePowerText);
        
        //MPG
        String MpgText = getSubElementText(engineElement, "MPG");
        int mpg = Integer.parseInt(MpgText);
        
        //FuelType
        String fuelString =  getSubElementText(engineElement, "FuelType");
                FuelType type = FuelType.valueOf(fuelString);
        
        //Return
        return new Engine(type, cylinders, capacity, horsePower, mpg);
    }
    
    private Interior getInterior(Element interiorElement) {
        String color1 = getSubElementText(interiorElement, "Color1");
        
        String color2 = getSubElementText(interiorElement, "Color2");
        
        String hasMoonRoofText = getSubElementText(interiorElement, "MoonRoof");
        
        boolean hasMoonRoof = Boolean.parseBoolean(hasMoonRoofText);
        
        String hasSunRoofText = getSubElementText(interiorElement, "SunRoof");
        
        boolean hasSunRoof = Boolean.parseBoolean(hasSunRoofText);
        
        String hasPanoViewText = getSubElementText(interiorElement, "PanoramicView");
        
        boolean hasPanoView = Boolean.parseBoolean(hasPanoViewText);
        
        
        return new Interior(color1, color2, hasMoonRoof, hasSunRoof, hasPanoView);
    }

    private Trunk getTrunk(Element trunkElement) {
        String spareTireText = getSubElementText(trunkElement, "SpareTire");
        Boolean SpareTire = Boolean.parseBoolean(spareTireText);
        
        String firstAidKitText  = getSubElementText(trunkElement, "FirstAidKit");
        Boolean firstAidKit = Boolean.parseBoolean(firstAidKitText);
        
        String carpetText  = getSubElementText(trunkElement, "Carpet");
        Boolean carpet = Boolean.parseBoolean(carpetText);
        
        String jackText  = getSubElementText(trunkElement, "Jack");
        Boolean jack = Boolean.parseBoolean(jackText);
        
        return new Trunk(SpareTire, firstAidKit, carpet, jack);
    }
    
     private void writeEngine(Engine engine) throws XMLStreamException {
        writer.writeStartElement("Engine");
        writeElement("FuelType", engine.getFuelType().toString());
        writeIntElement("Cylinders", engine.getNoOfCylinders());
        writeIntElement("capacity", engine.getCapacity());
        writeIntElement("HorsePower", engine.getHorsePower());
        writeIntElement("MPG", (int) engine.getMpg());
        writer.writeEndElement();
    }
     
     private void writeInterior(Interior interior) throws XMLStreamException {
         writer.writeStartElement("Interior");
         writeElement("Color1", interior.getColor1());
         writeElement("Color2", interior.getColor2());
         writeElement("SunRoof", interior.getSunRoof().toString());
         writeElement("MoonRoof", interior.getMoonRoof().toString());
         writeElement("PanoramicView", interior.getPanoView().toString());
         writer.writeEndElement();
     }
     
     private void writeTrunk(Trunk trunk) throws XMLStreamException {
         writer.writeStartElement("Trunk");
         writeElement("SpareTire", trunk.GetTire().toString());
         writeElement("FirstAidKit", trunk.GetFirstAid().toString());
         writeElement("Carpet", trunk.GetCarpet().toString());
         writeElement("Jack", trunk.GetJack().toString());
         writer.writeEndElement();
     }
     //ExtraCredit
      private ArrayList<String>getModels(String make){
        ArrayList<Car> makes = new ArrayList<>();
        Document docsDoc = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            File file = new File("Models.xml");
            docsDoc = db.parse(file);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.xml.sax.SAXException ex) {
            ex.printStackTrace();
        }
//retrieve document elements
        NodeList carNodes = docsDoc.getElementsByTagName("CarInfo");
        for (int i = 0; i < carNodes.getLength(); i++) {
            {
                Element makeElement = (Element) carNodes.item(i);
                String elementName = makeElement.getAttribute("make");
                String elementModel = makeElement.getAttribute("model");
                 Car makemodels = new Car(elementName, elementModel);
                 
                 makes.add(makemodels);
            }
            
        }
        return null;
    }

}
