package Class.Gestores;

import Exceptions.EElementoNoEncontrado;
import Exceptions.EElementoRepetido;
import Interface.IIdentificable;

import java.util.HashSet;

public class GestorGenerico<T extends IIdentificable> {
    //Lista.
    private HashSet<T> lista;

    //Método constructor.
    public GestorGenerico(){
        this.lista = new HashSet<>();
    }


    //Métodos propios.
        //Método agregar a lista.
    public Boolean agregar(T elemento) throws NullPointerException, EElementoRepetido {
        if(elemento == null) throw new NullPointerException();
        if(lista.contains(elemento)) throw new EElementoRepetido();
        return lista.add(elemento);
    }

        //Método eliminar a lista.
    public Boolean eliminar(T elemento) throws NullPointerException, EElementoRepetido, EElementoNoEncontrado {
        if(elemento == null) throw new NullPointerException();
        if(!lista.contains(elemento)) throw new EElementoRepetido();
        return lista.remove(elemento);
    }

        //Método buscar en la lista.
    public T buscarPorId(int idBuscar) throws EElementoNoEncontrado{
        for(T elemento : lista){
            if(elemento.getId() == idBuscar) return elemento;
        }
        throw new EElementoNoEncontrado();
    }

        //Mostrar lista.
    public String mostrarLista(){
        StringBuilder sb = new StringBuilder("- Lista -\n");
        for(T elemento : lista){
            sb.append(elemento).append("\n");
        }
        return sb.toString();
    }


}
