package Models.Pedidos;
import Exceptions.ElementoNoEncontradoException;
import Exceptions.ElementoRepetidoException;
import Models.Productos.Producto;
import Interface.IJson;
import Enum.ETipoPago;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Pedido implements IJson {
    //Atributos.
    private int id;
    private static int idGeneral = 0;
    private HashMap<Producto, Integer> listaProductos;
    private Double total;
    private Double totalConDescuento;
    private LocalDateTime fecha;
    private ETipoPago tipoPago;
    private String dniCliente;
    public static Double descuentoAAplicar = 0.0;
    public static Double gastoMinimo = 0.0;

    //Método constructor.
    public Pedido(ETipoPago tipoPago, String dniCliente) {
        idGeneral++;
        this.id = idGeneral;
        this.listaProductos = new HashMap<>();
        this.fecha = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        this.tipoPago = tipoPago;
        this.dniCliente = dniCliente;
    }
    public Pedido() {
        idGeneral++;
        this.id = idGeneral;
        this.listaProductos = new HashMap<>();
        this.fecha = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);;
        this.tipoPago = ETipoPago.VACIO;
        this.dniCliente = "";
    }

    //Getters y Setters.
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public Double getTotal() {return total;}
    public String getDniCliente(){return dniCliente;}
    public void setDniCliente(String dniCliente) {this.dniCliente = dniCliente;}
    public void setTotal(Double total) {this.total = total;}
    public LocalDateTime getFecha() {return fecha;}
    public void setFecha(LocalDateTime fecha) {this.fecha = fecha;}
    public ETipoPago getTipoPago() {return tipoPago;}
    public void setTipoPago(ETipoPago tipoPago) {this.tipoPago = tipoPago;}
    public static Double getDescuentoAAplicar() {return descuentoAAplicar;}
    public static void setDescuentoAAplicar(Double descuentoAAplicar) {Pedido.descuentoAAplicar = descuentoAAplicar;}
    public static Double getGastoMinimo() {return gastoMinimo;}
    public static void setGastoMinimo(Double gastoMinimo) {Pedido.gastoMinimo = gastoMinimo;}
    public Double getTotalConDescuento() {return totalConDescuento;}
    public void setTotalConDescuento(Double totalConDescuento) {this.totalConDescuento = totalConDescuento;}

    //Métodos propios.
    //Métodos JSON.
        //toJSON.
    @Override
    public JSONObject toJson() {
        JSONObject objetoJSON = new JSONObject();
        JSONArray arregloJSON = new JSONArray();
        try{
            objetoJSON.put("id_pedido", id);
            for(Map.Entry<Producto, Integer> lista : listaProductos.entrySet()){
                JSONObject ob = new JSONObject();
                ob.put("producto", lista.getKey().toJson());
                ob.put("cantidad_producto", lista.getValue());
                arregloJSON.put(ob);
            }
            objetoJSON.put("lista_productos", arregloJSON);
            objetoJSON.put("total", total);
            objetoJSON.put("total_con_descuento", totalConDescuento);
            objetoJSON.put("fecha", fecha.toString());
            objetoJSON.put("tipo_de_pago", tipoPago.name());
            objetoJSON.put("dni_cliente", dniCliente);
            objetoJSON.put("descuento_aplicado", descuentoAAplicar);
            objetoJSON.put("gasto_minimo", gastoMinimo);
            objetoJSON.put("clase",this.getClass().getSimpleName());
        }catch(JSONException e){
            e.printStackTrace();
        }
        return objetoJSON;
    }

        //toPedido.
    @Override
    public void fromJson(JSONObject objetoJSON) {
        try{
            id = objetoJSON.getInt("id_pedido");
            JSONArray arregloJSON = objetoJSON.getJSONArray("lista_productos");
            for(int i = 0; i < arregloJSON.length(); i++){
                JSONObject elementoActual = arregloJSON.getJSONObject(i);
                JSONObject productoJSON = elementoActual.getJSONObject("producto");
                Producto producto = new Producto();
                producto.fromJson(productoJSON);
                int cantidad = elementoActual.getInt("cantidad_producto");
                listaProductos.put(producto, cantidad);
            }
            total = objetoJSON.getDouble("total");
            totalConDescuento = objetoJSON.getDouble("total_con_descuento");
            fecha = LocalDateTime.parse(objetoJSON.getString("fecha"));
            tipoPago = ETipoPago.valueOf(objetoJSON.getString("tipo_de_pago"));
            dniCliente = objetoJSON.getString("dni_cliente");
            descuentoAAplicar = objetoJSON.getDouble("descuento_aplicado");
            gastoMinimo = objetoJSON.getDouble("gasto_minimo");
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    //Métodos HashMap lista de productos.
        //Método agregar producto a lista de productos dentro del pedido.
    public Boolean agregar(Producto producto, int cantidad) throws NullPointerException, ElementoRepetidoException {
        if(producto == null) throw new NullPointerException();
        if(listaProductos.containsKey(producto.getNombre())) throw new ElementoRepetidoException();
        listaProductos.put(producto, cantidad);
        return true;
    }
        //Método eliminar producto a lista de productos dentro del pedido.
    public Boolean eliminar(String nombreProducto){
        if(listaProductos.containsKey(nombreProducto)){
            listaProductos.remove(nombreProducto);
            return true;
        }
        return false;
    }

        //Método buscar producto a lista de productos dentro del pedido.
    public Boolean existe(String nombre) throws ElementoNoEncontradoException {
        if(!listaProductos.containsKey(nombre)) throw new ElementoNoEncontradoException();
        return true;
    }

    public Double calcularTotal(){
        total = 0.0;
        for(Map.Entry<Producto, Integer> lista : listaProductos.entrySet()){
            Producto producto = lista.getKey();
            int cantidad = lista.getValue();
            total += producto.getPrecio() * cantidad;
        }
        return total;
    }
        //Método motrar lista de productos.
    public String mostrarListaDeProductos(){
        StringBuilder sb = new StringBuilder("- Lista de productos del pedido -\n");
        for(Map.Entry<Producto, Integer> producto_clave : listaProductos.entrySet()){
            Producto producto = producto_clave.getKey();
            sb.append(producto).append(", cantidad: " + producto_clave.getValue()).append("\n");
        }
        return sb.toString();
    }

    //Override.
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pedido pedido)) return false;
        return id == pedido.id && Objects.equals(listaProductos, pedido.listaProductos) && Objects.equals(total, pedido.total) && Objects.equals(fecha, pedido.fecha) && tipoPago == pedido.tipoPago;
    }
    @Override public int hashCode() {return Objects.hash(id, listaProductos, total, fecha, tipoPago);}

    @Override public String toString() {return "Pedido{ " + "ID pedido: " + id + ", fecha: " + fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ", DNI cliente: "+ dniCliente + ", tipo de pago: " + tipoPago + ", total: " + total + ", descuento a aplicar: " + descuentoAAplicar + ", total con el descuento aplicado: " + totalConDescuento +"\n" + mostrarListaDeProductos() + " }";}
}
