package org.techtown.dailycolorproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class PixeldiaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pixeldiary);

        //툴바 사용
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        //툴바 타이틀 지우기
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}