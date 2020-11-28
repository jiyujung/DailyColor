package org.techtown.dailycolorproject;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class InformationMain extends AppCompatActivity {
    private ViewPager viewPager;
    private List<String> numberList;
    private CircleAnimIndicator circleAnimIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_main);

        //툴바 사용
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        //툴바 타이틀 지우기
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //툴바 뒤로가기 생성(뒤로가기 화살표가 자동 제공됨)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //뒤로가기 버튼 설정(drawable에 이미지를 등록해서 화살표 대신 사용가능, 사이즈가 충분히 작아야 함)
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setOffscreenPageLimit(3);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Infor1 infor1 = new Infor1();
        adapter.addItem(infor1);

        Infor2 infor2 = new Infor2();
        adapter.addItem(infor2);

        Infor3 infor3 = new Infor3();
        adapter.addItem(infor3);

        pager.setAdapter(adapter);

        initLayout();
        init();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:{ //툴바 뒤로가기 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items = new ArrayList<Fragment>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

//        public ViewPagerAdapter(Context applicationContext, List<String> numberList) {
//            super(applicationContext, numberList);
//        }

        public void addItem(Fragment item) {
            items.add(item);
        }

        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }

    /*
   레이아웃 초기화
    */
    private void initLayout() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        circleAnimIndicator = (CircleAnimIndicator) findViewById(R.id.circleAnimIndicator);
    }

    /*
    데이터 초기화
     */
    private void init() {
        //데이터 초기화
        initData();
        //ViewPager 초기화
        initViewPager();
    }

    /*
    데이터 초기화
     */
    private void initData() {
        numberList = new ArrayList<>();
        numberList.add("1");
        numberList.add("2");
        numberList.add("3");
    }

    /*
    ViewPager 초기화
     */
    private void initViewPager() {
        //ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getApplicationContext(), numberList);
        //viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(mOnpageChangeListener);

        //Indicator 초기화
        initIndicator();
    }

    /*
    Indicator 초기화
     */
    private void initIndicator() {
        // 원사이의 간격
        circleAnimIndicator.setItemMargin(15);
        //애니메이션 속도
        circleAnimIndicator.setAnimDuration(300);
        //indicator 생성
        circleAnimIndicator.createDotPanel(numberList.size(), R.drawable.indicator_non, R.drawable.indicator_on);
    }

    /*
    ViewPager 전환시 호출되는 메서드
     */
    private ViewPager.OnPageChangeListener mOnpageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        @Override
        public void onPageSelected(int position) {
            circleAnimIndicator.selectDot(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

}