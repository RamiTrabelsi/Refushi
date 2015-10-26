package com.refushi;

import com.pixplicity.easyprefs.library.Prefs;
import com.refushi.external.RefushiManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Login_activity extends Activity {
RefushiManager mManager;
	
	private EditText edit_Email,edit_password;
	private Button  btn_login;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_activity);
		
		
		
		edit_Email               = (EditText) findViewById(R.id.edit_Email);
		edit_password            = (EditText) findViewById(R.id.edit_Password);
		btn_login=(Button)findViewById(R.id.btn_Login);
		
		btn_login.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				postData();
			}
		});
	
		
	}

	





public void postData() {

String email = edit_Email.getText().toString();
String password = edit_password.getText().toString();

if (email.equals("") || password.equals("")  ) {
	mManager.showWarning(Login_activity.this, "Merci de renseigner votre Email et Mot de passe");


}else if (!mManager.isValidEmail(email)) {
	mManager.showWarning(Login_activity.this, "Veuillez introduire une adresse mail valide");


} else
	{
	//mManager.login(Login_activity.this, email, password);
	Prefs.putString("email", email);
	Prefs.putString("password", password);
	}

}}