package com.example.laptrackingapps;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class Pencarian extends Activity {

	SearchView svPencarian, svKunjungan;
	TextView tvInput;
	String id_anak;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pencarian);

		svPencarian = (SearchView) findViewById(R.id.svPencarian);
		svKunjungan = (SearchView) findViewById(R.id.svKunjungan);
		tvInput = (TextView) findViewById(R.id.tvInput);

		Intent i = getIntent();
		String a = i.getStringExtra("input");
		if (i.getStringExtra("id_anak") != null) {
			id_anak = i.getStringExtra("id_anak");
		}
		svPencarian.setQueryHint(a);
		tvInput.setText("'" + a + "'");

	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn_back:
			Intent intent = new Intent(Pencarian.this, KunjunganActivity.class);
			intent.putExtra("id_anak_pencarian", ""+id_anak);
			startActivity(intent);
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pencarian, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	

}