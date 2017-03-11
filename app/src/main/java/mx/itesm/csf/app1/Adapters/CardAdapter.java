package mx.itesm.csf.app1.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import mx.itesm.csf.app1.Models.Card;
import mx.itesm.csf.app1.R;

/**
 * Created by rubcuadra on 3/9/17.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>
{
    private List<Card> cards;
    private Activity activity;

    public CardAdapter(Activity activity, List<Card> cds)
    {
        this.cards = cds;
        this.activity = activity;
    }

    public void addCards(List<Card> cs)
    {
        this.cards.addAll(cs);
        notifyDataSetChanged();
    }


    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_card,parent, false);
        return new CardAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardAdapter.ViewHolder holder, int position)
    {
        final Card current = cards.get(position);
        holder.title.setText(current.getAuto());
        holder.marca.setText(current.getMarca());
    }


    @Override
    public int getItemCount()
    {
        return cards.size();
    }

    /**
     * View holder to display each RecylerView item
     */
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        protected TextView title;
        protected TextView marca;

        public ViewHolder(View view)
        {
            super(view);
            title = (TextView) view.findViewById( R.id.card_list_title);
            marca = (TextView) view.findViewById( R.id.card_list_marca);
        }
    }
}
