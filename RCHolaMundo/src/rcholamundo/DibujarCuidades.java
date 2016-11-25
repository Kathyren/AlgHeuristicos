/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcholamundo;

import java.awt.Graphics;
import static java.awt.image.ImageObserver.WIDTH;

/**
 *
 * @author Gerardo
 */
public class DibujarCuidades {
    
    Graphics g;
    
    public DibujarCuidades(Graphics gp){
        g = gp;
    }
    
    public void DibujarCuidades(Tour s){
        
        Capitales c = new Capitales();
        for (int i = 0; i < s.ind.length-1; i++) {
            g.drawRect((int)c.c[s.ind[i]].x/10, (int)c.c[s.ind[i]].y/10, 5, 5);
            if(i + 1 == s.ind.length -1)
                g.drawLine((int)c.c[s.ind[i]].x/10,(int)c.c[s.ind[i]].y/10,(int)c.c[s.ind[0]].x/10,(int)c.c[s.ind[0]].y/10);
            else
                g.drawLine((int)c.c[s.ind[i]].x/10,(int)c.c[s.ind[i]].y/10,(int)c.c[s.ind[i+1]].x/10,(int)c.c[s.ind[i+1]].y/10);
        }
        
    }
    
}
