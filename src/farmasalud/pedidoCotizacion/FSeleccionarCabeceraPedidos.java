/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.pedidoCotizacion;

import farmasalud.atributosclases.ACabeceraCotizacion;
import farmasalud.atributosclases.ACabeceraPedidos;
import farmasalud.atributosclases.ADetalleConsolidado;
import farmasalud.atributosclases.ADetalleCotizacion;
import farmasalud.atributosclases.ADetalleDetalleDeCotizacion;
import farmasalud.atributosclases.ADetallePedidos;
import farmasalud.atributosclases.UsuarioActivo;
import farmasalud.jtable.jtCabeceraPedidos;
import farmasalud.jtable.jtNuevaCotizacion;
import farmasalud.obtenerFecha;
import farmasalud.operaciones.OCabeceraPedidos;
import farmasalud.operaciones.OManager;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 *
 * @author Francisco Palacio
 */
public class FSeleccionarCabeceraPedidos extends javax.swing.JFrame {

    private ADetalleCotizacion insertarDetalles;
    private ADetalleDetalleDeCotizacion insertarDetallesDelDetalle;
    private ArrayList<ADetallePedidos> detallesNuevos;
    private jtNuevaCotizacion modelo;
    private  OManager manager;
    private ACabeceraCotizacion nuevaCabecera;
    private obtenerFecha fecha;
    private UsuarioActivo obtener;
    private ADetallePedidos ingresar;

    public FSeleccionarCabeceraPedidos(ArrayList<ACabeceraPedidos> cabeceraPedidos) {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        fecha = obtenerFecha.getFecha();
        obtener = UsuarioActivo.getInstancia();
        manager = new OManager();
        llenarTabla(cabeceraPedidos);
    }

    private void llenarTabla(ArrayList<ACabeceraPedidos> cabeceraPedidos) {
        modelo = new jtNuevaCotizacion(cabeceraPedidos,jTableCabeceraPedidos);
        jTableCabeceraPedidos.getTableHeader().setReorderingAllowed(false);
        modelo.llenarTablas();
    }

    private FSeleccionarCabeceraPedidos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCabeceraPedidos = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTableCabeceraPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"zzz", "ddaf", "sadfasf", "asdfasdf",  new Boolean(false)},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableCabeceraPedidos.setRowHeight(25);
        jTableCabeceraPedidos.setRowMargin(5);
        jTableCabeceraPedidos.setSelectionBackground(new java.awt.Color(0, 153, 0));
        jScrollPane1.setViewportView(jTableCabeceraPedidos);

        jButton1.setText("Generar Cotizacion");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(304, 304, 304)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        insertarDetallesDelDetalle = new ADetalleDetalleDeCotizacion();
        nuevaCabecera = new ACabeceraCotizacion();
        ArrayList<Integer> cabeceraEditarEstado = new ArrayList();
        detallesNuevos = new ArrayList();  
        nuevaCabecera.setFecha(Date.valueOf(fecha.getFechaSistema()));
        nuevaCabecera.setEstadoCotizacion("BO");
        nuevaCabecera.setIdUsuario(obtener.getIdUsuario());
        nuevaCabecera.setVigencia(null);
        int id = manager.getCabeceraCotizacion().insertar(nuevaCabecera);
        
        modelo.obtenerFilasSeleccionadas().forEach((String a) -> {
            System.out.println("filas seleccionadas" +a);
            manager.getDetallePedidos().obtenerDetallesPorCabecera(Integer.valueOf(a)).forEach((ADetallePedidos as) -> {
                ingresar = new ADetallePedidos();
                ingresar.setIdProducto(as.getIdProducto());
                ingresar.setCantidad(as.getCantidad());
                ingresar.setNumeroPedidos(as.getNumeroPedidos());
                ingresar.setIdDetalle(as.getIdDetalle());
                detallesNuevos.add(ingresar);
            });
        });
        
        detallesNuevos.stream()
                .collect(Collectors.groupingBy(foo -> foo.getIdProducto(),
                        Collectors.summingInt(foo -> foo.getCantidad()))).forEach((idProducto, total) -> {
            insertarDetalles = new ADetalleCotizacion();
            insertarDetalles.setIdCotizacion(id);
            insertarDetalles.setIdProducto(idProducto);
            insertarDetalles.setCantidad(total);
            int idDetalleCotizacion = manager.getDetalleCotizaciones().insertar(insertarDetalles);
           
            insertarDetallesDelDetalle.setIdDetalleCotizacion(idDetalleCotizacion);
           
            manager.getDetalleCotizaciones().obtenerIdProductos(idDetalleCotizacion).forEach((Integer b) -> {
                 detallesNuevos.forEach((ADetallePedidos ad)->{
               if(b.equals(ad.getIdProducto())){
                 insertarDetallesDelDetalle.setIdDetallePedido(ad.getIdDetalle());  
                 manager.getDetalleDetalle().insertar(insertarDetallesDelDetalle);
                 cabeceraEditarEstado.add(ad.getNumeroPedidos());
               }
            });
                
            });
        });
        manager.getCabeceraPedidos().actulizarEstado(cabeceraEditarEstado, "EC");
        
         FGenerarCotizacion abrir = new  FGenerarCotizacion(id,"BO");
         abrir.setVisible(true);
         this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(FSeleccionarCabeceraPedidos.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FSeleccionarCabeceraPedidos.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FSeleccionarCabeceraPedidos.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FSeleccionarCabeceraPedidos.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FSeleccionarCabeceraPedidos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCabeceraPedidos;
    // End of variables declaration//GEN-END:variables

}
