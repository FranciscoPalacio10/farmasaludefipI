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
public class ACabeceraOrdenDeCompra  {
    private int	idOrdenDeCompra, condicionesPago,idUsuario,idOferta,idProveedor,plazoDeEntrega;
    private String estado;
   private Date fechafinalizacion,fechaInicio;

    public ACabeceraOrdenDeCompra() {
    }

    public int getIdOrdenDeCompra() {
        return idOrdenDeCompra;
    }

    public void setIdOrdenDeCompra(int idOrdenDeCompra) {
        this.idOrdenDeCompra = idOrdenDeCompra;
    }

    public int getCondicionesPago() {
        return condicionesPago;
    }

    public void setCondicionesPago(int condicionesPago) {
        this.condicionesPago = condicionesPago;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public int getPlazoDeEntrega() {
        return plazoDeEntrega;
    }

    public void setPlazoDeEntrega(int plazoDeEntrega) {
        this.plazoDeEntrega = plazoDeEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechafinalizacion() {
        return fechafinalizacion;
    }

    public void setFechafinalizacion(Date fechafinalizacion) {
        this.fechafinalizacion = fechafinalizacion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
    
    
    
}
