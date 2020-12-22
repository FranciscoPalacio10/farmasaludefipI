package farmasalud;

import farmasalud.atributosclases.AEstados;
import farmasalud.conectarBD.ConexionB;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Francisco Palacio
 */
public class ConsultasGenerales{
    private static ConsultasGenerales obtenerInstancia;
   private int row,colum;
   private   boolean seleccionado = false;
   private ConsultasGenerales() {
    }
    
    public static ConsultasGenerales obtenerInstacia(){
        if(obtenerInstancia==null){
            obtenerInstancia=new ConsultasGenerales();
        }
        return obtenerInstancia;
        
    }

   public int seleccionItemTabla(JTable tabla,MouseEvent evt,JPopupMenu menuPop){ 
          tabla.getTableHeader().setReorderingAllowed(false);
        if (evt.isPopupTrigger()) {
           row = tabla.rowAtPoint(evt.getPoint());
           colum = tabla.columnAtPoint(evt.getPoint());
            if (!tabla.isRowSelected(row)) {
                tabla.changeSelection(row, colum, false, false);
            }
            menuPop.show(evt.getComponent(), evt.getX(), evt.getY());
             tabla.setPreferredScrollableViewportSize(tabla.getPreferredSize());
        }
    return row;
    }
    
     public void habilitarBotonAbrir(JTable tabla,JButton bt) {
         tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if(!tabla.getSelectionModel().isSelectionEmpty()){
                     bt.setEnabled(true);
                }else{
                     bt.setEnabled(false);
                }
            }
        });
    }
  
     
   public void evitarIngresoCaracteres(char caracter, KeyEvent evt) {
        if ((caracter < '0' || (caracter > '9'))) {
            evt.consume();
        }
    }
}
