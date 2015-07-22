package com.example.laptrackingapps;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import com.example.databaselap.TM_Caregiver;
import com.example.databaselap.TM_Child;
import com.example.databaselap.TM_Child_Facility;
import com.example.databaselap.TM_Class;
import com.example.databaselap.TM_Cost_Facility;
import com.example.databaselap.TM_District;
import com.example.databaselap.TM_Drug_Dose;
import com.example.databaselap.TM_Drug_Status;
import com.example.databaselap.TM_Drug_Type;
import com.example.databaselap.TM_Facility;
import com.example.databaselap.TM_Parent_Status;
import com.example.databaselap.TM_Subdistrict;
import com.example.modellap.TM_Child_Facility_Model;
import com.example.modellap.TM_Child_Model;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ProfilAnakActivity extends Activity implements
		OnItemSelectedListener {

	final Context context = this;
	ImageView back, tambah_foto;
	LinearLayout simpan_profil;
	EditText txtName, txtFatherName, txtMotherName, txtCareGiverName,
			txtAddress, txtTeleponNumberCG, txtSchool;
	TextView tvtambah_jenis_arv;
	Button susu, vitamin, popok;
	TextView tvSusu, tvVitamin, tvPopok, tvarv1, tvarv2, tvarv3, tvarv4,
			tvarv5;

	// spinner
	Spinner spin;
	int position;

	// untuk kamera
	private static final int Image_take = 1;
	private static final int Image_pick = 2;

	// untuk tanggal lahir
	int hour, minute, mYear, mMonth, mDay;
	static final int TIME_DIALOG_ID = 0;
	static final int DATE_DIALOG_ID = 1;
	private EditText txtDate;
	private String[] arrMonth = { "Januari", "Februari", "Maret", "April",
			"Mei", "Juni", "Juli", "Agustus", "September", "Oktober",
			"November", "Desember" };

	// spinner
	String item_golongandarah[] = { "-", "A", "B", "O", "AB" };
	// this still static
	String item_jenis_obat[];
	String item_dosis[];
	String item_status_obat[];

	String item_kelas[];
	String item_caregiver[];
	String item_kotamadya[];
	String item_kecamatan[];
	Spinner golongan_darah, jenis_obat, dosis, status_obat, care_giver,
			kotamadya, kecamatan, kotamadya_sekolah, kecamatan_sekolah, kelas;
	Spinner spinner_kotamadya, spinner_kecamatan, spinner_kotamadyasekolah,
			spinner_kecamatansekolah;

	View popupView;

	// fetch data kelas
	String[] datakelas;

	// radio button
	RadioGroup radio_gender_group, radio_status_ayah_group, radio_status_ayah,
			radio_status_ibu_group, radio_status_ibu;

	String gender = "", status_ayah = "", status_ibu = "", status_temp = "";
	Boolean status_parent_temp, status_parent_temp1;

	// String
	String picturePath, Path, PathNull;

	// phone number caregiver
	EditText txtPhone1, txtPhone2;

	// arraylist kotamadya dan kecamatan
	ArrayList<String> arr_kecamatan = new ArrayList<String>();
	ArrayList<String> arr_kotamadya = new ArrayList<String>();
	ArrayList<String> data_tipeARV = new ArrayList<String>();
	ArrayList<String> data_kotamadya;

	// table arv dose,arv status, arv type, arv facility, caregiver,
	// parent_status , tabel class
	TM_Caregiver tabel_caregiver;
	TM_Class tabel_kelas;
	TM_Drug_Dose tabel_dosis;
	TM_Drug_Status tabel_status_arv;
	TM_Drug_Type tabel_type_arv;
	TM_Facility tabel_facility;
	TM_Parent_Status tabel_parent_status;
	TM_Child tabel_anak;
	TM_District tabel_kotamadya;
	TM_District tabel_district;
	TM_Subdistrict tabel_subdistrict;
	TM_Child_Facility tabel_child_facility;
	TM_Cost_Facility tabel_cost_facility;
	// simpan fasilitas

	ArrayList<Integer> selList = new ArrayList();
	String msg = "";
	// this is concated facilities id
	String facility_id = " ";
	Boolean status_susu = false, status_vitamin = false, status_popok = false;
	String fasilitas_susu_id = " ", fasilitas_vitamin_id = " ",
			fasilitas_popok_id = " ";
	ArrayList<String> fasilitas_susu = new ArrayList<String>();
	ArrayList<String> fasilitas_vitamin = new ArrayList<String>();
	ArrayList<String> fasilitas_popok = new ArrayList<String>();
	// checkbox
	CheckBox Checkbox_Susu;
	CheckBox Checkbox_Vitamin;
	CheckBox Checkbox_Popok;
	// Button fasilitas
	Button btn_susu;
	Button btn_vitamin;
	Button btn_popok;
	ArrayAdapter<String> kecAdapter;
	ArrayAdapter<String> adapter_caregiver;
	int spinner_position;
	ArrayAdapter<String> adapter_golda;

	Spinner spinner_cg;
	Spinner spinner_golda;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_profil_anak);

		// textview
		// arv from spinner
		TextView arv1 = (TextView) findViewById(R.id.arv1);
		TextView arv2 = (TextView) findViewById(R.id.arv2);
		TextView arv3 = (TextView) findViewById(R.id.arv3);
		TextView arv4 = (TextView) findViewById(R.id.arv4);
		TextView arv5 = (TextView) findViewById(R.id.arv5);

		// linear layout from spinner
		LinearLayout linArv1 = (LinearLayout) findViewById(R.id.linArv1);
		LinearLayout linArv2 = (LinearLayout) findViewById(R.id.linArv2);
		LinearLayout linArv3 = (LinearLayout) findViewById(R.id.linArv3);
		LinearLayout linArv4 = (LinearLayout) findViewById(R.id.linArv4);
		LinearLayout linArv5 = (LinearLayout) findViewById(R.id.linArv5);

		// textview
		tvSusu = (TextView) findViewById(R.id.textview_susu);
		tvVitamin = (TextView) findViewById(R.id.textViewVitamin);
		tvPopok = (TextView) findViewById(R.id.textViewPopok);
		tvtambah_jenis_arv = (TextView) findViewById(R.id.tvtambah_jenis_arv);

		// editText
		txtName = (EditText) findViewById(R.id.edit_nama_anak);
		txtDate = (EditText) findViewById(R.id.editTextTtl);
		txtFatherName = (EditText) findViewById(R.id.edit_namaayah);
		txtMotherName = (EditText) findViewById(R.id.edit_namaibu);
		txtCareGiverName = (EditText) findViewById(R.id.edit_caregiver_name);
		txtAddress = (EditText) findViewById(R.id.edit_alamat);
		txtTeleponNumberCG = (EditText) findViewById(R.id.edit_telepon);
		txtSchool = (EditText) findViewById(R.id.edit_sekolah);

		//
		txtPhone1 = (EditText) findViewById(R.id.edit_telepon1);
		txtPhone2 = (EditText) findViewById(R.id.edit_telepon2);

		back = (ImageView) findViewById(R.id.btn_back);
		simpan_profil = (LinearLayout) findViewById(R.id.button_simpanprofil);
		golongan_darah = (Spinner) findViewById(R.id.spinner_golongandarah);
		jenis_obat = (Spinner) findViewById(R.id.spinner_jenisobat);
		dosis = (Spinner) findViewById(R.id.spinner_dosisobat);
		status_obat = (Spinner) findViewById(R.id.spinner_statusobat);
		care_giver = (Spinner) findViewById(R.id.spinner_caregiver);
		kelas = (Spinner) findViewById(R.id.spinner_kelas);

		tambah_foto = (ImageView) findViewById(R.id.tambahfoto);
		susu = (Button) findViewById(R.id.button_susu);
		vitamin = (Button) findViewById(R.id.button_vitamin);
		popok = (Button) findViewById(R.id.button_popok);

		// checkbox
		Checkbox_Susu = (CheckBox) findViewById(R.id.checkBoxSusu);
		Checkbox_Vitamin = (CheckBox) findViewById(R.id.checkBoxVitamin);
		Checkbox_Popok = (CheckBox) findViewById(R.id.checkBoxPopok);

		// Button
		btn_susu = (Button) findViewById(R.id.button_susu);
		btn_vitamin = (Button) findViewById(R.id.button_vitamin);
		btn_popok = (Button) findViewById(R.id.button_popok);

		// insertt to local database
		InsertDataToLocalDatabase();
		SelectFasilitas();
		// spinner
		// fetch golongan darah
		spinner_golda = (Spinner) findViewById(R.id.spinner_golongandarah);
		spinner_golda.setOnItemSelectedListener(this);
		adapter_golda = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, item_golongandarah);
		adapter_golda
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_golda.setAdapter(adapter_golda);

		// jenis obat
		ArrayList<String> data_tipeARV = tabel_type_arv.getAllData();
		item_jenis_obat = data_tipeARV.toArray(new String[data_tipeARV.size()]);

		Spinner spinner_jenis_obat = (Spinner) findViewById(R.id.spinner_jenisobat);
		spinner_jenis_obat.setOnItemSelectedListener(this);
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, item_jenis_obat);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_jenis_obat.setAdapter(adapter2);
		spinner_jenis_obat.setPrompt("select");
		// dosis
		ArrayList<String> data_dosisARV = tabel_dosis.getAllData();
		item_dosis = data_dosisARV.toArray(new String[data_dosisARV.size()]);

		Spinner spinner_dosis = (Spinner) findViewById(R.id.spinner_dosisobat);
		spinner_dosis.setOnItemSelectedListener(this);
		ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, item_dosis);
		adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_dosis.setAdapter(adapter3);

		// status obat
		ArrayList<String> data_statusARV = tabel_status_arv.getAllData();
		item_status_obat = data_statusARV.toArray(new String[data_statusARV
				.size()]);
		Spinner spinner_status_obat = (Spinner) findViewById(R.id.spinner_statusobat);
		spinner_status_obat.setOnItemSelectedListener(this);
		ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, item_status_obat);
		adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_status_obat.setAdapter(adapter4);

		// kelas
		ArrayList<String> data_kelas = tabel_kelas.getDataKelas();
		item_kelas = data_kelas.toArray(new String[data_kelas.size()]);
		Spinner spinner_kelas = (Spinner) findViewById(R.id.spinner_kelas);
		spinner_kelas.setOnItemSelectedListener(this);
		ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, item_kelas);
		adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_kelas.setAdapter(adapter5);

		// caregiver
		ArrayList<String> data_caregiver = tabel_caregiver.getAllData();
		item_caregiver = data_caregiver.toArray(new String[data_caregiver
				.size()]);
		spinner_cg = (Spinner) findViewById(R.id.spinner_caregiver);
		spinner_cg.setOnItemSelectedListener(this);
		adapter_caregiver = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, item_caregiver);
		adapter_caregiver
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_cg.setAdapter(adapter_caregiver);

		// wilayah kotamadya dan kecamatan
		data_kotamadya = tabel_kotamadya.getAllData();
		item_kotamadya = data_kotamadya.toArray(new String[data_kotamadya
				.size()]);
		spinner_kotamadya = (Spinner) findViewById(R.id.spinner_kotamadya);
		spinner_kotamadya.setOnItemSelectedListener(this);
		// for school
		spinner_kotamadyasekolah = (Spinner) findViewById(R.id.spinner_kotamadyasekolah);
		spinner_kotamadyasekolah.setOnItemSelectedListener(this);
		spinner_kecamatan = (Spinner) findViewById(R.id.spinner_kecamatan);
		spinner_kecamatansekolah = (Spinner) findViewById(R.id.spinner_kecamatansekolah);

		//
		ArrayAdapter<String> kotamadyaAdapter = new ArrayAdapter<String>(this,
				R.layout.spin, item_kotamadya);
		kotamadyaAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_kotamadya.setAdapter(kotamadyaAdapter);
		spinner_kotamadyasekolah.setAdapter(kotamadyaAdapter);

		Kotamadya(spinner_kotamadya, 1);
		Kotamadya(spinner_kotamadyasekolah, 2);

		// status ayah
		radio_status_ayah_group = (RadioGroup) findViewById(R.id.radioStatusayahgroup);
		radio_status_ayah = (RadioGroup) findViewById(R.id.radioStatusayah);
		radio_status_ayah_group.clearCheck();
		radio_status_ayah.clearCheck();
		// status ibu
		radio_status_ibu_group = (RadioGroup) findViewById(R.id.radioStatusibugroup);
		radio_status_ibu = (RadioGroup) findViewById(R.id.radioStatusibu);
		radio_status_ibu_group.clearCheck();
		radio_status_ibu.clearCheck();

		// getting value of radio button
		// this is for gender
		radio_gender_group = (RadioGroup) findViewById(R.id.radioGenderGroup);
		radio_gender_group.clearCheck();
		radio_gender_group
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						RadioButton rb_gender = (RadioButton) group
								.findViewById(checkedId);
						if (null != rb_gender && checkedId > -1) {
							gender = rb_gender.getText().toString();
							// Toast.makeText(getApplicationContext(),
							// "" + rb_gender.getText(),
							// Toast.LENGTH_SHORT).show();
						}
					}
				});

		// // this is getting id status of
		status_ayah = findStatusParent(radio_status_ayah_group,
				radio_status_ayah);
		status_ibu = findStatusParent(radio_status_ibu_group, radio_status_ibu);

		// pop up tambah obat
		tvtambah_jenis_arv.setOnClickListener(new View.OnClickListener() {
			// button tambah foto

			@Override
			public void onClick(View v) {

				ArrayList<String> data_tipeARV = tabel_type_arv.getAllData();
				item_jenis_obat = data_tipeARV.toArray(new String[data_tipeARV
						.size()]);

				Toast.makeText(getApplicationContext(), "yuhu",
						Toast.LENGTH_LONG).show();
				pilihArv(item_jenis_obat, "Pilih ARV");
			}
		});

		// menyimpan data anak
		simpan_profil.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				GetCaregiverPhone();
				tabel_anak = new TM_Child(getApplicationContext());
				// check whether it is create or update -> krn me-refer ke
				// activity yang sama
				String path_gambar = "";
				Intent i = getIntent();
				PathNull = "kosong";
				if (picturePath != null) {
					path_gambar = picturePath;
				}

				else if (Path != null) {
					path_gambar = Path;
				} else if (PathNull != null) {

					path_gambar = PathNull;
				}
				if (i.getStringExtra("id_anak") == null) {

					tabel_anak.addRow(
							txtName.getText().toString(),
							txtDate.getText().toString(),
							gender,
							golongan_darah.getSelectedItem().toString(),
							txtFatherName.getText().toString(),
							txtMotherName.getText().toString(),
							txtCareGiverName.getText().toString(),
							txtAddress.getText().toString(),
							GetCaregiverPhone(),
							txtSchool.getText().toString(),
							path_gambar,
							tabel_status_arv.getIdStatusARV(status_obat
									.getSelectedItem().toString()),
							tabel_type_arv.getIdTypeARV(jenis_obat
									.getSelectedItem().toString()),
							tabel_dosis.getIdDosisARV(dosis.getSelectedItem()
									.toString()),
							tabel_kelas.getIdKelas(kelas.getSelectedItem()
									.toString()),
							tabel_caregiver.getIdCaregiver(care_giver
									.getSelectedItem().toString()),
							"-",
							findStatusParent(radio_status_ayah_group,
									radio_status_ayah),
							findStatusParent(radio_status_ibu_group,
									radio_status_ibu), tabel_subdistrict
									.getIdSubDistrict(spinner_kecamatan
											.getSelectedItem().toString()),
							tabel_subdistrict
									.getIdSubDistrict(spinner_kecamatansekolah
											.getSelectedItem().toString()));

					// insert semua fasilitas anak
					InsertFasilitasAnak(tabel_anak.getLastInsertedChild());

					// insert arv

					Log.e("gender ", "" + gender);
				} else {
					tabel_anak.updateChildIdentityById(
							i.getStringExtra("id_anak"),
							txtName.getText().toString(),
							txtDate.getText().toString(),
							gender,
							golongan_darah.getSelectedItem().toString(),
							txtFatherName.getText().toString(),
							txtMotherName.getText().toString(),
							txtCareGiverName.getText().toString(),
							txtAddress.getText().toString(),
							GetCaregiverPhone(),
							txtSchool.getText().toString(),
							findStatusParent(radio_status_ayah_group,
									radio_status_ayah),
							findStatusParent(radio_status_ibu_group,
									radio_status_ibu), tabel_caregiver
									.getIdCaregiver(care_giver
											.getSelectedItem().toString()),
							tabel_subdistrict
									.getIdSubDistrict(spinner_kecamatan
											.getSelectedItem().toString()),
							tabel_subdistrict
									.getIdSubDistrict(spinner_kecamatansekolah
											.getSelectedItem().toString()),
							tabel_kelas.getIdKelas(kelas.getSelectedItem()
									.toString()), path_gambar);
					
					//update tabel fasilitas anak
					DeleteFasilitasAnak(i.getStringExtra("id_anak"));
					InsertFasilitasAnak(i.getStringExtra("id_anak"));

				}
				//
				Toast.makeText(
						getApplicationContext(),
						"status ayah "
								+ findStatusParent(radio_status_ayah_group,
										radio_status_ayah)
								+ " status ibu "
								+ findStatusParent(radio_status_ibu_group,
										radio_status_ibu), Toast.LENGTH_LONG)
						.show();

				// go to test activity
				Intent test = new Intent(getApplication(),
						ListAnakActivity.class);
				startActivity(test);

			}
		});

		tambah_foto.setOnClickListener(new View.OnClickListener() {
			// button tambah foto
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LayoutInflater layoutinflater = LayoutInflater.from(context);
				View promptView = layoutinflater.inflate(
						R.layout.activity_foto_dialog, null);

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						context);
				alertDialogBuilder.setView(promptView);

				final Button btn_kamera = (Button) promptView
						.findViewById(R.id.button_kamera);
				final Button btn_galeri = (Button) promptView
						.findViewById(R.id.button_galeri);

				btn_kamera.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(
								MediaStore.ACTION_IMAGE_CAPTURE);

						File f = new File(android.os.Environment
								.getExternalStorageDirectory(), "temp.jpg");

						intent.putExtra(MediaStore.EXTRA_OUTPUT,
								Uri.fromFile(f));

						startActivityForResult(intent, 1);
					}
				});

				btn_galeri.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(
								Intent.ACTION_PICK,
								android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

						startActivityForResult(intent, 2);

					}
				});

				AlertDialog alertD = alertDialogBuilder.create();
				alertD.show();
			}
		});

		// mendapatkan tanggal sekarang
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);

		txtDate.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				showDialog(DATE_DIALOG_ID);
				return true;
			}
		});

		Calendar now = Calendar.getInstance();
		Calendar tanggallahir = Calendar.getInstance();

		tanggallahir.set(mYear, mMonth, mDay);

		int years = now.get(Calendar.YEAR) - tanggallahir.get(Calendar.YEAR);
		int months = now.get(Calendar.MONTH) - tanggallahir.get(Calendar.MONTH);
		int days = now.get(Calendar.DAY_OF_MONTH)
				- tanggallahir.get(Calendar.DAY_OF_MONTH);
		if (days < 0) {
			months--;
			days += now.getActualMaximum(Calendar.DAY_OF_MONTH);
		}
		if (months < 0) {
			years--;
			months += 12;
		}
		String umur = years + " tahun " + months + " bulan " + days + " hari";

		// check for update view
		// intent3.putExtra("id_anak",id_child);
		Intent i = getIntent();
		if (i != null && i.getStringExtra("id_anak") != null) {
			Log.d("***DEBUG****", "For update data anak");
			String id_child = i.getStringExtra("id_anak");
			TM_Child_Model model_anak = new TM_Child_Model();
			tabel_anak = new TM_Child(getApplicationContext());
			model_anak = tabel_anak.getChildIdentityById(id_child);
			// displaying foto
			// cek jika poto belum dimasukkan -> maka display default
			if (model_anak.getImage_path() == "kosong") {
				File image = new File(model_anak.getImage_path());
				BitmapFactory.Options bmOptions = new BitmapFactory.Options();
				Bitmap bitmap = BitmapFactory.decodeFile(
						image.getAbsolutePath(), bmOptions);
				bitmap = Bitmap.createScaledBitmap(bitmap, 120, 120, true);
				tambah_foto.setImageBitmap(bitmap);
			} else {
				Bitmap bitmap1 = BitmapFactory.decodeResource(
						context.getResources(), R.drawable.icon);
				tambah_foto.setImageBitmap(bitmap1);
			}

			txtName.setText(model_anak.getChild_name());
			txtDate.setText(model_anak.getChild_bod());
			txtFatherName.setText(model_anak.getFather_name());
			txtMotherName.setText(model_anak.getMother_name());
			txtCareGiverName.setText(model_anak.getCaregiver_name());
			txtAddress.setText(model_anak.getChild_address());
			txtTeleponNumberCG.setText(model_anak.getCaregiver_phone());
			txtSchool.setText(model_anak.getSchool_name());

			// checked any gender
			if (model_anak.getChild_gender().equals("PEREMPUAN")) {
				RadioButton pr = (RadioButton) findViewById(R.id.radioButtonPerempuan);
				pr.setChecked(true);

			} else if (model_anak.getChild_gender().equals("LAKI - LAKI")) {
				RadioButton lk = (RadioButton) findViewById(R.id.radioButtonLakilaki);
				lk.setChecked(true);
			} else {
			}

			// checked any parent status
			RadioButton life_fat = (RadioButton) findViewById(R.id.radioButtonHidup);
			RadioButton life_fat1 = (RadioButton) findViewById(R.id.radioButtonMeninggal);
			RadioButton hiv_fat = (RadioButton) findViewById(R.id.radioButtonAyahPositif);
			RadioButton hiv_fat1 = (RadioButton) findViewById(R.id.radioButtonAyahNegatif);

			RadioButton life_mot = (RadioButton) findViewById(R.id.radioButtonIbuHidup);
			RadioButton life_mot1 = (RadioButton) findViewById(R.id.radioButtonIbuMeninggal);
			RadioButton hiv_mot = (RadioButton) findViewById(R.id.radioButtonIbuPositif);
			RadioButton hiv_mot1 = (RadioButton) findViewById(R.id.radioButtonIbuNegatif);

			if (model_anak.getDad_status_id().equals("1")) {
				life_fat.setChecked(true);
				hiv_fat.setChecked(true);
			} else if (model_anak.getDad_status_id().equals("2")) {
				life_fat.setChecked(true);
				hiv_fat1.setChecked(true);
			} else if (model_anak.getDad_status_id().equals("3")) {
				life_fat1.setChecked(true);
				hiv_fat.setChecked(true);
			} else if (model_anak.getDad_status_id().equals("4")) {
				life_fat1.setChecked(true);
				hiv_fat1.setChecked(true);
			} else {
			}

			if (model_anak.getMom_status_id().equals("1")) {
				life_mot.setChecked(true);
				hiv_mot.setChecked(true);
			} else if (model_anak.getMom_status_id().equals("2")) {
				life_mot.setChecked(true);
				hiv_mot1.setChecked(true);
			} else if (model_anak.getMom_status_id().equals("3")) {
				life_mot1.setChecked(true);
				hiv_mot.setChecked(true);
			} else if (model_anak.getMom_status_id().equals("4")) {
				life_mot1.setChecked(true);
				hiv_mot1.setChecked(true);
			} else {
			}

			SetCaregiverPhone(model_anak.getCaregiver_phone());

			// set display name of spinner golda
			spinner_position = adapter_golda.getPosition(model_anak
					.getBlood_type());
			spinner_golda.setSelection(spinner_position);

			// caregiver spinner
			spinner_position = adapter_caregiver.getPosition(tabel_caregiver
					.getNameCaregiver(model_anak.getCaregiver_id()));
			spinner_cg.setSelection(spinner_position);

			// spinner kotamadya dan kecamatan

			arr_kecamatan = tabel_subdistrict.getAllDistrict();
			kecAdapter = new ArrayAdapter<String>(getApplicationContext(),
					R.layout.spin, arr_kecamatan);
			spinner_position = kecAdapter.getPosition(tabel_subdistrict
					.getNameSubDistrict(model_anak.getSubdistrict_id()));
			spinner_kecamatan.setAdapter(kecAdapter);
			spinner_kecamatan.setSelection(spinner_position);

			Toast.makeText(
					getApplicationContext(),
					""
							+ tabel_subdistrict.getNameSubDistrict(model_anak
									.getSubdistrict_id()), Toast.LENGTH_SHORT)
					.show();
			// spinner_kecamatan.setSelection(spinner_position);

			// fetch the id of district id and then populated in names
			// spinner_position =
			// kotamadyaAdapter.getPosition(tabel_district.getNameDistrict(tabel_subdistrict.getIDDistrict(model_anak.getSubdistrict_id())));
			// spinner_kotamadya.setSelection(spinner_position);
			//
			// spinner_position =
			// kotamadyaAdapter.getPosition(tabel_district.getNameDistrict(tabel_subdistrict.getIDDistrict(model_anak.getSchool_subdistrict_id())));
			// spinner_kotamadyasekolah.setSelection(spinner_position);
			
			//displaying fasilitas anak
			RetrieveFasilitasAnak(model_anak.getChild_id());
		} else {
			Log.d("***DEBUG****", "Intent was null");

		}

	}

	public String GetCaregiverPhone() {
		String result = "";
		if (!txtTeleponNumberCG.getText().toString().isEmpty()) {
			result += txtTeleponNumberCG.getText().toString() + ";";
		}
		if (!txtPhone1.getText().toString().isEmpty()) {
			result += txtPhone1.getText().toString() + ";";
		}
		if (!txtPhone2.getText().toString().isEmpty()) {
			result += txtPhone2.getText().toString() + ";";
		}
		Log.e("Phone number caregiver ", "" + result);
		return result;
	}

	// displaying caregiver Phone
	public void SetCaregiverPhone(String concatedPhoneNumber) {
		if (!concatedPhoneNumber.isEmpty() && !concatedPhoneNumber.equals("")) {
			String[] phones = concatedPhoneNumber.split(";");

			if (phones.length == 1) {
				txtTeleponNumberCG.setText(phones[0]);
			} else if (phones.length == 2) {
				txtTeleponNumberCG.setText(phones[0]);
				txtPhone1.setVisibility(View.VISIBLE);
				txtPhone1.setText(phones[1]);
			} else if (phones.length == 3) {
				txtTeleponNumberCG.setText(phones[0]);
				txtPhone1.setVisibility(View.VISIBLE);
				txtPhone1.setText(phones[1]);
				txtPhone2.setVisibility(View.VISIBLE);
				txtPhone2.setText(phones[2]);
			}
		}
	}
	
	public void DeleteFasilitasAnak(String id_anak)
	{
		tabel_child_facility.DeleteFasilitasAnak(id_anak);
		Log.e("Delete obsolete fasiltas anak", id_anak);
	}
	
	public void InsertFasilitasAnak(String id_anak) {
		// cek apakah mk memilih susu
		Log.e("insert fasilitas anak", "" + true);
		if (!fasilitas_susu.isEmpty()) {
			for (String a : fasilitas_susu) {
				tabel_child_facility.InsertFasilitasAnak(id_anak, "1",tabel_cost_facility.getIdCostFacility(a));
				Log.e("insert ke tabel cost facility -> susu", a);
			}
		}
		if (!fasilitas_vitamin.isEmpty()) {
			for (String b : fasilitas_vitamin) {
				tabel_child_facility.InsertFasilitasAnak(id_anak, "2",
						tabel_cost_facility.getIdCostFacility(b));
				Log.e("insert ke tabel cost facility -> vitamin", b);
			}
		}

		if (!fasilitas_popok.isEmpty()) {
			for (String b : fasilitas_popok) {
				tabel_child_facility.InsertFasilitasAnak(id_anak, "3",
						tabel_cost_facility.getIdCostFacility(b));
				Log.e("insert ke tabel cost facility -> popok", b);
			}
		}

	}

	public void RetrieveFasilitasAnak(String id_anak) {
		// retrieve fasilitas id
		// jika tidak null
		// count fasilitas id
		// looping per fasilitas
		// checked the fasilitas id
		// retrieve ke semua fasilitas cost id yang punya fasilitas child yang
		// fasilitas id sekian dengan anak sekian
		// jika tidak null
		// set text
		
		ArrayList<String> fasilitas_susu = new ArrayList<String>();
		ArrayList<String> fasilitas_vitamin = new ArrayList<String>();
		ArrayList<String> fasilitas_popok = new ArrayList<String>();
		ArrayList<TM_Child_Facility_Model> all_facility_id = tabel_child_facility.getSemuaFasilitasAnak(id_anak);
		if (all_facility_id != null) {
			for (TM_Child_Facility_Model model : all_facility_id) {
				if (model.getFacility_id().equals("1")) {
					Checkbox_Susu.setChecked(true);SelectFasilitas();
					fasilitas_susu.add(tabel_cost_facility.getNameCostFacility(model.getFacility_cost_id()));
					continue;
				}
				if (model.getFacility_id().equals("2")) {
					Checkbox_Vitamin.setChecked(true);SelectFasilitas();
					fasilitas_vitamin.add(tabel_cost_facility.getNameCostFacility(model.getFacility_cost_id()));
					continue;
				}
				if (model.getFacility_id().equals("3")) {
					Checkbox_Popok.setChecked(true);SelectFasilitas();
					fasilitas_popok.add(tabel_cost_facility.getNameCostFacility(model.getFacility_cost_id()));
					continue;
				}
			}
		}
		
		//displaying it
		if(!fasilitas_susu.isEmpty())
		{
			msg="";
			for(String a : fasilitas_susu)
			{
				msg +=a +"\n";
			}
			tvSusu.setText(msg);
		}
		if(!fasilitas_vitamin.isEmpty())
		{
			msg="";
			for(String a : fasilitas_vitamin)
			{
				msg +=a +"\n";
			}
			tvVitamin.setText(msg);
		}
		if(!fasilitas_popok.isEmpty())
		{
			msg="";
			for(String a : fasilitas_popok)
			{
				msg +=a +"\n";
			}
			tvPopok.setText(msg);
		}
		Log.e("retrieve fasilitas anak", "true");

	}

	// this method is to get facility the caregiver selected into a child
	public void SelectFasilitas() {
		Checkbox_Susu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (Checkbox_Susu.isChecked()) {
					btn_susu.setVisibility(View.VISIBLE);
				} else {
					btn_susu.setVisibility(View.INVISIBLE);
					fasilitas_susu.clear();
					tvSusu.setText("");
				}
			}
		});

		Checkbox_Vitamin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (Checkbox_Vitamin.isChecked()) {
					btn_vitamin.setVisibility(View.VISIBLE);
				} else {
					btn_vitamin.setVisibility(View.INVISIBLE);
					fasilitas_vitamin.clear();
					tvVitamin.setText("");
				}
			}
		});

		Checkbox_Popok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (Checkbox_Popok.isChecked()) {
					btn_popok.setVisibility(View.VISIBLE);
				} else {
					btn_popok.setVisibility(View.INVISIBLE);
					fasilitas_popok.clear();
					tvPopok.setText("");
				}
			}
		});
	}

	// getting id status parent child
	public String findStatusParent(RadioGroup status_life, RadioGroup status_hiv) {
		status_life.clearCheck();
		status_life
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						RadioButton rb = (RadioButton) group
								.findViewById(checkedId);
						if (null != rb && checkedId > -1) {
							if (rb.getText().toString().equals("HIDUP")) {
								status_parent_temp = true;
								Log.e("temp", "" + status_parent_temp);
							} else if (rb.getText().toString()
									.equals("MENINGGAL")) {
								status_parent_temp = false;
								Log.e("temp", "" + status_parent_temp);
							} else {
							}

						}
					}

				});
		status_hiv.clearCheck();
		status_hiv
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						RadioButton rb = (RadioButton) group
								.findViewById(checkedId);
						if (null != rb && checkedId > -1) {
							if (rb.getText().toString().equals("POSITIF")) {
								status_parent_temp1 = true;
								Log.e("temp1", "" + status_parent_temp1);
							} else if (rb.getText().toString()
									.equals("NEGATIF")) {
								status_parent_temp1 = false;
								Log.e("temp1", "" + status_parent_temp1);
							} else {
							}
						}
					}
				});
		// getIdParentStatus(status_parent_temp,status_parent_temp1);
		// find id of parent status from table
		if (status_parent_temp != null && status_parent_temp1 != null) {
			status_temp = getIdParentStatus(status_parent_temp,
					status_parent_temp1);
		}
		return status_temp;
	}

	public String getIdParentStatus(Boolean one, Boolean two) {
		String temp = "";
		if (one == true && two == true) {
			temp = "" + 1;
		} else if (one == true && two == false) {
			temp = "" + 2;
		} else if (one == false && two == true) {
			temp = "" + 3;
		} else if (one == false && two == false) {
			temp = "" + 4;
		} else {
		}
		return temp;
	}

	// drop downlist for kotamadya (child address and school address)
	// flag => to check kotamadya is child address or school address
	// 1 = child address 2 => school address
	public void Kotamadya(Spinner wilayah, final int flag) {
		wilayah.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View view,
					int position, long id) {
				String sKotaMadya = tabel_kotamadya
						.getIdDistrict(item_kotamadya[position]);
				if (position == 0) {

				} else {

				}
				// // check for the spinner of childs address or school adress
				if (flag == 1) {
					Kecamatan(sKotaMadya, 1);
				} else {
					Kecamatan(sKotaMadya, 2);
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
	}

	// drop downlist for kecamatan (child address and school address)
	public void Kecamatan(String id_kotamadya, int flag) {
		// flag => to check kecamatan is child address or school address
		// 1 = child address
		arr_kecamatan = tabel_subdistrict.getAllNameDistrict(id_kotamadya);

		kecAdapter = new ArrayAdapter<String>(getApplicationContext(),
				R.layout.spin, arr_kecamatan);

		if (flag == 1) {

			spinner_kecamatan.setOnItemSelectedListener(this);
			spinner_kecamatan.setAdapter(kecAdapter);
			Log.e("kecamatan", "" + spinner_kecamatan.getSelectedItem());
			Toast.makeText(getApplicationContext(),
					"" + spinner_kecamatan.getSelectedItem(),
					Toast.LENGTH_SHORT).show();
		} else {

			spinner_kecamatansekolah.setOnItemSelectedListener(this);
			spinner_kecamatansekolah.setAdapter(kecAdapter);
			Toast.makeText(getApplicationContext(),
					"" + spinner_kecamatansekolah.getSelectedItem(),
					Toast.LENGTH_SHORT).show();
			Log.e("kecamatan", "" + spinner_kecamatansekolah.getSelectedItem());
		}

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {

			if (requestCode == 1) {
				File f = new File(Environment.getExternalStorageDirectory().toString());

				for (File temp : f.listFiles()) {

					if (temp.getName().equals("temp.jpg")) {

						f = temp;

						break;

					}

				}

				try {

					Bitmap bitmap;

					BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
					bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
					final String[] imageColumns = {
							MediaStore.Images.Media._ID,
							MediaStore.Images.Media.DATA };
					final String imageOrderBy = MediaStore.Images.Media._ID
							+ " DESC";
					Cursor imageCursor = managedQuery(
							MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
							imageColumns, null, null, imageOrderBy);
					if (imageCursor.moveToFirst()) {
						int id = imageCursor.getInt(imageCursor
								.getColumnIndex(MediaStore.Images.Media._ID));
						Path = imageCursor.getString(imageCursor
								.getColumnIndex(MediaStore.Images.Media.DATA));
						imageCursor.close();
					}

					bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),

					bitmapOptions);

					tambah_foto.setImageBitmap(bitmap);
					// /storage/sdcard0/Pictures/BBM/IMG_20150623_144841
					// Path = android.os.Environment
					//
					// .getExternalStorageDirectory()
					//
					// + File.separator
					//
					// + "DCIM" + File.separator + "Camera";
					//
					// Log.w("ini path", Path);
					f.delete();

					OutputStream outFile = null;

					File file = new File(Path);

					try {

						outFile = new FileOutputStream(file);

						bitmap.compress(Bitmap.CompressFormat.JPEG, 100,
								outFile);

						outFile.flush();

						outFile.close();

					} catch (FileNotFoundException e) {

						e.printStackTrace();

					} catch (IOException e) {

						e.printStackTrace();

					} catch (Exception e) {

						e.printStackTrace();

					}

				} catch (Exception e) {

					e.printStackTrace();

				}

			} else if (requestCode == 2) {

				Uri selectedImage = data.getData();

				String[] filePath = { MediaStore.Images.Media.DATA };

				Cursor c = getContentResolver().query(selectedImage, filePath,
						null, null, null);

				c.moveToFirst();

				int columnIndex = c.getColumnIndex(filePath[0]);

				picturePath = c.getString(columnIndex);

				c.close();

				Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));

				Log.w("path of image from gallery......******************.........",
						picturePath + "");

				tambah_foto.setImageBitmap(thumbnail);

			}

		}

	}

	int banyak;
	ArrayList<String> selectedItems = new ArrayList<String>();

	private void pilihArv(final String[] arv, String Title) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(Title);
		builder.setMultiChoiceItems(arv, null,
				new DialogInterface.OnMultiChoiceClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1,
							boolean arg2) {
						// TODO Auto-generated method stub

						if (arg2) {
							selList.add(arg1);

							banyak = selList.size();
							// Toast.makeText(getApplicationContext(),
							// "" + banyak + arg1,
							// Toast.LENGTH_SHORT).show();
							selectedItems.add(arv[arg1]);

						} else if (selList.contains(arg1)) {
							// if the item is already selected then remove it
							selList.remove(Integer.valueOf(arg1));
						}

						if (banyak >= 6) {
							Toast.makeText(
									context,
									"Anda Tidak Dapat Memilih Obat Lebih Dari 5 ",
									Toast.LENGTH_LONG).show();
							((AlertDialog) arg0).getListView().setItemChecked(
									arg1, false);
							banyak--;
							// ((AlertDialog)arg0).getButton(DialogInterface.BUTTON_POSITIVE).setVisibility(View.INVISIBLE);
						}
					}
				});

		builder.setNegativeButton("CANCEL",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

		builder.setPositiveButton("SIMPAN",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						msg = "";

						for (int i = 0; i < selList.size(); i++) {
							msg = msg + arv[selList.get(i)] + "\n";

						}
						String msg_list[] = msg.split("\n");
						int lngth = msg_list.length;

						for (int j = 0; j < lngth; j++) {
							// System.out.println("Split Output: "+
							// msg_list[j]);
						}

						ArrayList<String> data_dosis_pagi = tabel_dosis
								.getAllData();
						item_dosis = data_dosis_pagi
								.toArray(new String[data_dosis_pagi.size()]);

						LayoutInflater layoutInflater = (LayoutInflater) getBaseContext()
								.getSystemService(LAYOUT_INFLATER_SERVICE);
						popupView = layoutInflater
								.inflate(R.layout.popup, null);
						final PopupWindow popupWindow = new PopupWindow(
								popupView, LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT);

						Button btnDismiss = (Button) popupView
								.findViewById(R.id.dismiss);
						TextView tv0 = (TextView) popupView
								.findViewById(R.id.tv1);
						TextView tv1 = (TextView) popupView
								.findViewById(R.id.tv2);
						TextView tv2 = (TextView) popupView
								.findViewById(R.id.tv3);
						TextView tv3 = (TextView) popupView
								.findViewById(R.id.tv4);
						TextView tv4 = (TextView) popupView
								.findViewById(R.id.tv5);

						LinearLayout linARV1 = (LinearLayout) popupView
								.findViewById(R.id.linARV1);
						LinearLayout linARV2 = (LinearLayout) popupView
								.findViewById(R.id.linARV2);
						LinearLayout linARV3 = (LinearLayout) popupView
								.findViewById(R.id.linARV3);
						LinearLayout linARV4 = (LinearLayout) popupView
								.findViewById(R.id.linARV4);
						LinearLayout linARV5 = (LinearLayout) popupView
								.findViewById(R.id.linARV5);

						if (banyak == 1) {
							linARV1.setVisibility(View.VISIBLE);
							tv0.setText(msg_list[0]);
						} else if (banyak == 2) {
							linARV1.setVisibility(View.VISIBLE);
							linARV2.setVisibility(View.VISIBLE);
							tv0.setText(msg_list[0]);
							tv1.setText(msg_list[1]);

						} else if (banyak == 3) {
							linARV1.setVisibility(View.VISIBLE);
							linARV2.setVisibility(View.VISIBLE);
							linARV3.setVisibility(View.VISIBLE);
							tv0.setText(msg_list[0]);
							tv1.setText(msg_list[1]);
							tv2.setText(msg_list[2]);

						} else if (banyak == 4) {
							linARV1.setVisibility(View.VISIBLE);
							linARV2.setVisibility(View.VISIBLE);
							linARV3.setVisibility(View.VISIBLE);
							linARV4.setVisibility(View.VISIBLE);
							tv0.setText(msg_list[0]);
							tv1.setText(msg_list[1]);
							tv2.setText(msg_list[2]);
							tv3.setText(msg_list[3]);

						} else if (banyak == 5) {
							linARV1.setVisibility(View.VISIBLE);
							linARV2.setVisibility(View.VISIBLE);
							linARV3.setVisibility(View.VISIBLE);
							linARV4.setVisibility(View.VISIBLE);
							linARV5.setVisibility(View.VISIBLE);
							tv0.setText(msg_list[0]);
							tv1.setText(msg_list[1]);
							tv2.setText(msg_list[2]);
							tv3.setText(msg_list[3]);
							tv4.setText(msg_list[4]);
						}

						// dosis pagi1

						final Spinner popupSpinnerDosisPagi1 = (Spinner) popupView
								.findViewById(R.id.spinDosisPagi1);
						setSpin(item_dosis, popupSpinnerDosisPagi1);
						// popupSpinnerDosisPagi1.setOnItemSelectedListener(this);

						// dosis malam1
						final Spinner popupSpinnerDosisMalam1 = (Spinner) popupView
								.findViewById(R.id.spinDosisMalam1);
						setSpin(item_dosis, popupSpinnerDosisMalam1);

						// dosis pagi2

						final Spinner popupSpinnerDosisPagi2 = (Spinner) popupView
								.findViewById(R.id.spinDosisPagi2);
						setSpin(item_dosis, popupSpinnerDosisPagi2);

						// dosis malam2
						final Spinner popupSpinnerDosisMalam2 = (Spinner) popupView
								.findViewById(R.id.spinDosisMalam2);
						setSpin(item_dosis, popupSpinnerDosisMalam2);

						// dosis pagi3

						final Spinner popupSpinnerDosisPagi3 = (Spinner) popupView
								.findViewById(R.id.spinDosisPagi3);
						setSpin(item_dosis, popupSpinnerDosisPagi3);

						// dosis malam3
						final Spinner popupSpinnerDosisMalam3 = (Spinner) popupView
								.findViewById(R.id.spinDosisMalam3);
						setSpin(item_dosis, popupSpinnerDosisMalam3);

						// dosis pagi4

						final Spinner popupSpinnerDosisPagi4 = (Spinner) popupView
								.findViewById(R.id.spinDosisPagi4);
						setSpin(item_dosis, popupSpinnerDosisPagi4);

						// dosis malam4
						final Spinner popupSpinnerDosisMalam4 = (Spinner) popupView
								.findViewById(R.id.spinDosisMalam4);
						setSpin(item_dosis, popupSpinnerDosisMalam4);

						// dosis pagi5

						final Spinner popupSpinnerDosisPagi5 = (Spinner) popupView
								.findViewById(R.id.spinDosisPagi5);
						setSpin(item_dosis, popupSpinnerDosisPagi5);

						// dosis malam5
						final Spinner popupSpinnerDosisMalam5 = (Spinner) popupView
								.findViewById(R.id.spinDosisMalam5);
						setSpin(item_dosis, popupSpinnerDosisMalam5);

						btnDismiss
								.setOnClickListener(new Button.OnClickListener() {

									@Override
									public void onClick(View v) {
										popupWindow.dismiss();

										String msg_list[] = msg.split("\n");

										// linear layout from spinner
										LinearLayout linArv1 = (LinearLayout) findViewById(R.id.linArv1);
										LinearLayout linArv2 = (LinearLayout) findViewById(R.id.linArv2);
										LinearLayout linArv3 = (LinearLayout) findViewById(R.id.linArv3);
										LinearLayout linArv4 = (LinearLayout) findViewById(R.id.linArv4);
										LinearLayout linArv5 = (LinearLayout) findViewById(R.id.linArv5);

										// arv
										TextView arv1 = (TextView) findViewById(R.id.arv1);
										TextView arv2 = (TextView) findViewById(R.id.arv2);
										TextView arv3 = (TextView) findViewById(R.id.arv3);
										TextView arv4 = (TextView) findViewById(R.id.arv4);
										TextView arv5 = (TextView) findViewById(R.id.arv5);

										// dosis_pagi from spinner
										final TextView dos_pagi1 = (TextView) findViewById(R.id.dos_pagi1);
										TextView dos_pagi2 = (TextView) findViewById(R.id.dos_pagi2);
										TextView dos_pagi3 = (TextView) findViewById(R.id.dos_pagi3);
										TextView dos_pagi4 = (TextView) findViewById(R.id.dos_pagi4);
										TextView dos_pagi5 = (TextView) findViewById(R.id.dos_pagi5);

										// dosis_pagi from spinner
										TextView dos_malam1 = (TextView) findViewById(R.id.dos_malam1);
										TextView dos_malam2 = (TextView) findViewById(R.id.dos_malam2);
										TextView dos_malam3 = (TextView) findViewById(R.id.dos_malam3);
										TextView dos_malam4 = (TextView) findViewById(R.id.dos_malam4);
										TextView dos_malam5 = (TextView) findViewById(R.id.dos_malam5);

										if (banyak == 1) {
											linArv1.setVisibility(View.VISIBLE);
											arv1.setText(msg_list[0]);
											// setSpinner(popupSpinnerDosisPagi1,dos_pagi1);
											dos_pagi1
													.setText(popupSpinnerDosisPagi1
															.getSelectedItem()
															.toString());
											dos_malam1
													.setText(popupSpinnerDosisMalam1
															.getSelectedItem()
															.toString());

										} else if (banyak == 2) {
											linArv1.setVisibility(View.VISIBLE);
											linArv2.setVisibility(View.VISIBLE);
											arv1.setText(msg_list[0]);
											arv2.setText(msg_list[1]);
											dos_pagi1
													.setText(popupSpinnerDosisPagi1
															.getSelectedItem()
															.toString());
											dos_malam1
													.setText(popupSpinnerDosisMalam1
															.getSelectedItem()
															.toString());
											dos_pagi2
													.setText(popupSpinnerDosisPagi2
															.getSelectedItem()
															.toString());
											dos_malam2
													.setText(popupSpinnerDosisMalam2
															.getSelectedItem()
															.toString());

										} else if (banyak == 3) {
											linArv1.setVisibility(View.VISIBLE);
											linArv2.setVisibility(View.VISIBLE);
											linArv3.setVisibility(View.VISIBLE);
											arv1.setText(msg_list[0]);
											arv2.setText(msg_list[1]);
											arv3.setText(msg_list[2]);
											dos_pagi1
													.setText(popupSpinnerDosisPagi1
															.getSelectedItem()
															.toString());
											dos_malam1
													.setText(popupSpinnerDosisMalam1
															.getSelectedItem()
															.toString());
											dos_pagi2
													.setText(popupSpinnerDosisPagi2
															.getSelectedItem()
															.toString());
											dos_malam2
													.setText(popupSpinnerDosisMalam2
															.getSelectedItem()
															.toString());
											dos_pagi3
													.setText(popupSpinnerDosisPagi3
															.getSelectedItem()
															.toString());
											dos_malam3
													.setText(popupSpinnerDosisMalam3
															.getSelectedItem()
															.toString());

										} else if (banyak == 4) {
											linArv1.setVisibility(View.VISIBLE);
											linArv2.setVisibility(View.VISIBLE);
											linArv3.setVisibility(View.VISIBLE);
											linArv4.setVisibility(View.VISIBLE);
											arv1.setText(msg_list[0]);
											arv2.setText(msg_list[1]);
											arv3.setText(msg_list[2]);
											arv4.setText(msg_list[3]);

											dos_pagi1
													.setText(popupSpinnerDosisPagi1
															.getSelectedItem()
															.toString());
											dos_malam1
													.setText(popupSpinnerDosisMalam1
															.getSelectedItem()
															.toString());
											dos_pagi2
													.setText(popupSpinnerDosisPagi2
															.getSelectedItem()
															.toString());
											dos_malam2
													.setText(popupSpinnerDosisMalam2
															.getSelectedItem()
															.toString());
											dos_pagi3
													.setText(popupSpinnerDosisPagi3
															.getSelectedItem()
															.toString());
											dos_malam3
													.setText(popupSpinnerDosisMalam3
															.getSelectedItem()
															.toString());
											dos_pagi4
													.setText(popupSpinnerDosisPagi4
															.getSelectedItem()
															.toString());
											dos_malam4
													.setText(popupSpinnerDosisMalam4
															.getSelectedItem()
															.toString());

										} else if (banyak == 5) {
											linArv1.setVisibility(View.VISIBLE);
											linArv2.setVisibility(View.VISIBLE);
											linArv3.setVisibility(View.VISIBLE);
											linArv4.setVisibility(View.VISIBLE);
											linArv5.setVisibility(View.VISIBLE);
											arv1.setText(msg_list[0]);
											arv2.setText(msg_list[1]);
											arv3.setText(msg_list[2]);
											arv4.setText(msg_list[3]);
											arv5.setText(msg_list[4]);
											dos_pagi1
													.setText(popupSpinnerDosisPagi1
															.getSelectedItem()
															.toString());
											dos_malam1
													.setText(popupSpinnerDosisMalam1
															.getSelectedItem()
															.toString());
											dos_pagi2
													.setText(popupSpinnerDosisPagi2
															.getSelectedItem()
															.toString());
											dos_malam2
													.setText(popupSpinnerDosisMalam2
															.getSelectedItem()
															.toString());
											dos_pagi3
													.setText(popupSpinnerDosisPagi3
															.getSelectedItem()
															.toString());
											dos_malam3
													.setText(popupSpinnerDosisMalam3
															.getSelectedItem()
															.toString());
											dos_pagi4
													.setText(popupSpinnerDosisPagi4
															.getSelectedItem()
															.toString());
											dos_malam4
													.setText(popupSpinnerDosisMalam4
															.getSelectedItem()
															.toString());
											dos_pagi5
													.setText(popupSpinnerDosisPagi5
															.getSelectedItem()
															.toString());
											dos_malam5
													.setText(popupSpinnerDosisMalam5
															.getSelectedItem()
															.toString());
										}
									}
								});

						popupWindow.showAsDropDown(tvtambah_jenis_arv, 50, -30);

					}
				});

		AlertDialog alert = builder.create();
		alert.show();

		Button bq_pos = alert.getButton(DialogInterface.BUTTON_POSITIVE);
		Button bq_neg = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
		bq_pos.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.button));
		bq_pos.setTextColor(Color.WHITE);
		bq_neg.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.button));
		bq_neg.setTextColor(Color.WHITE);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profil_anak, menu);
		return true;
	}

	// this function is to inserting values into local database this is still
	// static :)
	public void InsertDataToLocalDatabase() {
		tabel_caregiver = new TM_Caregiver(getApplicationContext());
		tabel_kelas = new TM_Class(getApplicationContext());
		tabel_dosis = new TM_Drug_Dose(getApplicationContext());
		tabel_status_arv = new TM_Drug_Status(getApplicationContext());
		tabel_type_arv = new TM_Drug_Type(getApplicationContext());
		tabel_kotamadya = new TM_District(getApplicationContext());
		tabel_subdistrict = new TM_Subdistrict(getApplicationContext());
		tabel_child_facility = new TM_Child_Facility(getApplicationContext());
		tabel_cost_facility = new TM_Cost_Facility(getApplicationContext());
	}

	// spinner
	// dosis pagi
	public void setSpin(String[] item, Spinner spin) {
		// Spinner popupSpinnerDosisPagi = (Spinner) popupView
		// .findViewById(R.id.spinDosisPagi);

		ArrayAdapter<String> adapter_dosis = new ArrayAdapter<String>(
				getApplicationContext(), R.layout.spin, item);
		adapter_dosis
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(adapter_dosis);

	}

	public void textViewSetText(String value) {

		tvSusu.setText(value);
	}

	int countSelectedItem;

	private void SimpanFasilitasArray(final String flag,
			final CharSequence[] fasilitas, final TextView tv) {
		// instance od table to get id facility
		tabel_facility = new TM_Facility(getApplicationContext());

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("Select Milk");

		builder.setMultiChoiceItems(fasilitas, null,
				new DialogInterface.OnMultiChoiceClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1,
							boolean arg2) {
						// TODO Auto-generated method stub

						if (arg2) {
							selList.add(arg1);
							countSelectedItem = selList.size();
						} else if (selList.contains(arg1)) {
							// if the item is already selected then remove it
							selList.remove(Integer.valueOf(arg1));
						}
						if (countSelectedItem >= 3) {
							Toast.makeText(context,
									"Anda Tidak Dapat Fasilitas Lebih Dari 2 ",
									Toast.LENGTH_LONG).show();
							((AlertDialog) arg0).getListView().setItemChecked(
									arg1, false);
							selList.remove(Integer.valueOf(arg1));
							countSelectedItem--;
							// ((AlertDialog)arg0).getButton(DialogInterface.BUTTON_POSITIVE).setVisibility(View.INVISIBLE);
						}
					}
				});

		builder.setNegativeButton("CANCEL",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				msg = "";
				ArrayList<String> hasil_fasilitas_sementara = new ArrayList<String>();
				if (selList.isEmpty()) {
					Log.e("Array list", "kosong");
				} else {
					for (int i = 0; i < selList.size(); i++) {
						hasil_fasilitas_sementara.add(fasilitas[selList.get(i)].toString());
						Log.e("Fasilitas", "tidak kosong");
						msg += fasilitas[selList.get(i)] + "\n";
					}
					// cek
					if (flag.equals("susu")) {
						fasilitas_susu = hasil_fasilitas_sementara;
					} else if (flag.equals("vitamin")) {
						fasilitas_vitamin = hasil_fasilitas_sementara;
					} else if (flag.equals("popok")) {
						fasilitas_popok = hasil_fasilitas_sementara;
					}
				}
				tv.setText(msg);
			}
		});

		AlertDialog alert = builder.create();
		alert.show();

		Button bq_pos = alert.getButton(DialogInterface.BUTTON_POSITIVE);
		Button bq_neg = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
		bq_pos.setBackgroundDrawable(getResources().getDrawable(R.drawable.button));
		bq_pos.setTextColor(Color.WHITE);
		bq_neg.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.button));
		bq_neg.setTextColor(Color.WHITE);

	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn_back:
			Intent intent = new Intent(ProfilAnakActivity.this,
					ListAnakActivity.class);
			startActivity(intent);
			break;

		// case R.id.button_simpanprofil:
		// Intent intent4 = new Intent(ProfilAnakActivity.this,
		// ListAnakActivity.class);
		// startActivity(intent4);
		// break;

		case R.id.button_susu:
			selList.clear();
			final CharSequence[] susu = tabel_cost_facility
					.getAllBrandName("1");
			final String flag = "susu";
			SimpanFasilitasArray(flag, susu, tvSusu);

			break;

		case R.id.button_vitamin:
			selList.clear();
			final CharSequence[] vitamin = tabel_cost_facility
					.getAllBrandName("2");
			final String flag_vitamin = "vitamin";
			SimpanFasilitasArray(flag_vitamin, vitamin, tvVitamin);

			break;

		case R.id.button_popok:
			selList.clear();
			final CharSequence[] popok = tabel_cost_facility
					.getAllBrandName("3");
			final String flag_popok = "popok";
			SimpanFasilitasArray(flag_popok, popok, tvPopok);

			break;
		case R.id.button_tambah:
			// TODO Auto-generated method stub
			if (txtPhone1.getVisibility() == View.VISIBLE) {
				txtPhone2.setVisibility(View.VISIBLE);
			} else {
				txtPhone1.setVisibility(View.VISIBLE);
			}
			break;
		}
	}

	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
					mDay);
		}
		return null;
	}

	// untuk tanggal lahir
	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			// String sdate = arrMonth[mMonth] + " " + LPad(mDay + "", "0", 2) +
			// ", " + mYear;
			// String sdate = ""+year+"/"+(monthOfYear + 1 )+"/"+ LPad(mDay +
			// "", "0",2) ;
			String sdate = LPad(mDay + "", "0", 2) + " " + arrMonth[mMonth]
					+ " " + mYear;
			txtDate.setText(sdate);
			// TODO Auto-generated method stub

		}
	};

	// untuk tanggal lahir
	private static String LPad(String schar, String spad, int len) {
		String sret = schar;
		for (int i = sret.length(); i < len; i++) {
			sret = spad + sret;
		}
		return new String(sret);
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
