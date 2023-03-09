package com.example.photoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    GridView gridview;

    private AdapterView.OnItemClickListener onitemclick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getBaseContext(),ViewPhotoActivity1.class);
            intent.putExtra("id", gridview.getAdapter().getItemId(position));
            startActivity(intent);

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PhotoData photoData = new PhotoData(this);

        gridview = findViewById(R.id.gridview);
        PhotoAdapter adapter = new PhotoAdapter(PhotoData.generatePhotoData(),getApplicationContext());
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(onitemclick);
    }
}