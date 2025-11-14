package Models.Personas;
import Interface.IJson;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalAmount;
import java.util.Objects;

public class Persona implements IJson {
    //Atributos.
    private int id;
    private static int idGeneral = 0;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private int edad;
    private int dni;
    private String telefono;

    //Método constructor.
    public Persona(String nombre, String apellido, LocalDate fechaNacimiento, int dni, String telefono) {
        idGeneral++;
        this.id = idGeneral;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = calcularEdad();
        this.dni = dni;
        this.telefono = telefono;
    }
    public Persona() {
        idGeneral++;
        this.id = idGeneral;
        this.nombre = "";
        this.apellido = "";
        TemporalAmount temp = Period.ofYears(16);
        this.fechaNacimiento = LocalDate.now().minus(temp);
        this.edad = calcularEdad();
        this.dni = 0;
        this.telefono = "";
    }

    //Getters y Setters.
    public int getId() {return id;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getApellido() {return apellido;}
    public void setApellido(String apellido) {this.apellido = apellido;}
    public int getEdad() {return edad;}
    public void setEdad(int edad) {this.edad=edad;}
    public int getDni() {return dni;}
    public void setDni(int dni) {this.dni = dni;}
    public String getTelefono() {return telefono;}
    public void setTelefono(String telefono) {this.telefono = telefono;}
    public void setFechaNacimiento(LocalDate fechaNacimiento) {this.fechaNacimiento = fechaNacimiento;}

    //Métodos propios.
    public int calcularEdad(){
        Period periodo = Period.between(fechaNacimiento, LocalDate.now());
        return periodo.getYears();
    }
    public boolean validarTelefono() {
        return telefono.matches("\\+?[0-9 ]{7,20}"); // permite validar un numero con +,espacios, y entre 7 y 20 numeros
    }

    //Métodos JSON.
        //toJSON.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        try{
            json.put("id", id);
            json.put("nombre",nombre);
            json.put("apellido",apellido);
            json.put("fechaNacimiento",fechaNacimiento.toString());
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
            fechaNacimiento = LocalDate.parse(objetoJSON.getString("fechaNacimiento"));
            edad = objetoJSON.getInt("edad");
            dni = objetoJSON.getInt("dni");
            telefono = objetoJSON.getString("telefono");
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
