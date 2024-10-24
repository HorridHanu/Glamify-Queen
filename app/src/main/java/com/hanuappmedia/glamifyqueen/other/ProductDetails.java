package com.hanuappmedia.glamifyqueen.other;


import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.hanuappmedia.glamifyqueen.databinding.ActivityProductDetailsBinding;
import com.hanuappmedia.glamifyqueen.model.RecommendModel;


public class ProductDetails extends AppCompatActivity {
    private ActivityProductDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }

    private void setListeners() {
        getBundle();
        binding.backIcon.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());

    }

    @SuppressLint("SetTextI18n")
    private void getBundle() {
        RecommendModel recommendModel = (RecommendModel) getIntent().getSerializableExtra("object");
        if (recommendModel != null) {
            // Access fields here
            binding.productPrice.setText("$" + recommendModel.getPrice());

            double price = recommendModel.getPrice();
            double doublePrice = price * 1.29;
            @SuppressLint("DefaultLocale") String formattedPrice = String.format("%.2f", doublePrice);
            binding.actualPrice.setText("$"+formattedPrice);
            binding.actualPrice.setPaintFlags(binding.actualPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            Glide.with(getApplicationContext())
                    .load(recommendModel.getImage())
                    .into(binding.productImage);

            binding.productName.setText(recommendModel.getName());
            binding.ratingText.setText(""+ recommendModel.getRating());
            binding.productDescription.setText(recommendModel.getDescription());
            binding.rating.setRating((float) recommendModel.getRating());
        } else {
            // Handle case where recommendModel is null
            showToast("RecommendModel is null");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}