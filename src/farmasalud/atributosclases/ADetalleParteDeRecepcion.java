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
public class ADetalleParteDeRecepcion {
    private int idDetalleParteRecepcion,iDPartederecepcion,cantidad,detalleOrdenDeCompra,idProducto,idUsuarioVerificacion;
    private int cantidadRecibida,cantidadAceptada,cantidadRechazada;
    private int cantidadDisponible;
    private String valor;

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }
       public ADetalleParteDeRecepcion() {
    }

    public int getIdDetalleParteRecepcion() {
        return idDetalleParteRecepcion;
    }

    public void setIdDetalleParteRecepcion(int idDetalleParteRecepcion) {
        this.idDetalleParteRecepcion = idDetalleParteRecepcion;
    }

    public int getiDPartederecepcion() {
        return iDPartederecepcion;
    }

    public void setiDPartederecepcion(int iDPartederecepcion) {
        this.iDPartederecepcion = iDPartederecepcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdUsuarioVerificacion() {
        return idUsuarioVerificacion;
    }

    public void setIdUsuarioVerificacion(int idUsuarioVerificacion) {
        this.idUsuarioVerificacion = idUsuarioVerificacion;
    }


    public int getCantidadRecibida() {
        return cantidadRecibida;
    }

    public void setCantidadRecibida(int cantidadRecibida) {
        this.cantidadRecibida = cantidadRecibida;
    }

    public int getCantidadAceptada() {
        return cantidadAceptada;
    }

    public void setDetalleOrdenDeCompra(int detalleOrdenDeCompra) {
        this.detalleOrdenDeCompra = detalleOrdenDeCompra;
    }

    public void setCantidadAceptada(int cantidadAceptada) {
        this.cantidadAceptada = cantidadAceptada;
    }

    public int getCantidadRechazada() {
        return cantidadRechazada;
    }

    public void setCantidadRechazada(int cantidadRechazada) {
        this.cantidadRechazada = cantidadRechazada;
    }


    public int getDetalleOrdenDeCompra() {
        return detalleOrdenDeCompra;
    }

   

   


    
}
