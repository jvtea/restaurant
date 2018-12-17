package com.example.joost.restaurant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MenuItemActivity extends AppCompatActivity {

    MenuItem item;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        Log.d("hierbenje", "nieuwe pagina begin");

        Intent intent = getIntent();
        item = (MenuItem) intent.getSerializableExtra("menuItem");

        Log.d("hierbenje", "dit is item: " + item);

        String name = item.getName();
        String description = item.getDescription();
        String imageUrl = item.getImageUrl();
        double price = item.getPrice();

        Log.d("hierbenje", "nieuwe pagina midden");

        TextView nameText = findViewById(R.id.nameText);
        TextView descriptionText = findViewById(R.id.descriptionText);
        TextView priceText = findViewById(R.id.priceText);
        ImageView imageView = findViewById(R.id.image);

        nameText.setText(name);
        descriptionText.setText(description);

        // Display price correctly
        String priceString = Double.toString(item.getPrice());
        priceString = priceString.replace(".", ",");
        priceText.setText("€ " + priceString + "0");

        Picasso.with(context).load(imageUrl).into(imageView);

        Log.d("hierbenje", "nieuwe pagina einde");
    }
}
