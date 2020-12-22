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
public class AVademecum {
    int idVademecum,formaFarmaceutica,laboratorio,condcionReceta,nombreComercial,nombreGenerico,concentracion,
            cantidadPresentacion;
    String certificado,presentacion, accionTerapeutica,idMedida;

    public int getIdVademecum() {
        return idVademecum;
    }

    public int getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(int nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public int getNombreGenerico() {
        return nombreGenerico;
    }

    public void setNombreGenerico(int nombreGenerico) {
        this.nombreGenerico = nombreGenerico;
    }

    public int getConcentracion() {
        return concentracion;
    }

    public void setConcentracion(int concentracion) {
        this.concentracion = concentracion;
    }

    public int getCantidadPresentacion() {
        return cantidadPresentacion;
    }

    public void setCantidadPresentacion(int cantidadPresentacion) {
        this.cantidadPresentacion = cantidadPresentacion;
    }

    public String getIdMedida() {
        return idMedida;
    }

    public void setIdMedida(String idMedida) {
        this.idMedida = idMedida;
    }

    public void setIdVademecum(int idVademecum) {
        this.idVademecum = idVademecum;
    }

    public int getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(int laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getCertificado() {
        return certificado;
    }

    public void setCertificado(String certificado) {
        this.certificado = certificado;
    }


    

    public int getFormaFarmaceutica() {
        return formaFarmaceutica;
    }

    public void setFormaFarmaceutica(int formaFarmaceutica) {
        this.formaFarmaceutica = formaFarmaceutica;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getAccionTerapeutica() {
        return accionTerapeutica;
    }

    public void setAccionTerapeutica(String accionTerapeutica) {
        this.accionTerapeutica = accionTerapeutica;
    }

    public int getCondcionReceta() {
        return condcionReceta;
    }

    public void setCondcionReceta(int condcionReceta) {
        this.condcionReceta = condcionReceta;
    }

    public AVademecum() {
    }
    
    
    
}
