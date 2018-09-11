/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author msalehan
 */
public class Interior {
    private String color1;
    private String color2;
    private boolean hasSunRoof;
    private boolean hasMoonRoof;
    private boolean hasPanoView;

    public Interior(String color1, String color2, boolean hasMoonRoof, boolean hasSunRoof, boolean hasPanoView) {
        this.color1 = color1;
        this.color2 = color2;
        this.hasMoonRoof = hasMoonRoof;
        this.hasSunRoof = hasSunRoof;
        this.hasPanoView = hasPanoView;
      
    }
    
    public String getColor1(){
    return color1;
}
    public String getColor2(){
    return color2;
}
    public Boolean getSunRoof(){
        return hasSunRoof;
    }
    public Boolean getMoonRoof(){
        return hasMoonRoof;
    }
    public Boolean getPanoView(){
        return hasPanoView;
    }
}
    

