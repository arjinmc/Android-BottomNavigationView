package com.arjinmc.bottomnavigationviewdemo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.arjinmc.expandrecyclerview.adapter.RecyclerViewAdapter;
import com.arjinmc.expandrecyclerview.adapter.RecyclerViewSingleTypeProcessor;
import com.arjinmc.expandrecyclerview.adapter.RecyclerViewViewHolder;
import com.arjinmc.expandrecyclerview.style.RecyclerViewStyleHelper;
import com.arjinmc.recyclerviewdecoration.RecyclerViewLinearItemDecoration;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private Integer[] mList = new Integer[]{R.string.main_list_bottom_navigation_view};

    private RecyclerView mRvData;
    private RecyclerViewAdapter mDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRvData = findViewById(R.id.rv_data);
        RecyclerViewStyleHelper.toLinearLayout(mRvData, RecyclerView.VERTICAL);
        mRvData.addItemDecoration(new RecyclerViewLinearItemDecoration.Builder(this).color(Color.BLACK).thickness(2).create());

        mDataAdapter = new RecyclerViewAdapter<>(this, Arrays.asList(mList), R.layout.item_main_list
                , new RecyclerViewSingleTypeProcessor<Integer>() {
            @Override
            public void onBindViewHolder(RecyclerViewViewHolder holder, final int position, final Integer resId) {
                TextView textView = holder.getView(R.id.tv_name);
                textView.setText(resId);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (resId) {
                            case R.string.main_list_bottom_navigation_view:
                                openActivity(BottomNavigationViewActivity.class);
                                break;
                            default:
                                break;
                        }
                    }
                });
            }
        });
        mRvData.setAdapter(mDataAdapter);
    }

    public void openActivity(Class clz) {
        startActivity(new Intent(this, clz));
    }
}
