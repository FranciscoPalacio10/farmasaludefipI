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
public class AParteDeRecepcion {
    private int idParteRecepcion,numeroOc,numeroRemito,idProveedor,idUsuarioRecepcion;
    private Date 	fechaRecepcion;
    private String 	observaciones,estado;

    public AParteDeRecepcion() {
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdParteRecepcion() {
        return idParteRecepcion;
    }

    public void setIdParteRecepcion(int idParteRecepcion) {
        this.idParteRecepcion = idParteRecepcion;
    }

    public int getNumeroOc() {
        return numeroOc;
    }

    public void setNumeroOc(int numeroOc) {
        this.numeroOc = numeroOc;
    }

    public int getNumeroRemito() {
        return numeroRemito;
    }

    public void setNumeroRemito(int numeroRemito) {
        this.numeroRemito = numeroRemito;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getIdUsuarioRecepcion() {
        return idUsuarioRecepcion;
    }

    public void setIdUsuarioRecepcion(int idUsuarioRecepcion) {
        this.idUsuarioRecepcion = idUsuarioRecepcion;
    }
    
}
