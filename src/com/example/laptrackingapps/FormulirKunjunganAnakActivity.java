package com.example.laptrackingapps;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.example.databaselap.Database;
import com.example.modellap.ChildFacility_Model;
import com.example.modellap.Complaint_Model;
import com.example.modellap.Image_Model;
import com.example.modellap.Visit_Facility;
import com.example.modellap.Visit_Model;

import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class FormulirKunjunganAnakActivity extends Activity implements OnItemSelectedListener {
	// untuk foto anak (kamera)
	private static final int Image_take = 1;
	
	Database db;
	ImageView back, foto_anak1, foto_anak2, foto_anak3, foto_anak4, foto_anak5,foto_anak6;
	Button tambah_keluhan;
	LinearLayout tambahKeluhan, tambahKeluhan2;
	Button btn_foto1, btn_foto2, btn_foto3, btn_foto4, btn_foto5, btn_foto6;
	LinearLayout layout_tambahkeluhan, layout_keluhan, layout_status,layout_tindakan;
	LinearLayout button_lanjut;

	// GPSTracker class
	GPSTracker gps;

	// untuk tanggal ambil arv
	int hour, minute, mYear, mMonth, mDay;
	static final int TIME_DIALOG_ID = 0;
	static final int DATE_DIALOG_ID = 1;
	private EditText txtDate;
	Bitmap bitmap1, bitmap56;
	private String[] arrMonth = { "Januari", "Februari", "Maret", "April","Mei", "Juni", "Juli", "Agustus", "September", "Oktober","November", "Desember" };
	Bitmap thumbnail1, thumbnail;
	LinearLayout btn_kembali;
	String child_id;
	
	// Spinner jenis kunjungan
	Spinner jenis_kunjungan;
	// tb,bb,ll
	EditText tb, bb, ll;
	// catatan
	EditText note;
	// keluhan, status, tindakan
	Spinner keluhan1, keluhan2, keluhan3, status1, status2, status3;
	EditText tindakan1, tindakan2, tindakan3;

	// arraylist data keluhan dan status
	ArrayList<String> arr_keluhan, arr_status;
	//arr fasilitas
	ArrayList<ChildFacility_Model> arr_fasilitas = new ArrayList<ChildFacility_Model>();
	Visit_Model model_visit;
	ArrayAdapter<String> adptr_visit_type, adptr_keluhan1, adptr_keluhan2,adptr_keluhan3, adptr_status1, adptr_status2, adptr_status3;

	int spinner_position;
	ArrayList<Complaint_Model> complaints = new ArrayList<Complaint_Model>();
	ArrayList<Visit_Facility> facilities = new ArrayList<Visit_Facility>();
	ArrayList<Image_Model> photos = new ArrayList<Image_Model>();
	Visit_Facility facility1 = new Visit_Facility();
	Visit_Facility facility2 = new Visit_Facility();
	Visit_Facility facility3 = new Visit_Facility();
	Visit_Facility facility4 = new Visit_Facility();
	Visit_Facility facility5 = new Visit_Facility();
	Visit_Facility facility6 = new Visit_Facility();
	
	Complaint_Model complaint1 = new Complaint_Model();
	Complaint_Model complaint2 = new Complaint_Model();
	Complaint_Model complaint3 = new Complaint_Model();

	final Context context = this;
	Boolean foto1=false,foto2=false,foto3=false,foto4=false,foto5=false,foto6=false;
	
	TextView subfas1, subfas2, subfas3, subfas4, subfas5, subfas6;
	EditText et_subfas1, et_subfas2,et_subfas3,et_subfas4,et_subfas5,et_subfas6;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_formulir_kunjungan_anak);
		
		/***************************INSTANSIASI PROPERTI ***********************************/
		InstansiasiProperties();

		/********************************LISTENING INTENT ********************************/
		// get id child
		Intent i = getIntent();
		//Bundle extras = i.getExtras();
		Bundle extras = getIntent().getExtras();
		if(extras != null )
		{
			Log.e("Intent", "not null");
			if(extras.getString("id_anak_fka") != null)
			{
				child_id = i.getStringExtra("id_anak_fka");
				Log.e("[received from KA] ", " " + child_id);
			}
			else if(extras.getString("id_anak_fka1") != null)
			{
				child_id = i.getStringExtra("id_anak_fka1");
				Log.e("[received from KA] ", " " + child_id);
			}
		}
		else
		{
			Log.e("Intent","null");
		}

		/*********************SET FASILITAS ANAK************************************/
		SetFasilitasAnak();
		
		
		
		//------> tambah keluhan
		tambah_keluhan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// // TODO Auto-generated method stub

				if (tambahKeluhan.getVisibility() == View.VISIBLE) {
					tambahKeluhan2.setVisibility(View.VISIBLE);
				} else {
					tambahKeluhan.setVisibility(View.VISIBLE);
				}

				if (tambahKeluhan2.getVisibility() == View.VISIBLE) {
					tambah_keluhan.setVisibility(View.INVISIBLE);
				}
			}
		});
		
		//----------> foto anak
		btn_foto1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (getDrawableImage(foto_anak1) == bitmap1) {
					foto1 = true;
					image();
				} else {
					alertImage(foto_anak1,"1");
				}
			}
		});
		btn_foto2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (getDrawableImage(foto_anak2) == bitmap1) {
					foto2 = true;
					image();
				} else {
					alertImage(foto_anak2,"2");
				}
			}
		});
		btn_foto3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (getDrawableImage(foto_anak3) == bitmap1) {
					foto3 = true;
					image();
				} else {
					alertImage(foto_anak3,"3");
				}
			}
		});
		btn_foto4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (getDrawableImage(foto_anak4) == bitmap1) {
					foto4 = true;
					image();
				} else {
					alertImage(foto_anak4,"4");
				}
			}
		});
		btn_foto5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (getDrawableImage(foto_anak5) == bitmap1) {
					foto5 = true;
					image();
				} else {
					alertImage(foto_anak5,"5");
				}
			}
		});
		btn_foto6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (getDrawableImage(foto_anak6) == bitmap1) {
					foto6 = true;
					image();
				} else {
					alertImage(foto_anak6,"6");
				}
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
		
		/********************************UPDATING KUNJUNGAN*****************************************/
		if (i.getStringExtra("id_anak_fka1") != null) {
			
			child_id = i.getStringExtra("id_anak_fka1");
			Log.e("Child id from KA", "" + child_id);
			if (i.getParcelableExtra("Visit_Model_Update") != null) {
				model_visit = (Visit_Model) i.getParcelableExtra("Visit_Model_Update");
				spinner_position = adptr_visit_type.getPosition(db.getNameVisitType(model_visit.GetVisitTypeID()));
				jenis_kunjungan.setSelection(spinner_position);
				// tb
				tb.setText(model_visit.GetHeight());
				// bb
				bb.setText(model_visit.GetWeight());
				// ll
				ll.setText(model_visit.GetLILA());
				// catatan
				note.setText(model_visit.GetNote());
				// ambil obat
				txtDate.setText(model_visit.GetARVTaken());
				// keluhan dsb
				complaints = model_visit.GetComplaints();
				if (complaints.size() == 3) {
					tambahKeluhan2.setVisibility(View.VISIBLE);
					tambahKeluhan.setVisibility(View.VISIBLE);

					complaint1 = complaints.get(0);
					// keluhan
					spinner_position = adptr_keluhan1.getPosition(db.getNameComplaint(complaint1.GetKeluhan()));
					keluhan1.setSelection(spinner_position);
					// status
					spinner_position = adptr_status1.getPosition(db.getNameComplaintStatus(complaint1.GetStatusKeluhan()));
					status1.setSelection(spinner_position);
					// tindakan
					tindakan1.setText(complaint1.GetTindakan());

					complaint2 = complaints.get(1);
					// keluhan
					spinner_position = adptr_keluhan2.getPosition(db.getNameComplaint(complaint2.GetKeluhan()));
					keluhan2.setSelection(spinner_position);
					// status
					spinner_position = adptr_status2.getPosition(db.getNameComplaintStatus(complaint2.GetStatusKeluhan()));
					status2.setSelection(spinner_position);
					// tindakan
					tindakan2.setText(complaint2.GetTindakan());

					complaint3 = complaints.get(2);
					// keluhan
					spinner_position = adptr_keluhan3.getPosition(db.getNameComplaint(complaint3.GetKeluhan()));
					keluhan3.setSelection(spinner_position);
					// status
					spinner_position = adptr_status1.getPosition(db.getNameComplaintStatus(complaint3.GetStatusKeluhan()));
					status3.setSelection(spinner_position);
					// tindakan
					tindakan3.setText(complaint3.GetTindakan());
				} else if (complaints.size() == 2) {
					tambahKeluhan.setVisibility(View.VISIBLE);
					complaint1 = complaints.get(0);
					// keluhan
					spinner_position = adptr_keluhan1.getPosition(db.getNameComplaint(complaint1.GetKeluhan()));
					keluhan1.setSelection(spinner_position);
					// status
					spinner_position = adptr_status1.getPosition(db.getNameComplaintStatus(complaint1.GetStatusKeluhan()));
					status1.setSelection(spinner_position);
					// tindakan
					tindakan1.setText(complaint1.GetTindakan());

					complaint2 = complaints.get(1);
					// keluhan
					spinner_position = adptr_keluhan2.getPosition(db.getNameComplaint(complaint2.GetKeluhan()));
					keluhan2.setSelection(spinner_position);
					// status
					spinner_position = adptr_status2.getPosition(db.getNameComplaintStatus(complaint2.GetStatusKeluhan()));
					status2.setSelection(spinner_position);
					// tindakan
					tindakan2.setText(complaint2.GetTindakan());

				} else {
					complaint1 = complaints.get(0);
					// keluhan
					spinner_position = adptr_keluhan1.getPosition(db.getNameComplaint(complaint1.GetKeluhan()));
					keluhan1.setSelection(spinner_position);
					// status
					spinner_position = adptr_status1.getPosition(db.getNameComplaintStatus(complaint1.GetStatusKeluhan()));
					status1.setSelection(spinner_position);
					// tindakan
					tindakan1.setText(complaint1.GetTindakan());
				}

				for (int a = 0; a < complaints.size(); a++) {

					if (a == 0) {
						complaint1 = complaints.get(0);
						// keluhan
						spinner_position = adptr_keluhan1.getPosition(db.getNameComplaint(complaint1.GetKeluhan()));
						keluhan1.setSelection(spinner_position);
						// status
						spinner_position = adptr_status1.getPosition(db.getNameComplaintStatus(complaint1.GetStatusKeluhan()));
						status1.setSelection(spinner_position);
						// tindakan
						tindakan1.setText(complaint1.GetTindakan());
					} else if (a == 1) {
						complaint2 = complaints.get(1);
						// keluhan
						spinner_position = adptr_keluhan2.getPosition(db.getNameComplaint(complaint2.GetKeluhan()));
						keluhan2.setSelection(spinner_position);
						// status
						spinner_position = adptr_status2.getPosition(db.getNameComplaintStatus(complaint2.GetStatusKeluhan()));
						status2.setSelection(spinner_position);
						// tindakan
						tindakan2.setText(complaint2.GetTindakan());
					} else {
						complaint3 = complaints.get(2);
						// keluhan
						spinner_position = adptr_keluhan3.getPosition(db.getNameComplaint(complaint3.GetKeluhan()));
						keluhan3.setSelection(spinner_position);
						// status
						spinner_position = adptr_status1.getPosition(db.getNameComplaintStatus(complaint3.GetStatusKeluhan()));
						status3.setSelection(spinner_position);
						// tindakan
						tindakan3.setText(complaint3.GetTindakan());
					}
				}
			}

			// remove the array list complaint model
			complaints.clear();
//			//set photo
			photos = db.GetAllPathFoto(model_visit.GetVisitDate(), model_visit.GetChildID());
			Log.e("size photo", ""+photos.size());
			photos = model_visit.GetPhotos();
			for(Image_Model img : photos)
			{
				if(img.GetImage_type_id().equals("2"))
				{
					continue;
				}
				else
				{
					File image = new File(img.GetImage_path());
					BitmapFactory.Options bmOptions = new BitmapFactory.Options();
					Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(),bmOptions);
					bitmap = Bitmap.createScaledBitmap(bitmap, 132, 105, true);
					
					if(getDrawableImage(foto_anak1) == bitmap1)
					{
						foto_anak1.setImageBitmap(bitmap);
						img_1 = new Image_Model(img.GetImage_name(),img.GetImage_date(),img.GetImage_id(),img.GetImage_path(),img.GetImage_server_path(),img.GetImage_type_id(),img.GetImage_longitude(),img.GetImage_latitude());
					}
					else if(getDrawableImage(foto_anak2) == bitmap1)
					{
						foto_anak2.setImageBitmap(bitmap); 
						img_2 = new Image_Model(img.GetImage_name(),img.GetImage_date(),img.GetImage_id(),img.GetImage_path(),img.GetImage_server_path(),img.GetImage_type_id(),img.GetImage_longitude(),img.GetImage_latitude());
					}
					else if(getDrawableImage(foto_anak3) == bitmap1)
					{
						foto_anak3.setImageBitmap(bitmap);
						img_3 = new Image_Model(img.GetImage_name(),img.GetImage_date(),img.GetImage_id(),img.GetImage_path(),img.GetImage_server_path(),img.GetImage_type_id(),img.GetImage_longitude(),img.GetImage_latitude());
					}
					else if(getDrawableImage(foto_anak4) == bitmap1)
					{
						foto_anak4.setImageBitmap(bitmap);
						img_4 = new Image_Model(img.GetImage_name(),img.GetImage_date(),img.GetImage_id(),img.GetImage_path(),img.GetImage_server_path(),img.GetImage_type_id(),img.GetImage_longitude(),img.GetImage_latitude());
					}
					else if(getDrawableImage(foto_anak5) == bitmap1)
					{
						foto_anak5.setImageBitmap(bitmap);
						img_5 = new Image_Model(img.GetImage_name(),img.GetImage_date(),img.GetImage_id(),img.GetImage_path(),img.GetImage_server_path(),img.GetImage_type_id(),img.GetImage_longitude(),img.GetImage_latitude());
					}
					else if(getDrawableImage(foto_anak6) == bitmap1)
					{
						foto_anak6.setImageBitmap(bitmap);
						img_6 = new Image_Model(img.GetImage_name(),img.GetImage_date(),img.GetImage_id(),img.GetImage_path(),img.GetImage_server_path(),img.GetImage_type_id(),img.GetImage_longitude(),img.GetImage_latitude());
					}
				}
			}
		}
		/************************************************************************************************************************/

	}
	
	public void InstansiasiProperties()
	{
		//database
		db = new Database(this);
		//model
		model_visit = new Visit_Model();
		//inisialisasi properti fasilitas
		subfas1 = (TextView) findViewById(R.id.tvSusu1);
		subfas2 = (TextView) findViewById(R.id.tvSusub);
		subfas3 = (TextView) findViewById(R.id.tvVitamin1);
		subfas4 = (TextView) findViewById(R.id.tvVitaminb);
		subfas5 = (TextView) findViewById(R.id.tvPopok1);
		subfas6 = (TextView) findViewById(R.id.tvPopokb);
		
		et_subfas1 = (EditText) findViewById(R.id.etSusu1);
		et_subfas2 = (EditText) findViewById(R.id.etSusub);
		et_subfas3 = (EditText) findViewById(R.id.etVitamin1);
		et_subfas4 = (EditText) findViewById(R.id.etVitaminb);
		et_subfas5 = (EditText) findViewById(R.id.etPopok1);
		et_subfas6 = (EditText) findViewById(R.id.etPopokb);
			
		txtDate = (EditText) findViewById(R.id.edittext_ttl);
		button_lanjut = (LinearLayout) findViewById(R.id.button_lanjut);
		back = (ImageView) findViewById(R.id.btn_back);
		// spinner
		jenis_kunjungan = (Spinner) findViewById(R.id.spinner_jeniskunjungan);
		// tinggi badan, berat badan, lingkar lengan
		tb = (EditText) findViewById(R.id.editText_tinggibadan);
		bb = (EditText) findViewById(R.id.editText_beratbadan);
		ll = (EditText) findViewById(R.id.editText_lingkarlengan);

		keluhan1 = (Spinner) findViewById(R.id.spinner_keluhan);
		keluhan2 = (Spinner) findViewById(R.id.spinner_keluhan1);
		keluhan3 = (Spinner) findViewById(R.id.spinner_keluhan2);

		status1 = (Spinner) findViewById(R.id.spinner_status);
		status2 = (Spinner) findViewById(R.id.spinner_status1);
		status3 = (Spinner) findViewById(R.id.spinner_status2);

		tindakan1 = (EditText) findViewById(R.id.etTindakan);
		tindakan2 = (EditText) findViewById(R.id.etTindakan1);
		tindakan3 = (EditText) findViewById(R.id.etTindakan2);
		
		// note
		note = (EditText) findViewById(R.id.editText_catatan);
		btn_kembali = (LinearLayout) findViewById(R.id.button_kembali);
		layout_tambahkeluhan = (LinearLayout) findViewById(R.id.linearlayout_tambahkeluhan);
		tambah_keluhan = (Button) findViewById(R.id.button_tambahkeluhan);
		
		tambahKeluhan = (LinearLayout) findViewById(R.id.tambahKeluhan);
		tambahKeluhan2 = (LinearLayout) findViewById(R.id.tambahKeluhan2);
		
		foto_anak1 = (ImageView) findViewById(R.id.foto_anak1);
		foto_anak2 = (ImageView) findViewById(R.id.foto_anak2);
		foto_anak3 = (ImageView) findViewById(R.id.foto_anak3);
		foto_anak4 = (ImageView) findViewById(R.id.foto_anak4);
		foto_anak5 = (ImageView) findViewById(R.id.foto_anak5);
		foto_anak6 = (ImageView) findViewById(R.id.foto_anak6);

		bitmap1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.new_button_camera);
		foto_anak1.setImageBitmap(bitmap1);
		foto_anak2.setImageBitmap(bitmap1);
		foto_anak3.setImageBitmap(bitmap1);
		foto_anak4.setImageBitmap(bitmap1);
		foto_anak5.setImageBitmap(bitmap1);
		foto_anak6.setImageBitmap(bitmap1);

		btn_foto1 = (Button) findViewById(R.id.btn_foto1);
		btn_foto2 = (Button) findViewById(R.id.btn_foto2);
		btn_foto3 = (Button) findViewById(R.id.btn_foto3);
		btn_foto4 = (Button) findViewById(R.id.btn_foto4);
		btn_foto5 = (Button) findViewById(R.id.btn_foto5);
		btn_foto6 = (Button) findViewById(R.id.btn_foto6);
		
		// spinner visit type
				ArrayList<String> data_visit = db.getDataVisitType();
				jenis_kunjungan.setOnItemSelectedListener(this);
				adptr_visit_type = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, data_visit);
				adptr_visit_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				jenis_kunjungan.setAdapter(adptr_visit_type);
				// InitiateSpinner(data_visit, jenis_kunjungan);

				// keluhan, status, tindakan
				/*************************************/
				arr_keluhan = db.getDataComplaint();
				arr_status = db.getDataComplaintStatus();

				// 1
				adptr_keluhan1 = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, arr_keluhan);
				adptr_keluhan1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				keluhan1.setAdapter(adptr_keluhan1);
				// 2
				adptr_keluhan2 = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, arr_keluhan);
				adptr_keluhan2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				keluhan2.setAdapter(adptr_keluhan2);
				// 3
				adptr_keluhan3 = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, arr_keluhan);
				adptr_keluhan3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				keluhan3.setAdapter(adptr_keluhan3);

				// sts 1
				adptr_status1 = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, arr_status);
				adptr_status1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				status1.setAdapter(adptr_status1);
				// sts 2
				adptr_status2 = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, arr_status);
				adptr_status2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				status2.setAdapter(adptr_status2);

				// sts 3
				adptr_status3 = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, arr_status);
				adptr_status3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				status3.setAdapter(adptr_status3);
				
	}
	
	public void SetFasilitasAnak()
	{
		//fasilitas anak
		
		arr_fasilitas = db.getSemuaFasilitasAnak(child_id);
		Log.e("Jumlah Fasilitas Anak", ""+arr_fasilitas.size());
		
		for(ChildFacility_Model model : arr_fasilitas)
		{
			if(model.getFacility_id().equals("FA001"))
			{
				if(!subfas1.getText().toString().equals("-"))
				{
					subfas2.setText(db.getNameCostFacility(model.getFacility_cost_id()));
				}
				else
				{
					subfas1.setText(db.getNameCostFacility(model.getFacility_cost_id()));
				}
				
			}
			if(model.getFacility_id().equals("FA002"))
			{
				if(!subfas3.getText().toString().equals("-"))
				{
					subfas4.setText(db.getNameCostFacility(model.getFacility_cost_id()));
				}
				else
				{
					subfas3.setText(db.getNameCostFacility(model.getFacility_cost_id()));
				}
			}
			if(model.getFacility_id().equals("FA003"))
			{
				if(!subfas5.getText().toString().equals("-"))
				{
					subfas6.setText(db.getNameCostFacility(model.getFacility_cost_id()));
				}
				else
				{
					subfas5.setText(db.getNameCostFacility(model.getFacility_cost_id()));
				}
			}
			Log.e("Displaying facility",""+"id anak "+model.getChild_id()+" fasilitas id "+model.getFacility_id()+" fasilitas cost id "+model.getFacility_cost_id());
		}
	}
	//to validate when filling the form
	public Boolean validate()
	{
		
		if(jenis_kunjungan.getSelectedItem().toString().equals("-"))
		{
			
			Toast.makeText(getApplicationContext(), "Anda harus memilih tipe kunjungan ", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(tb.getText().toString().equals(""))
		{
			Toast.makeText(getApplicationContext(), "Silahkan isi tinggi badan Anak", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(bb.getText().toString().equals(""))
		{
			Toast.makeText(getApplicationContext(), "Silahkan isi berat badan Anak", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(ll.getText().toString().equals(""))
		{
			Toast.makeText(getApplicationContext(), "Silahkan isi lingkar lengan Anak", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(!keluhan1.getSelectedItem().toString().equals("-") && !keluhan2.getSelectedItem().toString().equals("-") && keluhan1.getSelectedItem().toString().equals(keluhan2.getSelectedItem().toString()))
		{
			Toast.makeText(getApplicationContext(), "Anda tidak boleh memilih keluhan yang sama (Keluhan 1 & 2)", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(!keluhan1.getSelectedItem().toString().equals("-") && !keluhan3.getSelectedItem().toString().equals("-") && keluhan1.getSelectedItem().toString().equals(keluhan3.getSelectedItem().toString()))
		{
			Toast.makeText(getApplicationContext(), "Anda tidak boleh memilih keluhan yang sama (Keluhan 1 & 3)", Toast.LENGTH_SHORT).show();
			return false;
		}
		
		if(!keluhan2.getSelectedItem().toString().equals("-") && !keluhan3.getSelectedItem().toString().equals("-") && keluhan2.getSelectedItem().toString().equals(keluhan3.getSelectedItem().toString()) )
		{
			Toast.makeText(getApplicationContext(), "Anda tidak boleh memilih keluhan yang sama (Keluhan 2 & 3)", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(keluhan1.getSelectedItem().toString().equals("-") && keluhan2.getSelectedItem().toString().equals("-") && keluhan3.getSelectedItem().toString().equals("-"))
		{
			
			Toast.makeText(getApplicationContext(), "Anda harus memilih minimal satu keluhan", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(count_foto < 2)
		{
			Toast.makeText(getApplicationContext(), "Foto Anak minimal 2", Toast.LENGTH_SHORT).show();
			Toast.makeText(getApplicationContext(), "Silahkan ambil gambar anak kembali", Toast.LENGTH_SHORT).show();
			return false;
		}
		
		else 
		{
			return true;
		}
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.formulir_kunjungan_anak, menu);
		return true;
	}

	int count_foto = 0;
	
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn_back:
			Intent intent3 = new Intent(FormulirKunjunganAnakActivity.this,KunjunganActivity.class);
			Log.e("[back to KA] ",""+child_id);
			intent3.putExtra("id_anak_k", child_id);
			startActivity(intent3);
			break;

		case R.id.button_lanjut:
			/**********************LOG********************************/
			Log.i("visit type id",""+ db.getIdVisitType(jenis_kunjungan.getSelectedItem().toString()));
			Log.i("tb bb ll", "" + tb.getText().toString() + "- "+ bb.getText().toString() + "- " + ll.getText().toString());
			Log.i("note", "" + note.getText().toString());
			Log.i("tanggal lahir", "" + txtDate.getText().toString());
			Log.i("***************************", "");
			Log.i("keluhan1", "" + keluhan1.getSelectedItem().toString());
			Log.i("keluhan2", "" + keluhan2.getSelectedItem().toString());
			Log.i("keluhan3", "" + keluhan3.getSelectedItem().toString());
			Log.i("***************************", "");
			Log.i("status1", "" + status1.getSelectedItem().toString());
			Log.i("status2", "" + status2.getSelectedItem().toString());
			Log.i("status3", "" + status3.getSelectedItem().toString());
			Log.i("***************************", "");
			Log.i("tindakan1", "" + tindakan1.getText().toString());
			Log.i("tindakan2", "" + tindakan2.getText().toString());
			Log.i("tindakan3", "" + tindakan3.getText().toString());
			Log.e("Sending intent into fk_env", "" + child_id);
			
			//inisialisasi kembali arr keluhan
			complaints.clear();
			
			// save into object first then send intent
			model_visit.setChildID(child_id);
			model_visit.setVisitTypeID(db.getIdVisitType(jenis_kunjungan.getSelectedItem().toString()));
			model_visit.setHeight(tb.getText().toString());
			model_visit.setWeight(bb.getText().toString());
			model_visit.setLILA(ll.getText().toString());
			model_visit.setNote(note.getText().toString());
			model_visit.setARVTaken(txtDate.getText().toString());
			
			// save the complaints
			
			complaint1.SetKeluhan(db.getIdComplaint(keluhan1.getSelectedItem().toString()));
			complaint1.SetStatusKeluhan(db.getIdComplaintStatus(status1.getSelectedItem().toString()));
			complaint1.SetTindakan(tindakan1.getText().toString());
			if (!keluhan1.getSelectedItem().toString().equals("-") && !status1.getSelectedItem().toString().equals("-")) {
				complaints.add(complaint1);
			}

			complaint2.SetKeluhan(db.getIdComplaint(keluhan2.getSelectedItem().toString()));
			complaint2.SetStatusKeluhan(db.getIdComplaintStatus(status2.getSelectedItem().toString()));
			complaint2.SetTindakan(tindakan2.getText().toString());
			if (!keluhan2.getSelectedItem().toString().equals("-") && !status2.getSelectedItem().toString().equals("-")) {
				complaints.add(complaint2);
			}

			complaint3.SetKeluhan(db.getIdComplaint(keluhan3.getSelectedItem().toString()));
			complaint3.SetStatusKeluhan(db.getIdComplaintStatus(status3.getSelectedItem().toString()));
			complaint3.SetTindakan(tindakan3.getText().toString());
			if (!keluhan3.getSelectedItem().toString().equals("-") && !status3.getSelectedItem().toString().equals("-")) {
				complaints.add(complaint3);
			}
			for (Complaint_Model a : complaints) {
				Log.e("keluhan status tindakan","keluhan " + a.GetKeluhan() + " status"+ a.GetStatusKeluhan() + " tindakan"+ a.GetTindakan());
			}
			
			model_visit.setComplaints(complaints);
			//save facilities
			//migrasi dari tabel child_facility ke visit_facility
			for(ChildFacility_Model model : arr_fasilitas)
			{
				if(model.getFacility_id().equals("FA001"))
				{
					if(facility1.getFacility_id() != null)
					{
						facility2.setChild_id(child_id);
						facility2.setFacility_id(model.getFacility_id());
						facility2.setFacility_cost_id(model.getFacility_cost_id());
						facility2.SetFacilityQty(et_subfas2.getText().toString());
						facility2.SetVisitDate(GetTimeNow());
						facilities.add(facility2);//--> arraylist
					}
					else
					{
						facility1.setChild_id(child_id);
						facility1.setFacility_id(model.getFacility_id());
						facility1.setFacility_cost_id(model.getFacility_cost_id());
						facility1.SetFacilityQty(et_subfas1.getText().toString());
						facility1.SetVisitDate(GetTimeNow());
						facilities.add(facility1);//--> arraylist
					}
					
				}
				if(model.getFacility_id().equals("FA002"))
				{
					if(facility3.getFacility_id() != null)
					{
						facility4.setChild_id(child_id);
						facility4.setFacility_id(model.getFacility_id());
						facility4.setFacility_cost_id(model.getFacility_cost_id());
						facility4.SetFacilityQty(et_subfas4.getText().toString());
						facility4.SetVisitDate(GetTimeNow());
						facilities.add(facility4);//--> arraylist
					}
					else
					{
						facility3.setChild_id(child_id);
						facility3.setFacility_id(model.getFacility_id());
						facility3.setFacility_cost_id(model.getFacility_cost_id());
						facility3.SetFacilityQty(et_subfas3.getText().toString());
						facility3.SetVisitDate(GetTimeNow());
						facilities.add(facility3);//--> arraylist
					}
					
				}
				if(model.getFacility_id().equals("FA003"))
				{
					if(facility5.getFacility_id() != null)
					{
						facility6.setChild_id(child_id);
						facility6.setFacility_id(model.getFacility_id());
						facility6.setFacility_cost_id(model.getFacility_cost_id());
						facility6.SetFacilityQty(et_subfas6.getText().toString());
						facility6.SetVisitDate(GetTimeNow());
						facilities.add(facility6);//--> arraylist
					}
					else
					{
						facility5.setChild_id(child_id);
						facility5.setFacility_id(model.getFacility_id());
						facility5.setFacility_cost_id(model.getFacility_cost_id());
						facility5.SetFacilityQty(et_subfas5.getText().toString());
						facility5.SetVisitDate(GetTimeNow());
						facilities.add(facility5);//--> arraylist
					}
				}
			}
			
			model_visit.setFasilitasKunjungan(facilities);
			
			/*************************************************/
			// save images
			
			if(img_1 != null)
			{
				if(img_1.GetImage_name() != null)
				{
					image_anak.add(img_1);
				}
			}
			if(img_2 != null)
			{
				if(img_2.GetImage_name() != null)
				{
					image_anak.add(img_2);
				}
			}
			if(img_3 != null)
			{
				if(img_3.GetImage_name() != null)
				{
					image_anak.add(img_3);
				}
			}
			if(img_4 != null)
			{
				if(img_4.GetImage_name() != null)
				{
					image_anak.add(img_4);
				}
			}
			if(img_5 != null)
			{
				if(img_5.GetImage_name() != null)
				{
					image_anak.add(img_5);
				}
			}
			if(img_6 != null)
			{
				if(img_6.GetImage_name() != null)
				{
					image_anak.add(img_6);
				}
			}
			count_foto = image_anak.size();
			
			model_visit.setPhotos(image_anak);
			Log.e("Size photo anak sent",""+image_anak.size());
			
			//----------------------> validate
			Boolean valid = validate();
			if(valid == true)
			{
				Intent intent_kunjungan_env = new Intent(this,FormulirKunjunganRumahActivity.class);
				
				//-----> send the activity 
				Intent i = getIntent();
				if(i.getStringExtra("id_anak_fka1") != null)
				{
					Log.e("[Sending to FKRA] Updating Kjgn","id anak "+i.getStringExtra("id_anak_fka1"));
					intent_kunjungan_env.putExtra("id_update", ""+child_id);
					intent_kunjungan_env.putExtra("Object_VisitModel", model_visit);
					startActivity(intent_kunjungan_env);
					finish();
					
				}
				else
				{
					Log.e("[Sending to FKR] Creating Kjgn", ""+child_id);
					intent_kunjungan_env.putExtra("id", ""+child_id);
					intent_kunjungan_env.putExtra("Object_VisitModel", model_visit);
					startActivity(intent_kunjungan_env);
					finish();
				}
			}
			else
			{
				
			}

			
			break;

		default:
			break;
		}
	}
	
//	public void Keluhan(Spinner Keluhan)
//	{
//		Keluhan.setOnItemSelectedListener(new OnItemSelectedListener() {
//			@Override
//			public void onItemSelected(AdapterView<?> arg0, View view,int position, long id) {
//				String sKotaMadya = db.getIdDistrict(item_kotamadya[position]);
//				if (position == 0) {
//				} 
//				else {
//				}
//				StatusKeluhan
//			}
//			
//			@Override
//			public void onNothingSelected(AdapterView<?> arg0) {
//			}});
//	}
//	public void StatusKeluhan(String StatusKeluhan)
//	{
//		
//	}
	
//	public void Kecamatan(String id_kotamadya, int flag) {
//		// flag => to check kecamatan is child address or school address
//		// 1 = child address
//		arr_kecamatan = db.getAllNameSubDistrict(id_kotamadya);
//
//		kecAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.spin, arr_kecamatan);
//
//		if (flag == 1) {
//
//			spinner_kecamatan.setOnItemSelectedListener(this);
//			spinner_kecamatan.setAdapter(kecAdapter);
//			Log.e("kecamatan", "" + spinner_kecamatan.getSelectedItem());
//		} else {
//
//			spinner_kecamatansekolah.setOnItemSelectedListener(this);
//			spinner_kecamatansekolah.setAdapter(kecAdapter);
//			Log.e("kecamatan", "" + spinner_kecamatansekolah.getSelectedItem());
//		}
//
//	}
	
//	public void Kotamadya(Spinner wilayah, final int flag) {
//		wilayah.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//			@Override
//			public void onItemSelected(AdapterView<?> arg0, View view,
//					int position, long id) {
//				String sKotaMadya = db.getIdDistrict(item_kotamadya[position]);
//				if (position == 0) {
//
//				} else {
//
//				}
//				// // check for the spinner of childs address or school adress
//				if (flag == 1) {
//					Kecamatan(sKotaMadya, 1);
//				} else {
//					Kecamatan(sKotaMadya, 2);
//				}
//
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> arg0) {
//
//			}
//		});
//	}

	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
					mDay);
		}
		return null;
	}

	// untuk tanggal ambil arv
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

	// untuk tanggal ambil arv
	private static String LPad(String schar, String spad, int len) {
		String sret = schar;
		for (int i = sret.length(); i < len; i++) {
			sret = spad + sret;
		}
		return new String(sret);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}
	
	public String GetTimeNow() {
		Calendar c = Calendar.getInstance();

		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		return df.format(c.getTime());
	}
	
	int count;

	public String image() {	  
		  // create class object
		  gps = new GPSTracker(FormulirKunjunganAnakActivity.this);
		  double latitude=0;
		  double longitude=0;
		  // check if GPS enabled
		  if (gps.canGetLocation()) {
			   Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			   startActivityForResult(intent, 1);
			   latitude = gps.getLatitude();
			   longitude = gps.getLongitude();
			   
			  // Toast.makeText(getApplicationContext(),"Lokasi Anda - \nLatitude : " + latitude + "\nLongitude : "+ longitude, Toast.LENGTH_LONG).show();
			   
		  }else {
				gps.showSettingsAlert();
		  }
		  return Double.toString(longitude)+"~"+Double.toString(latitude);
	}

	ArrayList<Image_Model> image_anak = new ArrayList<Image_Model>();
	Image_Model img_1, img_2, img_3, img_4, img_5, img_6;
		 
		 @Override
		 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		  super.onActivityResult(requestCode, resultCode, data);
		  if (resultCode == RESULT_OK) {
		   if (requestCode == 1) {
		       thumbnail = (Bitmap) data.getExtras().get("data");
		       thumbnail = Bitmap.createScaledBitmap(thumbnail, 132, 105, true);
		       thumbnail1 = (Bitmap) data.getExtras().get("data");
		       thumbnail1 = Bitmap.createScaledBitmap(thumbnail1, 258, 150,true);
		       ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		       thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
		       thumbnail1.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

		       File destination = new File(Environment.getExternalStorageDirectory(),System.currentTimeMillis() + ".jpg");
		       FileOutputStream fo;
		       //Uri selectedImage = data.getData();
		       String[] filePathColumn = {MediaStore.Images.Media.DATA};
		       //Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
		       final String imageOrderBy = MediaStore.Images.Media._ID+ " DESC";
		       Cursor cursor = managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,filePathColumn, null, null, imageOrderBy);            
		       cursor.moveToFirst();
		       int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
		       String filePath = cursor.getString(columnIndex);
		       Log.v("log","filePath is : "+filePath);
		       Toast.makeText(getApplicationContext(),"nama foto" + GetPhotoName(filePath), Toast.LENGTH_LONG).show();
		       Toast.makeText(getApplicationContext(),"path" + filePath, Toast.LENGTH_LONG).show();
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
		    
		    String[] split = image().split("~");
		    
		    if (getDrawableImage(foto_anak1) == bitmap1 && foto1 == true) {
		     foto_anak1.setImageBitmap(thumbnail);
		     //fix it
		     //split latitude and longitude
		     img_1 = new Image_Model(GetPhotoName(filePath), GetTimeNow(), child_id, filePath, "-", "1", split[0], split[1]);
		    }

		    if (getDrawableImage(foto_anak2) == bitmap1 && foto2 == true) {
		     foto_anak2.setImageBitmap(thumbnail);
		     img_2 = new Image_Model(GetPhotoName(filePath), GetTimeNow(), child_id, filePath, "-", "1", split[0], split[1]);
		    }

		    if (getDrawableImage(foto_anak3) == bitmap1 && foto3 == true) {
		     foto_anak3.setImageBitmap(thumbnail);
		     img_3 = new Image_Model(GetPhotoName(filePath), GetTimeNow(), child_id, filePath, "-", "1", split[0], split[1]);
		    }

		    if (getDrawableImage(foto_anak4) == bitmap1 && foto4 == true) {
		     foto_anak4.setImageBitmap(thumbnail);
		     img_4 = new Image_Model(GetPhotoName(filePath), GetTimeNow(), child_id, filePath, "-", "1", split[0], split[1]);
		    }

		    if (getDrawableImage(foto_anak5) == bitmap1 && foto5 == true) {
		     foto_anak5.setImageBitmap(thumbnail);
		     img_5 = new Image_Model(GetPhotoName(filePath), GetTimeNow(), child_id, filePath, "-", "1", split[0], split[1]);
		    }

		    if (getDrawableImage(foto_anak6) == bitmap1 && foto6 == true) {
		     foto_anak6.setImageBitmap(thumbnail);
		     img_6 = new Image_Model(GetPhotoName(filePath), GetTimeNow(), child_id, filePath, "-", "1", split[0], split[1]);
		    }
		    // Bitmap photo = null;
		    // Bundle extras = data.getExtras();
		    // if (extras != null) {
		    // photo = extras.getParcelable("data");
		    // }
		    // Intent i = new Intent(this,DialogDeleteImage.class);
		    // i.putExtra("imag)e", photo);
		    // //startActivity(i;

		   }
		  }
		 }
		 
		//parameter nomor foto di maksudkan agar dapat mengambil path photo
		 public void alertImage(final ImageView input,final String nomor_foto)
		 {
		   final Dialog dialog = new Dialog(context); 
		   dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		   dialog.setContentView(R.layout.custom_dialog);   
		   final Dialog dialogConfirm = new Dialog(context);
		   dialogConfirm.requestWindowFeature(Window.FEATURE_NO_TITLE);      
		   ImageView imageDialog = (ImageView) dialog.findViewById(R.id.image);
		   imageDialog.setImageBitmap(getDrawableImage(input).createScaledBitmap(getDrawableImage(input), 258, 150, true));
		   
		   LinearLayout linHapus = (LinearLayout) dialog.findViewById(R.id.btnHapus);      
		   linHapus.setOnClickListener(new OnClickListener() {
			   public void onClick(View v) {
				// TODO Auto-generated method stub     
				dialogConfirm.setContentView(R.layout.confirm_delete);
				LinearLayout linYakin = (LinearLayout) dialogConfirm.findViewById(R.id.btnYakinHapus);
				LinearLayout linKembali = (LinearLayout) dialogConfirm.findViewById(R.id.button_kembali);    
				
				linYakin.setOnClickListener(new OnClickListener() {
				      public void onClick(View v) {
				       // TODO Auto-generated method stub
				    	  if(nomor_foto.equals("1"))
				    	  {
				    		  img_1 = null;
				    	  }
				    	  else if(nomor_foto.equals("2"))
				    	  {
				    		  img_2 = null;
				    	  }
				    	  else if(nomor_foto.equals("3"))
				    	  {
				    		  img_3 = null;
				    	  }
				    	  else if(nomor_foto.equals("4"))
				    	  {
				    		  img_4 = null;
				    	  }
				    	  else if(nomor_foto.equals("5"))
				    	  {
				    		  img_5 = null;
				    	  }
				    	  else if(nomor_foto.equals("6"))
				    	  {
				    		  img_6 = null;
				    	  }
				       input.setImageBitmap(bitmap1);
				      
				       dialogConfirm.dismiss();
				       dialog.dismiss();
				      }
				     });
		     
		     linKembali.setOnClickListener(new OnClickListener() {
		       public void onClick(View v) {
		        // TODO Auto-generated method stub	        
		        dialogConfirm.dismiss();
		        dialog.dismiss();
		       }
		      });
		     	     
		     dialogConfirm.show();
		     
		    }});
		         //input.setImageBitmap(thumbnail);
		         dialog.show();
		  }

		 public Bitmap getDrawableImage(ImageView input) {
			  input.buildDrawingCache(true);
			  Bitmap bitmap = input.getDrawingCache(true);
			  BitmapDrawable drawable = (BitmapDrawable) input.getDrawable();
			  bitmap56 = drawable.getBitmap();
			  return bitmap56;
		 }
		 
		 public String GetPhotoName(String path)
		 {
			 String result;
			 String[] splitting = path.split("/");
			 result = splitting[splitting.length-1];
			 return result;
		 }

}
