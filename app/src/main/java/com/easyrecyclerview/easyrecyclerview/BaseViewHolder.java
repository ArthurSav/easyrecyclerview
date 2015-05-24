package com.easyrecyclerview.easyrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Base class for recycle view holder
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    private View v;

    public BaseViewHolder(View itemView) {
        super(itemView);

        this.v = itemView;
    }

    public View getView() {
        return v;
    }


}
