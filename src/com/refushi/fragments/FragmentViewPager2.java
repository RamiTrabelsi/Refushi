package com.refushi.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.refushi.R;



public class FragmentViewPager2 extends Fragment{


	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.frag2, container, false);


		
		
		
		// TODO Auto-generated method stub
		return rootView;
	}
	
	public static FragmentViewPager2 newInstance(String text) {

		FragmentViewPager2 f = new FragmentViewPager2();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
	
}
