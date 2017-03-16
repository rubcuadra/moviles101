package mx.itesm.csf.app1.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.itesm.csf.app1.R;
import mx.itesm.csf.app1.Requester;

public class LoginActivity extends AppCompatActivity
{
    @BindView(R.id.button3) Button loginButton;
    @BindView(R.id.button4) Button registerButton;

    @BindView(R.id.editText) EditText et_username;
    @BindView(R.id.editText2) EditText et_password;
    @BindView(R.id.editText10) EditText et_email;


    public static final String AUTH_HEADER = "Basic OTEwMjo5MTAy";
    private static final String SERVICIO_REGISTRO = "http://ubiquitous.csf.itesm.mx/~pddm-1019102/content/parcial1/ejercicios/160217/servicio.registro.php";
    private static final String SERVICIO_LOGIN = "http://ubiquitous.csf.itesm.mx/~pddm-1019102/content/parcial2/ejercicios/270217/servicio.login.php";
    private static final String TAG = "LOGINACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        registerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                registerUser(et_username.getText().toString(),et_email.getText().toString(),et_password.getText().toString());
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loginUser(et_username.getText().toString(), et_password.getText().toString());

            }
        });
    }

    private void registerUser(final String uname, final String mail, final String pwd)
    {
        StringRequest rq = new StringRequest(Request.Method.POST, SERVICIO_REGISTRO, new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response)
                            {
                                Toast.makeText(LoginActivity.this,response,Toast.LENGTH_LONG).show();
                            }
                        }, new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                                Toast.makeText(LoginActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                            }
                        }){
                            @Override
                            protected Map<String,String> getParams()
                            {
                                Map<String,String> params = new HashMap<String, String>();
                                params.put("usuario",uname);
                                params.put("password",mail);
                                params.put("email", pwd);
                                return params;
                            }
                        };
        Requester.getInstance().addToRequestQueue(rq);
    }
    private void loginUser(final String uname, final String pwd)
    {
        String url = SERVICIO_LOGIN + String.format( "?usuario=%s&password=%s", uname,pwd );

        JsonArrayRequest rq = new JsonArrayRequest(Request.Method.GET,url, null ,new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response)
            {
                try
                {
                    JSONObject codes = response.getJSONObject(0);
                    if (codes.getInt("Codigo") == 01) //Exito
                    {
                        JSONObject data = response.getJSONObject(1);
                        JSONObject data2 = response.getJSONObject(2);
                        Toast.makeText(LoginActivity.this,"Welcome "+data2.getString("Nombre") ,Toast.LENGTH_SHORT).show();
                        Intent lst = new Intent().setClass(LoginActivity.this, Main2Activity.class);
                        lst.putExtra("user",data.getString("usuario"));
                        lst.putExtra("pass",data.getString("password")  );
                        lst.putExtra("uId", data.getInt("id") );
                        startActivity(lst);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this,response.toString(),Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                error.printStackTrace();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                HashMap<String, String> headers = new HashMap<String, String>();

                String auth = new String(Base64.encode("9102:9102".getBytes(),Base64.NO_WRAP ))  ;
                headers.put("Authorization", "Basic "+auth);

                return headers;
            }


        };

        Requester.getInstance().addToRequestQueue(rq);
    }
}
