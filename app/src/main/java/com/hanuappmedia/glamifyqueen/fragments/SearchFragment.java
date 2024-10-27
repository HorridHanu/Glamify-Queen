package com.hanuappmedia.glamifyqueen.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.hanuappmedia.glamifyqueen.adapter.RecommendAdapter;
import com.hanuappmedia.glamifyqueen.databinding.FragmentSearchBinding;
import com.hanuappmedia.glamifyqueen.model.RecommendModel;
import com.hanuappmedia.glamifyqueen.utilities.Constants;
import com.hanuappmedia.glamifyqueen.utilities.GridSpacingItemDecoration;

import java.util.ArrayList;
import androidx.recyclerview.widget.GridLayoutManager;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;
    private ArrayList<RecommendModel> recommendModels;
    private RecommendAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recommendModels = new ArrayList<>();
        adapter = new RecommendAdapter(getContext(), recommendModels);

        // Set LayoutManager and attach adapter
        binding.searchRecycler.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        binding.searchRecycler.addItemDecoration(new GridSpacingItemDecoration(2, 18));
        binding.searchRecycler.setAdapter(adapter);

        setupSearch();
    }

    private void setupSearch() {
        binding.searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString().trim();
                if (query.isEmpty()) {
                    clearSearchResults();
                } else {
                    searchProducts(query);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void clearSearchResults() {
        Log.d("SearchFragment", "Clearing search results");
        recommendModels.clear();
        adapter.notifyDataSetChanged();
        showProduct(false); // Hide RecyclerView and show search badge
    }

    @SuppressLint("NotifyDataSetChanged")
    private void searchProducts(String query) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String lowerCaseQuery = query.toLowerCase(); // Convert query to lowercase for case-insensitive search

        db.collection(Constants.RECOMMENDED_COLLECTION)
                .get() // Fetch all documents
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        recommendModels.clear(); // Clear old results
                        QuerySnapshot documents = task.getResult();

                        if (documents != null) {
                            for (QueryDocumentSnapshot document : documents) {
                                RecommendModel product = document.toObject(RecommendModel.class);
                                // Add product if its name matches the query
                                if (product.getName().toLowerCase().contains(lowerCaseQuery)) {
                                    recommendModels.add(product);
                                    Log.d("SearchFragment", "Product added: " + product.getName());
                                }
                            }
                            adapter.notifyDataSetChanged();
                            showProduct(!recommendModels.isEmpty());
                        } else {
                            showProduct(false);
                        }
                    } else {
                        Log.e("SearchFragment", "Error: " + task.getException());
                        Toast.makeText(getContext(), "Error: " + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showProduct(boolean show) {
        binding.searchBadge.setVisibility(show ? View.GONE : View.VISIBLE);
        binding.searchRecycler.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
