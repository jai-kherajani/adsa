package com.example.adsa;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.Collections;

public class HomeActivity extends AppCompatActivity {

    private final String TAG = "HOME ACTIVITY";
    private final ArrayList<String> itemNames = new ArrayList<>();
    private final ArrayList<Integer> itemImages = new ArrayList<>();
    private final ArrayList<Activity> itemActivities = new ArrayList<>();

    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar myToolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(myToolbar);

        Collections.addAll(itemNames, "ID Card", "Directory", "Calendar", "Time Sheets", "Wellbeing", "Others");
        Collections.addAll(itemImages, R.drawable.id_card, R.drawable.team, R.drawable.calendar, R.drawable.timeline, R.drawable.health, R.drawable.options);
        homeAdapter = new HomeAdapter(HomeActivity.this, itemNames, itemImages, itemActivities);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        Log.d(TAG, "HomeAdapter Size : " + homeAdapter.getItemCount());
        recyclerView.setAdapter(homeAdapter);
    }
}