/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.partederecepcion;

import farmasalud.ConsultasGenerales;
import farmasalud.atributosclases.ACabeceraCotizacion;
import farmasalud.atributosclases.ACabeceraOrdenDeCompra;
import farmasalud.atributosclases.ADetalleOrdenDeCompra;
import farmasalud.atributosclases.ADetalleParteDeRecepcion;
import farmasalud.atributosclases.ALoteProducto;
import farmasalud.atributosclases.ANuevoDetallesR;
import farmasalud.atributosclases.AParteDeRecepcion;
import farmasalud.atributosclases.AProveedor;
import farmasalud.atributosclases.UsuarioActivo;
import farmasalud.jtable.jtDetalleOrdenDeCompra;
import farmasalud.jtable.jtInsertaLoteDetalleRecepcion;
import farmasalud.obtenerFecha;
import farmasalud.operaciones.OManager;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Francisco Palacio
 */
public class FCantidadRecibida extends javax.swing.JFrame {
    
    private OManager manager;
    private UsuarioActivo usuarioActivo;
    private obtenerFecha fecha;
    private jtDetalleOrdenDeCompra modelDetalleOC;
    private int idProveedor, idOrdenDeCompra;
    ArrayList<ANuevoDetallesR> detalles = new ArrayList();
    ANuevoDetallesR aNuevoDetalle;
    DefaultTableModel modelo;
    private int idDetalleOC, idProducto, cantidadPedida,cantidadAceptada;
    private double precioOC;
    private boolean bPrecioOC, bFechaVTO, bLote, bCantidadRecibida;
    private jtInsertaLoteDetalleRecepcion loteDetalleRecepcion;
    private ADetalleParteDeRecepcion detalleParteRececpcion;
    private ALoteProducto cabeceraLote;
    private ConsultasGenerales consultas;

    
    public FCantidadRecibida() {
        initComponents();
        deshabilitarBotones();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        fecha = obtenerFecha.getFecha();
        manager = new OManager();
        usuarioActivo = UsuarioActivo.getInstancia();
        idProveedor = seleccionaProveedor();
        if (idProveedor != 0) {
            idOrdenDeCompra = seleccionarNroOrdenDeCompra(idProveedor);
        } else {
            this.dispose();
        }
        consultas = ConsultasGenerales.obtenerInstacia();  
        habilitarBtnAgregar();
        
        bFecha();
        insertarTabla();
        loteDetalleRecepcion = new jtInsertaLoteDetalleRecepcion();
        jTableNueva.setModel(loteDetalleRecepcion);
    }
    
    private void bFecha() {
        txtFechaVto.getDateEditor().addPropertyChangeListener((PropertyChangeEvent e) -> {
            if (txtFechaVto.getDate() != null) {
                bFechaVTO = "date".equals(e.getPropertyName()) && txtFechaVto.getDate().after(fecha.addMonths(3));
            }
            
        });
    }
    
    private void habilitarBtnAgregar() {
        consultas.habilitarBotonAbrir(jTableDetalleOC,btnEditar);   
    }
    
    private void deshabilitarBotones() {
        btnEditar.setEnabled(false);
        btnGenerarParte.setEnabled(false);
        btnInsertar.setVisible(false);
        txtLote.setEnabled(false);
        txtFechaVto.setEnabled(false);
        txtFechaEnvasado.setEnabled(false);
        txtCantidadRecibida.setEnabled(false);
    }
    
    private void habilitarBotontes() {
        btnGenerarParte.setEnabled(false);
        txtLote.setEnabled(true);
        txtFechaVto.setEnabled(true);
        txtFechaEnvasado.setEnabled(true);
        txtCantidadRecibida.setEnabled(true);
        btnInsertar.setVisible(true);
    }
    
    private void insertarTabla() {
        modelDetalleOC = new jtDetalleOrdenDeCompra(manager);
        modelDetalleOC.updateModel(idOrdenDeCompra);
        jTableDetalleOC.setModel(modelDetalleOC);
    }
    
    private int seleccionaProveedor() throws HeadlessException {
        int idProveedor = 0;
        JComboBox comboBox = new javax.swing.JComboBox<>();
        manager.getCabeceraOrdenDeCompra().obtenerTodos().stream().filter(a->"EN".equals(a.getEstado())).forEach((ACabeceraOrdenDeCompra acd) -> {
            manager.getProveedor().obtenerUno(acd.getIdProveedor()).forEach((AProveedor ap) -> {
                comboBox.addItem(ap.getNombre());
            });
        });
        int elegido = JOptionPane.showConfirmDialog(this, comboBox,
                "Selecciona proveedor", JOptionPane.DEFAULT_OPTION);
        if (elegido == JOptionPane.OK_OPTION) {
            String nombreProveedor = comboBox.getSelectedItem().toString();
            idProveedor = manager.getProveedor().obtenerIdProveedorXNombre(nombreProveedor);
        }
        
        return idProveedor;
    }
    
    private int seleccionarNroOrdenDeCompra(int idProveedor) {
        
        int idOrdenDeCompra = 0;
        JComboBox comboBox1 = new javax.swing.JComboBox<>();
        manager.getCabeceraOrdenDeCompra().obtenerTodos().stream().filter(b -> "EN".equals(b.getEstado())).filter(a -> a.getIdProveedor() == idProveedor).collect(Collectors.groupingBy(d -> d.getIdOrdenDeCompra())).keySet()
                .stream().collect((Collectors.toList())).forEach(comboBox1::addItem);
        
        int elegido = JOptionPane.showConfirmDialog(this, comboBox1,
                "Selecciona nro Orden De Compra", JOptionPane.DEFAULT_OPTION);
        if (elegido == JOptionPane.OK_OPTION) {
            idOrdenDeCompra = Integer.valueOf(comboBox1.getSelectedItem().toString());
        }
        return idOrdenDeCompra;
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
        jTableNueva = new javax.swing.JTable();
        btnGenerarParte = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableDetalleOC = new javax.swing.JTable();
        btnEditar = new javax.swing.JButton();
        jPaneldetalles = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCantidadRecibida = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtFechaEnvasado = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        txtFechaVto = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        txtLote = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtIdProducto = new javax.swing.JLabel();
        btnInsertar = new javax.swing.JButton();

        jTableNueva.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableNueva);

        btnGenerarParte.setText("Generar Parte Recepcion");
        btnGenerarParte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarParteActionPerformed(evt);
            }
        });

        jTableDetalleOC.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableDetalleOC.setRowHeight(25);
        jTableDetalleOC.setRowMargin(5);
        jTableDetalleOC.setSelectionBackground(new java.awt.Color(0, 204, 51));
        jTableDetalleOC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTableDetalleOCKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableDetalleOCKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTableDetalleOC);

        btnEditar.setText("Insertar Parte De Recepcion");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        jPaneldetalles.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalles"));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Lote Producto:");

        txtCantidadRecibida.setText("0");
        txtCantidadRecibida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadRecibidaActionPerformed(evt);
            }
        });
        txtCantidadRecibida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadRecibidaKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("Fecha VTO:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("Fecha Envasado:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Cantidad Recibida:");

        txtLote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLoteKeyTyped(evt);
            }
        });

        jLabel6.setText("Id Producto:");

        txtIdProducto.setText("------");

        btnInsertar.setText("Insertar");
        btnInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPaneldetallesLayout = new javax.swing.GroupLayout(jPaneldetalles);
        jPaneldetalles.setLayout(jPaneldetallesLayout);
        jPaneldetallesLayout.setHorizontalGroup(
            jPaneldetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneldetallesLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPaneldetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPaneldetallesLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFechaEnvasado, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPaneldetallesLayout.createSequentialGroup()
                        .addGroup(jPaneldetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPaneldetallesLayout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPaneldetallesLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(jPaneldetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPaneldetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFechaVto, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLote, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdProducto))))
                .addGap(120, 120, 120)
                .addGroup(jPaneldetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPaneldetallesLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtCantidadRecibida, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPaneldetallesLayout.setVerticalGroup(
            jPaneldetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneldetallesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPaneldetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCantidadRecibida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(txtIdProducto))
                .addGroup(jPaneldetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPaneldetallesLayout.createSequentialGroup()
                        .addGroup(jPaneldetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPaneldetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtFechaVto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPaneldetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(txtFechaEnvasado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(26, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPaneldetallesLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPaneldetalles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGenerarParte, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(340, 340, 340))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 914, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPaneldetalles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGenerarParte, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(511, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarParteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarParteActionPerformed
        AParteDeRecepcion cabecera = new AParteDeRecepcion();
        //insertar cabecera parte de recepcion
        cabecera.setNumeroOc(idOrdenDeCompra);
        cabecera.setIdProveedor(idProveedor);
        cabecera.setFechaRecepcion(Date.valueOf(fecha.getFechaSistema()));
        cabecera.setEstado("BO");
        cabecera.setObservaciones("");
        cabecera.setIdUsuarioRecepcion(usuarioActivo.getIdUsuario());
        int idCabecera = manager.getParteDeRecepcion().insertar(cabecera);
        System.out.println(idCabecera);
        //insertar detalle parte de recepcion
        loteDetalleRecepcion.totalFilasInsertadas().forEach((ANuevoDetallesR ad) -> {
            detalleParteRececpcion = new ADetalleParteDeRecepcion();
            detalleParteRececpcion.setCantidad(ad.getCantidadPedida());
            detalleParteRececpcion.setCantidadRecibida(ad.getCantidadRecibida());
            detalleParteRececpcion.setCantidadRechazada(0);
            detalleParteRececpcion.setCantidadAceptada(0);
            detalleParteRececpcion.setDetalleOrdenDeCompra(ad.getDetalleOC());
            detalleParteRececpcion.setIdProducto(ad.getIdProducto());
            detalleParteRececpcion.setiDPartederecepcion(idCabecera);
            detalleParteRececpcion.setIdUsuarioVerificacion(1);
            int idDetalleInsertado = manager.getDetalleParteDeRecepcion().insertar(detalleParteRececpcion);
            //insertar Lote
            cabeceraLote = new ALoteProducto();
            cabeceraLote.setIdDetalleParteRecepcion(idDetalleInsertado);
            cabeceraLote.setFechaEnvasado(ad.getFechaEnvasado());
            cabeceraLote.setFechaVto(ad.getFechaVto());
            cabeceraLote.setIdLote(ad.getLote());
            cabeceraLote.setIdProducto(idProducto);            
            manager.getLoteProducto().insertar(cabeceraLote);
        });
        this.dispose();
        
    }//GEN-LAST:event_btnGenerarParteActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int fila = jTableDetalleOC.getSelectedRow();
        idDetalleOC = Integer.valueOf(jTableDetalleOC.getValueAt(fila, 0).toString());
        idProducto = Integer.valueOf(jTableDetalleOC.getValueAt(fila, 1).toString());
        cantidadPedida = Integer.valueOf(jTableDetalleOC.getValueAt(fila, 3).toString());
        cantidadAceptada = Integer.valueOf(jTableDetalleOC.getValueAt(fila, 4).toString());
        txtIdProducto.setText(String.valueOf(idProducto));
        habilitarBotontes();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void jTableDetalleOCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableDetalleOCKeyReleased
        

    }//GEN-LAST:event_jTableDetalleOCKeyReleased

    private void jTableDetalleOCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableDetalleOCKeyPressed

    }//GEN-LAST:event_jTableDetalleOCKeyPressed

    private void txtCantidadRecibidaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadRecibidaKeyTyped
        consultas.evitarIngresoCaracteres(evt.getKeyChar(), evt);
       

    }//GEN-LAST:event_txtCantidadRecibidaKeyTyped
    
    private Boolean isEmpty(JTextField c) {
        Boolean b;
        b = !c.getText().isEmpty();
        System.out.println(b);
        return b;
    }

    private void txtLoteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoteKeyTyped
        bLote = isEmpty(txtLote);
    }//GEN-LAST:event_txtLoteKeyTyped
 

    private void btnInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarActionPerformed
          bCantidadRecibida = isEmpty(txtCantidadRecibida);
          int cantidadAceptable=cantidadPedida-cantidadAceptada;
        System.out.println(bLote + " " + bFechaVTO + bPrecioOC + bCantidadRecibida);
        if (bLote && bFechaVTO && bCantidadRecibida) {
            if(Integer.valueOf(txtCantidadRecibida.getText())<=cantidadAceptable){ 
                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = sdf.format(txtFechaEnvasado.getDate());
            String date2 = sdf.format(txtFechaVto.getDate());
            aNuevoDetalle = new ANuevoDetallesR();
            aNuevoDetalle.setCantidadPedida(cantidadPedida);
            aNuevoDetalle.setCantidadRecibida(Integer.valueOf(txtCantidadRecibida.getText()));
            aNuevoDetalle.setDetalleOC(idDetalleOC);
            aNuevoDetalle.setFechaEnvasado(Date.valueOf(date1));
            aNuevoDetalle.setFechaVto(Date.valueOf(date2));
            aNuevoDetalle.setIdProducto(idProducto);
            aNuevoDetalle.setLote(txtLote.getText());
            aNuevoDetalle.setPrecio(precioOC);
            detalles.add(aNuevoDetalle);
            loteDetalleRecepcion.addRow(detalles);
            jTableNueva.updateUI();
            modelDetalleOC.removeRow(jTableDetalleOC.getSelectedRow());
            limpiarCampos();
            deshabilitarBotones();
            btnGenerarParte.setEnabled(true);
            }else{
                JOptionPane.showMessageDialog(this, "ajusta el valor de cantidad recibida");
            }
           
        } else {
            JOptionPane.showMessageDialog(this, "falta ingresa algun valor");
        }
    }//GEN-LAST:event_btnInsertarActionPerformed

    private void txtCantidadRecibidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadRecibidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadRecibidaActionPerformed
    private int cantidadAceptada(int fila) { 
      return  Integer.valueOf(jTableDetalleOC.getValueAt(fila, 4).toString());
    }
           
    private void limpiarCampos() {
        txtIdProducto.setText("---");
        txtLote.setText("");
        txtFechaVto.setCalendar(null);
        txtCantidadRecibida.setText("");
        txtFechaEnvasado.setCalendar(null);
        
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
            java.util.logging.Logger.getLogger(FCantidadRecibida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FCantidadRecibida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FCantidadRecibida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FCantidadRecibida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FCantidadRecibida().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGenerarParte;
    private javax.swing.JButton btnInsertar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPaneldetalles;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableDetalleOC;
    private javax.swing.JTable jTableNueva;
    private javax.swing.JTextField txtCantidadRecibida;
    private com.toedter.calendar.JDateChooser txtFechaEnvasado;
    private com.toedter.calendar.JDateChooser txtFechaVto;
    private javax.swing.JLabel txtIdProducto;
    private javax.swing.JTextField txtLote;
    // End of variables declaration//GEN-END:variables
}
