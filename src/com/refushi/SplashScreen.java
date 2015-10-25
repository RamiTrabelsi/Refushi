package com.refushi;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.refushi.external.RefushiFonts;
import com.refushi.external.RefushiManager;
import com.refushi.external.RefushiRestClient;
import com.refushi.model.Category;


@SuppressLint("HandlerLeak")
public class SplashScreen extends Activity {

	private static final int STOPSPLASH = 0;
	private static final long SPLASHTIME = 3500;
	private RefushiManager mManager;


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		RefushiFonts.Init(this);
		mManager = RefushiManager.getInstance(this);

		Message msg = new Message();
		msg.what = STOPSPLASH;

		splashHandler.sendMessageDelayed(msg, SPLASHTIME);

	}


	private Handler splashHandler = new Handler() {

		public void handleMessage(Message msg) {



			loadCategories();

			super.handleMessage(msg);
		}
	};


	private void loadCategories() {

		RefushiRestClient mClient = new RefushiRestClient(mManager.getAccessToken());
		mClient.get(RefushiRestClient.URL_CATEGORY, null, new JsonHttpResponseHandler(){

			@Override
			public void onStart() {

			}
			@Override
			public void onFailure(Throwable arg0, String arg1) {
				super.onFailure(arg0, arg1);

				Log.e("JSON Failure1", "failure with msg : "+arg1);
				mManager.showWarning(SplashScreen.this, "Un probl�me est survenu. Merci de v�rifier votre connexion Internet. ");
			}

			@Override
			public void onSuccess(int arg0, JSONObject response) {
				Log.e("URL_Categories","URL_Categories"+ RefushiRestClient.URL_CATEGORY);


				Log.e("JSON RESPONSE loadCat", "success  with msg : "+response+" + int :"+arg0);
				JSONArray responseArray;

				try {
					responseArray = response.getJSONArray("result");

					for (int i = 0; i < responseArray.length(); i++) {

						Category mCategory = new Category();

						JSONObject json = responseArray.getJSONObject(i).getJSONObject("Category");
						mCategory.setId(json.getString("id"));
						mCategory.setName(json.getString("name"));

						Log.e("22222222","2222222222" +mCategory.toString());

					}

					Intent intent = new Intent(SplashScreen.this, IntroActivity.class);
					startActivity(intent);
					overridePendingTransition(R.anim.fadein, R.anim.fadeout);
					finish();

				}
				catch (JSONException e) {
					e.printStackTrace();}

			} 

			public void onFinish() {
				Log.e("JSON RESPONSE", "finish");
			};
		});
	}




	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			splashHandler.removeMessages(STOPSPLASH);
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onResume() {
		super.onResume();
		this.registerReceiver(mManager.myConnectivityStateReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
	}

	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver(mManager.myConnectivityStateReceiver);
	}




}