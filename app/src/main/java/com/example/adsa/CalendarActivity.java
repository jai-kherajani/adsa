package com.example.adsa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    private static String TAG = "CalendarActivity";
    final SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
    private CompactCalendarView calendarView;
    private DatabaseReference databaseReference;
    private ArrayList<Event> eventsList = new ArrayList<>();
    private TextView month_year, date, event, sl, pl;
    private Calendar calendar;
    private Button applyLeaves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Toolbar myToolbar = findViewById(R.id.calendar_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        calendarView = findViewById(R.id.calendarView);
        calendarView.shouldSelectFirstDayOfMonthOnScroll(false);
        calendarView.setFirstDayOfWeek(1);
        calendarView.setUseThreeLetterAbbreviation(true);

        calendar = Calendar.getInstance();

        fetchEvents(calendar);

        month_year = findViewById(R.id.month_year);
        date = findViewById(R.id.date);
        event = findViewById(R.id.event);

        calendar.setTime(calendarView.getFirstDayOfCurrentMonth());
        String monthYear = new SimpleDateFormat("MMMM-yyyy").format(calendar.getTime());
        month_year.setText(monthYear);
        date.setText(f.format(Calendar.getInstance().getTime()));

        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = calendarView.getEvents(dateClicked);
                Log.d(TAG, "Day was clicked: " + dateClicked + " with events : " + events);
                date.setText(f.format(dateClicked));
                event.setText("");
                if (!events.isEmpty()) {
                    for (int i = 0; i < events.size(); i++) {
                        //event.setText(events.get(i).getData().toString());
                        if (i + 1 < events.size())
                            event.append(events.get(i).getData().toString() + ", ");
                        else
                            event.append(events.get(i).getData().toString());
                    }
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                Log.d(TAG, "Month was scrolled to: " + firstDayOfNewMonth);
                calendar.setTime(calendarView.getFirstDayOfCurrentMonth());
                String monthYear = new SimpleDateFormat("MMMM-yyyy").format(calendar.getTime());
                month_year.setText(monthYear);
                fetchEvents(calendar);
            }
        });

        pl = findViewById(R.id.pl);
        sl = findViewById(R.id.sl);
        ADSAApplication adsaApplication = (ADSAApplication) getApplicationContext();
        HashMap<String, String> values = adsaApplication.getData();
        sl.setText(String.valueOf(values.get("sl_bal")));
        pl.setText(String.valueOf(values.get("pl_bal")));

        applyLeaves = findViewById(R.id.leave);
        applyLeaves.setOnClickListener(v -> {
            startActivity(new Intent(CalendarActivity.this, ComingSoon.class));
        });
    }

    private void fetchEvents(Calendar calendar) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Events/" + calendar.get(Calendar.YEAR));
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "Data : " + dataSnapshot);
                eventsList.clear();
                for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                    System.out.println("event data : " + eventSnapshot.child("date").getValue(String.class));

                    try {
                        Date d = f.parse(eventSnapshot.child("date").getValue(String.class));
                        eventsList.add(new Event(getResources().getColor(R.color.black, null), d.getTime(), eventSnapshot.child("name").getValue(String.class)));
                        loadData();
                        loadToday();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(CalendarActivity.this, "Something happened while fetching events!", Toast.LENGTH_SHORT).show();
            }
        });
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

    private void loadData() {
        calendarView.removeAllEvents();
        calendarView.addEvents(eventsList);
    }

    void loadToday() {
        Calendar temp_c = Calendar.getInstance();
        temp_c.set(Calendar.HOUR_OF_DAY, 0);
        temp_c.set(Calendar.MINUTE, 0);
        temp_c.set(Calendar.SECOND, 0);
        List<Event> events = calendarView.getEvents(temp_c.getTime());
        System.out.println("default date : " + temp_c.getTime() + " with events : " + events.toString());
        event.setText("");
        if (!events.isEmpty()) {
            for (int i = 0; i < events.size(); i++) {
                //event.setText(events.get(i).getData().toString());
                if (i + 1 < events.size())
                    event.append(events.get(i).getData().toString() + ", ");
                else
                    event.append(events.get(i).getData().toString());
            }
        }
    }
}