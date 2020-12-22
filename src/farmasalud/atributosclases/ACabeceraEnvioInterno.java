/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.atributosclases;



import java.sql.Date;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Palacio
 */
public class ACabeceraEnvioInterno {
    private int idEnvioInterno,	numeroPedido;
    private String estado;
            private Date fechaenvio;

    public int getIdEnvioInterno() {
        return idEnvioInterno;
    }

    public void setIdEnvioInterno(int idEnvioInterno) {
        this.idEnvioInterno = idEnvioInterno;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaenvio() {
        return fechaenvio;
    }

    public void setFechaenvio(Date fechaenvio) {
        this.fechaenvio = fechaenvio;
    }

    public ACabeceraEnvioInterno() {
    }
    
    
    
    
}
