package com.easyrecyclerview.easyrecyclerview.single;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.easyrecyclerview.easyrecyclerview.BaseRecyclerAdapter;
import com.easyrecyclerview.easyrecyclerview.BaseViewHolder;
import com.easyrecyclerview.easyrecyclerview.ListCallbacks;

import java.util.ArrayList;
import java.util.List;

/**
 * Base recyclerview adapter
 */
public class EasyAdapter<ViewHolder extends BaseViewHolder, Item> extends BaseRecyclerAdapter<ViewHolder, Item> {


    /**
     * The list of items to display
     */
    private List<Item> items = new ArrayList<>();

    /**
     * Callbacks
     */
    private ListCallbacks<ViewHolder> listCallbacks;

    /**
     * Layout id for the list row
     */
    @LayoutRes
    private int layoutId;

    /**
     * View holder class
     */
    @NonNull
    private Class<? extends ViewHolder> cls;


    /**
     * Constructor
     *
     * @param activity
     */
    public EasyAdapter(Activity activity, Builder builder) {

        super(activity);
        this.items = builder.items;
        this.listCallbacks = builder.listCallbacks;
        this.layoutId = builder.layoutId;
        this.cls = builder.cls;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {

        return getViewHolder(parent, layoutId, cls);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        if (listCallbacks != null) listCallbacks.onBindViewHolder(viewHolder, i);
    }


    /*****************************************************
     * ---------------- * Setters * --------------------
     *
     *
     *
     ****************************************************/

    /**
     * View holder class We use it to construct the view holder via abstraction
     * onCreateViewHolder()
     *
     * @return
     */
    public EasyAdapter setViewHolderClass(Class<? extends ViewHolder> cls) {

        this.cls = cls;
        return this;
    }

    public EasyAdapter setLayoutId(@LayoutRes int layoutId) {

        this.layoutId = layoutId;
        return this;
    }

    public EasyAdapter setCallbacks(ListCallbacks<ViewHolder> callbacks) {

        this.listCallbacks = callbacks;
        return this;
    }

    @Override
    public List<Item> getItems() {

        return items;
    }

    /**
     * Set list items
     *
     * @param items items to display
     *
     * @return
     */

    public EasyAdapter setItems(@NonNull List<Item> items) {

        this.items = items;
        return this;
    }

    /*****************************************************
     * ---------------- * Getters * --------------------
     ****************************************************/


    @Override
    public Item getItem(int position) {

        return items.get(position);
    }

    /*****************************************************
     * ---------------- * Builder * --------------------
     ****************************************************/

    public static class Builder<ViewHolder extends BaseViewHolder, Item> {

        private List<Item> items;

        private ListCallbacks<ViewHolder> listCallbacks;

        private int layoutId;

        private Class<? extends ViewHolder> cls;

        public Builder items(List<Item> items) {

            this.items = items;
            return this;
        }

        public Builder listCallbacks(ListCallbacks<ViewHolder> listCallbacks) {

            this.listCallbacks = listCallbacks;
            return this;
        }

        public Builder layoutId(int layoutId) {

            this.layoutId = layoutId;
            return this;
        }

        public Builder cls(Class<? extends ViewHolder> cls) {

            this.cls = cls;
            return this;
        }

        public Builder fromPrototype(EasyAdapter prototype) {

            items = prototype.items;
            listCallbacks = prototype.listCallbacks;
            layoutId = prototype.layoutId;
            cls = prototype.cls;
            return this;
        }

        public EasyAdapter build(Activity activity) {

            return new EasyAdapter(activity, this);
        }
    }
}
