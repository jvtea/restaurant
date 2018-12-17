package com.example.joost.restaurant;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<MenuItem> {

    private ArrayList<MenuItem> menuList;
    private Context context;

    public ItemAdapter(Context context, ArrayList<MenuItem> menuList){
        super(context, R.layout.item_row, menuList);
        this.menuList = menuList;
        this.context = context;

    }

    // fills correct elements with data
    // HIER GEBLEVEN, JE MOET bindView IMPLEMENTEREN IN MENUACTIVITY OM RODE KRINGELTJES WEG TE HALEN

    @ Override
    public View getView(int position, View listView, ViewGroup parent) {

        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.item_row, parent, false);
        }

        MenuItem item = menuList.get(position);

        TextView name = listView.findViewById(R.id.nameText);
        name.setText(item.getName());
        TextView price = listView.findViewById(R.id.priceText);

        // Display price correctly
        String priceString = Double.toString(item.getPrice());
        priceString = priceString.replace(".", ",");
        price.setText("€ " + priceString + "0");

        String imageUrl = item.getImageUrl();

        ImageView imageView = listView.findViewById(R.id.image);
        Picasso.with(this.context).load(imageUrl).into(imageView);


        return listView;

    }
}
