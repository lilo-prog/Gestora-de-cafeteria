package Models.Productos;
import Models.Proveedores.Proveedor;
import Interface.IJson;
import Enum.ETipoProducto;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Producto implements IJson {
    //Atributos.
    private int id;
    private static int idGeneral = 0;
    private String nombre;
    private long upc; //codigo del producto (tiene 12 digitos).
    private String marca;
    private Double precio;
    private Proveedor proveedor;
    private String categoria;
    private ETipoProducto tipoProducto;

    //Método constructor.
    public Producto(String nombre, long upc, String marca, Double precio, Proveedor proveedor, String categoria, ETipoProducto tipoProducto) {
        idGeneral++;
        this.id = idGeneral;
        this.nombre = nombre;
        this.upc = upc;
        this.marca = marca;
        this.precio = precio;
        this.proveedor = proveedor;
        this.categoria = categoria.toLowerCase();
        this.tipoProducto = tipoProducto;
    }
    public Producto() {
        idGeneral++;
        this.id = idGeneral;
        this.nombre = "";
        this.upc = 0;
        this.marca = "";
        this.precio = 0.0;
        this.proveedor = null;
        this.categoria = "";
        this.tipoProducto = ETipoProducto.VACIO;
    }

    //Getters y Setters.
    public int getId() {return id;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public long getUpc() {return upc;}
    public void setUpc(long upc) {this.upc = upc;}
    public String getMarca() {return marca;}
    public void setMarca(String marca) {this.marca = marca;}
    public Double getPrecio() {return precio;}
    public void setPrecio(Double precio) {this.precio = precio;}
    public Proveedor getProveedor() {return proveedor;}
    public void setProveedor(Proveedor proveedor) {this.proveedor = proveedor;}
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
            json.put("id_producto", id);
            json.put("nombre",nombre);
            json.put("upc",upc);
            json.put("marca",marca);
            json.put("precio",precio);
            if (proveedor != null) {
                json.put("proveedor", proveedor.toJson());
            }
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
            id = objetoJSON.getInt("id_producto");
            nombre = objetoJSON.getString("nombre");
            upc = objetoJSON.getLong("upc");
            marca = objetoJSON.getString("marca");
            precio = objetoJSON.getDouble("precio");
            if (objetoJSON.has("proveedor") && !objetoJSON.isNull("proveedor")) {
                Proveedor proveedor = new Proveedor();
                JSONObject proveedorJSON = objetoJSON.getJSONObject("proveedor");
                proveedor.fromJson(proveedorJSON);
                this.proveedor = proveedor;
            } else {
                this.proveedor = null;
            }
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

    @Override public String toString() {return "Producto{ " + "ID producto: " + id + ", nombre: '" + nombre + '\'' + ", UPC: '" + upc + '\'' + ", marca: '" + marca + '\'' + ", precio: " + precio  + ", categoría: '" + categoria + '\'' + ", tipo de producto: " + tipoProducto + "\n" + proveedor + " }";}

}
