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

import java.util.StringTokenizer;

import android.util.Log;

/**
 * @author DOTIT Edition - H.L - admin
 *
 */
public class MyLocationHelper {
	
	final static double RADIAN 		= 0.01745329;
	final static int EARTH_RADIUS 	= 6371000;
	private static final String TAG = MyLocationHelper.class.getSimpleName();
	
	public static double[] formatLocation(String location){
		
		double[] loc = new double[2];
		String lng = "", lat = "";
		
		StringTokenizer tokens = new StringTokenizer(location, "/");
		int counter = 0;
		
		while (tokens.hasMoreTokens()){
			String next = tokens.nextToken();
//			Log.v(TAG, " next : " + next);
			
			switch (counter) {
			case 0:
				lng = next;
				break;
			case 1:
				lat = next;
				break;
				
			default:
				break;
			}
			
			counter++;
		}
		
		loc[0] = convertStringToDouble(lng);
		loc[1] = convertStringToDouble(lat);
		
		return loc;
	}
	
	public static Integer[] getMinSec(String str){
		
		Integer[] data = new Integer[2];
		
		StringTokenizer tokens = new StringTokenizer(str, ",");
		int counter = 0;
		
		while (tokens.hasMoreTokens()){
			String next = tokens.nextToken();
			data[counter++] = Integer.valueOf(next);
		}
		
		return data;
	}
	
	public static double convertStringToDouble(String str){
		
		double value = -1;
		
		try{
			double degree = 0, min = 0, sec = 0;
			String sign = "";
			
			StringTokenizer posTokens = new StringTokenizer(str, "e");
			if(posTokens == null || posTokens.countTokens() <= 1){
				posTokens = new StringTokenizer(str, "n");
			}
			
			StringTokenizer negTokens = new StringTokenizer(str, "w");
			if(negTokens == null || negTokens.countTokens() <= 1){
				negTokens = new StringTokenizer(str, "s");
			}
			
			StringTokenizer tokens = null;
			
			if(posTokens != null && posTokens.countTokens() > 1)
			{
				sign = "n";
				tokens = posTokens;
			}
			else 
				if(negTokens != null && negTokens.countTokens() > 1){
				sign = "s";
				tokens = negTokens;
			}
			
			if(tokens != null){
				degree = Double.valueOf(tokens.nextToken());
				Integer[] data = getMinSec(tokens.nextToken());
				min = data[0];
				if(data[1] != null)
					sec = data[1];
				
				value = degree + (min/60) + (sec/3600);
				
				if(sign.equalsIgnoreCase("w") || sign.equalsIgnoreCase("s"))
					value = 0-value;
				
//				Log.v(TAG, "value " + value);
			}
			
		}catch(Exception e){
			Log.e(TAG, " convertStringToDouble error ! " + e.getMessage());
		}
				
		return value;
		
	}
	
	// Input a double latitude or longitude in the decimal format
	// e.g. -79.982195
	public static String decimalToDMS(double coord, String sign) {
		String output, degrees, minutes, seconds;
	 
		// gets the modulus the coordinate divided by one (MOD1).
		// in other words gets all the numbers after the decimal point.
		// e.g. mod := -79.982195 % 1 == 0.982195 
		//
		// next get the integer part of the coord. On other words the whole number part.
		// e.g. intPart := -79
	 
		double mod = coord % 1;
		int intPart = (int)coord;
	 
		//set degrees to the value of intPart
		//e.g. degrees := "-79"
	 
		degrees = String.valueOf(intPart);
	 
		// next times the MOD1 of degrees by 60 so we can find the integer part for minutes.
		// get the MOD1 of the new coord to find the numbers after the decimal point.
		// e.g. coord :=  0.982195 * 60 == 58.9317
		//	mod   := 58.9317 % 1    == 0.9317
		//
		// next get the value of the integer part of the coord.
		// e.g. intPart := 58
	 
		coord = mod * 60;
		mod = coord % 1;
		intPart = (int)coord;
	        if (intPart < 0) {
	           // Convert number to positive if it's negative.
	           intPart *= -1;
	        }
	 
		// set minutes to the value of intPart.
		// e.g. minutes = "58"
		minutes = String.valueOf(intPart);
	 
		//do the same again for minutes
		//e.g. coord := 0.9317 * 60 == 55.902
		//e.g. intPart := 55
		coord = mod * 60;
		intPart = (int)coord;
	        if (intPart < 0) {
	           // Convert number to positive if it's negative.
	           intPart *= -1;
	        }
	 
		// set seconds to the value of intPart.
		// e.g. seconds = "55"
		seconds = String.valueOf(intPart);
	 
		// I used this format for android but you can change it 
		// to return in whatever format you like
		// e.g. output = "-79/1,58/1,56/1"
		output = degrees + sign + minutes;
	 
		//Standard output of D�M'S"
		//output = degrees + "�" + minutes + "'" + seconds + "\"";
	 
		return output;
	}
	
	public static String longitudeToDMS(double coord){
		String sign = coord >=0 ? "e":"w";
		return decimalToDMS(coord, sign);
	}
	
	public static String latitudeToDMS(double coord){
		String sign = coord >=0 ? "n":"s";
		return decimalToDMS(coord, sign);
	}
		
	public static double calculateRadian(double value){
		return value * RADIAN;
	}
	
	public static double distanceTo(double fromLat, double fromLng, double toLat, double toLng){

		double fromLatRad = calculateRadian(fromLat);
		double fromLngRad = calculateRadian(fromLng);
		double toLatRad = calculateRadian(toLat);
		double toLngRad = calculateRadian(toLng);
				
		return EARTH_RADIUS*(Math.PI/2-Math.asin(Math.sin(fromLatRad)*Math.sin(toLatRad)+Math.cos(fromLngRad-toLngRad)*Math.cos(toLatRad)*Math.cos(fromLatRad)));
	}
}
