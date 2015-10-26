package com.refushi.external;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.TextView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener;

import com.refushi.R;


public class RefushiManager {


	private Context mContext;
	private static RefushiManager mInstance = null;

	public AlertDialog alert ;

	private String accessToken ;


	public RefushiManager(Context context) {
		mContext = context;
	}

	public synchronized static RefushiManager getInstance(Context context) {
		if (mInstance == null)
			mInstance = new RefushiManager(context);

		return mInstance;
	}




	public void showPopUp(Context context, String message, String ok) {

		if (context != null) {
			// Alert dialogue
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
			// set dialog message

			TextView content = new TextView(context);
			content.setText(message);
			content.setTypeface(RefushiFonts.getPetitaLight());
			content.setGravity(Gravity.CENTER);
			content.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
			content.setPadding(15, 15, 15, 15);


			alertDialogBuilder
			.setView(content)
			.setCancelable(false)
			;

			alert = alertDialogBuilder.create();
			DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					//				System.exit(0);
					exit();
				}
			};
			alert.setButton(DialogInterface.BUTTON_POSITIVE, ok, listener);
			alert.setOnShowListener(new DialogInterface.OnShowListener() {
				@Override
				public void onShow(DialogInterface dialog) {
					AlertDialog alertDialog = (AlertDialog) dialog;
					Button btn = alertDialog.getButton(Dialog.BUTTON_POSITIVE);
					btn.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
					btn.setTypeface(RefushiFonts.getPetitaLight());
					btn.setGravity(Gravity.CENTER);

				}
			});

			// show it
			alert.show();
		}
	}

	public BroadcastReceiver myConnectivityStateReceiver = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {

			NetworkInfo networkStateInfo = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
			if(null != networkStateInfo){
				setNetworkStateMessage(context, networkStateInfo.getState());
			}
		}

		private void setNetworkStateMessage(Context context , NetworkInfo.State state){


			switch(state){
			case CONNECTED:   

				Log.w("isConnected", "isConnected");

				if(alert != null && alert.isShowing())
					alert.dismiss();

				break;


			case DISCONNECTED:  

				Log.w("isDisconnected", "isDisconnected");

				showPopUp(context, "Merci de vérifier votre connexion Internet.", "Ok");  

				break;

			case SUSPENDED:         
				break;

			default:                
				break;
			}
		}
	};


	public void exit(){
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		mContext.startActivity(intent);

		System.exit(0);
	}

	public void showWarning(Context ctx, String text){
		new SweetAlertDialog(ctx, SweetAlertDialog.WARNING_TYPE)
		.setTitleText("Oops...")
		.setContentText(text)
		.show();
	}


	public void showWarningExit(final Context ctx, String text){
		SweetAlertDialog loading = new SweetAlertDialog(ctx, SweetAlertDialog.WARNING_TYPE);
		loading.setTitleText("Oops...")
		.setContentText(text)
		.setConfirmClickListener(new OnSweetClickListener() {
			@Override
			public void onClick(SweetAlertDialog sweetAlertDialog) {

				exit();
			}
		});


		loading.setOnShowListener(new DialogInterface.OnShowListener() {
			@Override
			public void onShow(DialogInterface dialog) {
				SweetAlertDialog alertDialog = (SweetAlertDialog) dialog;
				TextView text = (TextView) alertDialog.findViewById(R.id.content_text);
				text.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
				text.setTypeface(RefushiFonts.getPetitaLight());
				text.setGravity(Gravity.CENTER);

			}
		});

		loading.show();
	}


	public File saveBitmapToFile(File file){
		try {

			// BitmapFactory options to downsize the image
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			o.inSampleSize = 6;
			// factor of downsizing the image

			FileInputStream inputStream = new FileInputStream(file);
			//Bitmap selectedBitmap = null;
			BitmapFactory.decodeStream(inputStream, null, o);
			inputStream.close();

			// The new size we want to scale to
			final int REQUIRED_SIZE=75;

			// Find the correct scale value. It should be the power of 2.
			int scale = 1;
			while(o.outWidth / scale / 2 >= REQUIRED_SIZE &&
					o.outHeight / scale / 2 >= REQUIRED_SIZE) {
				scale *= 2;
			}

			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			inputStream = new FileInputStream(file);

			Bitmap selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2);
			inputStream.close();

			// here i override the original image file
			file.createNewFile();
			FileOutputStream outputStream = new FileOutputStream(file);

			selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 100 , outputStream);

			return file;
		} catch (Exception e) {
			return null;
		}
	}

	public void switchView(final View firstLayout, final View secondeLayout) {
		final Animation in = new AlphaAnimation(0.0f, 1.0f);
		in.setDuration(200);

		final Animation out = new AlphaAnimation(1.0f, 0.0f);
		out.setDuration(200);

		AnimationSet as = new AnimationSet(true);
		as.addAnimation(out);
		in.setStartOffset(200);
		as.addAnimation(in);

		out.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationEnd(Animation animation) {
				firstLayout.setVisibility(View.GONE);
				secondeLayout.setVisibility(View.VISIBLE);
				secondeLayout.startAnimation(in);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationStart(Animation animation) {
			}
		});

		firstLayout.startAnimation(out);

	}

	public static long daysBetween(Date startDate, Date endDate) {
		Calendar sDate = getDatePart(startDate);
		Calendar eDate = getDatePart(endDate);

		long daysBetween = 0;
		while (sDate.before(eDate)) {
			sDate.add(Calendar.DAY_OF_MONTH, 1);
			daysBetween++;
		}
		return daysBetween;
	}

	public static Calendar getDatePart(Date date){
		Calendar cal = Calendar.getInstance();       // get calendar instance
		cal.setTime(date);      
		cal.set(Calendar.HOUR_OF_DAY, 0);            // set hour to midnight
		cal.set(Calendar.MINUTE, 0);                 // set minute in hour
		cal.set(Calendar.SECOND, 0);                 // set second in minute
		cal.set(Calendar.MILLISECOND, 0);            // set millisecond in second

		return cal;                                  // return the date part
	}


	public String calculateJodaperiod(Date date) {
		// TODO Auto-generated method stub

		DateTime myBirthDate = new DateTime(date);
		DateTime now = new DateTime();
		Period period = new Period(myBirthDate, now);

		PeriodFormatter formatter = new PeriodFormatterBuilder()
		//		    .appendSeconds().appendSuffix(" seconds ago\n")
		.appendYears().appendSuffix(" années et ")
		.appendMonths().appendSuffix(" mois et ")
		.appendWeeks().appendSuffix(" semaines et ")
		.appendDays().appendSuffix(" jours et ")
		.appendHours().appendSuffix(" heures et ")
		.appendMinutes().appendSuffix(" minutes")
		.printZeroNever()
		.toFormatter();

		return formatter.print(period).replace("-", "") ;

	}


	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public  boolean isValidEmail(CharSequence target) {
		if (target == null) {
			return false;
		} else {
			return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
		}
	}




}
