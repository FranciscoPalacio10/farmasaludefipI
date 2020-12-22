/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.pedidoCotizacion;

import com.sun.prism.paint.Color;
import farmasalud.atributosclases.AProveedor;
import farmasalud.atributosclases.ATipoProveedor;
import farmasalud.jtable.jtProveedor;
import farmasalud.operaciones.OManager;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.RowFilter;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Francisco Palacio
 */
public class VerProveedor extends javax.swing.JFrame {

    private final OManager traer = new OManager();
    private final Object[] nuevo = new Object[6];
    private ArrayList<AProveedor> proveedores = new ArrayList();
    private final JList lista;
    private DefaultListModel modeloJList;
    private jtProveedor modeloAbstract;
    private JPanel jPanelenviarCotizacion;
    private boolean bNombre, bMail, bTelefono, bPais, bProvincia, bCondicionAfip, bCalle, bTipo, bCuit;

    /**
     * Creates new form VerProveedor
     *
     * @param lista
     * @param jPanelEnviarCotizacion
     *
     *
     * @paramjPanelEnviarCotizacion
     *
     */
    public VerProveedor(JList lista, JPanel jPanelEnviarCotizacion) {
        initComponents();
        this.lista = lista;
        this.jPanelenviarCotizacion = jPanelEnviarCotizacion;
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        modeloJList = (DefaultListModel) lista.getModel();
        modeloAbstract = new jtProveedor(traer, modeloJList);
        traer.getTipoProveedor().obtenerTodos().forEach(e -> jComboTipoProveedor.addItem(e.getDescripcion()));
        traer.getPais().obtenerTodos().forEach(e -> jComboPais.addItem(e.getDescripcion()));
        traer.getCondicionAfip().obtenerTodos().forEach(e -> jComboAfip.addItem(e.getDescripcion()));
        jTableProveedores.requestFocus();
        txtBuscar.setText("Buscar por nombre de proveedor");
        llenarTabla();
        this.setSize(this.getPreferredSize().width,
                this.getPreferredSize().height - jPanelRegistrarProveedor.getPreferredSize().height);
        jPanelRegistrarProveedor.setVisible(false);
    }

    private VerProveedor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void llenarTabla() {
        modeloAbstract.update();
        jTableProveedores.setModel(modeloAbstract);
        jTableProveedores.setSize(1000, 1500);
        jTableProveedores.getTableHeader().setReorderingAllowed(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelRegistrarProveedor = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNroTelefono = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCuit = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboProvincia = new javax.swing.JComboBox<>();
        jComboPais = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jComboTipoProveedor = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboAfip = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        btnRegistrarProveedor = new javax.swing.JButton();
        txtCalle = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProveedores = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();

        jPanelRegistrarProveedor.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createTitledBorder("Registrar Proveedor")));

        txtNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Nombre:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("Email:");

        txtNroTelefono.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtNroTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNroTelefonoKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("CUIT:");

        txtCuit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCuit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCuitKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Provincia:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Tipo Proveedor:");

        jComboProvincia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        jComboProvincia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jComboProvincia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboProvinciaActionPerformed(evt);
            }
        });

        jComboPais.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        jComboPais.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jComboPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboPaisActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("Pais:");

        jComboTipoProveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        jComboTipoProveedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jComboTipoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboTipoProveedorActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText("Telefono:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setText("Condcion AFIP");

        jComboAfip.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        jComboAfip.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jComboAfip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboAfipActionPerformed(evt);
            }
        });

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        txtDescripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane2.setViewportView(txtDescripcion);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setText("Descripcion:");

        btnRegistrarProveedor.setText("Registrar");
        btnRegistrarProveedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnRegistrarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarProveedorActionPerformed(evt);
            }
        });

        txtCalle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCalleKeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel10.setText("Calle:");

        txtEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmailKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanelRegistrarProveedorLayout = new javax.swing.GroupLayout(jPanelRegistrarProveedor);
        jPanelRegistrarProveedor.setLayout(jPanelRegistrarProveedorLayout);
        jPanelRegistrarProveedorLayout.setHorizontalGroup(
            jPanelRegistrarProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRegistrarProveedorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelRegistrarProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanelRegistrarProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelRegistrarProveedorLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtCuit, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelRegistrarProveedorLayout.createSequentialGroup()
                        .addGroup(jPanelRegistrarProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelRegistrarProveedorLayout.createSequentialGroup()
                                .addGroup(jPanelRegistrarProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNroTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                                    .addComponent(txtNombre)
                                    .addComponent(txtEmail))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelRegistrarProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRegistrarProveedorLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jComboProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRegistrarProveedorLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(txtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRegistrarProveedorLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(jComboPais, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRegistrarProveedorLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(jComboTipoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRegistrarProveedorLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addGroup(jPanelRegistrarProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnRegistrarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboAfip, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        jPanelRegistrarProveedorLayout.setVerticalGroup(
            jPanelRegistrarProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRegistrarProveedorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelRegistrarProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(txtCuit, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanelRegistrarProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelRegistrarProveedorLayout.createSequentialGroup()
                        .addGroup(jPanelRegistrarProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanelRegistrarProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNroTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(10, 10, 10))
                    .addGroup(jPanelRegistrarProveedorLayout.createSequentialGroup()
                        .addGroup(jPanelRegistrarProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboTipoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(10, 10, 10)
                        .addGroup(jPanelRegistrarProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboPais, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addGroup(jPanelRegistrarProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelRegistrarProveedorLayout.createSequentialGroup()
                        .addGroup(jPanelRegistrarProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPanelRegistrarProveedorLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanelRegistrarProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))))
                        .addGap(10, 10, 10)
                        .addGroup(jPanelRegistrarProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(10, 10, 10)
                        .addGroup(jPanelRegistrarProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboAfip, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addComponent(btnRegistrarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTableProveedores.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTableProveedores.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableProveedores.setRowHeight(35);
        jTableProveedores.setRowMargin(5);
        jTableProveedores.setSelectionBackground(new java.awt.Color(0, 255, 51));
        jScrollPane1.setViewportView(jTableProveedores);

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Registrar Proveedor");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        txtBuscar.setToolTipText("");
        txtBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarFocusLost(evt);
            }
        });
        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(322, 322, 322)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(339, 339, 339)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        txtBuscar.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanelRegistrarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelRegistrarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jtProveedor modeloAntesDeEditar = (jtProveedor) jTableProveedores.getModel();
        int i = jTableProveedores.getSelectedRow();
        String nombre = jTableProveedores.getValueAt(i, 1).toString();
        agregarElementoJList(nombre);
        modeloAntesDeEditar.removeRow(i);
        jPanelenviarCotizacion.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed

    }//GEN-LAST:event_txtBuscarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setSize(this.getPreferredSize().width,
                this.getPreferredSize().height + jPanelRegistrarProveedor.getPreferredSize().height);
        jPanelRegistrarProveedor.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void bordeRojo(JTextField text) {
        text.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 0), 1, true));
    }

    private void borderVerde(JTextField text) {
        text.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 200, 0), 1, true));
    }

    private void bordeRojo(JComboBox text) {
        text.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 0), 1, true));
    }

    private void borderVerde(JComboBox text) {
        text.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 200, 0), 1, true));
    }

    private void txtCuitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuitKeyTyped
        char caracter = evt.getKeyChar();
        evitarIngresoCaracteres(caracter, evt);
        if (txtCuit.getText().length() < 11) {
            bordeRojo(txtCuit);
            bCuit = false;
        } else if (txtCuit.getText().length() == 11) {
            bCuit = true;
            borderVerde(txtCuit);
        } else {
            evt.consume();
        }

    }//GEN-LAST:event_txtCuitKeyTyped

    private void txtNroTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroTelefonoKeyTyped
        char caracter = evt.getKeyChar();
        evitarIngresoCaracteres(caracter, evt);
        if (txtNroTelefono.getText().length() > 10) {
            borderVerde(txtNroTelefono);
            bTelefono = true;
        } else {
            bTelefono = false;
            bordeRojo(txtNroTelefono);
        }
    }//GEN-LAST:event_txtNroTelefonoKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        if (txtNombre.getText().length() > 0) {
            borderVerde(txtNombre);
            bNombre = true;
        } else {
            bNombre = false;
            bordeRojo(txtNombre);
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void jComboPaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboPaisActionPerformed
        bPais = pintarComboBox(jComboPais);
        String pais = jComboPais.getSelectedItem().toString();
        String codigoPais = traer.getPais().obtenerCodigoPais(pais);
        traer.getProvincia().obtenerPaisXProvincia(codigoPais).forEach(e -> jComboProvincia.addItem(e.getDescripcion()));
    }//GEN-LAST:event_jComboPaisActionPerformed

    private void jComboTipoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboTipoProveedorActionPerformed
        bTipo = pintarComboBox(jComboTipoProveedor);
    }//GEN-LAST:event_jComboTipoProveedorActionPerformed

    private void jComboAfipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboAfipActionPerformed
        bCondicionAfip = pintarComboBox(jComboAfip);
    }//GEN-LAST:event_jComboAfipActionPerformed

    private void jComboProvinciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboProvinciaActionPerformed
        bProvincia = pintarComboBox(jComboProvincia);
    }//GEN-LAST:event_jComboProvinciaActionPerformed

    private void txtEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyTyped
        if (txtEmail.getText().contains("@") && txtEmail.getText().contains(".")) {
            borderVerde(txtEmail);
            bMail = true;
        } else {
            bMail = false;
            bordeRojo(txtEmail);
        }
    }//GEN-LAST:event_txtEmailKeyTyped

    private void btnRegistrarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarProveedorActionPerformed
        System.out.println(bCalle + "" + bNombre + " " + bCuit + " " + bPais + " " + bProvincia);
        System.out.println(bTipo + "" + bMail + " " + bCuit + " " + bCondicionAfip + " " + bTelefono);
        int idCondcionAfip = traer.getCondicionAfip().obtenerId(jComboAfip.getSelectedItem().toString());
        String idProv = traer.getProvincia().obtenerId(jComboProvincia.getSelectedItem().toString());
        int idTipoProveedor = traer.getTipoProveedor().obtenerID(jComboTipoProveedor.getSelectedItem().toString());
        if (bCalle && bCuit && bNombre && bPais && bProvincia && bTipo && bMail && bCondicionAfip && bTelefono) {
            AProveedor nuevo = new AProveedor();
            nuevo.setCalle(txtCalle.getText());
            nuevo.setCondicionesAfip(idCondcionAfip);
            nuevo.setCuit(Long.valueOf(txtCuit.getText()));
            nuevo.setDescripcion(txtDescripcion.getText());
            nuevo.setMail(txtEmail.getText());
            nuevo.setNombre(txtNombre.getText().toUpperCase());
            nuevo.setIdTipoProveedor(idTipoProveedor);
            nuevo.setTelefono(Long.valueOf(txtNroTelefono.getText()));
            nuevo.setIdProvincia(idProv);
            int i = traer.getProveedor().insertar(nuevo);

            modeloAbstract.update();
            modeloAbstract.fireTableDataChanged();
            limpiarCampos();

        } else {
            JOptionPane.showMessageDialog(this, "Falta ingresar valores");
        }
    }//GEN-LAST:event_btnRegistrarProveedorActionPerformed

    private void txtCalleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCalleKeyTyped
        if (txtCalle.getText().length() > 0) {
            borderVerde(txtCalle);
            bCalle = true;
        } else {
            bCalle = false;
            bordeRojo(txtCalle);
        }
    }//GEN-LAST:event_txtCalleKeyTyped

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
  
     
    
    
    
    
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
      String texto=    txtBuscar.getText().toUpperCase().trim();
      TableRowSorter<jtProveedor> sorter = new TableRowSorter<>((jtProveedor) jTableProveedores.getModel());
               jTableProveedores.setRowSorter(sorter);
        
                 if (texto.length() == 0) {
                   sorter.setRowFilter(null);
                 } else {
                   sorter.setRowFilter(RowFilter.regexFilter("^"+texto,1));
                   
                 }
   
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void txtBuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusGained
        txtBuscar.setText("");
    }//GEN-LAST:event_txtBuscarFocusGained

    private void txtBuscarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusLost
         txtBuscar.setText("Buscar por nombre de proveedor");
    }//GEN-LAST:event_txtBuscarFocusLost

    private boolean pintarComboBox(JComboBox combo) {
        Boolean pintado;
        if (!"Seleccionar".equals(combo.getSelectedItem())) {
            borderVerde(combo);
            pintado = true;
        } else {
            pintado = false;
            bordeRojo(combo);
        }
        return pintado;
    }

    private void evitarIngresoCaracteres(char caracter, KeyEvent evt) {
        if ((caracter < '0' || (caracter > '9'))) {
            evt.consume();
        }
    }

    private void agregarElementoJList(Object elemento) {
        modeloJList.addElement(elemento);
        lista.setModel(modeloJList);

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
            java.util.logging.Logger.getLogger(VerProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VerProveedor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrarProveedor;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboAfip;
    private javax.swing.JComboBox<String> jComboPais;
    private javax.swing.JComboBox<String> jComboProvincia;
    private javax.swing.JComboBox<String> jComboTipoProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelRegistrarProveedor;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableProveedores;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCalle;
    private javax.swing.JTextField txtCuit;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNroTelefono;
    // End of variables declaration//GEN-END:variables

    private void limpiarCampos() {
        txtNombre.setText("");
        txtEmail.setText("");
        txtCuit.setText("");
        txtNroTelefono.setText("");
        txtDescripcion.setText("");
        txtCalle.setText("");
        jComboTipoProveedor.setSelectedIndex(0);
        jComboPais.setSelectedIndex(0);
        jComboProvincia.setSelectedIndex(0);
        jComboAfip.setSelectedIndex(0);
        txtNombre.setFocusable(true);
    }

}
