package mx.itesm.csf.app1.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import mx.itesm.csf.app1.R;

public class MainActivity extends AppCompatActivity
{
    private Button firstButton;
    private Button secondButton;
    private Button thirdButton;
    private Button fourthButton;
    private Button fifthButton;
    private Spinner spinAct;

    private String[] av_activities;
    private String selectedIntent;
    private int selectedIntentId;

    private static final int DIAL_ID = 43;
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
        fifthButton = (Button) findViewById(R.id.activities);
        spinAct = (Spinner) findViewById(R.id.activityChoice);

        av_activities = getResources().getStringArray(R.array.activities_array);

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
        fifthButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    if (selectedIntent != null)
                        startActivityForResult(new Intent(selectedIntent), selectedIntentId);
                }
                catch (Exception e)
                {
                    Log.e("LOG",e.toString());
                    Toast.makeText(MainActivity.this,"No se encontro actividad para eso", Toast.LENGTH_SHORT).show();
                }
            }
        });

        spinAct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                fifthButton.setText(av_activities[position]);
                selectedIntentId = position;
                switch (position)
                {
                    case 0:
                        selectedIntent = Intent.ACTION_ANSWER;
                        break;
                    case 1:
                        selectedIntent = Intent.ACTION_CALL;
                        break;
                    case 2:
                        selectedIntent = Intent.ACTION_DELETE;
                        break;
                    case 3:
                        selectedIntent = Intent.ACTION_DIAL;
                        break;
                    case 4:
                        selectedIntent = Intent.ACTION_EDIT;
                        break;
                    case 5:
                        selectedIntent = Intent.ACTION_INSERT;
                        break;
                    case 6:
                        selectedIntent = Intent.ACTION_PICK;
                        break;
                    case 7:
                        selectedIntent = Intent.ACTION_SEARCH;
                        break;
                    case 8:
                        selectedIntent = Intent.ACTION_SENDTO;
                        break;
                    case 9:
                        selectedIntent = Intent.ACTION_VIEW;
                        break;
                    case 10:
                        selectedIntent = Intent.ACTION_WEB_SEARCH;
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
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
                    Log.d(TAG, String.valueOf(data.getData()));
                }
                break;
            default:
                break;
        }

    }

}
