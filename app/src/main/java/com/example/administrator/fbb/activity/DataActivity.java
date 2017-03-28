package com.example.administrator.fbb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.fbb.R;
import com.example.administrator.fbb.adapter.Base_data_Adapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/27 0027.
 */

public class DataActivity extends AppCompatActivity {
    ListView data_Listview;
    ArrayList<String> list1=new ArrayList<>();
    ArrayList<String> list=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        data_Listview= (ListView) findViewById(R.id.data_Listview);
        Base_data_Adapter adapter=new Base_data_Adapter(getData(),getData1(),this);
        data_Listview.setAdapter(adapter);
        data_Listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                case 0:
                Intent in0=new Intent(DataActivity.this,GrdataActivity.class);
                startActivity(in0);
                break;
                case 1:
                    Intent in1=new Intent(DataActivity.this,VipActivity.class);
                    startActivity(in1);
                break;
                case 2:
                    Intent in2=new Intent(DataActivity.this,UserActivity.class);
                    startActivity(in2);
                        break;
                 case 3:

                        break;
                 case 4:

                        break;
                 case 5:

                        break;

                }
                Toast.makeText(DataActivity.this, "position:" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private ArrayList<String> getData(){
        list.add("个人信息");
        list.add("开通vip");
        list.add("账号管理");
        list.add("登录账号");
        list.add("推广密码");
        list.add("修改密码");
        return list;
    }
    private ArrayList<String> getData1(){
        list1.add("0%");
        list1.add(" ");
        list1.add(" ");
        list1.add("18482109559");
        list1.add("sdaf");
        list1.add(" ");
        return list1;
    }
}
