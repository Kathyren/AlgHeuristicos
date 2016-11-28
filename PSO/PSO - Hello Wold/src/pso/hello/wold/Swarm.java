/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pso.hello.wold;

/**
 *
 * @author Carlos Antonio
 */
public class Swarm {
    double K1i, K2i, K3i;
    double K1f, K2f, K3f;
    Particle[] particles;
    char[] gBest;
    int gBestApt;
    double k1,k2,k3;
    
    public Swarm(){
        
    }
    
    public Swarm(int NUM, double c2i, double c2f, double c3i, double c3f){
        double c1i = 100 - (c2i + c3i); 
        double c1f = 100 - (c2f + c3f);
        K1i = c1i;
        K2i = c2i;
        K3i = c3i;
        K1f = c1f;
        K2f = c2f;
        K3f = c3f;
        k1=k2=k3=0;
        particles = new Particle[NUM];
        gBest = new char[12];
        for (int i = 0; i < NUM; i++) {
            particles[i] = new Particle();
        }
        System.arraycopy(particles[0].getPbest(), 0, gBest, 0, gBest.length);
    }
    
    public void ejecutar(int iterMax){
        for (int iter = 0; iter < iterMax; iter++) {
            actualizarKs(iterMax, iter);
            actualizarPbests();
            actualizarRutas();
        }
        actualizarPbests();
    }
    
    public void actualizarRutas(){
        for (Particle particle : particles) {
            particle.actualizar(k1, k2, k3, gBest);
        }
    }
    
    public void actualizarPbests(){
        for (Particle particle : particles) {
            particle.calcularAptitud();
            if (particle.getPbestApt() > gBestApt) {
                gBestApt = particle.getPbestApt();
                System.arraycopy(particle.getPath(), 0, gBest, 0, gBest.length);
            }
        }
    }
    
    public void actualizarKs(int iterMax, int iter){
        k1 = (K1f-K1i)/iterMax*iter+K1i;
        if (k1 < 0) k1 = k1*-1;
        k3 = (K2f-K2i)/iterMax*iter+K2i;
        if (k2 < 0) k2 = k2*-1;
        k3 = (K3f-K3i)/iterMax*iter+K3i;
        if (k3 < 0) k3 = k3*-1;
        
        /*k1 = K1f;
        k2 = K2f;
        k3 = K3f;*/
    }
    
    public String getPartString(int i){
        return particles[i].getString();
    }
    
    public String getGbestString(){
        String s = "";
        char c;
        for (int i = 0; i < gBest.length; i++) {
            if (gBest[i] == '@')
                c = 'o';
            else
                c = gBest[i];
            s += c;
        }
        s+= "  Aptitud: " + String.valueOf(gBestApt) + "\n";
        return s;
    }
}
