/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author Francisco Palacio
 */
public interface IManager {

    ICabeceraCotizacion getCabeceraCotizacion();

    ICabeceraPedidos getCabeceraPedidos();

    IObtenerEstados getEstados();

    IDetallePedidos getDetallePedidos();

    IDetalleCotizaciones getDetalleCotizaciones();

    IPermisos getPermisos();

    IRol getRol();

    IPermisosXRol getPermisosXRol();

    IUsuario getUsuario();

    IDetalleDetalleCotizacion getDetalleDetalle();

    IProducto getProductos();

    INombreComercial getNombreComercial();

    INombreGenerico getNombreGenerico();

    IFormaFarmaceutica getFormaFarmaceutica();

    IProveedor getProveedor();

    ICondicionReceta getCondicionReceta();

    ISucursales getSucursales();

    IVademecum getVademecum();

    INoFarmaceutico getNoFarmaceutico();

    ITipoProveedor getTipoProveedor();

    IDetalleConsolidado getDetallesConsolidados();

    ICotizacionXProveedor getCotizacionXProveedor();

    ICondicionAfip getCondicionAfip();

    IProvincia getProvincia();

    IPais getPais();

    IParteDeRecepcion getParteDeRecepcion();

    IDetalleParteDeRecepcion getDetalleParteDeRecepcion();

    ILoteProducto getLoteProducto();

    IDetalleLote getDetalleLote();
    
    ICabeceraEnvioInterno getCabeceraEnvioInterno();
    
    IDetalleEnvioInterno getDetalleEnvioInterno();
    
    ICabeceraOrdenDeCompra getCabeceraOrdenDeCompra();
    
    IDetalleOrdenDeCompra getDetalleOrdenDeComrpa();
    
    ICabeceraOferta getCabeceraOferta();
    
    IDetalleOferta getDetalleOferta();
    
    
    
}
