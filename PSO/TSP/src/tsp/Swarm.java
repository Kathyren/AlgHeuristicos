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
import static tsp.Constants.IterMax;

/**
 *
 * @author Carlos Antonio
 */
public class Swarm {
    int[] gBest;//Arreglo del mejor camino 
    double apGBest;//aptitud del mejor camino de todos los tiempos
    int c1,c2,iter;//factores de cambio
    double k;
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
        actualizar();
    }
    public void actualizar()
    {
        c1= (C1min-C1max)/IterMax*iter+C1max;
        c2= (C2max-C2min)/IterMax*iter+C2min;
        w= (wMin-wMax)/(IterMax-1)*iter-1+wMax;
        int o= c1+c2;
        k= 2 / Math.abs(2-o-Math.pow(Math.pow(o^2-4*o,2),0.5));
    }
    
    
}
