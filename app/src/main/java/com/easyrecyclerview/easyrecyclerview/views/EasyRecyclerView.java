package com.easyrecyclerview.easyrecyclerview.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;


/**
 * An enhanced version of the recyclerview for easier use
 */
public class EasyRecyclerView extends RecyclerView {

    AdapterChangeListener listener;

    LinearLayoutManager manager;


    public EasyRecyclerView(Context context) {
        super(context);
    }

    public EasyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setupManager(context);
    }

    protected void setupManager(Context context) {

        manager = new LinearLayoutManager(context);
        manager.setOrientation(OrientationHelper.VERTICAL);

        beforeManagerSetup(manager);

        setLayoutManager(manager);
    }

    /**
     * Use it to customize the layout manager before we set it up
     *
     * @param linearLayoutManager
     */
    public void beforeManagerSetup(LinearLayoutManager linearLayoutManager) {
    }

    /*****************************************************
     * ---------------- * Divider * --------------------
     ****************************************************/

    public void setDivider(boolean set) {
        if (set)
            addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext()).build());
    }

    public void setDivider(HorizontalDividerItemDecoration horizontalDividerItemDecoration) {
        addItemDecoration(horizontalDividerItemDecoration);
    }


    /**
     * Scrolls to last position
     */
    public void scrollToLast() {

        Adapter adapter = getAdapter();

        if (adapter != null) {

            int count = adapter.getItemCount();

            //convert size to positions
            count = count > 0 ? count - 1 : 0;

            scrollToPosition(count);
        }

    }


    /*****************************************************
     * ---------------- * View overlays * --------------------
     ****************************************************/

    public void setAdapterChangeListener(AdapterChangeListener listener){
        this.listener = listener;
    }

    final private AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            checkIfEmpty();
        }
    };

    /**
     * Called when there are changes in the adapter size.
     * Make sure you register a listener
     */
    void checkIfEmpty() {

        Adapter adapter = getAdapter();

        if (adapter != null && listener !=null) {

           listener.onAdapterChange(adapter, adapter.getItemCount());
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {

        final Adapter oldAdapter = getAdapter();

        if (oldAdapter != null) {
            oldAdapter.unregisterAdapterDataObserver(observer);
        }

        super.setAdapter(adapter);

        if (adapter != null) {
            adapter.registerAdapterDataObserver(observer);
        }

        checkIfEmpty();
    }


    public interface AdapterChangeListener {
        void onAdapterChange(@NonNull Adapter adapter, int count);
    }

}
