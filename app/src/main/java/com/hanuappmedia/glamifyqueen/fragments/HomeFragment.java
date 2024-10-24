package com.hanuappmedia.glamifyqueen.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.hanuappmedia.glamifyqueen.R;
import com.hanuappmedia.glamifyqueen.adapter.OfferAdapter;
import com.hanuappmedia.glamifyqueen.adapter.RecommendAdapter;
import com.hanuappmedia.glamifyqueen.databinding.FragmentHomeBinding;
import com.hanuappmedia.glamifyqueen.model.OfferModel;
import com.hanuappmedia.glamifyqueen.model.RecommendModel;
import com.hanuappmedia.glamifyqueen.utilities.Constants;
import com.hanuappmedia.glamifyqueen.utilities.GridSpacingItemDecoration;
import com.hanuappmedia.glamifyqueen.utilities.PreferenceManager;
import com.hanuappmedia.glamifyqueen.utilities.ReadWriteUser;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.Objects;


public class HomeFragment extends Fragment {

    private int currentPosition = 0;
    private FragmentHomeBinding binding;
    PreferenceManager preferenceManager;
    FirebaseFirestore db;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUserDetails();
        initSlider();
        initOfferProducts();
        initRecommendedProducts();

    }

    private void initRecommendedProducts() {

        ArrayList<RecommendModel> recommendModels = new ArrayList<>();
        RecommendAdapter adapter = new RecommendAdapter(getContext(), recommendModels);

        db.collection(Constants.RECOMMENDED_COLLECTION)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        recommendModels.clear();
                        for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                            RecommendModel recommendModel = documentSnapshot.toObject(RecommendModel.class);
                            recommendModels.add(recommendModel);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
        binding.recommendedProductRecycler.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        binding.recommendedProductRecycler.addItemDecoration(new GridSpacingItemDecoration(2, 18));
        binding.recommendedProductRecycler.setAdapter(adapter);

    }

    private void initUserDetails() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(Constants.USER_COLLECTION);
        assert user != null;
        reference.child(user.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ReadWriteUser readWriteUser = snapshot.getValue(ReadWriteUser.class);
                        if (readWriteUser != null) {
                            preferenceManager = new PreferenceManager(requireContext());
                            preferenceManager.putString(Constants.KEY_PREFERENCE_NAME, readWriteUser.username);
                            binding.username.setText(preferenceManager.getString(Constants.KEY_PREFERENCE_NAME));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void initOfferProducts() {
        db = FirebaseFirestore.getInstance();

        ArrayList<OfferModel> offerModelArrayList = new ArrayList<>();
        OfferAdapter adapter = new OfferAdapter(getContext(), offerModelArrayList);


        db.collection(Constants.OFFER_COLLECTION)
                .get()
                .addOnCompleteListener(task -> {
                    offerModelArrayList.clear();
                    for (DocumentSnapshot snapshot : task.getResult()) {
                        OfferModel productModel = snapshot.toObject(OfferModel.class);
                        offerModelArrayList.add(productModel);
                        adapter.notifyDataSetChanged();
                        binding.progressCircular.setVisibility(View.INVISIBLE);
                        binding.scrollView.setVisibility(View.VISIBLE);
                    }
                });

        binding.offerRecycler.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        binding.offerRecycler.setAdapter(adapter);

        binding.nextIcon.setOnClickListener(v -> {
            if (currentPosition < Objects.requireNonNull(binding.offerRecycler.getAdapter()).getItemCount() - 1) {
                // Increment the position and scroll to the next item
                currentPosition++;
                binding.offerRecycler.smoothScrollToPosition(currentPosition);
            }
        });

        binding.backIcon.setOnClickListener(v -> {
            if (currentPosition > 0) {
                currentPosition--;
                binding.offerRecycler.smoothScrollToPosition(currentPosition);
            }
        });

    }

    private void initSlider() {
        binding.carousel.addData(new CarouselItem(getString(R.string.carousel1)));
        binding.carousel.addData(new CarouselItem(getString(R.string.carousel3)));
        binding.carousel.addData(new CarouselItem(getString(R.string.carousel2)));
    }

}