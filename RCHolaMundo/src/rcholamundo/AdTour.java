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
/*
* TourManager.java
* Holds the cities of a tour
*/


import java.util.ArrayList;

public class AdTour {

    // Holds our cities
    private static ArrayList combinacion = new ArrayList<Individuo>();

    
    public static void AgregarCadena(Individuo cadena) {
        combinacion.add(cadena);
    }
    
    
    // Get a city
    public static Individuo getCapital(int index){
        return (Individuo)combinacion.get(index);
    }
    
    // Get the number of destination cities
    public static int NumerodeCapitales(){
        return combinacion.size();
    }
    
}
