package com.hanuappmedia.glamifyqueen.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hanuappmedia.glamifyqueen.R;
import com.hanuappmedia.glamifyqueen.databinding.OfferProductBinding;
import com.hanuappmedia.glamifyqueen.model.OfferModel;

import java.util.ArrayList;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.PopularProductViewHolder> {

    Context context;
    ArrayList<OfferModel> offerModels;

    public OfferAdapter(Context context, ArrayList<OfferModel> offerModels) {
        this.context = context;
        this.offerModels = offerModels;
    }


    @NonNull
    @Override
    public PopularProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_product, parent, false);
        return new PopularProductViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PopularProductViewHolder holder, int position) {
        OfferModel productModel = offerModels.get(position);
        holder.binding.name.setText(productModel.getName());
        holder.binding.description.setText(productModel.getDescription());
        holder.binding.views.setText(productModel.getViews() + "k");
        holder.binding.timing.setText(productModel.getTime());
        Glide.with(context)
                .load(productModel.getImage())
                .into(holder.binding.productImage);
    }

    @Override
    public int getItemCount() {
        return offerModels.size();
    }

    public static class PopularProductViewHolder extends RecyclerView.ViewHolder {
        private final OfferProductBinding binding;

        public PopularProductViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = OfferProductBinding.bind(itemView);
        }
    }
}
