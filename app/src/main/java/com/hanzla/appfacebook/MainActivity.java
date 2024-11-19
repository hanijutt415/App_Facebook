package com.hanzla.appfacebook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    EditText loginNameEditText;
    EditText loginPasswordEditText;
    Button loginButton;
    ImageView imageView;
    private static final int IMAGE_PICK_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.activity_main);

        loginNameEditText = findViewById(R.id.loginname);
        loginPasswordEditText = findViewById(R.id.loginpassword);
        loginButton = findViewById(R.id.loginbtn);
        imageView = findViewById(R.id.imageview);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = loginNameEditText.getText().toString();
                String password = loginPasswordEditText.getText().toString();

                if (name.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill in both fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (name.equals("Admin") && password.equals("123")) {
                        Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid login credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        imageView.setOnClickListener(view -> {
            openGallery();
        });
    }

    private void openGallery() {
        // create an intent to pick an image from gallery
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_PICK_CODE && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();

            if (selectedImageUri != null) {
                // Use Glide to load the selected image into the ImageView
                Glide.with(this)
                        .load(selectedImageUri)
                        .into(imageView);
            }
        }
    }
}
