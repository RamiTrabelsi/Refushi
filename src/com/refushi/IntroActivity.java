package com.refushi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.refushi.fragments.FragmentViewPager1;
import com.refushi.fragments.FragmentViewPager2;
import com.refushi.fragments.FragmentViewPager3;

public class IntroActivity extends FragmentActivity  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_viewpager);
		
		
		ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
	}
	
	
	private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {

            case 0: return FragmentViewPager1.newInstance("FirstFragment, Instance 1");
            case 1: return FragmentViewPager2.newInstance("SecondFragment, Instance 1");
            case 2: return FragmentViewPager3.newInstance("SecondFragment, Instance 1");
            
            default: return FragmentViewPager1.newInstance("ThirdFragment, Default");
            }
        }

        @Override
        public int getCount() {
            return 3;
        }       
    }
}
