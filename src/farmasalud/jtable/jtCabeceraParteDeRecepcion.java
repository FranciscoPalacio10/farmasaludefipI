/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.jtable;

import farmasalud.atributosclases.AParteDeRecepcion;
import farmasalud.atributosclases.AProveedor;
import farmasalud.operaciones.OManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Francisco Palacio
 */
public class jtCabeceraParteDeRecepcion extends AbstractTableModel {

    private OManager manager;
    private List<AParteDeRecepcion> arrayListCabeceraRecepcion;
    private String nombreProveedor;

    public jtCabeceraParteDeRecepcion(OManager manager) {
        this.manager = manager;
    }

    public void updateTable(String estado){
        arrayListCabeceraRecepcion=manager.getParteDeRecepcion().obtenerTodos().stream()
                .filter(a->estado.equals(a.getEstado())).collect(Collectors.toList());
    }
    
    @Override
    public boolean isCellEditable(int i, int i1) {
        return false; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getColumnName(int i) {
        switch (i) {
            case 0:
                return "Id Cabecera Parte Recepcion";
            case 1:
                return "Numero Orden De Compra";
            case 2:
                return "Numero Remito";
            case 3:
                return "Proveedor";
            case 4:
                return "Fecha Recepcion";
            case 5:
                return "Estado";
            case 6:
                return "Observaciones";
            default:
                return "[No]";
        }
    }

    @Override
    public int getRowCount() {
        return arrayListCabeceraRecepcion.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        AParteDeRecepcion obtener = arrayListCabeceraRecepcion.get(fila);
        manager.getProveedor().obtenerUno(obtener.getIdProveedor()).forEach((AProveedor traer) -> {
            nombreProveedor = traer.getNombre();

        });

        switch (columna) {
            case 0:
                return obtener.getIdParteRecepcion();
            case 1:
                return obtener.getNumeroOc();
            case 2:
                return obtener.getNumeroRemito();
            case 3:
                return nombreProveedor;
            case 4:
                return obtener.getFechaRecepcion();
            case 5:
                return obtener.getEstado();
            case 6:
                return obtener.getObservaciones();
            default:
                return "[No]";

        }

    }

}
