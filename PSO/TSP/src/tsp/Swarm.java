/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;
import static tsp.Constants.N_CIUDADES;
import static tsp.Constants.wMin;
import static tsp.Constants.wMax;
import static tsp.Constants.C2min;
import static tsp.Constants.C2max;
import static tsp.Constants.C3min;
import static tsp.Constants.C3max;
import static tsp.Constants.N_PARTICLES;
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
    double c1,c2,c3;
    int iter;//factores de cambio
    //double k;
    int cambio;
    //double wMin,wMax,w;//factor de inhercia
    Particle[] particulas;
     private double[][] datos= new double[N_CIUDADES][N_CIUDADES];
    
    
    
    public Swarm(){
        apGBest = 999999;
        
        particulas = new Particle[N_PARTICLES];
        CalcularDistancias(datos);
        for (int i=0; i<N_PARTICLES; i++)
        {
            particulas[i]= new Particle(new Capitals(), datos);
        }
        
    }
    public String Ejecutar()
    {
        definirMejores();
        for (int i=0; i<IterMax;i++)
        {
            cambio++;
            Generación();
            iter=i;
            if (cambio>100) {
                break;
                
            }
            
        }
       // return escribirSolucion();
       return String.valueOf( apGBest)+"\n"+ escribirSolucion();
        
    }
    public void definirMejores(){
        double pBest = 0, act; 
        for (int i=0; i<N_PARTICLES; i++)
        {
            pBest = particulas[i].calcMejorAptitud(datos);
            act=particulas[i].calcAptitud(datos);
            if (pBest>act){
                particulas[i].setPbest(particulas[i].getPath());
            }
            if (particulas[i].getAptitud()<apGBest)
            {
                gBest=particulas[i].getPath();
                apGBest=particulas[i].getAptitud();
                cambio =0;
            }
        }
    }
    public void Generación(){
        actualizar();
        for (int i=0; i<N_PARTICLES; i++){
            //particulas[i].cambiarRutas(w,c1,c2,gBest);
            particulas[i].calcVelocidad(c1, c2, c3, gBest);
            particulas[i].position_updating(c1);
            particulas[i].optimizar();
        
        }
        definirMejores();
    }
    public void actualizar()
    {
        /* 
        w= (wMin-wMax)/(IterMax-1)*(iter-1)+wMax;
        c1= (C1min-C1max)/IterMax*iter+C1max;
        c2= (C2max-C2min)/IterMax*iter+C2min;
        */
        c1= (wMin-wMax)/(IterMax-1)*(iter-1)+wMax;
        c2= (C2min-C2max)/IterMax*iter+C2max;
        c3= (C3max-C3min)/IterMax*iter+C3min;
       // double o= c1+c2;
        //k= 2 / Math.abs(2-o-Math.pow(Math.pow(Math.pow(o,2)-4*o,2),0.5));
    }
    public static void CalcularDistancias(double [][] dat){
        Capitals capi = new Capitals();
        for (int i = 0; i < N_CIUDADES; i++) {
            for (int j = 0; j < N_CIUDADES; j++) {
                if(i != j)
                    dat[i][j]= distacia(i,j,capi);
            }
        }
    }
     public static float distacia(int i, int j, Capitals c){
        return (float) Math.sqrt( Math.pow((c.c[j].x - c.c[i].x), 2) +  Math.pow((c.c[j].y - c.c[i].y), 2));
    }

    private String escribirSolucion() {
        String R="     ";
        for (int i=0; i<N_CIUDADES; i++)
        {
            //2-0-7-8-6-5-11-10-4-9-3-1
            String letra="";
           switch (gBest[i])
           {
               case 2:
                   letra="¡";
                   break;
               case 0:
                   letra="H";
                   break;
               case 7:
                   letra="o";
                   break;
               case 8:
                   letra="l";
                   break;
                case 6:
                   letra="a";
                   break;
               case 5:
                   letra="_";
                   break;
               case 11:
                   letra="M";
                   break;
               case 10:
                   letra="u";
                   break;
                case 4:
                   letra="n";
                   break;
               case 9:
                   letra="d";
                   break;
               case 3:
                   letra="o";
                   break;
               case 1:
                   letra="!";
                   break;
           }
            R+= letra+" - ";
            
        }
        return R;
    }
    public int[] getMejor(){
        return gBest;
    }
    
}
