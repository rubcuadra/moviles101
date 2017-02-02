package mx.itesm.csf.app1.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import java.util.List;

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

    public void reset()
    {
        posts.clear();
        notifyDataSetChanged();
    }

    public void addPosts(List<Post> posts_list)
    {
        this.posts.addAll(posts_list);
        notifyDataSetChanged();
    }
    public void addPost(Post post)
    {
        this.posts.add(post);
        notifyDataSetChanged();
    }
    @Override
    public int getItemViewType(int position)
    {
        return R.layout.item_post;
    }

    @Override
    public int
    getItemCount()
    {return posts.size();}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_post, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Post current = posts.get(position);
        holder.title.setText(current.getTitle());
        Log.d("Adding",posts.get(position).getTitle());
    }

    /**
     * View holder to display each RecylerView item
     */
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        protected TextView title;
        protected WebView webView;

        public ViewHolder(View view)
        {
            super(view);
            title = (TextView) view.findViewById( R.id.post_title);
            webView = (WebView) view.findViewById(R.id.posdt_webView);
        }
    }
}
