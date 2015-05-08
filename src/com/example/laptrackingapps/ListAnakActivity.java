package com.example.laptrackingapps;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ListAnakActivity extends Activity {
	
	ImageView btn_back, reminder_lab, reminder_obat;
	LinearLayout tambah_profil, daftar_anak1;
	
	final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_list_anak);
		
		btn_back = (ImageView) findViewById(R.id.btn_back);
		tambah_profil  = (LinearLayout) findViewById(R.id.button_tambahprofil);
		reminder_lab = (ImageView) findViewById(R.id.reminder_lab);
		reminder_obat = (ImageView) findViewById(R.id.reminder_obat);
		daftar_anak1 = (LinearLayout) findViewById(R.id.daftar_anak1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_anak, menu);
		return true;
	}
	
	public void onClick (View view){
		switch (view.getId()) {
		case R.id.btn_back:
			Intent intent = new Intent(ListAnakActivity.this, LoginActivity.class);
			startActivity(intent);
			break;
		
		case R.id.button_tambahprofil:
			Intent intent2 = new Intent(ListAnakActivity.this, ProfilAnakActivity.class);
			startActivity(intent2);
			break;
			
		case R.id.daftar_anak1:
			Intent intent3 = new Intent(ListAnakActivity.this, KunjunganActivity.class);
			startActivity(intent3);
			break;
			
		case R.id.reminder_lab:
			LayoutInflater layoutinflater = LayoutInflater.from(context);
			View promptView = layoutinflater.inflate(R.layout.activity_reminder_lab, null);
			
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
			alertDialogBuilder.setView(promptView);
			
			AlertDialog alertD = alertDialogBuilder.create();
			alertD.show();
			
			break;
			
		case R.id.reminder_obat:
			LayoutInflater lf = LayoutInflater.from(context);
			View pv = lf.inflate(R.layout.activity_reminder_obat, null);
			
			AlertDialog.Builder alert = new AlertDialog.Builder(context);
			alert.setView(pv);
			
			AlertDialog alertd = alert.create();
			alertd.show();
			
			break;

		default:
			break;
		}
	}

}
