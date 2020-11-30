package org.techtown.dailycolorproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Pixeldiary_fgprepare extends Fragment {
    private View view;

    public static Pixeldiary_fgprepare newInstance(){
        Pixeldiary_fgprepare Pixeldiary_fgprepare = new Pixeldiary_fgprepare();
        return Pixeldiary_fgprepare;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pixeldiary_prepare,container,false);
        return view;
    }
}
