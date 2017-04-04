package mx.itesm.csf.app1.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mx.itesm.csf.app1.R;
import mx.itesm.csf.app1.Requester;

public class CRUD extends AppCompatActivity
{
    @BindView(R.id.editText15) EditText auto_nombre;
    @BindView(R.id.editText16) EditText auto_id;
    @BindView(R.id.editText17) EditText auto_price;
    @BindView(R.id.editText18) EditText auto_url;
    @BindView(R.id.editText19) EditText auto_id_marca;

    @BindView(R.id.editText21) EditText marca_name;
    @BindView(R.id.editText22) EditText marca_id;

    @BindView(R.id.editText23) EditText compa_name;

    private static final String SERVICIO_CREATE_AUTO = "http://ubiquitous.csf.itesm.mx/~pddm-1019102/content/parcial2/tareas/7/servicio.c.autos.php";
    private static final String SERVICIO_CREATE_MARCA = "http://ubiquitous.csf.itesm.mx/~pddm-1019102/content/parcial2/tareas/7/servicio.c.marcas.php";
    private static final String SERVICIO_CREATE_COMPA = "http://ubiquitous.csf.itesm.mx/~pddm-1019102/content/parcial2/tareas/7/servicio.c.compa.php";

    @OnClick(R.id.button5)
    public void create_auto(View view)
    {
        //Validar que no esten vacios los campos
        StringRequest requestBorrar = new StringRequest(Request.Method.POST, SERVICIO_CREATE_AUTO, new Response.Listener<String>()
        {
                    @Override
                    public void onResponse(String response)
                    {
                        Toast.makeText(CRUD.this, response , Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.d("volley", "Error : " + error.getMessage());
                        Toast.makeText(CRUD.this, "Respuesta: Error al crear coche", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> map = new HashMap<>();

                map.put("clave", auto_id.getText().toString() );
                map.put("nombre", auto_nombre.getText().toString() );
                map.put("precio", auto_price.getText().toString() );
                map.put("imagen", auto_url.getText().toString() );
                map.put("id_marca", auto_id_marca.getText().toString() );

                return map;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                HashMap<String, String> headers = new HashMap<String, String>();
                String auth = new String(Base64.encode("9102:9102".getBytes(),Base64.NO_WRAP ))  ;
                headers.put("Authorization", "Basic "+auth);
                return headers;
            }
        };

        Requester.getInstance().addToRequestQueue(requestBorrar);
    }

    @OnClick(R.id.button6)
    public void create_marca(View view)
    {
        //Validar que no esten vacios los campos
        StringRequest requestBorrar = new StringRequest(Request.Method.POST, SERVICIO_CREATE_MARCA, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Toast.makeText(CRUD.this, response , Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.d("volley", "Error : " + error.getMessage());
                        Toast.makeText(CRUD.this, "Respuesta: Error al crear marca", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> map = new HashMap<>();

                map.put("clave", marca_id.getText().toString() );
                map.put("nombre", marca_name.getText().toString() );

                return map;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                HashMap<String, String> headers = new HashMap<String, String>();
                String auth = new String(Base64.encode("9102:9102".getBytes(),Base64.NO_WRAP ))  ;
                headers.put("Authorization", "Basic "+auth);
                return headers;
            }
        };

        Requester.getInstance().addToRequestQueue(requestBorrar);
    }

    @OnClick(R.id.button7)
    public void create_compa(View view)
    {
        //Validar que no esten vacios los campos
        StringRequest requestBorrar = new StringRequest(Request.Method.POST, SERVICIO_CREATE_COMPA, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Toast.makeText(CRUD.this, response , Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.d("volley", "Error : " + error.getMessage());
                        Toast.makeText(CRUD.this, "Respuesta: Error al crear marca", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> map = new HashMap<>();

                map.put("nombre", compa_name.getText().toString() );

                return map;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                HashMap<String, String> headers = new HashMap<String, String>();
                String auth = new String(Base64.encode("9102:9102".getBytes(),Base64.NO_WRAP ))  ;
                headers.put("Authorization", "Basic "+auth);
                return headers;
            }
        };

        Requester.getInstance().addToRequestQueue(requestBorrar);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

}
