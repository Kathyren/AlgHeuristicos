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
public class Particle {
   private int[] pBest;
    private int[] path;
    private double aptitud;
    private double[][] velocidad;
    private int cd = N_CIUDADES;
    //constructor de la particula
    public  Particle(){ 
        path = new int[cd];
        for (int i = 0; i < cd ; i++) {
            path[i] = -1;
        }        
        pBest = new int[cd];
        velocidad = probabilidades();
        aptitud = 999999;        
    }
    public  Particle(Capitals cap){ 
        path = new int[cd];
        path = cap.Revolver();
        pBest = path.clone();
        velocidad = probabilidades();
        aptitud = 999999;        
    }    

    //inicializar la velociadades en random 
    public double[][] probabilidades(){
        double[][] par = new double[cd][cd];
        for (int i = 0; i<cd ; i++ ) {
            for (int j = 0; j<cd ; j++ ) {
                par[i][j] = Math.random();
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
    
    public double[][] multi(double[] c, int[][] A){

        double [][] CxR = mEnCeros();

        for (int i=0; i<cd ; i++) {
            int x = A[i][0];// aqui tendria el valor del primer elemento en la reesta
            int y = A[i][1];
            if (c[i]>1)
                c[i]=1;
            if (c[i]<0)
                c[i]=0;
            CxR[x][y]=c[i];
            CxR[y][x]=c[i];
        }

        return CxR;
    }

    public double[][] WxVelocidades(double w){
        
        double[][] A = new double[cd][cd];
        for (int i = 0; i < cd; i++) {
            for (int j = 0; j < cd; j++) {
                A[i][j] = velocidad[i][j] * w;
            }
        }        
        return A;
    }

    public double calcAptitud(double[][] dat){ 
        aptitud = 0;
        for(int i = 0; i < path.length-1; i++){
            aptitud+= dat[path[i]][path[i+1]];
        }
        aptitud+= dat[path[cd-1]][path[0]];
        return aptitud;
    }
    //PBest - X || gBest - X
    public int[][] resta(int [] A, int [] B){ //Aquellos que están en A pero no están en B; !!! Misma posicion!
        
        int [][] a = convert(A);
        int [][] b = convert(B);
        int [][] C = new int[cd][2];
        boolean agrega = false;

        for (int i = 0; i < A.length ; i++ ) {
                    
<<<<<<< HEAD
            if (A[i] == B[i]) {
=======
            if ((a[i][0] == b[i][0] && a[i][1] == b[i][1]) ||
                (a[i][1] == b[i][0] && a[i][0] == b[i][1])) {
>>>>>>> 96b376cf05aff42edcd9a5ab8fbf35230915b0d3
                agrega = true;
            }
            else{
                agrega = false;                
            }            
            if (!agrega) {
                C[i][0] = a[i][0];
                C[i][1] = a[i][1];
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
                if (WxP[j][i] > mult[j][i]) {
                    r[j][i] = WxP[j][i];
                }
                else
                    r[j][i] = mult[j][i];
                

                if (mult2[j][i] > r[j][i]) {
                    r[j][i] = mult2[j][i];
                }                
            }
        }        
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
<<<<<<< HEAD
        int[] nuevaRuta = new int[N_CIUDADES];
        for(int i=0; i < path.length; i++){
            double r = Math.random();
            
=======
        
        int[] nuevaRuta = new int[cd];
        for (int j = 0; j < N_CIUDADES; j++){
            nuevaRuta[j] = -1;
        }
        for(int i=0; i < path.length; i++){
            double r = Math.random();
>>>>>>> 96b376cf05aff42edcd9a5ab8fbf35230915b0d3
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
                int b= nuevaRuta[i-1];
                
            }
        }
        for (int i = 0; i < N_CIUDADES; i++) {
            path[i] = nuevaRuta[i];
        }
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
}
