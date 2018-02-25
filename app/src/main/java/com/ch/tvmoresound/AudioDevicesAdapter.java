package com.ch.tvmoresound;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by chenhao on 2018/2/25.
 */

public class AudioDevicesAdapter extends ArrayAdapter<AudioDeviceItem> {

    private static final String TAG = "AudioDevicesAdapter";


    public AudioDevicesAdapter( Context context, int resourceLayout,int resourceTextView){
        super(context,resourceLayout,resourceTextView);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.i(TAG, "getView:"+convertView);
        if(convertView==null){
            Log.i(TAG, "getView:");
            convertView  = LayoutInflater.from(this.getContext()).inflate(R.layout.audio_device_item,null);
        }
        TextView tv = convertView.findViewById(R.id.audio_device_item_textView);


        tv.setText(this.getItem(position).toString());
        ToggleButton toggleButton = convertView.findViewById(R.id.audio_device_broadcast_switch);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    AudioDeviceItem data = AudioDevicesAdapter.this.getItem(position);
                    Log.i(TAG, "broadcast AudioDeviceItem:"+data.getName());
                }
            }
        });
        return convertView;
    }
}
