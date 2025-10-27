package Class.Pedidos;
import Class.Productos.Producto;
import Class.Proveedores.Proveedor;
import Interface.IJson;
import Enum.ETipoPago;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Pedido implements IJson {
    //Atributos.
    private int id;
    private HashMap<String, Integer> listaProductos;
    private Double total;
    private LocalDateTime fecha;
    private ETipoPago tipoPago;

    //MétoodO constructor.
    public Pedido(int id, ETipoPago tipoPago) {
        this.id = id;
        this.listaProductos = new HashMap<>();
        this.total = 0.0;
        this.fecha = LocalDateTime.now();
        this.tipoPago = ETipoPago.VACIO;
    }
    public Pedido() {
        this.id = 0;
        this.listaProductos = new HashMap<>();
        this.total = 0.0;
        this.fecha = LocalDateTime.now();
        this.tipoPago = ETipoPago.VACIO;
    }

    //Getters y Setters.
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public Double getTotal() {return total;}
    public void setTotal(Double total) {this.total = total;}
    public LocalDateTime getFecha() {return fecha;}
    public void setFecha(LocalDateTime fecha) {this.fecha = fecha;}
    public ETipoPago getTipoPago() {return tipoPago;}
    public void setTipoPago(ETipoPago tipoPago) {this.tipoPago = tipoPago;}

    //Métodos propios.
    //Métodos JSON.
        //toJSON.
    @Override
    public JSONObject toJson() {
        return null;
    }

        //toPedido.
    @Override
    public void fromJson(JSONObject objetoJSON) {

    }

    //Métodos HashMap lista de productos.
    public Boolean agregar(Producto producto, int cantidad){
        if(producto != null && !listaProductos.containsKey(producto.getNombre())) {
            listaProductos.put(producto.getNombre(), cantidad);
            return true;
        }
        return false;
    }
}
