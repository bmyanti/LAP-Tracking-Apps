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
import com.example.databaselap.TM_Class;
import com.example.databaselap.TM_District;
import com.example.databaselap.TM_Drug_Dose;
import com.example.databaselap.TM_Drug_Status;
import com.example.databaselap.TM_Drug_Type;
import com.example.databaselap.TM_Facility;
import com.example.databaselap.TM_Parent_Status;
import com.example.databaselap.TM_Subdistrict;
import com.example.modellap.TM_Child_Model;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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
	Button susu, vitamin, popok;
	TextView tvSusu, tvVitamin, tvPopok;

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
	String item_golongandarah[] = { "-","A", "B", "O", "AB" };
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

	// fetch data kelas
	String[] datakelas;

	// radio button
	RadioGroup radio_gender_group, radio_status_ayah_group, radio_status_ayah,
			radio_status_ibu_group, radio_status_ibu;
	
	String gender = "", status_ayah = "", status_ibu = "", status_temp = "";
	Boolean status_parent_temp, status_parent_temp1;

	// String
	String picturePath, Path, PathNull;

	// arraylist kotamadya dan kecamatan
	ArrayList<String> arr_kecamatan = new ArrayList<String>();
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
	// simpan fasilitas

	ArrayList<Integer> selList = new ArrayList();
	String msg = "";
	// this is concated facilities id
	String facility_id = " ";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_profil_anak);

		// textview
		tvSusu = (TextView) findViewById(R.id.textview_susu);
		tvVitamin = (TextView) findViewById(R.id.textViewVitamin);
		tvPopok = (TextView) findViewById(R.id.textViewPopok);

		// editText
		txtName = (EditText) findViewById(R.id.edit_nama_anak);
		txtDate = (EditText) findViewById(R.id.editTextTtl);
		txtFatherName = (EditText) findViewById(R.id.edit_namaayah);
		txtMotherName = (EditText) findViewById(R.id.edit_namaibu);
		txtCareGiverName = (EditText) findViewById(R.id.edit_caregiver_name);
		txtAddress = (EditText) findViewById(R.id.edit_alamat);
		txtTeleponNumberCG = (EditText) findViewById(R.id.edit_telepon);
		txtSchool = (EditText) findViewById(R.id.edit_sekolah);

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

		// insertt to local database
		InsertDataToLocalDatabase();

		// spinner
		// fetch golongan darah
		Spinner spinner_golda = (Spinner) findViewById(R.id.spinner_golongandarah);
		spinner_golda.setOnItemSelectedListener(this);
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, item_golongandarah);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_golda.setAdapter(adapter1);

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
		item_status_obat = data_statusARV.toArray(new String[data_statusARV.size()]);
		Spinner spinner_status_obat = (Spinner) findViewById(R.id.spinner_statusobat);
		spinner_status_obat.setOnItemSelectedListener(this);
		ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, item_status_obat);
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
		Spinner spinner_cg = (Spinner) findViewById(R.id.spinner_caregiver);
		spinner_cg.setOnItemSelectedListener(this);
		ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, item_caregiver);
		adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_cg.setAdapter(adapter6);

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

		// menyimpan data anak
		simpan_profil.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				tabel_anak = new TM_Child(getApplicationContext());
				// check whether it is create or update -> krn me-refer ke
				// activity yang sama
				String path_gambar = "";
				Intent i = getIntent();
				PathNull = "kosong";
				if (i.getStringExtra("id_anak") == null) {

					if (picturePath != null) {
						path_gambar = picturePath;
					}

					else if (Path != null) {
						path_gambar = Path;
					} else if (PathNull != null) {

						path_gambar = PathNull;
					}
					tabel_anak.addRow(
							txtName.getText().toString(),
							txtDate.getText().toString(),
							gender,
							golongan_darah.getSelectedItem().toString(),
							txtFatherName.getText().toString(),
							txtMotherName.getText().toString(),
							txtCareGiverName.getText().toString(),
							txtAddress.getText().toString(),
							txtTeleponNumberCG.getText().toString(),
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
							facility_id,
							findStatusParent(radio_status_ayah_group,
									radio_status_ayah),
							findStatusParent(radio_status_ibu_group,
									radio_status_ibu), tabel_subdistrict
									.getIdSubDistrict(spinner_kecamatan
											.getSelectedItem().toString()),
							tabel_subdistrict
									.getIdSubDistrict(spinner_kecamatansekolah
											.getSelectedItem().toString()));
					Log.e("gender ",
							""
									+gender);
				} else {
					tabel_anak = new TM_Child(getApplicationContext());
					tabel_anak.updateChildIdentityById(i
							.getStringExtra("id_anak"), txtName.getText()
							.toString(), txtDate.getText().toString(), gender,
							golongan_darah.getSelectedItem().toString(),
							txtFatherName.getText().toString(), txtMotherName
									.getText().toString(), txtCareGiverName
									.getText().toString(), txtAddress.getText()
									.toString(), txtTeleponNumberCG.getText()
									.toString(), txtSchool.getText().toString());

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

		/*
		 * //btn simpan profil anak simpan_profil.setOnClickListener(new
		 * OnClickListener() {
		 * 
		 * @Override public void onClick(View arg0) { // TODO Auto-generated
		 * method stub int TIMEOUT_MILLISEC = 10000; HttpParams httpParams = new
		 * BasicHttpParams();
		 * HttpConnectionParams.setConnectionTimeout(httpParams,
		 * TIMEOUT_MILLISEC); HttpConnectionParams.setSoTimeout(httpParams,
		 * TIMEOUT_MILLISEC); HttpClient client = new
		 * DefaultHttpClient(httpParams); List<NameValuePair> nameValuePairs =
		 * new ArrayList<NameValuePair>(3); nameValuePairs.add(new
		 * BasicNameValuePair("nama", txtName.getText().toString()));
		 * nameValuePairs.add(new BasicNameValuePair("date",
		 * txtDate.getText().toString())); nameValuePairs.add(new
		 * BasicNameValuePair("goldar", golongan_darah.toString()));
		 * 
		 * try { HttpPost request = new
		 * HttpPost("http://192.168.1.171/jsonn/addkota.php");
		 * request.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		 * 
		 * HttpResponse response = client.execute(request); } catch(Exception
		 * e){ Toast.makeText(getApplicationContext(), "Error",
		 * Toast.LENGTH_LONG).show(); e.printStackTrace(); }
		 * Toast.makeText(getApplicationContext(), "Berhasil",
		 * Toast.LENGTH_LONG).show(); ListAnakActivity.la.RefreshList();
		 * finish(); } });
		 */
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
			
			//checked any gender
			if(model_anak.getChild_gender().equals("PEREMPUAN"))
			{
				RadioButton pr = (RadioButton) findViewById(R.id.radioButtonPerempuan);
				pr.setChecked(true);
			
			}
			else if(model_anak.getChild_gender().equals("LAKI - LAKI"))
			{
				RadioButton lk = (RadioButton) findViewById(R.id.radioButtonLakilaki);
				lk.setChecked(true);
			}
			else{}
			
			//checked any parent status
			RadioButton life_fat = (RadioButton) findViewById(R.id.radioButtonHidup);
			RadioButton life_fat1 = (RadioButton) findViewById(R.id.radioButtonMeninggal);
			RadioButton hiv_fat = (RadioButton) findViewById(R.id.radioButtonAyahPositif);
			RadioButton hiv_fat1 = (RadioButton) findViewById(R.id.radioButtonAyahNegatif);
			
			RadioButton life_mot = (RadioButton) findViewById(R.id.radioButtonIbuHidup);
			RadioButton life_mot1 = (RadioButton) findViewById(R.id.radioButtonIbuMeninggal);
			RadioButton hiv_mot = (RadioButton) findViewById(R.id.radioButtonIbuPositif);
			RadioButton hiv_mot1 = (RadioButton) findViewById(R.id.radioButtonIbuNegatif);
			
			if(model_anak.getDad_status_id().equals("1")) {life_fat.setChecked(true); hiv_fat.setChecked(true);}
			else if(model_anak.getDad_status_id().equals("2")) {life_fat.setChecked(true); hiv_fat1.setChecked(true);}
			else if(model_anak.getDad_status_id().equals("3")){life_fat1.setChecked(true); hiv_fat.setChecked(true);}
			else if(model_anak.getDad_status_id().equals("4")) {life_fat1.setChecked(true); hiv_fat1.setChecked(true);}
			else{}
			
			if(model_anak.getMom_status_id().equals("1")) {life_mot.setChecked(true); hiv_mot.setChecked(true);}
			else if(model_anak.getMom_status_id().equals("2")) {life_mot.setChecked(true); hiv_mot1.setChecked(true);}
			else if(model_anak.getMom_status_id().equals("3")) {life_mot1.setChecked(true); hiv_mot.setChecked(true);}
			else if(model_anak.getMom_status_id().equals("4")) {life_mot1.setChecked(true); hiv_mot1.setChecked(true);}
			else{}
		} else {
			Log.d("***DEBUG****", "Intent was null");

		}

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
				if(position == 0)
				{
					
				}
				else
				{
					
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

		ArrayAdapter<String> kecAdapter = new ArrayAdapter<String>(
				getApplicationContext(), R.layout.spin, arr_kecamatan);

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

				File f = new File(Environment.getExternalStorageDirectory()
						.toString());

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

	// public void onRadioButtonClicked(View view) {
	// // Is the button now checked?
	// boolean checked = ((RadioButton) view).isChecked();
	//
	// // Check which radio button was clicked
	// switch(view.getId()) {
	// case R.id.radio_pirates:
	// if (checked)
	// // Pirates are the best
	// break;
	// case R.id.radio_ninjas:
	// if (checked)
	// // Ninjas rule
	// break;
	// }
	// }

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
	}

	public void textViewSetText(String value) {

		tvSusu.setText(value);
	}

	private void SimpanFasilitasArray(final CharSequence[] fasilitas,
			final TextView tv) {
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
						} else if (selList.contains(arg1)) {
							// if the item is already selected then remove it
							selList.remove(Integer.valueOf(arg1));
						}
					}
				});

		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				msg = "";
				if(selList.isEmpty())
				{
					facility_id = null;
					Log.e("Array list", "kosong");
				}
				else
				{
					for (int i = 0; i < selList.size(); i++) {
						facility_id += tabel_facility
								.getIdFacility(fasilitas[selList.get(i)].toString());
						facility_id += ",";
						msg = msg + fasilitas[selList.get(i)] + "\n";

					}
				}
				
				tv.setText(msg);

			}
		});

		AlertDialog alert = builder.create();
		alert.show();

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
			final CharSequence[] susu = { "Bebelac 4-Madu-800 gr",
					"Chilschool-4-soya-300 gr", "Dancow 1+-Vanilla-800 gr",
					"Dancow 5+-Vanilla-800 gr", "Dancow-1+-Madu-800 gr",
					"Dancow-1+-Vanilla-800 gr", "Dancow-3+-Madu-800 gr",
					"Dancow-5+-Madu-800 gr", "Dancow-5+-Madu-800 gr",
					"Dancow-Datita3/5-1000 gr", "Dancow-Fullcream-Choc-800 gr",
					"Dancow-Fullcream-Vanilla-800  gr" };
			SimpanFasilitasArray(susu, tvSusu);

			break;

		case R.id.button_vitamin:
			// LayoutInflater layoutinflater1 = LayoutInflater.from(context);
			// View promptView1 =
			// layoutinflater1.inflate(R.layout.activity_fasilitas_vitamin,
			// null);
			//
			// AlertDialog.Builder alertDialogBuilder1 = new
			// AlertDialog.Builder(context);
			// alertDialogBuilder1.setView(promptView1);
			//
			// final LinearLayout btn_simpan1 = (LinearLayout)
			// promptView1.findViewById(R.id.btn_simpan1);
			//
			// btn_simpan1.setOnClickListener(new OnClickListener() {
			//
			// @Override
			// public void onClick(View v) {
			// // TODO Auto-generated method stub
			// Intent simpan1 = new
			// Intent(getApplication(),ProfilAnakActivity.class);
			// startActivity(simpan1);
			//
			// }
			// });
			// AlertDialog alert = alertDialogBuilder1.create();
			// alert.show();
			// break;

			selList.clear();
			final CharSequence[] vitamin = { "Vidorant", "Sangobion" };
			SimpanFasilitasArray(vitamin, tvVitamin);

			break;

		case R.id.button_popok:
			// LayoutInflater layoutinflater2 = LayoutInflater.from(context);
			// View promptView2 =
			// layoutinflater2.inflate(R.layout.activity_fasilitas_popok, null);
			//
			// AlertDialog.Builder alertDialogBuilder2 = new
			// AlertDialog.Builder(context);
			// alertDialogBuilder2.setView(promptView2);
			//
			// final LinearLayout btn_simpan2 = (LinearLayout)
			// promptView2.findViewById(R.id.btn_simpan2);
			//
			// btn_simpan2.setOnClickListener(new OnClickListener() {
			//
			// @Override
			// public void onClick(View v) {
			// // TODO Auto-generated method stub
			// Intent simpan2 = new
			// Intent(getApplication(),ProfilAnakActivity.class);
			// startActivity(simpan2);
			//
			// }
			// });
			// AlertDialog alert1 = alertDialogBuilder2.create();
			// alert1.show();
			// break;
			//
			// default:
			// break;

			selList.clear();
			final CharSequence[] popok = { "Diaper-Goon-pants-XXL38",
					"Diaper-Pampers-pants-L36", "Diaper-Pampers-pants-M42",
					"Diaper-Pampers-pants-XL32" };
			SimpanFasilitasArray(popok, tvPopok);

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
