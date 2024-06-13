package com.shak.fakestore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;


public class ShowItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_item);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.showItemActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        double price = intent.getDoubleExtra("price", 0.0);
        String category = intent.getStringExtra("category");
        String imageUrl = intent.getStringExtra("image");
        double rate = intent.getDoubleExtra("rate", 0.0);
        int count = intent.getIntExtra("count", 0);

        TextView txtTitle = findViewById(R.id.txtTitle);
        TextView txtDescription = findViewById(R.id.txtDescription);
        TextView txtPrice = findViewById(R.id.txtPrice);
        TextView txtCategory = findViewById(R.id.txtCategory);
        ImageView imgShow = findViewById(R.id.imgProduct);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        TextView txtCount = findViewById(R.id.txtRatingCount);


        txtTitle.setText(title);
        txtDescription.setText(description);
        txtPrice.setText(String.valueOf(price));
        txtCategory.setText(category);
        Glide.with(this).load(imageUrl).into(imgShow);
        ratingBar.setRating((float) rate);
        txtCount.setText(String.valueOf(count));
    }
}