/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.text.NumberFormat;

/**
 *
 * @author msalehan
 */
public class Car {
    private String make;
    private int year;
    private String model;
    private int price;
    private String color; 
    private CarType type;
    private Engine engine; 
    private Interior interior;
    private Trunk trunk; 
    private int miles;
    private String imagePath;
    
    public Car (String make, int year, String model, int price,
            String color, int miles, CarType type){
        this.make = make;
        this.year = year;
        this.model = model;
        this.price = price;
        this.color = color;
        this.miles = miles;
        this.type = type;
        this.engine = engine;
        this.interior = interior;
        this.trunk = trunk;
    }
    
    public Car (String make, String model) {
        this.make = make;
        this.model = model;
    }
    public Car (String make, int year, String model, int price,
            String color, int miles, CarType type, Engine engine,
            Interior interior, Trunk trunk){
        
        this.make = make;
        this.year = year;
        this.model = model;
        this.price = price;
        this.color = color;
        this.miles = miles;
        this.type = type;
        this.engine = engine;
        this.interior = interior;
        this.trunk = trunk;
        
    }
     public Car (String make, int year, String model, int price,
            String color, int miles, CarType type, Engine engine,
            Interior interior, Trunk trunk, String imagePath){
        
        this.make = make;
        this.year = year;
        this.model = model;
        this.price = price;
        this.color = color;
        this.miles = miles;
        this.type = type;
        this.engine = engine;
        this.interior = interior;
        this.trunk = trunk;
        this.imagePath = imagePath;
        
        
    }
     public String getImage() {
String sentence = imagePath;
String[] tokens = sentence.split(" ");
String lastToken = tokens[tokens.length-1];
String result = lastToken.substring(lastToken.lastIndexOf('/') + 1).trim();



  this.imagePath = result;
  
  System.out.println("ImageTest" + result);
        return imagePath;
    }
     
    public CarType getType() {
        return type;
    }
    public String getColor() {
        return color;
    }
    public String getMake(){
        return make;
    }
    
    public Engine getEngine(){
        return this.engine;
    }
    
   public Trunk getTrunk() {
       return trunk;
   }
    
    public Interior getInterior(){
        return interior;
    }
    
    public int getYear(){
        return year;
    }
    public int getMiles(){
        return miles;
    }
    public int getPrice(){
        
        
        return price;
    }
    
    
    
    public void SetEngine(Engine engine){
        this.engine = engine;
    }
    
    
    public void SetInterior(Interior interior){
        
    }
    public void SetTrunk(Trunk trunk){}
    
    public String OpenTrunk(){return null;}
    public String CheckEngine(){
        return null;
    }
    public String CheckInterior(){return null;}
    public String ShowOptions(){return null;}
    public String toString(){
        return null;
        
    }
    public String CompareTo (Car otherCar){
        return null;
    }
    
    public String getModel() {
        return model;
    }
}
