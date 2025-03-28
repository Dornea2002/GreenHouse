package com.greenhouse.greenhouseapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.greenhouse.greenhouseapp.databinding.WelcomeLayoutBinding;

import java.util.Objects;

public class WelcomeFragment extends Fragment {
    private WelcomeLayoutBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = WelcomeLayoutBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Here", "Click");
                binding.getStartedButton.setText("CLICKKKKKKKKKK");
            }
        });

        if (binding.getStartedButton == null) {
            Log.e("WelcomeFragment", "getStartedButton is NULL!");
        } else {
            Log.d("WelcomeFragment", "Button found successfully!");
        }

        return view;
    }
}
