/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.pedidoCotizacion;

import farmasalud.operaciones.OCabeceraCotizacion;
import farmasalud.operaciones.OManager;
import farmasalud.ConsultasGenerales;
import farmasalud.atributosclases.ACabeceraCotizacion;
import farmasalud.atributosclases.ADetalleCotizacion;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Francisco Palacio
 */
public class FListadoCotizacionesPorEstado extends javax.swing.JFrame {
      private OManager manager = new OManager();
      private final Object[] nuevo = new Object[8];
      private DefaultTableModel modelo;
      private ArrayList<ACabeceraCotizacion> cotizacion = new ArrayList();
      private String estado;
      private int fila;
      private ConsultasGenerales consultas;
      
    /**
     * Creates new form ListadoCotizaciones
     * @param estado
     * @param titulo
     */
    public FListadoCotizacionesPorEstado(String estado, String titulo) {
        initComponents();
        this.estado=estado;
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle(titulo);
        
        consultas=ConsultasGenerales.obtenerInstacia();
        cotizacion= manager.getCabeceraCotizacion().ejecutarProcedimientoAlmacenado(this.estado);
        llenarTablas(jTableEstados,cotizacion);
        
    }
    
    private FListadoCotizacionesPorEstado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

      private void llenarTablas(JTable tabla,ArrayList<ACabeceraCotizacion> cotizacion) {
       String[] titulos={"ID ","Usuario","Vigencia Hasta","Estado","Fecha Creado"};
       modelo = new DefaultTableModel(null, titulos){
                   @Override
            public boolean isCellEditable(int i, int i1) {
                return false; 
            }
        };
       cotizacion.forEach((ACabeceraCotizacion c)->{
            nuevo[0]=c.getIdCotizacion();
            nuevo[1]=c.getIdUsuario();
            nuevo[2]=c.getVigencia();
            nuevo[3]=c.getEstadoCotizacion();
            nuevo[4]=c.getFecha();
              modelo.addRow(nuevo);
           });
       tabla.getTableHeader().setReorderingAllowed(false) ;
        tabla.setModel(modelo);
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
        jTableEstados = new javax.swing.JTable();
        jAbrir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTableEstados.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableEstados.setRowHeight(25);
        jTableEstados.setRowMargin(5);
        jTableEstados.setSelectionBackground(new java.awt.Color(0, 153, 0));
        jTableEstados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableEstadosMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableEstados);

        jAbrir.setText("Ver");
        jAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAbrirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(274, 274, 274)
                .addComponent(jAbrir, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jAbrir, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAbrirActionPerformed
      fila=jTableEstados.getSelectedRow();
      int idCotizacion=Integer.valueOf(modelo.getValueAt(fila,0).toString());
      FGenerarCotizacion abrir = new FGenerarCotizacion(idCotizacion,this.estado);
      abrir.setVisible(true);
      this.dispose();
    }//GEN-LAST:event_jAbrirActionPerformed

    private void jTableEstadosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableEstadosMouseReleased
    
    }//GEN-LAST:event_jTableEstadosMouseReleased

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
            java.util.logging.Logger.getLogger(FListadoCotizacionesPorEstado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FListadoCotizacionesPorEstado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FListadoCotizacionesPorEstado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FListadoCotizacionesPorEstado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FListadoCotizacionesPorEstado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAbrir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEstados;
    // End of variables declaration//GEN-END:variables
}
