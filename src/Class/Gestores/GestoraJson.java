package Class.Gestores;

import Class.Pedidos.Pedido;
import Class.Personas.Cliente;
import Class.Personas.Empleado;
import Class.Productos.Producto;
import Class.Proveedores.Proveedor;
import Interface.IJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.HashSet;

public class GestoraJson extends GestorGenerico{
    //metodo FromJSON
    public static <T> HashSet fromJson(JSONArray arrJson){
        HashSet<T> lista = new HashSet<>();
        try{
            String clase = arrJson.get(0).getClass().toString();
            for(int i=0;i<arrJson.length();i++){
                switch(clase) {
                    case "Class.Pedidos.Pedido":
                        Pedido p = new Pedido();
                        p.fromJson(arrJson.getJSONObject(i));
                        lista.add((T) p);
                        break;
                    case "Class.Personas.Cliente":
                        Cliente c = new Cliente();
                        c.fromJson(arrJson.getJSONObject(i));
                        lista.add((T) c);
                        break;
                    case "Class.Personas.Empleado":
                        Empleado e = new Empleado();
                        e.fromJson(arrJson.getJSONObject(i));
                        lista.add((T) e);
                        break;
                    case "Class.Proveedores.Proveedor":
                        Proveedor pr = new Proveedor();
                        pr.fromJson(arrJson.getJSONObject(i));
                        lista.add((T) pr);
                        break;
                    case "Class.Productos.Producto":
                        Producto pro = new Producto();
                        pro.fromJson(arrJson.getJSONObject(i));
                        lista.add((T) pro);
                        break;
                    default:
                        System.out.println("Ocurrió un error");
                        break;
                }
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
        return lista;
    }
}
