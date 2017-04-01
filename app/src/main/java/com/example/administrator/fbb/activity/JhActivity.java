package com.example.administrator.fbb.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
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
    NavigationView navigationView;
    @InjectView(R.id.dl_left)
    DrawerLayout dlLeft;
    @InjectView(R.id.text_jh_city)
    TextView textJhCity;
    private long firstTime = 0;
    //声明相关变量
    private Toolbar toolbar;
    private Boolean mDrawer=false;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private String[] lvs = {"List Item 01", "List Item 02", "List Item 03", "List Item 04"};
    private int number = 0;
    private boolean isRunning = true;
    private String gg = "公告:";
    private String[] strings = {gg + "我的剑，就是你的剑!", gg + "俺也是从石头里蹦出来得!", gg + "我用双手成就你的梦想!", gg + "人在塔在!", gg + "犯我德邦者，虽远必诛!", gg + "我会让你看看什么叫残忍!", gg + "我的大刀早已饥渴难耐了!"};
    private AutoVerticalScrollTextView verticalScrollTV;
    public AMapLocationClient mLocationClient = null;
    public AMapLocationClientOption mLocationOption = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
//可在其中解析amapLocation获取相应内容。
                    Log.e("城市", aMapLocation.getCity());
                    aMapLocation.getProvince();//省信息
                    aMapLocation.getCity();//城市信息
                    textJhCity.setText(aMapLocation.getProvince()+" "+aMapLocation.getCity());
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }


        }
    };


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if(mDrawer){
                    mDrawerLayout.closeDrawers();
                    return true;
                }else {
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {                                         //如果两次按键时间间隔大于2秒，则不退出
                    Toast.makeText(this, "再次点击退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;//更新firstTime
                    return true;
                } else {                                                    //两次按键小于2秒时，退出应用
                    System.exit(0);
                }}
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jh_td);
        ButterKnife.inject(this);
        initView();
        findViews(); //获取控件
        tab_viewPager();
        initTND();
        initLocation();
    }

    private void initLocation() {
//初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
//设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
//设置定位模式为AMapLocationMode.Battery_Saving，低功耗模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
//获取一次定位结果：
//该方法默认为false。
        mLocationOption.setOnceLocation(true);

//获取最近3s内精度最高的一次定位结果：
//设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        mLocationClient.setLocationOption(mLocationOption);
//启动定位
        mLocationClient.startLocation();


    }


    private void initTND() {
        toolbar.setTitle("粉宝宝");//设置Toolbar标题
        toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                mDrawer=true;
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                mDrawer=false;
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        //设置菜单列表
        navigationView = (NavigationView) findViewById(R.id.navi);
        //图片本来颜色
        navigationView.setItemIconTintList(null);
        //获取头布局文件
        View headerView = navigationView.getHeaderView(0);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(JhActivity.this, ImageActivity1.class);
                startActivity(in);
            }
        });
        //点击事件
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                //在这里处理item的点击事件
                switch (item.getItemId()) {
                    case R.id.nav_camera:
                        Intent in = new Intent(JhActivity.this, MsgActivity1.class);
                        startActivity(in);
                        break;
                    case R.id.nav_gallery:
                        Intent in1 = new Intent(JhActivity.this, DataActivity1.class);
                        startActivity(in1);
                        break;
                    case R.id.nav_slideshow:
                        Intent in2 = new Intent(JhActivity.this, TaskActivity1.class);
                        startActivity(in2);
                        break;
                    case R.id.nav_manage:
                        Intent in3 = new Intent(JhActivity.this, AccountActivity1.class);
                        startActivity(in3);
                        break;
                    case R.id.nav_jbzz:
                        Intent in4 = new Intent(JhActivity.this, GoldActivity1.class);
                        startActivity(in4);
                        break;
                    case R.id.nav_aaa:
                        Intent in5 = new Intent(JhActivity.this, RankActivity1.class);
                        startActivity(in5);
                        break;
                    case R.id.nav_share:
                        Intent in6 = new Intent(JhActivity.this, MymsgActivity1.class);
                        startActivity(in6);
                        break;
                    case R.id.nav_send:
                        Intent in7 = new Intent(JhActivity.this, CallmeActivity1.class);
                        startActivity(in7);
                        break;

                }

                return true;
            }
        });
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

