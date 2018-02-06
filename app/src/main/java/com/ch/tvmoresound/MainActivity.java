package com.ch.tvmoresound;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.ch.tvmoresound.serivce.AudioMediaRouteProviderService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Intent audioServiceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        audioServiceIntent=new Intent(this,AudioMediaRouteProviderService.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Switch toggleButton=(Switch)menu.findItem(R.id.app_action_service_switch).getActionView().findViewById(R.id.toggle_audio_service_button);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                Log.i(TAG,"start ................isChecked:"+isChecked);
                if(isChecked){
                    startService(audioServiceIntent);
                    Log.i(TAG,"started audio service!");
                    Toast.makeText(MainActivity.this,"已开启音频服务",Toast.LENGTH_SHORT).show();
                }else {
                    stopService(audioServiceIntent);
                    Log.i(TAG,"stopped audio service!");
                    Toast.makeText(MainActivity.this,"已关闭音频服务",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
