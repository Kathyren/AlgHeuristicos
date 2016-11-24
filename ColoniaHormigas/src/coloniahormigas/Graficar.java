/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sabe La Paz
 */

import java.awt.Color;
import java.awt.Graphics;

public class Graficar {
    public static void dibujar(Graphics g, int[]camino, double aptitud, double[][] capitales, double[][] distancias, float width, float height){
        //maximo en width = 8,000
        //maximo en height = 6,000
        int marginTop=50;
        float pixpw = width / 8000;
        float pixph = (height-marginTop) / 6000;
        
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, (int)width, (int)height);
        
        g.setColor(Color.BLUE);
        for(int i=0;i<camino.length-1;i++){
            
            int px1 = (int)(capitales[camino[i]][1] * pixpw)+10;
            int py1 = (int)(capitales[camino[i]][2] * pixph) + marginTop+10;
            int px2 = (int)(capitales[camino[i+1]][1] * pixpw)+10;
            int py2 = (int)(capitales[camino[i+1]][2] * pixph) + marginTop+10;
            
            g.drawLine(px1, py1, px2, py2);
        }
        
        int px1 = (int)(capitales[camino[camino.length-1]][1] * pixpw)+10;
        int py1 = (int)(capitales[camino[camino.length-1]][2] * pixph) + marginTop+10;
        int px2 = (int)(capitales[camino[0]][1] * pixpw)+10;
        int py2 = (int)(capitales[camino[0]][2] * pixph) + marginTop+10;
            
        g.drawLine(px1, py1, px2, py2);
        
        for(int i=0;i<camino.length;i++){
            
            int px = (int)(capitales[i][1] * pixpw);
            int py = (int)(capitales[i][2] * pixph) + marginTop;
            
            g.drawOval(px, py, 20, 20);
            g.setColor(Color.RED);
            String cp = camino[i]+"";
            g.drawString(cp, px+5, py+15);
            g.setColor(Color.BLACK);
            cp = (i+1)+"";
            g.drawString(cp, px, py);
        }
        
        String apt = aptitud+"";
        
        g.drawString(apt, 100, 100);
    }
}

