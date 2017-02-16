package mx.itesm.csf.app1.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;

import butterknife.ButterKnife;
import mx.itesm.csf.app1.R;

public class GUIActivity extends AppCompatActivity
{
    //@BindView(R.id.frescoImage) SimpleDraweeView mDraweeView;
    //private static final String image_url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/Garlicbread.jpg/2560px-Garlicbread.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_gui);
        ButterKnife.bind(this);


        //mDraweeView.setImageURI(Uri.parse(image_url));

    }
}
