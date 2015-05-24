package com.easyrecyclerview.easyrecyclerview;

public interface ListCallbacks<Holder extends BaseViewHolder>{

    void onBindViewHolder(Holder holder, int position);
}
