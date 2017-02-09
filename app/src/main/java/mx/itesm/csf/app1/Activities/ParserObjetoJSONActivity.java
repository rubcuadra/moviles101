package mx.itesm.csf.app1.Activities;

import android.app.ProgressDialog;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.itesm.csf.app1.Models.Auto;
import mx.itesm.csf.app1.R;
import mx.itesm.csf.app1.Requester;
import mx.itesm.csf.app1.Utils.JSONParser;

public class ParserObjetoJSONActivity extends AppCompatActivity {

    private static final String TAG ="ParseaObjetoJSON";

    @BindView(R.id.autoMarca) TextView textView_autoMarca;
    @BindView(R.id.autoModel) TextView textView_autoModel;
    @BindView(R.id.autoImage) SimpleDraweeView drawee_autoImage;

    // definimos end pont del objeto JSON
    private static String url = "http://ubiquitous.csf.itesm.mx/~raulms/do/REST/Objeto.exe";

    // referenciamos elementos del layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_parser_objeto_json);
        ButterKnife.bind(this);

        // definimos la barra de progreso
        final ProgressDialog barraDeProgreso = new ProgressDialog(ParserObjetoJSONActivity.this);
        barraDeProgreso.setMessage("Cargando datos...");
        barraDeProgreso.show();


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,url, null,new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {

                Auto miAuto = JSONParser.parseaObjeto(response);
                textView_autoMarca.setText( miAuto.getMarca() );
                textView_autoModel.setText( miAuto.getAuto () );
                drawee_autoImage.setImageURI(Uri.parse(miAuto.getImage()));
                Log.d(TAG, response.toString());
                barraDeProgreso.hide();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error)
            {
                VolleyLog.d(TAG, "Error en: " + error.getMessage());
                // oculta la barra de progreso
                barraDeProgreso.hide();
            }
        });
        Requester.getInstance().addToRequestQueue(jsonObjReq);
    }
}
