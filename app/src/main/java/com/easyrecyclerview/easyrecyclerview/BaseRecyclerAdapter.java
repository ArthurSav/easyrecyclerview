package com.easyrecyclerview.easyrecyclerview;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Base class to be extended by recycler adapters
 */
public abstract class BaseRecyclerAdapter<ViewHolder extends BaseViewHolder, Item> extends RecyclerView.Adapter<ViewHolder> {

    protected Activity activity;


    /**
     * Constructor
     *
     * @param activity
     */
    public BaseRecyclerAdapter(Activity activity) {
        this.activity = activity;
    }

    /**
     * Creates a view holder for the onCreateViewHolder() method
     * Helps with view holder abstraction
     *
     * @param viewGroup
     * @param viewId
     * @param viewHolderClass
     * @param <T>
     * @return
     */
    public static <T extends BaseViewHolder> T getViewHolder(ViewGroup viewGroup, @LayoutRes int viewId, Class<T> viewHolderClass) {

        //create view from layout
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(viewId, viewGroup, false);

        //View holder class
        T holder = null;

        try {

            //create new instance of the given holder class. Invoking constructor with generics
            holder = viewHolderClass.getDeclaredConstructor(View.class).newInstance(v);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return holder;
    }


    public abstract List<Item> getItems();

    public abstract Item getItem(int position);

    /*****************************************************
     * ---------------- * Helpers * --------------------
     *
     *
     *
     ****************************************************/

    public int getItemPositions() {
        return getItemCount() > 0? getItemCount() - 1 : 0;
    }

    @Override
    public int getItemCount() {
        return getSize(getItems());
    }


    public <T> int getSize(List<T> list) {
        return list != null ? list.size() : 0;
    }

    /**
     * **************************************************
     * ---------------- * Other * --------------------
     * <p/>
     * <p/>
     * <p/>
     * **************************************************
     */

    /**
     * Will add new items to the current list
     * @param items
     */
    public void addItems(@NonNull List<Item> items) {

        int previousCount = getItemPositions();

        getItems().addAll(items);

        notifyItemRangeInserted(previousCount + 1, items.size());
    }

    /**
     * Adds a new item to the end of the list
     * @param item
     */
    public void addItem(Item item) {

        getItems().add(item);

        int size = getItemPositions();

        notifyItemInserted(size);

        notifyItemRangeChanged(size, 1);
    }

    /**
     * Adds a new item at a specified position
     * @param item
     * @param position
     */
    public void addItem(Item item, int position){

        getItems().add(position, item);

        notifyItemInserted(position);

        notifyItemRangeChanged(position, getItemCount());
    }

    /**
     * Replaces current list of items
     * @param items
     */
    public void replaceItems(@NonNull List<Item> items){

        getItems().clear();

        getItems().addAll(items);

        notifyDataSetChanged();
    }

    /**
     * Remove item from a certain position
     * @param position
     */
    public void removeItem(int position) {

        getItems().remove(position);

        notifyItemRemoved(position);

        notifyItemRangeChanged(position, getItemCount());
    }

    /**
     * Use this to refresh replaced items
     *
     * [NOTE] There's a bug when using notifyItemRangeChanged() with count=0 & position=0, so we have to use notifyDataSetChanged()
     */
    @Deprecated
    private void notifyItemsReplaced(){

        int count = getItemCount();

        if (count == 0) notifyDataSetChanged();

        else notifyItemRangeChanged(0, getItemCount());
    }

    /**
     * Returns true if the item exists in the list
     * @param position
     * @return
     */
    public boolean itemExists(int position) {

        int size = getItemCount() - 1;

        return position <= size;
    }



}
