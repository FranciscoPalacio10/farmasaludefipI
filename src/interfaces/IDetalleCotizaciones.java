/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;
import farmasalud.atributosclases.ADetalleCotizacion;
import farmasalud.atributosclases.ADetalleDetalleDeCotizacion;

/**
 *
 * @author Francisco Palacio
 */
public interface IDetalleCotizaciones extends IOperacionesGenerales<ADetalleCotizacion,Integer> {
    ArrayList<ADetalleCotizacion> devolverIDproductoConsolidado();
    ArrayList<Integer> obtenerIdProductos(int idDetalleCotizacion);
    int obtenerIdDetalleCotizacion(int nroProducto);
}
