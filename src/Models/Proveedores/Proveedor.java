package Models.Proveedores;
import Interface.IJson;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Objects;

public class Proveedor implements IJson {
    //Atributos.
    private String nombre;
    private String cuil; // porque tiene 11 digitos
    private String telefono;

    //Métodos constructores.
    public Proveedor(String nombre, String cuil,String telefono) {
        this.nombre = nombre;
        this.cuil = cuil;
        this.telefono = telefono;
    }
    public Proveedor() {
        this.nombre = "";
        this.cuil = "";
        this.telefono = "";
    }

    //Getters y Setters.
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getCuil() {return cuil;}
    public void setCuil(String cuil) {this.cuil = cuil;}
    public String getTelefono() {return telefono;}
    public void setTelefono(String telefono) {this.telefono = telefono;}

    //Métodos propios.
    public boolean validarTelefono() {
        return telefono.matches("\\+?[0-9 ]{7,20}"); // permite validar un numero con +,espacios, y entre 7 y 20 numeros
    }
    //Métodos JSON.
        //toJson.
    @Override
    public JSONObject toJson() {
        JSONObject objetoJSON = new JSONObject();
        try {
            objetoJSON.put("nombre", this.nombre);
            objetoJSON.put("cuil", this.cuil);
            objetoJSON.put("telefono", this.telefono);
            objetoJSON.put("clase",this.getClass().getSimpleName());
        } catch (JSONException e){
            e.printStackTrace();
        }
        return objetoJSON;
    }
        //toProveedor.
    @Override
    public void fromJson(JSONObject objetoJSON) {
        try {
            this.nombre = objetoJSON.getString("nombre");
            this.cuil = objetoJSON.getString("cuil");
            this.telefono = objetoJSON.getString("telefono");
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    //Override.
    @Override public boolean equals(Object o) {
        if (!(o instanceof Proveedor proveedor)) return false;
        return Objects.equals(cuil, proveedor.cuil);
    }
    @Override public int hashCode() {return Objects.hashCode(cuil);}

    @Override public String toString() {return "Proveedor{ " + "nombre: '" + nombre + '\'' + ", CUIL: '" + cuil + '\'' + ", teléfono: " + telefono + " }";}
}
