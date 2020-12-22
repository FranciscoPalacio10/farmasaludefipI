/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.jtable;

import farmasalud.atributosclases.ACabeceraPedidos;
import farmasalud.atributosclases.AUsuario;
import farmasalud.operaciones.OCabeceraPedidos;
import farmasalud.operaciones.OManager;
import java.awt.Component;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Francisco Palacio
 */
public class jtCabeceraPedidos extends AbstractTableModel {
    private  String usuario,sucursal;
    private OManager manager;
    private List<ACabeceraPedidos> actualizar = new ArrayList<>();
   private boolean generarCotizacion;
   private String seleccionado;
   private ArrayList<Boolean> checkBoxes = new ArrayList<>();
    public jtCabeceraPedidos(OManager manager) {
        this.manager = manager;
    }
   
    @Override
    public String getColumnName(int i) {
        switch (i) {
            case 0: return "Numero Pedido";
            case 1:return "Sucursal";
            case 2:return "Lugar De Entrega";
            case 3:return "Fecha";
            case 4: return "Estado";
            case 5:return "Usuario";
            case 6:return "Elegir";
        default: return "[No]";
        }
    }

    public void updateModel() {
        actualizar = manager.getCabeceraPedidos().obtenerTodos();
    }

    public void updateModelPorEstado(String estado) {
        actualizar = manager.getCabeceraPedidos().obtenerPedidosXEstado(estado);
        System.out.println(actualizar);
    }
    
    
    public void updateModelGenerarCotizacion(String estado) {
        actualizar = manager.getCabeceraPedidos().obtenerPedidosXEstado(estado);
        generarCotizacion=true;
       
    }

    @Override
    public int getRowCount() {
        return actualizar.size();
    }

    @Override
    public int getColumnCount() {
        if(generarCotizacion){
            return 7;
        }else{
            return 6;
        }
              
   
    }
 
    @Override
    public Object getValueAt(int fila, int columna) {
     ACabeceraPedidos numero=actualizar.get(fila);
  
    manager.getUsuario().obtenerUsuarioXId(numero.getId_usuario()).forEach((AUsuario us)->{
                      sucursal= us.getIdSucursal();
                     usuario= us.getUsuario();
              });
     switch(columna){
            case 0:return numero.getNumeroPedido();
            case 1: return sucursal;
            case 2: return numero.getLugarDeEntrega();           
            case 3:return numero.getFechaPedido();
            case 4: return numero.getEstado();
            case 5: return usuario;
           default: return seleccionado;
     }  
    }

   
    
    
    @Override
    public boolean isCellEditable(int i, int i1) {
        return false; //To change body of generated methods, choose Tools | Templates.
    }



}
