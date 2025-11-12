package Class.Productos;
import Class.Proveedores.Proveedor;
import Interface.IJson;
import Enum.ETipoProducto;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Producto implements IJson {
    //Atributos.
    private int id;
    private String nombre;
    private String upc; //codigo del producto.
    private String marca;
    private Double precio;
    private Proveedor proveedor;
    private String descripcion;
    private String categoria;
    private ETipoProducto tipoProducto;
    private static int idGeneral = 0;
    //Método constructor.
    public Producto(String nombre, String upc, String marca, Double precio, Proveedor proveedor, String descripcion, String categoria, ETipoProducto tipoProducto) {
        idGeneral++;
        this.id = idGeneral;
        this.nombre = nombre;
        this.upc = upc;
        this.marca = marca;
        this.precio = precio;
        this.proveedor = proveedor;
        this.descripcion = descripcion;
        this.categoria = categoria.toLowerCase();
        this.tipoProducto = tipoProducto;
    }
    public Producto() {
        idGeneral++;
        this.id = idGeneral;
        this.nombre = "";
        this.upc = "";
        this.marca = "";
        this.precio = 0.0;
        this.proveedor = null;
        this.descripcion = "";
        this.categoria = "";
        this.tipoProducto = ETipoProducto.VACIO;
    }

    //Getters y Setters.
    public int getId() {return id;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getUpc() {return upc;}
    public void setUpc(String upc) {this.upc = upc;}
    public String getMarca() {return marca;}
    public void setMarca(String marca) {this.marca = marca;}
    public Double getPrecio() {return precio;}
    public void setPrecio(Double precio) {this.precio = precio;}
    public Proveedor getProveedor() {return proveedor;}
    public void setProveedor(Proveedor proveedor) {this.proveedor = proveedor;}
    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
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
            json.put("id", id);
            json.put("nombre",nombre);
            json.put("upc",upc);
            json.put("marca",marca);
            json.put("precio",precio);
            json.put("proveedor",proveedor);
            json.put("descripcion",descripcion);
            json.put("categoria",categoria);
            json.put("tipoProducto",tipoProducto.name());
            json.put("clase",this.getClass().getSimpleName());
        }catch(JSONException e){
            e.printStackTrace();
        }
        return json;
    }
        //toProducto.
    @Override public void fromJson(JSONObject objetoJSON) {
        try{
            id = objetoJSON.getInt("id");
            nombre = objetoJSON.getString("nombre");
            upc = objetoJSON.getString("upc");
            marca = objetoJSON.getString("marca");
            precio = objetoJSON.getDouble("precio");
            proveedor.fromJson(objetoJSON.getJSONObject("proveedor"));
            descripcion = objetoJSON.getString("descripcion");
            categoria = objetoJSON.getString("categoria");
            tipoProducto = ETipoProducto.valueOf(objetoJSON.getString("tipoProducto"));
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

    @Override public String toString() {return "Producto{ " + "ID: " + id + "nombre: '" + nombre + '\'' + ", upc: '" + upc + '\'' + ", marca: '" + marca + '\'' + ", precio: " + precio + ", proveedor: " + proveedor + ", descripción: '" + descripcion + '\'' + ", categoría: '" + categoria + '\'' + ", tipo de producto: " + tipoProducto + " }";}

}
