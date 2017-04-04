package mx.itesm.csf.app1.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mx.itesm.csf.app1.Models.Marca;
import mx.itesm.csf.app1.R;

/**
 * Created by rubcuadra on 4/3/17.
 */

public class MarcasAdapter extends RecyclerView.Adapter<MarcasAdapter.ViewHolder>
{
    private List<Marca> cards;
    private Activity activity;

    public MarcasAdapter(Activity activity, List<Marca> cds)
    {
        this.cards = cds;
        this.activity = activity;
    }

    public void addMarcas(List<Marca> cs)
    {
        this.cards.addAll(cs);
        notifyDataSetChanged();
    }


    @Override
    public MarcasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_marca,parent, false);
        return new MarcasAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MarcasAdapter.ViewHolder holder, int position)
    {
        final Marca current = cards.get(position);
        holder.id.setText( String.valueOf(current.getId() ));
        holder.marca.setText( current.getName() );

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
        protected TextView id;
        protected TextView marca;

        public ViewHolder(View view)
        {
            super(view);
            id= (TextView) view.findViewById( R.id.marca_list_id);
            marca = (TextView) view.findViewById( R.id.marca_list_marca);
        }
    }
}
