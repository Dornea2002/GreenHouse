package com.greenhouse.greenhouseapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.greenhouse.greenhouseapp.databinding.HomeLayoutBinding;
import com.greenhouse.greenhouseapp.helpers.MqttHelper;

public class HomeFragment extends Fragment {
    Toolbar homeToolbar;
    Button humidityButton;
    Button temperatureButton;
    Button lightButton;
    Button soilButton;
    private HomeLayoutBinding binding;
    private MqttHelper mqttHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = HomeLayoutBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        if(view == null){
            Log.d("mqtt", "View is null");
        } else Log.d("mqtt", "View is not null");

        mockupInitiateUI();

        binding.humidityButton.performClick();

        initiateCommunicationConnection();

        return  view;
    }

    private void mockupInitiateUI(){
        binding.humidityButton.setText("Led");
        binding.humidityButton.setOnClickListener(view -> {
            if(view == null){
                Log.d("mqtt", "View is null");
            } else Log.d("mqtt", "I know View is not null");
        });
    }

    private void initiateCommunicationConnection(){
        mqttHelper = new MqttHelper(this);
        mqttHelper.connect();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
                mqttHelper.subscribe();
//                mqttHelper.publish("Salut, RabbitMQ!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void updateUI_whenMessageReceived(String text){
        if(getActivity()!=null){
            getActivity().runOnUiThread(()->binding.humidityButton.setText(text));
        }
    }
}
