package com.hanuappmedia.glamifyqueen.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hanuappmedia.glamifyqueen.R;
import com.hanuappmedia.glamifyqueen.databinding.RecommendedProductBinding;
import com.hanuappmedia.glamifyqueen.model.RecommendModel;
import com.hanuappmedia.glamifyqueen.other.ProductDetails;
import java.util.ArrayList;

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.RecommendViewHolder> {

    Context context;
    ArrayList<RecommendModel> recommendModels;

    public RecommendAdapter(Context context, ArrayList<RecommendModel> recommendModels) {
        this.context = context;
        this.recommendModels = recommendModels;
    }

    @NonNull
    @Override
    public RecommendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommended_product,parent,false);
        return new RecommendViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecommendViewHolder holder, int position) {
        RecommendModel recommendModel = recommendModels.get(position);
        holder.binding.productName.setText(recommendModel.getName());
        holder.binding.productDescription.setText(recommendModel.getDescription());
        holder.binding.productPrice.setText("$" + recommendModel.getPrice());

        double price = recommendModel.getPrice();
        double doublePrice = price * 1.29;

        @SuppressLint("DefaultLocale") String formattedPrice = String.format("%.2f", doublePrice);
        holder.binding.actualPrice.setText("$"+formattedPrice);

        holder.binding.actualPrice.setPaintFlags(holder.binding.actualPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        holder.binding.productRating.setText(""+recommendModel.getRating());
        Glide.with(context)
                .load(recommendModel.getImage())
                .into(holder.binding.productImage);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetails.class);
            intent.putExtra("object", recommendModels.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return recommendModels.size();
    }

    public static class RecommendViewHolder extends RecyclerView.ViewHolder{

        private RecommendedProductBinding binding;

        public RecommendViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RecommendedProductBinding.bind(itemView);
        }
    }
}
