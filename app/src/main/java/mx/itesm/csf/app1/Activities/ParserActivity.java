package mx.itesm.csf.app1.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.itesm.csf.app1.Models.Auto;
import mx.itesm.csf.app1.R;
import mx.itesm.csf.app1.Requester;
import mx.itesm.csf.app1.Utils.JSONParser;

public class ParserActivity extends AppCompatActivity {

    @BindView(R.id.objectsJSON) Button boton_obtieneObjetoJSON;
    @BindView(R.id.arrayJSON) Button boton_obtieneArregloJSON;

    private Auto objectAuto;
    private static final String TAG =  "ParserActivityButtons";
    private static String url = "http://ubiquitous.csf.itesm.mx/~pddm-1019102/content/parcial1/tareas/8/servicio.autoObject.php";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parser);
        ButterKnife.bind(this);
        getRequests();

        boton_obtieneObjetoJSON.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplication(), AutoDetailActivity.class);
                i.putExtras( Auto.asBundle(objectAuto) );
                startActivity(i);
            }
        });

        boton_obtieneArregloJSON.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplication(), AutosListActivity.class);
                startActivity(i);
            }
        });
    }
    void getRequests()
    {
        final ProgressDialog barraDeProgreso = new ProgressDialog(this);
        barraDeProgreso.setMessage("Espera...");
        barraDeProgreso.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,url, null,new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                objectAuto = JSONParser.parseaObjeto(response);
                barraDeProgreso.hide();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error)
            {
                VolleyLog.d(TAG,error.getMessage());
                barraDeProgreso.hide();
            }
        });

        Requester.getInstance().addToRequestQueue(jsonObjReq);
    }
}
