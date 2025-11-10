package Class.Gestores;

import Exceptions.EElementoNoEncontrado;
import Exceptions.EElementoRepetido;
import Interface.IJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class GestorGenerico<T extends IJson> {
    //Lista.
    protected HashMap<Integer,T> lista;

    //Método constructor.
    public GestorGenerico(){
        this.lista = new HashMap<>();
    }


    //Métodos propios.
        //Método agregar a lista.
    public Boolean agregar(Integer clave,T elemento) throws NullPointerException, EElementoRepetido {
        if(elemento == null) throw new NullPointerException();
        if(lista.containsKey(clave)) throw new EElementoRepetido();
        lista.put(clave, elemento);
        return true;
    }

        //Método eliminar a lista.
    public Boolean eliminar(Integer clave) throws EElementoNoEncontrado {
        if(!lista.containsKey(clave)) throw new EElementoNoEncontrado();
        lista.remove(clave);
        return true;
    }

        //Método buscar en la lista.
    public T buscarPorId(Integer idBuscar) throws EElementoNoEncontrado{
        if(lista.containsKey(idBuscar)){
            return lista.get(idBuscar);
        }
        throw new EElementoNoEncontrado();
    }

        //Mostrar lista.
    public String mostrarLista(){
        StringBuilder sb = new StringBuilder("- Lista -\n");
        for(Map.Entry<Integer, T> entry : lista.entrySet()){
            sb.append(entry.getValue().toString()).append("\n");
        }
        return sb.toString();
    }
    public HashMap<Integer,T> getMap(){
        return lista;
    }

        //toJson.
    public JSONArray toJson(){
        JSONArray listaJSON = new JSONArray();
        for(Map.Entry<Integer, T> entry : lista.entrySet()){
            listaJSON.put(entry.getValue().toJson());
        }
        return listaJSON;
    }

}
