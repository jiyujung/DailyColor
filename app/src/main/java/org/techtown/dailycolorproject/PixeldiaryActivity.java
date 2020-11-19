package org.techtown.dailycolorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class PixeldiaryActivity extends AppCompatActivity {
    ImageButton bar_onCalendar,bar_pixeldiary,bar_settings;//bottom menu bar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pixeldiary);

        //bottom bar
        bar_onCalendar = findViewById(R.id.oncalendar_unclick2);
        bar_onCalendar.setOnClickListener(bottomBarListener);

        bar_pixeldiary = findViewById(R.id.pixeldiary_click2);
        bar_pixeldiary.setOnClickListener(bottomBarListener);

        bar_settings = findViewById(R.id.settings_unclick1);
        bar_settings.setOnClickListener(bottomBarListener);
    }
    //bottom bar listener
    View.OnClickListener bottomBarListener=new View.OnClickListener(){
        Intent intent;
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.oncalendar_unclick2:
                    intent=new Intent(getApplicationContext(),CalendarActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.not_move_activity,R.anim.not_move_activity);
                    finish();
                    Toast.makeText(getApplicationContext(), "캘린더의 하루를 선택해 당신의 일기를 확인하세요.", Toast.LENGTH_LONG).show();
                    break;
                case R.id.pixeldiary_click2:
                    Toast.makeText(getApplicationContext(), "하루의 감정을 픽셀로 기록하세요.", Toast.LENGTH_LONG).show();
                    break;
                case R.id.settings_unclick2:
                    intent=new Intent(getApplicationContext(),SettingsActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.not_move_activity,R.anim.not_move_activity);
                    finish();
                    Toast.makeText(getApplicationContext(), "설정을 통해 기능을 활용하거나 확인해보세요.", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };


}