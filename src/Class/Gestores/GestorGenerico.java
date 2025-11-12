package Class.Gestores;
import Exceptions.ElementoNoEncontradoException;
import Exceptions.ElementoRepetidoException;
import Interface.IJson;
import org.json.JSONArray;
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
    public Boolean agregar(Integer clave,T elemento) throws ElementoRepetidoException {
        if(elemento == null) throw new NullPointerException();
        if(lista.containsKey(clave)) throw new ElementoRepetidoException();
        lista.put(clave, elemento);
        return true;
    }

        //Método eliminar a lista.
    public Boolean eliminar(Integer clave) throws ElementoNoEncontradoException {
        if(!lista.containsKey(clave)) throw new ElementoNoEncontradoException();
        lista.remove(clave);
        return true;
    }

        //Método buscar en la lista.
    public T buscarPorId(Integer idBuscar) throws ElementoNoEncontradoException {
        if(lista.containsKey(idBuscar)){
            return lista.get(idBuscar);
        }
        throw new ElementoNoEncontradoException();
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

    public static <T> HashSet exportarASet(HashMap<Integer,T> elementos){
        HashSet<T> set = new HashSet<>();
        for(Map.Entry<Integer,T> entry : elementos.entrySet()){
            set.add(entry.getValue());
        }
        return set;
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
