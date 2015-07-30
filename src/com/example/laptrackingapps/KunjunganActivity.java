package com.example.laptrackingapps;

import java.io.File;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.example.databaselap.TM_Caregiver;
import com.example.databaselap.TM_Child;
import com.example.databaselap.TM_Child_Facility;
import com.example.databaselap.TM_Class;
import com.example.databaselap.TM_Cost_Facility;
import com.example.databaselap.TM_Drug_Dose;
import com.example.databaselap.TM_Drug_Status;
import com.example.databaselap.TM_Drug_Type;
import com.example.databaselap.TM_Facility;
import com.example.databaselap.TM_Parent_Status;
import com.example.modellap.ChildFacility_Model;
import com.example.modellap.Child_Model;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;
import android.widget.Toast;

public class KunjunganActivity extends Activity {

	ImageView back, edit_infoanak, edit_kunjungan;
	LinearLayout tambah_kunjungan;
	TextView lihat_selengkapnya;
	LinearLayout lihatselengkapnya;
	TextView txt_Nama_Anak, txt_BOD_Anak, txt_Umur_Anak, txt_JK_Anak,
			txt_GOLDA_Anak, txt_ALAMAT_Anak, txt_StatusARV, txt_JenisARV,
			txt_DosisARV, txt_NamaAyah, txt_StatusAyah, txt_NamaIbu,
			txt_StatusIbu, txt_NamaPengasuh, Pengasuh, txt_NomorTelepon,
			txt_Sekolah, txt_Kelas, txt_Fasilitas;
	String facility_name = "";
	String id_child, id_child_l, id_child_k,child_id_fkra;

	// instansisasi tabel
	TM_Caregiver tabel_caregiver;
	TM_Class tabel_kelas;
	TM_Drug_Dose tabel_dosis;
	TM_Drug_Status tabel_status_arv;
	TM_Drug_Type tabel_type_arv;
	TM_Facility tabel_facility;
	TM_Parent_Status tabel_parent_status;
	TM_Child tabel_anak;
	TM_Child_Facility tabel_fasilitas_anak;
	TM_Cost_Facility tabel_cost_fasilitas;
	
	Child_Model model_anak;

	TextView ivTgl1, ivTgl2, ivTgl3, ivTgl4, ivTgl5, ivTgl6, ivTgl7, ivTgl8,
			ivTgl1a, ivTgl2a, ivTgl3a, ivTgl4a, ivTgl5a, ivTgl6a, ivTgl7a,
			ivTgl8a;

	RelativeLayout RLtgl1, RLtgl2, RLtgl3, RLtgl4, RLtgl5, RLtgl6, RLtgl7,
			RLtgl8;

	final Context context = this;
	ImageView image_anak;
	SearchView searchView1;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_kunjungan);

		// instanstiate
		tabel_caregiver = new TM_Caregiver(getApplicationContext());
		tabel_kelas = new TM_Class(getApplicationContext());
		tabel_dosis = new TM_Drug_Dose(getApplicationContext());
		tabel_status_arv = new TM_Drug_Status(getApplicationContext());
		tabel_type_arv = new TM_Drug_Type(getApplicationContext());
		tabel_facility = new TM_Facility(getApplicationContext());
		tabel_parent_status = new TM_Parent_Status(getApplicationContext());
		tabel_anak = new TM_Child(getApplicationContext());
		tabel_fasilitas_anak = new TM_Child_Facility(getApplicationContext());
		tabel_cost_fasilitas = new TM_Cost_Facility(getApplicationContext());

		// model anak
		model_anak = new Child_Model();

		txt_Nama_Anak = (TextView) findViewById(R.id.viewNamaAnak);
		txt_BOD_Anak = (TextView) findViewById(R.id.viewBODAnak);
		txt_Umur_Anak = (TextView) findViewById(R.id.viewUMURAnak);
		txt_JK_Anak = (TextView) findViewById(R.id.viewJKAnak);
		txt_GOLDA_Anak = (TextView) findViewById(R.id.viewGOLDAAnak);
		txt_ALAMAT_Anak = (TextView) findViewById(R.id.viewALAMATAnak);
		txt_StatusARV = (TextView) findViewById(R.id.viewStatusARV);
		txt_JenisARV = (TextView) findViewById(R.id.viewJenisARV);
		txt_DosisARV = (TextView) findViewById(R.id.viewDosisARV);
		txt_NamaAyah = (TextView) findViewById(R.id.viewNamaAyah);
		txt_StatusAyah = (TextView) findViewById(R.id.viewStatusAyah);
		txt_NamaIbu = (TextView) findViewById(R.id.viewNamaIbu);
		txt_StatusIbu = (TextView) findViewById(R.id.viewStatusIbu);
		txt_NamaPengasuh = (TextView) findViewById(R.id.viewNamaPengasuh);
		Pengasuh = (TextView) findViewById(R.id.viewPengasuh);
		txt_NomorTelepon = (TextView) findViewById(R.id.viewNomorTelepon);
		txt_Sekolah = (TextView) findViewById(R.id.viewSekolah);
		txt_Kelas = (TextView) findViewById(R.id.viewKelas);
		txt_Fasilitas = (TextView) findViewById(R.id.viewFasilitas);

		// textview
		ivTgl1 = (TextView) findViewById(R.id.ivTgl1);
		ivTgl2 = (TextView) findViewById(R.id.ivTgl2);
		ivTgl3 = (TextView) findViewById(R.id.ivTgl3);
		ivTgl4 = (TextView) findViewById(R.id.ivTgl4);
		ivTgl5 = (TextView) findViewById(R.id.ivTgl5);
		ivTgl6 = (TextView) findViewById(R.id.ivTgl6);
		ivTgl7 = (TextView) findViewById(R.id.ivTgl7);
		ivTgl8 = (TextView) findViewById(R.id.ivTgl8);

		// textview
		ivTgl1a = (TextView) findViewById(R.id.ivTgl1a);
		ivTgl2a = (TextView) findViewById(R.id.ivTgl2a);
		ivTgl3a = (TextView) findViewById(R.id.ivTgl3a);
		ivTgl4a = (TextView) findViewById(R.id.ivTgl4a);
		ivTgl5a = (TextView) findViewById(R.id.ivTgl5a);
		ivTgl6a = (TextView) findViewById(R.id.ivTgl6a);
		ivTgl7a = (TextView) findViewById(R.id.ivTgl7a);
		ivTgl8a = (TextView) findViewById(R.id.ivTgl8a);

		// relative layout
		RLtgl1 = (RelativeLayout) findViewById(R.id.rl1);
		RLtgl2 = (RelativeLayout) findViewById(R.id.rl2);
		RLtgl3 = (RelativeLayout) findViewById(R.id.rl3);
		RLtgl4 = (RelativeLayout) findViewById(R.id.rl4);
		RLtgl5 = (RelativeLayout) findViewById(R.id.rl5);
		RLtgl6 = (RelativeLayout) findViewById(R.id.rl6);
		RLtgl7 = (RelativeLayout) findViewById(R.id.rl7);
		RLtgl8 = (RelativeLayout) findViewById(R.id.rl8);

		image_anak = (ImageView) findViewById(R.id.ij3);
		
		searchView1 = (SearchView) findViewById(R.id.svKunjungan);


		// get child id to retrieve from database and display
		// hear intent from list activity and form kunjungan activity

		Intent intent = getIntent();
		id_child_l = intent.getStringExtra("id_child");
		id_child_k = intent.getStringExtra("id_anak_k");
		child_id_fkra = intent.getStringExtra("child_id_fkra");
		
		if (id_child_l != null) {
			id_child = id_child_l;
		} else if (id_child_k != null) {
			id_child = id_child_k;
		}
		else if(child_id_fkra != null)
		{
			id_child = child_id_fkra;
		}
		else if(intent.getStringExtra("id_child_ka") !=null)
		{
			id_child = intent.getStringExtra("id_child_ka");
		}
		else if(intent.getStringExtra("id_anak_pencarian") != null)
		{
			id_child = intent.getStringExtra("id_anak_pencarian") ;
		}
		else if(intent.getStringExtra("id_child_own") != null)
		{
			id_child = ""+1 ;
		}
		
		model_anak = tabel_anak.getChildIdentityById(id_child);

		txt_Nama_Anak.setText(model_anak.getChild_name());
		txt_BOD_Anak.setText(model_anak.getChild_bod());
		if (model_anak.getChild_bod().equals("")) {
			txt_Umur_Anak.setText("-");
		} else {
			txt_Umur_Anak.setText(getChildAge(model_anak.getChild_bod()));
		}

		txt_JK_Anak.setText(model_anak.getChild_gender());
		txt_GOLDA_Anak.setText(model_anak.getBlood_type());
		txt_ALAMAT_Anak.setText(model_anak.getChild_address());

		//txt_StatusARV.setText(model_anak.getDrug_status_id());
		//txt_JenisARV.setText(model_anak.getDrug_type_id());
		txt_DosisARV.setText(model_anak.getDrug_dose_id());
		txt_StatusARV.setText(tabel_status_arv.getNameStatusARV(model_anak
				.getDrug_status_id()));
		txt_JenisARV.setText("Zidovudin");
//		txt_DosisARV.setText(tabel_dosis.getNameDose(model_anak
//				.getDrug_dose_id()));

		txt_NamaAyah.setText(model_anak.getFather_name());
		txt_StatusAyah.setText(tabel_parent_status
				.getNameStatusParent(model_anak.getDad_status_id()));
		txt_NamaIbu.setText(model_anak.getMother_name());
		txt_StatusIbu.setText(tabel_parent_status
				.getNameStatusParent(model_anak.getMom_status_id()));
		txt_NamaPengasuh.setText(model_anak.getCaregiver_name());
		Pengasuh.setText(tabel_caregiver.getNameCaregiver(model_anak
				.getCaregiver_id()));
		txt_NomorTelepon.setText(model_anak.getCaregiver_phone());
		txt_Sekolah.setText(model_anak.getSchool_name());
		txt_Kelas.setText(tabel_kelas.getNameKelas(model_anak.getClass_id()));
		getChildFacilities(id_child);
		txt_Fasilitas.setText(facility_name);
		// set image
		String as = "kosong";
		if (model_anak.getImage_path().equals(as)) {
			Bitmap bitmap1 = BitmapFactory.decodeResource(
					context.getResources(), R.drawable.icon);
			image_anak.setImageBitmap(bitmap1);
		} else {
			File image = new File(model_anak.getImage_path());

			BitmapFactory.Options bmOptions = new BitmapFactory.Options();

			Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(),
					bmOptions);

			bitmap = Bitmap.createScaledBitmap(bitmap, 120, 120, true);
			image_anak.setImageBitmap(bitmap);

		}

		// set visit date

		final int[] tvBulan = { R.id.ivTgl1, R.id.ivTgl2, R.id.ivTgl3,
				R.id.ivTgl4, R.id.ivTgl5, R.id.ivTgl6, R.id.ivTgl7, R.id.ivTgl8 };

		final int[] tvTahun = { R.id.ivTgl1a, R.id.ivTgl2a, R.id.ivTgl3a,
				R.id.ivTgl4a, R.id.ivTgl5a, R.id.ivTgl6a, R.id.ivTgl7a,
				R.id.ivTgl8a };

		TextView tv = null;
		TextView tvYear = null;

		// tanggal
		ArrayList<Integer> aList = new ArrayList<Integer>();
		// Add elements to ArrayList object
		aList.add(1);
		aList.add(2);
		aList.add(3);
		aList.add(4);
		aList.add(5);
		aList.add(6);
		aList.add(7);
		aList.add(8);
		aList.add(9);

		// bulan
		ArrayList<Integer> aListBulan = new ArrayList<Integer>();
		// Add elements to ArrayList object
		aListBulan.add(1);
		aListBulan.add(2);
		aListBulan.add(3);
		aListBulan.add(4);
		aListBulan.add(5);
		aListBulan.add(6);
		aListBulan.add(7);
		aListBulan.add(8);
		aListBulan.add(9);

		ArrayList<Integer> aListYear = new ArrayList<Integer>();
		// Add elements to ArrayList object
		aListYear.add(2011);
		aListYear.add(2012);
		aListYear.add(2013);
		aListYear.add(2014);
		aListYear.add(2015);
		aListYear.add(2016);
		aListYear.add(2017);
		aListYear.add(2018);
		aListYear.add(2019);

		if (aList.size() > 7 && aListYear.size() > 7 && aListBulan.size() > 7) {
			int lastIndex = aList.size() - 1;
			aList.remove(0);
			int lastIndexMonth = aListBulan.size() - 1;
			aListBulan.remove(0);
			int lastIndexYear = aListYear.size() - 1;
			aListYear.remove(0);
		}
		// String.format("%02d",aList);
		Collections.reverse(aList);
		Collections.reverse(aListBulan);
		Collections.reverse(aListYear);

		for (int j = 0; j < aList.size(); j++) {
			tv = (TextView) findViewById(tvBulan[j]);
			tv.setText(formatDigit(aList.get(j)));
			tv.setGravity(View.TEXT_ALIGNMENT_CENTER);
			// tv.setText(String.format("%02d",(CharSequence) aList.get(j)));
		}

		for (int k = 0; k < aListYear.size(); k++) {
			tvYear = (TextView) findViewById(tvTahun[k]);
			tvYear.setText(formatDigit(aListBulan.get(k)) + "."
					+ set2Digit(aListYear.get(k)));
			tvYear.setGravity(View.TEXT_ALIGNMENT_CENTER);
			// tv.setText(String.format("%02d",(CharSequence) aList.get(j)));
		}
		ivTgl1.setBackgroundDrawable(getResources()
				.getDrawable(R.drawable.lila));
		ivTgl3.setBackgroundDrawable(getResources()
				.getDrawable(R.drawable.lila));

		RLtgl1.setVisibility(View.VISIBLE);

		searchView1.setOnQueryTextListener(new OnQueryTextListener() {

			@Override
			public boolean onQueryTextSubmit(String query) {
				// TODO Auto-generated method stub

				Toast.makeText(getBaseContext(), "query: " + query,
						Toast.LENGTH_SHORT).show();

				// setContentView(R.layout.pencarian);

				// String input = svKunjungan.getQuery().toString();

				Intent intent = new Intent(KunjunganActivity.this,
						Pencarian.class);
				intent.putExtra("id_anak", ""+id_child);
				intent.putExtra("input", "" + query);
				startActivity(intent);
				// Toast.makeText(getBaseContext(), svKunjungan.getQuery(),
				// Toast.LENGTH_SHORT).show();
				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				// TODO Auto-generated method stub

				// Toast.makeText(getBaseContext(), newText,
				// Toast.LENGTH_SHORT).show();
				return false;
			}
		});

		back = (ImageView) findViewById(R.id.btn_back);
		tambah_kunjungan = (LinearLayout) findViewById(R.id.button_tambahkunjungan);
		edit_infoanak = (ImageView) findViewById(R.id.edit_infoanak);
		edit_kunjungan = (ImageView) findViewById(R.id.edit_kunjungan);

		// String split[] = model_anak.getChild_bod().split(" ");
		//
		// Toast.makeText(
		// getApplicationContext(),
		// "tanggal " + split[0] + " bulan " + split[1] + " tahun "
		// + split[2], Toast.LENGTH_LONG).show();

		lihatselengkapnya = (LinearLayout) findViewById(R.id.layout_lihatselengkapnya);
		lihat_selengkapnya = (TextView) findViewById(R.id.textview_lihat_selengkapnya);
		lihat_selengkapnya.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (lihat_selengkapnya.getVisibility() == View.VISIBLE) {
					lihatselengkapnya.setVisibility(View.VISIBLE);
				} else {
					lihatselengkapnya.setVisibility(View.INVISIBLE);
				}
			}
		});
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

	public String formatDigit(int input) {
		// to separate year into single integer
		String frmt = String.format("%02d", input);

		return frmt;
	}

	// get child age
	private String getChildAge(String bod) {
		String split[] = bod.split(" ");
		String[] Month = { "Januari", "Februari", "Maret", "April", "Mei",
				"Juni", "Juli", "Agustus", "September", "Oktober", "November",
				"Desember" };
		// get int of month
		// January -> 1
		int bulan = -1;
		for (int i = 0; i < Month.length; i++) {
			if (Month[i].equals(split[1])) {
				bulan = i;
				break;
			}
		}

		Calendar dob = Calendar.getInstance();
		Calendar today = Calendar.getInstance();
		dob.set(Integer.parseInt(split[2]), bulan, Integer.parseInt(split[0]));
		int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
		if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
			age--;
		}
		Integer ageInt = new Integer(age);
		String ageS = ageInt.toString();

		return ageS;
	}

	public String getChildFacilities(String id_child)
	{
		ArrayList<ChildFacility_Model> arr = new ArrayList<ChildFacility_Model>();
		Log.e("Retrieve fasilitas anak", "");
		arr = tabel_fasilitas_anak.getSemuaFasilitasAnak(id_child);
		for(ChildFacility_Model a: arr)
		{
			facility_name += tabel_cost_fasilitas.getNameCostFacility(a.getFacility_cost_id()) +"\n";
		}
		return facility_name;
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.kunjungan, menu);
		return true;
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn_back:
			Intent intent = new Intent(KunjunganActivity.this,
					ListAnakActivity.class);
			startActivity(intent);
			break;

		case R.id.button_tambahkunjungan:
			Intent intent2 = new Intent(KunjunganActivity.this,
					FormulirKunjunganAnakActivity.class);
			intent2.putExtra("id_anak_fk", id_child);
			startActivity(intent2);
			Log.i("id anak pda kunj", " " + id_child);
			break;

		case R.id.edit_infoanak:
			Intent intent_ = getIntent();
			String id_child = intent_.getStringExtra("id_child");
			Intent intent3 = new Intent(KunjunganActivity.this,
					ProfilAnakActivity.class);
			intent3.putExtra("id_anak", id_child);
			startActivity(intent3);
			break;

		case R.id.edit_kunjungan:
			Intent intent4 = new Intent(KunjunganActivity.this,
					FormulirKunjunganAnakActivity.class);
			startActivity(intent4);
			break;

		default:
			break;
		}
	}

}
