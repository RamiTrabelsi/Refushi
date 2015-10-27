package com.refushi;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.facebook.widget.ProfilePictureView;
import com.pixplicity.easyprefs.library.Prefs;
import com.refushi.external.RefushiManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {
RefushiManager mManager;
	

//ProfilePictureView profile_picture;
//private UiLifecycleHelper uiHelper;
//private LoginButton loginBtn;


private UiLifecycleHelper uiHelper;
private View otherView;
private static final String TAG = "LoginActivity";

	private EditText edit_Email,edit_password;
	private Button  btn_login;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		
		mManager = RefushiManager.getInstance(this);
		
		edit_Email               = (EditText) findViewById(R.id.edit_Email);
		edit_password            = (EditText) findViewById(R.id.edit_Password);
		btn_login=(Button)findViewById(R.id.btn_Login);
		
		uiHelper = new UiLifecycleHelper(this, callback);
		uiHelper.onCreate(savedInstanceState);
		
		
//		uiHelper = new UiLifecycleHelper(this, statusCallback);
//		uiHelper.onCreate(savedInstanceState);
//		
//		
		
		
//		
//		
//		
		btn_login.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View v) {
			// TODO Auto-generated method stub
				
				
				postData();
			}
	});
		}
//	userName = (TextView) findViewById(R.id.user_name);
//	loginBtn = (LoginButton) findViewById(R.id.fb_login_button);
//	
//	loginBtn.setUserInfoChangedCallback(new UserInfoChangedCallback() {
//		@Override
//		public void onUserInfoFetched(GraphUser user) {
//			if (user != null) {
//				
//				userName.setText("Hello, " + user.getName());
//				
//			} else {
//				userName.setText("You are not logged");
//			}
//		}
//	});

//
//Session.openActiveSession(Login_Activity.this, true, new Session.StatusCallback() {
//
//	// callback when session changes state
//	@Override
//	public void call(Session session, SessionState state,
//			Exception exception) {
//		// TODO Auto-generated method stub
//		
//		if (session.isOpened()) {
//
//			// make request to the /me API
//			Request.newMeRequest(session, new Request.GraphUserCallback() {
//
//				// callback after Graph API response with user object
//				@Override
//				public void onCompleted(GraphUser user, Response response) {
//					if (user != null) {
//
//						Log.e("Facebook USER", "");
//						profile_picture.setProfileId(user.getId());
//						profile_picture.setCropped(true);
////						username.setText(user.getName());
//					
//					}
//				}
//			}).executeAsync();}}
//});}

//
//private Session.StatusCallback statusCallback = new Session.StatusCallback() {
//@Override
//public void call(Session session, SessionState state,
//		Exception exception) {
//	if (state.isOpened()) {
//	//	buttonsEnabled(true);
//		Log.d("FacebookSampleActivity", "Facebook session opened");
//		
//	
//		
//		
//	} else if (state.isClosed()) {
//	//	buttonsEnabled(false);
//		Log.d("FacebookSampleActivity", "Facebook session closed");
//	}
//}
//};

//public boolean checkPermissions() {
//	Session s = Session.getActiveSession();
//	if (s != null) {
//		return s.getPermissions().contains("publish_actions");
//	} else
//		return false;
//}
//
//public void requestPermissions() {
//	Session s = Session.getActiveSession();
//	if (s != null)
//		s.requestNewPublishPermissions(new Session.NewPermissionsRequest(
//				this, PERMISSIONS));
//}

	private Session.StatusCallback callback = new Session.StatusCallback() {
		@Override
		public void call(Session session, SessionState state,
				Exception exception) {
			onSessionStateChange(session, state, exception);
		}
	};
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	
public void postData() {

String email = edit_Email.getText().toString();
String password = edit_password.getText().toString();

Log.e("11111111111111","1111111111111"+email);
Log.e("222222222222","222222222222222"+password);

if (email.equals("") || password.equals("")  ) {
	mManager.showWarning(LoginActivity.this, "Merci de renseigner votre Email et Mot de passe");


	
}
else if (!mManager.isValidEmail(email)) {
	mManager.showWarning(LoginActivity.this, "Veuillez introduire une adresse mail valide");


} else
	{
	mManager.login(LoginActivity.this, email, password);
	
	
	

//	Prefs.putString("email", email);
//	Prefs.putString("password", password);
	}

}


// When session is changed, this method is called from callback method
private void onSessionStateChange(Session session, SessionState state,
		Exception exception) {
	//final TextView name = (TextView) findViewById(R.id.name);
	//final TextView gender = (TextView) findViewById(R.id.gender);
	//final TextView location = (TextView) findViewById(R.id.location);
	// When Session is successfully opened (User logged-in)
	if (state.isOpened()) {
		Log.i(TAG, "Logged in...");
		// make request to the /me API to get Graph user
		Request.newMeRequest(session, new Request.GraphUserCallback() {

			// callback after Graph API response with user
			// object
			@Override
			public void onCompleted(GraphUser user, Response response) {
				if (user != null) {
					// Set view visibility to true
					otherView.setVisibility(View.VISIBLE);
					// Set User name 
//					name.setText("Hello " + user.getName());
//					// Set Gender
//					gender.setText("Your Gender: "
//							+ user.getProperty("gender").toString());
					//location.setText("Your Current Location: "
						//	+ user.getLocation().getProperty("name")
							//		.toString());
				}
			}
		}).executeAsync();
	} else if (state.isClosed()) {
		Log.i(TAG, "Logged out...");
		otherView.setVisibility(View.GONE);
	}
}













@Override
public void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);

	uiHelper.onActivityResult(requestCode, resultCode, data);
	Log.i(TAG, "OnActivityResult...");
}

@Override
public void onResume() {
	super.onResume();
	uiHelper.onResume();
}

@Override
public void onPause() {
	super.onPause();
	uiHelper.onPause();
}

@Override
public void onDestroy() {
	super.onDestroy();
	uiHelper.onDestroy();
}

@Override
public void onSaveInstanceState(Bundle outState) {
	super.onSaveInstanceState(outState);
	uiHelper.onSaveInstanceState(outState);
}



}