package mx.itesm.csf.app1.Activities;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mx.itesm.csf.app1.Models.Auto;
import mx.itesm.csf.app1.Models.Card;
import mx.itesm.csf.app1.R;
import mx.itesm.csf.app1.Requester;

public class CardDetailActivity extends AppCompatActivity {

    private static final String TAG ="AutoDetail";

    @BindView(R.id.autoMarca2) EditText editText_autoMarca;
    @BindView(R.id.autoModel2) EditText editText_autoModel;
    @BindView(R.id.autoPrecio) EditText editText_autoPrice;

    @BindView(R.id.autoImage2) SimpleDraweeView drawee_autoImage;

    private final static String SERVICIO_ACTUALIZAR = "http://ubiquitous.csf.itesm.mx/~pddm-1019102/content/parcial2/tareas/7/servicio.u.autos.php";
    private Card current;

    // referenciamos elementos del layout
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_card_detail);
        ButterKnife.bind(this);
        current = Card.fromBundle( getIntent().getExtras() );
        Log.d("DETAIL", current.toString());
        editText_autoModel.setText( current.getAuto () );
        editText_autoPrice.setText( current.getPrecio() );
        editText_autoMarca.setText( String.valueOf(current.getClave_marca())  );
        drawee_autoImage.setImageURI(Uri.parse(current.getImage()));
    }

    public boolean is_valid_data()
    {
        //Precio y Marca sean numericos
        return editText_autoModel.length() != 0 &
                editText_autoPrice.length() != 0 &
                editText_autoMarca.length() != 0 ;
    }
    @OnClick(R.id.delete_card)
    public void delete(View view)
    {

    }

    @OnClick(R.id.update_card)
    public void update(View view)
    {
        if (!is_valid_data()) return;

        StringRequest updateReq = new StringRequest(Request.Method.POST, SERVICIO_ACTUALIZAR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {

                        try
                        {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(CardDetailActivity.this, "Respuesta : "+   res.getString("Mensaje") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(CardDetailActivity.this, "Respuesta: Error al insertar datos", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String,String> map = new HashMap<>();
                map.put("id_auto",current.getId().toString());
                map.put("imagen",current.getImage());

                map.put("nombre", editText_autoModel.getText().toString());
                map.put("precio", editText_autoPrice.getText().toString());
                map.put("id_marca",editText_autoMarca.getText().toString());

                Log.d("Parámetros: ", SERVICIO_ACTUALIZAR + map.toString());

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

        Requester.getInstance().addToRequestQueue(updateReq);
    }
}
