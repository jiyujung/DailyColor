package org.techtown.dailycolorproject;

import android.graphics.drawable.Drawable;

public class ListItem {
    Drawable iconDrawable;

    public Drawable getIcon(){
        return iconDrawable;
    }
    public void setIcon(Drawable icon){
        this.iconDrawable=icon;
    }
}
