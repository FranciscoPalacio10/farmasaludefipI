/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import farmasalud.atributosclases.ACabeceraOrdenDeCompra;

/**
 *
 * @author Francisco Palacio
 */
public interface ICabeceraOrdenDeCompra extends IOperacionesGenerales<ACabeceraOrdenDeCompra,Integer> {
   void actulizarEestado(int idCabecera,String estado);
}
