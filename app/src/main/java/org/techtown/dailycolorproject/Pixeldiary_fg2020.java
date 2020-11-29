package org.techtown.dailycolorproject;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pixeldiary_2020,container,false);

//        String[] menuItems={"","",""};
//        ListView listView=(ListView)view.findViewById(R.id.pixel_listView_1);
//        ArrayAdapter<String> listViewAdapter=new ArrayAdapter<String>(
//            getActivity(),android.R.layout.simple_list_item_1,menuItems
//        );
//        listView.setAdapter(listViewAdapter);
        // 빈 데이터 리스트 생성.
        final ArrayList<String> items = new ArrayList<String>() ;

        // ArrayAdapter 생성. 아이템 View를 선택(single choice)가능하도록 만듦.
        ListView listView1,listView2,listView3,listView4,listView5,listView6,listView7,listView8,listView9,listView10,listView11,listView12;
        ListViewAdapter adapter1,adapter2,adapter3,adapter4,adapter5,adapter6,adapter7,adapter8,adapter9,adapter10,adapter11,adapter12;

        //Adapter생성
        adapter1=new ListViewAdapter(getActivity());
        adapter2=new ListViewAdapter(getActivity());
        adapter3=new ListViewAdapter(getActivity());
        adapter4=new ListViewAdapter(getActivity());
        adapter5=new ListViewAdapter(getActivity());
        adapter6=new ListViewAdapter(getActivity());
        adapter7=new ListViewAdapter(getActivity());
        adapter8=new ListViewAdapter(getActivity());
        adapter9=new ListViewAdapter(getActivity());
        adapter10=new ListViewAdapter(getActivity());
        adapter11=new ListViewAdapter(getActivity());
        adapter12=new ListViewAdapter(getActivity());

        //listView1
        //리스트뷰 참조 및 Adapter달기
        listView1=(ListView)view.findViewById(R.id.pixel_listView_1);
        //이미지 아이템 추가
        for(int i=0;i<31;i++){
            adapter1.addItem(ContextCompat.getDrawable(getActivity(), drawable.pixel_white));
        }
        listView1.setAdapter(adapter1);

        //listView2
        listView2=(ListView)view.findViewById(R.id.pixel_listView_2);
        for(int i=0;i<29;i++){
            adapter2.addItem(ContextCompat.getDrawable(getActivity(), drawable.pixel_white));
        }
        listView2.setAdapter(adapter2);

        //listView3
        listView3=(ListView)view.findViewById(R.id.pixel_listView_3);
        for(int i=0;i<31;i++){
            adapter3.addItem(ContextCompat.getDrawable(getActivity(), drawable.pixel_white));
        }
        listView3.setAdapter(adapter3);

        //listView4
        listView4=(ListView)view.findViewById(R.id.pixel_listView_4);
        for(int i=0;i<30;i++){
            adapter4.addItem(ContextCompat.getDrawable(getActivity(), drawable.pixel_white));
        }
        listView4.setAdapter(adapter4);

        //listView5
        listView5=(ListView)view.findViewById(R.id.pixel_listView_5);
        for(int i=0;i<31;i++){
            adapter5.addItem(ContextCompat.getDrawable(getActivity(), drawable.pixel_white));
        }
        listView5.setAdapter(adapter5);

        //listView6
        listView6=(ListView)view.findViewById(R.id.pixel_listView_6);
        for(int i=0;i<30;i++){
            adapter6.addItem(ContextCompat.getDrawable(getActivity(), drawable.pixel_white));
        }
        listView6.setAdapter(adapter6);

        //listView7
        listView7=(ListView)view.findViewById(R.id.pixel_listView_7);
        for(int i=0;i<31;i++){
            adapter7.addItem(ContextCompat.getDrawable(getActivity(), drawable.pixel_white));
        }
        listView7.setAdapter(adapter7);

        //listView8
        listView8=(ListView)view.findViewById(R.id.pixel_listView_8);
        for(int i=0;i<31;i++){
            adapter8.addItem(ContextCompat.getDrawable(getActivity(), drawable.pixel_white));
        }
        listView8.setAdapter(adapter8);

        //listView9
        listView9=(ListView)view.findViewById(R.id.pixel_listView_9);
        for(int i=0;i<30;i++){
            adapter9.addItem(ContextCompat.getDrawable(getActivity(), drawable.pixel_white));
        }
        listView9.setAdapter(adapter9);

        //listView10
        listView10=(ListView)view.findViewById(R.id.pixel_listView_10);
        for(int i=0;i<31;i++){
            adapter10.addItem(ContextCompat.getDrawable(getActivity(), drawable.pixel_white));
        }
        listView10.setAdapter(adapter10);

        //listView11
        listView11=(ListView)view.findViewById(R.id.pixel_listView_11);
        for(int i=0;i<30;i++){
            adapter11.addItem(ContextCompat.getDrawable(getActivity(), drawable.pixel_white));
        }
        listView11.setAdapter(adapter11);

        //listView12
        listView12=(ListView)view.findViewById(R.id.pixel_listView_12);
        for(int i=0;i<31;i++){
            adapter12.addItem(ContextCompat.getDrawable(getActivity(), drawable.pixel_white));
        }
        listView12.setAdapter(adapter12);

        return view;
    }

}
