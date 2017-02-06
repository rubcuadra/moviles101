package mx.itesm.csf.app1.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import mx.itesm.csf.app1.R;

public class MainActivity extends AppCompatActivity
{
    private Button firstButton;
    private Button secondButton;
    private Button thirdButton;
    private Button fourthButton;
    private static final int DIAL_ID = 1;
    private static final String phone = "5591011410";
    private static final String TAG = "PHONE";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstButton = (Button) findViewById(R.id.lista);
        secondButton = (Button) findViewById(R.id.button2);
        thirdButton = (Button) findViewById(R.id.llamada);
        fourthButton = (Button) findViewById(R.id.video);

        firstButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent().setClass(MainActivity.this, ActivityPostsList.class)); //finish();
            }
        });

        secondButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent().setClass(MainActivity.this, Activity2.class));
            }
        });

        thirdButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent dial = new Intent(Intent.ACTION_DIAL);
                dial.setData(Uri.parse("tel:"+phone));
                if (dial.resolveActivity(getPackageManager()) != null)
                {
                    startActivityForResult(dial,DIAL_ID);
                }
            }
        });

        fourthButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent().setClass(MainActivity.this, SplashVideoActivity.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        switch (requestCode)
        {
            case DIAL_ID:
                if (resultCode == RESULT_OK)
                {
                    //Nos dijo que fue exitoso el resultado de la llamada
                    Log.d(TAG, String.valueOf(data.getData()));
                }
                break;
            default:
                break;
        }

    }

}
