package Controllers;
import Models.Pedidos.Pedido;
import Models.Personas.Cliente;
import Models.Personas.Empleado;
import Models.Productos.Producto;
import Models.Proveedores.Proveedor;
import Interface.IJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;

public class GestoraJson{
    // Método FromJSON.
    // Se obtiene por parámetro un JSONArray que, dependiendo de la clase que se obtenga, se crea un objeto del mismo tipo
    // y se agrega al HashSet, para después retornarlo.

    public static <T extends IJson> HashSet fromJson(JSONArray arrJson){
        HashSet<T> lista = new HashSet<>();
        try{
            JSONObject obj = arrJson.getJSONObject(0);
            String clase = obj.getString("clase");
            for(int i=0;i<arrJson.length();i++){
                switch(clase) {
                    case "Pedido":
                        Pedido p = new Pedido();
                        p.fromJson(arrJson.getJSONObject(i));
                        lista.add((T) p);
                        break;
                    case "Cliente":
                        Cliente c = new Cliente();
                        c.fromJson(arrJson.getJSONObject(i));
                        lista.add((T) c);
                        break;
                    case "Empleado":
                        Empleado e = new Empleado();
                        e.fromJson(arrJson.getJSONObject(i));
                        lista.add((T) e);
                        break;
                    case "Proveedor":
                        Proveedor pr = new Proveedor();
                        pr.fromJson(arrJson.getJSONObject(i));
                        lista.add((T) pr);
                        break;
                    case "Producto":
                        Producto pro = new Producto();
                        pro.fromJson(arrJson.getJSONObject(i));
                        lista.add((T) pro);
                        break;
                    default:
                        System.out.println("- Ocurrió un error");
                        break;
                }
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
        return lista;
    }

    // Método ToJSON.
    // Se obtiene por parámetro un HasSet que, si tiene elementos, se recorre, se obtiene la clase para guardarlo como nombre del archivo
    // y se pasa a JSON.

    public static <T extends IJson> void toJson(HashSet<T> elementos){
        if(!elementos.isEmpty()) {
            String nombreArchivo = "";
            JSONArray arregloJSON = new JSONArray();
            for(T t: elementos) {
                nombreArchivo = t.getClass().getSimpleName(); //con getSimpleName agarro e nombre de la clase y lo pongo de nombre al archivo.
                arregloJSON.put(t.toJson());
            }
            JsonUtiles.grabarUnJson(arregloJSON,nombreArchivo + ".json");
        }
    }

    // Método ToJSON con la diferencia de que éste es para Strings (marcas y categorias). Donde se agarra el nombre de la lista para
    // así utilizarlo como nombre del archivo.

    public static <T extends GestorString> void toJsonString(T elementos){
        if(!elementos.getMap().isEmpty()) {
            String nombreArchivo = "";
            nombreArchivo = elementos.getNombre();
            JSONArray arregloJSON = elementos.toJson();

            JsonUtiles.grabarUnJson(arregloJSON,nombreArchivo + ".json");
        }
    }
}

