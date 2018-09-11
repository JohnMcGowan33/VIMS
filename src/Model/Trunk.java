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
public class Trunk {
    private boolean hasSpareTire;
    private boolean hasFirstAidKit;
    private boolean hasCarpet;
    private boolean hasJack;
    
    public Trunk(boolean hasSpareTire, boolean hasFirstAidKit, boolean hasCarpet, boolean hasJack){
        this.hasCarpet = hasCarpet;
        this.hasFirstAidKit = hasFirstAidKit;
        this.hasJack = hasJack;
        this.hasSpareTire = hasSpareTire;
    }
    
    public Boolean GetTire(){ return hasSpareTire; }
        public Boolean GetFirstAid(){ return hasFirstAidKit; }
            public Boolean GetCarpet(){ return hasCarpet; }
             public Boolean GetJack(){ return hasJack; }
}

