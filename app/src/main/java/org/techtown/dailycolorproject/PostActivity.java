package org.techtown.dailycolorproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

// 사용자 마이페이지
public class PostActivity extends AppCompatActivity {
    private static final String TAG = "UserActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        //툴바 사용
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_post));
        //툴바 타이틀 지우기
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //툴바 뒤로가기 생성(뒤로가기 화살표가 자동 제공됨)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //뒤로가기 버튼 설정(drawable에 이미지를 등록해서 화살표 대신 사용가능, 사이즈가 충분히 작아야 함)
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        final ArrayList<AddInfo> postList = new ArrayList<>();
        db.collection("Posts").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        postList.add(new AddInfo(
                                document.getData().get("post_title").toString(),
                                document.getData().get("post_content").toString(),
                                document.getData().get("date").toString(),
                                document.getData().get("post_image").toString()));
                    }
                    RecyclerView recyclerView = findViewById(R.id.recyclerView);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(PostActivity.this));

                    RecyclerView.Adapter mAdapter = new PostAdapter(PostActivity.this, postList);
                    recyclerView.setAdapter(mAdapter);

                } else {
                    Log.d(TAG, "Error getting documents : ", task.getException());
                }
            }
        });
    }

    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // 앱이 꺼짐
        startActivity(intent);
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
}