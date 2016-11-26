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
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class DrawCities {
    
    Graphics g;
    int wi,he;
    
    public DrawCities(Graphics gp, JPanel panel){
        g = gp;
        wi = panel.getWidth();
        he = panel.getHeight();
    }
    
    public void draw(int[] ind){
        Capitals c = new Capitals();
        for (int i = 0; i < ind.length; i++) {
            g.drawRect((int)c.c[ind[i]].x/10, (int)c.c[ind[i]].y/10, 5, 5);
            g.drawLine((int)c.c[ind[i]].x/10,(int)c.c[ind[i]].y/10,(int)c.c[ind[i+1]].x/10,(int)c.c[ind[i+1]].y/10);
            if(i + 1 == ind.length - 1)
                g.drawLine((int)c.c[ind[i]].x/10,(int)c.c[ind[i]].y/10,(int)c.c[ind[0]].x/10,(int)c.c[ind[0]].y/10);
            else
                g.drawLine((int)c.c[ind[i]].x/10,(int)c.c[ind[i]].y/10,(int)c.c[ind[i+1]].x/10,(int)c.c[ind[i+1]].y/10);
        }
        
    }
}