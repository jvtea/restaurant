package com.example.joost.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity
        implements CategoriesRequest.Callback {

    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        CategoriesRequest category = new CategoriesRequest(this);
        category.getCategories(this);
    }


    @Override
    public void gotCategories(ArrayList<String> categories) {

        // make an array adapter and connect to listview
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, categories);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        // set itemclicklistener for listview
        listView.setOnItemClickListener(new ListViewItemClick());
    }

    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        String category = parent.getItemAtPosition(position).toString();
//        Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
//        intent.putExtra("category", category);
//        startActivity(intent);
//
//    }

    private class ListViewItemClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String category = parent.getItemAtPosition(position).toString();
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("category", category);
            startActivity(intent);
        }
    }
}
