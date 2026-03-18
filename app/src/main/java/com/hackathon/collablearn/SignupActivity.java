package com.hackathon.collablearn;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    private EditText etFullName, etEmail, etPassword, etConfirmPassword;
    private Button btnSignup;
    private TextView tvLoginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize Views
        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnSignup = findViewById(R.id.btnSignup);
        tvLoginLink = findViewById(R.id.tvLoginLink);

        // Sign Up Button Click Listener
        btnSignup.setOnClickListener(v -> {
            String fullName = etFullName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();

            if (TextUtils.isEmpty(fullName)) {
                etFullName.setError("Username is required");
                return;
            }
            if (fullName.length() != 16) {
                etFullName.setError("Username must be exactly 16 characters");
                return;
            }

            if (TextUtils.isEmpty(email)) {
                etEmail.setError("Email is required");
                return;
            }
            if (!email.toLowerCase().endsWith("@gmail.com")) {
                etEmail.setError("Please enter a valid @gmail.com address");
                return;
            }

            if (TextUtils.isEmpty(password)) {
                etPassword.setError("Password is required");
                return;
            }
            if (password.length() > 8) {
                etPassword.setError("Password cannot exceed 8 characters");
                return;
            }
            // Check if password contains ONLY letters (no numbers, no symbols)
            if (!password.matches("^[a-zA-Z]+$")) {
                etPassword.setError("Password can only contain letters (no numbers or symbols)");
                return;
            }

            if (!password.equals(confirmPassword)) {
                etConfirmPassword.setError("Passwords do not match");
                return;
            }

            // TODO: Implement actual signup logic (e.g., Firebase Auth or API call)
            Toast.makeText(SignupActivity.this, "Sign Up Attempted for: " + email, Toast.LENGTH_SHORT).show();
            
            // Navigate to Main Activity or Login Activity if successful
            // startActivity(new Intent(SignupActivity.this, MainActivity.class));
            // finish();
        });

        // Password Visibility Toggle Logic
        android.widget.ImageView ivTogglePassword = findViewById(R.id.ivTogglePassword);
        ivTogglePassword.setOnClickListener(v -> togglePasswordVisibility(etPassword, ivTogglePassword));

        android.widget.ImageView ivToggleConfirmPassword = findViewById(R.id.ivToggleConfirmPassword);
        ivToggleConfirmPassword.setOnClickListener(v -> togglePasswordVisibility(etConfirmPassword, ivToggleConfirmPassword));

        // Login Link Click Listener
        tvLoginLink.setOnClickListener(v -> {
            // Since LoginActivity is likely the parent, finishing this activity brings us back
            finish();
        });
    }

    private void togglePasswordVisibility(EditText editText, android.widget.ImageView icon) {
        if (editText.getInputType() == android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            // Hide Password
            editText.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
            icon.setImageResource(android.R.drawable.ic_menu_view);
        } else {
            // Show Password
            editText.setInputType(android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            // using the same icon for now since Android standard doesn't have a built-in eye-slash
            // in a real app, you'd swap to an eye-disabled icon here
        }
        editText.setSelection(editText.getText().length());
    }
}
