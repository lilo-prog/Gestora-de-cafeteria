package Class.Gestores;

import Class.Pedidos.Pedido;
import Class.Personas.Cliente;
import Class.Personas.Empleado;
import Class.Productos.Producto;
import Class.Proveedores.Proveedor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;

public class Cafeteria {
    public GestorGenerico<Empleado> listaEmpleados;
    public GestorGenerico<Cliente> listaClientes;
    public GestorGenerico<Producto> listaProductos;
    public GestorGenerico<Pedido> listaPedidos;
    public GestorGenerico<Proveedor> listaProveedores;
    public GestorString listaMarcas;
    public GestorString listaCategorias;
    //convertir a CAFETERIA a JSON
    public void toJson(){
        HashSet<Empleado> setEmpleados = listaEmpleados.exportarASet(listaEmpleados.getMap());
        GestoraJson.toJson(setEmpleados);
    }

    //convertir DE JSON a CAFETERIA
    public void fromJson(JSONArray cafeteriaJson) {
        try {
            for(int i = 0 ; i < cafeteriaJson.length(); i++) {
                JSONObject objetoJson = cafeteriaJson.getJSONObject(i);
                String clave = objetoJson.toString();
                switch(clave){
                    case "empleados":
                        listaEmpleadosFromJson(objetoJson.getJSONArray("empleados"));
                        break;
                    case "clientes":
                        listaClientesFromJson(objetoJson.getJSONArray("clientes"));
                        break;
                    case "proveedores":
                        listaProveedoresFromJson(objetoJson.getJSONArray("proveedores"));
                        break;
                    case "productos":
                        listaProductosFromJson(objetoJson.getJSONArray("productos"));
                        break;
                    case "pedidos":
                        listaPedidosFromJson(objetoJson.getJSONArray("pedidos"));
                        break;
                    case "marcas":
                        listaMarcasFromJson(objetoJson.getJSONArray("marcas"));
                        break;
                    case "categorias":
                        listaCategoriasFromJson(objetoJson.getJSONArray("categorias"));
                        break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //convertir JSON a LISTAS
    private void listaEmpleadosFromJson(JSONArray listaJson){
        HashSet<Empleado> lista = GestoraJson.fromJson(listaJson);
        for(Empleado e : lista){
            listaEmpleados.agregar(e.getId(),e);
        }
    }

    private void listaClientesFromJson(JSONArray listaJson){
        HashSet<Cliente> lista = GestoraJson.fromJson(listaJson);
        for(Cliente e : lista){
            listaClientes.agregar(e.getId(),e);
        }
    }

    private void listaProductosFromJson(JSONArray listaJson){
        HashSet<Producto> lista = GestoraJson.fromJson(listaJson);
        for(Producto e : lista){
            listaProductos.agregar(e.getId(),e);
        }
    }

    private void listaPedidosFromJson(JSONArray listaJson){
        HashSet<Pedido> lista = GestoraJson.fromJson(listaJson);
        for(Pedido e : lista){
            listaPedidos.agregar(e.getId(),e);
        }
    }

    private void listaProveedoresFromJson(JSONArray listaJson){
        HashSet<Proveedor> lista = GestoraJson.fromJson(listaJson);
        for(Proveedor e : lista){
            listaProveedores.agregar(e.getId(),e);
        }
    }

    private void listaMarcasFromJson(JSONArray listaJson){
        HashSet<String> lista = GestoraJson.fromJson(listaJson);
        for(String e : lista){
            listaMarcas.agregar(e);
        }
    }

    private void listaCategoriasFromJson(JSONArray listaJson){
        HashSet<String> lista = GestoraJson.fromJson(listaJson);
        for(String e : lista){
            listaCategorias.agregar(e);
        }
    }


}
