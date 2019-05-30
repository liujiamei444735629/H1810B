package com.example.liu.day03;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.liu.day03.fragment.GuanzhuFragment;
import com.example.liu.day03.fragment.XiaomenFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTab;
    private ViewPager mVp;
    private ArrayList<Fragment> arr;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setSupportActionBar(mToolbar);
        arr = new ArrayList<>();
        arr.add(new XiaomenFragment());
        arr.add(new GuanzhuFragment());
        final String[] a = {"校门", "关注"};
        mVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return arr.get(i);
            }

            @Override
            public int getCount() {
                return arr.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return a[position];
            }
        });
        mTab.setupWithViewPager(mVp);
    }

    private void initView() {
        mTab = (TabLayout) findViewById(R.id.tab);
        mVp = (ViewPager) findViewById(R.id.vp);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
    }
}
