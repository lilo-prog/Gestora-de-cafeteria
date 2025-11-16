package Controllers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUtiles {

    public static void grabarUnJson(JSONArray jsonArray, String archivo){
        try {
            FileWriter file = new FileWriter(archivo);
            file.write(jsonArray.toString(4));
            file.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public static JSONTokener leerUnJson(String archivo) {
        // Siempre agregar ".json" si no viene
        if (!archivo.endsWith(".json")) {
            archivo = archivo + ".json";
        }

        File f = new File(archivo);

        // Si no existe, no intentar leerlo
        if (!f.exists()) {
            return null;
        }

        try {
            return new JSONTokener(new FileReader(f));

        } catch (FileNotFoundException e) {
            System.out.println("Error al abrir el archivo JSON.");
            return null;
        }
    }

    //Otra forma
    public static String leer(String archivo)
    {
        String contenido = "";
        try
        {
            contenido = new String(Files.readAllBytes(Paths.get(archivo+".json")));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return contenido;
    }
}