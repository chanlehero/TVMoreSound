package com.ch.tvmoresound;

import android.media.MediaRouter;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends ListFragment {

    private static final String TAG = "MainActivityFragment";

    private boolean isScaning = false;

    private List<AudioMediaDeviceItem> audioMediaDeviceItemList = new ArrayList<AudioMediaDeviceItem>();

    private MediaRouter mr;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button scanButton = (Button)this.getActivity().findViewById(R.id.button_scan_devices);
        if(scanButton!=null) {
            Log.i(TAG, "button is not null");
            scanButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button button = (Button) view;
                    Log.i(TAG, "start to scan audio devices");
                    if (isScaning) {
                        isScaning = false;
                        button.setText(R.string.action_scan_audio_devices_button_label);

                    } else {
                        isScaning = true;
                        button.setText(R.string.action_scan_audio_devices_button_scanning_label);
                        mr.addCallback(MediaRouter.ROUTE_TYPE_LIVE_AUDIO, new MediaRouter.Callback() {
                            @Override
                            public void onRouteSelected(MediaRouter mediaRouter, int i, MediaRouter.RouteInfo routeInfo) {
                                Log.i(TAG, "onRouteSelected");
                            }

                            @Override
                            public void onRouteUnselected(MediaRouter mediaRouter, int i, MediaRouter.RouteInfo routeInfo) {
                                Log.i(TAG, "onRouteSelected");
                            }

                            @Override
                            public void onRouteAdded(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
                                Log.i(TAG, "onRouteSelected");
                            }

                            @Override
                            public void onRouteRemoved(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
                                Log.i(TAG, "onRouteSelected");
                            }

                            @Override
                            public void onRouteChanged(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
                                Log.i(TAG, "onRouteSelected");
                            }

                            @Override
                            public void onRouteGrouped(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo, MediaRouter.RouteGroup routeGroup, int i) {
                                Log.i(TAG, "onRouteSelected");
                            }

                            @Override
                            public void onRouteUngrouped(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo, MediaRouter.RouteGroup routeGroup) {
                                Log.i(TAG, "onRouteSelected");
                            }

                            @Override
                            public void onRouteVolumeChanged(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
                                Log.i(TAG, "onRouteSelected");
                            }
                        });
                    }

                }
            });
        }

    }




}
