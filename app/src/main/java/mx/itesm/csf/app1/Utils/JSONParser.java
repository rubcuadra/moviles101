package mx.itesm.csf.app1.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mx.itesm.csf.app1.Models.Auto;

/**
 * Created by rubcuadra on 2/9/17.
 */

public class JSONParser
{
    public static ArrayList<Auto> autos = new ArrayList<>();

    public static Auto parseaObjeto(JSONObject obj)
    {
        try
        {
            Auto aut = new Auto();
            aut.setAuto( obj.getString("auto") );
            aut.setMarca(obj.getString("marca"));
            aut.setImage(obj.getString("image"));
            aut.setAnio( obj.getInt("anio"));
            aut.setColor(obj.getString("color"));
            aut.setId(obj.getInt("id"));
            aut.setPuertas(obj.getInt("puertas"));
            return aut;

        }
        catch(JSONException e1)
        {
            e1.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Auto> parseaArreglo(JSONArray arr)
    {
        JSONObject obj = null;
        Auto auto = null;
        autos.clear();
        try
        {
            for (int i = 0; i < arr.length(); i++)
            {
                obj = arr.getJSONObject(i);
                auto = new Auto();

                auto.setAuto( obj.getString("auto") );
                auto.setMarca(obj.getString("marca"));
                auto.setImage(obj.getString("image"));
                auto.setAnio( obj.getInt("anio"));
                auto.setColor(obj.getString("color"));
                auto.setId(obj.getInt("id"));
                auto.setPuertas(obj.getInt("puertas"));

                autos.add(auto);
            }
            return autos;
        }
        catch(JSONException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}
