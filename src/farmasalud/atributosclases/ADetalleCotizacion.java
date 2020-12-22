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
public class ADetalleCotizacion extends ACabeceraCotizacion {
    private int idDetalleCotizacion,idCotizacion,cantidad,idProducto;
  


    public int getIdDetalleCotizacion() {
        return idDetalleCotizacion;
    }

    public void setIdDetalleCotizacion(int idDetalleCotizacion) {
        this.idDetalleCotizacion = idDetalleCotizacion;
    }

    @Override
    public int getIdCotizacion() {
        return idCotizacion;
    }

    @Override
    public void setIdCotizacion(int idCotizacion) {
        this.idCotizacion = idCotizacion;
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

  
    
}
