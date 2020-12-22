/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.atributosclases;

/**
 *
 * @author Francisco Palacio
 */
public class ADetalleLote {
    private int idDetalleLote,stock,idDetallePedido,cantidadVendida;
    private String estado,idLote;

    public ADetalleLote() {
    }

    public int getIdDetalleLote() {
        return idDetalleLote;
    }

    public void setIdDetalleLote(int idDetalleLote) {
        this.idDetalleLote = idDetalleLote;
    }

    public String getIdLote() {
        return idLote;
    }

    public void setIdLote(String idLote) {
        this.idLote = idLote;
    }

 

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getIdDetallePedido() {
        return idDetallePedido;
    }

    public void setIdDetallePedido(int idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

  
    
    
    
    
    
    
}
