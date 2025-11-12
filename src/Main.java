import Class.Gestores.*;
import Class.Personas.Empleado;
import Exceptions.ElementoRepetidoException;
import org.json.JSONArray;
import org.json.JSONException;

public class Main {
    public static void main(String[] args) {
        Cafeteria muddy = new Cafeteria();

        try {
            Empleado e = new Empleado("Lilo", "Trola", 21, 1, 1234, 333.33);
            System.out.println(e);
            if(muddy.listaEmpleados.agregar(e.getId(), e)) System.out.println("- Agregado");
        } catch (ElementoRepetidoException | NullPointerException ex) {
            System.out.println(ex.getMessage());
            System.out.println("- No agregado.");
        }

        System.out.println(muddy.listaEmpleados.mostrarLista());

        muddy.toJsonEmpleado();

        try{

            if(muddy.listaMarcas.agregar("pepsi")) System.out.println("- Agregado.");

        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
            System.out.println(" No agregado.");
        }
        System.out.println(muddy.listaMarcas.mostrar());

        muddy.toJsonMarcas();
        Cafeteria mussy = new Cafeteria();

        try {
            String muddyBackup = JsonUtiles.leer("class Class.Personas.Empleado");
            JSONArray muddyArreglo = new JSONArray(muddyBackup);
            mussy.fromJson(muddyArreglo);
        } catch (ElementoRepetidoException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        System.out.println(mussy.listaEmpleados.mostrarLista());

    }
}