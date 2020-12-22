/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.pedidosSucursales;

import farmasalud.atributosclases.ADetallePedidos;
import farmasalud.atributosclases.ACabeceraPedidos;
import farmasalud.operaciones.OManager;
import com.toedter.calendar.JDateChooser;
import farmasalud.ConsultasGenerales;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import static javafx.scene.input.KeyCode.I;
import static javafx.scene.input.KeyCode.M;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import jdk.internal.dynalink.linker.ConversionComparator;
import jdk.internal.dynalink.linker.ConversionComparator.Comparison;
import farmasalud.atributosclases.AEstados;
import farmasalud.atributosclases.ANombreComercial;
import farmasalud.jtable.jtCabeceraPedidos;
import farmasalud.operaciones.OCabeceraPedidos;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.stream.Collectors;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Francisco Palacio
 */
public class FPedidosSucursales extends javax.swing.JFrame {

    private final ConsultasGenerales consultas;
    private int row;
    private final OManager manager;
    private boolean bSucursal, bPedidos, bEstado, bFecha1, bFecha2, bNombreComercial;
    private RowFilter<Object, Object> numPedFilter, sucFilter, estadoFilter, nuevoFilter, txtFilter,
            fecha1Filter, fecha2Filter;
    private final List<RowFilter<Object, Object>> filtros = new ArrayList<>();
    private final List<RowFilter<Object, Object>> andfiltros = new ArrayList<>();
    private final List<RowFilter<Object, Object>> orFiltros = new ArrayList<>();
    private TableRowSorter sorter;
    private final TableModel modelo;
    private ArrayList<ACabeceraPedidos> arrayListPedidos = new ArrayList<>();
    private ArrayList<ADetallePedidos> arrayListDetallesPedidos = new ArrayList<>();
    private ArrayList<ANombreComercial> arrayListNombreComercial = new ArrayList<>();
    private ArrayList<AEstados> arrayListEstados = new ArrayList<>();
    private final Font font = new Font("TAHOMA", Font.BOLD, 16);
    private final String PROCEDIMIENTO_BUSCAR_DETALLE_FARMA = "call buscarEnDetallesFarmaceutico(?)";
    private final String PROCEDIMIENTO_BUSCAR_DETALLE_NO_FARMA = "call buscarEnDetallesNoFarmaceutico(?)";

    private jtCabeceraPedidos modelo1;

    /**
     * Creates new form Recibiepedidos
     */
    public FPedidosSucursales() {
        
        initComponents();
        mostrarTitulo();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        manager = new OManager();
        // obteneer sucursales y estados para jComboBox
        arrayListEstados = manager.getEstados().obtenerUno("Pedidos");
        arrayListEstados.forEach((e) -> jComboEstado.addItem(e.getDescripcion()));
        manager.getSucursales().obtenerTodos().forEach((e) -> jComboSucursal.addItem(e.getIdSucursal()));
        llenarTablas();
        //listenersFechas
        listenerFecha1();
        listenerFecha2();

        //obtener instancia ACabeceraPedidos Cabecera
        arrayListPedidos = manager.getCabeceraPedidos().obtenerTodos();
        arrayListPedidos.stream().forEach((e) -> jComboNumPedido.addItem(String.valueOf(e.getNumeroPedido())));
        // llenarTablas(jTablePedidos, arrayListPedidos);

        //descativar botones
        btnBuscar.setEnabled(false);
        btnLimpiarFiltros.setEnabled((false));

        //item verDetalles
        jMenuVerDetalle.setText("Ver detalle");

        //instancia consultas generales
        consultas = ConsultasGenerales.obtenerInstacia();

        //tabla y obtener instancia de filtrado sorter
        modelo = jTablePedidos.getModel();
        sorter = new TableRowSorter(modelo);
        jTablePedidos.setRowSorter(sorter);

    }

    private void llenarTablas() {
        modelo1 = new jtCabeceraPedidos(manager);
        modelo1.updateModel();
        jTablePedidos.getTableHeader().setReorderingAllowed(false);
        pintarCeldas pintar = new pintarCeldas("Pedidos");
        jTablePedidos.setModel(modelo1);
        jTablePedidos.getColumn("Estado").setCellRenderer(pintar);
    }

    private void listenerFecha1() {
        txtFecha1.getDateEditor().addPropertyChangeListener((PropertyChangeEvent e) -> {
            if ("date".equals(e.getPropertyName())) {
                bFecha1 = filtrarDate(txtFecha1);
                if (bFecha1 == true) {
                    fecha2Filter = RowFilter.dateFilter(RowFilter.ComparisonType.AFTER, txtFecha1.getDate(), 3);
                    btnBuscar.setEnabled(true);
                } else {
                    btnBuscar.setEnabled(false);
                }

            } else {
                btnBuscar.setEnabled(false);

            }
        });
    }

    private void listenerFecha2() {
        txtFecha2.getDateEditor().addPropertyChangeListener((PropertyChangeEvent e) -> {
            if ("date".equals(e.getPropertyName())) {
                bFecha2 = filtrarDate(txtFecha2);
                if (bFecha2 == true) {
                    fecha2Filter = RowFilter.dateFilter(RowFilter.ComparisonType.BEFORE, txtFecha2.getDate(), 3);
                    btnBuscar.setEnabled(true);
                }
            } else {
                btnBuscar.setEnabled(false);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuPop = new javax.swing.JPopupMenu();
        jMenuVerDetalle = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePedidos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jComboNumPedido = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtFecha1 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jComboSucursal = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboEstado = new javax.swing.JComboBox<>();
        btnLimpiarFiltros = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtFecha2 = new com.toedter.calendar.JDateChooser();
        txtNombreComercial = new javax.swing.JTextField();
        btnSeleccionarNombreComercial = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();

        menuPop.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                menuPopKeyPressed(evt);
            }
        });

        jMenuVerDetalle.setText("jMenuItem1");
        jMenuVerDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuVerDetalleActionPerformed(evt);
            }
        });
        menuPop.add(jMenuVerDetalle);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("PEDIDOS");

        jTablePedidos.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTablePedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Numero pedido", "Sucursal", "Lugar de entrega", "Fecha", "Estado", "Usuario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTablePedidos.setRowHeight(32);
        jTablePedidos.setRowMargin(4);
        jTablePedidos.setSelectionBackground(new java.awt.Color(0, 153, 0));
        jTablePedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTablePedidosMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTablePedidosMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTablePedidos);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Filtros busqueda.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jComboNumPedido.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jComboNumPedido.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        jComboNumPedido.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboNumPedidoItemStateChanged(evt);
            }
        });
        jComboNumPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboNumPedidoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Numero pedido:");

        txtFecha1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Desde:");

        jComboSucursal.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jComboSucursal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        jComboSucursal.setPreferredSize(new java.awt.Dimension(95, 25));
        jComboSucursal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboSucursalItemStateChanged(evt);
            }
        });
        jComboSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboSucursalActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Sucursal:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Estado:");

        jComboEstado.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jComboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        jComboEstado.setPreferredSize(new java.awt.Dimension(95, 25));
        jComboEstado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboEstadoItemStateChanged(evt);
            }
        });
        jComboEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboEstadoActionPerformed(evt);
            }
        });

        btnLimpiarFiltros.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnLimpiarFiltros.setText("Limpiar filtros tabla");
        btnLimpiarFiltros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarFiltrosActionPerformed(evt);
            }
        });

        btnBuscar.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Hasta:");

        txtFecha2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        txtNombreComercial.setEditable(false);
        txtNombreComercial.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtNombreComercial.setText("Nombre Comercial");
        txtNombreComercial.setToolTipText("");
        txtNombreComercial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreComercialActionPerformed(evt);
            }
        });

        btnSeleccionarNombreComercial.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnSeleccionarNombreComercial.setText("Seleccionar");
        btnSeleccionarNombreComercial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarNombreComercialActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboNumPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                        .addComponent(txtNombreComercial, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboEstado, 0, 165, Short.MAX_VALUE)
                    .addComponent(jComboSucursal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSeleccionarNombreComercial, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(241, 241, 241)
                .addComponent(btnLimpiarFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboNumPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jComboSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jComboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(9, 9, 9)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNombreComercial, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSeleccionarNombreComercial, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiarFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 160, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(527, 527, 527)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTablePedidosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePedidosMousePressed

    }//GEN-LAST:event_jTablePedidosMousePressed

    private void jTablePedidosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePedidosMouseReleased
        row = consultas.seleccionItemTabla(jTablePedidos, evt, menuPop);
    }//GEN-LAST:event_jTablePedidosMouseReleased


    private void menuPopKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_menuPopKeyPressed

    }//GEN-LAST:event_menuPopKeyPressed

    private void jMenuVerDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuVerDetalleActionPerformed
        int nro = Integer.parseInt(jTablePedidos.getValueAt(row, 0).toString());
        FDetallesPedidosSucursales abrir = new FDetallesPedidosSucursales(nro);
        abrir.setVisible(true);
    }//GEN-LAST:event_jMenuVerDetalleActionPerformed

    private void jComboEstadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboEstadoItemStateChanged
        bEstado = filtrarJCombo(jComboEstado);
        if (bEstado == true) {
            btnBuscar.setEnabled(true);
            estadoFilter = RowFilter.regexFilter(jComboEstado.getSelectedItem().toString(), 4);
        } else {
            btnBuscar.setEnabled(false);
        }
    }//GEN-LAST:event_jComboEstadoItemStateChanged

    private void btnLimpiarFiltrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarFiltrosActionPerformed
        andfiltros.removeAll(andfiltros);
        filtros.removeAll(filtros);
        orFiltros.removeAll(orFiltros);
        txtFilter = null;
        nuevoFilter = null;
        numPedFilter = null;
        sucFilter = null;
        estadoFilter = null;
        fecha1Filter = null;
        fecha2Filter = null;
        bSucursal = false;
        bPedidos = false;
        bFecha1 = false;
        bFecha2 = false;
        bNombreComercial = false;
        bEstado = false;
        btnBuscar.setEnabled(false);
        btnLimpiarFiltros.setEnabled((false));
        jComboNumPedido.setSelectedIndex(0);
        jComboSucursal.setSelectedIndex(0);
        jComboEstado.setSelectedIndex(0);
        txtNombreComercial.setText("Nombre Comercial");
        txtFecha1.setCalendar(null);
        txtFecha2.setCalendar(null);
        llenarTablas();
        sorter = new TableRowSorter(jTablePedidos.getModel());
        jTablePedidos.setRowSorter(sorter);
    }//GEN-LAST:event_btnLimpiarFiltrosActionPerformed

    private void jComboNumPedidoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboNumPedidoItemStateChanged
        bPedidos = filtrarJCombo(jComboNumPedido);
        if (bPedidos == true) {
            btnBuscar.setEnabled(true);
            numPedFilter = RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL,
                    Integer.parseInt(jComboNumPedido.getSelectedItem().toString()), 0);
        } else {
            btnBuscar.setEnabled(false);

        }

    }//GEN-LAST:event_jComboNumPedidoItemStateChanged

    private void jComboSucursalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboSucursalItemStateChanged
        bSucursal = filtrarJCombo(jComboSucursal);
        if (bSucursal == true) {
            btnBuscar.setEnabled(true);
            sucFilter = RowFilter.regexFilter(jComboSucursal.getSelectedItem().toString(), 1);
        } else {
            btnBuscar.setEnabled(false);

        }

    }//GEN-LAST:event_jComboSucursalItemStateChanged

    private void jComboNumPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboNumPedidoActionPerformed

    }//GEN-LAST:event_jComboNumPedidoActionPerformed

    private void jComboSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboSucursalActionPerformed


    }//GEN-LAST:event_jComboSucursalActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if (bSucursal == false && bPedidos == false && bEstado == false && bFecha1 == false && bFecha2 == false
                && bNombreComercial == false) {
            btnBuscar.setEnabled(false);
            JOptionPane.showMessageDialog(this, "Debe elegir un filtro");
        } else {
            if (bNombreComercial == true && !txtNombreComercial.getText().equals("Nombre Comercial")) {
                filtrarDetallesPedidos();
                txtFilter = RowFilter.orFilter(orFiltros);
                System.out.println(txtFilter);
            }
            filtros.add(txtFilter);
            filtros.add(sucFilter);
            filtros.add(estadoFilter);
            filtros.add(numPedFilter);
            filtros.add(fecha1Filter);
            filtros.add(fecha2Filter);
            System.out.println(filtros);
            filtros.forEach((RowFilter<Object, Object> a) -> {
                if (a != null) {
                    andfiltros.add(a);
                }
            });
            nuevoFilter = RowFilter.andFilter(andfiltros);
            System.out.println(andfiltros);
            sorter.setRowFilter(nuevoFilter);

        }
        btnLimpiarFiltros.setEnabled(true);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void jComboEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboEstadoActionPerformed

    }//GEN-LAST:event_jComboEstadoActionPerformed

    private void btnSeleccionarNombreComercialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarNombreComercialActionPerformed
        JComboBox sModel = new javax.swing.JComboBox<>();
        sModel.setFont(font);
        AutoCompleteDecorator.decorate(sModel);
        List<Integer> a = manager.getDetallePedidos().obtenerTodos().stream().collect(Collectors.groupingBy(s -> s.getIdProducto())).keySet()
                .stream().distinct().collect((Collectors.toList()));
        a.forEach((Integer as) -> {
            arrayListNombreComercial = manager.getNombreComercial().obtenerNombreComercialProductosFarmace(as);
            arrayListNombreComercial.addAll(manager.getNombreComercial().obtenerNombreComercialProductosNoFarmace(as));
            arrayListNombreComercial.forEach((ANombreComercial anc) -> {
                sModel.addItem(anc.getDescripcion());
            });
        });

        int elegido = JOptionPane.showConfirmDialog(this, sModel,
                "Selecciona un detalle de pedido", JOptionPane.DEFAULT_OPTION);
        if (elegido == JOptionPane.OK_OPTION) {
            txtNombreComercial.setText(sModel.getSelectedItem().toString());
            bNombreComercial = true;
            btnBuscar.setEnabled(true);
        }
    }//GEN-LAST:event_btnSeleccionarNombreComercialActionPerformed

    private void txtNombreComercialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreComercialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreComercialActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
              long TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
  TInicio = System.currentTimeMillis(); 
            new FPedidosSucursales().setVisible(true);
            TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
  tiempo = TFin - TInicio; //Calculamos los milisegundos de diferencia
  System.out.println("Tiempo de ejecución en milisegundos: " + tiempo); //Mostramos en pantalla el tiempo de ejecución en milisegundos
 
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnLimpiarFiltros;
    private javax.swing.JButton btnSeleccionarNombreComercial;
    private javax.swing.JComboBox<String> jComboEstado;
    private javax.swing.JComboBox<String> jComboNumPedido;
    private javax.swing.JComboBox<String> jComboSucursal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuVerDetalle;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePedidos;
    private javax.swing.JPopupMenu menuPop;
    private com.toedter.calendar.JDateChooser txtFecha1;
    private com.toedter.calendar.JDateChooser txtFecha2;
    private javax.swing.JTextField txtNombreComercial;
    // End of variables declaration//GEN-END:variables

    private void mostrarTitulo() {
        this.setTitle("PEDIDOS SUCURSALES");
    }

    private Boolean filtrarJCombo(JComboBox a) {
        boolean seleccionado;
        seleccionado = !a.getSelectedItem().toString().equals("Seleccionar");

        return seleccionado;
    }

    private Boolean filtrarDate(JDateChooser a) {
        boolean seleccionado;
        seleccionado = a.getDate() != null;
        return seleccionado;
    }

    private void filtrarDetallesPedidos() {
        String nombreComercial = txtNombreComercial.getText();
        arrayListDetallesPedidos = manager.getDetallePedidos().buscarEnDetallesProductos(nombreComercial, PROCEDIMIENTO_BUSCAR_DETALLE_FARMA);
        arrayListDetallesPedidos.addAll(manager.getDetallePedidos().buscarEnDetallesProductos(nombreComercial, PROCEDIMIENTO_BUSCAR_DETALLE_NO_FARMA));
        arrayListDetallesPedidos.forEach((ADetallePedidos pe) -> {
            orFiltros.add(RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, pe.getNumeroPedidos(), 0));
        });
        System.out.println(andfiltros);
    }

}
