package mx.itesm.csf.app1.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.itesm.csf.app1.Models.Post;
import mx.itesm.csf.app1.R;

public class PostDetailActivity extends AppCompatActivity
{
    @BindView(R.id.post_webView) WebView mWebView;
    @BindView(R.id.post_title) TextView mPostTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        ButterKnife.bind(this);
        Post post = Post.fromBundle(getIntent().getExtras());
        mPostTitle.setText(post.getTitle());
        mWebView.loadData(post.getContent(),"text/html","UTF-8");
    }
}
