package com.refushi.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.refushi.R;
 
public class Fragment1NaviagationDrawer extends Fragment  {
     
    public Fragment1NaviagationDrawer(){}
     
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  //jhjhjh
        View rootView = inflater.inflate(R.layout.navigation_fragment1, container, false);
          
        return rootView;
    }
}