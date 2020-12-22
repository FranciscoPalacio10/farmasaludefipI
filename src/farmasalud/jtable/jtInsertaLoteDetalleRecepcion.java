/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.jtable;

import farmasalud.atributosclases.ANuevoDetallesR;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Francisco Palacio
 */
public class jtInsertaLoteDetalleRecepcion extends AbstractTableModel {
    private ArrayList<ANuevoDetallesR> arrayListNuevoDetalles= new ArrayList();

    public jtInsertaLoteDetalleRecepcion() {
       
    }

   public void addRow(ArrayList<ANuevoDetallesR> arrayListNuevoDetalles){
      this.arrayListNuevoDetalles=arrayListNuevoDetalles;
   }
     public ArrayList<ANuevoDetallesR> totalFilasInsertadas(){
     return arrayListNuevoDetalles;
   }
     @Override
    public String getColumnName(int i) {
        switch (i) {
            case 0: return "id detalle OC";
            case 1:return "Id Producto";
            case 2: return "Cantidad Pedida";
            case 3:return "Cantidad Recibida";
            case 4:return "Lote";
            case 5:return "Fecha VTO";
            case 6: return "Fecha Envasado";
         default: return "[No]";
        }
    }
    
    @Override
    public int getRowCount() {
      return  arrayListNuevoDetalles.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int fila, int columna) {
       ANuevoDetallesR traer =  arrayListNuevoDetalles.get(fila);
       switch(columna) {
           case 0: return traer.getDetalleOC();
           case 1: return traer.getIdProducto();
           case 2: return traer.getCantidadPedida();
           case 3: return traer.getCantidadRecibida();
           case 4: return traer.getLote();
           case 5: return traer.getFechaVto();
           case 6: return traer.getFechaEnvasado();           
           default: return "[No]";
           
           
           
    }
    }
    
}
