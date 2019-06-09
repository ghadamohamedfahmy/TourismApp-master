package com.somesh.android.bhopaldarshan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class showvideo extends YouTubeBaseActivity {
YouTubePlayerView mYouTubePlayerView;
    private static final String TAG = "showvideo";
    YouTubePlayer.OnInitializedListener mOnInitializedListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showvideo);
        Log.d(TAG,"onCreate Starting");
        mYouTubePlayerView=  (YouTubePlayerView)findViewById(R.id.youtubeplayer);
        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d(TAG,"intialization player done");
                youTubePlayer.loadVideo("-QxhPjMso88");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d(TAG,"intialization player failed");
            }
        };
        Log.d(TAG,"intialization player Starting");
        mYouTubePlayerView.initialize(YouTubeConfig.getApiKey(),mOnInitializedListener);

    }
}
