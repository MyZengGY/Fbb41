package com.example.administrator.fbb.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/27 0027.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragment=new ArrayList<Fragment>();
    private final List<String> mFragmentTitle=new ArrayList<String>();

    public void addFragment(Fragment  fragment,String title){
        mFragment.add(fragment);
        mFragmentTitle.add(title);
    }
    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();

    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitle.get(position);
    }
}
