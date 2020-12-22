/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import farmasalud.atributosclases.AProvincia;
import java.util.List;

/**
 *
 * @author Francisco Palacio
 */
public interface IProvincia extends IOperacionesGenerales<AProvincia,String> {
    
    List<AProvincia> obtenerPaisXProvincia(String idPais);
    String obtenerId(String descripcion);
    
}
