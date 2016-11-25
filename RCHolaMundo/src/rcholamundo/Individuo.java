/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcholamundo;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Individuo {
    char cad;
    
    // Constructs a randomly placed city
    public Individuo(){
          this.cad=((char) ThreadLocalRandom.current().nextInt(0,255));
    }
    
    // Constructs a city at chosen x, y location
    public Individuo(char cad){
          this.cad=cad;
    }
    
    
    public char getCadena(){
        return this.cad;
    }
    
   
    

    public String toStringcad(){
        return getCadena()+", ";
    }
    
    
}