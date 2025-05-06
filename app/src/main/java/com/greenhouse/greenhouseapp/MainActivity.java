package com.greenhouse.greenhouseapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.greenhouse.greenhouseapp.databinding.ActivityMainBinding;
import com.greenhouse.greenhouseapp.fragments.HomeFragment;
import com.greenhouse.greenhouseapp.fragments.WelcomeFragment;
import com.greenhouse.greenhouseapp.helpers.MqttHelper;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MqttHelper mqttHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initiateCommunicationConnection();
        navigateToFragment(new WelcomeFragment());
    }

    public void navigateToFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment_container, fragment);
        fragmentTransaction.commit();
    }

    private void initiateCommunicationConnection(){
        mqttHelper = new MqttHelper();
        mqttHelper.connect();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
                mqttHelper.subscribe();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}