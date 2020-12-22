package farmasalud;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import static java.util.Calendar.DATE;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Francisco Palacio
 */

    
   public class obtenerFecha {
    private String hora;

    private static obtenerFecha fecha;
    private Date date;
    private Calendar calendario ;
    private final String zonaHoraria= "GMT-3";
     private DateFormat formato;
    private DateTimeFormatter formatos;
    private obtenerFecha(){
     date = new Date();
     calendario = Calendar.getInstance();
         }

    public static obtenerFecha getFecha(){
        if(fecha==null){
            fecha=new obtenerFecha();
        }
        return fecha;
    }

    public String getFechaSistema(){
        formato = new SimpleDateFormat("yyyy-MM-dd");    
            return formato.format(date);
    }
    
    public Date getDate(){
        return date;
    }
   
    public Date addMonths(int numero){
        calendario.setTime(new Date());
        calendario.add(Calendar.MONTH, +numero);
        return calendario.getTime();
    }
             


 
    
     }
    
    

