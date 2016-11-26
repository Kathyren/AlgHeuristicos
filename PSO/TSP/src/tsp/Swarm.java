/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;
import java.awt.Graphics;
import java.lang.*;
import javax.swing.JPanel;
import static tsp.Constants.N_CIUDADES;
import static tsp.Constants.C1min;
import static tsp.Constants.C1max;
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
    double k;
    double wMin,wMax,w;//factor de inhercia
    Particle[] particulas;
     private double[][] datos= new double[N_CIUDADES][N_CIUDADES];
    
    
    
    public Swarm(){
        apGBest = 999999;
        particulas = new Particle[N_PARTICLES];
        for (int i=0; i<N_PARTICLES; i++)
        {
            particulas[i]= new Particle(new Capitals());
        }
        CalcularDistancias(datos);
    }
    public String Ejecutar(JPanel panel)
    {
        Graphics gr = panel.getGraphics();
        DrawCities dc = new DrawCities(gr,panel);
        for (int i=0; i<IterMax;i++)
        {
            Generación(dc);
            iter=i;
            panel.update(gr);
        }
       // return escribirSolucion();
       return String.valueOf( apGBest)+"\n"+ escribirSolucion();
        
    }
<<<<<<< HEAD
    public void Generación(DrawCities dc)
    {
=======
    public void definirMejores(){
>>>>>>> b35b4cc78471e7b463d4125aed28ef657edd8f9d
        double aptAnt = 0; 
        for (int i=0; i<N_PARTICLES; i++)
        {
            aptAnt = particulas[i].getAptitud();
            particulas[i].calcAptitud(datos);
            if (particulas[i].getAptitud()<aptAnt){
                particulas[i].setPbest(particulas[i].getPath());
            }
            if (particulas[i].getAptitud()<apGBest)
            {
                gBest=particulas[i].getPath();
                apGBest=particulas[i].getAptitud();
            }
        }
    }
    public void Generación(){
        definirMejores();
        actualizar();
<<<<<<< HEAD
        for (int i=0; i<N_CIUDADES; i++){
            //particulas[i].cambiarRutas(w,c1,c2,gBest);
=======
        for (int i=0; i<N_PARTICLES; i++){
            particulas[i].cambiarRutas(w,c1,c2,gBest);
>>>>>>> b35b4cc78471e7b463d4125aed28ef657edd8f9d
        }
        dc.draw(gBest);
    }
    public void actualizar()
    {
        /* 
        w= (wMin-wMax)/(IterMax-1)*(iter-1)+wMax;
        c1= (C1min-C1max)/IterMax*iter+C1max;
        c2= (C2max-C2min)/IterMax*iter+C2min;
        */
        c1= (C1min-C1max)/IterMax*iter+C1max;
        c2= (C2min-C2max)/IterMax*iter+C2max;
        c3= (C3min-C3max)/IterMax*iter+C3max;
        double o= c1+c2;
        k= 2 / Math.abs(2-o-Math.pow(Math.pow(Math.pow(o,2)-4*o,2),0.5));
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
        String R="";
        for (int i=0; i<N_CIUDADES; i++)
        {
            R+= String.valueOf(gBest[i])+" - ";
            
        }
        return R;
    }
    public int[] getMejor(){
        return gBest;
    }
    
}
