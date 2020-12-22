/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import farmasalud.atributosclases.ADetalleLote;
import farmasalud.atributosclases.AInvetario;
import farmasalud.atributosclases.ALoteProducto;
import java.util.List;

/**
 *
 * @author Francisco Palacio
 */
public interface IDetalleLote extends IOperacionesGenerales<ADetalleLote,Integer> {
    List<ADetalleLote> obtenerIDetalePedido(int idDetalle);
    List<AInvetario> procedimientoObtenerStock(String lote);
     List<AInvetario> procedimientoObtenerStockXProducto(int idProducto);
}
