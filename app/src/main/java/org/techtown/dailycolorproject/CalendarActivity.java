package org.techtown.dailycolorproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.techtown.dailycolorproject.decorators.SaturdayDecorator;
import org.techtown.dailycolorproject.decorators.SundayDecorator;
import org.techtown.dailycolorproject.decorators.TodayDecorator;

public class CalendarActivity extends AppCompatActivity {
    ImageButton onCalendar, pixeldiary, settings;
    MaterialCalendarView materialCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
      
        //툴바 사용
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        //툴바 타이틀 지우기
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        materialCalendarView = findViewById(R.id.calendarView);
        materialCalendarView.addDecorators(new SundayDecorator(), new SaturdayDecorator(), new TodayDecorator());

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int Year = date.getYear();
                int Month = date.getMonth() + 1;
                int Day = date.getDay();

                Log.i("Year", Year + "");
                Log.i("Month", Month + "");
                Log.i("Day", Day + "");

                String selectedDate = Year + "/" + Month + "/" + Day;

                Log.i("selectedDate", selectedDate + "");

                Intent intent = new Intent(CalendarActivity.this, AddActivity.class);
                intent.putExtra("Date", selectedDate);
                setResult(Activity.RESULT_OK, intent);
                Toast.makeText(CalendarActivity.this, selectedDate, Toast.LENGTH_SHORT).show();
                startActivity(intent);
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
                    Toast.makeText(getApplicationContext(), "캘린더의 하루를 선택해 당신의 일기를 확인하세요.", Toast.LENGTH_LONG).show();
                    break;
                case R.id.pixeldiary_unclick1:
                    intent = new Intent(getApplicationContext(), PixeldiaryActivity.class);
                    Toast.makeText(getApplicationContext(), "하루의 감정을 픽셀로 기록하세요.", Toast.LENGTH_LONG).show();
                    break;
                case R.id.settings_unclick1:
                    intent = new Intent(getApplicationContext(), SettingsActivity.class);
                    Toast.makeText(getApplicationContext(), "설정을 통해 기능을 활용하거나 확인해보세요.", Toast.LENGTH_LONG).show();
                    break;
            }
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        }
    };
}