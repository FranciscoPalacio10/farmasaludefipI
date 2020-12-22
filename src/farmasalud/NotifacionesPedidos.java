/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud;

/**
 *
 * @author Francisco Palacio
 */
import ds.desktop.notify.DesktopNotify;
import farmasalud.operaciones.OCabeceraPedidos;
import farmasalud.operaciones.OManager;
import farmasalud.atributosclases.ACabeceraPedidos;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
 import static java.util.concurrent.TimeUnit.*;
import javax.swing.JTable;
import static javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS;
import javax.swing.table.DefaultTableModel;
 
public class NotifacionesPedidos {
   private final ScheduledExecutorService scheduler =  Executors.newScheduledThreadPool(1);
   private final OManager manager = new OManager();
   private  DefaultTableModel modelo;
   public void notificarPedidos(JTable tabla) {      
   ArrayList<ACabeceraPedidos> arrayListPedidos = manager.getCabeceraPedidos().obtenerUltimosPedidos("AU");
    llenaTablaNotifiaciones(tabla,arrayListPedidos);
     final Runnable consultarBase = () -> {      
        verificarCantidad("AU",tabla);
    };
     final ScheduledFuture<?> beeperHandle =
       scheduler.scheduleAtFixedRate(consultarBase, 1, 1, MINUTES);
         scheduler.schedule(() -> {
          beeperHandle.cancel(true);
    }, 60 * 60, MINUTES);
    }
  
 public void verificarCantidad(String estado,JTable tableNotiPedidos){
      ArrayList<ACabeceraPedidos> arrayListPedidos = manager.getCabeceraPedidos().obtenerUltimosPedidos(estado);
      int i=arrayListPedidos.size();
      int j= modelo.getRowCount();
      System.out.println(i);
      System.out.println(j);
       if(i>j){
          llenaTablaNotifiaciones(tableNotiPedidos,arrayListPedidos); 
           DesktopNotify.showDesktopMessage("FARMASALUD","HAY UN NUEVO PEDIDO",
           DesktopNotify.SUCCESS,15500);
       }else if(i<j){
             llenaTablaNotifiaciones(tableNotiPedidos,arrayListPedidos); 
           System.out.println("Se elimino un pedido");
       }else{
            System.out.println("No se actualizo..."); 
       }
   }
    
    public void llenaTablaNotifiaciones(JTable tableNotiPedidos,ArrayList<ACabeceraPedidos> pedidoss){
        String[] titulos={"Numero pedido","Fecha","Estado"};
     modelo=new DefaultTableModel(null, titulos){
                   @Override
            public boolean isCellEditable(int i, int i1) {
                return false; 
            }
        };
     Object[] nuevo = new Object[6];
    int j;
        pedidoss.forEach((ACabeceraPedidos traer)->{
                         nuevo[0]=traer.getNumeroPedido();
                         nuevo[1]=traer.getFechaPedido();
                         nuevo[2]=traer.getEstado();
                          modelo.addRow(nuevo);
                     });
     tableNotiPedidos.getTableHeader().setReorderingAllowed(false);
     tableNotiPedidos.setModel(modelo);
     tableNotiPedidos.setAutoResizeMode(AUTO_RESIZE_ALL_COLUMNS);
  
    }
   
 }