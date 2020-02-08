package com.example.wowtimedemo;


import android.os.Bundle;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;



public class PlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    String TAG=">>>>",videoId,title,date,channelName,description;

    public  static  final  String API_KEY="AIzaSyAQvfBHq_cAHSr8LYapwayHuWWtUTUCecw";

    TextView titleTextView, dateTextView, channelNameTextView, descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        //get values from intent
        videoId=getIntent().getStringExtra("videoId");
        title=getIntent().getStringExtra("title");
        date=getIntent().getStringExtra("date");
        channelName=getIntent().getStringExtra("channelName");
        description=getIntent().getStringExtra("description");

        //initialize youtube player
        YouTubePlayerView playerView=findViewById(R.id.player_view);
        playerView.initialize(API_KEY, this);

        //init textviews
        titleTextView=findViewById(R.id.title);
        dateTextView=findViewById(R.id.date);
        channelNameTextView=findViewById(R.id.channel_name);
        descriptionTextView=findViewById(R.id.description);


        //setting values on TextViews
        titleTextView.setText(title);
        dateTextView.setText(date);
        channelNameTextView.setText(channelName);
        descriptionTextView.setText(description);


    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        youTubePlayer.setPlayerStateChangeListener(playerStateChangedListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);

        if(!b){
            youTubePlayer.cueVideo(videoId);
        }

    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {

        }

        @Override
        public void onPaused() {

        }

        @Override
        public void onStopped() {

        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };

    private YouTubePlayer.PlayerStateChangeListener playerStateChangedListener= new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {

        }

        @Override
        public void onVideoStarted() {

        }

        @Override
        public void onVideoEnded() {

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
