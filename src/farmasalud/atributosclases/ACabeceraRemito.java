/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.atributosclases;

import java.util.Date;

/**
 *
 * @author Francisco Palacio
 */
public class ACabeceraRemito {
    private String idRemito,idSucursal;
    private Date fecha; 
    private int numeroOC;

    public ACabeceraRemito() {
    }

    public String getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(String idRemito) {
        this.idRemito = idRemito;
    }

    public String getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(String idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNumeroOC() {
        return numeroOC;
    }

    public void setNumeroOC(int numeroOC) {
        this.numeroOC = numeroOC;
    }
    
}
