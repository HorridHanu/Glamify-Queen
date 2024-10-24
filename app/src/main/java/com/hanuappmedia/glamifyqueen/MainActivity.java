package com.hanuappmedia.glamifyqueen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.hanuappmedia.glamifyqueen.databinding.ActivityMainBinding;
import com.hanuappmedia.glamifyqueen.fragments.CartFragment;
import com.hanuappmedia.glamifyqueen.fragments.HomeFragment;
import com.hanuappmedia.glamifyqueen.fragments.ProfileFragment;
import com.hanuappmedia.glamifyqueen.fragments.SearchFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Fragment currentFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState == null) {
            currentFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_view, currentFragment, HomeFragment.class.getSimpleName()).commit();
        }

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.homeFragment) {
                replaceFragment(new HomeFragment());
            } else if (id == R.id.searchFragment) {
                replaceFragment(new SearchFragment());
            } else if (id == R.id.cartFragment) {
                replaceFragment(new CartFragment());
            } else if (id == R.id.profileFragment) {
                replaceFragment(new ProfileFragment());
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        // Hide the current fragment if it exists
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }

        // Check if the fragment is already added
        String fragmentTag = fragment.getClass().getSimpleName();
        Fragment existingFragment = getSupportFragmentManager().findFragmentByTag(fragmentTag);

        if (existingFragment == null) {
            fragmentTransaction.add(R.id.fragment_view, fragment, fragmentTag);
        } else {
            fragmentTransaction.show(existingFragment);
            fragment = existingFragment;
        }

        currentFragment = fragment;
        fragmentTransaction.commit();
    }
}
