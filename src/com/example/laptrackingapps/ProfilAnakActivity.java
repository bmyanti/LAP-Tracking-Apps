package com.example.laptrackingapps;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
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

import com.example.databaselap.Database;
import com.example.modellap.ChildARV_Model;
import com.example.modellap.ChildFacility_Model;
import com.example.modellap.Child_Model;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings.Secure;
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
import android.telephony.TelephonyManager;
import android.util.Log;
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

	//database
	Database db;
	final Context context = this;
	ImageView back, tambah_foto;
	LinearLayout simpan_profil, linPhone1, linPhone2;
	EditText txtName, txtFatherName, txtMotherName, txtCareGiverName,
			txtAddress, txtTeleponNumberCG, txtSchool;
	TextView tvtambah_jenis_arv;
	Button susu, vitamin, popok;
	TextView tvSusu, tvVitamin, tvPopok, tvarv1, tvarv2, tvarv3, tvarv4,
			tvarv5;
	RadioButton rb_gender;

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
	String item_dosis_pagi[];
	String item_dosis_malam[];
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
	// String temp status, type, and dosis
	String status_ = "DS000", type_ = "DTY000", dosis_ = "DD000";

	// phone number caregiver
	EditText txtPhone1, txtPhone2;

	Bitmap thumbnailCamera;

	// arraylist kotamadya dan kecamatan
	ArrayList<String> arr_kecamatan = new ArrayList<String>();
	ArrayList<String> arr_kotamadya = new ArrayList<String>();
	ArrayList<String> data_tipeARV = new ArrayList<String>();
	ArrayList<String> data_kotamadya;
	
	String filePath;

	ArrayList<Integer> selList = new ArrayList();
	String msg = "";
	// this is concated facilities id
	String facility_id = " ";
	Boolean status_susu = false, status_vitamin = false, status_popok = false;
	String fasilitas_susu_id = " ", fasilitas_vitamin_id = " ",fasilitas_popok_id = " ";
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


	int banyak;
	ArrayList<String> selectedItems = new ArrayList<String>();
	ArrayList<ChildARV_Model> arv_anak = new ArrayList<ChildARV_Model>();
	ChildARV_Model arv_model1 = new ChildARV_Model();
	ChildARV_Model arv_model2 = new ChildARV_Model();
	ChildARV_Model arv_model3 = new ChildARV_Model();
	ChildARV_Model arv_model4 = new ChildARV_Model();
	ChildARV_Model arv_model5 = new ChildARV_Model();
	
	Button btnDismiss;
	TextView tv0,tv1,tv2 ,tv3 ,tv4; 

	LinearLayout linARV1,linARV2 ,linARV3, linARV4 ,linARV5;
	
	LinearLayout linArv1,linArv2 ,linArv3,linArv4 ,linArv5;

	// arv
	TextView arv1,arv2 ,arv3,arv4,arv5;

	// dosis_pagi from spinner
	TextView dos_pagi1 ,dos_pagi2 ,dos_pagi3 ,dos_pagi4,dos_pagi5 ;

	// dosis_pagi from spinner
	TextView dos_malam1,dos_malam2,dos_malam3,dos_malam4, dos_malam5;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_profil_anak);

		//database
		db = new Database(this);
		// textview
		// arv from spinner
		linArv1 = (LinearLayout) findViewById(R.id.linArv1);
		linArv2 = (LinearLayout) findViewById(R.id.linArv2);
		linArv3 = (LinearLayout) findViewById(R.id.linArv3);
		linArv4 = (LinearLayout) findViewById(R.id.linArv4);
		linArv5 = (LinearLayout) findViewById(R.id.linArv5);
		// arv
		arv1 = (TextView) findViewById(R.id.arv1);
		arv2 = (TextView) findViewById(R.id.arv2);
		arv3 = (TextView) findViewById(R.id.arv3);
		arv4 = (TextView) findViewById(R.id.arv4);
		arv5 = (TextView) findViewById(R.id.arv5);

		// dosis_pagi from spinner
		dos_pagi1 = (TextView) findViewById(R.id.dos_pagi1);
		dos_pagi2 = (TextView) findViewById(R.id.dos_pagi2);
		dos_pagi3 = (TextView) findViewById(R.id.dos_pagi3);
		dos_pagi4 = (TextView) findViewById(R.id.dos_pagi4);
		dos_pagi5 = (TextView) findViewById(R.id.dos_pagi5);

		// dosis_pagi from spinner
		dos_malam1 = (TextView) findViewById(R.id.dos_malam1);
		dos_malam2 = (TextView) findViewById(R.id.dos_malam2);
		dos_malam3 = (TextView) findViewById(R.id.dos_malam3);
		dos_malam4 = (TextView) findViewById(R.id.dos_malam4);
		dos_malam5 = (TextView) findViewById(R.id.dos_malam5);

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

		linPhone1 = (LinearLayout) findViewById(R.id.linPhone1);
		linPhone2 = (LinearLayout) findViewById(R.id.linPhone2);
		//
		txtPhone1 = (EditText) findViewById(R.id.edit_telepon1);
		txtPhone2 = (EditText) findViewById(R.id.edit_telepon2);

		back = (ImageView) findViewById(R.id.btn_back);
		simpan_profil = (LinearLayout) findViewById(R.id.button_simpanprofil);
		golongan_darah = (Spinner) findViewById(R.id.spinner_golongandarah);
		// jenis_obat = (Spinner) findViewById(R.id.spinner_jenisobat);
		// dosis = (Spinner) findViewById(R.id.spinner_dosisobat);
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

		
		SelectFasilitas();
		// spinner
		// fetch golongan darah
		spinner_golda = (Spinner) findViewById(R.id.spinner_golongandarah);
		spinner_golda.setOnItemSelectedListener(this);
		adapter_golda = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, item_golongandarah);
		adapter_golda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_golda.setAdapter(adapter_golda);
		// status obat
		ArrayList<String> data_statusARV = db.getAllDataARVStatus();
		item_status_obat = data_statusARV.toArray(new String[data_statusARV.size()]);
		Spinner spinner_status_obat = (Spinner) findViewById(R.id.spinner_statusobat);
		spinner_status_obat.setOnItemSelectedListener(this);
		ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, item_status_obat);
		adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_status_obat.setAdapter(adapter4);

		// kelas
		ArrayList<String> data_kelas = db.getDataKelas();
		item_kelas = data_kelas.toArray(new String[data_kelas.size()]);
		Spinner spinner_kelas = (Spinner) findViewById(R.id.spinner_kelas);
		spinner_kelas.setOnItemSelectedListener(this);
		ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, item_kelas);
		adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_kelas.setAdapter(adapter5);

		// caregiver
		ArrayList<String> data_caregiver = db.getAllDataCareGiver();
		item_caregiver = data_caregiver.toArray(new String[data_caregiver.size()]);
		spinner_cg = (Spinner) findViewById(R.id.spinner_caregiver);
		spinner_cg.setOnItemSelectedListener(this);
		adapter_caregiver = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, item_caregiver);
		adapter_caregiver.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_cg.setAdapter(adapter_caregiver);

		// wilayah kotamadya dan kecamatan
		data_kotamadya = db.getAllDataDistrict();
		item_kotamadya = data_kotamadya.toArray(new String[data_kotamadya.size()]);
		spinner_kotamadya = (Spinner) findViewById(R.id.spinner_kotamadya);
		spinner_kotamadya.setOnItemSelectedListener(this);

		// for school
		spinner_kotamadyasekolah = (Spinner) findViewById(R.id.spinner_kotamadyasekolah);
		spinner_kotamadyasekolah.setOnItemSelectedListener(this);
		spinner_kecamatan = (Spinner) findViewById(R.id.spinner_kecamatan);
		spinner_kecamatansekolah = (Spinner) findViewById(R.id.spinner_kecamatansekolah);

		//
		ArrayAdapter<String> kotamadyaAdapter = new ArrayAdapter<String>(this,R.layout.spin, item_kotamadya);
		kotamadyaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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
		radio_gender_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						rb_gender = (RadioButton) group.findViewById(checkedId);
						if (null != rb_gender && checkedId > -1) {
							gender = rb_gender.getText().toString();
						}
					}
				});

		// // this is getting id status of
		status_ayah = findStatusParent(radio_status_ayah_group,radio_status_ayah);
		status_ibu = findStatusParent(radio_status_ibu_group, radio_status_ibu);

		// pop up tambah obat
		tvtambah_jenis_arv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ArrayList<String> data_tipeARV = db.getAllDataARVType();
				item_jenis_obat = data_tipeARV.toArray(new String[data_tipeARV.size()]);
				pilihArv(item_jenis_obat, "Pilih ARV");
			}
		});

		// menyimpan data anak
		 simpan_profil.setOnClickListener(new OnClickListener() {
			  @Override
			  public void onClick(View arg0) {
			    GetCaregiverPhone();
			    // check whether it is create or update -> krn me-refer ke
			    // activity yang sama
			    String path_gambar = "";
			    Intent i = getIntent();
			    PathNull = "kosong";
			    if (picturePath != null) {
			         path_gambar = picturePath;
			    }else if (filePath != null) {
			         path_gambar = filePath;
			    } else if (PathNull != null) {
			     path_gambar = PathNull;
			        }
			    if (i.getStringExtra("id_anak") == null) {
			     /**************************INSERT DATA ANAK ******************************/
			     if(validasi()==true)
			     {
			     db.InsertDataAnak(GetChildID(), 
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
			       db.getIdStatusARV(status_obat.getSelectedItem().toString()), 
			       type_, 
			       dosis_, 
			       db.getIdKelas(kelas.getSelectedItem().toString()),
			       db.getIdCaregiver(care_giver.getSelectedItem().toString()), 
			       "-", 
			       findStatusParent(radio_status_ayah_group,radio_status_ayah), 
			       findStatusParent(radio_status_ibu_group,radio_status_ibu), 
			       db.getIdSubDistrict(spinner_kecamatan.getSelectedItem().toString()), 
			       db.getIdSubDistrict(spinner_kecamatansekolah.getSelectedItem().toString()), 
			       "",
			       "", 
			       "",
			       "",
			       "", 
			       "", 
			       "", 
			       "");
			     }
			     Log.e("Insert Data Anak", "ID "+GetChildID() +" path "+path_gambar);
			     /***************************INSERT FASILITAS ANAK **********************************/
			     InsertFasilitasAnak(db.getLastInsertedChild());
			     /**************************INSERT ARV ANAK******************************************/
			     for(ChildARV_Model mod_arv : arv_anak)
			     {
			      //insert untuk data dosis pagi & malam
			      db.InsertARVAnak(db.getLastInsertedChild(), mod_arv.GetDrugTypeID(), mod_arv.GetDrugDoseID(), "Sartika", GetTimeNow());
			      db.InsertARVAnak(db.getLastInsertedChild(), mod_arv.GetDrugTypeID(), mod_arv.GetDrugDoseID1(), "Sartika", GetTimeNow());
			     }
			     
			     
			    } else {
			     /***************************UPDATE DATA ANAK******************************************/
			     String id = i.getStringExtra("id_anak");
			     db.updateChildIdentityById(
			       id,
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
			       findStatusParent(radio_status_ayah_group,radio_status_ayah),
			       findStatusParent(radio_status_ibu_group,radio_status_ibu), 
			       db.getIdCaregiver(care_giver.getSelectedItem().toString()),
			       db.getIdSubDistrict(spinner_kecamatan.getSelectedItem().toString()),
			       db.getIdSubDistrict(spinner_kecamatansekolah.getSelectedItem().toString()),
			       db.getIdKelas(kelas.getSelectedItem().toString()), path_gambar,"-","-");

			     /**************************UPDATE FASILITAS ANAK **********************************/
			     DeleteFasilitasAnak(i.getStringExtra("id_anak"));
			     InsertFasilitasAnak(i.getStringExtra("id_anak"));
			     
			     /************************* UPDATE ARV *********************/
			     db.DeleteARVAnak(i.getStringExtra("id_anak"));
			     for(ChildARV_Model mod_arv : arv_anak)
			     {
			      //insert untuk data dosis pagi & malam
			      db.InsertARVAnak(i.getStringExtra("id_anak"), mod_arv.GetDrugTypeID(), mod_arv.GetDrugDoseID(), "Sartika", GetTimeNow());
			      db.InsertARVAnak(i.getStringExtra("id_anak"), mod_arv.GetDrugTypeID(), mod_arv.GetDrugDoseID1(), "Sartika", GetTimeNow());
			     }

			    }

			    // go to test activity
			    if(validasi()==true)
			    {
			    Intent test = new Intent(getApplication(),ListAnakActivity.class);
			    startActivity(test);
			    }
			    
			    

			   }
			  });
		tambah_foto.setOnClickListener(new View.OnClickListener() {
			   // button tambah foto
			   @Override
			   public void onClick(View v) {
			    // TODO Auto-generated method stub
			    LayoutInflater layoutinflater = LayoutInflater.from(context);
			    View promptView = layoutinflater.inflate(R.layout.activity_foto_dialog, null);

			    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
			    alertDialogBuilder.setView(promptView);

			    final Button btn_kamera = (Button) promptView.findViewById(R.id.button_kamera);
			    final Button btn_galeri = (Button) promptView.findViewById(R.id.button_galeri);

			    filePath=null;
			    btn_kamera.setOnClickListener(new OnClickListener() {

			     public void onClick(View v) {
			      // TODO Auto-generated method stub
			      Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			      startActivityForResult(intent, 1);
			      alertDialogBuilder.create().dismiss();
			     }
			    });

			    btn_galeri.setOnClickListener(new OnClickListener() {

			     public void onClick(View v) {
			      // TODO Auto-generated method stub
			      Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			      startActivityForResult(intent, 2);
			      alertDialogBuilder.create().dismiss();
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

		/*******************************UPDATING DATA ANAK ******************************/
		Intent i = getIntent();
		if (i != null && i.getStringExtra("id_anak") != null) {
			Log.d("***DEBUG****", "For update data anak");
			String id_child = i.getStringExtra("id_anak");
			Child_Model model_anak = new Child_Model();
			
			model_anak = db.getChildIdentityById(id_child);
			// displaying foto
			// cek jika poto belum dimasukkan -> maka display default
			if (!model_anak.getImage_path().equals("kosong")) {
				File image = new File(model_anak.getImage_path());
				BitmapFactory.Options bmOptions = new BitmapFactory.Options();
				Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(), bmOptions);
				bitmap = Bitmap.createScaledBitmap(bitmap, 120, 120, true);
				tambah_foto.setImageBitmap(bitmap);
			} else {
				Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon);
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
			spinner_position = adapter_golda.getPosition(model_anak.getBlood_type());
			spinner_golda.setSelection(spinner_position);

			// caregiver spinner
			spinner_position = adapter_caregiver.getPosition(db.getNameCaregiver(model_anak.getCaregiver_id()));
			spinner_cg.setSelection(spinner_position);

			// spinner kotamadya dan kecamatan
//			arr_kecamatan = db.getAllDataSubdistrict();
//			kecAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.spin, arr_kecamatan);
//			spinner_position = kecAdapter.getPosition(db.getNameSubDistrict(model_anak.getSubdistrict_id()));
//			spinner_kecamatan.setAdapter(kecAdapter);
//			spinner_kecamatan.setSelection(spinner_position);

			// Toast.makeText(
			// getApplicationContext(),
			// ""+
			// tabel_subdistrict.getNameSubDistrict(model_anak.getSubdistrict_id()),
			// Toast.LENGTH_SHORT)
			// .show();
			// spinner_kecamatan.setSelection(spinner_position);

			// fetch the id of district id and then populated in names
			// spinner_position =
			// kotamadyaAdapter.getPosition(tabel_district.getNameDistrict(tabel_subdistrict.getIDDistrict(model_anak.getSubdistrict_id())));
			// spinner_kotamadya.setSelection(spinner_position);
			//
			// spinner_position =
			// kotamadyaAdapter.getPosition(tabel_district.getNameDistrict(tabel_subdistrict.getIDDistrict(model_anak.getSchool_subdistrict_id())));
			// spinner_kotamadyasekolah.setSelection(spinner_position);

			// displaying fasilitas anak
			/***************************** DISPLAYING FASILITAS ANAK ******************************/
			RetrieveFasilitasAnak(model_anak.getChild_id());
			/*****************************DISPLAYING ARV ANAK *************************************/
			RetrieveARVAnak(model_anak.getChild_id());
		} else {
			Log.d("***DEBUG****", "This is for create new child profil");

		}

	}
	
	

	public String GetTimeNow() {
		Calendar c = Calendar.getInstance();

		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		return df.format(c.getTime());
	}
	
	public String GetCaregiverPhone() {
		String result = "";
		if (!txtTeleponNumberCG.getText().toString().isEmpty()) {
			result += txtTeleponNumberCG.getText().toString() + ";\n";
		}
		if (!txtPhone1.getText().toString().isEmpty()) {
			result += txtPhone1.getText().toString() + ";\n";
		}
		if (!txtPhone2.getText().toString().isEmpty()) {
			result += txtPhone2.getText().toString() + ";\n";
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

	public void DeleteFasilitasAnak(String id_anak) {
		db.DeleteFasilitasAnak(id_anak);
		Log.e("Delete obsolete fasiltas anak", id_anak);
	}

	public void InsertFasilitasAnak(String id_anak) {
		// cek apakah mk memilih susu
		Log.e("insert fasilitas anak", "" + true);
		if (!fasilitas_susu.isEmpty()) {
			for (String a : fasilitas_susu) {
				db.InsertFasilitasAnak(id_anak, "FA001",db.getIdCostFacility(a));
				Log.e("insert ke tabel cost facility -> susu", a);
			}
		}
		if (!fasilitas_vitamin.isEmpty()) {
			for (String b : fasilitas_vitamin) {
				db.InsertFasilitasAnak(id_anak, "FA002",db.getIdCostFacility(b));
				Log.e("insert ke tabel cost facility -> vitamin", b);
			}
		}

		if (!fasilitas_popok.isEmpty()) {
			for (String b : fasilitas_popok) {
				db.InsertFasilitasAnak(id_anak, "FA003",db.getIdCostFacility(b));
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
		ArrayList<ChildFacility_Model> all_facility_id = db
				.getSemuaFasilitasAnak(id_anak);
		if (all_facility_id != null) {
			for (ChildFacility_Model model : all_facility_id) {
				if (model.getFacility_id().equals("1")) {
					Checkbox_Susu.setChecked(true);
					btn_susu.setVisibility(View.VISIBLE);
					SelectFasilitas();
					fasilitas_susu.add(db.getNameCostFacility(model.getFacility_cost_id()));
					continue;
				}
				if (model.getFacility_id().equals("2")) {
					Checkbox_Vitamin.setChecked(true);
					btn_vitamin.setVisibility(View.VISIBLE);
					SelectFasilitas();
					fasilitas_vitamin.add(db.getNameCostFacility(model.getFacility_cost_id()));
					continue;
				}
				if (model.getFacility_id().equals("3")) {
					Checkbox_Popok.setChecked(true);
					btn_popok.setVisibility(View.VISIBLE);
					SelectFasilitas();
					fasilitas_popok.add(db.getNameCostFacility(model.getFacility_cost_id()));
					continue;
				}
			}
		}

		// displaying it
		if (!fasilitas_susu.isEmpty()) {
			msg = "";
			for (String a : fasilitas_susu) {
				msg += a + "\n";
			}
			tvSusu.setText(msg);
		}
		if (!fasilitas_vitamin.isEmpty()) {
			msg = "";
			for (String a : fasilitas_vitamin) {
				msg += a + "\n";
			}
			tvVitamin.setText(msg);
		}
		if (!fasilitas_popok.isEmpty()) {
			msg = "";
			for (String a : fasilitas_popok) {
				msg += a + "\n";
			}
			tvPopok.setText(msg);
		}
		Log.e("retrieve fasilitas anak", "true");
	}
	
	public void RetrieveARVAnak(String id_anak)
	{
		arv_anak = db.getSemuaARVAnak(id_anak);
		//make for object first
		ChildARV_Model arv = new ChildARV_Model(); 
		arv.SetARVTypeID("-");arv.SetARVDoseID("-");arv.SetARVDoseID1("-");
		for(ChildARV_Model mod_arv : arv_anak)
		{
			Log.e("Retrieve arv anak","true"+arv_anak.size());
			if(arv.GetDrugTypeID().equals("-"))
			{
				arv.SetARVTypeID(db.getNameTypeARV(mod_arv.GetDrugTypeID()));
				arv.SetARVDoseID(db.getNameDose(mod_arv.GetDrugDoseID()));
				continue;
			}
			else
			{
				arv.SetARVDoseID1(db.getNameDose(mod_arv.GetDrugDoseID()));
				Log.e("Dosis Malam", ""+arv.GetDrugDoseID1());
				if(linArv1.getVisibility() == View.INVISIBLE)
				{
					linArv1.setVisibility(View.VISIBLE);
					arv1.setText(arv.GetDrugTypeID());
					dos_pagi1.setText(arv.GetDrugDoseID());
					dos_malam1.setText(arv.GetDrugDoseID1());
				}else if(linArv2.getVisibility() == View.INVISIBLE)
				{
					linArv2.setVisibility(View.VISIBLE);
					arv2.setText(arv.GetDrugTypeID());
					dos_pagi2.setText(arv.GetDrugDoseID());
					dos_malam2.setText(arv.GetDrugDoseID1());
					
				}else if(linArv3.getVisibility() == View.INVISIBLE)
				{
					linArv3.setVisibility(View.VISIBLE);
					arv3.setText(arv.GetDrugTypeID());
					dos_pagi3.setText(arv.GetDrugDoseID());
					dos_malam3.setText(arv.GetDrugDoseID1());
				}else if(linArv4.getVisibility() == View.INVISIBLE)
				{
					linArv4.setVisibility(View.VISIBLE);
					arv4.setText(arv.GetDrugTypeID());
					dos_pagi4.setText(mod_arv.GetDrugDoseID());
					dos_malam4.setText(arv.GetDrugDoseID1());
				}else if(linArv5.getVisibility() == View.INVISIBLE)
				{
					linArv5.setVisibility(View.VISIBLE);
					arv5.setText(arv.GetDrugTypeID());
					dos_pagi5.setText(mod_arv.GetDrugDoseID());
					dos_malam5.setText(arv.GetDrugDoseID1());
				}
				
				arv.SetARVTypeID("-");arv.SetARVDoseID("-");arv.SetARVDoseID1("-");
			}
		}		
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
						RadioButton rb = (RadioButton) group.findViewById(checkedId);
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
				String sKotaMadya = db.getIdDistrict(item_kotamadya[position]);
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
		arr_kecamatan = db.getAllNameSubDistrict(id_kotamadya);

		kecAdapter = new ArrayAdapter<String>(getApplicationContext(),
				R.layout.spin, arr_kecamatan);

		if (flag == 1) {

			spinner_kecamatan.setOnItemSelectedListener(this);
			spinner_kecamatan.setAdapter(kecAdapter);
			Log.e("kecamatan", "" + spinner_kecamatan.getSelectedItem());
		} else {

			spinner_kecamatansekolah.setOnItemSelectedListener(this);
			spinner_kecamatansekolah.setAdapter(kecAdapter);
			Log.e("kecamatan", "" + spinner_kecamatansekolah.getSelectedItem());
		}

	}

	ArrayList<String> resultCamera;
	 
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  super.onActivityResult(requestCode, resultCode, data);

	  if (resultCode == RESULT_OK) {
	   if (requestCode == 1) {

	    thumbnailCamera = (Bitmap) data.getExtras().get("data");
	    thumbnailCamera = Bitmap.createScaledBitmap(thumbnailCamera, 132, 105, true);
	    
	    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
	    thumbnailCamera.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
	    
	    File destination = new File(Environment.getExternalStorageDirectory(),System.currentTimeMillis() + ".jpg");
	    FileOutputStream fo;
	    //Uri selectedImage = data.getData();

	    String[] filePathColumn = {MediaStore.Images.Media.DATA};
	    //Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
	    final String imageOrderBy = MediaStore.Images.Media._ID+ " DESC";
	    Cursor cursor = managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,filePathColumn, null, null, imageOrderBy);
	             
	    cursor.moveToFirst();
	    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
	    filePath = cursor.getString(columnIndex);
	    Log.v("log","filePath is : "+filePath); 
	    
	    try {
	    	destination.createNewFile();
	    	fo = new FileOutputStream(destination);
	    	fo.write(bytes.toByteArray());
	     
	    	fo.close();
	    } catch (FileNotFoundException e) {
	    	e.printStackTrace();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }

	    tambah_foto.setImageBitmap(thumbnailCamera);

	   } else if (requestCode == 2) {

	    Uri selectedImage = data.getData();
	    String[] filePath = { MediaStore.Images.Media.DATA };
	    Cursor c = getContentResolver().query(selectedImage, filePath,null, null, null);
	    c.moveToFirst();
	    int columnIndex = c.getColumnIndex(filePath[0]);
	    picturePath = c.getString(columnIndex);
	    c.close();
	    Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
	    Log.w("path of image from gallery......******************.........",picturePath + "");
	    tambah_foto.setImageBitmap(thumbnail);
	   }
	  }
	 }

	private void pilihArv(final String[] arv, String Title) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(Title);
		builder.setMultiChoiceItems(arv, null,new DialogInterface.OnMultiChoiceClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1,
							boolean arg2) {
						// TODO Auto-generated method stub

						if (arg2) {
							selList.add(arg1);
							banyak = selList.size();
							selectedItems.add(arv[arg1]);

						} else if (selList.contains(arg1)) {
							// if the item is already selected then remove it
							selList.remove(Integer.valueOf(arg1));
						}

						if (banyak >= 6) {
							//alert
							Toast.makeText(context,"Anda Tidak Dapat Memilih Obat Lebih Dari 5 ",Toast.LENGTH_LONG).show();
							((AlertDialog) arg0).getListView().setItemChecked(arg1, false);
							banyak--;
							// ((AlertDialog)arg0).getButton(DialogInterface.BUTTON_POSITIVE).setVisibility(View.INVISIBLE);
						}
					}
				});

		builder.setNegativeButton("CANCEL",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

		builder.setPositiveButton("SIMPAN",new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						msg = "";
						for (int i = 0; i < selList.size(); i++) {
							msg = msg + arv[selList.get(i)] + "\n";
						}
						
						String msg_list[] = msg.split("\n");
						int lngth = msg_list.length;
						
						ArrayList<String> data_dosis = db.getAllDataARVDose();		
						item_dosis = data_dosis.toArray(new String[data_dosis.size()]);
						ArrayList<String> data_dosis_pagi = db.getDataDosisPagi();
						item_dosis_pagi =  data_dosis_pagi.toArray(new String[data_dosis_pagi.size()]);
						ArrayList<String> data_dosis_malam = db.getDataDosisMalam();
						item_dosis_malam =  data_dosis_malam.toArray(new String[data_dosis_malam.size()]);
						
						LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
						popupView = layoutInflater.inflate(R.layout.popup, null);
						final PopupWindow popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
						
						Button btnDismiss = (Button) popupView.findViewById(R.id.dismiss);
						TextView tv0 = (TextView) popupView.findViewById(R.id.tv1);
						TextView tv1 = (TextView) popupView.findViewById(R.id.tv2);
						TextView tv2 = (TextView) popupView.findViewById(R.id.tv3);
						TextView tv3 = (TextView) popupView.findViewById(R.id.tv4);
						TextView tv4 = (TextView) popupView.findViewById(R.id.tv5);

						LinearLayout linARV1 = (LinearLayout) popupView.findViewById(R.id.linARV1);
						LinearLayout linARV2 = (LinearLayout) popupView.findViewById(R.id.linARV2);
						LinearLayout linARV3 = (LinearLayout) popupView.findViewById(R.id.linARV3);
						LinearLayout linARV4 = (LinearLayout) popupView.findViewById(R.id.linARV4);
						LinearLayout linARV5 = (LinearLayout) popupView.findViewById(R.id.linARV5);
						
						

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

						final Spinner popupSpinnerDosisPagi1 = (Spinner) popupView.findViewById(R.id.spinDosisPagi1);
						setSpin(item_dosis_pagi, popupSpinnerDosisPagi1);
						// popupSpinnerDosisPagi1.setOnItemSelectedListener(this);

						// dosis malam1
						final Spinner popupSpinnerDosisMalam1 = (Spinner) popupView.findViewById(R.id.spinDosisMalam1);
						setSpin(item_dosis_malam, popupSpinnerDosisMalam1);

						// dosis pagi2

						final Spinner popupSpinnerDosisPagi2 = (Spinner) popupView.findViewById(R.id.spinDosisPagi2);
						setSpin(item_dosis_pagi, popupSpinnerDosisPagi2);

						// dosis malam2
						final Spinner popupSpinnerDosisMalam2 = (Spinner) popupView.findViewById(R.id.spinDosisMalam2);
						setSpin(item_dosis_malam, popupSpinnerDosisMalam2);

						// dosis pagi3

						final Spinner popupSpinnerDosisPagi3 = (Spinner) popupView.findViewById(R.id.spinDosisPagi3);
						setSpin(item_dosis_pagi, popupSpinnerDosisPagi3);

						// dosis malam3
						final Spinner popupSpinnerDosisMalam3 = (Spinner) popupView.findViewById(R.id.spinDosisMalam3);
						setSpin(item_dosis_malam, popupSpinnerDosisMalam3);

						// dosis pagi4
						final Spinner popupSpinnerDosisPagi4 = (Spinner) popupView.findViewById(R.id.spinDosisPagi4);
						setSpin(item_dosis_pagi, popupSpinnerDosisPagi4);

						// dosis malam4
						final Spinner popupSpinnerDosisMalam4 = (Spinner) popupView.findViewById(R.id.spinDosisMalam4);
						setSpin(item_dosis_malam, popupSpinnerDosisMalam4);

						// dosis pagi5

						final Spinner popupSpinnerDosisPagi5 = (Spinner) popupView.findViewById(R.id.spinDosisPagi5);
						setSpin(item_dosis_pagi, popupSpinnerDosisPagi5);

						// dosis malam5
						final Spinner popupSpinnerDosisMalam5 = (Spinner) popupView.findViewById(R.id.spinDosisMalam5);
						setSpin(item_dosis_malam, popupSpinnerDosisMalam5);

						btnDismiss.setOnClickListener(new Button.OnClickListener() {
									@Override
									public void onClick(View v) {
										popupWindow.dismiss();
										
										String msg_list[] = msg.split("\n");
										// linear layout from spinner
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

										arv_anak.clear();
										//set into text view and store in to object
										if (banyak == 1) {
											linArv1.setVisibility(View.VISIBLE);
											arv1.setText(msg_list[0]);
											dos_pagi1.setText(popupSpinnerDosisPagi1.getSelectedItem().toString());
											dos_malam1.setText(popupSpinnerDosisMalam1.getSelectedItem().toString());
											arv_model1.SetARVTypeID(db.getIdTypeARV(msg_list[0]));
											arv_model1.SetARVDoseID(db.getIdDosisARV(popupSpinnerDosisPagi1.getSelectedItem().toString(), "Pagi"));
											arv_model1.SetARVDoseID1(db.getIdDosisARV(popupSpinnerDosisMalam1.getSelectedItem().toString(), "Malam"));
											arv_anak.add(arv_model1);
											
										} else if (banyak == 2) {
											linArv1.setVisibility(View.VISIBLE);
											linArv2.setVisibility(View.VISIBLE);
											arv1.setText(msg_list[0]);arv_model1.SetARVTypeID(db.getIdTypeARV(msg_list[0]));
											arv2.setText(msg_list[1]);arv_model2.SetARVTypeID(db.getIdTypeARV(msg_list[1]));
											dos_pagi1.setText(popupSpinnerDosisPagi1.getSelectedItem().toString());arv_model1.SetARVDoseID(db.getIdDosisARV(popupSpinnerDosisPagi1.getSelectedItem().toString(), "Pagi"));
											dos_malam1.setText(popupSpinnerDosisMalam1.getSelectedItem().toString());arv_model1.SetARVDoseID1(db.getIdDosisARV(popupSpinnerDosisMalam1.getSelectedItem().toString(), "Malam"));
											dos_pagi2.setText(popupSpinnerDosisPagi2.getSelectedItem().toString());arv_model2.SetARVDoseID(db.getIdDosisARV(popupSpinnerDosisPagi2.getSelectedItem().toString(), "Pagi"));
											dos_malam2.setText(popupSpinnerDosisMalam2.getSelectedItem().toString());arv_model2.SetARVDoseID1(db.getIdDosisARV(popupSpinnerDosisMalam2.getSelectedItem().toString(), "Malam"));
											arv_anak.add(arv_model1);arv_anak.add(arv_model2);
											

										} else if (banyak == 3) {
											linArv1.setVisibility(View.VISIBLE);
											linArv2.setVisibility(View.VISIBLE);
											linArv3.setVisibility(View.VISIBLE);
											arv1.setText(msg_list[0]);arv_model1.SetARVTypeID(db.getIdTypeARV(msg_list[0]));
											arv2.setText(msg_list[1]);arv_model2.SetARVTypeID(db.getIdTypeARV(msg_list[1]));
											arv3.setText(msg_list[2]);arv_model3.SetARVTypeID(db.getIdTypeARV(msg_list[2]));
											dos_pagi1.setText(popupSpinnerDosisPagi1.getSelectedItem().toString());arv_model1.SetARVDoseID(db.getIdDosisARV(popupSpinnerDosisPagi1.getSelectedItem().toString(), "Pagi"));
											dos_malam1.setText(popupSpinnerDosisMalam1.getSelectedItem().toString());arv_model1.SetARVDoseID1(db.getIdDosisARV(popupSpinnerDosisMalam1.getSelectedItem().toString(), "Malam"));
											dos_pagi2.setText(popupSpinnerDosisPagi2.getSelectedItem().toString());arv_model2.SetARVDoseID(db.getIdDosisARV(popupSpinnerDosisPagi2.getSelectedItem().toString(), "Pagi"));
											dos_malam2.setText(popupSpinnerDosisMalam2.getSelectedItem().toString());arv_model2.SetARVDoseID1(db.getIdDosisARV(popupSpinnerDosisMalam2.getSelectedItem().toString(), "Malam"));
											dos_pagi3.setText(popupSpinnerDosisPagi3.getSelectedItem().toString());arv_model3.SetARVDoseID(db.getIdDosisARV(popupSpinnerDosisPagi3.getSelectedItem().toString(), "Pagi"));
											dos_malam3.setText(popupSpinnerDosisMalam3.getSelectedItem().toString());arv_model3.SetARVDoseID1(db.getIdDosisARV(popupSpinnerDosisMalam3.getSelectedItem().toString(), "Malam"));
											arv_anak.add(arv_model1);arv_anak.add(arv_model2);arv_anak.add(arv_model3);
											
										} else if (banyak == 4) {
											linArv1.setVisibility(View.VISIBLE);
											linArv2.setVisibility(View.VISIBLE);
											linArv3.setVisibility(View.VISIBLE);
											linArv4.setVisibility(View.VISIBLE);
											arv1.setText(msg_list[0]);arv_model1.SetARVTypeID(db.getIdTypeARV(msg_list[0]));
											arv2.setText(msg_list[1]);arv_model2.SetARVTypeID(db.getIdTypeARV(msg_list[1]));
											arv3.setText(msg_list[2]);arv_model3.SetARVTypeID(db.getIdTypeARV(msg_list[2]));
											arv4.setText(msg_list[3]);arv_model4.SetARVTypeID(db.getIdTypeARV(msg_list[3]));

											dos_pagi1.setText(popupSpinnerDosisPagi1.getSelectedItem().toString());arv_model1.SetARVDoseID(db.getIdDosisARV(popupSpinnerDosisPagi1.getSelectedItem().toString(), "Pagi"));
											dos_malam1.setText(popupSpinnerDosisMalam1.getSelectedItem().toString());arv_model1.SetARVDoseID1(db.getIdDosisARV(popupSpinnerDosisMalam1.getSelectedItem().toString(), "Malam"));
											dos_pagi2.setText(popupSpinnerDosisPagi2.getSelectedItem().toString());arv_model2.SetARVDoseID(db.getIdDosisARV(popupSpinnerDosisPagi2.getSelectedItem().toString(), "Pagi"));
											dos_malam2.setText(popupSpinnerDosisMalam2.getSelectedItem().toString());arv_model2.SetARVDoseID1(db.getIdDosisARV(popupSpinnerDosisMalam2.getSelectedItem().toString(), "Malam"));
											dos_pagi3.setText(popupSpinnerDosisPagi3.getSelectedItem().toString());arv_model3.SetARVDoseID(db.getIdDosisARV(popupSpinnerDosisPagi3.getSelectedItem().toString(), "Pagi"));
											dos_malam3.setText(popupSpinnerDosisMalam3.getSelectedItem().toString());arv_model3.SetARVDoseID1(db.getIdDosisARV(popupSpinnerDosisMalam3.getSelectedItem().toString(), "Malam"));
											dos_pagi4.setText(popupSpinnerDosisPagi4.getSelectedItem().toString());arv_model4.SetARVDoseID(db.getIdDosisARV(popupSpinnerDosisPagi4.getSelectedItem().toString(), "Pagi"));
											dos_malam4.setText(popupSpinnerDosisMalam4.getSelectedItem().toString());arv_model4.SetARVDoseID1(db.getIdDosisARV(popupSpinnerDosisMalam4.getSelectedItem().toString(), "Malam"));

											arv_anak.add(arv_model1);arv_anak.add(arv_model2);arv_anak.add(arv_model3);arv_anak.add(arv_model4);
										} else if (banyak == 5) {
											linArv1.setVisibility(View.VISIBLE);
											linArv2.setVisibility(View.VISIBLE);
											linArv3.setVisibility(View.VISIBLE);
											linArv4.setVisibility(View.VISIBLE);
											linArv5.setVisibility(View.VISIBLE);
											arv1.setText(msg_list[0]);arv_model1.SetARVTypeID(db.getIdTypeARV(msg_list[0]));
											arv2.setText(msg_list[1]);arv_model2.SetARVTypeID(db.getIdTypeARV(msg_list[1]));
											arv3.setText(msg_list[2]);arv_model3.SetARVTypeID(db.getIdTypeARV(msg_list[2]));
											arv4.setText(msg_list[3]);arv_model4.SetARVTypeID(db.getIdTypeARV(msg_list[3]));
											arv5.setText(msg_list[4]);arv_model5.SetARVTypeID(db.getIdTypeARV(msg_list[4]));
											dos_pagi1.setText(popupSpinnerDosisPagi1.getSelectedItem().toString());arv_model1.SetARVDoseID(db.getIdDosisARV(popupSpinnerDosisPagi1.getSelectedItem().toString(), "Pagi"));
											dos_malam1.setText(popupSpinnerDosisMalam1.getSelectedItem().toString());arv_model1.SetARVDoseID1(db.getIdDosisARV(popupSpinnerDosisMalam1.getSelectedItem().toString(), "Malam"));
											dos_pagi2.setText(popupSpinnerDosisPagi2.getSelectedItem().toString());arv_model2.SetARVDoseID(db.getIdDosisARV(popupSpinnerDosisPagi2.getSelectedItem().toString(), "Pagi"));
											dos_malam2.setText(popupSpinnerDosisMalam2.getSelectedItem().toString());arv_model2.SetARVDoseID1(db.getIdDosisARV(popupSpinnerDosisMalam2.getSelectedItem().toString(), "Malam"));
											dos_pagi3.setText(popupSpinnerDosisPagi3.getSelectedItem().toString());arv_model3.SetARVDoseID(db.getIdDosisARV(popupSpinnerDosisPagi3.getSelectedItem().toString(), "Pagi"));
											dos_malam3.setText(popupSpinnerDosisMalam3.getSelectedItem().toString());arv_model3.SetARVDoseID1(db.getIdDosisARV(popupSpinnerDosisMalam3.getSelectedItem().toString(), "Malam"));
											dos_pagi4.setText(popupSpinnerDosisPagi4.getSelectedItem().toString());arv_model4.SetARVDoseID(db.getIdDosisARV(popupSpinnerDosisPagi4.getSelectedItem().toString(), "Pagi"));
											dos_malam4.setText(popupSpinnerDosisMalam4.getSelectedItem().toString());arv_model4.SetARVDoseID1(db.getIdDosisARV(popupSpinnerDosisMalam4.getSelectedItem().toString(), "Malam"));
											dos_pagi5.setText(popupSpinnerDosisPagi5.getSelectedItem().toString());arv_model5.SetARVDoseID(db.getIdDosisARV(popupSpinnerDosisPagi5.getSelectedItem().toString(), "Pagi"));
											dos_malam5.setText(popupSpinnerDosisMalam5.getSelectedItem().toString());arv_model5.SetARVDoseID(db.getIdDosisARV(popupSpinnerDosisMalam5.getSelectedItem().toString(), "Malam"));
											
											arv_anak.add(arv_model1);arv_anak.add(arv_model2);arv_anak.add(arv_model3);arv_anak.add(arv_model4);arv_anak.add(arv_model5);
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
		bq_pos.setBackgroundDrawable(getResources().getDrawable(R.drawable.button));
		bq_pos.setTextColor(Color.WHITE);
		bq_neg.setBackgroundDrawable(getResources().getDrawable(R.drawable.button));
		bq_neg.setTextColor(Color.WHITE);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profil_anak, menu);
		return true;
	}
	
	//Get Device ID
	public String getUniqueID(){    
	    String AndroidDeviceId = "";
	    TelephonyManager mTelephony = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
	    if (mTelephony.getDeviceId() != null){
	        AndroidDeviceId = mTelephony.getDeviceId(); 
	    }else{
	         AndroidDeviceId = Secure.getString(getApplicationContext().getContentResolver(), Secure.ANDROID_ID); 
	    }
	    return AndroidDeviceId;
	}
	
	public String GetChildID()
	{
		String child_id;
		Integer current_id = db.GetSeq();
		child_id = getUniqueID() + "_"+(current_id+1);
		//update tabel seq
		db.UpdateSeq(""+current_id, ""+(current_id+1));
		return child_id;
		
	}

	// spinner
	// dosis pagi
	public void setSpin(String[] item, Spinner spin) {
		ArrayAdapter<String> adapter_dosis = new ArrayAdapter<String>(this,R.layout.spin, item);
		adapter_dosis.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(adapter_dosis);

	}

	int countSelectedItem;

	private void SimpanFasilitasArray(final String flag,final CharSequence[] fasilitas, final TextView tv) {
		// instance od table to get id facility
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("Pilih Fasilitas");

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
							Toast.makeText(context,"Anda Tidak Dapat Fasilitas Lebih Dari 2 ",Toast.LENGTH_LONG).show();
							((AlertDialog) arg0).getListView().setItemChecked(arg1, false);
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
						hasil_fasilitas_sementara.add(fasilitas[selList.get(i)]
								.toString());
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
		bq_pos.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.button));
		bq_pos.setTextColor(Color.WHITE);
		bq_neg.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.button));
		bq_neg.setTextColor(Color.WHITE);

	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn_back:
			Intent intent = new Intent(ProfilAnakActivity.this,ListAnakActivity.class);
			startActivity(intent);
			break;

		// case R.id.button_simpanprofil:
		// Intent intent4 = new Intent(ProfilAnakActivity.this,
		// ListAnakActivity.class);
		// startActivity(intent4);
		// break;

		case R.id.button_susu:
			selList.clear();
			final CharSequence[] susu = db
					.getAllBrandName("FA001");
			final String flag = "susu";
			SimpanFasilitasArray(flag, susu, tvSusu);

			break;

		case R.id.button_vitamin:
			selList.clear();
			final CharSequence[] vitamin = db
					.getAllBrandName("FA002");
			final String flag_vitamin = "vitamin";
			SimpanFasilitasArray(flag_vitamin, vitamin, tvVitamin);

			break;

		case R.id.button_popok:
			selList.clear();
			final CharSequence[] popok = db
					.getAllBrandName("FA003");
			final String flag_popok = "popok";
			SimpanFasilitasArray(flag_popok, popok, tvPopok);

			break;
		case R.id.button_tambah:
			// TODO Auto-generated method stub
			if (linPhone1.getVisibility() == View.VISIBLE) {
				linPhone2.setVisibility(View.VISIBLE);
			} else {
				linPhone1.setVisibility(View.VISIBLE);
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

	public Boolean validasi()
	 {
	  Boolean a = false;
	  if(txtName.getText().toString().equals(""))
	  {
	   Toast.makeText(getApplicationContext(),"Nama Anak Tidak Boleh Kosong"+txtName.getText(),Toast.LENGTH_SHORT).show();
	   a=false;
	  }
	  else if(txtDate.getText().toString().equals(""))
	  {
	   Toast.makeText(getApplicationContext(),"Tanggal Lahir Anak Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
	   a=false;
	  }
	  else if(rb_gender==null)
	  {
	   Toast.makeText(getApplicationContext(),"Jenis Kelamin Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
	   a=false;
	  }
	  else if(golongan_darah.getSelectedItem().toString().equals("-"))
	  {
	   Toast.makeText(getApplicationContext(),"Golongan Darah Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
	   a=false;
	  }
	  else
	  {
	   a= true;
	  }
	  return a;
	  
	 }
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
