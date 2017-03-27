package com.example.administrator.fbb.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.administrator.fbb.R;
import com.example.administrator.fbb.adapter.MyPagerAdapter;
import com.example.administrator.fbb.data.AutoVerticalScrollTextView;
import com.example.administrator.fbb.fragment.FirstListFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/3/21 0021.
 */


public class JhActivity extends ActionBarActivity {
    @InjectView(R.id.textview_auto_roll)
    AutoVerticalScrollTextView textviewAutoRoll;
    @InjectView(R.id.tabs_jh)
    TabLayout tabsJh;
    @InjectView(R.id.viewPager_jh)
    ViewPager viewPagerJh;
    @InjectView(R.id.navi)
    NavigationView navi;
    @InjectView(R.id.dl_left)
    DrawerLayout dlLeft;
    //声明相关变量
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private String[] lvs = {"List Item 01", "List Item 02", "List Item 03", "List Item 04"};
    private int number = 0;
    private boolean isRunning = true;
    private String gg = "公告:";
    private String[] strings = {gg + "我的剑，就是你的剑!", gg + "俺也是从石头里蹦出来得!", gg + "我用双手成就你的梦想!", gg + "人在塔在!", gg + "犯我德邦者，虽远必诛!", gg + "我会让你看看什么叫残忍!", gg + "我的大刀早已饥渴难耐了!"};
    private AutoVerticalScrollTextView verticalScrollTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jh_td);
        ButterKnife.inject(this);
        initView();
        findViews(); //获取控件
        tab_viewPager();
        toolbar.setTitle("Toolbar");//设置Toolbar标题
        toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        //设置菜单列表

    }

    private void initView() {
        verticalScrollTV = (AutoVerticalScrollTextView) findViewById(R.id.textview_auto_roll);
        verticalScrollTV.setText(strings[0]);

        new Thread() {
            @Override
            public void run() {
                while (isRunning) {
                    SystemClock.sleep(3000);
                    handler.sendEmptyMessage(199);
                }
            }
        }.start();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 199) {
                verticalScrollTV.next();
                number++;
                verticalScrollTV.setText(strings[number % strings.length]);
            }

        }
    };

    private void tab_viewPager() {
        tabsJh = (TabLayout) findViewById(R.id.tabs_jh);
        //mTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);//挤在一起显示
        viewPagerJh = (ViewPager) findViewById(R.id.viewPager_jh);
        setupViewPager(viewPagerJh);
        tabsJh.setupWithViewPager(viewPagerJh);

    }

    private void setupViewPager(ViewPager viewpager) {
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(FirstListFragment.newInstance(1), "微信");
        adapter.addFragment(FirstListFragment.newInstance(2), "微博");
        adapter.addFragment(FirstListFragment.newInstance(3), "淘宝");
        adapter.addFragment(FirstListFragment.newInstance(4), "其他");
        viewpager.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRunning = false;
    }

    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.tl_custom);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_left);

    }


}

