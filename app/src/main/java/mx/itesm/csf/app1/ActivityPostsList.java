package mx.itesm.csf.app1;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

public class ActivityPostsList extends AppCompatActivity
{
    private RecyclerView mRecyclerView;
    private static final String api_base=  "http://ubiquitous.csf.itesm.mx/~pddm-1019102/CMS/wp-json/wp/v2/";
    private static final String posts_endpoint =  "posts";
    private List<Object> posts;
    private Gson gson;

    private Map<String,Object> mapPost;
    private Map<String,Object> mapTitle;
    private int postId;
    private String postTitle[];

    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_posts);



    }
}
