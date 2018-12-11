package com.example.joost.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback {

    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        String category = intent.getStringExtra("category");

        MenuRequest request = new MenuRequest(this, category);
        request.getMenu(this);
    }

    @Override
    public void gotMenu(ArrayList<MenuItem> menuList) {

        // make an array adapter and connect to listview
        adapter = new ItemAdapter(this, menuList);
        ListView listView = findViewById(R.id.listView);

        Log.d("dingen_menulist", menuList + "");
        Log.d("dingen_adapter", adapter + "");
        Log.d("dingen_listView", listView + "");

        listView.setAdapter(adapter);



        // set itemclicklistener for listview
        listView.setOnItemClickListener(new MenuActivity.ListViewItemClick());
    }

    @Override
    public void gotMenuError(String message) {
        //Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private class ListViewItemClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MenuItem menuItem = (MenuItem) parent.getItemAtPosition(position);

            Log.d("hierbenje", "dit is item voor:" + menuItem);
            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            intent.putExtra("menuItem", menuItem);
            Log.d("hierbenje", "hier ook");
            startActivity(intent);
        }
    }
}
