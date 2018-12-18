package com.allisonwalke.sparky.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.allisonwalke.sparky.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CardActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button signOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        signOutButton = findViewById(R.id.signOutButton);
        mAuth = FirebaseAuth.getInstance();

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                updateUI();
            }
        });
    }

    public void updateUI() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            Intent intent = new Intent(CardActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
