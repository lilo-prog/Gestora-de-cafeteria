package Models.Personas;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;

public class Cliente extends Persona {
    //Atributos.
    private Double gastoTotal = 0.0;
    private Double descuento = 0.0;

    //Método constructor.
    public Cliente(String nombre, String apellido, LocalDate fechaNacimiento, String dni, String telefono, Double gastosTotales, Double descuento) {
        super(nombre, apellido, fechaNacimiento, dni, telefono);
        this.gastoTotal = gastosTotales;
        this.descuento = descuento;
    }
    public Cliente() {
        super();
    }

    //Getters y Setters.
    public Double getGastoTotal() {return gastoTotal;}
    public void setGastoTotal(Double gastoTotal) {this.gastoTotal = gastoTotal;}
    public Double getDescuento() {return descuento;}
    public void setDescuento(Double descuento) {this.descuento = descuento;}

//Métodos propios.

    //Métodos JSON.
        //toJSON.
    @Override public JSONObject toJson() {
        JSONObject json =  super.toJson();
        try{
            json.put("gastos_totales", gastoTotal);
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
            gastoTotal = objetoJSON.getDouble("gastos_totales");
            descuento = objetoJSON.getDouble("descuento");
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    //Override.
    @Override public String toString() {return "Cliente{ " + super.toString() + ", gastos totales: " + gastoTotal + ", descuento: " + descuento + " }";}

}
