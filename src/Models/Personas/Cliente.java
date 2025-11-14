package Models.Personas;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;

public class Cliente extends Persona {
    //Atributos.
    private Double gastosTotales;

    //Método constructor.
    public Cliente(String nombre, String apellido, LocalDate fechaNacimiento, int dni, String telefono, Double gastosTotales) {
        super(nombre, apellido, fechaNacimiento, dni, telefono);
        this.gastosTotales = gastosTotales;
    }
    public Cliente() {
        super();
        this.gastosTotales = 0.0;
    }

    //Getters y Setters.
    public Double getGastosTotales() {return gastosTotales;}
    public void setGastosTotales(Double gastosTotales) {this.gastosTotales = gastosTotales;}

    //Métodos propios.
    //Métodos JSON.
        //toJSON.
    @Override public JSONObject toJson() {
        JSONObject json =  super.toJson();
        try{
            json.put("gastos_totales",gastosTotales);
            json.put("clase",this.getClass().getSimpleName());
        }catch(JSONException e){
            e.printStackTrace();
        }
        return json;
    }

        //toCLiente.
    @Override
    public void fromJson(JSONObject objetoJSON) {
        super.fromJson(objetoJSON);
        try{
            gastosTotales = objetoJSON.getDouble("gastos_totales");
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    //Override.
    @Override public String toString() {return "Cliente{ " + super.toString() + ", gastos totales=: " + gastosTotales + " }";}

}
