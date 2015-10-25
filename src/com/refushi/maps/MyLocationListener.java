package com.refushi.maps;

/* ************************************************************************
* Copyright (C) Continental Automotive GmbH 2012
* All rights reserved
*
* The reproduction, transmission or use of this document or its contents is
* not permitted without express written authority.
* Offenders will be liable for damages. All rights, including rights created
* by patent grant or registration of a utility model or design, are reserved.
* ************************************************************************/

import java.util.ArrayList;

import org.json.JSONObject;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;


/**
 * @author DOTIT Edition - H.L - admin
 *
 */
public class MyLocationListener implements LocationListener {


	private final String TAG = "FSDS : " + getClass().getSimpleName();

	private LocationManager mlocManager;
	
	private String gpsProvider;
	Criteria crta = new Criteria();
	private static  MyLocationListener mySelf;
	
	private static Context myContext;
	ArrayList <LocationListener> listener = new ArrayList <LocationListener>();
	Location lastLocationGPS;
	
	public Location getLastLocationGPS() {
		return lastLocationGPS;
	}
	
//	public Coord getLastCoord(){
//		Coord coord = null;
//		
//		if(lastLocationGPS != null)
//			coord = new Coord(lastLocationGPS.getLatitude(), lastLocationGPS.getLongitude());
//		
//		return coord;
//	}
	
//	public GeoPoint getGEOPoint () {
//		
//		if(lastLocationGPS != null)
//			return new GeoPoint((int) (lastLocationGPS.getLatitude() * 1e6), (int) (lastLocationGPS.getLongitude() * 1e6));
//		
//		return null;
//	}

	boolean providerAvailable = false;
	
	private MyLocationListener () {
		
		mlocManager =	(LocationManager) myContext.getSystemService(Context.LOCATION_SERVICE);
		
		crta.setAccuracy(Criteria.ACCURACY_FINE);
		crta.setAltitudeRequired(false);
		crta.setBearingRequired(false);
		crta.setCostAllowed(true);
		crta.setPowerRequirement(Criteria.POWER_LOW); 
		setBestProvider ();		
	}

	
	public static MyLocationListener getInstance (Context ctx) {

		myContext = ctx;
		if (mySelf == null)
			mySelf = new MyLocationListener ();
		
		return mySelf;
	}

	public void register (LocationListener l) {
		listener.add (l);
		l.onLocationChanged(lastLocationGPS);
	}
	
	public void remove (LocationListener l) {
		listener.remove (l);
	}
	
	
	
	private void setBestProvider () {
			// remove current if any
		mlocManager.removeUpdates(this);
		
		gpsProvider = mlocManager.getBestProvider(crta, true);
		 
		 	
		if (gpsProvider == null)
			gpsProvider = LocationManager.GPS_PROVIDER;
		 
		 
		if (gpsProvider != null) {
			
			mlocManager.requestLocationUpdates(gpsProvider, 86400000, 1000, MyLocationListener.this);
					   
			// GPS LastKnownLocation
			lastLocationGPS = mlocManager.getLastKnownLocation(gpsProvider);
			if (lastLocationGPS != null)
				onLocationChanged (lastLocationGPS);
		 }
//		 else{
//			 new LoadingData().execute();
//		 }
		 
		 providerAvailable = gpsProvider!=null;

	}

	
	public boolean isProviderAvailable() {
		return providerAvailable;
	}


	@Override
	public void onLocationChanged(Location loc) {
		// TODO Auto-generated method stub
		
		lastLocationGPS = loc;
		notifyNewLocation(loc);
	}
	
	private void notifyNewLocation (Location loc){
		for (LocationListener i : listener) {
			i.onLocationChanged(loc);
		}
	}

	@Override
	public void onProviderDisabled(String provider) {

			// we should have an alternative
		String prov = mlocManager.getBestProvider(crta, true);
		if (prov == null)
			return;

		setBestProvider ();
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		
///		mlocManager.requestLocationUpdates(gpsProvider, 0, 100,	this);
	}
	
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {

		if (status == LocationProvider.OUT_OF_SERVICE) 
			setBestProvider ();

	}
	
	private class LoadingData extends AsyncTask<Void, Void, Boolean>
	{
		String response=null;
		double lat, lng;

		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO Auto-generated method stub


			try {
//				response=ConnectionToServer.executeHttpGet("http://api.wipmania.com/jsonp?callback=");
				response=response.replace("(", "").replace(")", "");
				Log.e("song response", response);

				if(response!=null)
				{
					JSONObject jsonObj=new JSONObject(response);
					
					lat = jsonObj.getDouble("latitude");
					lng = jsonObj.getDouble("longitude");
					
					return true;

				}


			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return false;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			if(result){
				Log.i(TAG, " ... lat " + lat);
				Log.i(TAG, " ... lng " + lng);
				lastLocationGPS.setLatitude(lat);
				lastLocationGPS.setLongitude(lng);
				notifyNewLocation(lastLocationGPS);
			}
		}



	}
	
	public boolean isGPSEnabled(Context context){

		boolean isGPSOn = false;

		if (mlocManager == null)
			mlocManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

		try {
			isGPSOn = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		} catch (Exception ex) {}

		return isGPSOn;

	}

	public boolean isNetworkEnabled(Context context){

		boolean isNetworkOn = false;

		if (mlocManager == null)
			mlocManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

		try {
			isNetworkOn = mlocManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		} catch (Exception ex) {}

		return isNetworkOn;

	}
	
}
