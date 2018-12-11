package com.example.joost.restaurant;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener  {

    // declaration of instance variables
    Context context;
    String category;
    Callback activity;

    // constructor
    public MenuRequest(Context context, String category){
        this.context = context;
        this.category = category;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotMenuError(error.getMessage());
    }

    // passes information from JSONObject to activity
    @Override
    public void onResponse(JSONObject response) {

        JSONArray menu = null;
        try {
            menu = response.getJSONArray("items");
            Log.d("anderedingen", menu + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // fill this list with MenuItems, create those in for loop below
        ArrayList<MenuItem> menuList = new ArrayList<>();

        for (int index = 0; index < menu.length(); index++) {
            try {
                JSONObject item = menu.getJSONObject(index);
                String name = item.getString("name");
                //Log.d("dingen_dit is name", name);
                String description = item.getString("description");
                String imageUrl = item.getString("image_url");
                String itemCategory = item.getString("category");
                double price = item.getDouble("price");

                //Log.d("dingen_itemcat", itemCategory + this.category + "");

                if (itemCategory.equals(this.category)) {
                    menuList.add(new MenuItem(name, description, imageUrl, price, category));

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //
        activity.gotMenu(menuList);
    }



    // tries to retrieve menu items from the API and saves activity
    void getMenu(Callback activity) {

        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/menu", null, this, this);
        queue.add(jsonObjectRequest);

        this.activity = activity;
    }

    // callback method to see if process has finished
    public interface Callback {
        void gotMenu(ArrayList<MenuItem> menuList);
        void gotMenuError(String message);
    }

}
