package org.techtown.dailycolorproject;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    ArrayList<ListItem> items = new ArrayList<ListItem>();

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
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos=position;
        final Context context=parent.getContext();//어플리케이션 정보를 담고 있음

        //"listview_item" Layout을 inflate하여 convertView 참조 획득.
        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.item_pixeldiary_2020,parent,false);
        }

        //화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        ImageView iconImageView=(ImageView)convertView.findViewById(R.id.pixel);

        // Data Set(items -> 위에서 지정한 ArrayList)에서 position에 위치한 데이터 참조 획득
        //각 리스트에 뿌려줄 아이템 받아오는데 ListItem 재활용
        ListItem listViewItem=getItem(position);

        //아이템 내 각 위젯에 데이터 반영
        iconImageView.setImageDrawable(listViewItem.getIcon());

        return convertView;//뷰 객체를 반환합니다.
    }
}
