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
import android.view.View.OnClickListener;
import android.widget.CheckBox;
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
			
			final LinearLayout btn_simpan = (LinearLayout) promptView.findViewById(R.id.btn_simpan);
			final CheckBox cb1 = (CheckBox) promptView.findViewById(R.id.checkBox1);
			final CheckBox cb2 = (CheckBox) promptView.findViewById(R.id.checkBox2);
			final CheckBox cb3 = (CheckBox) promptView.findViewById(R.id.checkBox3);
			btn_simpan.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent simpan  = new Intent(getApplication(),ProfilAnakActivity.class);
					Bundle b = new Bundle();
					/*if(cb1.isSelected()){
						String susu1 = cb1.getText().toString();
						b.putString("parse_susu", susu1);
						}
					else if(cb2.isSelected()){
						String susu2 = cb2.getText().toString();
						b.putString("parse_susu", susu2);
					}
					else if(cb3.isSelected()){
						String susu3 = cb3.getText().toString();
						b.putString("parse_susu", susu3);
					}*/
					if(cb1.isChecked()) {
						b.putString("parse_susu1", cb1.getText().toString());		
					}if(cb2.isChecked()) {
						b.putString("parse_susu2", cb2.getText().toString());
					}if(cb3.isChecked()){
						b.putString("parse_susu3", cb3.getText().toString());
					}
					simpan.putExtras(b);
					startActivity(simpan);
					
				}
			});
			
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
