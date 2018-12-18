package com.allisonwalke.sparky.views;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.allisonwalke.sparky.R;
import com.allisonwalke.sparky.UserType;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private String email;
    private String password;
    private String confirmPassword;
    private int type;

    private TextView title;
    private EditText emailTextView;
    private EditText passwordTextView;
    private EditText confirmPasswordView;
    private TextView signUpButton;

    private FirebaseAuth mAuth;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        title = findViewById(R.id.signUpTitle);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/firasans_thinitalic.otf");
        title.setTypeface(typeface);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        type = intent.getIntExtra("type", UserType.ERROR);

        emailTextView = findViewById(R.id.signUpEmail);
        passwordTextView = findViewById(R.id.signUpPassword);
        confirmPasswordView = findViewById(R.id.confirmPassword);

        if (!email.isEmpty()) {
            emailTextView.setText(email);
        }

        signUpButton = findViewById(R.id.signUpButton2);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailTextView.getText().toString();
                password = passwordTextView.getText().toString();
                confirmPassword = confirmPasswordView.getText().toString();
                verify(email, password, confirmPassword);
            }
        });
    }

    public void verify(String email, String password, String confirmPassword) {
        if (!email.contains("@") || !email.contains(".")) {
            Toast.makeText(SignUpActivity.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.isEmpty()) {
            Toast.makeText(SignUpActivity.this, "Please enter a password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(SignUpActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        signUp(email, password);
    }

    public void signUp(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    public void updateUI(FirebaseUser user) {
        if (user != null && type == UserType.INDIVIDUAL) {
            Intent intent = new Intent(SignUpActivity.this, CardActivity.class);
            startActivity(intent);
        }
    }
}
