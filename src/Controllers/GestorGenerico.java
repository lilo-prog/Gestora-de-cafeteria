package Controllers;
import Exceptions.ElementoNoEncontradoException;
import Exceptions.ElementoRepetidoException;
import Interface.IJson;
import org.json.JSONArray;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class GestorGenerico<T extends IJson> {
    //Lista.
    // SE USA LONG PURA Y EXCLUSIVAMENTE POR QUE LA CLAVE DE LOS PROVEEDORES ES EL CUIL (NUMERO DE 11 DIGITOS).
    protected HashMap<Long,T> lista;

    //Método constructor.
    public GestorGenerico(){
        this.lista = new HashMap<>();
    }


    //Métodos propios.
        //Método agregar a lista.
    public Boolean agregar(Long clave,T elemento) throws ElementoRepetidoException {
        if(elemento == null) throw new NullPointerException();
        if(lista.containsKey(clave)) throw new ElementoRepetidoException();
        lista.put(clave, elemento);
        return true;
    }

        //Método eliminar a lista.
    public Boolean eliminar(Long clave) throws ElementoNoEncontradoException {
        if(!lista.containsKey(clave)) throw new ElementoNoEncontradoException();
        lista.remove(clave);
        return true;
    }

        //Método buscar en la lista.
    public T buscarPorId(Long idBuscar) throws ElementoNoEncontradoException {
        if(lista.containsKey(idBuscar)){
            return lista.get(idBuscar);
        }
        throw new ElementoNoEncontradoException();
    }

        //Mostrar lista.
    public String mostrarLista(){
        StringBuilder sb = new StringBuilder("- Lista -\n");
        for(Map.Entry<Long, T> entry : lista.entrySet()){
            sb.append(entry.getValue().toString()).append("\n");
        }
        return sb.toString();
    }

        //Getter.
    public HashMap<Long,T> getMap(){
        return lista;
    }

        //Método exportar a HashSet para luego poder hacer el toJson.
    public static <T> HashSet exportarASet(HashMap<Long,T> elementos){
        HashSet<T> set = new HashSet<>();
        for(Map.Entry<Long,T> entry : elementos.entrySet()){
            set.add(entry.getValue());
        }
        return set;
    }

        //toJson.
    public JSONArray toJson(){
        JSONArray listaJSON = new JSONArray();
        for(Map.Entry<Long, T> entry : lista.entrySet()){
            listaJSON.put(entry.getValue().toJson());
        }
        return listaJSON;
    }

}
