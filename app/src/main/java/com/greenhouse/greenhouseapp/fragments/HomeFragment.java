package com.greenhouse.greenhouseapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.greenhouse.greenhouseapp.databinding.HomeLayoutBinding;
import com.greenhouse.greenhouseapp.helpers.MqttHelper;

public class HomeFragment extends Fragment {
    private HomeLayoutBinding binding;
    private MqttHelper mqttHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = HomeLayoutBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        mockupInitiateUI();

        binding.humidityButton.performClick();

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

    public void updateUI_whenMessageReceived(String text){
        if(getActivity()!=null){
            getActivity().runOnUiThread(()->binding.humidityButton.setText(text));
        }
    }
}
