/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.atributosclases;

import java.sql.Date;

/**
 *
 * @author Francisco Palacio
 */
public class ANuevoDetallesR {
    private int idParteRecepcion,detalleOC,idProducto,cantidadPedida,cantidadRecibida;
    private Date fechaVto,fechaEnvasado;
    private double precio;
    private String Lote;

    public ANuevoDetallesR() {
    }

    
    
    public int getIdParteRecepcion() {
        return idParteRecepcion;
    }

    public void setIdParteRecepcion(int idParteRecepcion) {
        this.idParteRecepcion = idParteRecepcion;
    }

    public int getDetalleOC() {
        return detalleOC;
    }

    public void setDetalleOC(int detalleOC) {
        this.detalleOC = detalleOC;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidadPedida() {
        return cantidadPedida;
    }

    public void setCantidadPedida(int cantidadPedida) {
        this.cantidadPedida = cantidadPedida;
    }

    public int getCantidadRecibida() {
        return cantidadRecibida;
    }

    public void setCantidadRecibida(int cantidadRecibida) {
        this.cantidadRecibida = cantidadRecibida;
    }

  

    public Date getFechaVto() {
        return fechaVto;
    }

    public void setFechaVto(Date fechaVto) {
        this.fechaVto = fechaVto;
    }

    public Date getFechaEnvasado() {
        return fechaEnvasado;
    }

    public void setFechaEnvasado(Date fechaEnvasado) {
        this.fechaEnvasado = fechaEnvasado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


   

    public String getLote() {
        return Lote;
    }

    public void setLote(String Lote) {
        this.Lote = Lote;
    }
    
}
