package com.hanuappmedia.glamifyqueen.auth;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hanuappmedia.glamifyqueen.MainActivity;
import com.hanuappmedia.glamifyqueen.databinding.ActivitySignUpBinding;
import com.hanuappmedia.glamifyqueen.utilities.Constants;
import com.hanuappmedia.glamifyqueen.utilities.ReadWriteUser;

import java.util.Objects;

public class SignUp extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }

    private void setListeners() {
        binding.signInText.setPaintFlags(binding.signInText.getPaintFlags()|Paint.UNDERLINE_TEXT_FLAG);
        binding.signInText.setOnClickListener(v -> {
            startActivity(new Intent(SignUp.this, SignIn.class));
            finish();
        });
        binding.signUpButton.setOnClickListener(v->{
            if (isValidDetails()){
                View view = this.getCurrentFocus();
                InputMethodManager manager = (InputMethodManager) getSystemService(SignUp.INPUT_METHOD_SERVICE);
                assert view != null;
                manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                signUp();
            }
        });
    }

    private void signUp() {
        isLoading(true);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(binding.inputEmailId.getText().toString(), binding.inputPassword.getText().toString())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){

                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                        //Store userdata to firebase realtime database
                        ReadWriteUser writeUserDetails = new ReadWriteUser(binding.inputUsername.getText().toString());

                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(Constants.USER_COLLECTION);
                        assert firebaseUser != null;
                        databaseReference.child(firebaseUser.getUid()).setValue(writeUserDetails)
                                .addOnCompleteListener(task1 -> {
                                    if (task1.isSuccessful()){
                                        isLoading(false);
                                        // showToast("Profile created successfully, please verify your email");

                                        // Send verification link
                                        firebaseUser.sendEmailVerification();
                                        showToast("Verification link send to your entered email address");

                                        //Open main activity after successful register
                                        Intent iMainActivity = new Intent(SignUp.this, MainActivity.class);
                                        iMainActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(iMainActivity);
                                        finish();
                                    }else{
                                        isLoading(false);
                                        showToast("Something error occurred, please try again later");
                                    }
                                });



                    } else {
                        try {
                            throw Objects.requireNonNull(task.getException());
                        }catch (FirebaseAuthUserCollisionException e){
                            isLoading(false);
                            showToast("Email is already in use, try again or re-enter");
                        } catch (FirebaseAuthWeakPasswordException e){
                            isLoading(false);
                            showToast("Password is too weak, try mixing of alphabets");
                        }catch (FirebaseAuthInvalidCredentialsException e){
                            showToast("Email is invalid or already in use");
                            isLoading(false);
                        } catch (Exception e){
                            showToast(e.getMessage());
                        }
                    }
                });


    }

    private Boolean isValidDetails() {
        if (binding.inputUsername.getText().toString().isEmpty()) {
            showToast("Add a username to go ahead");
            return false;
        } else if (binding.inputEmailId.getText().toString().isEmpty()) {
            showToast("Choose an email account");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.inputEmailId.getText().toString()).matches()) {
            showToast("Choose an valid email account");
            return false;
        } else if (binding.inputPassword.getText().toString().isEmpty()) {
            showToast("Create a password for security purposes");
            return false;
        } else if(binding.inputPassword.getText().toString().length() < 6){
            showToast("Password must be 6 or more characters");
            return false;
        }

        return true;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void isLoading(boolean isLoading) {
        if (isLoading){
            binding.signUpButton.setVisibility(View.INVISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);
        } else{
            binding.signUpButton.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }
}