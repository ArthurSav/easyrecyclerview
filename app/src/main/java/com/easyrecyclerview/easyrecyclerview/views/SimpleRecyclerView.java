package com.easyrecyclerview.easyrecyclerview.views;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

/**
 * Created by arthur on 5/24/15.
 */
public class SimpleRecyclerView extends RecyclerView {

    LinearLayoutManager manager;

    public SimpleRecyclerView(Context context) {
        super(context);
    }

    public SimpleRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setupManager(context);
    }

    private void setupManager(Context context){

        manager = new LinearLayoutManager(context);
        manager.setOrientation(OrientationHelper.VERTICAL);

        setLayoutManager(manager);
    }

    public void setDivider(boolean set){
        if (set) addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext()).build());
    }

    public void setDivider(HorizontalDividerItemDecoration horizontalDividerItemDecoration){
        addItemDecoration(horizontalDividerItemDecoration);
    }
}
