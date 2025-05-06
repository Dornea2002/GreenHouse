package com.greenhouse.greenhouseapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.greenhouse.greenhouseapp.MainActivity;
import com.greenhouse.greenhouseapp.databinding.WelcomeLayoutBinding;

public class WelcomeFragment extends Fragment {
    private WelcomeLayoutBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = WelcomeLayoutBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ((MainActivity)requireActivity()).navigateToFragment(new HomeFragment());
                ((MainActivity)requireActivity()).navigateToFragment(new PlantFragment());
            }
        });
        return view;
    }
}
