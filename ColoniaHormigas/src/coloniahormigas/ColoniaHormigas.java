/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coloniahormigas;

import java.awt.Button;
import java.awt.TextArea;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Karen
 */
public class ColoniaHormigas {

    /**
     * @param args the command line arguments
     */
    final int[][] COORDENADAS = {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {10, 0}, {11, 0}};

    private Button btnInicializar;
    private Button btnEjecutar;
    private TextArea list;
    private Colonia colonia;
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
        
    }
    
   public void start(Stage stage) throws Execption {
        stage.setTitle("Algoritmo \"Colonia de Hormigas\" - Hola Mundo");
        stage.setResizable(false);

        BorderPane bp = new BorderPane();
        ToolBar tb = new ToolBar();

        btnInicializar = new Button("Inicializar");
        btnInicializar.setOnAction(e -> {
            begin();
        });

        btnEjecutar = new Button("Ejecutar");
        btnEjecutar.setOnAction(e -> {
            execute();
        });

        tb.getItems().addAll( new Label("Genetico: "), btnInicializar, btnEjecutar);
        list = new TextArea();
        list.setEditable(false);
        bp.setTop(tb);
        bp.setCenter(list);

        Scene scene = new Scene(bp);
        stage.setScene(scene);
        stage.show();
    }

    public void begin() {
        //list.clear();
        colonia = new Colonia(COORDENADAS.length, COORDENADAS, 12, 0.1);
        list.setText("Mejor Hormiga Inicial: " + colonia.getMejor().aString() + "  Aptitud: " +colonia.getMejor().getAptitud() + "\n");
    }


    public void execute() {
        Hormiga mejor = colonia.buscarSolucion(1000);
        list.setText(list.getText() + colonia.getProceso() + "Mejor Hormiga Final: " + mejor.aString() + "  Aptitud: " +mejor.getAptitud() + "\n");
    }
    /*
    
    */

    
}
