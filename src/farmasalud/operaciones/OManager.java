/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;

import farmasalud.conectarBD.ConexionBD;
import interfaces.ICabeceraCotizacion;
import interfaces.ICabeceraEnvioInterno;
import interfaces.ICabeceraOferta;
import interfaces.ICabeceraOrdenDeCompra;
import interfaces.ICabeceraPedidos;
import interfaces.ICondicionAfip;
import interfaces.ICondicionReceta;
import interfaces.ICotizacionXProveedor;
import interfaces.IDetalleConsolidado;
import interfaces.IDetalleCotizaciones;
import interfaces.IDetalleDetalleCotizacion;
import interfaces.IDetalleEnvioInterno;
import interfaces.IDetalleLote;
import interfaces.IDetalleOferta;
import interfaces.IDetalleOrdenDeCompra;
import interfaces.IDetalleParteDeRecepcion;
import interfaces.IDetallePedidos;
import interfaces.IFormaFarmaceutica;
import interfaces.ILoteProducto;
import interfaces.IManager;
import interfaces.INoFarmaceutico;
import interfaces.INombreComercial;
import interfaces.INombreGenerico;
import interfaces.IObtenerEstados;
import interfaces.IPais;
import interfaces.IParteDeRecepcion;
import interfaces.IPermisos;
import interfaces.IPermisosXRol;
import interfaces.IProducto;
import interfaces.IProveedor;
import interfaces.IProvincia;
import interfaces.IRol;
import interfaces.ISucursales;
import interfaces.ITipoProveedor;
import interfaces.IUsuario;
import interfaces.IVademecum;
import java.sql.Connection;

/**
 *
 * @author Francisco Palacio
 */
public class OManager implements IManager{
       private final Connection conexion;
       private static OCabeceraCotizacion cabeceraCotizacion =null;
       private OCabeceraPedidos cabeceraPedidos=null;
       private  static OEstadosXProceso estadosxProceso=null;
       private static ODetallesPedidos detallesPedidos=null;
       private static ODetallesCotizaciones detallesCotizaciones=null;
       private static OPermisos permisos=null;
       private static OPermisosXRol permisosXRol=null;
       private static OUsuario usuarioActivo=null;
       private static ORol rol=null;
       private static ODetalleDetalleDeCotizacion detalleDetalleCotizacion=null;
       private static OProductos obtenerProductos=null;
       private static ONombreComercial obtenerNombreComercial=null;
       private static ONombreGenerico obtenerNombreGenerico=null;
       private static OVademecum obtenerVademecum=null;
       private static ONoFarmaceutico obtenerNoFarmaceutico=null;
       private static OCondicionReceta obtenerCondicionReceta=null;
       private static OProveedor obtenerProveedor=null;
       private static OSucursales obtenerSucursales=null;
       private static OFormaFarmaceutica obtenerFormaFarmaceutica=null;
       private static OTipoProveedor obtenerTipoProveedor=null;
       private static  ODetallesConsolidados obtenerDetalleConsolidado=null;
       private static OCotizacionXProveedor obtenerCotizacionXProveedor=null;
       private static OCondicionAfip obtenerCondicionesAfip=null;
       private static OProvincia obtenerProvincia=null;
       private static OPais obtenerPais=null;
       private static OParteDeRecepcion obtenerParteDeRecepcion=null;
       private static ODetalleParteDeRecepcion obtenerDetalleParteDeRecepcion=null;
       private static OLoteProducto obtenerLoteProducto=null;
       private static ODetalleLote obtenerDetalleLote=null;
       private static OCabeceraEnvioInterno obtenerCabeceraEnvioInterno=null;
       private static ODetalleEnvioInterno obtenerDetalleEnvioInterno=null;    
       private static OCabeceraOrdenDeCompra obtenerCabeceraOrdenDeCompra=null;
       private   static ODetalleOrdenDeCompra obtenerDetalleOrdenDeCompra=null;
       private static OCabeceraOferta obtenerCabeceraOferta=null;
       private static ODetalleOferta obtenerDetalleOferta=null;
       private final ConexionBD instancia;
       
   
     public OManager() {  
       instancia = ConexionBD.obtenerIntancia();
       this.conexion=instancia.getConn(); 
      }

  
       @Override
  public OProductos getProductos() {
        if(obtenerProductos==null){
            obtenerProductos=new OProductos(conexion);
        }
        
        return obtenerProductos;
    }
       
  
    @Override
    public ICabeceraCotizacion getCabeceraCotizacion() {
       if(cabeceraCotizacion==null){
           cabeceraCotizacion=new  OCabeceraCotizacion (conexion);
            System.out.println("cabeceracotizacion");
       }
       return cabeceraCotizacion;
    }

    @Override
    public ICabeceraPedidos getCabeceraPedidos() {
        if(cabeceraPedidos==null){
            cabeceraPedidos=new OCabeceraPedidos(conexion);
        }
         return  cabeceraPedidos;
        
    }

    @Override
    public IObtenerEstados getEstados() {
         if(estadosxProceso==null){
            estadosxProceso=new OEstadosXProceso(conexion);
        }
         return estadosxProceso;
    }

    @Override
    public IDetallePedidos getDetallePedidos() {
      if(detallesPedidos==null){
            detallesPedidos=new ODetallesPedidos(conexion);
        }
         return detallesPedidos;
    }

    @Override
    public IDetalleCotizaciones getDetalleCotizaciones() {
      if(detallesCotizaciones==null){
            detallesCotizaciones=new ODetallesCotizaciones(conexion);
            System.out.println("detallecotizacion");
        }
         return detallesCotizaciones;
    }

    @Override
    public IPermisos getPermisos() {
       if(permisos==null){
            permisos=new OPermisos(conexion);
        }
         return permisos;
    }

    @Override
    public IRol getRol() {
      if(rol==null){
            rol=new ORol(conexion);
        }
         return rol;
    }

    @Override
    public IPermisosXRol getPermisosXRol() {
          if(permisosXRol==null){
            permisosXRol=new OPermisosXRol(conexion);
        }
         return permisosXRol; 
    }

    @Override
    public IUsuario getUsuario() {
        if(usuarioActivo==null){
          usuarioActivo=new OUsuario(conexion);
        }
         return usuarioActivo; 
    }

  @Override
    public IDetalleDetalleCotizacion getDetalleDetalle() {
    if(detalleDetalleCotizacion==null){
         detalleDetalleCotizacion= new ODetalleDetalleDeCotizacion(conexion);
      }
      return detalleDetalleCotizacion;
    }

    @Override
    public INombreComercial getNombreComercial() {
       if(obtenerNombreComercial==null){
         obtenerNombreComercial= new ONombreComercial(conexion);
      }
      return obtenerNombreComercial;
    }

    @Override
    public INombreGenerico getNombreGenerico() {
        if(obtenerNombreGenerico==null){
         obtenerNombreGenerico= new ONombreGenerico(conexion);
      }
      return obtenerNombreGenerico;
    }

    @Override
    public IFormaFarmaceutica getFormaFarmaceutica() {
    if(obtenerFormaFarmaceutica==null){
        obtenerFormaFarmaceutica= new OFormaFarmaceutica(conexion);
      }
      return obtenerFormaFarmaceutica;
    }

    @Override
    public IProveedor getProveedor() {
   if(obtenerProveedor==null){
         obtenerProveedor= new OProveedor(conexion);
      }
      return obtenerProveedor;
    }

    @Override
    public ICondicionReceta getCondicionReceta() {
       if(obtenerCondicionReceta==null){
         obtenerCondicionReceta= new OCondicionReceta(conexion);
      }
      return obtenerCondicionReceta;
    }

    @Override
    public ISucursales getSucursales() {
    if(obtenerSucursales==null){
         obtenerSucursales= new OSucursales(conexion);
      }
      return obtenerSucursales;
    }

    @Override
    public IVademecum getVademecum() {
       if(obtenerVademecum==null){
         obtenerVademecum= new OVademecum(conexion);
           System.out.println("1");
      }
      return obtenerVademecum;
    }

    @Override
    public INoFarmaceutico getNoFarmaceutico() {
      if(obtenerNoFarmaceutico==null){
         obtenerNoFarmaceutico= new ONoFarmaceutico(conexion);
      }
      return obtenerNoFarmaceutico;
    }

    @Override
    public ITipoProveedor getTipoProveedor() {
        if(obtenerTipoProveedor==null){
         obtenerTipoProveedor= new OTipoProveedor(conexion);
      } 
     return obtenerTipoProveedor;
    }

    @Override
    public IDetalleConsolidado getDetallesConsolidados() {
     if( obtenerDetalleConsolidado==null){
         obtenerDetalleConsolidado= new ODetallesConsolidados(conexion);
      } 
     return obtenerDetalleConsolidado;
    }

    @Override
    public ICotizacionXProveedor getCotizacionXProveedor() {
        if(obtenerCotizacionXProveedor==null){
        obtenerCotizacionXProveedor= new OCotizacionXProveedor(conexion);
      } 
     return obtenerCotizacionXProveedor;
    }

    @Override
    public ICondicionAfip getCondicionAfip() {
        if(obtenerCondicionesAfip==null){
        obtenerCondicionesAfip= new OCondicionAfip(conexion);
      } 
     return obtenerCondicionesAfip;
    }

    @Override
    public IProvincia getProvincia() {
     if(obtenerProvincia==null){
        obtenerProvincia= new OProvincia(conexion);
      } 
     return obtenerProvincia;
    }

    @Override
    public IPais getPais() {
      if(obtenerPais==null){
      obtenerPais= new OPais(conexion);
      } 
     return obtenerPais;
    }

    @Override
    public IParteDeRecepcion getParteDeRecepcion() {
         if(obtenerParteDeRecepcion==null){
      obtenerParteDeRecepcion= new OParteDeRecepcion(conexion);
      } 
     return obtenerParteDeRecepcion;
    }
    

    @Override
    public IDetalleParteDeRecepcion getDetalleParteDeRecepcion() {
       if(obtenerDetalleParteDeRecepcion==null){
      obtenerDetalleParteDeRecepcion= new ODetalleParteDeRecepcion(conexion);
      } 
     return obtenerDetalleParteDeRecepcion;
    }

    @Override
    public ILoteProducto getLoteProducto() {
       if(obtenerLoteProducto==null){
      obtenerLoteProducto= new OLoteProducto(conexion);
      } 
     return obtenerLoteProducto;
    }

    @Override
    public IDetalleLote getDetalleLote() {
        if(obtenerDetalleLote==null){
      obtenerDetalleLote= new ODetalleLote(conexion);
      } 
     return obtenerDetalleLote;
    }

    @Override
    public ICabeceraEnvioInterno getCabeceraEnvioInterno() {
         if(obtenerCabeceraEnvioInterno==null){
      obtenerCabeceraEnvioInterno= new OCabeceraEnvioInterno(conexion);
      } 
     return obtenerCabeceraEnvioInterno;
    }

    @Override
    public IDetalleEnvioInterno getDetalleEnvioInterno() {
       if(obtenerDetalleEnvioInterno==null){
      obtenerDetalleEnvioInterno= new ODetalleEnvioInterno(conexion);
      } 
     return obtenerDetalleEnvioInterno;
    }

    @Override
    public ICabeceraOrdenDeCompra getCabeceraOrdenDeCompra() {
      if( obtenerCabeceraOrdenDeCompra==null){
      obtenerCabeceraOrdenDeCompra= new OCabeceraOrdenDeCompra(conexion);
      } 
     return obtenerCabeceraOrdenDeCompra;
    }

    @Override
    public IDetalleOrdenDeCompra getDetalleOrdenDeComrpa() {
        if(obtenerDetalleOrdenDeCompra==null){
      obtenerDetalleOrdenDeCompra = new ODetalleOrdenDeCompra(conexion);
      } 
     return obtenerDetalleOrdenDeCompra;
    }

    @Override
    public ICabeceraOferta getCabeceraOferta() {
      if(obtenerCabeceraOferta==null){
      obtenerCabeceraOferta = new OCabeceraOferta(conexion);
      } 
     return obtenerCabeceraOferta;
    }

    @Override
    public IDetalleOferta getDetalleOferta() {
       if( obtenerDetalleOferta==null){
      obtenerDetalleOferta = new ODetalleOferta(conexion);
      } 
     return obtenerDetalleOferta;
    }
    
}
