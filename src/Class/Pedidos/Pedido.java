package Class.Pedidos;
import Class.Exceptions.EElementoNoEncontrado;
import Class.Exceptions.EElementoRepetido;
import Class.Productos.Producto;

import Interface.IJson;
import Enum.ETipoPago;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Pedido implements IJson {
    //Atributos.
    private int id;
    private HashMap<Producto, Integer> listaProductos;
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
        JSONObject objetoJSON = new JSONObject();
        JSONArray arregloJSON = new JSONArray();
        try{
            objetoJSON.put("idPedido",id);
            for(Map.Entry<Producto, Integer> lista : listaProductos.entrySet()){
                JSONObject ob = new JSONObject();
                ob.put("producto",lista.getKey().toJson());
                ob.put("cantidad_producto",lista.getValue());
                arregloJSON.put(ob);
            }
            objetoJSON.put("lista_productos", arregloJSON);
            objetoJSON.put("total", total);
            objetoJSON.put("fecha",fecha.toString());
            objetoJSON.put("tipo_de_pago", tipoPago.name());
        }catch(JSONException e){
            e.printStackTrace();
        }
        return objetoJSON;
    }

        //toPedido.
    @Override
    public void fromJson(JSONObject objetoJSON) {

        try{
            id = objetoJSON.getInt("id");
            JSONArray arregloJSON = objetoJSON.getJSONArray("lista_productos");
            for(int i = 0; i<arregloJSON.length(); i++){
                JSONObject elementoActual = arregloJSON.getJSONObject(i);
                Producto producto = new Producto();
                producto.fromJson(elementoActual);
                listaProductos.put(producto, elementoActual.getInt("cantidad_producto"));
            }
            total = objetoJSON.getDouble("total");
            fecha = LocalDateTime.parse(objetoJSON.getString("fecha"));
            tipoPago = ETipoPago.valueOf(objetoJSON.getString("tipoPago"));
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    //Métodos HashMap lista de productos.
        //Método agregar producto a lista de productos dentro del pedido.
    public Boolean agregar(Producto producto, int cantidad) throws NullPointerException, EElementoRepetido {
        if(producto != null) throw new NullPointerException();
        if(listaProductos.containsKey(producto.getNombre())) throw new EElementoRepetido();
        listaProductos.put(producto, cantidad);
        total = calcularTotal();
        return true;
    }
        //Método eliminar producto a lista de productos dentro del pedido.
    public Boolean eliminar(String nombreProducto){
        if(listaProductos.containsKey(nombreProducto)){
            listaProductos.remove(nombreProducto);
            total = calcularTotal();
            return true;
        }
        return false;
    }

        //Método buscar producto a lista de productos dentro del pedido.
    public Boolean buscar(String nombre) throws EElementoNoEncontrado {
        if(!listaProductos.containsKey(nombre)) throw new EElementoNoEncontrado();
        return true;
    }
    public Producto buscarNombre(Producto producto) throws EElementoNoEncontrado {
        if(listaProductos.containsKey(producto.getNombre())) return producto;
        return null;
    }
    public double calcularTotal(){
        for(Map.Entry<Producto, Integer> lista : listaProductos.entrySet()){
            Producto producto = lista.getKey();
            int cantidad = lista.getValue();
            total+=producto.getPrecio()*cantidad;
        }
        return total;
    }
}
