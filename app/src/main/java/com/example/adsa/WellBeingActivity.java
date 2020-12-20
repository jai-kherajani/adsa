package com.example.adsa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;

public class WellBeingActivity extends AppCompatActivity {

    private final String TAG = "WELLBEING ACTIVITY";
    private final ArrayList<String> itemNames = new ArrayList<>();
    private final ArrayList<Integer> itemImages = new ArrayList<>();
    private final ArrayList<String> itemActivities = new ArrayList<>();

    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_well_being);

        Toolbar myToolbar = findViewById(R.id.wellbeing_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Collections.addAll(itemNames, "Covid 19", "Digital Wellbeing", "EAP", "Medical Insurance");
        Collections.addAll(itemImages, R.drawable.covid, R.drawable.quitting, R.drawable.eap, R.drawable.medical_insurance);
        Collections.addAll(itemActivities, "Covid19Activity", "ComingSoon", "ComingSoon", "ComingSoon");
        homeAdapter = new HomeAdapter(WellBeingActivity.this, itemNames, itemImages, itemActivities);
        recyclerView = findViewById(R.id.recycler_view);
        //recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
        recyclerView.setLayoutManager(new CustomGridLayoutManager(WellBeingActivity.this, 2));
        Log.d(TAG, "HomeAdapter Size : " + homeAdapter.getItemCount());
        recyclerView.setAdapter(homeAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}