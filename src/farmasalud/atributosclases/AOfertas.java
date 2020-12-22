/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.atributosclases;

import java.util.Date;

/**
 *
 * @author Francisco Palacio
 */
public class AOfertas {
    private int idOferta,idProveedor,idCotizacion,idFormadepago,mantenimientoOferta;
    private int plazodeentrega;
    private String 	estado;
    private Date fecha;
            private double 	descuento;

    public AOfertas() {
    }

    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(int idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public int getIdFormadepago() {
        return idFormadepago;
    }

    public void setIdFormadepago(int idFormadepago) {
        this.idFormadepago = idFormadepago;
    }

    public int getMantenimientoOferta() {
        return mantenimientoOferta;
    }

    public void setMantenimientoOferta(int mantenimientoOferta) {
        this.mantenimientoOferta = mantenimientoOferta;
    }

    public int getPlazodeentrega() {
        return plazodeentrega;
    }

    public void setPlazodeentrega(int plazodeentrega) {
        this.plazodeentrega = plazodeentrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
    
    
    
}
