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
    private int aptitude;
    private int velocidad;

    //constructor de la particula
    public void Particle(){ 
        pBest = new int[48];
        path = new int[48];
        aptitude = 0;
        velocidad = 0;
    }
    //public void Particle(){ }
    public double calcAptitud(){ return 0;}
    public double calcVelocidad(int c1, int c2, int[] gBest){ return 0; }
    public void actuPath(){ }
}
