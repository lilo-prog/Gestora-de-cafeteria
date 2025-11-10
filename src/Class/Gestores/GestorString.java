package Class.Gestores;

import Interface.IJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;

public class GestorString {
    private HashSet<String> lista;

    public GestorString() {
        this.lista = new HashSet<>();
    }

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
                JSONObject aux = arreglo.getJSONObject(i);
                lista.add(aux.toString());
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

}
