package Class.Gestores;

import Interface.IJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;

public class GestorString {
    //Atributos.
    private String nombre;
    private HashSet<String> lista;

    //Método constructor.
    public GestorString(String nombre) {
        this.nombre = nombre;
        this.lista = new HashSet<>();
    }
    public GestorString() {
        this.nombre = "";
        this.lista = new HashSet<>();
    }

    //Getters y Setters.
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public HashSet<String> getLista() {return lista;}

    //Métodos propios.
    public boolean agregar(String s) {
        return lista.add(s.toLowerCase());
    }

    public boolean eliminar(String s) {
        if (lista.contains(s.toLowerCase())) return lista.remove(s);
        return false;
    }
    public boolean buscar(String s){
        if(lista.contains(s.toLowerCase())) return true;
        return false;
    }
    public String mostrar() {
        StringBuilder sb = new StringBuilder();
        for (String s : lista) {
            sb.append(s + "\n");
        }
        return sb.toString();
    }

    public JSONArray toJson() {
        JSONArray arreglo = new JSONArray();
        for(String s : lista){
            arreglo.put(s);
        }
        return arreglo;
    }

    public void fromJson(JSONArray arreglo) {
        try{
            for(int i = 0; i<arreglo.length(); i++){
                String aux = arreglo.getString(i);
                lista.add(aux);
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

}
