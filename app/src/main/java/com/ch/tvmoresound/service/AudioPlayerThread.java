package com.ch.tvmoresound.service;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.audiofx.Visualizer;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by chenhao on 2018/2/25.
 */

public class AudioPlayerThread extends Thread{

    private final AudioTrack audioTrack;
    private volatile boolean playing = true;
    private ConcurrentLinkedQueue<byte[]> audioDataBuffer =new ConcurrentLinkedQueue<byte[]>();
    public AudioPlayerThread(){
        audioTrack= new AudioTrack(AudioManager.STREAM_MUSIC,Visualizer.getMaxCaptureRate(),AudioFormat.CHANNEL_IN_STEREO,AudioFormat.ENCODING_PCM_8BIT,1024,AudioTrack.MODE_STREAM);
        audioTrack.play();
    }

    public void stopPlay(){
        this.playing=false;
    }

    public void write(byte[] data){
        audioDataBuffer.add(data);
    }


    @Override
    public void run() {
        while(playing){
            byte[] data = audioDataBuffer.poll();
            if(data!=null){
                audioTrack.write(data,0,data.length);
            }

        }

    }
}
