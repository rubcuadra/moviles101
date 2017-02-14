package mx.itesm.csf.app1.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import mx.itesm.csf.app1.Adapters.AutosAdapter;
import mx.itesm.csf.app1.Models.Auto;
import mx.itesm.csf.app1.R;
import mx.itesm.csf.app1.Requester;
import mx.itesm.csf.app1.Utils.JSONParser;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnAutosListFragmentInteractionListener}
 * interface.
 */
public class AutoFragment extends Fragment
{
    private OnAutosListFragmentInteractionListener mListener;
    private static String url_array = "http://ubiquitous.csf.itesm.mx/~pddm-1019102/content/parcial1/ejercicios/020217/servicio.multiplesAutos.php";
    private Activity CONTEXT;
    private RecyclerView mRecyclerView;
    private AutosAdapter mAutosAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AutoFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        CONTEXT = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_auto_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_autos);
        mAutosAdapter = new AutosAdapter(CONTEXT,new ArrayList<Auto>());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(CONTEXT));
        mRecyclerView.setAdapter( mAutosAdapter );
        loadAutos();
        return view;
    }


    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof OnAutosListFragmentInteractionListener)
        {
            mListener = (OnAutosListFragmentInteractionListener) context;
        } else
        {
            throw new RuntimeException(context.toString()+" must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    void loadAutos()
    {
        final ProgressDialog barraDeProgreso = new ProgressDialog(CONTEXT);
        barraDeProgreso.setMessage("Espera...");
        barraDeProgreso.show();
        JsonArrayRequest jsonArr = new JsonArrayRequest(Request.Method.GET,url_array,null,new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response)
            {
                mAutosAdapter.addAutos(JSONParser.parseaArreglo(response));
                barraDeProgreso.hide();
            }

        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                error.printStackTrace();
                barraDeProgreso.hide();
            }
        });

        Requester.getInstance().addToRequestQueue(jsonArr);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnAutosListFragmentInteractionListener
    {
        void OnAutosListFragmentInteractionListener(Auto auto);
    }
}
