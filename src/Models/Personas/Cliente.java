package Models.Personas;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;

public class Cliente extends Persona {
    //Atributos.
    private Double gastosTotales;
    private Double descuento;

    //Método constructor.
    public Cliente(String nombre, String apellido, LocalDate fechaNacimiento, int dni, String telefono, Double gastosTotales, Double descuento) {
        super(nombre, apellido, fechaNacimiento, dni, telefono);
        this.gastosTotales = gastosTotales;
        this.descuento = descuento;
    }
    public Cliente() {
        super();
        this.gastosTotales = 0.0;
    }

    //Getters y Setters.
    public Double getGastosTotales() {return gastosTotales;}
    public void setGastosTotales(Double gastosTotales) {this.gastosTotales = gastosTotales;}
    public Double getDescuento() {return descuento;}
    public void setDescuento(Double descuento) {this.descuento = descuento;}

//Métodos propios.

    //Métodos JSON.
        //toJSON.
    @Override public JSONObject toJson() {
        JSONObject json =  super.toJson();
        try{
            json.put("gastos_totales", gastosTotales);
            json.put("descuento", descuento);
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
            descuento = objetoJSON.getDouble("descuento");
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    //Override.
    @Override public String toString() {return "Cliente{ " + super.toString() + ", gastos totales=: " + gastosTotales + " }";}

}
