/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.jtable;

import farmasalud.atributosclases.ACabeceraPedidos;
import farmasalud.atributosclases.ADetallePedidos;
import farmasalud.operaciones.OManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Francisco Palacio
 */
public class jtDetallePedidos extends AbstractTableModel {

    private List<ADetallePedidos> lista = new ArrayList<>();
    private OManager manager;
    private String nombre;
    private Date fecha;

    public jtDetallePedidos(OManager manager) {
        this.manager = manager;

    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    public void update(int nroPedido) {
        lista = manager.getDetallePedidos().obtenerDetallesPorCabecera(nroPedido);

    }

    @Override
    public String getColumnName(int i) {
        switch (i) {
            case 0:
                return "Nro Detalle";
            case 1:
                return "Id producto";
            case 2:
                return "Nombre producto";
            case 3:
                return "Cantidad";
            case 4:
                return "Cantidad Envio Interno";
            case 5:
                return "Fecha";
            default:
                return "[No]";
        }

    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 5;

    }

    @Override
    public Object getValueAt(int fila, int columna) {
        ADetallePedidos detalles = lista.get(fila);
        manager.getProductos().obtenerNombreProducto(detalles.getIdProducto(), manager).forEach((String a) -> {
            nombre = a;
        });
        switch (columna) {
            case 0:
                return detalles.getIdDetalle();
            case 1:
                return detalles.getIdProducto();
            case 2:
                return nombre;
            case 3:
                return detalles.getCantidad();

            case 4:
                return detalles.getCantidadEnvioInterno();
            default:
                return "[No]";
        }

    }

}
