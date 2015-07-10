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
import com.example.modellap.TM_Child_Model;

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

	// instansisasi tabel
	TM_Caregiver tabel_caregiver;
	TM_Class tabel_kelas;
	TM_Drug_Dose tabel_dosis;
	TM_Drug_Status tabel_status_arv;
	TM_Drug_Type tabel_type_arv;
	TM_Facility tabel_facility;
	TM_Parent_Status tabel_parent_status;
	TM_Child tabel_anak;

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
		Intent intent = getIntent();
		String id_child = intent.getStringExtra("id_child");
		TM_Child_Model model_anak = new TM_Child_Model();
		model_anak = tabel_anak.getChildIdentityById(id_child);

		// splitting id_facility

		if (model_anak.getFacility_id().equalsIgnoreCase(" ")|| model_anak.getFacility_id() == null) {
			facility_name = "-";
			Log.e("Array list", "kosong");
		} else {
			String[] items = model_anak.getFacility_id().trim().split(",");
			for (String item : items) {
				facility_name += tabel_facility.getNameFacility(item.trim());
				facility_name += "\n";
				Toast.makeText(getApplicationContext(), "" + item,
						Toast.LENGTH_LONG).show();
			}
		}

		txt_Nama_Anak.setText(model_anak.getChild_name());
		txt_BOD_Anak.setText(model_anak.getChild_bod());
		txt_Umur_Anak.setText(getChildAge(model_anak.getChild_bod()));
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

		return "" + 0;
		// try {
		// Date date = (Date) new SimpleDateFormat("dd MMM yyyy",
		// Locale.).parse("6 Aug 2012");
		// } catch (ParseException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		//
		// SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		// java.util.Date date_bod;
		// String umur ="";
		// try {
		// date_bod = formatter.parse(bod);
		// Log.i("date bod", ""+date_bod);
		// Calendar now = Calendar.getInstance();
		// Calendar tanggallahir = Calendar.getInstance();
		// tanggallahir.setTime(date_bod);
		//
		// int years = now.get(Calendar.YEAR) - tanggallahir.get(Calendar.YEAR);
		// Log.i("tahun", ""+years);
		//
		// int months = now.get(Calendar.MONTH) -
		// tanggallahir.get(Calendar.MONTH);
		// int days = now.get(Calendar.DAY_OF_MONTH)
		// - tanggallahir.get(Calendar.DAY_OF_MONTH);
		// if (days < 0) {
		// months--;
		// days += now.getActualMaximum(Calendar.DAY_OF_MONTH);
		// }
		// if (months < 0) {
		// years--;
		// months += 12;
		// }
		// umur = years + " tahun " + months + " bulan " + days + " hari";
		//
		// } catch (ParseException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// umur = ""+0;
		// }
		// return umur;
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
			startActivity(intent2);
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
