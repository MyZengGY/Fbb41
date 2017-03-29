package com.example.administrator.fbb.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.fbb.R;
import com.example.administrator.fbb.adapter.MyPagerAdapter;
import com.example.administrator.fbb.fragment.TaskFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/3/27 0027.
 */

public class TaskActivity1 extends AppCompatActivity {
    @InjectView(R.id.tabs_task)
    TabLayout tabsTask;
    @InjectView(R.id.viewPager_task)
    ViewPager viewPagerTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        ButterKnife.inject(this);
        tab_viewPager();
    }
    private void tab_viewPager() {
        //mTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);//挤在一起显示
        setupViewPager(viewPagerTask);
        tabsTask.setupWithViewPager(viewPagerTask);

    }
    private void setupViewPager(ViewPager viewpager) {
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(TaskFragment.newInstance(1), "微信");
        adapter.addFragment(TaskFragment.newInstance(2), "微博");
        adapter.addFragment(TaskFragment.newInstance(3), "淘宝");
        adapter.addFragment(TaskFragment.newInstance(4), "其他");
        viewpager.setAdapter(adapter);
    }
}
