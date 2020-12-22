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
public class ALoteProducto {

    private int idDetalleParteRecepcion,idProducto;
    private String idLote;
    private Date fechaVto,fechaEnvasado;

    public ALoteProducto() {
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

   
    public int getIdDetalleParteRecepcion() {
        return idDetalleParteRecepcion;
    }

    public void setIdDetalleParteRecepcion(int idDetalleParteRecepcion) {
        this.idDetalleParteRecepcion = idDetalleParteRecepcion;
    }


    public String getIdLote() {
        return idLote;
    }

    public void setIdLote(String idLote) {
        this.idLote = idLote;
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
    
    
    
}
