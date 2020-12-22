/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.pedidosSucursales;

import farmasalud.operaciones.ODetallesCotizaciones;
import farmasalud.operaciones.OManager;
import farmasalud.atributosclases.ADetalleCotizacion;
import farmasalud.atributosclases.ADetalleDetalleDeCotizacion;
import farmasalud.obtenerFecha;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import static javafx.application.Platform.exit;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Francisco Palacio
 */
public class pintarCeldas extends DefaultTableCellRenderer {
   private int columna;
   private String proceso;
   private int nroPedido;
   private ODetallesCotizaciones obtener;
     OManager  traer = new OManager();
   private ArrayList<ADetalleDetalleDeCotizacion> arrayListDetalleDelDetalle;
   private    Font font= new Font("TAHOMA",Font.BOLD,16);
   private  DefaultTableCellRenderer  nuevo;
   private int contador=0;
   private obtenerFecha fechaActual;
   private SimpleDateFormat dateFormat;
   public pintarCeldas(String proceso)
{
   this.proceso= proceso;
} 
    
    
    @Override
public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column)
{        
   nuevo= (DefaultTableCellRenderer) super.getTableCellRendererComponent(table, value, selected, focused, row, column);  
      switch(proceso){
          case "Pedidos":
              colorEstadoPedido(table, row,column,selected,focused);   
              break;
          case "idConsolidado":       
              this.setForeground(Color.BLACK);          
                colorIdConsolidado(table,row,column,selected,focused);
                  break;
          case "fechaVTO":
               colorFechaVto(table,row,column,selected,focused);
              default:
              
                break;
      }
   
    return  nuevo;
}



    private void colorEstadoPedido(JTable table, int row, int column, boolean selected, boolean focused) {
        switch (table.getValueAt(row,column).toString()) {
            case "Anulado":
                this.setForeground(Color.RED);
                this.setFont(font);
                break;
            case "Borrador":
                this.setForeground(Color.BLUE);
                this.setFont(font);
                break;
            case "Comprado":
                this.setForeground(Color.GREEN);
                this.setFont(font);
                break;
            case "En Cotizacion":
                this.setForeground(Color.MAGENTA);
                this.setFont(font);
                break;
            case "Autorizado":
                this.setForeground(Color.ORANGE);
                this.setFont(font);
                break;
            default:
                this.setForeground(Color.BLACK);
                this.setFont(font);
                break;
        } }

      private void colorIdConsolidado(JTable table, int row, int column, boolean selected, boolean focused) {
         arrayListDetalleDelDetalle = traer.getDetalleDetalle().obtenerTodos();     
          System.out.println(arrayListDetalleDelDetalle);
           List<Integer> resultado=arrayListDetalleDelDetalle.stream().
            collect(Collectors.groupingBy(s -> s.getIdDetalleCotizacion())).entrySet()
            .stream().filter(e->e.getValue().size() > 1).map(e -> e.getKey()).collect(Collectors.toList());
           System.out.println(resultado);
           resultado.forEach((Integer a)->{  
                if(table.getValueAt(row,columna).equals(a)){
                      this.setForeground(Color.GREEN);
                      
              }
            });
           }         

    private void colorFechaVto(JTable table, int row, int column, boolean selected, boolean focused) {
       try {
           fechaActual=obtenerFecha.getFecha();
           dateFormat = new  SimpleDateFormat ("yyyy-MM-dd");
           Date date1 = (Date) dateFormat.parse(fechaActual.getFechaSistema());
           Date date2 = (Date) dateFormat.parse(table.getValueAt(row, column).toString());
     
           if(date2.before(date1)){
            setForeground(Color.RED);
             this.setFont(font);
               System.out.println("hola");
           }else if(fechaActual.addMonths(5).after(date2)){
              setForeground(Color.ORANGE);
               this.setFont(font);
               System.out.println("eee");
           }else{
               setForeground(Color.GREEN);
                this.setFont(font);
               System.out.println("aaa");
           }
           
       } catch (ParseException ex) {
           Logger.getLogger(pintarCeldas.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
         }  
      

 

      
            
        
