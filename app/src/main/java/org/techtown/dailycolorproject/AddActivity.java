package org.techtown.dailycolorproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import id.zelory.compressor.Compressor;

public class AddActivity extends AppCompatActivity {

    private EditText post_title;
    private EditText post_content;

    private String getTitle;

    private ImageView post_image;

    private Uri post_image_uri = null;

    private StorageReference storageReference;
    private FirebaseFirestore firebaseFirestore;

    private Bitmap compressedImageFile;

    private int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initial();
        findId();

        //툴바 사용
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        //툴바 타이틀 지우기
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //툴바 뒤로가기 생성(뒤로가기 화살표가 자동 제공됨)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //뒤로가기 버튼 설정(drawable에 이미지를 등록해서 화살표 대신 사용가능, 사이즈가 충분히 작아야 함)
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_cancel);

        Intent intent = getIntent();
        getTitle = intent.getStringExtra("Date");
        TextView tadayDate = findViewById(R.id.today_date);
        tadayDate.setText(getTitle);

        post_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setMinCropResultSize(700, 512)
                        .setAspectRatio(1, 1)
                        .start(AddActivity.this);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:{ //툴바 뒤로가기 동작
                finish();
                return true;
            }
            case R.id.btn_check:
                findViewById(R.id.btn_check).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String title = post_title.getText().toString();
                        final String content = post_content.getText().toString();

                        if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(content) && post_image_uri!=null) {
                            final String randomName = UUID.randomUUID().toString();

                            final StorageReference filepath = storageReference.child("post_images").child(randomName + ".jpg");
                            final UploadTask uploadTask1 = filepath.putFile(post_image_uri);

                            Task<Uri> urlTask = uploadTask1.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                @Override
                                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                    if (!task.isSuccessful()) {
                                        throw task.getException();
                                    }

                                    // Continue with the task to get the download URL
                                    return filepath.getDownloadUrl();
                                }
                            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull final Task<Uri> task) {

                                    final String downloadUri = task.getResult().toString();

                                    if (task.isSuccessful()) {

                                        File newImageFile = new File(post_image_uri.getPath());

                                        try {
                                            compressedImageFile = new Compressor(AddActivity.this)
                                                    .setMaxHeight(30)
                                                    .setMaxWidth(30)
                                                    .setQuality(2)
                                                    .compressToBitmap(newImageFile);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                        uploadTask1.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                            @Override
                                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                                                Map<String, Object> PostMap = new HashMap<>();
                                                PostMap.put("post_title", title);
                                                PostMap.put("post_content",content);
                                                PostMap.put("date", getTitle);
                                                PostMap.put("post_image",downloadUri);


                                                firebaseFirestore.collection("Posts").add(PostMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<DocumentReference> task) {

                                                        if(task.isSuccessful()) {
                                                            count++;
                                                            Toast.makeText(AddActivity.this,"글이 추가되었습니다!",Toast.LENGTH_LONG).show();
                                                            Intent mainIntent = new Intent(AddActivity.this, CalendarActivity.class);
                                                            mainIntent.putExtra("count", count);
                                                            startActivity(mainIntent);
                                                        }
                                                    }
                                                });

                                                firebaseFirestore.collection("Users/"+"main"+"/posts").add(PostMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<DocumentReference> task) {
                                                        if(task.isSuccessful()) {
                                                            Log.e("NewPost -> User Post","User 데베에 추가됨.");
                                                        }
                                                    }
                                                });

                                            }
                                        });
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(AddActivity.this,"빈 칸을 채워주세요!",Toast.LENGTH_LONG).show();
                        }
                    }

                });
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void findId() {
        post_title = findViewById(R.id.today_title);
        post_content = findViewById(R.id.today_content);
        post_image = findViewById(R.id.show_img);
    }

    private void initial() {
        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                post_image_uri = result.getUri();

                post_image.setImageURI(post_image_uri);


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}