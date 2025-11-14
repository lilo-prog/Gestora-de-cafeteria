package Controllers;
import Models.Pedidos.Pedido;
import Models.Personas.Cliente;
import Models.Personas.Empleado;
import Models.Productos.Producto;
import Models.Proveedores.Proveedor;
import Exceptions.ElementoRepetidoException;
import org.json.JSONArray;
import java.util.HashSet;

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
        this.listaCategorias = new GestorString("categorias");
    }

    //convertir a CAFETERIA a JSON
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

    //convertir DE JSON a CAFETERIA
//    public void fromJson(JSONArray cafeteriaJson) throws ElementoRepetidoException {
//        try {
//            for(int i = 0 ; i < cafeteriaJson.length(); i++) {
//                JSONObject objetoJson = cafeteriaJson.getJSONObject(i);
//                String clave = objetoJson.toString();
//                switch(clave){
//                    case "class Class.Personas.Empleado":
//                        listaEmpleadosFromJson(objetoJson.getJSONArray("class Class.Personas.Empleado"));
//                        break;
//                    case "class Class.Personas.Cliente":
//                        listaClientesFromJson(objetoJson.getJSONArray("class Class.Personas.Cliente"));
//                        break;
//                    case "class Class.Proveedores.Proveedor":
//                        listaProveedoresFromJson(objetoJson.getJSONArray("class Class.Proveedores.Proveedor"));
//                        break;
//                    case "class Class.Productos.Producto":
//                        listaProductosFromJson(objetoJson.getJSONArray("class Class.Productos.Producto"));
//                        break;
//                    case "class Class.Pedidos.Pedido":
//                        listaPedidosFromJson(objetoJson.getJSONArray("class Class.Pedidos.Pedido"));
//                        break;
//                    case "marcas":
//                        listaMarcasFromJson(objetoJson.getJSONArray("marcas"));
//                        break;
//                    case "categorias":
//                        listaCategoriasFromJson(objetoJson.getJSONArray("categorias"));
//                        break;
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

    //convertir JSON a LISTAS
    public void listaEmpleadosFromJson(JSONArray listaJson) throws ElementoRepetidoException {
        HashSet<Empleado> lista = GestoraJson.fromJson(listaJson); //pasamos el archivo json a un hashset
        for(Empleado e : lista){ //se recorre el hashset para pasarlo a map y guardarlo en cafeteria.
            listaEmpleados.agregar((long)e.getId(),e);
        }
    }

    public void listaClientesFromJson(JSONArray listaJson) throws ElementoRepetidoException {
        HashSet<Cliente> lista = GestoraJson.fromJson(listaJson);
        for(Cliente e : lista){
            listaClientes.agregar((long)e.getId(),e);
        }
    }

    public void listaProductosFromJson(JSONArray listaJson) throws ElementoRepetidoException {
        HashSet<Producto> lista = GestoraJson.fromJson(listaJson);
        for(Producto e : lista){
            listaProductos.agregar((long)e.getId(),e);
        }
    }

    public void listaPedidosFromJson(JSONArray listaJson) throws ElementoRepetidoException {
        HashSet<Pedido> lista = GestoraJson.fromJson(listaJson);
        for(Pedido e : lista){
            listaPedidos.agregar((long)e.getId(),e);
        }
    }

    public void listaProveedoresFromJson(JSONArray listaJson) throws ElementoRepetidoException {
        HashSet<Proveedor> lista = GestoraJson.fromJson(listaJson);
        for(Proveedor e : lista){
            listaProveedores.agregar((long)e.getId(),e);
        }
    }

    public void listaMarcasFromJson(JSONArray listaJson) throws ElementoRepetidoException {
        listaMarcas.fromJson(listaJson);
    }

    public void listaCategoriasFromJson(JSONArray listaJson) throws ElementoRepetidoException {
        listaCategorias.fromJson(listaJson);
    }

}
