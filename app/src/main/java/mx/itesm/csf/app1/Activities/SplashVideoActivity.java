package mx.itesm.csf.app1.Activities;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.VideoView;

import mx.itesm.csf.app1.R;

public class SplashVideoActivity extends AppCompatActivity {

    private VideoView mVideo;
    private static final String TAG = "VideoSplash";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_video);

        //Hide navigation Bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        mVideo = (VideoView) findViewById(R.id.splashVideo);
        Uri video = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.ad13);
        mVideo.setVideoURI( video );

        mVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mp)
            {
                finish();
            }
        });
        mVideo.start();
    }
}
