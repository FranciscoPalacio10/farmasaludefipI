/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.pedidoCotizacion;

import farmasalud.operaciones.OCabeceraPedidos;
import farmasalud.operaciones.OManager;
import farmasalud.ConsultasGenerales;
import farmasalud.atributosclases.ACabeceraCotizacion;
import farmasalud.pedidosSucursales.pintarCeldas;
import java.util.ArrayList;
import farmasalud.atributosclases.ACabeceraPedidos;
import farmasalud.atributosclases.ACotizacionXProveedor;
import farmasalud.atributosclases.ADetalleConsolidado;
import java.util.function.Consumer;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import farmasalud.atributosclases.ADetalleCotizacion;
import farmasalud.atributosclases.ADetalleDetalleDeCotizacion;
import farmasalud.atributosclases.ADetallePedidos;
import farmasalud.atributosclases.AFormaFarmaceutica;
import farmasalud.atributosclases.ANoFarmaceutico;
import farmasalud.atributosclases.ANombreComercial;
import farmasalud.atributosclases.AProducto;
import farmasalud.atributosclases.AProveedor;
import farmasalud.atributosclases.AVademecum;
import farmasalud.atributosclases.UsuarioActivo;
import farmasalud.jtable.jtDetalleCotizaciones;
import farmasalud.jtable.jtInsertaLoteDetalleRecepcion;
import farmasalud.obtenerFecha;
import farmasalud.operaciones.ODetallesCotizaciones;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.stream.Collectors;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import static javax.swing.text.StyleConstants.Size;

/**
 *
 * @author Francisco Palacio
 */
public class FGenerarCotizacion extends javax.swing.JFrame {

    private UsuarioActivo obtenerUsuario;
    private OManager traer = new OManager();
    private ArrayList<ADetalleCotizacion> cotizacion = new ArrayList();
    private final Object[] nuevo = new Object[8];
    private int row;
    private ConsultasGenerales seleccionar;
    private String idDetalleCotizacion;
    private ACabeceraCotizacion cabeceraCotizacion;
    private DefaultListModel modeloJList = new DefaultListModel();
    private jtDetalleCotizaciones tabla;
    private int idCotizacion;
    private obtenerFecha fecha;
    private String estado;
   

    /**
     * Creates new form PedidosCotizacion
     *
     * @param id
     * @param estado
     */
    public FGenerarCotizacion(int id, String estado) {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        fecha = obtenerFecha.getFecha();
        idCotizacion = id;
        this.estado = estado;
        obtenerUsuario = UsuarioActivo.getInstancia();
        seleccionar = ConsultasGenerales.obtenerInstacia();
        btnQuitarProveedor.setEnabled(false);
        jPanelEnviarCotizacion.setVisible(false);
        habilitarDateVigencia();
        estadoPedido(estado, id);
        jMenuVerDetalle.setName("Ver Detalle");
    }

    private void habilitarDateVigencia() {
        if (jDateVigencia == null) {
            jButtonAutorizar.setEnabled(false);
        } else {
            jButtonAutorizar.setEnabled(true);
        }
        jDateVigencia.getDateEditor().addPropertyChangeListener((PropertyChangeEvent e) -> {
            if ("date".equals(e.getPropertyName())) {
                jButtonAutorizar.setEnabled(true);
            } else {
                jButtonAutorizar.setEnabled(false);
            }
        });
    }

    private void estadoPedido(String estado, int id) {
        tabla = new jtDetalleCotizaciones((ODetallesCotizaciones) traer.getDetalleCotizaciones());
        pintarCeldas pintar = new pintarCeldas("idConsolidado");
        switch (estado) {
            case "BO":
                traerCabeceraCotizacionesPorEstado(id, "Borrador");
                cargarTabla(id, pintar);
                if (!traer.getCabeceraPedidos().obtenerPedidosXEstado("AU").isEmpty()) {
                    jButtonAgregarDetalle.setEnabled(true);
                } else {
                    jButtonAgregarDetalle.setEnabled(false);
                }
                this.setSize(this.getPreferredSize().width, this.getPreferredSize().height - jPanel2.getPreferredSize().height
                );
                this.remove(jPanelEnviarCotizacion);
                this.remove(jPanel2);
                break;
            case "AU":
                traerCabeceraCotizacionesPorEstado(id, "Autorizado");
                cargarTabla(id, pintar);
                this.setSize(this.getPreferredSize().width, this.getPreferredSize().height - jPanelBtnAutorizarGuardar.getPreferredSize().height
                );
                this.remove(jPanelAgegarDetalle);
                this.remove(jPanelBtnAutorizarGuardar);
                break;
            case "EN":
                traerCabeceraCotizacionesPorEstado(id, "Enviado");
                cargarTabla(id, pintar);
                obtenerProveedoresXCotizacion(id);
                this.remove(jPanelAgegarDetalle);
                this.setSize(this.getPreferredSize().width, this.getPreferredSize().height
                        - jPanelAgegarDetalle.getPreferredSize().height);
                jTextObeservaciones.setEditable(false);
                this.remove(jPanelBtnAutorizarGuardar);
                jPanel2.remove(btnAgregarProveedor);
                jPanel2.remove(btnQuitarProveedor);
                jPanel2.setEnabled(false);
                this.remove(btnCancelar);
                break;
            case "CT":
               traerCabeceraCotizacionesPorEstado(id, "Compra Anulada");
                cargarTabla(id, pintar);
                obtenerProveedoresXCotizacion(id);
                jTextObeservaciones.setEditable(false);
                this.remove(jPanelAgegarDetalle);
                this.setSize(this.getPreferredSize().width, this.getPreferredSize().height
                        - jPanelAgegarDetalle.getPreferredSize().height);
                this.remove(jPanelBtnAutorizarGuardar);
                jPanel2.remove(btnAgregarProveedor);
                jPanel2.remove(btnQuitarProveedor);
                jPanel2.setEnabled(false);
                this.remove(btnCancelar);
                break;
            case "DE":
            traerCabeceraCotizacionesPorEstado(id, "Compra Desierta");
                cargarTabla(id, pintar);
                obtenerProveedoresXCotizacion(id);
                jTextObeservaciones.setEditable(false);
                this.remove(jPanelAgegarDetalle);
                this.setSize(this.getPreferredSize().width, this.getPreferredSize().height
                        - jPanelAgegarDetalle.getPreferredSize().height);
                this.remove(jPanelBtnAutorizarGuardar);
                jPanel2.remove(btnAgregarProveedor);
                jPanel2.remove(btnQuitarProveedor);
                jPanel2.setEnabled(false);
                this.remove(btnCancelar);
                break;
            case "AN":
                traerCabeceraCotizacionesPorEstado(id, "Compra Anulada");
                cargarTabla(id, pintar);
                obtenerProveedoresXCotizacion(id);
                jTextObeservaciones.setEditable(false);
                this.remove(jPanelAgegarDetalle);
                this.setSize(this.getPreferredSize().width, this.getPreferredSize().height
                        - jPanelAgegarDetalle.getPreferredSize().height);
                this.remove(jPanelBtnAutorizarGuardar);
                jPanel2.remove(btnAgregarProveedor);
                jPanel2.remove(btnQuitarProveedor);
                jPanel2.setEnabled(false);
                this.remove(btnCancelar);
                break;

        }

    }

    private void cargarTabla(int id, pintarCeldas pintar) {
        tabla.update(id);
        jTableDetalleCotizacion.setModel(tabla);
        jTableDetalleCotizacion.getTableHeader().setReorderingAllowed(false);
        jTableDetalleCotizacion.getColumn("Id").setCellRenderer(pintar);
    }

    private void obtenerProveedoresXCotizacion(int id) {
        traer.getCotizacionXProveedor().obtenerUno(id).forEach((ACotizacionXProveedor act) -> {
            traer.getProveedor().obtenerUno(act.getIdProveedor()).forEach((AProveedor ap) -> {
                modeloJList.addElement(ap.getNombre());
                listProveedores.setModel(modeloJList);

            });

        });

    }

    private void traerCabeceraCotizacionesPorEstado(int id, String estadoCompleto) {
        ArrayList<ACabeceraCotizacion> cabeceracotizacion = traer.getCabeceraCotizacion().obtenerUno(id);
        cabeceracotizacion.forEach((ACabeceraCotizacion ver) -> {
            jTextObeservaciones.setText(ver.getObservaciones());
            txtIdCotizacion.setText(String.valueOf(ver.getIdCotizacion()));
            jDateVigencia.setDate(ver.getVigencia());
            txtEstado1.setText(estadoCompleto);
            txtUltimaEdicion.setText(String.valueOf(ver.getFecha()));
        });

    }

    private FGenerarCotizacion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuVerDetalle = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jDateVigencia = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextObeservaciones = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        txtUltimaEdicion = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtIdCotizacion = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtEstado1 = new javax.swing.JLabel();
        jPanelAgegarDetalle = new javax.swing.JPanel();
        jButtonAgregarDetalle = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableDetalleCotizacion = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnAgregarProveedor = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        listProveedores = new javax.swing.JList<>();
        btnQuitarProveedor = new javax.swing.JButton();
        jPanelBtnAutorizarGuardar = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButtonAutorizar = new javax.swing.JButton();
        jPanelEnviarCotizacion = new javax.swing.JPanel();
        jButtonEnviarCotizacion = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        jMenuVerDetalle.setText("jMenuItem1");
        jMenuVerDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuVerDetalleActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuVerDetalle);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("Generar Cotización");

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Vigencia hasta:");

        jDateVigencia.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Observaciones:");

        jTextObeservaciones.setColumns(20);
        jTextObeservaciones.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jTextObeservaciones.setRows(5);
        jScrollPane1.setViewportView(jTextObeservaciones);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Fecha ultima edición:");

        txtUltimaEdicion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtUltimaEdicion.setText("BORRADOR");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("ID COTIZACION:");

        txtIdCotizacion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtIdCotizacion.setText("Numero");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Estado:");

        txtEstado1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtEstado1.setText("BORRADOR");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateVigencia, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtIdCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                                .addComponent(jLabel5)))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUltimaEdicion)
                            .addComponent(txtEstado1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdCotizacion)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel4))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateVigencia, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtUltimaEdicion))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtEstado1))
                .addGap(51, 51, 51))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jPanelAgegarDetalle.setBorder(new javax.swing.border.MatteBorder(null));

        jButtonAgregarDetalle.setText("AGREGAR DETALLE PEDIDO");
        jButtonAgregarDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarDetalleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelAgegarDetalleLayout = new javax.swing.GroupLayout(jPanelAgegarDetalle);
        jPanelAgegarDetalle.setLayout(jPanelAgegarDetalleLayout);
        jPanelAgegarDetalleLayout.setHorizontalGroup(
            jPanelAgegarDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButtonAgregarDetalle)
        );
        jPanelAgegarDetalleLayout.setVerticalGroup(
            jPanelAgegarDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAgegarDetalleLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButtonAgregarDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder("Productos Consolidados"), javax.swing.BorderFactory.createEtchedBorder()));

        jTableDetalleCotizacion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTableDetalleCotizacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableDetalleCotizacion.setRowHeight(25);
        jTableDetalleCotizacion.setRowMargin(5);
        jTableDetalleCotizacion.setSelectionBackground(new java.awt.Color(0, 153, 0));
        jTableDetalleCotizacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableDetalleCotizacionMouseReleased(evt);
            }
        });
        jTableDetalleCotizacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableDetalleCotizacionKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jTableDetalleCotizacion);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(new javax.swing.border.MatteBorder(null));

        btnAgregarProveedor.setText("Agregar Proveedor");
        btnAgregarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProveedorActionPerformed(evt);
            }
        });

        listProveedores.setBorder(javax.swing.BorderFactory.createTitledBorder("Proveedores"));
        listProveedores.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                listProveedoresAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        listProveedores.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listProveedoresValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(listProveedores);

        btnQuitarProveedor.setText("Quitar Proveedor");
        btnQuitarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarProveedorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAgregarProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(btnQuitarProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btnAgregarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnQuitarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jButton3.setText("Guardar Borrador");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButtonAutorizar.setText("Autorizar cotizacion");
        jButtonAutorizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAutorizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBtnAutorizarGuardarLayout = new javax.swing.GroupLayout(jPanelBtnAutorizarGuardar);
        jPanelBtnAutorizarGuardar.setLayout(jPanelBtnAutorizarGuardarLayout);
        jPanelBtnAutorizarGuardarLayout.setHorizontalGroup(
            jPanelBtnAutorizarGuardarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBtnAutorizarGuardarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAutorizar, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
        );
        jPanelBtnAutorizarGuardarLayout.setVerticalGroup(
            jPanelBtnAutorizarGuardarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBtnAutorizarGuardarLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanelBtnAutorizarGuardarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonAutorizar, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonEnviarCotizacion.setText("Enviar Cotizacion");
        jButtonEnviarCotizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnviarCotizacionActionPerformed(evt);
            }
        });

        jButton1.setText("Guardar Cotizacion");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelEnviarCotizacionLayout = new javax.swing.GroupLayout(jPanelEnviarCotizacion);
        jPanelEnviarCotizacion.setLayout(jPanelEnviarCotizacionLayout);
        jPanelEnviarCotizacionLayout.setHorizontalGroup(
            jPanelEnviarCotizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEnviarCotizacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelEnviarCotizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonEnviarCotizacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelEnviarCotizacionLayout.setVerticalGroup(
            jPanelEnviarCotizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEnviarCotizacionLayout.createSequentialGroup()
                .addComponent(jButtonEnviarCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnCancelar.setText("Cancelar Cotizacion");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanelEnviarCotizacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(23, 23, 23))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelAgegarDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(841, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(335, 335, 335))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(335, 335, 335))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanelBtnAutorizarGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(347, 347, 347))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelAgegarDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelEnviarCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBtnAutorizarGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAutorizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAutorizarActionPerformed
        if (jDateVigencia.getDate().after(fecha.getDate())) {
            actualizarCabecera("AU");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Debes elegir una fecha de vigencia valida");
        }

    }//GEN-LAST:event_jButtonAutorizarActionPerformed

    private void actualizarCabecera(String Estado) {
        ArrayList<ACabeceraCotizacion> cabeceraCotizacionArray = new ArrayList();
        cabeceraCotizacion = new ACabeceraCotizacion();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(jDateVigencia.getDate());
        cabeceraCotizacion.setIdUsuario(obtenerUsuario.getIdUsuario());
        cabeceraCotizacion.setEstadoCotizacion(Estado);
        cabeceraCotizacion.setFecha(Date.valueOf(fecha.getFechaSistema()));
        cabeceraCotizacion.setVigencia(Date.valueOf(date));
        cabeceraCotizacion.setObservaciones(jTextObeservaciones.getText());
        cabeceraCotizacion.setIdCotizacion(idCotizacion);
        cabeceraCotizacionArray.add(cabeceraCotizacion);
        traer.getCabeceraCotizacion().actualizar(cabeceraCotizacionArray);
    }

    private void jMenuVerDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuVerDetalleActionPerformed
        DetallesDeDetallesConsolidados abrir = new DetallesDeDetallesConsolidados(Integer.valueOf(idDetalleCotizacion));
        abrir.setVisible(true);
    }//GEN-LAST:event_jMenuVerDetalleActionPerformed

    private void jTableDetalleCotizacionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDetalleCotizacionMouseReleased
        row = seleccionar.seleccionItemTabla(jTableDetalleCotizacion, evt, jPopupMenu1);
        idDetalleCotizacion = jTableDetalleCotizacion.getValueAt(row, 0).toString();
    }//GEN-LAST:event_jTableDetalleCotizacionMouseReleased

    private void jTableDetalleCotizacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableDetalleCotizacionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableDetalleCotizacionKeyReleased

    private void jButtonAgregarDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarDetalleActionPerformed
        AgregarDetallePedidos abrir = new AgregarDetallePedidos(jTableDetalleCotizacion, tabla, Integer.valueOf(txtIdCotizacion.getText()));
        abrir.setVisible(true);
    }//GEN-LAST:event_jButtonAgregarDetalleActionPerformed

    private void btnAgregarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProveedorActionPerformed
        listProveedores.setModel(modeloJList);
        VerProveedor abrir = new VerProveedor(listProveedores, jPanelEnviarCotizacion);
        abrir.setVisible(true);

    }//GEN-LAST:event_btnAgregarProveedorActionPerformed

    private void btnQuitarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarProveedorActionPerformed
        quitarElementoJList();
    }//GEN-LAST:event_btnQuitarProveedorActionPerformed

    private void listProveedoresValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listProveedoresValueChanged

        if (!listProveedores.isSelectionEmpty()) {
            btnQuitarProveedor.setEnabled(true);
        } else {
            btnQuitarProveedor.setEnabled(false);

        }
    }//GEN-LAST:event_listProveedoresValueChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        actualizarCabecera("BO");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void listProveedoresAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_listProveedoresAncestorAdded


    }//GEN-LAST:event_listProveedoresAncestorAdded

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        actualizarCabecera("AU");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonEnviarCotizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnviarCotizacionActionPerformed
        ACotizacionXProveedor insertar;
        if (!modeloJList.isEmpty()) {
            int i = modeloJList.getSize();
            System.out.println(i);
            for (int a = 0; a < i; a++) {
                insertar = new ACotizacionXProveedor();
                String nombreProveedor = modeloJList.getElementAt(a).toString();
                insertar.setIdProveedor(traer.getProveedor().obtenerIdProveedorXNombre(nombreProveedor));
                insertar.setIdCotiazacion(idCotizacion);
                traer.getCotizacionXProveedor().insertar(insertar);
            }
            actualizarCabecera("EN");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un proveedor como minimo");
        }
    }//GEN-LAST:event_jButtonEnviarCotizacionActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        int a = tabla.getRowCount();
        ArrayList<Integer> nroPedido = new ArrayList();
        for (int i = 0; i < a; i++) {
            Integer nroDetalleCotizacion = Integer.valueOf(jTableDetalleCotizacion.getValueAt(i, 0).toString());
            traer.getDetalleDetalle().obtenerUno(nroDetalleCotizacion).forEach((ADetalleDetalleDeCotizacion adt) -> {
                traer.getDetallePedidos().obtenerUno(adt.getIdDetallePedido()).forEach((ADetallePedidos adps) -> {
                    nroPedido.add(adps.getNumeroPedidos());
                });
            });
        }
        actualizarCabecera("CA");
        traer.getCabeceraPedidos().actulizarEstado(nroPedido.stream().distinct().collect(Collectors.toList()), "AU");

        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed
    private void quitarElementoJList() {
        int i = listProveedores.getSelectedIndex();
        modeloJList.removeElementAt(i);
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
            java.util.logging.Logger.getLogger(FGenerarCotizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FGenerarCotizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FGenerarCotizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FGenerarCotizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FGenerarCotizacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarProveedor;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnQuitarProveedor;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonAgregarDetalle;
    private javax.swing.JButton jButtonAutorizar;
    private javax.swing.JButton jButtonEnviarCotizacion;
    private com.toedter.calendar.JDateChooser jDateVigencia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuItem jMenuVerDetalle;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelAgegarDetalle;
    private javax.swing.JPanel jPanelBtnAutorizarGuardar;
    private javax.swing.JPanel jPanelEnviarCotizacion;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTableDetalleCotizacion;
    private javax.swing.JTextArea jTextObeservaciones;
    private javax.swing.JList<String> listProveedores;
    private javax.swing.JLabel txtEstado1;
    private javax.swing.JLabel txtIdCotizacion;
    private javax.swing.JLabel txtUltimaEdicion;
    // End of variables declaration//GEN-END:variables

}
