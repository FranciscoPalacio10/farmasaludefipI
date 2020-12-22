/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.jtable;

import farmasalud.atributosclases.ADetalleParteDeRecepcion;
import farmasalud.atributosclases.ALoteProducto;
import farmasalud.atributosclases.AParteDeRecepcion;
import farmasalud.atributosclases.AProducto;
import farmasalud.operaciones.OManager;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Francisco Palacio
 */
public class jtDetalleRecepcion extends AbstractTableModel {

    private ArrayList<ADetalleParteDeRecepcion> arrayListDetalleRecepcion = new ArrayList();
    private OManager manager;
    private String nombreProducto, loteProducto;
    private Object valor = "No";
    private int cantidad;
    private String estado;

    public jtDetalleRecepcion(OManager manager) {
        this.manager = manager;
    }

    public void updateModel(int idParteRecepcion) {
        arrayListDetalleRecepcion = manager.getDetalleParteDeRecepcion().obtenerPorIdCabecera(idParteRecepcion);
    }

    @Override
    public void setValueAt(Object o, int fila, int columna) {
        super.setValueAt(o, fila, columna);
        switch (columna) {
            case 7:
                arrayListDetalleRecepcion.get(fila).setValor((String) o);
                break;
            case 3:
                arrayListDetalleRecepcion.get(fila).setCantidadAceptada((int) o);
                System.out.println(arrayListDetalleRecepcion.get(fila));
                break;
            case 4:
                arrayListDetalleRecepcion.get(fila).setCantidadRechazada((int) o);
                break;
            case 8:
                arrayListDetalleRecepcion.get(fila).setCantidadDisponible((int) o);
                break;
        }
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getColumnName(int i) {
        switch (i) {
            case 0:
                return "Id Detalle";
            case 1:
                return "Cantidad Pedida";
            case 2:
                return "Cantidad Recibida";
            case 3:
                return "Cantidad Aceptada";
            case 4:
                return "Cantidad Rechazada";
            case 5:
                return "Producto";
            case 6:
                return "Lote producto";
            case 7:
                return "Verficado";
            default:
                return "[No]";
        }
    }

    @Override
    public int getRowCount() {
        return arrayListDetalleRecepcion.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        ADetalleParteDeRecepcion obtener = arrayListDetalleRecepcion.get(fila);
        manager.getProductos().obtenerNombreProducto(obtener.getIdProducto(), manager).forEach((String nombre) -> {
            nombreProducto = nombre;
        });
        manager.getLoteProducto().obtenerTodos().stream().filter(a -> a.getIdDetalleParteRecepcion() == obtener.getIdDetalleParteRecepcion())
                .collect(Collectors.toList()).forEach((ALoteProducto alt) -> {
            loteProducto = alt.getIdLote();
        });
        manager.getParteDeRecepcion().obtenerUno(obtener.getiDPartederecepcion()).forEach((AParteDeRecepcion adp)-> {
            estado=adp.getEstado();
        });
        cantidad=obtener.getCantidadRecibida()-(obtener.getCantidadRechazada()+obtener.getCantidadAceptada());
  
        
        
        
      if(estado.equals("BO") && cantidad==0 || obtener.getCantidadDisponible()!=0) {
            switch (columna) {
            case 0:
                return obtener.getIdDetalleParteRecepcion();
            case 1:
                return obtener.getCantidad();
            case 2:
                return obtener.getCantidadRecibida();
            case 3:
                return obtener.getCantidadAceptada();
            case 4:
                return obtener.getCantidadRechazada();
            case 5:
                return nombreProducto;
            case 6:
                return loteProducto;
            case 7:
                if (obtener.getValor() == null) {
                    return "No";
                } else {
                    return obtener.getValor();
                }

            default:
                return "[No]";
        } }else if(estado.equals("BO") && cantidad!=0 ){
                     switch (columna) {
            case 0:
                return obtener.getIdDetalleParteRecepcion();
            case 1:
                return obtener.getCantidad();
            case 2:
                return obtener.getCantidadRecibida();
            case 3:
                return obtener.getCantidadAceptada();
            case 4:
                return obtener.getCantidadRechazada();
            case 5:
                return nombreProducto;
            case 6:
                return loteProducto;
            case 7:
                if (obtener.getValor() == null) {
                    return "No";
                } else {
                    return obtener.getValor();
                }

            default:
                return "[No]";
        }}else if(estado.equals("BO") && cantidad==0 &&  obtener.getCantidadDisponible()==0)
      switch (columna) {
          default:  return null;
      }
      
      
      else if(!estado.equals("BO")){
            switch (columna) {
            case 0:
                return obtener.getIdDetalleParteRecepcion();
            case 1:
                return obtener.getCantidad();
            case 2:
                return obtener.getCantidadRecibida();
            case 3:
                return obtener.getCantidadAceptada();
            case 4:
                return obtener.getCantidadRechazada();
            case 5:
                return nombreProducto;
            case 6:
                return loteProducto;
            default:
                return "[No]";
            }
            
            }
        return null;
    
    }

    public ADetalleParteDeRecepcion obtenerDetalles(int fila) {
        return arrayListDetalleRecepcion.get(fila);
    }

}
