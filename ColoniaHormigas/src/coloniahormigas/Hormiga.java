/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coloniahormigas;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 *
 * @author Karen
 */
public class Hormiga {
    private int tamañoProblema;
    private double[][] distancias;

    private int[] tour;
    private double aptitud;

    public static Hormiga candidatoInicial(int tamañoProblema, double[][] distancias) {
        int[] tour = new int[tamañoProblema];
        Arrays.fill(tour, -1);
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int indice = 0;

        while (indice < tamañoProblema) {
            final int CIUDAD = random.nextInt(0, tamañoProblema);

            if (!IntStream.of(tour).anyMatch(c -> c == CIUDAD)) {
                tour[indice] = CIUDAD;
                indice++;
            }
        }

        Hormiga inicial = new Hormiga(tamañoProblema, distancias);
        inicial.setTour(tour);

        return inicial;
    }

    public Hormiga(int tamañoProblema, double[][] distancias) {
        this.tamañoProblema = tamañoProblema;
        this.distancias = distancias;

        tour = new int[tamañoProblema];
        aptitud = Double.MAX_VALUE;
    }

    private Hormiga(int tamañoProblema, int[] tour, double[][] distancias, double aptitud) {
        this.tamañoProblema = tamañoProblema;
        this.tour = Arrays.copyOf(tour, tamañoProblema);
        this.distancias = distancias;
        this.aptitud = aptitud;
    }

    public Hormiga clonar() {
        return new Hormiga(tamañoProblema, tour, distancias, aptitud);
    }

    /**
     * Solo sirve con el problema de ¡Hola Mundo!
     * @return cadena ¡Hola Mundo!
     */
    public String aString() {
        final char[] OPTIMO = {'¡', 'h', 'o', 'l', 'a', ' ', 'm', 'u', 'n', 'd', 'o', '!'};
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tamañoProblema; i++) {
            sb.append(OPTIMO[tour[i]]);
        }

        return sb.toString();
    }

    public int[] getTour() {
        return Arrays.copyOf(tour, tamañoProblema);
    }

    public void setTour(int[] tour) {
        this.tour = Arrays.copyOf(tour, tamañoProblema);
        calcularAptitud();
    }

    public double getAptitud() {
        return aptitud;
    }

    public void construirCamino(double[][] feromona) {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        tour[0] = random.nextInt(0, tamañoProblema);

        boolean[] ciudadesVisitadas = new boolean[tamañoProblema];
        ciudadesVisitadas[tour[0]] = true;
        double[] feromonaTour = new double[tamañoProblema];

        for (int i = 1; i < tamañoProblema; i++) {
            double totalFeromona = 0;

            for (int j = 0; j < tamañoProblema; j++) {
                if (!ciudadesVisitadas[j]) {
                    int k = tour[i - 1];
                    feromonaTour[j] = 1d / distancias[k][j] * feromona[k][j];
                    totalFeromona += feromonaTour[j];
                }
            }

            float coeficiente = random.nextFloat();

            while (coeficiente == 0) {
                coeficiente = random.nextFloat();
            }

            double feromonaRandom = totalFeromona * coeficiente;
            double sumaFeromona = 0;
            int indice = 0;

            while (sumaFeromona < feromonaRandom) {
                if (!ciudadesVisitadas[indice]) {
                    sumaFeromona += feromonaTour[indice];
                }

                indice++;
            }

            tour[i] = indice - 1;
            ciudadesVisitadas[indice - 1] = true;
        }

        calcularAptitud();
    }

    private void calcularAptitud() {
        // Código para calcular la aptitud en el problema ¡Hola Mundo!
        final int[] OPTIMO = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        aptitud = 34;

        for (int i = 0; i < tamañoProblema; i++) {
            if (tour[i] == OPTIMO[i]) {
                aptitud -= 1;
            }
        }
        // Fin ódigo


        // Código para calcular la aptitud en el TSP
        /*aptitud = 0;
        int x;
        int y;

        for (int i = 0; i < tamañoProblema - 1; i++) {
            x = tour[i];
            y = tour[i + 1];
            aptitud += distancias[x][y];
        }

        x = tour[tamañoProblema - 1];
        y = tour[0];
        aptitud += distancias[x][y];*/
        // Fin código
    }

    public void optimizar() {
        // Agregar su código de optimización
    }
}
