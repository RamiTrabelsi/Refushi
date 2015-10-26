package com.refushi.fragments;


import java.util.ArrayList;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;


import com.refushi.R;
import com.refushi.adapter.CategoriesGridAdapter;
import com.refushi.external.RefushiManager;
import com.refushi.model.Category;
 
public class Fragment3NaviagationDrawer extends Fragment  {
	private GridView catGridView ;
	private RefushiManager mManager ;
	private ArrayList<Category> allCategories = new ArrayList<Category>();
	private CategoriesGridAdapter adapter ;
	
	
    public Fragment3NaviagationDrawer(){}
     
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.navigation_fragment3, container, false);
          
        
        
        
         mManager = RefushiManager.getInstance(getActivity());
		
		allCategories = mManager.getAllCategories();
		
		for (int i = 0; i < allCategories.size(); i++) {
			
			Log.e("33333333333333333333333","Category "+i+" : "+allCategories.get(i).getName());
		}
		
		catGridView = (GridView) rootView.findViewById(R.id.gridView);
		adapter = new CategoriesGridAdapter(getActivity(), allCategories );
		catGridView.setAdapter(adapter);
		catGridView.setCacheColorHint(Color.TRANSPARENT);
		
        
       
        
        return rootView;
    }
}