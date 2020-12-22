/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.jtable;

import farmasalud.atributosclases.ACabeceraOrdenDeCompra;
import farmasalud.atributosclases.ADetalleLote;
import farmasalud.atributosclases.ADetalleOrdenDeCompra;
import farmasalud.atributosclases.AInvetario;
import farmasalud.atributosclases.ALoteProducto;
import farmasalud.atributosclases.AProducto;
import farmasalud.operaciones.OManager;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Francisco Palacio
 */
public class jtProductoInventario extends AbstractTableModel {

    private OManager manager;
    private List<AProducto> arayListProducto;
    private String nombreProducto;
    private int pendienteDeEntrega, cantidad1, cantidad2, cantidad3, cantidad4, cantidad5, idProducto;

    public jtProductoInventario(OManager manager) {
        this.manager = manager;
    }

    @Override
    public int getRowCount() {
        return arayListProducto.size();
    }

    public void updateTable() {
        arayListProducto = manager.getProductos().obtenerTodos();
    }

    @Override
    public int getColumnCount() {
        return 10;
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    @Override
    public String getColumnName(int i) {
        switch (i) {
            case 0:
                return "Id producto";
            case 1:
                return "Nombre producto";
            case 2:
                return "Stock Minimo";
            case 3:
                return "Sucursal 1";
            case 4:
                return "Sucursal 2";
            case 5:
                return "Sucursal 3";
            case 6:
                return "Sucursal 4";
            case 7:
                return "Sucursal 5";
            case 8:
                return "Total";
            case 9:
                return "Pendiente de recepcion";
            default:
                return "[No]";
        }

    }

    @Override
    public Object getValueAt(int fila, int columna) {
limpiarCantidades();
        AProducto traer = arayListProducto.get(fila);
        manager.getDetalleLote().procedimientoObtenerStockXProducto(traer.getIdProducto()).forEach((AInvetario ain) -> {
            idProducto = ain.getIdProducto();
            switch (ain.getSucursal()) {
                case "FARMACIA 1":
                    cantidad1 = ain.getCantidad();
                    break;
                case "FARMACIA 2":
                    cantidad2 = ain.getCantidad();
                    break;
                case "FARMACIA 3":
                    cantidad3 = ain.getCantidad();
                    break;
                case "FARMACIA 4":
                    cantidad4 = ain.getCantidad();
                    break;
                case "FARMACIA 5":
                    cantidad5 = ain.getCantidad();
                    break;
                default:

                    break;
            }
           
            manager.getCabeceraOrdenDeCompra().obtenerTodos().stream().filter(a -> "EN".equals(a.getEstado())).forEach((ACabeceraOrdenDeCompra acb) -> {
                manager.getDetalleOrdenDeComrpa().obtenerDetaleXIdCabcera(acb.getIdOrdenDeCompra()).forEach((ADetalleOrdenDeCompra adc) -> {
                    if (traer.getIdProducto() == adc.getId_producto()) {
                        pendienteDeEntrega = adc.getCantidad();
                    }
                });
            });

        });
 manager.getProductos().obtenerNombreProducto(traer.getIdProducto(), manager).forEach((String nombre) -> {
                nombreProducto = nombre;
            });
        switch (columna) {
            case 0:
                return traer.getIdProducto();
            case 1:
                return nombreProducto;
            case 2:
                return traer.getPuntoDePedido();
            case 3:
                return cantidad1;
            case 4:
                return cantidad2;
            case 5:
                return cantidad3;
            case 6:
                return cantidad4;
            case 7:
                return cantidad5;
            case 8:
                return cantidad1 + cantidad2 + cantidad3 + cantidad4 + cantidad5;
            case 9:
                return pendienteDeEntrega;
            default:
                return "no";
        }

    }
   private void limpiarCantidades() {
        cantidad1 = 0;
        cantidad2 = 0;
        cantidad3 = 0;
        cantidad4 = 0;
        cantidad5 = 0;
        pendienteDeEntrega=0;
    }
}
