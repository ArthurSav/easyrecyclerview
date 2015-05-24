package com.easyrecyclerview.easyrecyclerview.multi;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;

import com.easyrecyclerview.easyrecyclerview.BaseRecyclerAdapter;
import com.easyrecyclerview.easyrecyclerview.BaseViewHolder;
import com.easyrecyclerview.easyrecyclerview.ListCallbacks;


/**
 * Created by arthur on 5/22/15.
 */
public abstract class BaseBinder<ViewHolder extends BaseViewHolder>  {

    ListCallbacks<ViewHolder> callbacks;


    public BaseBinder(ListCallbacks<ViewHolder> callbacks) {
        this.callbacks = callbacks;
    }

    public BaseBinder() {
    }

    public ViewHolder getViewHolder(ViewGroup parent, @LayoutRes int layoutId, Class<ViewHolder> cls){
        return BaseRecyclerAdapter.getViewHolder(parent, layoutId, cls);
    }

    public abstract ViewHolder getViewHolder(ViewGroup parent);

    public abstract int getViewType();

    public void onBindViewHolder(ViewHolder holder, int position){
        if (callbacks !=null){
            callbacks.onBindViewHolder(holder, position);
        }
    }

}
