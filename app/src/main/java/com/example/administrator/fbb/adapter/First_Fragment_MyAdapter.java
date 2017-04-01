package com.example.administrator.fbb.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.fbb.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/27 0027.
 */

public class First_Fragment_MyAdapter extends RecyclerView.Adapter<First_Fragment_MyAdapter.ViewHolder> {
    public List<String> datas = new ArrayList();
    private int hh;

    public First_Fragment_MyAdapter(List<String> datas, int hh) {
        this.datas = datas;
        this.hh = hh;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }
    //将数据与界面进行绑定的操作

    @Override
    public void onBindViewHolder(ViewHolder viewHolder,final int position) {
        datas.clear();
        switch (hh){
        case 1:
            for (int i = 0; i < 20; i++) {
                datas.add("0");
            }

        viewHolder.mTextView.setText(datas.get(position));
            break;
            case 2:
                for (int i = 0; i < 20; i++) {
                    datas.add("1");
                }
                viewHolder.mTextView.setText(datas.get(position));
                break;
            case 3:
                for (int i = 0; i < 20; i++) {
                    datas.add("2");
                }
                viewHolder.mTextView.setText(datas.get(position));
                break;
            case 4:
                for (int i = 0; i < 20; i++) {
                    datas.add("3");
                }
                viewHolder.mTextView.setText(datas.get(position));
                break;
        }
    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(View view){
            super(view);
            mTextView = (TextView) view.findViewById(R.id.text_title);
        }
    }
}
