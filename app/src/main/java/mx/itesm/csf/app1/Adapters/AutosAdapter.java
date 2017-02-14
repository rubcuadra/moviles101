package mx.itesm.csf.app1.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.List;

import mx.itesm.csf.app1.Activities.AutoDetailActivity;
import mx.itesm.csf.app1.Models.Auto;
import mx.itesm.csf.app1.R;

/**
 * Created by rubcuadra on 2/13/17.
 */

public class AutosAdapter extends RecyclerView.Adapter<AutosAdapter.ViewHolder>
{
    private List<Auto> autos;
    private Activity activity;

    public AutosAdapter(Activity activity, List<Auto> autos)
    {
        this.autos = autos;
        this.activity = activity;
    }

    public void addAutos(List<Auto> autos)
    {
        this.autos.addAll(autos);
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_auto,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        final Auto current = autos.get(position);
        holder.title.setText(current.getAuto());
        holder.marca.setText(current.getMarca());
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("ADAPTER",current.toString() );
                Intent intent = new Intent(activity,AutoDetailActivity.class);
                intent.putExtras( Auto.asBundle(current) );
                activity.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount()
    {
        return autos.size();
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
            title = (TextView) view.findViewById( R.id.autos_list_title);
            marca = (TextView) view.findViewById( R.id.autos_list_marca);
        }
    }
}

