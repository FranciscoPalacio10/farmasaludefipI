/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.jtable;

import farmasalud.atributosclases.ACabeceraPedidos;
import farmasalud.atributosclases.AUsuario;
import farmasalud.operaciones.OManager;
import farmasalud.pedidosSucursales.pintarCeldas;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Francisco Palacio
 */
public class jtNuevaCotizacion {
    private ArrayList<ACabeceraPedidos> arrayListPedidos;
    private JTable tabla;
   private OManager manager;
   private  String usuario,sucursal;
   
    public jtNuevaCotizacion(ArrayList<ACabeceraPedidos> arrayListPedidos, JTable tabla) {
        this.arrayListPedidos = arrayListPedidos;
        this.tabla = tabla;
    }
    
    
    
  public void llenarTablas() {
        String[] titulos = {"Numero Pedido", "Sucursal", "Lugar De Entrega", "Fecha", "Estado", "Usuario","Seleccionar"};
        Object[] nuevo = new Object[6];
        DefaultTableModel modelo = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int i, int i1) { 
                return i1==6;         
            }
  
            @Override
             public Class getColumnClass(int columnIndex) {
        if(columnIndex==6){
            return Boolean.class;
        }else{
            return Object.class;
        }
                 }      
        };
         manager=new OManager();
       
        arrayListPedidos.forEach((ACabeceraPedidos pedidoss) -> {
              manager.getUsuario().obtenerUsuarioXId(pedidoss.getId_usuario()).forEach((AUsuario us)->{
                      sucursal= us.getIdSucursal();
                     usuario= us.getUsuario();
              });
            nuevo[0] = pedidoss.getNumeroPedido();
            nuevo[1] = sucursal;
            nuevo[2] = pedidoss.getLugarDeEntrega();
            nuevo[3] = pedidoss.getFechaPedido();
            nuevo[4] = pedidoss.getEstado();
            nuevo[5] = usuario;
            modelo.addRow(nuevo);
        });
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setModel(modelo);
        pintarCeldas pintar = new pintarCeldas("Pedidos");
        tabla.getColumn("Estado").setCellRenderer(pintar);
    }
   
  public int getSize(){
      return arrayListPedidos.size();
  }
  
 public ArrayList<String> obtenerFilasSeleccionadas(){
     ArrayList<String> seleccionado =new ArrayList<>();
     for(int s=0;getSize()>s;s++){
         if(tabla.getValueAt(s, 6)!=null){
             if(Boolean.parseBoolean(tabla.getValueAt(s, 6).toString())!=false){
              seleccionado.add(tabla.getValueAt(s, 0).toString());
         }        
         }  
     }
        return seleccionado;
     
 }
  
  
}
