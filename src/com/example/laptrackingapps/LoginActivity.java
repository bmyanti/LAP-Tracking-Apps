package com.example.laptrackingapps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity implements OnClickListener {

	EditText logingrup, username, password;
	Button login;
	TextView result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		login = (Button) findViewById(R.id.btn_login);
		login.setOnClickListener(this);

		result = (TextView) findViewById(R.id.textview_result);
	}

	private void openAlert(View view) {

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				LoginActivity.this);

		alertDialogBuilder.setTitle("Exit LAP Trackings Apps");

		alertDialogBuilder.setMessage("Keluar dari aplikasi?");
		// set neutral button: Exit the app message

		alertDialogBuilder.setNegativeButton("Ya",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int id) {
						// exit the app and go to the HOME
						LoginActivity.this.finish();

					}

				});

		// set positive button: Yes message

		alertDialogBuilder.setPositiveButton("Tidak",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int id) {

						// go to a new activity of the app
						dialog.cancel();

					}

				});

		AlertDialog alertDialog = alertDialogBuilder.create();

		// show alert

		alertDialog.show();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	/*
	 * private class JSONLogin extends AsyncTask<String, String, String>{
	 * 
	 * // Create a new HttpClient and Post Header HttpClient httpclient = new
	 * DefaultHttpClient(); //login.php returns true if logingrup, username, and
	 * password is equal to qwerty HttpPost httppost = new
	 * HttpPost("http://192.168.1.109:8000/XAMPP%20Project/loginlap.php");
	 * 
	 * EditText grup = (EditText) findViewById(R.id.editlogingrup); String
	 * logingrup = grup.getText().toString(); EditText user = (EditText)
	 * findViewById(R.id.editusername); String username =
	 * user.getText().toString(); EditText pass = (EditText)
	 * findViewById(R.id.editpassword); String password =
	 * pass.getText().toString();
	 * 
	 * @Override protected String doInBackground(String... params) { // TODO
	 * Auto-generated method stub try { List<NameValuePair> nameValuePairs = new
	 * ArrayList<NameValuePair>(3); nameValuePairs.add(new
	 * BasicNameValuePair("logingrup", logingrup)); nameValuePairs.add(new
	 * BasicNameValuePair("username", username)); nameValuePairs.add(new
	 * BasicNameValuePair("password", password)); httppost.setEntity(new
	 * UrlEncodedFormEntity(nameValuePairs));
	 * 
	 * // Execute HTTP Post Request Log.w("LAP", "Execute HTTP Post Request");
	 * HttpResponse response = httpclient.execute(httppost);
	 * 
	 * String str =
	 * inputStreamToString(response.getEntity().getContent()).toString();
	 * Log.w("LAP", str);
	 * 
	 * if(str.toString().equalsIgnoreCase("true")) { Log.w("LAP", "TRUE");
	 * //result.setText("Login successful"); }else { Log.w("LAP", "FALSE");
	 * result.setText(str); }
	 * 
	 * } catch (ClientProtocolException e) { e.printStackTrace(); } catch
	 * (IOException e) { e.printStackTrace(); }
	 * 
	 * return null; }
	 * 
	 * private Object inputStreamToString(InputStream is) { // TODO
	 * Auto-generated method stub String line = ""; StringBuilder total = new
	 * StringBuilder(); // Wrap a BufferedReader around the InputStream
	 * BufferedReader rd = new BufferedReader(new InputStreamReader(is)); //
	 * Read response until the end try { while ((line = rd.readLine()) != null)
	 * { total.append(line); } } catch (IOException e) { e.printStackTrace(); }
	 * // Return full string return total; }
	 * 
	 * }
	 */

	// untuk keluar dari aplikasi
	public void onBackPressed() {
		finish();
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn_login:
			if (view == login) {
				// new JSONLogin().execute();
				Intent intent = new Intent(LoginActivity.this,
						ListAnakActivity.class);
				startActivity(intent);
			} // else {
				// Toast.makeText(getApplicationContext(), "Incorrect input",
				// Toast.LENGTH_LONG).show();
			// }
			break;

		default:
			break;
		}
	}

}
