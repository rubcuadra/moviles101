package mx.itesm.csf.app1.Models;

import android.os.Bundle;

/**
 * Created by rubcuadra on 2/2/17.
 */

public class Post
{
    private String title;
    private String content;

    public Post(){}
    public Post(String title, String content)
    {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static Bundle asBundle(Post p)
    {
        Bundle b = new Bundle();
        b.putString("title",p.title);
        b.putString("content",p.content);
        return b;
    }

    public static Post fromBundle(Bundle b)
    {
        Post p = new Post();
        p.setTitle( b.getString("title") );
        p.setContent( b.getString("content") );
        return p;
    }

}
