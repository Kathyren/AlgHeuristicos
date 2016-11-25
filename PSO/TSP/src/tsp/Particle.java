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
    private double aptitude;
    private double velocidad;

    //constructor de la particula
    public void Particle(){ 
        pBest = new int[12];
        path = new int[12];
        aptitude = 0;
        velocidad = 0;
    }
    //public void Particle(){ }
    public double calcAptitud(){ return 0;}
    public double calcVelocidad(double w, double c1, double c2, int[] gBest){ 
        
        velocidad = w * velocidad + c1*(Math.random()*(0))+ c2*(Math.random()*(0));
        return 0; 
    }
    public void actuPath(){ }
}
