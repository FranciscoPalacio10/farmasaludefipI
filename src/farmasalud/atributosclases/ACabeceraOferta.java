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
public class ACabeceraOferta {
    private int idOferta,	idProveedor,idCotizacion,	idFormadepago,	mantenimientoOferta,	plazoDeEntrega;
    private Date fecha;
    private String estado;

    public ACabeceraOferta() {
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

    public int getPlazoDeEntrega() {
        return plazoDeEntrega;
    }

    public void setPlazoDeEntrega(int plazoDeEntrega) {
        this.plazoDeEntrega = plazoDeEntrega;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
    
}
