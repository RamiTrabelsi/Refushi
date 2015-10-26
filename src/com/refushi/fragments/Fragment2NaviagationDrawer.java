package com.refushi.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.refushi.R;
 
public class Fragment2NaviagationDrawer extends Fragment  {
     
    public Fragment2NaviagationDrawer(){}
     
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.navigation_fragment2, container, false);
          
        return rootView;
    }
}