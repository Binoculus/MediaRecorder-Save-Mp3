package com.example.mydemo;

import android.app.Activity;
import android.media.MediaRecorder;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class EcpMedia {
    MediaRecorder recorder;
    File audioFile; //录音保存的文件
    boolean isRecoding=false;// true 表示正在录音

    Activity activity;

    public void setActivity(Activity activity){
        this.activity = activity;
    }
    //初始化
    public void init(){
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);//设置播放源 麦克风
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP); //设置输入格式 3gp
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB); //设置编码 AMR
    }


    //录音
    public void recod(){

        //此处还应该对手机进行下判断，判断下手机里面有没有内存卡
        //保存在SD卡下MediaRecorderTest文件夹中
        File path = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+ "/MediaRecorderTest");
        init();
        if(!path.exists())
        {
            path.mkdirs();
        }

        try {
            audioFile=new File(path,"test.MP3");
            if(audioFile.exists())
            {
                audioFile.delete();
            }
            audioFile.createNewFile();//创建文件

        } catch (Exception e) {
            throw new RuntimeException("Couldn't create recording audio file", e);
        }

        recorder.setOutputFile(audioFile.getAbsolutePath()); //设置输出文件

        try {
            recorder.prepare();
        } catch (IllegalStateException e) {
            throw new RuntimeException("IllegalStateException on MediaRecorder.prepare", e);
        } catch (IOException e) {
            throw new RuntimeException("IOException on MediaRecorder.prepare", e);
        }
        isRecoding=true;
        recorder.start();
    }


    /**
     * 开始停止录音
     * startin为开始录音
     * stop为停止录音
     */
    public void startin(){
        Toast.makeText(activity,"开始录音",Toast.LENGTH_SHORT).show();
        this.recod();
    }
    public void stopin(){
        if(isRecoding)
        {
            Toast.makeText(activity,"停止录音",Toast.LENGTH_SHORT).show();
            if (recorder != null){
                try {
                    recorder.stop();
                } catch (IllegalStateException e) {
                    // TODO 如果当前java状态和jni里面的状态不一致，
                    //e.printStackTrace();
                    recorder = null;
                    recorder = new MediaRecorder();
                }
                recorder.release();
                recorder = null;
            }
        }
    }
}
