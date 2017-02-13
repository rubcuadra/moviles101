package mx.itesm.csf.app1.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mx.itesm.csf.app1.Activities.PostDetailActivity;
import mx.itesm.csf.app1.Models.Post;
import mx.itesm.csf.app1.R;

/**
 * Created by rubcuadra on 2/2/17.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>
{
    private List<Post> posts;
    private Activity activity;

    public PostAdapter(Activity activity, List<Post> posts)
    {
        this.posts = posts;
        this.activity = activity;
    }

    public void addPosts(List<Post> posts_list)
    {
        this.posts.addAll(posts_list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position)
    {
        return R.layout.item_post;
    }

    @Override
    public int getItemCount() {return posts.size();}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_post, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position)
    {
        final Post current = posts.get(position);
        holder.title.setText(current.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(activity,PostDetailActivity.class);
                intent.putExtras( Post.asBundle(current) );
                activity.startActivity(intent);
            }
        });
    }
    /**
     * View holder to display each RecylerView item
     */
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        protected TextView title;

        public ViewHolder(View view)
        {
            super(view);
            title = (TextView) view.findViewById( R.id.post_list_title);
        }
    }
}
