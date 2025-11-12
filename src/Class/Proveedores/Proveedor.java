package Class.Proveedores;
import Interface.IJson;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Objects;

public class Proveedor implements IJson {
    //Atributos.
    private int id;
    private static int idGeneral = 0;
    private String nombre;
    private String cuil;
    private int telefono;

    //Métodos constructores.
    public Proveedor(String nombre, String cuil, int telefono) {
        idGeneral++;
        this.id = idGeneral;
        this.nombre = nombre;
        this.cuil = cuil;
        this.telefono = telefono;
    }
    public Proveedor() {
        idGeneral++;
        this.id = idGeneral;
        this.nombre = "";
        this.cuil = "";
        this.telefono = 0;
    }

    //Getters y Setters.
    public int getId() {return id;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getCuil() {return cuil;}
    public void setCuil(String cuil) {this.cuil = cuil;}
    public int getTelefono() {return telefono;}
    public void setTelefono(int telefono) {this.telefono = telefono;}

    //Métodos propios.
    //Métodos JSON.
        //toJson.
    @Override
    public JSONObject toJson() {
        JSONObject objetoJSON = new JSONObject();
        try {
            objetoJSON.put("id", this.id);
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
            this.id = objetoJSON.getInt("id");
            this.nombre = objetoJSON.getString("nombre");
            this.cuil = objetoJSON.getString("cuil");
            this.telefono = objetoJSON.getInt("telefono");
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    //Override.
    @Override public String toString() {return "Proveedor{ " + "ID: " + id + "nombre: '" + nombre + '\'' + ", cuil: '" + cuil + '\'' + ", teléfono: " + telefono + " }";}
    @Override public boolean equals(Object o) {
        if (!(o instanceof Proveedor proveedor)) return false;
        return id == proveedor.id;
    }
    @Override public int hashCode() {return Objects.hashCode(id);}
}
