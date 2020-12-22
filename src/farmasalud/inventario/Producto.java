/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.inventario;

/**
 *
 * @author Francisco Palacio
 */
public class Producto {
    String laboratorio,nombreComercial,nombreGenerico,concetracion,
            formaFarmaceutica,presentacion,accionTerapeutica,condicionReceta,obseravaciones;

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getNombreGenerico() {
        return nombreGenerico;
    }

    public void setNombreGenerico(String nombreGenerico) {
        this.nombreGenerico = nombreGenerico;
    }

    public String getConcetracion() {
        return concetracion;
    }

    public void setConcetracion(String concetracion) {
        this.concetracion = concetracion;
    }

    public String getFormaFarmaceutica() {
        return formaFarmaceutica;
    }

    public void setFormaFarmaceutica(String formaFarmaceutica) {
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

    public String getCondicionReceta() {
        return condicionReceta;
    }

    public void setCondicionReceta(String condicionReceta) {
        this.condicionReceta = condicionReceta;
    }

    public String getObseravaciones() {
        return obseravaciones;
    }

    public void setObseravaciones(String obseravaciones) {
        this.obseravaciones = obseravaciones;
    }
    
}
