package com.refushi.maps;


import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.maps.MapActivity;

/**
 * Created by tangmm on 8/16/13.
 */
public class MyLocationManager implements LocationListener {

    private MapActivity mapActivity;
    private LocationManager gpsLocationManager;
    private LocationManager networkLocationManager;

    private LatLng currentPoint;

    public MyLocationManager(MapActivity activity) {
        this.mapActivity = activity;
    }



    public void getCurrentLocation() {
        final int MINTIME = 2000;
        final int MININSTANCE = 10;

        // GPS location services
        gpsLocationManager = (LocationManager) mapActivity.getSystemService(Context.LOCATION_SERVICE);
        Location gpsLocation = gpsLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        gpsLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                MINTIME, MININSTANCE, this);

        //TODO network location services
        networkLocationManager = (LocationManager) mapActivity.getSystemService(Context.LOCATION_SERVICE);

        // Creating a criteria object to retrieve provider
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);//best accuracy
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(false);
        criteria.setPowerRequirement(Criteria.POWER_LOW);// power demand

        // Getting the name of the best provider
        String provider = gpsLocationManager.getBestProvider(criteria, true);

        // Getting Current Location
        Location location = gpsLocationManager.getLastKnownLocation(provider);


        if (location != null) {
            onLocationChanged(location);
        }
        gpsLocationManager.requestLocationUpdates(provider, MINTIME, MININSTANCE, this);

    }

    /**
     * detect user's current location
     *
     * @param location
     */
    @Override
    public void onLocationChanged(Location location) {
        // Getting Latitude et longitude of the current location
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        // Creating a LatLng object for the current location
        this.currentPoint = new LatLng(latitude, longitude);

//        // Showing the current location in Google Map
//        mapActivity.getMap().moveCamera(CameraUpdateFactory.newLatLng(currentPoint));
//
//        // Zoom in the Google Map
//        mapActivity.getMap().animateCamera(CameraUpdateFactory.zoomTo(15));

        // Setting latitude and longitude in the TextView tv_location
        Toast.makeText(mapActivity.getApplicationContext(), "Latitude:" + latitude + ", Longitude:" + longitude,
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    /**
     * get current coordinates
     * @return
     */
    public LatLng getCurrentPoint() {
       if (currentPoint == null) {
            getCurrentLocation();
       }

       return this.currentPoint;
    }
}
