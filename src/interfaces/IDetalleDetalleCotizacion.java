/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import farmasalud.atributosclases.ADetalleCotizacion;
import farmasalud.atributosclases.ADetalleDetalleDeCotizacion;
import java.util.ArrayList;

/**
 *
 * @author Francisco Palacio
 */
public interface IDetalleDetalleCotizacion extends IOperacionesGenerales<ADetalleDetalleDeCotizacion,Integer> {
        ArrayList<Integer> devolverIDproductoConsolidado();
    
    
}
