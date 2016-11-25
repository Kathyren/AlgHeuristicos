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
        for (int i = 0; i < cd ; i++) {
            path[i] = -1;
        }        
        pBest = new int[cd];
        velocidad = new double[cd];
        aptitud = 0;        
    }
    public void Particle(Capitals cap){ 
        path = new int[cd];
        path = cap.Revolver();
        pBest = path.clone();
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
    public void cambiarRutas(double k1, double k2, double k3, int[] gBest){
        for(int i=0; i < path.length; i++){
            double r = Math.random()/100;
            int[] nuevaRuta = new int[48];
            boolean elegido = false;
            if(r < k3){
                if(!contains(nuevaRuta, gBest[i])){
                    nuevaRuta[i] = gBest[i];
                    elegido = true;
                }
            }
            if(r < (k3+k2) && !elegido){
                if(!contains(nuevaRuta, pBest[i])){
                    nuevaRuta[i] = pBest[i];
                    elegido = true;
                }
            }
            if(!elegido){
                int j = i;
                int c = path[j];
                while(contains(nuevaRuta, c)){
                    c = path[j];
                    j++;
                    if(j == path.length)
                        j=0;
                }
                nuevaRuta[i] = c;
            }
        }
        return;
    }
    
    public boolean contains(int[] arr, int v){
        for(int i=0;i<arr.length;i++){
            if(arr[i] == v){
                return true;
            }
        }
        return false;
    }
    public int[] getPath(){ return path; }
}
