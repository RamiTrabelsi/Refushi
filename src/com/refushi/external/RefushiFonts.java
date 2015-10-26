package com.refushi.external;

import android.content.Context;
import android.graphics.Typeface;



public class RefushiFonts {
	
	private static Typeface  petitaLight, petitaBold, petitaMedium;
	
	public static void Init(Context context){
		setPetitaLight(Typeface.createFromAsset(context.getAssets(), "fonts/PetitaLight.ttf"));
		setPetitaMedium(Typeface.createFromAsset(context.getAssets(), "fonts/PetitaMedium.ttf"));
		setPetitaBold(Typeface.createFromAsset(context.getAssets(), "fonts/PetitaBold.ttf"));
	}

	public static Typeface getPetitaMedium() {
		return petitaMedium;
	}

	public static void setPetitaMedium(Typeface petitaMedium) {
		RefushiFonts.petitaMedium = petitaMedium;
	}

	public static Typeface getPetitaLight() {
		return petitaLight;
	}

	public static void setPetitaLight(Typeface petitaLight) {
		RefushiFonts.petitaLight = petitaLight;
	}

	public static Typeface getPetitaBold() {
		return petitaBold;
	}

	public static void setPetitaBold(Typeface petitaBold) {
		RefushiFonts.petitaBold = petitaBold;
	}

	
}
