package com.example.administrator.fbb.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.fbb.R;
import com.example.administrator.fbb.adapter.First_Fragment_MyAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/27 0027.
 */

public class FirstListFragment extends Fragment {
private RecyclerView recyclerView_jh;
    private First_Fragment_MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
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
        recyclerView_jh= (RecyclerView) view.findViewById(R.id.recyclerView_jh);
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
        mAdapter = new First_Fragment_MyAdapter(list);
        recyclerView_jh.setAdapter(mAdapter);
        return view;
    }
}
