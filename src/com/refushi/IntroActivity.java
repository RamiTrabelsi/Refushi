package com.refushi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.refushi.fragments.FragmentIntro1;
import com.refushi.fragments.FragmentIntro2;
import com.refushi.fragments.FragmentIntro3;
import com.viewpagerindicator.CirclePageIndicator;


public class IntroActivity extends FragmentActivity  {
	CirclePageIndicator circlePageIndicator;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_viewpager);
		
		
		ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
		
		circlePageIndicator =(CirclePageIndicator) findViewById(R.id.viewPagerIndicator);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        
        circlePageIndicator.setViewPager(pager);
	}
	
	
	private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {
            case 0: return FragmentIntro1.newInstance("FirstFragment, Instance 1");
            case 1: return FragmentIntro2.newInstance("SecondFragment, Instance 1");
            case 2: return FragmentIntro3.newInstance("SecondFragment, Instance 1");
           default: return FragmentIntro1.newInstance("ThirdFragment, Default");
            }
        }

        @Override
        public int getCount() {
            return 3;
        }       
    }
}
