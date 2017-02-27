package mx.itesm.csf.app1.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

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


    private static final String SERVICIO_REGISTRO = "http://ubiquitous.csf.itesm.mx/~pddm-1019102/content/parcial1/ejercicios/160217/servicio.registro.php";
    private static final String SERVICIO_LOGIN = "http://ubiquitous.csf.itesm.mx/~pddm-1019102/content/parcial2/ejercicios/230217/servicio.login.php";

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
        StringRequest rq = new StringRequest(Request.Method.GET, SERVICIO_LOGIN+"?usuario="+uname+"&password="+pwd, new Response.Listener<String>()
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
        });
        Requester.getInstance().addToRequestQueue(rq);
    }
}
