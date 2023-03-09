package com.example.photoapp;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class PhotoData {
    private static Context context;

    public PhotoData(Context context) {
        this.context= context;
    }

    public static ArrayList<Photo> generatePhotoData() {
        ArrayList<Photo> photos = new ArrayList<>();
        try {
            InputStream inputStream = context.getAssets().open("photos.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String jsonString = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String source = jsonObject.getString("source");
                String title = jsonObject.getString("title");
                String description = jsonObject.getString("description");
                photos.add(new Photo(id, source, title, description));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return photos;
    }
    public static Photo getPhotoFromID(int id){
        ArrayList<Photo> phs = generatePhotoData();
        for (int i = 0; i < phs.size(); i++)
            if(phs.get(i).getId()==id)
                return phs.get(i);
        return null;
    }
}
