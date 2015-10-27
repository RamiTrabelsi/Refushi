package com.refushi.fragments;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.refushi.R;
import com.refushi.external.RefushiManager;
 
public class FragmentCategories extends Fragment  {
	private RefushiManager mManager ;
	
	
    public FragmentCategories(){}
     
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.fragment_categories, container, false);
        
         mManager = RefushiManager.getInstance(getActivity());
       
        
        return rootView;
    }
}