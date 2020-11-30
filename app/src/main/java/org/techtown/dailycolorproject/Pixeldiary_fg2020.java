package org.techtown.dailycolorproject;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.StorageReference;

import org.techtown.dailycolorproject.R.drawable;

import java.util.ArrayList;

public class Pixeldiary_fg2020 extends Fragment {
    private View view;

    public Pixeldiary_fg2020(){
        //Required empty public constructor
    }

    public static Pixeldiary_fg2020 newInstance(){
        Pixeldiary_fg2020 Pixeldiary_fg2020 = new Pixeldiary_fg2020();
        return Pixeldiary_fg2020;
    }

    //firebase
    private FirebaseDatabase database;
    FirebaseFirestore db = FirebaseFirestore.getInstance();



    int[] imgPixel={drawable.pixel_red,drawable.pixel_yello,drawable.pixel_green,drawable.pixel_darkblue,drawable.pixel_purple,drawable.pixel_white};
    int[] pixelCount={0,0,0,0,0};
    public int imageIndexFlag =5;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pixeldiary_2020,container,false);

        //firebase
        database=FirebaseDatabase.getInstance();
//        String[] menuItems={"","",""};
//        ListView listView=(ListView)view.findViewById(R.id.pixel_listView_1);
//        ArrayAdapter<String> listViewAdapter=new ArrayAdapter<String>(
//            getActivity(),android.R.layout.simple_list_item_1,menuItems
//        );
//        listView.setAdapter(listViewAdapter);
        // 빈 데이터 리스트 생성.
        final ArrayList<String> items = new ArrayList<String>() ;

        // ArrayAdapter 생성. 아이템 View를 선택(single choice)가능하도록 만듦.
        //ListView listView3,listView4,listView5,listView6,listView7,listView8,listView9,listView10,listView11,listView12;
        //final ListViewAdapter adapter3,adapter4,adapter5,adapter6,adapter7,adapter8,adapter9,adapter10,adapter11,adapter12;

        //Adapter생성
        //adapter1=new ListViewAdapter(getActivity(),1);
//        adapter2=new ListViewAdapter(getActivity(),2);
//        adapter3=new ListViewAdapter(getActivity(),3);
//        adapter4=new ListViewAdapter(getActivity(),4);
//        adapter5=new ListViewAdapter(getActivity(),5);
//        adapter6=new ListViewAdapter(getActivity(),6);
//        adapter7=new ListViewAdapter(getActivity(),7);
//        adapter8=new ListViewAdapter(getActivity(),8);
//        adapter9=new ListViewAdapter(getActivity(),9);
//       adapter10=new ListViewAdapter(getActivity(),10);
//        adapter11=new ListViewAdapter(getActivity(),11);
//        adapter12=new ListViewAdapter(getActivity(),12);

        db.collection("PixelOne")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("파이어베이스", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w("파이어베이스", "Error getting documents.", task.getException());
                        }
                    }
                });
        CollectionReference pixelRef = db.collection("Pixel");

        //listView1
        //리스트뷰 참조 및 Adapter달기


        final ListView listView1 =(ListView)view.findViewById(R.id.pixel_listView_1);
        final ListViewAdapter adapter1 = new ListViewAdapter(getActivity(),1);
        //이미지 아이템 추가
        for(int i=0;i<31;i++){
            final int finalI = i;
            final int imageIndex=imageIndexFlag;
            db.collection("PixelOne")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d("파이어베이스", document.getId() + " => " + document.getData());
                                    String si=Integer.toString(finalI);
                                    if(document.getData().get("position").toString().equals(si)){
                                        Log.d("파이어베이스", Integer.toString(finalI)+" == " + document.getData().get("position"));
                                        switch (document.getData().get("image").toString()){

                                            case "0":
                                                imageIndexFlag =0;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "1":
                                                imageIndexFlag =1;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "2":
                                                imageIndexFlag =2;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "3":
                                                imageIndexFlag =3;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "4":
                                                imageIndexFlag =4;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                        }
                                    }else{
                                        Log.d("파이어베이스", Integer.toString(finalI)+" != " + document.getData().get("position"));
                                        imageIndexFlag =5;
                                        Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                    }
                                    adapter1.addItem(ContextCompat.getDrawable(getActivity(), imgPixel[imageIndexFlag]));
                                }//end of for


                                listView1.setAdapter(adapter1);

                            } else {
                                Log.w("파이어베이스", "Error getting documents.", task.getException());
                            }
                        }
                    });
        }
        //listView1.setAdapter(adapter1);

        //listView2
        final ListView listView2 =(ListView)view.findViewById(R.id.pixel_listView_2);
        final ListViewAdapter adapter2 = new ListViewAdapter(getActivity(),2);
        for(int i=0;i<29;i++){
            final int finalI = i;
            final int imageIndex=imageIndexFlag;
            db.collection("PixelTwo")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d("파이어베이스", document.getId() + " => " + document.getData());
                                    String si=Integer.toString(finalI);
                                    if(document.getData().get("position").toString().equals(si)){
                                        Log.d("파이어베이스", Integer.toString(finalI)+" == " + document.getData().get("position"));
                                        switch (document.getData().get("image").toString()){

                                            case "0":
                                                imageIndexFlag =0;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "1":
                                                imageIndexFlag =1;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "2":
                                                imageIndexFlag =2;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "3":
                                                imageIndexFlag =3;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "4":
                                                imageIndexFlag =4;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                        }
                                    }else{
                                        Log.d("파이어베이스", Integer.toString(finalI)+" != " + document.getData().get("position"));
                                        imageIndexFlag =5;
                                        Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                    }
                                    adapter2.addItem(ContextCompat.getDrawable(getActivity(), imgPixel[imageIndexFlag]));
                                }//end of for


                                listView2.setAdapter(adapter2);

                            } else {
                                Log.w("파이어베이스", "Error getting documents.", task.getException());
                            }
                        }
                    });
        }

        //listView3
        final ListView listView3 =(ListView)view.findViewById(R.id.pixel_listView_3);
        final ListViewAdapter adapter3 = new ListViewAdapter(getActivity(),3);
        for(int i=0;i<31;i++){
            final int finalI = i;
            final int imageIndex=imageIndexFlag;
            db.collection("PixelThree")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d("파이어베이스", document.getId() + " => " + document.getData());
                                    String si=Integer.toString(finalI);
                                    if(document.getData().get("position").toString().equals(si)){
                                        Log.d("파이어베이스", Integer.toString(finalI)+" == " + document.getData().get("position"));
                                        switch (document.getData().get("image").toString()){

                                            case "0":
                                                imageIndexFlag =0;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "1":
                                                imageIndexFlag =1;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "2":
                                                imageIndexFlag =2;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "3":
                                                imageIndexFlag =3;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "4":
                                                imageIndexFlag =4;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                        }
                                    }else{
                                        Log.d("파이어베이스", Integer.toString(finalI)+" != " + document.getData().get("position"));
                                        imageIndexFlag =5;
                                        Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                    }
                                    adapter3.addItem(ContextCompat.getDrawable(getActivity(), imgPixel[imageIndexFlag]));
                                }//end of for


                                listView3.setAdapter(adapter3);

                            } else {
                                Log.w("파이어베이스", "Error getting documents.", task.getException());
                            }
                        }
                    });
        }


        //listView4
        final ListView listView4 =(ListView)view.findViewById(R.id.pixel_listView_4);
        final ListViewAdapter adapter4 = new ListViewAdapter(getActivity(),4);
        for(int i=0;i<30;i++){
            final int finalI = i;
            final int imageIndex=imageIndexFlag;
            db.collection("PixelFour")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d("파이어베이스", document.getId() + " => " + document.getData());
                                    String si=Integer.toString(finalI);
                                    if(document.getData().get("position").toString().equals(si)){
                                        Log.d("파이어베이스", Integer.toString(finalI)+" == " + document.getData().get("position"));
                                        switch (document.getData().get("image").toString()){

                                            case "0":
                                                imageIndexFlag =0;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "1":
                                                imageIndexFlag =1;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "2":
                                                imageIndexFlag =2;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "3":
                                                imageIndexFlag =3;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "4":
                                                imageIndexFlag =4;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                        }
                                    }else{
                                        Log.d("파이어베이스", Integer.toString(finalI)+" != " + document.getData().get("position"));
                                        imageIndexFlag =5;
                                        Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                    }
                                    adapter4.addItem(ContextCompat.getDrawable(getActivity(), imgPixel[imageIndexFlag]));
                                }//end of for


                                listView4.setAdapter(adapter4);

                            } else {
                                Log.w("파이어베이스", "Error getting documents.", task.getException());
                            }
                        }
                    });
        }

        //listView5
        final ListView listView5 =(ListView)view.findViewById(R.id.pixel_listView_5);
        final ListViewAdapter adapter5 = new ListViewAdapter(getActivity(),5);
        for(int i=0;i<31;i++){
            final int finalI = i;
            final int imageIndex=imageIndexFlag;
            db.collection("PixelFive")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d("파이어베이스", document.getId() + " => " + document.getData());
                                    String si=Integer.toString(finalI);
                                    if(document.getData().get("position").toString().equals(si)){
                                        Log.d("파이어베이스", Integer.toString(finalI)+" == " + document.getData().get("position"));
                                        switch (document.getData().get("image").toString()){

                                            case "0":
                                                imageIndexFlag =0;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "1":
                                                imageIndexFlag =1;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "2":
                                                imageIndexFlag =2;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "3":
                                                imageIndexFlag =3;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "4":
                                                imageIndexFlag =4;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                        }
                                    }else{
                                        Log.d("파이어베이스", Integer.toString(finalI)+" != " + document.getData().get("position"));
                                        imageIndexFlag =5;
                                        Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                    }
                                    adapter5.addItem(ContextCompat.getDrawable(getActivity(), imgPixel[imageIndexFlag]));
                                }//end of for


                                listView5.setAdapter(adapter5);

                            } else {
                                Log.w("파이어베이스", "Error getting documents.", task.getException());
                            }
                        }
                    });
        }

        //listView6
        final ListView listView6 =(ListView)view.findViewById(R.id.pixel_listView_6);
        final ListViewAdapter adapter6 = new ListViewAdapter(getActivity(),6);
        for(int i=0;i<30;i++){
            final int finalI = i;
            final int imageIndex=imageIndexFlag;
            db.collection("PixelSix")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d("파이어베이스", document.getId() + " => " + document.getData());
                                    String si=Integer.toString(finalI);
                                    if(document.getData().get("position").toString().equals(si)){
                                        Log.d("파이어베이스", Integer.toString(finalI)+" == " + document.getData().get("position"));
                                        switch (document.getData().get("image").toString()){

                                            case "0":
                                                imageIndexFlag =0;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "1":
                                                imageIndexFlag =1;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "2":
                                                imageIndexFlag =2;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "3":
                                                imageIndexFlag =3;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "4":
                                                imageIndexFlag =4;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                        }
                                    }else{
                                        Log.d("파이어베이스", Integer.toString(finalI)+" != " + document.getData().get("position"));
                                        imageIndexFlag =5;
                                        Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                    }
                                    adapter6.addItem(ContextCompat.getDrawable(getActivity(), imgPixel[imageIndexFlag]));
                                }//end of for


                                listView6.setAdapter(adapter6);

                            } else {
                                Log.w("파이어베이스", "Error getting documents.", task.getException());
                            }
                        }
                    });
        }

        //listView7
        final ListView listView7 =(ListView)view.findViewById(R.id.pixel_listView_7);
        final ListViewAdapter adapter7 = new ListViewAdapter(getActivity(),7);
        for(int i=0;i<31;i++){
            final int finalI = i;
            final int imageIndex=imageIndexFlag;
            db.collection("PixelSeven")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d("파이어베이스", document.getId() + " => " + document.getData());
                                    String si=Integer.toString(finalI);
                                    if(document.getData().get("position").toString().equals(si)){
                                        Log.d("파이어베이스", Integer.toString(finalI)+" == " + document.getData().get("position"));
                                        switch (document.getData().get("image").toString()){

                                            case "0":
                                                imageIndexFlag =0;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "1":
                                                imageIndexFlag =1;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "2":
                                                imageIndexFlag =2;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "3":
                                                imageIndexFlag =3;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "4":
                                                imageIndexFlag =4;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                        }
                                    }else{
                                        Log.d("파이어베이스", Integer.toString(finalI)+" != " + document.getData().get("position"));
                                        imageIndexFlag =5;
                                        Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                    }
                                    adapter7.addItem(ContextCompat.getDrawable(getActivity(), imgPixel[imageIndexFlag]));
                                }//end of for


                                listView7.setAdapter(adapter7);

                            } else {
                                Log.w("파이어베이스", "Error getting documents.", task.getException());
                            }
                        }
                    });
        }

        //listView8
        final ListView listView8 =(ListView)view.findViewById(R.id.pixel_listView_8);
        final ListViewAdapter adapter8 = new ListViewAdapter(getActivity(),8);
        for(int i=0;i<31;i++){
            final int finalI = i;
            final int imageIndex=imageIndexFlag;
            db.collection("PixelEight")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d("파이어베이스", document.getId() + " => " + document.getData());
                                    String si=Integer.toString(finalI);
                                    if(document.getData().get("position").toString().equals(si)){
                                        Log.d("파이어베이스", Integer.toString(finalI)+" == " + document.getData().get("position"));
                                        switch (document.getData().get("image").toString()){

                                            case "0":
                                                imageIndexFlag =0;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "1":
                                                imageIndexFlag =1;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "2":
                                                imageIndexFlag =2;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "3":
                                                imageIndexFlag =3;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "4":
                                                imageIndexFlag =4;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                        }
                                    }else{
                                        Log.d("파이어베이스", Integer.toString(finalI)+" != " + document.getData().get("position"));
                                        imageIndexFlag =5;
                                        Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                    }
                                    adapter8.addItem(ContextCompat.getDrawable(getActivity(), imgPixel[imageIndexFlag]));
                                }//end of for


                                listView8.setAdapter(adapter8);

                            } else {
                                Log.w("파이어베이스", "Error getting documents.", task.getException());
                            }
                        }
                    });
        }

        //listView9
        final ListView listView9 =(ListView)view.findViewById(R.id.pixel_listView_9);
        final ListViewAdapter adapter9 = new ListViewAdapter(getActivity(),9);
        for(int i=0;i<30;i++){
            final int finalI = i;
            final int imageIndex=imageIndexFlag;
            db.collection("PixelNine")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d("파이어베이스", document.getId() + " => " + document.getData());
                                    String si=Integer.toString(finalI);
                                    if(document.getData().get("position").toString().equals(si)){
                                        Log.d("파이어베이스", Integer.toString(finalI)+" == " + document.getData().get("position"));
                                        switch (document.getData().get("image").toString()){

                                            case "0":
                                                imageIndexFlag =0;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "1":
                                                imageIndexFlag =1;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "2":
                                                imageIndexFlag =2;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "3":
                                                imageIndexFlag =3;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "4":
                                                imageIndexFlag =4;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                        }
                                    }else{
                                        Log.d("파이어베이스", Integer.toString(finalI)+" != " + document.getData().get("position"));
                                        imageIndexFlag =5;
                                        Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                    }
                                    adapter9.addItem(ContextCompat.getDrawable(getActivity(), imgPixel[imageIndexFlag]));
                                }//end of for


                                listView9.setAdapter(adapter9);

                            } else {
                                Log.w("파이어베이스", "Error getting documents.", task.getException());
                            }
                        }
                    });
        }

        //listView10
        final ListView listView10 =(ListView)view.findViewById(R.id.pixel_listView_10);
        final ListViewAdapter adapter10 = new ListViewAdapter(getActivity(),10);
        for(int i=0;i<31;i++){
            final int finalI = i;
            final int imageIndex=imageIndexFlag;
            db.collection("PixelTen")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d("파이어베이스", document.getId() + " => " + document.getData());
                                    String si=Integer.toString(finalI);
                                    if(document.getData().get("position").toString().equals(si)){
                                        Log.d("파이어베이스", Integer.toString(finalI)+" == " + document.getData().get("position"));
                                        switch (document.getData().get("image").toString()){

                                            case "0":
                                                imageIndexFlag =0;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "1":
                                                imageIndexFlag =1;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "2":
                                                imageIndexFlag =2;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "3":
                                                imageIndexFlag =3;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "4":
                                                imageIndexFlag =4;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                        }
                                    }else{
                                        Log.d("파이어베이스", Integer.toString(finalI)+" != " + document.getData().get("position"));
                                        imageIndexFlag =5;
                                        Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                    }
                                    adapter10.addItem(ContextCompat.getDrawable(getActivity(), imgPixel[imageIndexFlag]));
                                }//end of for


                                listView10.setAdapter(adapter10);

                            } else {
                                Log.w("파이어베이스", "Error getting documents.", task.getException());
                            }
                        }
                    });
        }

        //listView11
        final ListView listView11 =(ListView)view.findViewById(R.id.pixel_listView_11);
        final ListViewAdapter adapter11 = new ListViewAdapter(getActivity(),11);
        for(int i=0;i<30;i++){
            final int finalI = i;
            final int imageIndex=imageIndexFlag;
            db.collection("PixelEleven")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d("파이어베이스", document.getId() + " => " + document.getData());
                                    String si=Integer.toString(finalI);
                                    if(document.getData().get("position").toString().equals(si)){
                                        Log.d("파이어베이스", Integer.toString(finalI)+" == " + document.getData().get("position"));
                                        switch (document.getData().get("image").toString()){

                                            case "0":
                                                imageIndexFlag =0;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "1":
                                                imageIndexFlag =1;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "2":
                                                imageIndexFlag =2;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "3":
                                                imageIndexFlag =3;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "4":
                                                imageIndexFlag =4;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                        }
                                    }else{
                                        Log.d("파이어베이스", Integer.toString(finalI)+" != " + document.getData().get("position"));
                                        imageIndexFlag =5;
                                        Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                    }
                                    adapter11.addItem(ContextCompat.getDrawable(getActivity(), imgPixel[imageIndexFlag]));
                                }//end of for


                                listView11.setAdapter(adapter11);

                            } else {
                                Log.w("파이어베이스", "Error getting documents.", task.getException());
                            }
                        }
                    });
        }

        //listView12
        final ListView listView12 =(ListView)view.findViewById(R.id.pixel_listView_12);
        final ListViewAdapter adapter12 = new ListViewAdapter(getActivity(),12);
        for(int i=0;i<31;i++){
            final int finalI = i;
            final int imageIndex=imageIndexFlag;
            db.collection("PixelTweleve")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d("파이어베이스", document.getId() + " => " + document.getData());
                                    String si=Integer.toString(finalI);
                                    if(document.getData().get("position").toString().equals(si)){
                                        Log.d("파이어베이스", Integer.toString(finalI)+" == " + document.getData().get("position"));
                                        switch (document.getData().get("image").toString()){

                                            case "0":
                                                imageIndexFlag =0;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "1":
                                                imageIndexFlag =1;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "2":
                                                imageIndexFlag =2;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "3":
                                                imageIndexFlag =3;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                            case "4":
                                                imageIndexFlag =4;
                                                Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                                break;
                                        }
                                    }else{
                                        Log.d("파이어베이스", Integer.toString(finalI)+" != " + document.getData().get("position"));
                                        imageIndexFlag =5;
                                        Log.d("파이어베이스",si+"=>"+imageIndexFlag+"");
                                    }
                                    adapter12.addItem(ContextCompat.getDrawable(getActivity(), imgPixel[imageIndexFlag]));
                                }//end of for


                                listView12.setAdapter(adapter12);

                            } else {
                                Log.w("파이어베이스", "Error getting documents.", task.getException());
                            }
                        }
                    });
        }

        return view;
    }

}
