package com.refushi.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.refushi.LoginActivity;
import com.refushi.MainActivity;
import com.refushi.R;
//import com.google.android.gms.internal.fo;

public class FragmentIntro3 extends Fragment implements OnClickListener{

	
	private Button btn_go_to_Login,btn_no_thanx;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		
		
		View rootView = inflater.inflate(R.layout.frag3, container, false);
        btn_go_to_Login = (Button) rootView.findViewById(R.id.btn_go_to_Login);
        btn_no_thanx = (Button) rootView.findViewById(R.id.btn_no_thanx);
		
        btn_no_thanx.setOnClickListener(this);

        btn_go_to_Login.setOnClickListener(this);
		// TODO Auto-generated method stub
		return rootView;
	}
	
	
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_no_thanx:
            	Intent intent = new Intent(this.getActivity(), MainActivity.class);
            	startActivity(intent);
                break;
                
            case R.id.btn_go_to_Login:
            	Intent intent_go_to_Login = new Intent(this.getActivity(), LoginActivity.class);
            	startActivity(intent_go_to_Login);
                break;
        }
    }
	
	public static FragmentIntro3 newInstance(String text) {

		FragmentIntro3 f = new FragmentIntro3();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
	
}
