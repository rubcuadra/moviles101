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

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.itesm.csf.app1.R;

public class MainActivity extends AppCompatActivity
{
    @BindView(R.id.lista) Button firstButton;
    @BindView(R.id.button2) Button secondButton;
    @BindView(R.id.llamada) Button thirdButton;
    @BindView(R.id.auto) Button fourthButton;
    @BindView(R.id.activities) Button fifthButton;
    @BindView(R.id.activityChoice) Spinner spinAct;

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
        ButterKnife.bind(this);
        av_activities = getResources().getStringArray(R.array.activities_array);

        firstButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent().setClass(MainActivity.this, PostsListActivity.class)); //finish();
            }
        });

        secondButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent().setClass(MainActivity.this, GUIActivity.class));
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
                startActivity(new Intent().setClass(MainActivity.this, ParserActivity.class));
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
