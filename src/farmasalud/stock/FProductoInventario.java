/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.stock;

import farmasalud.jtable.jtProductoInventario;
import farmasalud.operaciones.OManager;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


/**
 *
 * @author Francisco Palacio
 */
public class FProductoInventario extends javax.swing.JFrame {

    private jtProductoInventario modeloInventario;
    private OManager manager;
    private final List<RowFilter<Object, Object>> lisTfiltros = new ArrayList<>();
    private final List<RowFilter<Object, Object>> andfiltros = new ArrayList<>();
    private RowFilter<Object, Object> nombreFilter, idFilter, nuevoFilter;
    private TableRowSorter sorter;

    /**
     * Creates new form FProductoInventario
     */
    public FProductoInventario() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        manager = new OManager();
        llenarTabla();
        manager.getProductos().obtenerTodos().forEach((e) -> jComboIdProducto.addItem(String.valueOf(e.getIdProducto())));
        obtenerNombreProducto().forEach((e) -> jComboNombreComercial.addItem(e));
        AutoCompleteDecorator.decorate(jComboIdProducto);
        AutoCompleteDecorator.decorate(jComboNombreComercial);
        bloquerBoton();
        sorter = new TableRowSorter(tableProductoInvetario.getModel());
        tableProductoInvetario.setRowSorter(sorter);
    }

    private List<String> obtenerNombreProducto() {
        List<String> nombreProducto = new ArrayList();
        int i = tableProductoInvetario.getRowCount();
        System.out.println(i);
        for (int a = 0; a < i; a++) {

            nombreProducto.add(modeloInventario.getValueAt(a, 1).toString());
        }
        return nombreProducto;
    }

    private void llenarTabla() {
        modeloInventario = new jtProductoInventario(manager);
        modeloInventario.updateTable();
        tableProductoInvetario.setModel(modeloInventario);
        tableProductoInvetario.getTableHeader().setReorderingAllowed(false);
    }

    private void bloquerBoton() {
        btnLimpiarFiltro.setEnabled(false);
        btnBuscar.setEnabled(false);
    }

    private void habilitarBoton() {
        btnLimpiarFiltro.setEnabled(true);
        btnBuscar.setEnabled(true);
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
        tableProductoInvetario = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboIdProducto = new javax.swing.JComboBox<>();
        jComboNombreComercial = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        btnLimpiarFiltro = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tableProductoInvetario.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tableProductoInvetario.setModel(new javax.swing.table.DefaultTableModel(
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
        tableProductoInvetario.setRowHeight(35);
        tableProductoInvetario.setRowMargin(6);
        tableProductoInvetario.setSelectionBackground(new java.awt.Color(0, 153, 51));
        jScrollPane1.setViewportView(tableProductoInvetario);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtros"));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Id Producto:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Nombre Comercial:");

        jComboIdProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        jComboIdProducto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboIdProductoItemStateChanged(evt);
            }
        });

        jComboNombreComercial.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        jComboNombreComercial.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboNombreComercialItemStateChanged(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnLimpiarFiltro.setText("Limpiar Filtro");
        btnLimpiarFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarFiltroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboNombreComercial, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(112, Short.MAX_VALUE)
                .addComponent(btnLimpiarFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboNombreComercial, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel3.setText("Inventario por Producto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(348, 348, 348)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(389, 389, 389)
                        .addComponent(jLabel3)))
                .addContainerGap(403, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(12, 12, 12)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboIdProductoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboIdProductoItemStateChanged
        if (filtrarJCombo(jComboIdProducto)) {
            habilitarBoton();
            idFilter = RowFilter.regexFilter(jComboIdProducto.getSelectedItem().toString(), 0);
            lisTfiltros.add(idFilter);
        } else {
            bloquerBoton();
        }
    }//GEN-LAST:event_jComboIdProductoItemStateChanged

    private void jComboNombreComercialItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboNombreComercialItemStateChanged
        if (filtrarJCombo(jComboNombreComercial)) {
            habilitarBoton();
            nombreFilter = RowFilter.regexFilter(jComboNombreComercial.getSelectedItem().toString(), 1);
            lisTfiltros.add(nombreFilter);
        } else {
            bloquerBoton();
        }
    }//GEN-LAST:event_jComboNombreComercialItemStateChanged

    private void btnLimpiarFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarFiltroActionPerformed
        andfiltros.removeAll(andfiltros);
        lisTfiltros.removeAll(lisTfiltros);
        jComboIdProducto.setSelectedIndex(0);
        jComboNombreComercial.setSelectedIndex(0);
        llenarTabla();
        sorter = new TableRowSorter(tableProductoInvetario.getModel());
        tableProductoInvetario.setRowSorter(sorter);
        bloquerBoton();
    }//GEN-LAST:event_btnLimpiarFiltroActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        lisTfiltros.forEach((RowFilter<Object, Object> a) -> {
            if (a != null) {
                andfiltros.add(a);
            }
        });
        nuevoFilter = RowFilter.andFilter(andfiltros);
        sorter.setRowFilter(nuevoFilter);
    }//GEN-LAST:event_btnBuscarActionPerformed
    private Boolean filtrarJCombo(JComboBox a) {
        boolean seleccionado;
        seleccionado = !a.getSelectedItem().toString().equals("Seleccionar");

        return seleccionado;
    }

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
            java.util.logging.Logger.getLogger(FProductoInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FProductoInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FProductoInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FProductoInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FProductoInventario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnLimpiarFiltro;
    private javax.swing.JComboBox<String> jComboIdProducto;
    private javax.swing.JComboBox<String> jComboNombreComercial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableProductoInvetario;
    // End of variables declaration//GEN-END:variables
}
