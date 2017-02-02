package mx.itesm.csf.app1.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mx.itesm.csf.app1.Adapters.PostAdapter;
import mx.itesm.csf.app1.Models.Post;
import mx.itesm.csf.app1.R;
import mx.itesm.csf.app1.Requester;

public class ActivityPostsList extends AppCompatActivity
{
    private RecyclerView mRecyclerView;
    private static final String api_base=  "http://ubiquitous.csf.itesm.mx/~pddm-1019102/CMS/wp-json/wp/v2/";
    private static final String posts_endpoint =  "posts";
    private static final String posts_endpoint_args =  "?fields=title,content";
    private PostAdapter mPostsAdapter;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_posts);
        mPostsAdapter = new PostAdapter(this,new ArrayList<Post>());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter( mPostsAdapter );
        getPosts();
    }
    public void getPosts()
    {
        final String endpoint = api_base+posts_endpoint+posts_endpoint_args;
        StringRequest request = new StringRequest(Request.Method.GET, endpoint, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    List<Post> psts = new ArrayList<>();
                    String json = new String(response.getBytes(), "UTF-8");
                    JSONArray posts = new JSONArray(json);
                    for (int i = 0; i < posts.length(); i++)
                    {
                        JSONObject p = posts.getJSONObject(i);
                        String title = p.getJSONObject("title").getString("rendered");
                        String description = "";
                        psts.add(new Post(title,description));
                        Log.d("JSON",title);
                    }
                    mPostsAdapter.addPosts(psts);
                }
                catch(Exception e)
                {
                    Log.d("JSON",e.toString());
                }
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                VolleyLog.e(error.toString());
            }
        });

        Requester.getInstance().addToRequestQueue(request);
    }

}
