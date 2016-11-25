/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcholamundo;

/**
 *
 * @author Hugo
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Tour{

    // Holds our tour of cities
    int ind [];
    float aptitud;
    
    
    // Cache
    
    // Constructs a blank tour
    public Tour(){
        ind = new int[48];
        Capitales capi = new Capitales();
        ind = capi.Revolver();
        aptitud = 0;
    }
    
    public Tour(int a){
        ind = new int[48];
        for (int i = 0; i < ind.length; i++) {
            ind[i] = a;
        }
    }
    
    // Construye otra cadena

    // Returns tour information

 public Tour(int []ip, float ap){
        ind = new int[48];
        for (int i = 0; i < ind.length; i++) {
            ind[i] = ip[i];
        }
        aptitud = ap;
    }
 
    public Tour Copia(){
        return new Tour(ind, aptitud);
    }
    
    public Tour alg_2opt(){
        int i = 0, k = 0;
        Random azar1 = new Random();
        i = (int)(azar1.nextDouble()*47 + 0);
        do {
            k = (int)(azar1.nextDouble()*48 + 0);
        } while (i >= k);
        int[] arr = cambia(ind, i, k);
        return new Tour(arr, 0);
    }

    public int[] cambia(int[] arr, int i, int k){
        int[] mod = new int[arr.length];
        int j = 0;
        for (j = 0; j <= i - 1; j++){
             mod[j] = arr[j];
        }
        for (int p = k; p >= i; p--) {
            mod[j] = arr[p];
            j++;
        }
        for (j = k+1; j < arr.length; j++){
            mod[j] = arr[j];
        }
        return mod;
    }
    
    public void Mutar(){
    Random azar1 = new Random();
        
    int r0, r1;
    r0 = (int)(azar1.nextDouble() * 48 + 0);
    r1 = (int)(azar1.nextDouble() * 48 + 0);
        
    while(r0 == r1)
       r0 = (int)(azar1.nextDouble() * 48 + 0);
        
    Tour a = this.Copia();

    ind[r0] = a.ind[r1];
    ind[r1] = a.ind[r0];
    }
        
    public float getAptitud(float [][] dat){
        aptitud = 0;
        for(int i = 0; i < ind.length-1; i++){
            aptitud+= dat[ind[i]][ind[i+1]];
        }
        aptitud+= dat[ind[47]][ind[0]];
        return aptitud;
    }

}