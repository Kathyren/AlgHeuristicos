/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;
import static tsp.Constants.N_CIUDADES;
import static tsp.Constants.C1min;
      import static tsp.Constants.C1max;
      import static tsp.Constants.C2min;
      import static tsp.Constants.C2max;
      import static tsp.Constants.Kmin;
      import static tsp.Constants.Kmax;

/**
 *
 * @author Carlos Antonio
 */
public class Swarm {
    int[] gBest;//Arreglo del mejor camino 
    double apGBest;//aptitud del mejor camino de todos los tiempos
    int c1,c2,iter;//factores de cambio
    double wMin,wMax,w;//factor de inhercia
    Particle[] particulas;
    
    
    
    public Swarm(){
        apGBest = 999999;
        c1 = C1max;
        c2 = C2min;
        particulas = new Particle[N_CIUDADES];
    }
    public void Generación()
    {
        double mejor;
        for (int i=0; i<N_CIUDADES; i++)
        {
          mejor=  particulas[i].calcAptitud();
          if (mejor<apGBest)
          {
              gBest=particulas[i].getPath();
              apGBest=mejor;
          }
        }
    }
    
    
}
