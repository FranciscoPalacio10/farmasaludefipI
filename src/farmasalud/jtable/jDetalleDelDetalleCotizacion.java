/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.jtable;

import farmasalud.atributosclases.ACabeceraPedidos;
import farmasalud.atributosclases.ADetalleDetalleDeCotizacion;
import farmasalud.atributosclases.ADetallePedidos;
import farmasalud.atributosclases.AUsuario;
import farmasalud.operaciones.OManager;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Francisco Palacio
 */
public class jDetalleDelDetalleCotizacion extends AbstractTableModel {

    private OManager manager;
    private ArrayList<ADetalleDetalleDeCotizacion> arrayListDeltalleDetalle;
    private String sucursal;
    private Date fecha;
    private int cantidad, nroPedido;

    public jDetalleDelDetalleCotizacion(OManager manager) {
        this.arrayListDeltalleDetalle = new ArrayList();
        this.manager = manager;
         
    }

  

    public void update(int nroDetalleCotizacion) {
        arrayListDeltalleDetalle = manager.getDetalleDetalle().obtenerUno(nroDetalleCotizacion);
    }
 @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
    @Override
    public String getColumnName(int i) {
        switch (i) {
            case 0:
                return "Numero Pedido";
            case 1:
                return "Cantidad";
            case 2:
                return "Sucursal";
            case 3:
                return "Fecha del pedido";
            default:
                return "[No]";
        }
    }

    @Override
    public int getRowCount() {
        return this.arrayListDeltalleDetalle.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        ADetalleDetalleDeCotizacion traer = arrayListDeltalleDetalle.get(fila);
        manager.getDetallePedidos().obtenerUno(traer.getIdDetallePedido()).forEach((ADetallePedidos a) -> {
            nroPedido = a.getNumeroPedidos();
            cantidad = a.getCantidad();
            manager.getCabeceraPedidos().obtenerUno(a.getNumeroPedidos()).forEach((ACabeceraPedidos cp) -> {
                fecha = cp.getFechaPedido();
                manager.getUsuario().obtenerUsuarioXId(cp.getId_usuario()).forEach((AUsuario u) -> {
                    this.sucursal = u.getIdSucursal();

                });
            });
        });
        switch (columna) {
            case 0:
                return nroPedido;
            case 1:
                return cantidad;
            case 2:
                return sucursal;
            case 3:
                return fecha;
            default:
                return "[No]";
        }
    }
 }