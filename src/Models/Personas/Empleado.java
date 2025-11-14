package Models.Personas;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;

public class Empleado extends Persona{
    //Atributos.
    private Double sueldo;

    //Método constructor.
    public Empleado(String nombre, String apellido, LocalDate fechaNacimiento, int dni, String telefono, Double sueldo) {
        super(nombre, apellido, fechaNacimiento, dni, telefono);
        this.sueldo = sueldo;
    }
    public Empleado() {
        super();
        this.sueldo = 0.0;
    }

    //Getters y Setters.
    public Double getSueldo() {return sueldo;}
    public void setSueldo(Double sueldo) {this.sueldo = sueldo;}

    //Métodos propios.
    //Métodos JSON.
        //toJson.
    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        try{
            json.put("sueldo", this.sueldo);
            json.put("clase",this.getClass().getSimpleName());
        } catch (JSONException e){
            e.printStackTrace();
        }
        return json;
    }

        //toEmpleado.
    @Override
    public void fromJson(JSONObject objetoJSON) {
        super.fromJson(objetoJSON);
        try {
            this.sueldo = objetoJSON.getDouble("sueldo");
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    //Override.

    @Override public String toString() {return "Empleado{ " + super.toString() + ", sueldo: " + sueldo + " }";}
}
