/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcholamundo;

/**
 *
 * @author Hugo
 */
import java.awt.Graphics;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JPanel;

/**
 *
 * @author Hugo
 */
public class RCHolaMundo {


    public Tour SolucionActual;
    public static float Datos [][];
    DibujarCuidades db;
    public  Tour SolucionOptima;
    
    
    public RCHolaMundo(){
        SolucionActual = new Tour();
        Datos = new float[48][48];
    }
    public static void CalcularDistancias(float [][] dat){
        Capitales capi = new Capitales();
        for (int i = 0; i < 48; i++) {
            for (int j = 0; j < 48; j++) {
                if(i != j)
                    dat[i][j]= distacia(i,j,capi);
            }
        }
    }
    
    
    public static float distacia(int i, int j, Capitales c){
        return (float) Math.sqrt( Math.pow((c.c[j].x - c.c[i].x), 2) +  Math.pow((c.c[j].y - c.c[i].y), 2));
    }
    
    public static double acceptanceProbability(float energy, float newEnergy, double temperature) {

        // Si la nueva solución es mejor, acepta
        if (newEnergy <= energy) {
            return 1;
        }

        // Si la nueva solución es peor, calcule una probabilidad de aceptación
        return Math.exp((energy - newEnergy) / temperature);
    }
    

    public void main(Graphics g, JPanel j){ 
        
        
        double Temperatura = 1000000000;
        
        // Cooling rate
        double RangoCongelado = 0.9999;

        // Initialize intial solution
        
        CalcularDistancias(Datos);

        System.out.println("Distancia de la solucion inicial: " + SolucionActual.getAptitud(Datos));
        System.out.println("Ruta: " + SolucionActual);

        // Set as current best
        SolucionOptima = new Tour(SolucionActual.ind, SolucionActual.aptitud);
        j.update(g);
        db = new DibujarCuidades(g);
        db.DibujarCuidades(SolucionOptima);;
        // Loop until system has cooled
        int c=0;
        while (Temperatura > 1) {
            // Create new neighbour tour
            // Tour NuevaSolucion = new Tour();
            Tour NuevaSolucion = SolucionActual.Copia();
            
            // NuevaSolucion.Mutar();
            NuevaSolucion = NuevaSolucion.alg_2opt();

            // Get energy of solutions
            float CostoActual = SolucionActual.getAptitud(Datos);
            float CostoVecino = NuevaSolucion.getAptitud(Datos);

            // Decidir si debemos aceptar al vecino
            double comparador = Math.random();
           
           if (acceptanceProbability(CostoActual, CostoVecino, Temperatura) > comparador) {
                SolucionActual = new Tour(NuevaSolucion.ind, NuevaSolucion.aptitud);
                
            }
           
            
            if (SolucionActual.getAptitud(Datos) < SolucionOptima.getAptitud(Datos)) {
                SolucionOptima = SolucionActual.Copia();
                
            }
            
            
            if(c == 10){
                Temperatura *= RangoCongelado;
                c= 0;
            }
            else
                c++;          
        }
        j.update(g);
        db = new DibujarCuidades(g);
        db.DibujarCuidades(SolucionOptima);
        System.out.println("Distancia de Solucion Final: " + SolucionOptima.getAptitud(Datos));
        System.out.println("Ruta: " + SolucionOptima);
        
        
    }
    

   
}
