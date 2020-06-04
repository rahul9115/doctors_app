package com.example.bookdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

public class DashboardActivity extends AppCompatActivity {
    VideoView videoView;
    MediaController mediaController;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Button button = findViewById(R.id.play);
        videoView = (VideoView) findViewById(R.id.videoView1);
        mediaController = (MediaController) new MediaController(this);
        linearLayout=findViewById(R.id.schedule_appointment);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchDoctor();
            }
        });
    }
    public void videoplay (View view)
    {
        String videopath = "android.resource://" + getPackageName() + "/raw/video";
        Uri uri = Uri.parse(videopath);
        videoView.setVideoURI(uri);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.start();
    }
    public void SearchDoctor(){
        Intent intent = new Intent(this,SearchDoctor.class);
        startActivity(intent);
    }
}
