package mx.itesm.csf.app1.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import mx.itesm.csf.app1.Activities.AutoDetailActivity;
import mx.itesm.csf.app1.Activities.CardDetailActivity;
import mx.itesm.csf.app1.Models.Auto;
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
        holder.marca.setText(current.getPrecio());
        holder.img.setImageURI( Uri.parse( current.getImage() ) );
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("ADAPTER",current.toString() );
                Intent intent = new Intent(activity,CardDetailActivity.class);
                intent.putExtras( Card.asBundle(current) );
                activity.startActivity(intent);
            }
        });
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
        protected SimpleDraweeView img;

        public ViewHolder(View view)
        {
            super(view);
            title = (TextView) view.findViewById( R.id.card_list_title);
            marca = (TextView) view.findViewById( R.id.card_list_marca);
            img = (SimpleDraweeView) view.findViewById( R.id.card_list_image );
        }
    }
}
