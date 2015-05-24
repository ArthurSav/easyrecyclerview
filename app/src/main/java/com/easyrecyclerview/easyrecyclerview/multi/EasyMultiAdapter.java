package com.easyrecyclerview.easyrecyclerview.multi;

import android.app.Activity;
import android.view.ViewGroup;

import com.yqritc.recyclerviewflexibledivider.sample.recyclerview.BaseRecyclerAdapter;
import com.yqritc.recyclerviewflexibledivider.sample.recyclerview.BaseViewHolder;

import java.util.ArrayList;

/**
 * This adapter displays multiple types of views in a recyclerview
 *
 * To setup:
 *
 * - You need to use addViewType() to add all the different types that we're going to display
 * - Set the list of items we're going to display, make sure that the item must implement ItemViewType, so we can access the viewType
 * -
 */
public class EasyMultiAdapter<Item extends ItemViewType> extends BaseRecyclerAdapter<BaseViewHolder, Item> {

    /**
     * Items to display
     */
    private ArrayList<Item> items;

    /**
     * Types of views
     */
    private ArrayList<BaseBinder> types;

    /**
     * Constructor
     *
     * @param activity
     */
    public EasyMultiAdapter(Activity activity, Builder builder) {
        super(activity);
        this.items = builder.items;
        this.types = builder.types;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        for (BaseBinder baseBinder: types){

            int baseBinderType = baseBinder.getViewType();

            if (baseBinderType == viewType) return baseBinder.getViewHolder(parent);
        }

        //todo - return empty view
        return null;
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        for (BaseBinder baseBinder: types){
            if (baseBinder.getViewType() == getItemViewType(position)) baseBinder.onBindViewHolder(holder, position);
        }
    }


    @Override
    public int getItemViewType(int position) {
        return getItem(position).getViewType();
    }


    @Override
    public Item getItem(int position) {
        return items.get(position);
    }

    @Override
    public ArrayList<Item> getItems(){
        return items;
    }

    /*****************************************************
     * ---------------- * Builder * --------------------
     *
     *
     *
     ****************************************************/

    public static class Builder<Item extends ItemViewType> {

        private ArrayList<Item> items;
        private ArrayList<BaseBinder> types = new ArrayList<>();


        public Builder items(ArrayList<Item> items) {
            this.items = items;
            return this;
        }

        public Builder types(ArrayList<BaseBinder> types) {
            this.types = types;
            return this;
        }

        public Builder type(BaseBinder binder){
            types.add(binder);
            return this;
        }

        public EasyMultiAdapter build(Activity activity) {

            if (this.items == null) this.items = new ArrayList<>();
            if (this.types == null || types.size() == 0) throw new RuntimeException("You should define at least one view type");

            return new EasyMultiAdapter(activity, this);
        }
    }



}
