package mx.itesm.csf.app1.Activities;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.itesm.csf.app1.Models.Auto;
import mx.itesm.csf.app1.R;

public class AutoDetailActivity extends AppCompatActivity {

    private static final String TAG ="AutoDetail";

    @BindView(R.id.autoMarca) TextView textView_autoMarca;
    @BindView(R.id.autoModel) TextView textView_autoModel;

    @BindView(R.id.autoColor) TextView textView_autoColor;
    @BindView(R.id.autoAnio) TextView textView_autoAnio;
    @BindView(R.id.autoPuertas) TextView textView_autoPuertas;


    @BindView(R.id.autoImage) SimpleDraweeView drawee_autoImage;

    private Auto current;

    // referenciamos elementos del layout
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_auto_detail);
        ButterKnife.bind(this);
        current = Auto.fromBundle( getIntent().getExtras() );
        Log.d("DETAIL",current.toString());
        textView_autoMarca.setText( current.getMarca() );
        textView_autoModel.setText( current.getAuto () );
        textView_autoColor.setText( current.getColor() );
        textView_autoAnio.setText( String.valueOf(current.getAnio())  );
        textView_autoPuertas.setText( String.valueOf(current.getPuertas()) );
        drawee_autoImage.setImageURI(Uri.parse(current.getImage()));
    }
}
