package com.refushi.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.refushi.R;
import com.refushi.external.RefushiFonts;
import com.refushi.model.MenuGridItem;


public class MenuGridAdapter extends ArrayAdapter<MenuGridItem>  
{

	Context mContext;
	ArrayList<MenuGridItem> data = new ArrayList<MenuGridItem>();
	LayoutInflater inflater;


	public MenuGridAdapter(Context mContext, ArrayList<MenuGridItem> data) {

		super(mContext, 0, data);
		this.mContext = mContext;
		this.data = data;

		inflater = ((Activity) mContext).getLayoutInflater();

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		final ViewHolder holder;
		if(convertView==null)
		{
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.row_grid, parent, false);

			// get the elements in the layout
			holder.img_category 	= (ImageView) convertView.findViewById(R.id.img_category); 
			holder.txt_category 	= (TextView) convertView.findViewById(R.id.txt_category); 
			holder.list_item		= (RelativeLayout) convertView.findViewById(R.id.list_item); 
			//			holder.loader 			= (ImageView) convertView.findViewById(R.id.loader); 

			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder)convertView.getTag();
		}
		
		holder.txt_category.setTypeface(RefushiFonts.getPetitaLight());

		final MenuGridItem mItem = data.get(position);

		holder.img_category.setImageResource(mItem.getMenuDrawable());
		holder.txt_category.setText(mItem.getMenuText());

		holder.list_item.setTag(position);

		return convertView;
	}

	class ViewHolder
	{
		ImageView img_category;
		TextView txt_category;
		RelativeLayout list_item ;
	}


}
