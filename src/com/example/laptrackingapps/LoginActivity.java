package com.example.laptrackingapps;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
	
	EditText logingrup, username, password;
	Button login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		logingrup = (EditText) findViewById(R.id.editlogingrup);
		username = (EditText) findViewById(R.id.editusername);
		password = (EditText) findViewById(R.id.editpassword);
		login = (Button) findViewById(R.id.btn_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	//untuk keluar dari aplikasi
	public void onBackPressed()
	{
		moveTaskToBack(true);
		finish();
	}
	
	public void onClick (View view){
		switch (view.getId()) {
		case R.id.btn_login :
			Intent intent = new Intent(LoginActivity.this, ListAnakActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

}
