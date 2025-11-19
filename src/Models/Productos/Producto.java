package Models.Productos;
import Models.Proveedores.Proveedor;
import Interface.IJson;
import Enum.ETipoProducto;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Producto implements IJson {
    //Atributos.
    private String nombre;
    private String upc; //codigo del producto (tiene 12 digitos).
    private String marca;
    private Double precio;
    private String idProveedor;
    private String categoria;
    private ETipoProducto tipoProducto;

    //Método constructor.
    public Producto(String nombre, String upc, String marca, Double precio, String idProveedor, String categoria, ETipoProducto tipoProducto) {
        this.nombre = nombre;
        this.upc = upc;
        this.marca = marca;
        this.precio = precio;
        this.idProveedor = idProveedor;
        this.categoria = categoria.toLowerCase();
        this.tipoProducto = tipoProducto;
    }
    public Producto() {
        this.nombre = "";
        this.upc = "";
        this.marca = "";
        this.precio = 0.0;
        this.idProveedor = "";
        this.categoria = "";
        this.tipoProducto = ETipoProducto.VACIO;
    }

    //Getters y Setters.
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getUpc() {return upc;}
    public void setUpc(String upc) {this.upc = upc;}
    public String getMarca() {return marca;}
    public void setMarca(String marca) {this.marca = marca;}
    public Double getPrecio() {return precio;}
    public void setPrecio(Double precio) {this.precio = precio;}
    public String getIdProveedor() {return idProveedor;}
    public void setIdProveedor(String idProveedor) {this.idProveedor = idProveedor;}
    public String getCategoria() {return categoria;}
    public void setCategoria(String categoria) {this.categoria = categoria.toLowerCase();}
    public ETipoProducto getTipoProducto() {return tipoProducto;}
    public void setTipoProducto(ETipoProducto tipoProducto) {this.tipoProducto = tipoProducto;}

    //Métodos propios.

    //Métodos JSON.
        //toJson.
    @Override public JSONObject toJson() {
        JSONObject json = new JSONObject();
        try{
            json.put("nombre",nombre);
            json.put("upc",upc);
            json.put("marca",marca);
            json.put("precio",precio);
            json.put("id_proveedor", idProveedor);
            json.put("categoria",categoria);
            json.put("tipo_producto",tipoProducto.name());
            json.put("clase",this.getClass().getSimpleName());
        }catch(JSONException e){
            e.printStackTrace();
        }
        return json;
    }
        //toProducto.
    @Override public void fromJson(JSONObject objetoJSON) {
        try{
            nombre = objetoJSON.getString("nombre");
            upc = objetoJSON.getString("upc");
            marca = objetoJSON.getString("marca");
            precio = objetoJSON.getDouble("precio");
            idProveedor = objetoJSON.getString("id_proveedor");
            categoria = objetoJSON.getString("categoria");
            tipoProducto = ETipoProducto.valueOf(objetoJSON.getString("tipo_producto"));
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    //Override.
    @Override public boolean equals(Object o) {
        if (!(o instanceof Producto producto)) return false;
        return Objects.equals(upc, producto.upc);
    }
    @Override public int hashCode() {return Objects.hashCode(upc);}

    @Override public String toString() {return "Producto{ " + "nombre: '" + nombre + '\'' + ", UPC: '" + upc + '\'' + ", marca: '" + marca + '\'' + ", precio: " + precio  + ", categoría: '" + categoria + '\'' + ", tipo de producto: " + tipoProducto + ", id del proveedor: " + idProveedor + " }";}

}
