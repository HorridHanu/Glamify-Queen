package com.hanuappmedia.glamifyqueen.auth;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.hanuappmedia.glamifyqueen.MainActivity;
import com.hanuappmedia.glamifyqueen.R;
import com.hanuappmedia.glamifyqueen.databinding.ActivitySignInBinding;

import java.util.Objects;

public class SignIn extends AppCompatActivity {

    private ActivitySignInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }

    private void setListeners() {
        binding.signUpText.setPaintFlags(binding.signUpText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        binding.signUpText.setOnClickListener(v -> {
            startActivity(new Intent(SignIn.this, SignUp.class));
            finish();
        });
        binding.forgotPasswordText.setPaintFlags(binding.forgotPasswordText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        binding.signInButton.setOnClickListener(v -> {
            if (isValidDetails()) {
                View view = this.getCurrentFocus();
                InputMethodManager manager = (InputMethodManager) getSystemService(SignIn.INPUT_METHOD_SERVICE);
                assert view != null;
                manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                signIn();
            }
        });
    }

    private void signIn() {
        isLoading(true);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(binding.inputEmailId.getText().toString(), binding.inputPassword.getText().toString())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        isLoading(false);

                        // Send Email Verification link if not..
                        FirebaseUser firebaseUser = auth.getCurrentUser();
                        assert firebaseUser != null;
                        if (firebaseUser.isEmailVerified()) {
                            startActivity(new Intent(SignIn.this, MainActivity.class));
                            finish();
                        } else {
                            auth.signOut();
                            AlertDialog dialog = new AlertDialog.Builder(SignIn.this)
                                    .setMessage("Please verify your email address to access all features, Press continue to get verification link.")
                                    .setTitle("Verify your Email Address")
                                    .setPositiveButton("Continue", (dialog1, which) -> {
                                        firebaseUser.sendEmailVerification();
                                        showToast("Verification link send to email address, please verify");
                                    })
                                    .setCancelable(false)
                                    .show();

                            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.red));

                        }
                    } else {
                        try {
                            throw Objects.requireNonNull(task.getException());
                        } catch (FirebaseAuthInvalidUserException e) {
                            isLoading(false);
                            showToast("User doesn't exits or is longer valid, try to register again");
                        } catch (FirebaseAuthInvalidCredentialsException e) {
                            isLoading(false);
                            showToast("Invalid Credentials");
                        } catch (Exception e) {
                            isLoading(false);
                            showToast(e.getMessage());
                        }
                    }
                });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            startActivity(new Intent(SignIn.this, MainActivity.class));
            finish();
        }
    }

    private Boolean isValidDetails() {
        if (binding.inputEmailId.getText().toString().isEmpty()) {
            showToast("Choose an email address");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.inputEmailId.getText().toString()).matches()) {
            showToast("Choose an valid email address");
            return false;
        } else if (binding.inputPassword.getText().toString().isEmpty()) {
            showToast("Password required to sign in");
            return false;
        }
        return true;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void isLoading(boolean isLoading) {
        if (isLoading) {
            binding.signInButton.setVisibility(View.INVISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.signInButton.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }
}