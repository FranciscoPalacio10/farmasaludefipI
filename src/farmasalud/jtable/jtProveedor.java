/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.jtable;

import farmasalud.atributosclases.ACondicionAfip;
import farmasalud.atributosclases.APais;
import farmasalud.atributosclases.AProveedor;
import farmasalud.atributosclases.AProvincia;
import farmasalud.atributosclases.ATipoProveedor;
import farmasalud.operaciones.OManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Francisco Palacio
 */
public class jtProveedor extends AbstractTableModel {
    private OManager manager;
    private List<AProveedor> arrayListProveedor = new ArrayList();
    private DefaultListModel lista;
    private String tipoProveedor,condicionAfip,provincia,pais;
    public jtProveedor(OManager manager,DefaultListModel lista) {
        this.manager = manager;
        this.lista=lista;
    }

    public void update(){
      arrayListProveedor=manager.getProveedor().obtenerTodos();
    
    }
    

    
    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    @Override
    public String getColumnName(int columna) {
     
     switch(columna){
         case 0: return "Id" ;
         case 1: return "Nombre";
         case 2: return "Tipo de proveedor";
         case 3: return "Email";
         case 4: return "Cuit";
         case 5: return "Condicion Afip";
         case 6: return "Telefono";
         case 7: return "Provincia";
         case 8: return "Pais";
         case 9: return "Calle";
         default:
             return "no";    
    }
             
    }
   
    public void removeRow(int fila){
        arrayListProveedor.remove(fila);
          this.fireTableStructureChanged();
           }
    
    
    @Override
    public int getRowCount() {
       return arrayListProveedor.size();
    }

    @Override
    public int getColumnCount() {
       return 10;
    }

    @Override
    public Object getValueAt(int fila, int columna) {
     AProveedor traer= arrayListProveedor.get(fila);
     manager.getTipoProveedor().obtenerUno(traer.getIdTipoProveedor()).forEach((ATipoProveedor tipo)->{
         tipoProveedor=tipo.getDescripcion();
     });
     manager.getCondicionAfip().obtenerUno(traer.getCondicionesAfip()).forEach((ACondicionAfip acd)->{
         condicionAfip= acd.getDescripcion();
     });
     manager.getProvincia().obtenerUno(traer.getIdProvincia()).forEach((AProvincia apr)->{
         provincia=apr.getDescripcion();
         manager.getPais().obtenerUno(apr.getIdPais()).forEach((APais ap)->{
           pais=  ap.getDescripcion();
         });
     });
      if(lista.contains(traer.getNombre())){
      this.removeRow(fila);
      }               else{
              switch(columna){
         case 0: return traer.getIdProveedor();
         case 1: return traer.getNombre();
         case 2: return tipoProveedor;
         case 3: return traer.getMail();
         case 4: return traer.getCuit();
         case 5: return condicionAfip;
         case 6: return traer.getTelefono();
         case 7: return provincia;
         case 8: return pais;
         case 9: return traer.getCalle();
         default:
             return "no";
   
            }
      }
        return null;
    }

    @Override
    public void fireTableRowsDeleted(int i, int i1) {
        super.fireTableRowsDeleted(i, i1);
    }
    
    
    
}
