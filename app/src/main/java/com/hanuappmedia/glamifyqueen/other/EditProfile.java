package com.hanuappmedia.glamifyqueen.other;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hanuappmedia.glamifyqueen.R;
import com.hanuappmedia.glamifyqueen.databinding.ActivityEditProfileBinding;

public class EditProfile extends AppCompatActivity {

    private ActivityEditProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }

    private void setListeners() {
        binding.backIcon.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());
    }
}