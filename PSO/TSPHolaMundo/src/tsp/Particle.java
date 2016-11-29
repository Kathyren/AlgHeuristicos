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
                    par[i][j] = 100/distancias[i][j];
            }
        }
        return par;
    }

    public double[][] mEnCeros(){
        double[][] par = new double[cd][cd];
        for (int i = 0; i<cd ; i++ ) {
            for (int j = 0; j<cd ; j++ ) {
                par[i][j] = 0;
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
    
    public double[][] multi(double[] c, int[][] A){

        double [][] CxR = mEnCeros();
//double [][] CxR = new double[cd][3];
        try{
            for (int i=0; i<cd ; i++) {
                int x = A[i][0];// aqui tendria el valor del primer elemento en la reesta
                int y = A[i][1];
                
                if (x>=0 &&y>=0)
                {
                    if (c[i]>1)
                        c[i]=1;
                    if (c[i]<0)
                        c[i]=0;
                    CxR[x][y]=c[i];
                    CxR[y][x]=c[i];
                }
            }
        }
        catch(Exception e){
            e = e;
        }

        return CxR;
    }

    public double[][] WxVelocidades(double w){
        
        double[][] A = new double[cd][cd];
        for (int i = 0; i < cd; i++) {
            for (int j = 0; j < cd; j++) {
                A[i][j] = velocidad[i][j] * w;
                if (A[i][j] > 1) {
                    A[i][j]=1;
                }
                 
            }
        }        
        return A;
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
    public int[][] resta(int [] A, int [] B){ //Aquellos que están en A pero no están en B; !!! Misma posicion!
        
        
        int [][] C = convert(A);//new int[cd][2];
        

        for (int i = 0; i < A.length-1 ; i++ ) {
           for (int j=0; j<A.length-1; j++)
           {
                if ((A[i] == B[j] && A[i+1] == B[j+1]) ||
                    ((A[i] == B[j+1] && A[i+1] == B[j]) ))
                {
                   C[i][0] = -1;
                   C[i][1] = -1;
                }
           }
        }
        for (int j=0; j<A.length-1; j++)
           {
            if ((A[cd-1] == B[0] && A[0] == B[cd-1]) ||
                ((A[cd-1] == B[cd-1] && A[cd-1] == B[0]) ))
            {
               C[cd-1][0] = -1;
               C[cd-1][1] = -1;
            }
            
           }
        return C;
    }

    // Aun no completo
    public void calcVelocidad(double w, double c1, double c2, int[] gBest){ 
        
        double[][] WxP = WxVelocidades(w);

        double[] C1=new double[cd];
        double[] C2=new double[cd];
        for (int j = 0; j < cd ; j++)
        {
             double r =   Math.random();
            double r2 = Math.random();
            C1[j] = c1 * r;
            C2[j] = c2 * r2;
        }
        double[][] r = mEnCeros();
        int[][] res = resta(pBest, path);
        double[][] mult = multi(C1, res);
        int[][] res2 = resta(gBest, path);
        double[][] mult2 = multi(C2, res2);
        for (int j = 0; j < cd ; j++) {
            for ( int i = 0; i < cd; i ++) {              
               
                
                //Fórmula, pero no es suma, es ver cual suma es mayor y asignarla a velocidad[j][i]
                //velocidad[j][i] = WxP[j][i] + multi(c, resta(pBest[j], path[j])) + multi(c2, resta(gBest[j], path[j]));
                if (j != i){
                    
                    if (mult2[j][i] > mult[j][i]) 
                        r[j][i] = mult2[j][i]*w;
                    
                    else
                        r[j][i] = mult[j][i]*w;
                
                 }
                
            }
        }
        for (int i = 0; i < cd; i++) {
            for (int j = 0; j < cd; j++) {
                if (r[i][j] == 0 && i != j) {
                    r[i][j] = WxP[j][i];
                }
            }
        }
        velocidad = r;
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
    
    public void position_updating(double w){
        double a = Math.random();
        double[][] WxP = WxVelocidades(w);
        int[] New_X = mEnMenosUno();
        int indx=0;
        int idxCut = 0;
        ArrayList<Double[]> cutV=new  ArrayList<>();
        //int [] cutV= new int[cd];
        //ArrayList<Integer> cutV=new  ArrayList<Integer>();

        for (int i=0; i<cd; i++)
            for (int j = 0; j < cd; j++)
            {
                if (velocidad[i][j]>a)
                {
                    Double[] x = {Double.valueOf(i),Double.valueOf(j), velocidad[i][j]};
                    cutV.add(x);
                }
            }
        int cuentaNew=1;///Cuantos elementos tiene New_X
        //New_X[0]=ThreadLocalRandom.current().nextInt(0, cd);
        ArrayList<Double[]> candidato = generarCandidatos(cutV,New_X);
        try{
             New_X[0]=candidato.get(0)[0].intValue();
        }
        catch (Exception e)
        {
            New_X[0]=3;
        }
            {
                
                while (candidato.size()>0 && cuentaNew<cd)
                {
                   int idx=0;
                   int prop;
                   prop= MejorAristaCon(candidato,New_X[cuentaNew-1],idx);
                   if (prop>0)
                   {
                       candidato = generarCandidatos(cutV,New_X);
                       New_X[cuentaNew]=prop;
                        cuentaNew++;
                       
                   }
                   else
                       break;
                  
                 
                    
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
                             New_X[cuentaNew] = Candidato;
                             cuentaNew++;
                         }
                         else
                             break;

                    }while (cuentaNew<cd);
                }     
                
                while (cuentaNew<cd) {       
                    int i= New_X[cuentaNew-1];               
                    double probMax=Math.random();;
                     for (int j = 0; j < cd; j++) {
                         try{
                        if (!contains(New_X, j)) {
                            if (probMax < velocidad[i][j]) {
                                New_X[cuentaNew] = j;
                                cuentaNew++;
                                break;
                                //probMax = velocidad[i][j];
                            }
                           /* if (probMax < WxP[i][j]) {
                                New_X[cuentaNew] = j;
                                probMax = WxP[i][j];
                            }*/
                            
                        }
                         }
                         catch(Exception e)
                                 {
                                 e=e;
                                 }
                    }
                    
                }
                path = New_X;
            }
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
                    path[i+1] = aux;
                }
            }
        }
    }
}
