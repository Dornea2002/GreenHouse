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
import com.greenhouse.greenhouseapp.databinding.PlantLayoutBinding;
import com.greenhouse.greenhouseapp.helpers.MqttHelper;

public class PlantFragment extends Fragment {
    private PlantLayoutBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = PlantLayoutBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        mockupInitiateUI();

        return  view;
    }

    private void mockupInitiateUI(){

    }
}
