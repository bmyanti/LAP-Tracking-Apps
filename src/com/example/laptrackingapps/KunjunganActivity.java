package com.example.laptrackingapps;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class KunjunganActivity extends Activity {
	
	ImageView back, edit_infoanak, edit_kunjungan;
	LinearLayout tambah_kunjungan;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_kunjungan);
		
		back = (ImageView) findViewById(R.id.btn_back);
		tambah_kunjungan = (LinearLayout) findViewById(R.id.button_tambahkunjungan);
		edit_infoanak = (ImageView) findViewById(R.id.edit_infoanak);
		edit_kunjungan = (ImageView) findViewById(R.id.edit_kunjungan);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.kunjungan, menu);
		return true;
	}
	
	public void onClick (View view){
		switch (view.getId()) {
		case R.id.btn_back:
			Intent intent = new Intent(KunjunganActivity.this, ListAnakActivity.class);
			startActivity(intent);
			break;
			
		case R.id.button_tambahkunjungan:
			Intent intent2 = new Intent(KunjunganActivity.this, FormulirKunjunganAnakActivity.class);
			startActivity(intent2);
			break;
		
		case R.id.edit_infoanak:
			Intent intent3 = new Intent(KunjunganActivity.this, ProfilAnakActivity.class);
			startActivity(intent3);
			break;
			
		case R.id.edit_kunjungan:
			Intent intent4 = new Intent(KunjunganActivity.this, FormulirKunjunganAnakActivity.class);
			startActivity(intent4);
			break;

		default:
			break;
		}
	}

}
