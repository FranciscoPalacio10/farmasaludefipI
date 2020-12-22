/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.jtable;

import farmasalud.atributosclases.ADetalleCotizacion;
import farmasalud.atributosclases.ADetallePedidos;
import farmasalud.atributosclases.AFormaFarmaceutica;
import farmasalud.atributosclases.ANombreComercial;
import farmasalud.atributosclases.AProducto;
import farmasalud.atributosclases.AVademecum;
import farmasalud.operaciones.ODetallesCotizaciones;
import farmasalud.operaciones.ODetallesPedidos;
import farmasalud.operaciones.OManager;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Francisco Palacio
 */
public class jtDetalleCotizaciones extends AbstractTableModel {

    private String nombre, medida, formaFarmaceutica;
    private ODetallesCotizaciones detalles;
    private List<ADetalleCotizacion> lista = new ArrayList<>();
    private int concentracion, cantidadPresentacion;

    public jtDetalleCotizaciones(ODetallesCotizaciones detalles) {
        this.detalles = detalles;
    }

    public void update(int idCabecera){
        lista=detalles.obtenerUno(idCabecera);
    }
    
    
    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        ADetalleCotizacion obtener = lista.get(fila);
        OManager manager = new OManager();
        manager.getProductos().obtenerUno(obtener.getIdProducto()).forEach((AProducto as) -> {
            manager.getVademecum().obtenerUno(as.getIdVademecum()).forEach((AVademecum av) -> {
                medida = av.getIdMedida();
                concentracion = av.getConcentracion();
                cantidadPresentacion = av.getCantidadPresentacion();

                manager.getFormaFarmaceutica().obtenerUno(av.getFormaFarmaceutica()).forEach((AFormaFarmaceutica af) -> {
                    formaFarmaceutica = af.getDescripcion();
                });
                manager.getNombreComercial().obtenerUno(av.getNombreComercial()).forEach((ANombreComercial anc) -> {
                    nombre = anc.getDescripcion();

                });
            });
        });

        manager.getProductos().obtenerNombreProducto(obtener.getIdProducto(), manager).forEach((String a) -> {
            nombre = a;

        });
        switch (columna) {
            case 0:
                return obtener.getIdDetalleCotizacion();
            case 1:
                return obtener.getIdProducto();
            case 2:
                return nombre;
            case 3:
                return obtener.getCantidad();
            case 4:
                return medida;
            case 5:
                return concentracion;
            case 6:
                return formaFarmaceutica;
            case 7:
                return cantidadPresentacion;
            default:
                return "[No]";
        }
    }

    @Override
    public String getColumnName(int i) {
        switch (i) {
            case 0:
                return "Id";
            case 1:
                return "Id producto";
            case 2:
                return "Nombre producto";
            case 3:
                return "Cantidad Pedida";
            case 4:
                return "Medida";
            case 5:
                return "Concentracion";
            case 6:
                return "Forma Farmaceutica";
            case 7:
                return "Cantidad Prensentacion";
            default:
                return "[No]";
        }
    }
}
