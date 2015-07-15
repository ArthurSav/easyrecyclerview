package com.easyrecyclerview.sample.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.easyrecyclerview.easyrecyclerview.ListCallbacks;
import com.easyrecyclerview.easyrecyclerview.single.EasyAdapter;
import com.easyrecyclerview.easyrecyclerview.views.EasyRecyclerView;
import com.easyrecyclerview.sample.R;
import com.easyrecyclerview.sample.holdr.Holdr_ListItemRow;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements ListCallbacks<Holdr_ListItemRow>{

    EasyAdapter<Holdr_ListItemRow, ListItem> adapter;


    EasyRecyclerView easyRecyclerView;
    //RelativeListOverlay relativeListOverlay;


    Button btnClear;
    Button btnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overlay_recyclerview);

        setupViews();

        setupAdapter();
    }

    public void setupViews(){

        easyRecyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
        //relativeListOverlay = (RelativeListOverlay) findViewById(R.id.relativeListOverlay);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        //clear list
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.replaceItems(getItems(0));
            }
        });

        //add list items
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.replaceItems(getItems(50));
            }
        });
    }

    public void setupAdapter(){

        //add empty view
//        relativeListOverlay.setRecyclerViewOverlay(easyRecyclerView);
//        relativeListOverlay.setEmptyView("OMG EMPTY");

        adapter = new EasyAdapter.Builder<Holdr_ListItemRow, ListItem>()
                .layoutId(Holdr_ListItemRow.LAYOUT)
                .cls(Holdr_ListItemRow.class)
                .items(getItems(4))
                .listCallbacks(this)
                .build(this);

        easyRecyclerView.setAdapter(adapter);
    }

    public ArrayList<ListItem> getItems(int count){

        ArrayList<ListItem> items = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            items.add(new ListItem("position " + i));
        }

        return items;
    }

    /*****************************************************
     * ---------------- * List * --------------------
     *
     *
     *
     ****************************************************/


    @Override
    public void onBindViewHolder(Holdr_ListItemRow holder, int position) {

        ListItem item = adapter.getItem(position);

        holder.txtName.setText(item.getRowName());
    }
}
