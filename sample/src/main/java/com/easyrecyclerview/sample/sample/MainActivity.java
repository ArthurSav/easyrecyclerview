package com.easyrecyclerview.sample.sample;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.easyrecyclerview.easyrecyclerview.ListCallbacks;
import com.easyrecyclerview.easyrecyclerview.single.EasyAdapter;
import com.easyrecyclerview.easyrecyclerview.views.EasyRecyclerView;
import com.easyrecyclerview.sample.R;
import com.easyrecyclerview.sample.holdr.Holdr_ListItemRow;

import java.util.ArrayList;
import java.util.List;

import io.c0nnector.easyoverlay.RelativeListOverlay;


public class MainActivity extends AppCompatActivity implements ListCallbacks<Holdr_ListItemRow>{

    EasyAdapter<Holdr_ListItemRow, ListItem> adapter;

    EasyRecyclerView easyRecyclerView;

    RelativeListOverlay relativeListOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overlay_recyclerview);

        Button button = (Button) findViewById(R.id.button);
        Button buttonAdd = (Button) findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.replaceItems(getItems(0));
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.replaceItems(getItems(50));
            }
        });

        setupAdapter();
    }

    public void setupAdapter(){

        easyRecyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);

        relativeListOverlay = (RelativeListOverlay) findViewById(R.id.relativeListOverlay);
        relativeListOverlay.setRecyclerViewOverlay(easyRecyclerView);
        relativeListOverlay.setEmptyView("OMG EMPTY");

        adapter = new EasyAdapter.Builder<Holdr_ListItemRow, ListItem>()
                .layoutId(Holdr_ListItemRow.LAYOUT)
                .cls(Holdr_ListItemRow.class)
                .items(getItems(4))
                .listCallbacks(this)
                .build(this);

        easyRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onBindViewHolder(Holdr_ListItemRow holder, int position) {

        ListItem item = adapter.getItem(position);

        holder.txtName.setText(item.getRowName());
    }

    public ArrayList<ListItem> getItems(int count){

        ArrayList<ListItem> items = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            items.add(new ListItem("position " + i));
        }

        return items;
    }

    public CountDownTimer startTimer(int seconds, final TimerListener listener){
        return new CountDownTimer(1000*seconds, 1000) {
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
