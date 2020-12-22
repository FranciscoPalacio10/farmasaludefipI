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
public class ASucursales {
    String idSucursal,domicilio;

    public String getIdSucursal() {
        return idSucursal.substring(0,8).toUpperCase().concat(" ").concat(idSucursal.substring(8));
    }

    public void setIdSucursal(String idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public ASucursales() {
    }
   
}

