package mx.itesm.csf.app1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    private Button firstButton;
    private Button secondButton;
    private Button thirdButton;
    private Button fourthButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstButton = (Button) findViewById(R.id.button1);
        secondButton = (Button) findViewById(R.id.button2);
        thirdButton = (Button) findViewById(R.id.button3);
        fourthButton = (Button) findViewById(R.id.button4);

        firstButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent().setClass(MainActivity.this, Activity1.class)); //finish();
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
                startActivity(new Intent().setClass(MainActivity.this, Activity3.class));
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
}
