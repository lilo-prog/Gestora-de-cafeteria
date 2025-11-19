package Controllers;
import Exceptions.ElementoNoEncontradoException;
import Models.Pedidos.Pedido;
import Models.Personas.Cliente;
import Models.Personas.Empleado;
import Models.Productos.Producto;
import Models.Proveedores.Proveedor;
import Exceptions.ElementoRepetidoException;
import Enum.ETipoProducto;
import org.json.JSONArray;
import java.util.HashSet;
import java.util.Map;

public class Cafeteria {
    //Atributos.
    public GestorGenerico<Empleado> listaEmpleados;
    public GestorGenerico<Cliente> listaClientes;
    public GestorGenerico<Producto> listaProductos;
    public GestorGenerico<Pedido> listaPedidos;
    public GestorGenerico<Proveedor> listaProveedores;
    public GestorString listaMarcas;
    public GestorString listaCategorias;

    //Método constructor.
    public Cafeteria() {
        this.listaEmpleados = new GestorGenerico<>();
        this.listaClientes = new GestorGenerico<>();
        this.listaProductos = new GestorGenerico<>();
        this.listaPedidos = new GestorGenerico<>();
        this.listaProveedores = new GestorGenerico<>();
        this.listaMarcas = new GestorString("marcas");
        listaMarcas.agregar("sin marca",ETipoProducto.VACIO);
        this.listaCategorias = new GestorString("categorias");
    }

    public void calcularGastoTotalDeCliente(String dni) throws ElementoNoEncontradoException {
        listaClientes.buscarPorId(dni);
        Double total = 0.0;
        for(Map.Entry<String, Pedido> entry : listaPedidos.getMap().entrySet()){
            Pedido pedido = entry.getValue();
            if(pedido.getDniCliente().equals(dni)){
                total += pedido.getTotal();
            }
        }
        listaClientes.getMap().get(dni).setGastoTotal(total);
    }

    public void calcularDescuento(String dni, double gasto_total_minimo, double descuento_a_aplicar) throws ElementoNoEncontradoException{
        Cliente c = listaClientes.buscarPorId(dni);
        if(c.getGastoTotal() >= gasto_total_minimo){
            listaClientes.getMap().get(dni).setDescuento(descuento_a_aplicar);
        }else listaClientes.getMap().get(dni).setDescuento(0.0);
    }

    //Convertir a CAFETERIA a JSON.
    //Se llama al método de GestorGenerico.exportarASet pasandole por parámetro cada lista para que éste método lo pase de Map a HashSet
    //y así pasarlo a JSON.

    public void toJsonEmpleado(){
        HashSet<Empleado> setEmpleados = GestorGenerico.exportarASet(listaEmpleados.getMap());
        GestoraJson.toJson(setEmpleados);
    }
    public void toJsonClientes(){
        HashSet<Cliente> setClientes = GestorGenerico.exportarASet(listaClientes.getMap());
        GestoraJson.toJson(setClientes);
    }
    public void toJsonProductos(){
        HashSet<Producto> setProductos = GestorGenerico.exportarASet(listaProductos.getMap());
        GestoraJson.toJson(setProductos);
    }
    public void toJsonPedidos(){
        HashSet<Pedido> setPedidos = GestorGenerico.exportarASet(listaPedidos.getMap());
        GestoraJson.toJson(setPedidos);
    }
    public void toJsonProveedores(){
        HashSet<Proveedor> setProveedores = GestorGenerico.exportarASet(listaProveedores.getMap());
        GestoraJson.toJson(setProveedores);
    }
    public void toJsonMarcas(){
        GestoraJson.toJsonString(listaMarcas);
    }
    public void toJsonCategorias(){
        GestoraJson.toJsonString(listaCategorias);
    }


    //Convertir JSON a LISTAS.
    //Pasamos el archivo JSON a un HashSet. Luego se recorre el HashSet para pasarlo a Map y guardarlo en cafeteria.

    public void listaEmpleadosFromJson(JSONArray listaJson) throws ElementoRepetidoException {
        HashSet<Empleado> lista = GestoraJson.fromJson(listaJson);
        for(Empleado e : lista){
            listaEmpleados.agregar(e.getDni(),e);
        }
    }

    public void listaClientesFromJson(JSONArray listaJson) throws ElementoRepetidoException {
        HashSet<Cliente> lista = GestoraJson.fromJson(listaJson);
        for(Cliente e : lista){
            listaClientes.agregar(e.getDni(),e);
        }
    }

    public void listaProductosFromJson(JSONArray listaJson) throws ElementoRepetidoException {
        HashSet<Producto> lista = GestoraJson.fromJson(listaJson);
        for(Producto e : lista){
            listaProductos.agregar(e.getUpc(),e);
        }
    }

    public void listaPedidosFromJson(JSONArray listaJson) throws ElementoRepetidoException {
        HashSet<Pedido> lista = GestoraJson.fromJson(listaJson);
        for(Pedido e : lista){
            listaPedidos.agregar(String.valueOf(e.getId()),e);
        }
    }

    public void listaProveedoresFromJson(JSONArray listaJson) throws ElementoRepetidoException {
        HashSet<Proveedor> lista = GestoraJson.fromJson(listaJson);
        for(Proveedor e : lista){
            listaProveedores.agregar(e.getCuil(),e);
        }
    }

    public void listaMarcasFromJson(JSONArray listaJson) {
        listaMarcas.fromJson(listaJson);
    }

    public void listaCategoriasFromJson(JSONArray listaJson) {
        listaCategorias.fromJson(listaJson);
    }


}
