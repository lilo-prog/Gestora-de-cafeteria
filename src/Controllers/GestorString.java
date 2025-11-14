package Controllers;

import Exceptions.ElementoNoEncontradoException;
import Exceptions.ElementoRepetidoException;
import org.json.JSONArray;
import org.json.JSONException;

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
        //Método agregar.
    public boolean agregar(String s) {
        return lista.add(s.toLowerCase());
    }

        //Método eliminar.
    public boolean eliminar(String s) throws ElementoNoEncontradoException {
        if (!lista.contains(s.toLowerCase())) throw new ElementoNoEncontradoException();
        return lista.remove(s);

    }

        //Método buscar.
    public Boolean buscar(String s) throws ElementoNoEncontradoException {
        if(!lista.contains(s.toLowerCase())) throw new ElementoNoEncontradoException();
        return true;
    }

        //Método mostrar.
    public String mostrar() {
        StringBuilder sb = new StringBuilder();
        for (String s : lista) {
            sb.append(s + "\n");
        }
        return sb.toString();
    }

    //Métodos JSON.
        //Método toJSON.
    public JSONArray toJson() {
        JSONArray arreglo = new JSONArray();
        for(String s : lista){
            arreglo.put(s);
        }
        return arreglo;
    }

        //Método fromJson.
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
