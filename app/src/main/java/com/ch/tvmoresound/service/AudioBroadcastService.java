package com.ch.tvmoresound.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.audiofx.Visualizer;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by chenhao on 2018/2/24.
 */

public class AudioBroadcastService extends IntentService {

    private static final String TAG = "AudioBroadcastService";

    private Visualizer audioRecord;

    private AudioPlayerThread audioPlayerThread;

    private boolean recording;

    public AudioBroadcastService(){
        super("AudioBroadcastService");
        Log.i(TAG, "AudioBroadcastService --------->>>>>>>>>>>>>>>>>>>>>>>>>>");
        try {
            //int bufferSize = AudioRecord.getMinBufferSize(44100, AudioFormat.CHANNEL_IN_STEREO, AudioFormat.ENCODING_PCM_16BIT);
            //Log.i(TAG, "bufferSize:"+bufferSize);
            audioRecord =  new Visualizer(0);
            audioRecord.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);
            audioPlayerThread=new AudioPlayerThread();
            audioPlayerThread.start();
            audioRecord.setDataCaptureListener(new Visualizer.OnDataCaptureListener() {
                @Override
                public void onWaveFormDataCapture(Visualizer visualizer, byte[] waveform, int samplingRate) {
                    //Log.i(TAG, "---------------onWaveFormDataCapture-------->get data now");
                    //Log.i(TAG, "--------------size:"+waveform.length+",samplingRate:"+samplingRate);
                    audioPlayerThread.write(waveform);
                }
                @Override
                public void onFftDataCapture(Visualizer visualizer, byte[] fft, int samplingRate) {
                    Log.i(TAG, "--------------------onFftDataCapture--->get data now");
                }
            },Visualizer.getMaxCaptureRate(),true,false);

        }catch (Exception e){
            Log.e(TAG, "error:"+e.getMessage(),e);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate========="+this.hashCode());
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i(TAG, "onStart========="+this.hashCode());
        audioRecord.setEnabled(true);
        recording = true;
        Log.i(TAG, "start ");

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i(TAG, "onHandleIntent========="+this.hashCode());
        int i=0;
        while(true&&i<100){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            i++;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(recording) {
            audioRecord.release();
        }
        audioRecord.release();
        audioRecord=null;
        audioPlayerThread.stopPlay();
        Log.i(TAG, "onDestroy========="+this.hashCode());
    }
}
