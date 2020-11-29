package org.techtown.dailycolorproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MainViewHolder> {
    private ArrayList<AddInfo> mDataset;
    private Activity activity;
    private FirebaseFirestore firebaseFirestore;
    public Context context;

    static class MainViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        MainViewHolder(Activity activity, CardView v, AddInfo post){
                super(v);
            cardView = v;
        }
    }

    public PostAdapter(Activity activity, ArrayList<AddInfo> myDataset) {
        mDataset = myDataset;
        this.activity = activity;
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public int getItemViewType(int position){
        return position;
    }


    @NonNull
    @Override
    public PostAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        final MainViewHolder mainViewHolder = new MainViewHolder(activity, cardView, mDataset.get(viewType)); //-------- mainViewHolder
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return mainViewHolder;

    }


    @Override
    public void onBindViewHolder(@NonNull final MainViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView titleTextView = cardView.findViewById(R.id.titleTextView);
        TextView contentsTextView = cardView.findViewById(R.id.contentTextView);
        TextView dateTextView = cardView.findViewById(R.id.dateTextView);
        ImageView showImg = cardView.findViewById(R.id.showImageView);
        titleTextView.setText(mDataset.get(position).getTitle());
        contentsTextView.setText(mDataset.get(position).getContent());
        dateTextView.setText(mDataset.get(position).getDate());

        if(mDataset.get(position).getShowImg() != null){
            Glide.with(activity).load(mDataset.get(position).getShowImg()).centerCrop().override(500).into(showImg);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}