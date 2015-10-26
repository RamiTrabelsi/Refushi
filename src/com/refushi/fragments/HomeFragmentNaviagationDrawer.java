package com.refushi.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.refushi.R;
 
public class HomeFragmentNaviagationDrawer extends Fragment  {
     
    public HomeFragmentNaviagationDrawer(){}
     
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
          
        return rootView;
    }
}