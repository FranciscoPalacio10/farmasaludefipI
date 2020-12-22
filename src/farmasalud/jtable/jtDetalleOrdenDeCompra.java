/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.jtable;

import farmasalud.atributosclases.ADetalleOrdenDeCompra;
import farmasalud.atributosclases.ADetalleParteDeRecepcion;
import farmasalud.operaciones.OManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Francisco Palacio
 */
public class jtDetalleOrdenDeCompra extends AbstractTableModel {
    private OManager manager;
    private ArrayList<ADetalleOrdenDeCompra> arrayListDetalleOrdenDeCompra= new ArrayList();
    private String nombreProducto;
    private int cantidadAceptada;
    public jtDetalleOrdenDeCompra(OManager manager) {
        this.manager = manager;
    }
    
    @Override
    public int getRowCount() {
        return arrayListDetalleOrdenDeCompra.size();
    }
    
    public void updateModel(int idOrdenDeCompra){
         arrayListDetalleOrdenDeCompra=manager.getDetalleOrdenDeComrpa().obtenerDetaleXIdCabcera(idOrdenDeCompra);
    }
    
    public void add(ArrayList<ADetalleOrdenDeCompra> a){
         arrayListDetalleOrdenDeCompra.addAll(a);
    }
      public void removeRow(int fila){
       this.arrayListDetalleOrdenDeCompra.remove(fila);
          this.fireTableStructureChanged();
           }
  @Override
    public String getColumnName(int i) {
        switch (i) {
            case 0: return "id detalle OC";
            case 1:return "Id Producto";
            case 2: return "Nombre Producto";
            case 3:return "Cantidad";
            case 4: return "Cantidad Aceptada";
            case 5:return "Precio Unitario";
                 default: return "[No]";
        }
    }
    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int fila, int columna) {
      
       ADetalleOrdenDeCompra traer =  arrayListDetalleOrdenDeCompra.get(fila);
        manager.getProductos().obtenerNombreProducto((int) traer.getId_producto(), manager).forEach((String nombre)->{
           nombreProducto=nombre;
       }); 
        
       manager.getDetalleParteDeRecepcion().obtenerTodos().stream().collect(Collectors.groupingBy(a->a.getDetalleOrdenDeCompra()==traer.getIdDetalleOC(),
               Collectors.summingInt(b->b.getCantidadAceptada()))).forEach((Boolean a, Integer c)-> {
              System.out.println(a);
              if(a){
               cantidadAceptada=c;   
              }else{
                   cantidadAceptada=0;
              }
            
               });
        
       switch(columna){
           case 0: return traer.getIdDetalleOC();
           case 1: return traer.getId_producto();
           case 2: return nombreProducto;
           case 3: return traer.getCantidad();
           case 4: return  cantidadAceptada;
           default: return "[No]";
       }
    }
    
     @Override
    public boolean isCellEditable(int i, int i1) {
        return false; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
