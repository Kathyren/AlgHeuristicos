/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

/**
 *
 * @author Carlos Antonio
 */

public class Capital {
    
    public int n;
    public float x;
    public float y;
      
    public Capital(){
        n = -1;
        x = -1;
        y = -1;
    }
    
    public Capital(int a, float b, float c){
        n = a;
        x = b;
        y = c;
    }
    
    public  Capital Copia(){
        return new Capital(n,x,y);
    }
}

