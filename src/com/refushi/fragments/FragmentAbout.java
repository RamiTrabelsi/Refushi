package com.refushi.fragments;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.refushi.R;
 
public class FragmentAbout extends Fragment  {
     
    public FragmentAbout(){}
     
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.fragment_about, container, false);
          
        return rootView;
    }
}