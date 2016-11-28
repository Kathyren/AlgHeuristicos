/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;
import java.util.ArrayList;
import java.util.List;
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
//double [][] CxR = new double[cd][3];
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
                    
            if ((a[i][0] == b[i][0] && a[i][1] == b[i][1]) ||
                (a[i][1] == b[i][0] && a[i][0] == b[i][1])) {
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
                if (j != i){
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
        int[] New_X = new int[path.length];
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
        
        for (int j = 0; j < cd; j++) {
            int i = 0;
            int y=0;
            int ciudad = cutV.get(j)[0].intValue();
            int []arc;
            
            while (i < cd-1 && cutV.size()>0) {
                if (!contains(New_X, ciudad)) {
                    arc = MejorAristaCon(cutV,ciudad,idxCut);
                    if (ciudad==arc[0])
                        y=arc[1];
                    else
                        y=arc[0];  
                    New_X[i] =ciudad;
                    New_X[i+1] =y;
                    cutV.remove(idxCut);
                    i++;
                }
                
            }
        }
        
        /*for (int i= 0; i<cd;i++)
        {
            int x= Integer.valueOf(cutV.get(i)[0].toString());
            int y=0;
            int[] opc;
            if (!contains(New_X, x))
            {
                do 
                {
                    int idx=0;
                    opc = MejorAristaCon(cutV, x,idx);
                    cutV.remove(idx);
                    if (x==opc[0])
                        y=opc[1];
                    else
                        y=opc[0];     
                }while (!(!contains(New_X, y)||opc==null));
                if (!contains(New_X, y))
                    New_X[indx]=x;
                    New_X[indx+1]=y;
                    indx+=2;
                
            }
            
        }*/
    }
    public int[] MejorAristaCon(ArrayList<Double[]> candidatos, int ciudad, int idx)
    {
        int [] mejor = new int[2];
        int prob=0;
        for (int i=0; i<candidatos.size();i++)
        {
            if (candidatos.get(i)[0].intValue()==ciudad ||candidatos.get(i)[1].intValue()==ciudad)
            
                if (candidatos.get(i)[2].floatValue()>prob)
                {
                    mejor[0]=candidatos.get(i)[0].intValue();
                    mejor[1]=candidatos.get(i)[1].intValue();
                    idx=i;
                }
            
        }
        return  mejor;
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
}
