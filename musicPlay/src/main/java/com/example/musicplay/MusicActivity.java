package com.example.musicplay;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MusicActivity extends AppCompatActivity {

    private ProgressBar mpb;
    private ImageView mBegin;
    private ImageView mRed;
    private ImageView mGray;
    private ImageView mStop;
    private ImageView mMusicPic,back;
    Animation rotate;

    private MediaPlayer mMediaPlayer;
    /**
     * 00:00
     */
    private TextView mMusicTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        mMediaPlayer = MediaPlayer.create(this, R.raw.music1);

        mBegin = findViewById(R.id.begin);
        mBegin.setOnClickListener(starPlay);

        mStop = findViewById(R.id.stop);
        mStop.setOnClickListener(stopPlay);

        mRed = findViewById(R.id.red);
        mRed.setOnClickListener(redTogray);

        mGray = findViewById(R.id.gray);
        mGray.setOnClickListener(grayTored);

        mMusicPic = findViewById(R.id.music_pic);
        //使用AnimationUtils类的静态方法loadAnimation()来加载XML中的动画XML文件
        rotate = AnimationUtils.loadAnimation(this, R.anim.anim);

        mpb = findViewById(R.id.mpb);
        mMusicTime = findViewById(R.id.music_time);

        back = findViewById(R.id.back);
        back.setOnClickListener(backTohome);
    }

    private View.OnClickListener starPlay = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mMediaPlayer.start();
            mMusicPic.startAnimation(rotate);
            mBegin.setVisibility(View.GONE);
            mStop.setVisibility(View.VISIBLE);
            handler.sendEmptyMessage(0);
        }
    };

    private View.OnClickListener stopPlay = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            mMediaPlayer.pause();

            mMusicPic.setAnimation(rotate);
            mMusicPic.startAnimation(rotate);
            mMusicPic.clearAnimation();

            mBegin.setVisibility(View.VISIBLE);
            mStop.setVisibility(View.GONE);
            handler.sendEmptyMessage(1);
        }
    };

    private View.OnClickListener redTogray = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mRed.setVisibility(View.GONE);
            mGray.setVisibility(View.VISIBLE);
        }
    };

    private View.OnClickListener grayTored = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mGray.setVisibility(View.GONE);
            mRed.setVisibility(View.VISIBLE);
        }
    };

    private View.OnClickListener backTohome = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mMediaPlayer.pause();

            mMusicPic.setAnimation(rotate);
            mMusicPic.startAnimation(rotate);
            mMusicPic.clearAnimation();

            mBegin.setVisibility(View.VISIBLE);
            mStop.setVisibility(View.GONE);
            handler.sendEmptyMessage(1);

            Intent intent=new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }
    };

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if (mpb.getProgress() < 100) {
                        handler.postDelayed(runnable, 600);
                        if (mpb.getProgress() % 60 < 10){
                            mMusicTime.setText("0" + mpb.getProgress() / 60 + ":0" + mpb.getProgress() % 60);
                        }
                        else {
                            mMusicTime.setText("0" + mpb.getProgress() / 60 + ":" + mpb.getProgress() % 60);
                        }
                    } else {
                        mpb.setProgress(0);
                        mMusicTime.setText("00:00");
                        handler.sendEmptyMessage(0);
                    }
                    break;
                case 1:
                    handler.removeCallbacks(runnable);
                    break;
            }

        }
    };

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mpb.setProgress(mpb.getProgress() + 1);
            handler.sendEmptyMessage(0);
        }
    };

}