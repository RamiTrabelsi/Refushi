package com.refushi.model;

import com.google.android.gms.maps.model.Marker;


public class MyMarker
{
	private int position ;
	private Item place ;

	

	public MyMarker(int position, Item place) {
		super();
		this.position = position;
		this.place = place;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Item getPlace() {
		return place;
	}

	public void setPlace(Item place) {
		this.place = place;
	}





}
