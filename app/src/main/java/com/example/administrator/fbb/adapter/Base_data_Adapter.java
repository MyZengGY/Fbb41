package com.example.administrator.fbb.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.fbb.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/27 0027.
 */

public class Base_data_Adapter extends BaseAdapter {
    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<String> list1 = new ArrayList<>();
    private Context context;

    public Base_data_Adapter(ArrayList<String> list, ArrayList<String> list1, Context context) {
        this.list = list;
        this.list1 = list1;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = View.inflate(context, R.layout.data_item,null);
        TextView text= (TextView) item.findViewById(R.id.text_data_item);
        TextView text1= (TextView) item.findViewById(R.id.text_data_item_);
        text1.setText(list1.get(position));
        text.setText(list.get(position));
        return item;
    }
}
