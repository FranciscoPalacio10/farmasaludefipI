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
public class AInvetario {
    private String sucursal,lote;
    private int cantidad, idProducto;
    private Date fechaVto;

    public AInvetario() {
    }

    public String getSucursal() {
       return sucursal.substring(0,8).toUpperCase().concat(" ").concat(sucursal.substring(8));
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
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

    public Date getFechaVto() {
        return fechaVto;
    }

    public void setFechaVto(Date fechaVto) {
        this.fechaVto = fechaVto;
    }
    
    
}
