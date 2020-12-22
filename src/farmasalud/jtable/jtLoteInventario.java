/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.jtable;

import farmasalud.atributosclases.AInvetario;
import farmasalud.atributosclases.ALoteProducto;
import farmasalud.atributosclases.AProducto;

import farmasalud.operaciones.OManager;
import java.sql.Date;

import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Francisco Palacio
 */
public class jtLoteInventario extends AbstractTableModel {

    private OManager manager;
    private List<ALoteProducto> arrayListLote;
    private String nombreProducto, sucursal;
    private Date fechaVto;
    private int puntoDePedido, cantidad1, cantidad2, cantidad3, cantidad4, cantidad5;

    public jtLoteInventario(OManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    public void updateTable() {
        arrayListLote = manager.getLoteProducto().obtenerTodos();
    }

    @Override
    public String getColumnName(int i) {
        switch (i) {
            case 0:
                return "Lote";
            case 1:
                return "Nombre producto";
            case 2:
                return "Fecha VTO";
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
            default:
                return "[No]";
        }

    }

    @Override
    public int getRowCount() {
        return arrayListLote.size();
    }

    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        limpiarCantidades();
        ALoteProducto traer = arrayListLote.get(fila);
        manager.getDetalleLote().procedimientoObtenerStock(traer.getIdLote()).forEach((AInvetario ain) -> {
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
          manager.getProductos().obtenerNombreProducto(ain.getIdProducto(), manager).forEach((String nombre) -> {
               nombreProducto = nombre;
           });
          
        });
        switch (columna) {
            case 0:
                return traer.getIdLote();
            case 1:
                return nombreProducto;
            case 2:
                return traer.getFechaVto();
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
            default:
                return "[No]";
        }
    }

    private void limpiarCantidades() {
        cantidad1 = 0;
        cantidad2 = 0;
        cantidad3 = 0;
        cantidad4 = 0;
        cantidad5 = 0;
    }
    
    
    

}
