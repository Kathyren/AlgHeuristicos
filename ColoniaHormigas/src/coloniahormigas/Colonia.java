/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coloniahormigas;

/**
 *
 * @author Karen
 */
public class Colonia {
    private int tamañoProblema;
    private int[][] datos;
    private double[][] distancias;

    private double[][] feromona;
    private double feromonaInicial=0.5;
    private double reduccionFeromona;

    private Hormiga[] hormigas;
    private Hormiga mejor;

    private String texto = "";

   
    public String getProceso() { return texto; }

    public Colonia(int tamañoProblema, int[][] datos, int numeroHormigas, double reduccionFeromona) {
        this.tamañoProblema = tamañoProblema;
        this.datos = datos;
        this.reduccionFeromona = reduccionFeromona;

        inicializarDistancias();
        inicializarFeromona();

        hormigas = new Hormiga[numeroHormigas];

        for (int i = 0; i < numeroHormigas; i++) {
            hormigas[i] = new Hormiga(this.tamañoProblema, distancias);
        }
    }

    public Hormiga getMejor() {
        return mejor;
    }

    private void inicializarDistancias() {
        distancias = new double[datos.length][datos.length];

        for (int f = 0; f < tamañoProblema; f++) {
            for (int c = 0; c < tamañoProblema; c++) {
                distancias[f][c] = Math.sqrt(Math.pow(datos[c][0] - datos[f][0], 2) +
                        Math.pow(datos[c][1] - datos[f][1], 2));
            }
        }
    }

    private void inicializarFeromona() {
        feromona = new double[tamañoProblema][tamañoProblema];
        mejor = Hormiga.candidatoInicial(tamañoProblema, distancias);
        // En este punto, deben usar algún método de optimización para mejorar los resultados. Ejemplo: mejor.optimizar();
        //mejor.optimizar();
       // feromonaInicial = 1d / (tamañoProblema * mejor.getAptitud());

        for (int f = 0; f < tamañoProblema; f++) {
            for (int c = 0; c < tamañoProblema; c++) {
                if (f == c)
                    continue;

                feromona[f][c] = feromonaInicial;
            }
        }
    }

    public Hormiga buscarSolucion(int condicionParada) {
        int iteraciones = 0;
        double mejorAptitud = mejor.getAptitud();

        while (iteraciones < condicionParada) {
            construirCaminos();
            actualizarFeromonaGlobal();

            if (mejorAptitud > mejor.getAptitud()) {
                iteraciones = 0;
                mejorAptitud = mejor.getAptitud();
            }

            iteraciones++;
        }

        return mejor;
    }

    private void construirCaminos() {
        for (Hormiga hormiga : hormigas) {
            hormiga.construirCamino(feromona);
            hormiga.optimizar();
            actualizarFeromonaLocal(hormiga);

            if (mejor.getAptitud() > hormiga.getAptitud()) {
                mejor = hormiga.clonar();
                texto = texto + "Hormiga: " + mejor.aString() + "  Aptitud: " +mejor.getAptitud() + "\n "; // Solo sirve con el problema ¡Hola Mundo!
            }
        }
    }

    private void actualizarFeromonaLocal(Hormiga local) {
        int iF;
        int jF;
        double nuevaFeromona=0;

        for (int j = 0; j < tamañoProblema - 1; j++) {
            iF = local.getTour()[j];
            jF = local.getTour()[j + 1];
           // nuevaFeromona = (1d - reduccionFeromona) * feromona[iF][jF] + (reduccionFeromona * feromonaInicial);
           nuevaFeromona=1d/mejor.getAptitud();
            feromona[iF][jF] = nuevaFeromona;
            feromona[jF][iF] = nuevaFeromona;
            
            
        }

            
        iF = local.getTour()[tamañoProblema - 1];
        jF = local.getTour()[0];
        //nuevaFeromona = (1d - reduccionFeromona) * feromona[iF][jF] + (reduccionFeromona * feromonaInicial);
        feromona[iF][jF] = nuevaFeromona;
        feromona[jF][iF] = nuevaFeromona;
        for (int i=0;i<tamañoProblema -1; i++)
        {
            int iM= mejor.getTour()[i];
            int jM= mejor.getTour()[i+1];
            
            for (int j = 0; j < tamañoProblema - 1; j++) 
            {
                iF = local.getTour()[j];
                jF = local.getTour()[j + 1];
                if (iF==iM && jF==jM)
                {
                    nuevaFeromona=1d/mejor.getAptitud();
                    feromona[iF][jF] = nuevaFeromona;
                    feromona[jF][iF] = nuevaFeromona;
                }


            }
            
            iF = local.getTour()[tamañoProblema-1];
            jF = local.getTour()[0];
                if (iF==iM && jF==jM)
                {
                    nuevaFeromona=1d/mejor.getAptitud();
                    feromona[iF][jF] = nuevaFeromona;
                    feromona[jF][iF] = nuevaFeromona;
                }
        }

            
        iF = local.getTour()[tamañoProblema - 1];
        jF = local.getTour()[0];
        //nuevaFeromona = (1d - reduccionFeromona) * feromona[iF][jF] + (reduccionFeromona * feromonaInicial);
        feromona[iF][jF] = nuevaFeromona;
        feromona[jF][iF] = nuevaFeromona;
        
        
        
    }

    private void actualizarFeromonaGlobal() {
        double mejorFeromona = 1d / mejor.getAptitud();
        int iF;
        int jF;

        for (int i = 0; i < tamañoProblema - 1; i++) {
            iF = mejor.getTour()[i];
            jF = mejor.getTour()[i + 1];
            feromona[iF][jF] += mejorFeromona;
            feromona[jF][iF] += feromona[iF][jF];
        }

        iF = mejor.getTour()[tamañoProblema - 1];
        jF = mejor.getTour()[0];
        feromona[iF][jF] += mejorFeromona;
        feromona[jF][iF] += feromona[iF][jF];
    }
}

