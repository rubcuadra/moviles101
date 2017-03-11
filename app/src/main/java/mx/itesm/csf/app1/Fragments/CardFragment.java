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
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import mx.itesm.csf.app1.Adapters.AutosAdapter;
import mx.itesm.csf.app1.Adapters.CardAdapter;
import mx.itesm.csf.app1.Models.Auto;
import mx.itesm.csf.app1.Models.Card;
import mx.itesm.csf.app1.R;
import mx.itesm.csf.app1.Requester;
import mx.itesm.csf.app1.Utils.JSONParser;

/**
 * Created by rubcuadra on 3/9/17.
 */

public class CardFragment extends Fragment
{
    private CardFragment.OnCardListFragmentInteractionListener mListener;
    private Activity CONTEXT;
    private RecyclerView mRecyclerView;
    private CardAdapter mCardAdapter;

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
        //DUMMY CONTENT
        List<Card> cards = new ArrayList<>();
        Card c = new Card();
        for (int i = 0; i < 10; i++)
        {
            c.setAuto( String.format("test%s",i) ) ;
            c.setMarca( String.valueOf(i) );
            cards.add(c);
        }

        mCardAdapter.addCards(cards);
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
