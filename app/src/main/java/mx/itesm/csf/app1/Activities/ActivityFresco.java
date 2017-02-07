package mx.itesm.csf.app1.Activities;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import mx.itesm.csf.app1.R;

public class ActivityFresco extends AppCompatActivity
{
    private SimpleDraweeView mDraweeView;
    private static final String image_url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/Garlicbread.jpg/2560px-Garlicbread.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_fresco);

        mDraweeView = (SimpleDraweeView) findViewById(R.id.frescoImage);
        mDraweeView.setImageURI(Uri.parse(image_url));

    }
}
