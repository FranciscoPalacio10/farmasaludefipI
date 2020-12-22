/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;
import farmasalud.atributosclases.ADetalleCotizacion;
import farmasalud.atributosclases.ADetallePedidos;
import farmasalud.atributosclases.ACabeceraPedidos;

/**
 *
 * @author Francisco Palacio
 */
public interface IDetallePedidos extends IOperacionesGenerales<ADetallePedidos,Integer> {
    ArrayList<ADetallePedidos> traerDetallesProductos(int nroPedido,String procedimiento);
    ArrayList<ADetallePedidos> obtenerNombreComercialDeteallesProducto(String texto);
    ArrayList<ADetallePedidos> buscarEnDetallesProductos(String texto,String procedimiento);
    ArrayList<ADetallePedidos> procedimientoVerDetalleDeConsolidado(int nroProducto);
    ArrayList<ADetallePedidos> obtenerDetallesPorCabecera(int nroCabecera);
}
