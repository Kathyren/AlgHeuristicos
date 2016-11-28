/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pso.hello.wold;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import jdk.nashorn.internal.runtime.arrays.ArrayData;

/**
 *
 * @author Carlos Antonio
 */
public class Particle {
    char[] optimo = { '¡', 'H','o', 'l','a', '_','m', 'u','n', 'd','@','!'};
    char[] path = new char[12];
    char[] pBest = new char[12];
    int aptitud = 0;
    int pBestApt = 0;
    
    public Particle(){
        Random n = new Random();
        int val = 0;
        for (int i = 0; i < path.length; i++) {
            do {
                val = n.nextInt(path.length);
            } while (contains(path, optimo[val]));
            path[i] = optimo[val];
        }
        System.arraycopy(path, 0, pBest, 0, path.length);
    }
    
    public void actualizar(double k1, double k2, double k3, char[] gBest){
        double r;
        boolean sel;
        char[] nuevaRuta = new char[path.length];
        for (int i = 0; i < nuevaRuta.length; i++) {
            nuevaRuta[i] = 'x';
        }
        for (int i = 0; i < path.length; i++) {
            r = Math.random()*100;
            sel = false;
            if (r <= k3){
                if (!contains(nuevaRuta, gBest[i])){
                    nuevaRuta[i] = gBest[i];
                    sel = true;
                }
            }
            if (r <= k2 + k3 && !sel){
                if (!contains(nuevaRuta, pBest[i])){
                    nuevaRuta[i] = pBest[i];
                    sel = true;
                }
            }
            if (!sel){
                int j = i;
                do{
                    if (j == path.length) 
                        j = 0;
                    if (!contains(nuevaRuta, path[j])){
                        nuevaRuta[i] = path[j];
                        sel = true;
                    }
                    j++;
                } while(!sel);
            }
        }
        System.arraycopy(nuevaRuta, 0, path, 0, path.length);
    }
    
    public boolean contains(char[] arr, char v){
        for(int i=0;i<arr.length;i++){
            if(arr[i] == v){
                return true;
            }
        }
        return false;
    }
    
    public String getString(){
        String s = "";
        char c;
        for (int i = 0; i < path.length; i++) {
            if (path[i] == '@')
                c = 'o';
            else
                c = path[i];
            s += c;
        }
        s+= "  Aptitud: " + String.valueOf(aptitud) + "\n";
        return s;
    }
    
    public char[] getPath(){
        return path;
    }
    
    public char[] getPbest(){
        return pBest;
    }
    
    public int getPbestApt(){
        return pBestApt;
    }
    
    public void calcularAptitud(){
        aptitud = 0;
        for (int i = 0; i < path.length; i++) {
            if (optimo[i] == path[i])
                aptitud++;
        }
        if (aptitud > pBestApt){
            System.arraycopy(path, 0, pBest, 0, path.length);
            pBestApt = aptitud;
        }
    }
}
