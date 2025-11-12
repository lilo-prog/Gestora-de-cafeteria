package Class.Personas;
import Interface.IJson;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Objects;

public class Persona implements IJson{
    //Atributos.
    private int id;
    private static int idGeneral=0;
    private String nombre;
    private String apellido;
    private int edad;
    private int dni;
    private int telefono;

    //Método constructor.
    public Persona(String nombre, String apellido, int edad, int dni, int telefono) {
        idGeneral++;
        this.id = idGeneral;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.dni = dni;
        this.telefono = telefono;
    }
    public Persona() {
        idGeneral++;
        this.id = idGeneral;
        this.nombre = "";
        this.apellido = "";
        this.edad = 0;
        this.dni = 0;
        this.telefono = 0;
    }

    //Getters y Setters.
    public int getId() {return id;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getApellido() {return apellido;}
    public void setApellido(String apellido) {this.apellido = apellido;}
    public int getEdad() {return edad;}
    public void setEdad(int edad) {this.edad = edad;}
    public int getDni() {return dni;}
    public void setDni(int dni) {this.dni = dni;}
    public int getTelefono() {return telefono;}
    public void setTelefono(int telefono) {this.telefono = telefono;}

    //Métodos propios.
    //Métodos JSON.
        //toJSON.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        try{
            json.put("id", id);
            json.put("nombre",nombre);
            json.put("apellido",apellido);
            json.put("edad",edad);
            json.put("dni",dni);
            json.put("telefono",telefono);
        }catch(JSONException e){
            e.printStackTrace();
        }
        return json;
    }

    //toPersona.
    @Override
    public void fromJson(JSONObject objetoJSON) {
        try{
            id = objetoJSON.getInt("id");
            nombre = objetoJSON.getString("nombre");
            apellido = objetoJSON.getString("apellido");
            edad = objetoJSON.getInt("edad");
            dni = objetoJSON.getInt("dni");
            telefono = objetoJSON.getInt("telefono");
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    //Override.
    @Override public boolean equals(Object o) {
        if (!(o instanceof Persona persona)) return false;
        return dni == persona.dni;
    }
    @Override public int hashCode() {return Objects.hashCode(dni);}

    @Override public String toString() {return "ID: " + id + ", nombre: '" + nombre + '\'' + ", apellido: '" + apellido + '\'' + ", edad: " + edad + ", dni: " + dni + ", teléfono: " + telefono;}
}
