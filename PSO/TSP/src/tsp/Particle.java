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
public class Particle {
    private int[] pBest;
    private int[] path;
    private double aptitud;
    private double[] velocidad;
    private int cd = 48;
    //constructor de la particula
    public void Particle(){ 
        path = new int[cd];
        pBest = new int[cd];
        velocidad = new double[cd];
        aptitud = 0;        
    }
    //public void Particle(){ }
    public double calcAptitud(double[][] dat){ 
        aptitud = 0;
        for(int i = 0; i < path.length-1; i++){
            aptitud+= dat[path[i]][path[i+1]];
        }
        aptitud+= dat[path[47]][path[0]];
        return aptitud;
    }
    public void calcVelocidad(double w, double c1, double c2, int[] gBest){ 
        for (int i = 0; i < cd ; i++) {
            velocidad[i] = w*velocidad[i] + c1 * Math.random()*(pBest[i]-path[i])+c2*Math.random()*(gBest[i]-path[i]);
        }        
    }
    public void actuPath(){ }
    public int[] getPath(){ return path; }
}
