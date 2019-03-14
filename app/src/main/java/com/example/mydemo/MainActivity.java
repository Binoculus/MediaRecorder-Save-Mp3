package com.example.mydemo;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;

public class MainActivity extends Activity{


    Button startRecording, stopRecording, PlayButton;//开始录音、停止录音
    MediaRecorder recorder;
    File audioFile; //录音保存的文件
    boolean isRecoding=false;// true 表示正在录音

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        stopRecording = (Button) this.findViewById(R.id.StopRecording);
        startRecording = (Button) this.findViewById(R.id.StartRecording);
        PlayButton = findViewById(R.id.playAui);

//        下方为录音实现
        OpenJurisdiction openJurisdiction = new OpenJurisdiction();
//        传入Activity！！！必须！！！
        openJurisdiction.open(MainActivity.this);
        final EcpMedia ecpMedia = new EcpMedia();
        ecpMedia.init();
        ecpMedia.setActivity(MainActivity.this);
        startRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ecpMedia.startin();
            }
        });

        stopRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ecpMedia.stopin();
            }
        });
    }


}
