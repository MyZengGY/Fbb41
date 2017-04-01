package com.example.administrator.fbb.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.fbb.R;
import com.example.administrator.fbb.adapter.First_Fragment_MyAdapter;
import com.example.administrator.fbb.tool.ParallaxRecyclerView;
import com.example.administrator.fbb.tool.RecyclerItemClickListener;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/27 0027.
 */

public class FirstListFragment extends Fragment {
    private ParallaxRecyclerView recyclerView_jh;
    private First_Fragment_MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private int mParam1;
    public static FirstListFragment newInstance(int index) {
        FirstListFragment f = new FirstListFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fh_fragment, container, false);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt("index");
            Log.e("，大叔大妈sd卡吗e", "onCreateView: "+mParam1);
        }

        recyclerView_jh= (ParallaxRecyclerView) view.findViewById(R.id.recyclerView_jh);
        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView_jh.setLayoutManager(mLayoutManager);
//如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView_jh.setHasFixedSize(true);
//创建并设置Adapter
        ArrayList<String> list=new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("1");
        }
        switch (mParam1){
            case 1:
                reItemTL(1);
                break;
            case 2:
                reItemTL(2);
                break;
            case 3:
                reItemTL(3);
                break;
            case 4:
                reItemTL(4);
                break;
        }


        mAdapter = new First_Fragment_MyAdapter(list,mParam1);
        recyclerView_jh.setAdapter(mAdapter);
        return view;
    }

    private void reItemTL(final int a) {
        recyclerView_jh.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(getContext(), a+"position:" + position, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLongClick(View view, int position) {
                        Toast.makeText(getContext(), a+"position:大大大大" + position, Toast.LENGTH_SHORT).show();
                    }
                }));
    }
}
