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
public class nroOrdenCompra {
    
    String ordenCompra= "Pedido";
    private int inicio=0;
    private int obtenerOrdenActual;
    private String obtenerSiguienteOrden;
    private int nroSiguiente;
    
    public void nroOrdenCompra(int ordenAcutal){
        this.obtenerOrdenActual=ordenAcutal;       
    }
    
    public String siguienteOrden(){
        if(obtenerOrdenActual==inicio){
            obtenerSiguienteOrden=ordenCompra.concat("0");
                   }else{
            nroSiguiente=obtenerOrdenActual+1;
            obtenerSiguienteOrden=ordenCompra.concat(String.valueOf(nroSiguiente));
        }
        return obtenerSiguienteOrden;
    }
    
    
    
}
