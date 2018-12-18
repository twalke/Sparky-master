package com.allisonwalke.sparky.views;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.allisonwalke.sparky.R;
import com.allisonwalke.sparky.UserType;

import org.w3c.dom.Text;

public class ChooseTypeActivity extends AppCompatActivity implements View.OnClickListener{

    private Button individualButton;
    private Button shelterButton;
    private String email;
    private String password;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_type);

        title = findViewById(R.id.whoAreYou);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/firasans_thinitalic.otf");
        title.setTypeface(typeface);

        individualButton = findViewById(R.id.individualButton);
        shelterButton = findViewById(R.id.shelterButton);

        individualButton.setOnClickListener(this);
        shelterButton.setOnClickListener(this);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        password = intent.getStringExtra("password");
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(ChooseTypeActivity.this, SignUpActivity.class);
        intent.putExtra("email", email);
        intent.putExtra("password", password);

        if (v == individualButton) {
            intent.putExtra("type", UserType.INDIVIDUAL);
            startActivity(intent);
        } else if (v == shelterButton) {
            intent.putExtra("type", UserType.SHELTER);
            startActivity(intent);
        }
    }
}
