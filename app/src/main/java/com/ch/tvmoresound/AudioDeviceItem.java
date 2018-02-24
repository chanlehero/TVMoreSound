package com.ch.tvmoresound;

/**
 * Created by chenhao on 2018/2/24.
 */

public class AudioDeviceItem {

    private String name;
    private String macAddress;
    boolean checked;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
