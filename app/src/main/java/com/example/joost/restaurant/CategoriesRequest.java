package com.example.joost.restaurant;

import android.app.Activity;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    // declaration of instance variables
    Context context;
    Callback activity;

    public CategoriesRequest(Context context){
        this.context = context;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotCategoriesError(error.getMessage());
    }

    // passes information from JSONObject to activity
    @Override
    public void onResponse(JSONObject response) {

        JSONArray categories = null;
        try {
            categories = response.getJSONArray("categories");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayList<String> stringsList = new ArrayList<>();

        for (int position = 0; position < categories.length(); position++) {
            try {
                stringsList.add(categories.getString(position));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        activity.gotCategories(stringsList);
    }

    // callback method to see if process has finished
    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    // tries to retrieve categories from the API and saves activity
    void getCategories(Callback activity) {

        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/categories", null, this, this);
        queue.add(jsonObjectRequest);

        this.activity = activity;
    }

}
