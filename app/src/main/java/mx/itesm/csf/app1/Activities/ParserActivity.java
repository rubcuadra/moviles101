package mx.itesm.csf.app1.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.itesm.csf.app1.R;

public class ParserActivity extends AppCompatActivity {

    @BindView(R.id.objectsJSON) Button boton_obtieneObjetoJSON;
    @BindView(R.id.arrayJSON) Button boton_obtieneArregloJSON;

    private final String EXTRA_JSON_OBJECT_INDEX = "mx.itesm.csf.app1.parseractivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parser);
        ButterKnife.bind(this);
        // Como hemos hecho en otros ejemplos, el setOnClickListener nos permite
        // identificar si ha sido pulsado un boton en particular para llevarnos
        // a una actividad definida
        boton_obtieneObjetoJSON.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), ParserObjetoJSONActivity.class);
                i.putExtra(EXTRA_JSON_OBJECT_INDEX, 0);
                startActivity(i);
            }
        });

        boton_obtieneArregloJSON.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), ParserArregloJSONActivity.class);
                startActivity(i);
            }
        });

    }
}
