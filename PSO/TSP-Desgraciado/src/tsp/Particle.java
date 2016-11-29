/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;
import static java.lang.Math.max;
import static java.lang.Math.max;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import static tsp.Constants.N_CIUDADES;
/**
 *
 * @author Carlos Antonio
 */
public class Particle {
   private int[] pBest;
    private int[] path;
    private double aptitud;
    public double mejorAptitud;
    private double[][] velocidad;
    private int cd = N_CIUDADES;
    double[][] distancias;
    int c1=2;
    int c2=2;
    double w=0.5;
    //constructor de la particula
    public  Particle(){ 
        path = new int[cd];
        for (int i = 0; i < cd ; i++) {
            path[i] = -1;
        }        
        pBest = new int[cd];
        velocidad = probabilidades();
        aptitud = 9999999; 
        mejorAptitud=99999999;
    }
    public  Particle(Capitals cap, double[][]dat){ 
        distancias= dat;
        path = new int[cd];
        path = cap.Revolver();
        pBest = path.clone();
        velocidad = probabilidades();
        aptitud = 999999; 
        mejorAptitud=99999999;
    }    

    //inicializar la velociadades en random 
    public double[][] probabilidades(){
        double[][] par = new double[cd][cd];
        for (int i = 0; i<cd ; i++ ) {
            for (int j = 0; j<cd ; j++ ) {
                if (i==j)
                    par[i][j]=0;
                else
                    par[i][j] = 1000/distancias[i][j];
            }
        }
        return par;
    }

    public int[] mEnMenosUno(){
        int[] par = new int[cd];
       
            for (int j = 0; j<cd ; j++ ) {
                par[j] = -1;
            }
        
        return par;
    }
    
    
    

    public void WxVelocidades(double w){
        
        for (int i = 0; i < cd; i++) {
            for (int j = 0; j < cd; j++) {
                if (j!=i)
                {
                    velocidad[i][j] *= w;
                    if (velocidad[i][j] > 1) 
                    {
                         velocidad[i][j]=1;
                    }
                }
                 
            }
        }        
        
    }

    public double calcAptitud(double[][] dat){ 
        
        aptitud = 0;
        try{                    
            for(int i = 0; i < path.length-1; i++){
                aptitud+= dat[path[i]][path[i+1]];
            }
            aptitud+= dat[path[cd-1]][path[0]];
        }
        catch(Exception e){
            e=e;
        }
        return aptitud;
    }
    public double calcMejorAptitud(double[][] dat){ 
        mejorAptitud = 0;
        try{                    
            for(int i = 0; i < cd-1; i++){
                mejorAptitud+= dat[pBest[i]][pBest[i+1]];
            }
            mejorAptitud+= dat[pBest[cd-1]][pBest[0]];
        }
        catch(Exception e){
            e=e;
        }
        return mejorAptitud;
    }
    //PBest - X || gBest - X
    public void resta(int [] A, int [] B){ //Aquellos que están en A pero no están en B; !!! Misma posicion!
        
       
        double r = Math.random();
        Boolean existe=false;
        for (int i = 0; i < A.length-1 ; i++ ) 
        {
            existe=false;
           for (int j=0; j<A.length-1; j++)
           {
                if ((A[i] == B[j] && A[i+1] == B[j+1]) ||
                    ((A[i] == B[j+1] && A[i+1] == B[j]) ))
                {
                   
                   existe=true;
                   break;
                }
               
           }
            if (!existe)
                {
                    r = Math.random();
                    double x= r*c1;
                    if (velocidad[i][i+1]<r*c1)
                    {
                        velocidad[i][i+1]=x;
                        velocidad[i+1][i]=x;
                    }
                }
           
        }
        for (int j=0; j<A.length-1; j++)
           {
            if ((A[cd-1] == B[j] && A[0] == B[j+1]) ||
                ((A[cd-1] == B[j+1] && A[cd-1] == B[j]) ))
            {
               existe=true;
               break;
            }
            
           }
         if (!existe)
         {
                    double x= r*c1;
                    if (velocidad[0][cd-1]<r*c1)
                    {
                        velocidad[cd-1][0]=x;
                        velocidad[0][cd-1]=x;
                    }
         }
        
    }

    // Aun no completo
    public void calcVelocidad(double w, double c1, double c2, int[] gBest){ 
        
        WxVelocidades(w);

        resta(pBest, path);
        resta(gBest, path);
        
    }
    
    public int[] getPbest(){
        return pBest;
    }
    public void setPbest(int[] ruta){
        pBest = ruta.clone();
    }
    public double getAptitud(){
        return aptitud;
    }
    public void actuPath(){ 
    }
    /**
     * 
     * @param k1
     * @param k2
     * @param k3
     * @param gBest 
     */
    public void cambiarRutas(double k1, double k2, double k3, int[] gBest){

        
    } 
    public boolean contains(int[] arr, int v){
        for(int i=0;i<arr.length;i++){
            if(arr[i] == v){
                return true;
            }
        }
        return false;
    }
    public int containsIn(int[] arr, int v){
        for(int i=0;i<arr.length;i++){
            if(arr[i] == v){
                return i;
            }
        }
        return -1;
    }
    public int[] getPath(){ return path; }

    public int[][] convert(int[] A){
        
        int [][] C = new int[A.length][2];

        for (int i= 0; i<A.length-1 ; i++)
        {
            int x = A[i];
            int y = A[i+1];
            C[i][0] = x;
            C[i][1] = y;
        }
        C[A.length-1][0] = A[A.length-1];
        C[A.length-1][1] = A[0];
        return C;
    }
    
    public void position_updating(){
        double a = Math.random();
        int[] New_X = mEnMenosUno();
        double[]ruleta= new double[48];
        double sumaProb=0;
        int cuentaNew=1;///Cuantos elementos tiene New_X
       New_X[0]=0;
       double prob;
       boolean go=true;
       while ( cuentaNew<cd&&go)
        {
           prob=Math.random();
           int cd1= New_X[cuentaNew-1];
           go=false;
           for (int j=0; j<cd;j++)
           {
                if (prob<velocidad[cd1][j]&&!contains(New_X, j))
                {
                    ruleta[j]=velocidad[cd1][j];
                    sumaProb+=velocidad[cd1][j];

                     go = true;
                }
                else
                {
                    ruleta[j]=0;
                }
            
           }
           if (go)
           {
                a = Math.random();
                double probAc=0;
                int ind=-1;
                while (probAc < a) {
                    if (ind>46)
                        ind=-1;
                    ind++;
                         probAc += ruleta[ind]/sumaProb;
                         
                         
                 }
                New_X[cuentaNew]=ind;
                cuentaNew++;
           }
           
        }
        if (cuentaNew<cd)
         {
             do
              {  
                 int pos= containsIn(path,New_X[cuentaNew-1]);
                 if (pos==cd-1)
                 {pos =-1;}
                    int Candidato = path[pos+1];
                  if (!contains(New_X, Candidato)) {
                      go = true;
                      New_X[cuentaNew] = Candidato;
                      cuentaNew++;
                  }
                  else
                      break;

             }while (cuentaNew<cd);
         }     
                
        while (cuentaNew<cd)
        {       
                int i= New_X[cuentaNew-1];               
                prob=Math.random()*0.5;
                sumaProb=0;
                 for (int j = 0; j < cd; j++) 
                {
                    if (!contains(New_X, j) )
                            {
                                go=true;
                                ruleta[j]=velocidad[i][j];
                                sumaProb+=velocidad[i][j];
                                /*New_X[cuentaNew] = j;
                                
                               cuentaNew++;
                               break;*/
                            }
                    else
                        ruleta[j]=0;
                 }
                 
                  
                 if (go)
                {
                    if (sumaProb>0)
                        
                    {
                        a = Math.random();
                        double probAc=0;
                        int ind=-1;
                        while (probAc < a) {
                            if (ind>46)
                                ind=-1;
                            ind++;
                                 probAc += ruleta[ind]/sumaProb;


                         }

                        New_X[cuentaNew]=ind;
                        if (New_X[cuentaNew]==New_X[cuentaNew-1])
                            a=a;
                        cuentaNew++;
                    }
                    else
                   {
                       a=a;
                    }
                }

         }
            path = New_X;
            
    }
        
       
    
    public int MejorAristaCon(ArrayList<Double[]> candidatos, int ciudad, int idx)
    {
        int [] mejor = new int[2];
        double prob=0;
        for (int i=0; i<candidatos.size();i++)
        {
            if (candidatos.get(i)[0].intValue()==ciudad ||candidatos.get(i)[1].intValue()==ciudad)
            
                if (candidatos.get(i)[2].floatValue()>prob)
                {
                    mejor[0]=candidatos.get(i)[0].intValue();
                    mejor[1]=candidatos.get(i)[1].intValue();
                    prob=candidatos.get(i)[2].floatValue();
                    idx=i;
                }
        }
        if (prob==0)
        {
            return -1;
        }
        if (mejor[0]==ciudad)
            return  mejor[1];
        return mejor[0];
    }
    public int[] MejorAristaCon(double[][] candidatos, int ciudad)
    {
        int [] mejor = new int[2];
        int prob=0;
        for (int i=0; i<candidatos.length;i++)
        {
            if ((int)candidatos[i][0]==ciudad ||(int)candidatos[i][1]==ciudad)
            
                if (candidatos[i][2]>prob)
                {
                    mejor[0]=(int)candidatos[i][0];
                    mejor[1]=(int)candidatos[i][1];
            
                }        
        }
        return  mejor;
    }
    /*
        Regresa todos los candidatos que no estan en X
    */
    public ArrayList<Double[]> generarCandidatos( ArrayList<Double[]> cut, int[] x)
    {
         ArrayList<Double[]> candidato= new ArrayList<>();
         int cant = cut.size();
         for (int i=0; i<cant;i++)
         {
             int v1= cut.get(i)[0].intValue();
             int v2= cut.get(i)[1].intValue();
             
             if (!(contains(x, v2)||contains(x, v1)))
             {
                 candidato.add(cut.get(i));
             }
             
         }
        
        
        return candidato;
    }
    /**
     * Regresa todos los candidatos uno de sus puntos no estan en x
    */
     public ArrayList<Double[]> generarCandidatos( ArrayList<Double[]> cut, int[] x, int ciudad)
    {
         ArrayList<Double[]> candidato= new ArrayList<>();
         if (x.length<1)
         {
             candidato=cut;
             return candidato;
         }
         int cant = cut.size();
         for (int i=0; i<cant;i++)
         {
             int v1= cut.get(i)[0].intValue();
             int v2= cut.get(i)[1].intValue();
             if (ciudad==v1 || ciudad==v2)
             if (!(contains(x, v2)&&ciudad==v1||contains(x, v1)&&ciudad!=v2))
             {
                 candidato.add(cut.get(i));
             }
             
         }

        return candidato;
    }
     public void optimizar() {
        // Agregar su código de optimización
        for (int i = 0; i < cd-2; i++) {
            for (int j = i+1; j < cd-1; j++) {
                if ((distancias[path[i]][path[i+1]] + distancias[path[j]][path[j+1]]) > 
                        (distancias[path[i]][path[j]] + distancias[path[i+1]][path[j+1]])){
                    int aux = path[j];
                    path[j] = path[i+1];
                    velocidad[path[i]][path[j]]+=100/distancias[i][j]*w;
                    velocidad[path[i+1]][path[j+1]]+=100/distancias[i][j]*w;/*
                    velocidad[path[i]][path[i+1]]-=100/distancias[i][j];
                    velocidad[path[j]][path[j+1]]-=100/distancias[i][j];*/
                    path[i+1] = aux;
                }
            }
        }
    }
}
