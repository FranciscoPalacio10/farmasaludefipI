/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.pedidoCotizacion;

import farmasalud.atributosclases.ACabeceraPedidos;
import farmasalud.operaciones.ODetallesCotizaciones;
import farmasalud.operaciones.OManager;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import farmasalud.atributosclases.ADetalleCotizacion;
import farmasalud.atributosclases.ADetalleDetalleDeCotizacion;
import farmasalud.atributosclases.ADetallePedidos;
import farmasalud.atributosclases.ASucursales;
import farmasalud.atributosclases.AUsuario;
import farmasalud.jtable.jDetalleDelDetalleCotizacion;
import farmasalud.jtable.jtDetallePedidos;

/**
 *
 * @author Francisco Palacio
 */
public class DetallesDeDetallesConsolidados extends javax.swing.JFrame {
     private OManager  manager;
     private ArrayList<ADetalleDetalleDeCotizacion> detallesDelConsolidado = new ArrayList();
     private jDetalleDelDetalleCotizacion tabla;

    /**
     * Creates new form DetallesDeDetallesConsolidados
     * @param idDetalleCotizacion     
     */
    public DetallesDeDetallesConsolidados(int idDetalleCotizacion) {
        initComponents();
        this.setLocationRelativeTo(null);
        manager=new OManager();
        llenarTabla(idDetalleCotizacion);
    }

    private DetallesDeDetallesConsolidados() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void llenarTabla(int idDetalleCotizacion) {
        tabla= new jDetalleDelDetalleCotizacion(manager);
        tabla.update(idDetalleCotizacion);
        jTableDetalleConsolidado.setModel(tabla);
        jTableDetalleConsolidado.getTableHeader().setReorderingAllowed(false);
    }

   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDetalleConsolidado = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTableDetalleConsolidado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTableDetalleConsolidado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableDetalleConsolidado.setRowHeight(25);
        jTableDetalleConsolidado.setRowMargin(5);
        jTableDetalleConsolidado.setSelectionBackground(new java.awt.Color(0, 153, 0));
        jScrollPane1.setViewportView(jTableDetalleConsolidado);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(DetallesDeDetallesConsolidados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetallesDeDetallesConsolidados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetallesDeDetallesConsolidados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetallesDeDetallesConsolidados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DetallesDeDetallesConsolidados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDetalleConsolidado;
    // End of variables declaration//GEN-END:variables
}