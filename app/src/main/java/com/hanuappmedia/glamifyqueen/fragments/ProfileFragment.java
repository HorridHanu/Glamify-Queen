package com.hanuappmedia.glamifyqueen.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hanuappmedia.glamifyqueen.MainActivity;
import com.hanuappmedia.glamifyqueen.R;
import com.hanuappmedia.glamifyqueen.auth.SignIn;
import com.hanuappmedia.glamifyqueen.databinding.FragmentProfileBinding;
import com.hanuappmedia.glamifyqueen.other.EditProfile;

import java.util.Objects;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        setListeners();
        return binding.getRoot();
    }

    private void setListeners() {

        binding.editProfile.setOnClickListener(v -> startActivity(new Intent(getContext(), EditProfile.class)));

        binding.logoutAccount.setOnClickListener(v -> {
            AlertDialog dialog = new AlertDialog.Builder(requireContext())
                    .setTitle("Logout Confirmation")
                    .setMessage("Are you sure you want to log out? All unsaved changes will be lost.")
                    .setPositiveButton("Logout", (dialog12, which) -> {
                        FirebaseAuth auth = FirebaseAuth.getInstance();
                            auth.signOut();
                            Intent intent = new Intent(getActivity(), SignIn.class);
                            startActivity(intent);
                            requireActivity().finish();
                    })
                    .setNegativeButton("No", (dialog1, which) -> dialog1.dismiss())
                    .setCancelable(false)
                    .show();

            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(requireContext(), R.color.red));




        });
    }
}