package mx.itesm.csf.app1.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.itesm.csf.app1.Adapters.CardAdapter;
import mx.itesm.csf.app1.Models.Card;
import mx.itesm.csf.app1.R;
import mx.itesm.csf.app1.Requester;

import static mx.itesm.csf.app1.Activities.LoginActivity.AUTH_HEADER;

/**
 * Created by rubcuadra on 3/9/17.
 */

public class CardFragment extends Fragment
{
    private CardFragment.OnCardListFragmentInteractionListener mListener;
    private Activity CONTEXT;
    private RecyclerView mRecyclerView;
    private CardAdapter mCardAdapter;
    private static final String SERVICIO_AUTOS = "http://ubiquitous.csf.itesm.mx/~pddm-1019102/content/parcial2/ejercicios/060317/servicio.autos.php";

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CardFragment() {}
    public static CardFragment newInstance()
    {
        CardFragment fragment = new CardFragment();
        Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        //fragment.setArguments(args);
        return fragment;
    }
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
        View view = inflater.inflate(R.layout.fragment_cards_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_cards);
        mCardAdapter = new CardAdapter(CONTEXT,new ArrayList<Card>());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(CONTEXT));
        mRecyclerView.setAdapter( mCardAdapter );
        loadCards();
        return view;
    }


    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof CardFragment.OnCardListFragmentInteractionListener )
        {
            mListener = (CardFragment.OnCardListFragmentInteractionListener ) context;
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

    void loadCards()
    {

        String url = SERVICIO_AUTOS; //+ algo?
        JsonArrayRequest rq = new JsonArrayRequest(Request.Method.GET,url, null ,new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response)
            {
                List<Card> cards = new ArrayList<>();
                try
                {
                    JSONObject codes = response.getJSONObject(0);
                    if (codes.getInt("Codigo") == 01) //Exito
                    {
                        for (int i = 1; i < response.length(); i++) //De 1 por que 0 son codigos
                        {
                            JSONObject current = response.getJSONObject(i);
                            Card c = new Card();
                            c.setAuto( current.getString("Nombre")  ) ;
                            c.setPrecio( current.getString("Precio") );
                            c.setImage( current.getString("imagen") );
                            c.setId( current.getInt("Clave_auto") );
                            c.setClave_marca( current.getInt("Clave_marca"));
                            //Log.d("CardFrag",c.toString());
                            cards.add(c);
                        }
                        mCardAdapter.addCards(cards);
                    }
                    else
                    {
                        Toast.makeText(CONTEXT,response.toString(),Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                error.printStackTrace();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", AUTH_HEADER);
                return headers;
            }
        };

        Requester.getInstance().addToRequestQueue(rq);
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
    public interface OnCardListFragmentInteractionListener
    {
        void OnCardListFragmentInteraction(Card c);
    }
}
