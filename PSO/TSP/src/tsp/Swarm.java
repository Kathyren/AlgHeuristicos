/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;
import static tsp.Constants.N_CIUDADES;

/**
 *
 * @author Carlos Antonio
 */
public class Swarm {
    int[] gBest;
    int apGBest;
    int c1,c2,iter;
    double wMin,wMax,w;
    Particle[] particulas;
    
    public Swarm(){
        apGBest = 999999;
        c1 = 999999;
        c2 = 0;
        particulas = new Particle[N_CIUDADES];
    }
    
    
}
