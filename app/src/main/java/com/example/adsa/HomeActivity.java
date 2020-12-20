package com.example.adsa;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class HomeActivity extends AppCompatActivity {

    private final String TAG = "HOME ACTIVITY";
    private final ArrayList<String> itemNames = new ArrayList<>();
    private final ArrayList<Integer> itemImages = new ArrayList<>();
    private final ArrayList<String> itemActivities = new ArrayList<>();

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
        Collections.addAll(itemActivities, "IdCardActivity", "ComingSoon", "CalendarActivity", "JiraActivity", "WellBeingActivity", "OtherActivity");
        homeAdapter = new HomeAdapter(HomeActivity.this, itemNames, itemImages, itemActivities);
        recyclerView = findViewById(R.id.recycler_view);
        //recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
        recyclerView.setLayoutManager(new CustomGridLayoutManager(HomeActivity.this, 2));
        Log.d(TAG, "HomeAdapter Size : " + homeAdapter.getItemCount());
        recyclerView.setAdapter(homeAdapter);
    }
}