package Class.Gestores;

import Interface.IJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;

public class GestorString implements IJson{
    private String nombreLista;
    private HashSet<String> lista;

    public GestorString(String nombreLista) {
        this.nombreLista = nombreLista;
        this.lista = new HashSet<>();
    }
    public GestorString() {
        this.nombreLista = "";
        this.lista = new HashSet<>();
    }

    public String getNombreLista() {
        return nombreLista;
    }

    public void setNombreLista(String nombreLista) {
        this.nombreLista = nombreLista;
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray arreglo = new JSONArray();
        try{
            json.put("nombreLista",nombreLista);
            arreglo.put(lista);
            json.put(nombreLista,arreglo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
    @Override
    public void fromJson(JSONObject objetoJSON) {
        try{
            nombreLista = objetoJSON.getString("nombreLista");
            JSONArray arreglo = objetoJSON.getJSONArray("lista");
            for(int i = 0; i<arreglo.length(); i++){
                JSONObject aux = arreglo.getJSONObject(i);
                lista.add(aux.toString());
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
    }
    
}
