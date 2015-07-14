package io.c0nnector.github.sample;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.easyrecyclerview.easyrecyclerview.BaseViewHolder;
import com.easyrecyclerview.easyrecyclerview.ListCallbacks;
import com.easyrecyclerview.easyrecyclerview.single.EasyAdapter;
import com.easyrecyclerview.easyrecyclerview.views.EasyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.c0nnector.github.sample.holdr.Holdr_ListItemRow;

public class MainActivity extends AppCompatActivity implements ListCallbacks<Holdr_ListItemRow>{

    EasyAdapter<Holdr_ListItemRow, ListItem> adapter;

    EasyRecyclerView easyRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_easyrecyclerview);

        startTimer(10, new TimerListener() {
            @Override
            public void onFinish() {
                setupAdapter();
            }
        });
    }



    public void setupAdapter(){

        easyRecyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);

        adapter = new EasyAdapter.Builder<Holdr_ListItemRow, ListItem>()
                .layoutId(Holdr_ListItemRow.LAYOUT)
                .cls(Holdr_ListItemRow.class)
                .items(getItems())
                .listCallbacks(this)
                .build(this);

        easyRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onBindViewHolder(Holdr_ListItemRow holder, int position) {

        ListItem item = adapter.getItem(position);

        holder.txtName.setText(item.getRowName());
    }

    public List<ListItem> getItems(){

        List<ListItem> items = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            items.add(new ListItem("position " + i));
        }

        return items;
    }

    /*****************************************************
     * ---------------- * Timer * --------------------
     *
     *
     *
     ****************************************************/

    public void startTimer(int sec, final TimerListener listener){
        new CountDownTimer(sec * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                listener.onFinish();
            }
        }.start();
    }

    public interface TimerListener {
        void onFinish();
    }

}
