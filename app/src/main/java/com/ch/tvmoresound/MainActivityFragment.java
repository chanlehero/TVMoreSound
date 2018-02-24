package com.ch.tvmoresound;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.media.MediaControlIntent;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.ch.tvmoresound.serivce.AudioMediaRouteProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends ListFragment {

    private static final String TAG = "MainActivityFragment";

    private boolean scanning = false;
    private ArrayAdapter<AudioDeviceItem> deviceItemArrayAdapter;
    private List<AudioDeviceItem>  audioDeviceItems = new ArrayList<AudioDeviceItem>();
    private BluetoothAdapter bluetoothAdapter;
    private int REQUEST_ENABLE_BT=101;

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Log.i(TAG,"found device:"+device.getName()+":"+device.getAddress());
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    Log.i(TAG,"add:"+device.getName()+":"+device.getAddress());
                    AudioDeviceItem item = new AudioDeviceItem();
                    item.setName(device.getName());
                    item.setMacAddress(device.getAddress());
                    item.setChecked(false);
                    deviceItemArrayAdapter.add(item);
                }else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                    Log.i(TAG,"ACTION_DISCOVERY_FINISHED=========");
                    setScanButtonText(R.string.action_scan_audio_devices_button_label);
                }
                // Add the name and address to an array adapter to show in a ListView
                //mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Button scanButton = (Button)this.getActivity().findViewById(R.id.button_scan_devices);
        bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        if(bluetoothAdapter==null){
            scanButton.setClickable(false);
            Toast.makeText(this.getContext(),"不支持蓝牙！",Toast.LENGTH_SHORT).show();
            return;
        }
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(scanning){
                    scanning=false;
                    bluetoothAdapter.cancelDiscovery();
                    scanButton.setText(R.string.action_scan_audio_devices_button_label);
                }else {
                    scanning=true;
                    //deviceItemArrayAdapter.clear();
                    Toast.makeText(MainActivityFragment.this.getContext(),"扫描音频设置中...",Toast.LENGTH_SHORT).show();
                    scanButton.setText(R.string.action_scan_audio_devices_button_scanning_label);
                    if(bluetoothAdapter.isDiscovering()){
                    bluetoothAdapter.cancelDiscovery();
                    }
                    bluetoothAdapter.startDiscovery();
                }

            }
        });
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
        Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();
        for (BluetoothDevice device:devices) {
            AudioDeviceItem item = new AudioDeviceItem();
            item.setName(device.getName());
            item.setMacAddress(device.getAddress());
            item.setChecked(false);
            deviceItemArrayAdapter.add(item);
        }
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        this.getActivity().registerReceiver(mReceiver, filter);
        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        this.getActivity().registerReceiver(mReceiver, filter); // Don't forget to unregister during onDestroy
    }

    public void setScanButtonText(int str_id){
         Button scanButton = (Button)this.getActivity().findViewById(R.id.button_scan_devices);
         scanButton.setText(str_id);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bluetoothAdapter != null) {
            bluetoothAdapter.cancelDiscovery();
        }
        // Unregister broadcast listeners
        this.getActivity().unregisterReceiver(mReceiver);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deviceItemArrayAdapter=new ArrayAdapter<AudioDeviceItem>(this.getContext(),R.layout.audio_device_item,R.id.audio_device_item_textView,audioDeviceItems);
        this.setListAdapter(deviceItemArrayAdapter);
    }
}
