package org.techtown.dailycolorproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    ImageButton onCalendar, pixeldiary, settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //툴바 사용
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        //툴바 타이틀 지우기
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        onCalendar = findViewById(R.id.oncalendar_click3);
        onCalendar.setOnClickListener(btnListener);

        pixeldiary = findViewById(R.id.pixeldiary_unclick3);
        pixeldiary.setOnClickListener(btnListener);

        settings = findViewById(R.id.settings_unclick3);
        settings.setOnClickListener(btnListener);
    }

    View.OnClickListener btnListener = new View.OnClickListener() {
        Intent intent;
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.oncalendar_click3:
                    intent = new Intent(getApplicationContext(), CalendarActivity.class);
                    Toast.makeText(getApplicationContext(), "캘린더의 하루를 선택해 당신의 일기를 확인하세요.", Toast.LENGTH_LONG).show();
                    break;
                case R.id.pixeldiary_unclick3:
                    intent = new Intent(getApplicationContext(), PixeldiaryActivity.class);
                    Toast.makeText(getApplicationContext(), "하루의 감정을 픽셀로 기록하세요.", Toast.LENGTH_LONG).show();
                    break;
                case R.id.settings_unclick3:
                    intent = new Intent(getApplicationContext(), SettingsActivity.class);
                    Toast.makeText(getApplicationContext(), "설정을 통해 기능을 활용하거나 확인해보세요.", Toast.LENGTH_LONG).show();
                    break;
            }
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        }
    };
}