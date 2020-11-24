package org.techtown.dailycolorproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Pixeldiary_fg2020 extends Fragment {
    private View view;

    public static Pixeldiary_fg2020 newInstance(){
        Pixeldiary_fg2020 Pixeldiary_fg2020 = new Pixeldiary_fg2020();
        return Pixeldiary_fg2020;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pixeldiary_2020,container,false);
        return view;
    }
}
