package com.ch.tvmoresound.serivce;

import android.support.v7.media.MediaRouteProvider;
import android.support.v7.media.MediaRouteProviderService;

/**
 * Created by chenhao on 2018/2/6.
 */

public class AudioMediaRouteProviderService extends MediaRouteProviderService {
    @Override
    public MediaRouteProvider onCreateMediaRouteProvider() {
        return new AudioMediaRouteProvider(this);
    }
}
