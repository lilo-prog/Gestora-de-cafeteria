package Controllers;

import Exceptions.ElementoNoEncontradoException;
import Enum.ETipoProducto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class GestorString {
    //Atributos.
    private String nombre;
    private HashMap<String,ETipoProducto> lista;

    //Método constructor.
    public GestorString(String nombre) {
        this.nombre = nombre;
        this.lista = new HashMap<>();
    }

    public GestorString() {
        this.nombre = "";
        this.lista = new HashMap<>();
    }

    //Getters y Setters.
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public HashMap<String,ETipoProducto> getMap() {return lista;}

    //Métodos propios.
        //Método agregar.
    public void agregar(String s,ETipoProducto e) {
        lista.put(s.toLowerCase(),e);
    }

        //Método eliminar.
    public boolean eliminar(String s) throws ElementoNoEncontradoException {
        if (!lista.containsKey(s.toLowerCase())) throw new ElementoNoEncontradoException();
        return lista.remove(s.toLowerCase()) != null;

    }

        //Método buscar.
    public Boolean buscar(String s) throws ElementoNoEncontradoException {
        if(!lista.containsKey(s.toLowerCase())) throw new ElementoNoEncontradoException();
        return true;
    }

        //Método mostrar.
    public String mostrar() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, ETipoProducto> entry : lista.entrySet()) {
            sb.append(entry.getKey()).append("\n");
        }
        return sb.toString();
    }

    //Métodos JSON.
        //Método toJSON.
    public JSONArray toJson() {
        JSONArray arreglo = new JSONArray();
        try {
            for(Map.Entry<String, ETipoProducto> entry : lista.entrySet()) {
                JSONObject objeto = new JSONObject();
                objeto.put("nombre",entry.getKey());
                objeto.put("tipo_producto",entry.getValue().name());
                arreglo.put(objeto);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arreglo;
    }

        //Método fromJson.
    public void fromJson(JSONArray arreglo) {
        try{
            for(int i = 0; i<arreglo.length(); i++){
                JSONObject objeto = arreglo.getJSONObject(i);
                lista.put(objeto.getString("nombre"),ETipoProducto.valueOf(objeto.getString("tipo_producto")));
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}
