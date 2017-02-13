package mx.itesm.csf.app1.Activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.itesm.csf.app1.Adapters.AutosAdapter;
import mx.itesm.csf.app1.Fragments.AutoFragment;
import mx.itesm.csf.app1.Models.Auto;
import mx.itesm.csf.app1.R;

public class AutosListActivity extends AppCompatActivity implements AutoFragment.OnAutosListFragmentInteractionListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autos);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.autos_activity_container);

        if (fragment == null)
        {
            fragment = new AutoFragment();
            fm.beginTransaction().add(R.id.autos_activity_container, fragment).commit();
        }
    }

    @Override
    public void OnAutosListFragmentInteractionListener(Auto auto)
    {
        Log.d("ACTIVIDAD FRAGMENTO","ME PASARON UN AUTO");
    }
}
