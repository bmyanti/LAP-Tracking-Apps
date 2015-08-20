package com.example.laptrackingapps;

import java.util.ArrayList;
import java.util.Collections;

import com.example.databaselap.Database;
import com.example.modellap.Complaint_Model;
import com.example.modellap.Visit_Model;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class Pencarian extends Activity {

	SearchView svPencarian, svKunjungan;
	TextView tvInput, tvKeluhanSearch1;
	String id_anak, a;
	String model_pencarian;
	Database db;
	 ArrayList<Visit_Model> g = new ArrayList<Visit_Model>();
	 ArrayList<Visit_Model> h = new ArrayList<Visit_Model>();
	 ArrayList<Visit_Model> j = new ArrayList<Visit_Model>();

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pencarian);
		
		db = new Database(this);
		svPencarian = (SearchView) findViewById(R.id.svPencarian);
		svKunjungan = (SearchView) findViewById(R.id.svKunjungan);
		tvInput = (TextView) findViewById(R.id.tvInput);
		tvKeluhanSearch1 = (TextView) findViewById(R.id.tvKeluhanSearch1);
		final int[] tvPencarian = { R.id.tvKeluhanSearch1,R.id.tvKeluhanSearch2, R.id.tvKeluhanSearch3,R.id.tvKeluhanSearch4,
				R.id.tvKeluhanSearch5,R.id.tvKeluhanSearch6, R.id.tvKeluhanSearch7,R.id.tvKeluhanSearch8};
		final int[] tvStatus = { R.id.tvStatus1, R.id.tvStatus2,R.id.tvStatus3, R.id.tvStatus4,
				R.id.tvStatus5, R.id.tvStatus6,R.id.tvStatus7, R.id.tvStatus8};
		final int[] tvTindakan = { R.id.tvTindakan1, R.id.tvTindakan2,R.id.tvTindakan3, R.id.tvTindakan4,
				R.id.tvTindakan5, R.id.tvTindakan6,R.id.tvTindakan7, R.id.tvTindakan8};
		final int[] tvTgl = { R.id.ivTgl_penc1, R.id.ivTgl_penc2,R.id.ivTgl_penc3, R.id.ivTgl_penc4,
				R.id.ivTgl_penc5, R.id.ivTgl_penc6,R.id.ivTgl_penc7, R.id.ivTgl_penc8};
		final int[] tvMonthYear = { R.id.ivTgl1a, R.id.ivTgl2a, R.id.ivTgl3a,R.id.ivTgl4a, 
				R.id.ivTgl5a, R.id.ivTgl6a, R.id.ivTgl7a,R.id.ivTgl8a};
		final int[] tvRl = { R.id.linPenc1, R.id.linPenc2, R.id.linPenc3,R.id.linPenc4,
				R.id.linPenc5, R.id.linPenc6, R.id.linPenc7,R.id.linPenc8 };

		Intent i = getIntent();
		a = i.getStringExtra("input");
		id_anak = i.getStringExtra("id_anak");
		model_pencarian = db.getIdComp(a);
		ArrayList<Complaint_Model> d = new ArrayList<Complaint_Model>();
		ArrayList<Visit_Model> aList = new ArrayList<Visit_Model>();
		  aList = db.get8VisitDate(id_anak);
		  
		 Collections.reverse(aList);
		
			    	g = db.searchComplaintAction(model_pencarian,id_anak,a);
			    	
			    
					for (int j = 0; j < g.size(); j++) {
						if(g.size()>8)
			            {
			             int lastIndex = g.size() - 1;
			             g.remove(lastIndex);
			            }
						//g.get(j).GetVisitDate();
						
						TextView tvKeluhan = (TextView) findViewById(tvPencarian[j]);
						TextView tvStat = (TextView) findViewById(tvStatus[j]);
						TextView tvTind = (TextView) findViewById(tvTindakan[j]);
						TextView tvTangl = (TextView) findViewById(tvTgl[j]);
						TextView tvBulanTahun = (TextView) findViewById(tvMonthYear[j]);
						LinearLayout rl = (LinearLayout) findViewById(tvRl[j]);
						Complaint_Model f = g.get(j).GetComplaints().get(j);

						String k = g.get(j).GetCreatTime().get(j);
						String kel = db.getNameComplaint(f.GetKeluhan());
						String stat = db.getNameComplaintStatus(f.GetStatusKeluhan());
						String[] splitedValue = k.split("-");
						int df = Integer.parseInt(splitedValue[2]);
						tvStat.setText(stat);
						tvKeluhan.setText(kel);
						tvTind.setText(f.GetTindakan());
						
						rl.setVisibility(View.VISIBLE);
						
						if (g.get(j).GetVisitTypId().get(j).equals("VT002")) {
							tvTangl.setBackgroundDrawable(getResources().getDrawable(
									R.drawable.lila));
							tvTangl.setText(splitedValue[0]);
							tvBulanTahun.setText(splitedValue[1] + "." + set2Digit(df));
							tvBulanTahun.setGravity(View.TEXT_ALIGNMENT_CENTER);
							
						} else {
							tvTangl.setBackgroundDrawable(getResources().getDrawable(
									R.drawable.kotak_tanggal));
							tvTangl.setText(splitedValue[0]);
							tvBulanTahun.setText(splitedValue[1] + "." + set2Digit(df));
							tvBulanTahun.setGravity(View.TEXT_ALIGNMENT_CENTER);
						}
						if (g.get(j).GetVisitDate()!=null) {
							tvInput.setText("'" + a + "'");
						} else {
							Toast.makeText(getApplicationContext(), "Pencarian Tidak Ada",
									Toast.LENGTH_SHORT).show();
							tvInput.setText("'" + a + "' "+" TIDAK DITEMUKAN");
						}

						}
					
				svPencarian.setQueryHint(a);
				
			
	
	}
	public String set2Digit(int input) {
		// to separate year into single integer
		int var, var1, var2, var3, var4;
		var1 = ((int) (input / 1000));
		var2 = ((int) (input / 100)) - (var1 * 10);
		var3 = ((int) (input / 10)) - (var1 * 100 + var2 * 10);
		var4 = input - (var1 * 1000 + var2 * 100 + var3 * 10);
		String index3 = Integer.toString(var3);
		String index4 = Integer.toString(var4);
		String Digit = index3.concat(index4);
		return Digit;
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn_back:
			Intent intent = new Intent(Pencarian.this, KunjunganActivity.class);
			intent.putExtra("id_anak_pencarian", "" + id_anak);
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