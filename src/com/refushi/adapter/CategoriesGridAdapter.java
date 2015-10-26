package com.refushi.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.creativeapps.utils.ResizableImageView;
import com.refushi.R;
import com.refushi.external.RefushiManager;
import com.refushi.model.Category;



public class CategoriesGridAdapter extends ArrayAdapter<Category> implements OnTouchListener{

	Context mContext;
	ArrayList<Category> data ;
	LayoutInflater inflater;
    Category mCategory ;
	RefushiManager mManager ;

	public CategoriesGridAdapter(Context mContext, ArrayList<Category> data) {
		super(mContext, 0, data);

		this.mContext = mContext;
		this.data = data;

		inflater = ((Activity) mContext).getLayoutInflater();
        mManager = RefushiManager.getInstance(mContext) ;
        
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		final ViewHolder holder;
		if(convertView==null)
		{
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.row_categories_grid, parent, false);

			holder.categoryImg 		= (ResizableImageView) convertView.findViewById(R.id.category_img);

			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder)convertView.getTag();
		}

		mCategory = data.get(position);


	//	holder.categoryImg.setImageResource(mCategory.getCategoryIcon());
        holder.categoryImg.setTag(position);
		holder.categoryImg.setOnTouchListener(this);

		return convertView;
	}

	class ViewHolder
	
	{
     ResizableImageView categoryImg ;

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			ImageView view = (ImageView) v;
			//overlay is black with transparency of 0x77 (119)
			view.getDrawable().setColorFilter(0x55ffffff,PorterDuff.Mode.SRC_ATOP);
			view.invalidate();
			break;
		}
		case MotionEvent.ACTION_UP: {

			Category cat = data.get((Integer) v.getTag());
			
			Log.e("SHORT DESCRIPTION",cat.getName());
			
		//	mManager.setCategoryToView(cat);
		//	mContext.startActivity(new Intent((Activity)mContext , CategoryItemsActivity.class));

		}
		
		case MotionEvent.ACTION_CANCEL: {
			ImageView view = (ImageView) v;
			//clear the overlay
			view.getDrawable().clearColorFilter();
			view.invalidate();
			break;
		}
		}

		return true;
	}



}
