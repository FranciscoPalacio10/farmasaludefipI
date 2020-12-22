/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import farmasalud.atributosclases.AParteDeRecepcion;

/**
 *
 * @author Francisco Palacio
 */
public interface IParteDeRecepcion extends IOperacionesGenerales<AParteDeRecepcion,Integer> {
    void acutalizarEstado(String estado, int idCabecera);
}
