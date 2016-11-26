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
    private double[][] velocidad;
    private int cd = 48;
    //constructor de la particula
    public void Particle(){ 
        path = new int[cd];
        for (int i = 0; i < cd ; i++) {
            path[i] = -1;
        }        
        pBest = new int[cd];
        velocidad = probabilidades();
        aptitud = 999999;        
    }
    public void Particle(Capitals cap){ 
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
    public int[] multi(int c, int [] A){ //en construccion
        return new int [cd];
    }

    public double[][] WxVelocidades(double w){
        
        double[][] A = new double[48][48];
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
        aptitud+= dat[path[47]][path[0]];
        return aptitud;
    }
    //PBest - X || gBest - X
    public int[] resta(int [] A, int [] B){ //Aquellos que están en A pero no están en B
        
        int [] C = new int[cd];
        boolean agrega = false;

        for (int i = 0; i < A.length ; i++ ) {
            for (int j = 0; j < B.length ; j++ ) {            
                if (A[i] != B[j]) {
                    agrega = true;
                }
                else{
                    agrega = false;
                    break;
                }
            }
            if (agrega) {
                C[i] = A[i];
            }        
        }
        return C;
    }

    public void calcVelocidad(double w, double c1, double c2, int[] gBest){ 
        for (int j = 0; j < cd ; j++) {
            for ( int i = 0; i < cd; i ++) {
                //calcular cada velocidad[j][i]
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
