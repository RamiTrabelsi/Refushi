package com.refushi.maps;


import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.apache.http.message.LineParser;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * demand route from origin to destination
 *
 * Created by tangmm on 8/16/13.
 */
public class DirectionsManager {

    GoogleMap map;
    ArrayList<LatLng> markerPoints = null;
    PolylineOptions lineOptions = null;
    MarkerOptions options = null;

    public DirectionsManager() { }

    public DirectionsManager(GoogleMap map) {
        this.map = map;
        markerPoints = new ArrayList<LatLng>();
    }


    /**
     * create request URL from origin to destination using HTTPS
     * driving route by default
     * @param origin
     * @param dest
     * @return
     */
    public String getDirectionsUrl(LatLng origin,LatLng dest){

        String strOrigin = "origin="+origin.latitude+","+origin.longitude;
        String strDest = "destination="+dest.latitude+","+dest.longitude;

        String sensor = "sensor=false";   // Sensor enabled

        // parameters to the web service
        String parameters = strOrigin+"&"+strDest+"&"+sensor;
        String output = "json";   // Output format

        // url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;

        return url;
    }

    /**
     * get directions in a certain travel mode among "driving","walking","bicycling"
     * ("transit" by public transport is not supported actually)
     * @param origin
     * @param dest
     * @param mode
     * @return
     */
    public String getDirectionsUrlWithMode (LatLng origin, LatLng dest, String mode) {

        String url = getDirectionsUrl(origin, dest);

        if (mode == null) {
            return url;     // driving route by default
        }else {
           return url + "&" + mode;
        }
    }


    /**
     * download json data from URL
     */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;

        try{
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
            StringBuffer sb  = new StringBuffer();

            String line = "";
            while( ( line = br.readLine())  != null){
                sb.append(line);
            }

            data = sb.toString();
            br.close();

        }catch(Exception e){
            Log.d("Exception while downloading url", e.toString());
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    /**
     * A class to download data from Google Directions URL
     */
    public class DownloadTask extends AsyncTask<String, Void, String> {

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try{
                // Fetching the data from web service
                data = downloadUrl(url[0]);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }

    /**
     * A class to parse the Google Directions in JSON format
     */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try{
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser(); // instantiation of JSON parser

                // Starts parsing data
                routes = parser.parse(jObject);
            
            }catch(Exception e){
            	
            	Log.e("routes ", "routes problem");
                e.printStackTrace();
                
            }

            return routes;
        }

        /**
         * Executes in UI thread, after the parsing process
         * (ref from web)
         * @param result
         */
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
         //   PolylineOptions lineOptions = null;
            
            
            Log.e("LINEresult ", "LINEresult "+result.size()+"");

            // Traversing through all the routes
            for(int i = 0; i < result.size(); i++){
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for(int j=0;j<path.size();j++){
                    HashMap<String,String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                
               
                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(2);
                lineOptions.color(Color.RED);
            }

            // Drawing polyline in the Google Map for the i-th route
            Log.w("LINEOPTIONS", lineOptions+"");
            
            if (lineOptions != null )
            map.addPolyline(lineOptions);
        }
    }


    public DownloadTask instantiateDownloadTask() {
       return new DownloadTask();
    }

    public PolylineOptions getLineOptions() {
        return this.lineOptions;
    }

}
