package com.example.laptrackingapps;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.example.databaselap.TM_Caregiver;
import com.example.databaselap.TM_Child;
import com.example.databaselap.TM_Class;
import com.example.databaselap.TM_Drug_Dose;
import com.example.databaselap.TM_Drug_Status;
import com.example.databaselap.TM_Drug_Type;
import com.example.databaselap.TM_Facility;
import com.example.databaselap.TM_Parent_Status;
import com.example.modellap.Child_Model;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
	String id_child, id_child_l, id_child_k;

	// instansisasi tabel
	TM_Caregiver tabel_caregiver;
	TM_Class tabel_kelas;
	TM_Drug_Dose tabel_dosis;
	TM_Drug_Status tabel_status_arv;
	TM_Drug_Type tabel_type_arv;
	TM_Facility tabel_facility;
	TM_Parent_Status tabel_parent_status;
	TM_Child tabel_anak;
	Child_Model model_anak;

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

		// get child id to retrieve from database and display
		//hear intent from list activity and form kunjungan activity
		
		Intent intent = getIntent();
		id_child_l = intent.getStringExtra("id_child");
		id_child_k = intent.getStringExtra("id_anak_k");

		if (id_child_l != null ) {
			id_child = id_child_l;
		}
		else if(id_child_k != null)
		{
			id_child = id_child_k;
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

		// txt_StatusARV.setText(model_anak.getDrug_status_id());
		txt_JenisARV.setText(model_anak.getDrug_type_id());
		// txt_DosisARV.setText(model_anak.getDrug_dose_id());
		txt_StatusARV.setText(tabel_status_arv.getNameStatusARV(model_anak
				.getDrug_status_id()));
		txt_JenisARV.setText(tabel_type_arv.getNameTypeARV(model_anak
				.getDrug_type_id()));
		txt_DosisARV.setText(tabel_dosis.getNameDose(model_anak
				.getDrug_dose_id()));

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
		txt_Fasilitas.setText(facility_name);

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
