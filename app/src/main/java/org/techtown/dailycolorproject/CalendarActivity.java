package org.techtown.dailycolorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class CalendarActivity extends AppCompatActivity {
    ImageButton onCalendar, pixeldiary, settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Button button1 =(Button) findViewById(R.id.test);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), InformationMain.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });

        onCalendar = findViewById(R.id.oncalendar_click1);
        onCalendar.setOnClickListener(btnListener);

        pixeldiary = findViewById(R.id.pixeldiary_unclick1);
        pixeldiary.setOnClickListener(btnListener);

        settings = findViewById(R.id.settings_unclick1);
        settings.setOnClickListener(btnListener);
    }

    View.OnClickListener btnListener = new View.OnClickListener() {
        Intent intent;
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.oncalendar_click1:
                    intent = new Intent(getApplicationContext(), CalendarActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    break;
                case R.id.pixeldiary_unclick1:
                    intent = new Intent(getApplicationContext(), PixeldiaryActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    break;
                case R.id.settings_unclick1:
                    intent = new Intent(getApplicationContext(), SettingsActivity.class);
                    break;
            }
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        }
    };
}