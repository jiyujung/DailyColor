package org.techtown.dailycolorproject;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.icu.text.Transliterator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListViewAdapter extends BaseAdapter {

    private FirebaseDatabase database;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    ArrayList<ListItem> items = new ArrayList<ListItem>();
    View dialogView;
    Context context;
    int adapterName;

    public ListViewAdapter(Context context,int adapterName){
        this.context=context;
        this.adapterName=adapterName;
    }


    public void addItem(Drawable img){
        ListItem listItem = new ListItem();
        //ListItem에 아이템 세팅
        listItem.setIcon(img);
        //items에 listItem을 추가
        items.add(listItem);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public ListItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        database=FirebaseDatabase.getInstance();

        final int pos=position;
        final Context context=parent.getContext();//어플리케이션 정보를 담고 있음

        //"listview_item" Layout을 inflate하여 convertView 참조 획득.
        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.item_pixeldiary_2020,parent,false);
        }

        //화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        final ImageView iconImageView=(ImageView)convertView.findViewById(R.id.pixel);

        // Data Set(items -> 위에서 지정한 ArrayList)에서 position에 위치한 데이터 참조 획득
        //각 리스트에 뿌려줄 아이템 받아오는데 ListItem 재활용
        final ListItem listViewItem=getItem(position);

        //아이템 내 각 위젯에 데이터 반영
        iconImageView.setImageDrawable(listViewItem.getIcon());

        //이미지 클릭 시 적용되는 리스너
        iconImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //선택된 위치 값 logcat에 출력
                String s=Integer.toString(adapterName)+"/"+Integer.toString(position);
                Log.d("선택된 픽셀의 position 값",s);

                //toast
                Toast.makeText(context,"기분을 기분을 선택해주세요",Toast.LENGTH_SHORT).show();

                //dialog창 띄우기
                LayoutInflater inflater = LayoutInflater.from(context);
                dialogView = inflater.inflate(R.layout.dialog_pixeldiary_2020, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(context);

                dlg.setView(dialogView);

                //콜렉션 구별 위해 Adapter 생성할 때 따로 이름을 받아 분류
                String collectionName="";
                switch (adapterName){
                    case 1:
                        collectionName="PixelOne";
                        break;
                    case 2:
                        collectionName="PixelTwo";
                        break;
                    case 3:
                        collectionName="PixelThree";
                        break;
                    case 4:
                        collectionName="PixelFour";
                        break;
                    case 5:
                        collectionName="PixelFive";
                        break;
                    case 6:
                        collectionName="PixelSix";
                        break;
                    case 7:
                        collectionName="PixelSeven";
                        break;
                    case 8:
                        collectionName="PixelEight";
                        break;
                    case 9:
                        collectionName="PixelNine";
                        break;
                    case 10:
                        collectionName="PixelTen";
                        break;
                    case 11:
                        collectionName="PixelEleven";
                        break;
                    case 12:
                        collectionName="PixelTweleve";
                        break;
                }

                final String plg="false";

                //dialog에 있는 픽셀 색 버튼 클릭 이벤트
                Button button_red=dialogView.findViewById(R.id.dialog_red);
                final String finalCollectionName = collectionName;
                final String finalPlg=plg;
                button_red.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        //이미지 변경하고 저장
                        iconImageView.setImageResource(R.drawable.pixel_red);
                        final Map<String,Object> PixelMap=new HashMap<>();
                        db.collection(finalCollectionName).get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if(task.isSuccessful()){
                                            for (QueryDocumentSnapshot document : task.getResult()){
                                                Log.d("파이어베이스[ADD]", document.getId() + " => " + document.getData());
                                                String spos=Integer.toString(pos);//포지션
                                                if(!document.getData().get("position").toString().equals(spos)){
                                                    //선택된 포지션과 collection에서의 포지션 값 중 동일한 것이 있을 떄
                                                    //필드 값 수정해야 함(덮어쓰기)
                                                    Log.d("파이어베이스[ADD] start","position="+pos+" image="+0);

                                                    PixelMap.put("position",pos);
                                                    PixelMap.put("image",0);

                                                }else{
                                                    //데이터 있을 땐 추가 안함
                                                    return;
                                                }
                                            }//end of for
                                            db.collection(finalCollectionName).add(PixelMap);
                                            Log.d("파이어베이스[ADD] done","position="+pos+"image"+0);
                                        }
                                    }
                                });
                    }
                });
                Button button_yello=dialogView.findViewById(R.id.dialog_yello);
                button_yello.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        iconImageView.setImageResource(R.drawable.pixel_yello);
                        final Map<String,Object> PixelMap=new HashMap<>();
                        db.collection(finalCollectionName).get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if(task.isSuccessful()){
                                            for (QueryDocumentSnapshot document : task.getResult()){
                                                Log.d("파이어베이스[ADD]", document.getId() + " => " + document.getData());
                                                String spos=Integer.toString(pos);//포지션
                                                if(!document.getData().get("position").toString().equals(spos)){
                                                    //선택된 포지션과 collection에서의 포지션 값 중 동일한 것이 있을 떄
                                                    //필드 값 수정해야 함(덮어쓰기)
                                                    Log.d("파이어베이스[ADD] start","position="+pos+" image="+0);

                                                    PixelMap.put("position",pos);
                                                    PixelMap.put("image",1);

                                                }else{
                                                    //데이터 있을 땐 추가 안함
                                                    return;
                                                }
                                            }//end of for
                                            db.collection(finalCollectionName).add(PixelMap);
                                            Log.d("파이어베이스[ADD] done","position="+pos+"image"+0);
                                        }
                                    }
                                });
                    }
                });
                Button button_green=dialogView.findViewById(R.id.dialog_green);
                button_green.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        iconImageView.setImageResource(R.drawable.pixel_green);
                        final Map<String,Object> PixelMap=new HashMap<>();
                        db.collection(finalCollectionName).get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if(task.isSuccessful()){
                                            for (QueryDocumentSnapshot document : task.getResult()){
                                                Log.d("파이어베이스[ADD]", document.getId() + " => " + document.getData());
                                                String spos=Integer.toString(pos);//포지션
                                                if(!document.getData().get("position").toString().equals(spos)){
                                                    //선택된 포지션과 collection에서의 포지션 값 중 동일한 것이 있을 떄
                                                    //필드 값 수정해야 함(덮어쓰기)
                                                    Log.d("파이어베이스[ADD] start","position="+pos+" image="+0);

                                                    PixelMap.put("position",pos);
                                                    PixelMap.put("image",2);

                                                }else{
                                                    //데이터 있을 땐 추가 안함
                                                    return;
                                                }
                                            }//end of for
                                            db.collection(finalCollectionName).add(PixelMap);
                                            Log.d("파이어베이스[ADD] done","position="+pos+"image"+0);
                                        }
                                    }
                                });
                    }
                });
                Button button_darkblue=dialogView.findViewById(R.id.dialog_darkblue);
                button_darkblue.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        iconImageView.setImageResource(R.drawable.pixel_darkblue);
                        final Map<String,Object> PixelMap=new HashMap<>();
                        db.collection(finalCollectionName).get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if(task.isSuccessful()){
                                            for (QueryDocumentSnapshot document : task.getResult()){
                                                Log.d("파이어베이스[ADD]", document.getId() + " => " + document.getData());
                                                String spos=Integer.toString(pos);//포지션
                                                if(!document.getData().get("position").toString().equals(spos)){
                                                    //선택된 포지션과 collection에서의 포지션 값 중 동일한 것이 있을 떄
                                                    //필드 값 수정해야 함(덮어쓰기)
                                                    Log.d("파이어베이스[ADD] start","position="+pos+" image="+0);

                                                    PixelMap.put("position",pos);
                                                    PixelMap.put("image",3);

                                                }else{
                                                    //데이터 있을 땐 추가 안함
                                                    return;
                                                }
                                            }//end of for
                                            db.collection(finalCollectionName).add(PixelMap);
                                            Log.d("파이어베이스[ADD] done","position="+pos+"image"+0);
                                        }
                                    }
                                });
                    }
                });
                Button button_purple=dialogView.findViewById(R.id.dialog_purple);
                button_purple.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        iconImageView.setImageResource(R.drawable.pixel_purple);

                        final Map<String,Object> PixelMap=new HashMap<>();
                        db.collection(finalCollectionName).get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if(task.isSuccessful()){
                                            for (QueryDocumentSnapshot document : task.getResult()){
                                                Log.d("파이어베이스[ADD]", document.getId() + " => " + document.getData());
                                                String spos=Integer.toString(pos);//포지션
                                                if(!document.getData().get("position").toString().equals(spos)){
                                                    //선택된 포지션과 collection에서의 포지션 값 중 동일한 것이 있을 떄
                                                    //필드 값 수정해야 함(덮어쓰기)
                                                    Log.d("파이어베이스[ADD] start","position="+pos+" image="+0);

                                                    PixelMap.put("position",pos);
                                                    PixelMap.put("image",4);

                                                }else{
                                                    //데이터 있을 땐 추가 안함
                                                    return;
                                                }
                                            }//end of for
                                            db.collection(finalCollectionName).add(PixelMap);
                                            Log.d("파이어베이스[ADD] done","position="+pos+"image"+0);
                                        }
                                    }
                                });
                    }
                });
                //다이알로그 보이기
                dlg.show();
            }
        });//end of icon 클릭 리스너
        return convertView;//뷰 객체를 반환합니다.
    }
}
