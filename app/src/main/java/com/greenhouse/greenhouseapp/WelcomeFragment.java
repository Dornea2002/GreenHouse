package com.greenhouse.greenhouseapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class WelcomeFragment extends Fragment {
    private Button getStartedButton;
    private TextView textView;

    public WelcomeFragment() {
        super(R.layout.welcome_layout);
    }

    @SuppressLint("ShowToast")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Log.d("Here", "Here in onViewCreated");

        super.onViewCreated(view, savedInstanceState);

        initiateUI(view);
        setListeners(view);
    }

    private void initiateUI(View view){
        getStartedButton = view.findViewById(R.id.get_started_button);
        textView = view.findViewById(R.id.property_text);
    }

    @SuppressLint("SetTextI18n")
    private void setListeners(View view){
        Log.d("Here", "In listeners:   " + view.toString());
        getStartedButton.setOnClickListener(v -> {
            textView.setText("Ai apasat FRAERE");
        });
    }

}
