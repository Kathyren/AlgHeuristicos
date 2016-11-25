/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coloniahormigas;

import java.awt.Button;
import java.awt.TextArea;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.swing.DefaultListModel;

/**
 *
 * @author Karen
 */
public class Visual extends javax.swing.JFrame {

    /**
     * Creates new form Visual
     */
    final int[][] COORDENADAS = {
    {6734 ,1453},
{2233 ,10},
{5530 ,1424},
{401 ,841},
{3082 ,1644},
{7608 ,4458},
{7573 ,3716},
{7265 ,1268},
{6898 ,1885},
{1112 ,2049},
{5468 ,2606},
{5989, 2873},
{4706, 2674},
{4612, 2035},
{6347, 2683},
{6107, 669},
{7611, 5184},
{7462, 3590},
{7732, 4723},
{5900, 3561},
{4483, 3369},
{6101, 1110},
{5199, 2182},
{1633, 2809},
{4307, 2322},
{675 ,1006},
{7555 ,4819},
{7541 ,3981},
{3177 ,756},
{7352, 4506},
{7545 ,2801},
{3245 ,3305},
{6426 ,3173},
{4608 ,1198},
{23 ,2216},
{7248 ,3779},
{7762 ,4595},
{7392 ,2244},
{3484 ,2829},
{6271 ,2135},
{4985 ,140},
{1916 ,1569},
{7280 ,4899},
{7509 ,3239},
{10 ,2676},
{6807 ,2993},
{5185 ,3258},
{3023 ,1942}
    };

    private Button btnInicializar;
    private Button btnEjecutar;
    private TextArea list;
    private Colonia colonia;
    DefaultListModel<String> model = new DefaultListModel<>();
    public Visual() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_ejecutar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lbx_r = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_ejecutar.setText("Ejecutar");
        btn_ejecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ejecutarActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(lbx_r);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(217, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btn_ejecutar, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(125, Short.MAX_VALUE)
                .addComponent(btn_ejecutar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ejecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ejecutarActionPerformed
        begin();
        execute();
    }//GEN-LAST:event_btn_ejecutarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Visual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Visual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Visual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Visual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Visual().setVisible(true);
            }
        });
    }
public void execute() {
        Hormiga mejor = colonia.buscarSolucion(1000);
        
        
        model.addElement( "\n"+colonia.getProceso() +  "  Aptitud: " +mejor.getAptitud() + "\n");
        lbx_r.setModel(model);
    }
 public void start(Stage stage) {
        stage.setTitle("Algoritmo \"Colonia de Hormigas\" - Hola Mundo");
        stage.setResizable(false);

        BorderPane bp = new BorderPane();
        ToolBar tb = new ToolBar();

        btnInicializar = new Button("Inicializar");
       

        btnEjecutar = new Button("Ejecutar");

        Scene scene = new Scene(bp);
        stage.setScene(scene);
        stage.show();
    }

    public void begin() {
        
        colonia = new Colonia(COORDENADAS.length, COORDENADAS, 10, 0.1);
        
        model.addElement("Mejor Hormiga Inicial: "  + "  Aptitud: " +colonia.getMejor().getAptitud() + "\n");
        lbx_r.setModel(model);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ejecutar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> lbx_r;
    // End of variables declaration//GEN-END:variables
}