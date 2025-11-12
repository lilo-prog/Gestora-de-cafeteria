package Class.Gestores;
import Class.Pedidos.Pedido;
import Class.Personas.Cliente;
import Class.Personas.Empleado;
import Class.Productos.Producto;
import Class.Proveedores.Proveedor;
import Interface.IJson;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.HashSet;

public class GestoraJson{
    //metodo FromJSON
    public static <T extends IJson> HashSet fromJson(JSONArray arrJson){
        HashSet<T> lista = new HashSet<>();
        try{
            String clase = arrJson.get(0).getClass().toString();
            for(int i=0;i<arrJson.length();i++){
                switch(clase) {
                    case " class Class.Pedidos.Pedido":
                        Pedido p = new Pedido();
                        p.fromJson(arrJson.getJSONObject(i));
                        lista.add((T) p);
                        break;
                    case "class Class.Personas.Cliente":
                        Cliente c = new Cliente();
                        c.fromJson(arrJson.getJSONObject(i));
                        lista.add((T) c);
                        break;
                    case "class Class.Personas.Empleado":
                        Empleado e = new Empleado();
                        e.fromJson(arrJson.getJSONObject(i));
                        lista.add((T) e);
                        break;
                    case "class Class.Proveedores.Proveedor":
                        Proveedor pr = new Proveedor();
                        pr.fromJson(arrJson.getJSONObject(i));
                        lista.add((T) pr);
                        break;
                    case "class Class.Productos.Producto":
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

    public static <T extends IJson> void toJson(HashSet<T> elementos){
        if(!elementos.isEmpty()) {
            String nombreArchivo = "";
            JSONArray arregloJSON = new JSONArray();
            for(T t: elementos) {
                nombreArchivo = t.getClass().toString();
                arregloJSON.put(t.toJson());
            }
            JsonUtiles.grabarUnJson(arregloJSON,nombreArchivo + ".json");
        }
    }
    public static <T extends GestorString> void toJsonString(T elementos){
        if(!elementos.getLista().isEmpty()) {
            String nombreArchivo = "";
            nombreArchivo = elementos.getNombre();
            JSONArray arregloJSON = elementos.toJson();

            JsonUtiles.grabarUnJson(arregloJSON,nombreArchivo + ".json");
        }
    }
}

