package mx.itesm.csf.app1;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.VideoView;

public class SplashVideoActivity extends AppCompatActivity {

    private VideoView mVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_video);


        mVideo = (VideoView) findViewById(R.id.splashVideo);
        Uri video = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.ad13);
        mVideo.setVideoURI( video );
        mVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mp)
            {
                Log.d("ACT1","FINISHED");
                finish();
            }
        });
        mVideo.start();
    }
}
