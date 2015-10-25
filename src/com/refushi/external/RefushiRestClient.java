package com.refushi.external;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class RefushiRestClient {

	public final static String BASE_URL 						= "http://refushi.smthweb.com/json/webservices/" ;
	public final static String URL_CATEGORY						= BASE_URL + "category/" ;
	public final static String URL_ITEM							= BASE_URL + "items/" ;
	public final static String URL_REGISTER						= BASE_URL + "register/" ;
	public final static String URL_LOGIN						= BASE_URL + "login/" ;
	public final static String URL_LOGOUT						= BASE_URL + "logout/" ;

	private AsyncHttpClient client ;

	public RefushiRestClient(String token){

		client = new AsyncHttpClient();
//		if (token != null ) client.addHeader("X-Auth-Token", token);
//		client.addHeader("Accept", "application/json");
//		client.addHeader("Content-type", "application/json");
		client.setBasicAuth("X-Auth-Token", token);
		client.setTimeout(10000);

		Log.e("TOKEN ", "Token : "+token);
	}



	public void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		client.get(url, params, responseHandler);
	}

	public  void post( String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		client.post(url, params, responseHandler);
	}

	public void put(Context context, String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		client.put(url, params, responseHandler);
	}

	public void delete( String url,  AsyncHttpResponseHandler responseHandler) {
		client.delete(url, responseHandler) ;
	}


	
	
}
