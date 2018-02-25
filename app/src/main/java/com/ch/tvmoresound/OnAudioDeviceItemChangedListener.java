package com.ch.tvmoresound;

/**
 * Created by chenhao on 2018/2/25.
 */

public interface OnAudioDeviceItemChangedListener {

    public void onAudioDeviceItemSwitchOn(AudioDeviceItem item);

    public void onAudioDeviceItemSwitchOff(AudioDeviceItem item);


}
