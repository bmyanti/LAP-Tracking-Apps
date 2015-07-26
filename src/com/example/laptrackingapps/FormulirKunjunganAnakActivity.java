package com.example.laptrackingapps;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import com.example.databaselap.TM_Complaint;
import com.example.databaselap.TM_Complaint_Status;
import com.example.databaselap.TM_Visit_Type;
import com.example.modellap.Complaint_Model;
import com.example.modellap.Visit_Model;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcel;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class FormulirKunjunganAnakActivity extends Activity implements
		OnItemSelectedListener {

	// untuk foto anak (kamera)
	private static final int Image_take = 1;

	ImageView back, foto_anak1, foto_anak2, foto_anak3, foto_anak4, foto_anak5,
			foto_anak6;
	Button tambah_keluhan;
	LinearLayout tambahKeluhan, tambahKeluhan2;
	Button  btn_foto1, btn_foto2,
			btn_foto3, btn_foto4, btn_foto5, btn_foto6;
	LinearLayout layout_tambahkeluhan, layout_keluhan, layout_status,
			layout_tindakan;
	LinearLayout button_lanjut;

	// GPSTracker class
	GPSTracker gps;

	// untuk tanggal ambil arv
	int hour, minute, mYear, mMonth, mDay;
	static final int TIME_DIALOG_ID = 0;
	static final int DATE_DIALOG_ID = 1;
	private EditText txtDate;
	Bitmap bitmap1, bitmap56;
	private String[] arrMonth = { "Januari", "Februari", "Maret", "April",
			"Mei", "Juni", "Juli", "Agustus", "September", "Oktober",
			"November", "Desember" };
	Bitmap thumbnail1, thumbnail;
	String child_id;
	// instansisasi tabel
	TM_Visit_Type tabel_visit_type;
	TM_Complaint tabel_complaint;
	TM_Complaint_Status tabel_complaint_status;
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

	Visit_Model model_visit;

	ArrayAdapter<String> adptr_visit_type, adptr_keluhan1, adptr_keluhan2,
			adptr_keluhan3, adptr_status1, adptr_status2, adptr_status3;

	int spinner_position;
	ArrayList<Complaint_Model> complaints = new ArrayList<Complaint_Model>();
	Complaint_Model complaint = new Complaint_Model();

	final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_formulir_kunjungan_anak);

		// this object is store information all visit data

		// instasiasi tabel yang berelasi
		InstansisasiTabel();

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

		layout_tambahkeluhan = (LinearLayout) findViewById(R.id.linearlayout_tambahkeluhan);
		tambah_keluhan = (Button) findViewById(R.id.button_tambahkeluhan);
		tambahKeluhan = (LinearLayout) findViewById(R.id.tambahKeluhan);
		tambahKeluhan2 = (LinearLayout) findViewById(R.id.tambahKeluhan2);
		tambah_keluhan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// // TODO Auto-generated method stub
				// //TextView tv1 = new TextView(v.getContext());
				// //tv1.setText("TAMBAH KELUHAN");
				// //layout_tambahkeluhan.addView(tv1);
				// //((LinearLayout)findViewById(R.id.linearlayout_tambahkeluhan)).addView(layout_tambahkeluhan);
				// LinearLayout layout_tambahkeluhan = (LinearLayout)
				// findViewById(R.id.linearlayout_tambahkeluhan);
				// LinearLayout tambah_keluhan = new
				// LinearLayout(v.getContext());
				// //tambah_keluhan.setLayoutParams(new
				// LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
				// LinearLayout.LayoutParams.FILL_PARENT));
				// layout_tambahkeluhan.addView(tambah_keluhan);
				// //getLayoutInflater().inflate(R.id.tambah_keluhan,
				// layout_tambahkeluhan);
				// //layout_tambahkeluhan.setVisibility(View.VISIBLE);

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

		// spinner visit type
		ArrayList<String> data_visit = tabel_visit_type.getDataVisitType();
		// item_kelas = data_kelas.toArray(new String[data_kelas.size()]);
		jenis_kunjungan.setOnItemSelectedListener(this);
		adptr_visit_type = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, data_visit);
		adptr_visit_type
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		jenis_kunjungan.setAdapter(adptr_visit_type);
		// InitiateSpinner(data_visit, jenis_kunjungan);

		// keluhan, status, tindakan
		/*************************************/
		arr_keluhan = tabel_complaint.getDataComplaint();
		arr_status = tabel_complaint_status.getDataComplaintStatus();

		// 1
		adptr_keluhan1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, arr_keluhan);
		adptr_keluhan1
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		keluhan1.setAdapter(adptr_keluhan1);
		// 2
		adptr_keluhan2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, arr_keluhan);
		adptr_keluhan2
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		keluhan2.setAdapter(adptr_keluhan2);
		// 3
		adptr_keluhan3 = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, arr_keluhan);
		adptr_keluhan3
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		keluhan3.setAdapter(adptr_keluhan3);

		// sts 1
		adptr_status1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, arr_status);
		adptr_status1
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		status1.setAdapter(adptr_status1);
		// sts 2
		adptr_status2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, arr_status);
		adptr_status2
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		status2.setAdapter(adptr_status2);

		// sts 3
		adptr_status3 = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, arr_status);
		adptr_status3
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		status3.setAdapter(adptr_status3);

		// note
		note = (EditText) findViewById(R.id.editText_catatan);

		// bagian foto anak
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

		btn_foto1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (getDrawableImage(foto_anak1) == bitmap1) {
					image();
				} else {
					alertImage(foto_anak1);
				}
			}
		});
		btn_foto2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (getDrawableImage(foto_anak2) == bitmap1) {
					image();
				} else {
					alertImage(foto_anak2);
				}
			}
		});
		btn_foto3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (getDrawableImage(foto_anak3) == bitmap1) {
					image();
				} else {
					alertImage(foto_anak3);
				}
			}
		});
		btn_foto4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (getDrawableImage(foto_anak4) == bitmap1) {
					image();
				} else {
					alertImage(foto_anak4);
				}
			}
		});
		btn_foto5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (getDrawableImage(foto_anak5) == bitmap1) {
					image();
				} else {
					alertImage(foto_anak5);
				}
			}
		});
		btn_foto6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (getDrawableImage(foto_anak6) == bitmap1) {
					image();
				} else {
					alertImage(foto_anak6);
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

		// model

		model_visit = new Visit_Model();
		// get id child
		Intent i = getIntent();
		//
		if (i.getStringExtra("id_anak_fk") != null) {
			// model_visit = null;
			child_id = i.getStringExtra("id_anak_fk");
			Log.i("id anak pda form", " " + child_id);
		} else if (i.getStringExtra("id_child_ka") != null) {
			// model visit id di set kembali
			// semua field pada form diisi sesuai objek kiriman
			/*********************************************/

			child_id = i.getStringExtra("id_child_ka");
			Log.e("Child id received", "" + child_id);
			if (i.getParcelableExtra("Object_VisitModel1") != null) {
				model_visit = (Visit_Model) i
						.getParcelableExtra("Object_VisitModel1");
				Log.e("Object Kiriman Visit Type",
						""
								+ tabel_visit_type.getNameVisitType(model_visit
										.GetVisitTypeID()));
				spinner_position = adptr_visit_type
						.getPosition(tabel_visit_type
								.getNameVisitType(model_visit.GetVisitTypeID()));
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

					complaint = complaints.get(0);
					// keluhan
					spinner_position = adptr_keluhan1
							.getPosition(tabel_complaint
									.getNameComplaint(complaint.GetKeluhan()));
					keluhan1.setSelection(spinner_position);
					// status
					spinner_position = adptr_status1
							.getPosition(tabel_complaint_status
									.getNameComplaintStatus(complaint
											.GetStatusKeluhan()));
					status1.setSelection(spinner_position);
					// tindakan
					tindakan1.setText(complaint.GetTindakan());

					complaint = complaints.get(1);
					// keluhan
					spinner_position = adptr_keluhan2
							.getPosition(tabel_complaint
									.getNameComplaint(complaint.GetKeluhan()));
					keluhan2.setSelection(spinner_position);
					// status
					spinner_position = adptr_status2
							.getPosition(tabel_complaint_status
									.getNameComplaintStatus(complaint
											.GetStatusKeluhan()));
					status2.setSelection(spinner_position);
					// tindakan
					tindakan2.setText(complaint.GetTindakan());

					complaint = complaints.get(2);
					// keluhan
					spinner_position = adptr_keluhan3
							.getPosition(tabel_complaint
									.getNameComplaint(complaint.GetKeluhan()));
					keluhan3.setSelection(spinner_position);
					// status
					spinner_position = adptr_status1
							.getPosition(tabel_complaint_status
									.getNameComplaintStatus(complaint
											.GetStatusKeluhan()));
					status3.setSelection(spinner_position);
					// tindakan
					tindakan3.setText(complaint.GetTindakan());
				} else if (complaints.size() == 2) {
					tambahKeluhan.setVisibility(View.VISIBLE);

					complaint = complaints.get(0);
					// keluhan
					spinner_position = adptr_keluhan1
							.getPosition(tabel_complaint
									.getNameComplaint(complaint.GetKeluhan()));
					keluhan1.setSelection(spinner_position);
					// status
					spinner_position = adptr_status1
							.getPosition(tabel_complaint_status
									.getNameComplaintStatus(complaint
											.GetStatusKeluhan()));
					status1.setSelection(spinner_position);
					// tindakan
					tindakan1.setText(complaint.GetTindakan());

					complaint = complaints.get(1);
					// keluhan
					spinner_position = adptr_keluhan2
							.getPosition(tabel_complaint
									.getNameComplaint(complaint.GetKeluhan()));
					keluhan2.setSelection(spinner_position);
					// status
					spinner_position = adptr_status2
							.getPosition(tabel_complaint_status
									.getNameComplaintStatus(complaint
											.GetStatusKeluhan()));
					status2.setSelection(spinner_position);
					// tindakan
					tindakan2.setText(complaint.GetTindakan());

				} else {
					complaint = complaints.get(0);
					// keluhan
					spinner_position = adptr_keluhan1
							.getPosition(tabel_complaint
									.getNameComplaint(complaint.GetKeluhan()));
					keluhan1.setSelection(spinner_position);
					// status
					spinner_position = adptr_status1
							.getPosition(tabel_complaint_status
									.getNameComplaintStatus(complaint
											.GetStatusKeluhan()));
					status1.setSelection(spinner_position);
					// tindakan
					tindakan1.setText(complaint.GetTindakan());
				}

				for (int a = 0; a < complaints.size(); a++) {

					if (a == 0) {
						complaint = complaints.get(0);
						// keluhan
						spinner_position = adptr_keluhan1
								.getPosition(tabel_complaint
										.getNameComplaint(complaint
												.GetKeluhan()));
						keluhan1.setSelection(spinner_position);
						// status
						spinner_position = adptr_status1
								.getPosition(tabel_complaint_status
										.getNameComplaintStatus(complaint
												.GetStatusKeluhan()));
						status1.setSelection(spinner_position);
						// tindakan
						tindakan1.setText(complaint.GetTindakan());
					} else if (a == 1) {
						complaint = complaints.get(1);
						// keluhan
						spinner_position = adptr_keluhan2
								.getPosition(tabel_complaint
										.getNameComplaint(complaint
												.GetKeluhan()));
						keluhan2.setSelection(spinner_position);
						// status
						spinner_position = adptr_status2
								.getPosition(tabel_complaint_status
										.getNameComplaintStatus(complaint
												.GetStatusKeluhan()));
						status2.setSelection(spinner_position);
						// tindakan
						tindakan2.setText(complaint.GetTindakan());
					} else {
						complaint = complaints.get(2);
						// keluhan
						spinner_position = adptr_keluhan3
								.getPosition(tabel_complaint
										.getNameComplaint(complaint
												.GetKeluhan()));
						keluhan3.setSelection(spinner_position);
						// status
						spinner_position = adptr_status1
								.getPosition(tabel_complaint_status
										.getNameComplaintStatus(complaint
												.GetStatusKeluhan()));
						status3.setSelection(spinner_position);
						// tindakan
						tindakan3.setText(complaint.GetTindakan());
					}
				}

			}

			// model_visit = (Visit_Model) getIntent().getSerializableExtra(
			// "Object_VisitModel1");
			// // jenis kunj

			// //keluhan1, status1, tindakan
			//

		}

	}

	// foto anak

	/*
	 * private void layout_tambahkeluhan(Button tambah_keluhan) { // TODO
	 * Auto-generated method stub layout_keluhan = (LinearLayout)
	 * findViewById(R.id.layout_keluhan); layout_status = (LinearLayout)
	 * findViewById(R.id.layout_status); layout_tindakan = (LinearLayout)
	 * findViewById(R.id.layout_tindakan); ImageView image = new
	 * ImageView(null); image.setImageResource(R.drawable.lap_logo);
	 * layout_tambahkeluhan.setLayoutParams(new
	 * LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
	 * LayoutParams.WRAP_CONTENT));
	 * layout_tambahkeluhan.addView(layout_tambahkeluhan);
	 * 
	 * }
	 */

	public void InstansisasiTabel() {
		tabel_visit_type = new TM_Visit_Type(getApplicationContext());
		tabel_complaint = new TM_Complaint(getApplicationContext());
		tabel_complaint_status = new TM_Complaint_Status(
				getApplicationContext());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.formulir_kunjungan_anak, menu);
		return true;
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn_back:
			Intent intent3 = new Intent(FormulirKunjunganAnakActivity.this,
					KunjunganActivity.class);
			intent3.putExtra("id_anak_k", child_id);
			startActivity(intent3);
			break;

		case R.id.button_lanjut:
			Log.i("visit type id",
					""
							+ tabel_visit_type.getIdVisitType(jenis_kunjungan
									.getSelectedItem().toString()));
			Log.i("tb bb ll", "" + tb.getText().toString() + "- "
					+ bb.getText().toString() + "- " + ll.getText().toString());
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
			// save into object first then send intent

			model_visit.setChildID(child_id);
			model_visit.setVisitTypeID(tabel_visit_type
					.getIdVisitType(jenis_kunjungan.getSelectedItem()
							.toString()));
			model_visit.setHeight(tb.getText().toString());
			model_visit.setWeight(bb.getText().toString());
			model_visit.setLILA(ll.getText().toString());
			model_visit.setNote(note.getText().toString());
			model_visit.setARVTaken(txtDate.getText().toString());
			//

			// save the complaints

			complaint.SetKeluhan(tabel_complaint.getIdComplaint(keluhan1
					.getSelectedItem().toString()));
			complaint
					.SetStatusKeluhan(tabel_complaint_status
							.getIdComplaintStatus(status1.getSelectedItem()
									.toString()));
			complaint.SetTindakan(tindakan1.getText().toString());
			if (!keluhan1.getSelectedItem().toString().equals("-")
					&& !status1.getSelectedItem().toString().equals("-")) {
				complaints.add(complaint);
			}

			complaint.SetKeluhan(tabel_complaint.getIdComplaint(keluhan2
					.getSelectedItem().toString()));
			complaint
					.SetStatusKeluhan(tabel_complaint_status
							.getIdComplaintStatus(status2.getSelectedItem()
									.toString()));
			complaint.SetTindakan(tindakan2.getText().toString());
			if (!keluhan2.getSelectedItem().toString().equals("-")
					&& !status2.getSelectedItem().toString().equals("-")) {
				complaints.add(complaint);
			}

			complaint.SetKeluhan(tabel_complaint.getIdComplaint(keluhan3
					.getSelectedItem().toString()));
			complaint
					.SetStatusKeluhan(tabel_complaint_status
							.getIdComplaintStatus(status3.getSelectedItem()
									.toString()));
			complaint.SetTindakan(tindakan3.getText().toString());
			if (!keluhan3.getSelectedItem().toString().equals("-")
					&& !status3.getSelectedItem().toString().equals("-")) {
				complaints.add(complaint);
			}
			model_visit.setComplaints(complaints);

			// model_visit.setComplaints(complaints);
			/*************************************************/
			// save images, facilities

			Intent intent_kunjungan_env = new Intent(
					FormulirKunjunganAnakActivity.this,
					FormulirKunjunganRumahActivity.class);
			intent_kunjungan_env.putExtra("id_child_ke", "" + child_id);
			// intent_kunjungan_env.putExtra("Object_VisitModel", model_visit);
			// model_visit = new Visit_Model("1","2","3","4","5","6");
			// model visit = new model_visit("1",complaint);
			intent_kunjungan_env.putExtra("Object_VisitModel", model_visit);
			startActivity(intent_kunjungan_env);
			// this.startActivityForResult(intent_kunjungan_env, 0);
			// case R.id.button_tambahkeluhan:
			// Intent intent2 = new Intent(FormulirKunjunganAnakActivity.this,
			// ProfilAnakActivity.class);
			// startActivity(intent2);
			// layout_tambahkeluhan = (LinearLayout)
			// findViewById(R.id.linearlayout_tambahkeluhan);
			// tambah_keluhan.setLayoutParams(new
			// LayoutParams(LayoutParams.WRAP_CONTENT,
			// LayoutParams.WRAP_CONTENT));
			// layout_tambahkeluhan.addView(tambah_keluhan);
			/*
			 * for (int i = 1; i < 10; i++) { tambah_keluhan.setLayoutParams(new
			 * LayoutParams(LayoutParams.WRAP_CONTENT,
			 * LayoutParams.WRAP_CONTENT)); tambah_keluhan.setText("" + i);
			 * tambah_keluhan.setId(i);
			 * layout_tambahkeluhan.addView(tambah_keluhan); //((Button)
			 * findViewById(i)).setOnClickListener(this); }
			 */
			// tambah_keluhan.setVisibility(View.VISIBLE);
			// LayoutInflater layoutinflater = (LayoutInflater)
			// getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// View vi =
			// layoutinflater.inflate(R.layout.activity_formulir_kunjungan_anak,
			// null);

			// layout_tambahkeluhan = (LinearLayout)
			// vi.findViewById(R.id.linearlayout_tambahkeluhan);

			// ViewGroup vg = (ViewGroup)
			// findViewById(R.id.button_tambahkeluhan);
			// vg.addView(vi, 0, new
			// ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
			// ViewGroup.LayoutParams.WRAP_CONTENT));
			// break;

		default:
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

	int count;

	public void image() {
		count++;
		if (count > 6) {
			Toast.makeText(getApplicationContext(),
					"Anda Tidak Dapat Menambah Foto Lebih Dari 6",
					Toast.LENGTH_LONG).show();
		}
		// create class object
		gps = new GPSTracker(FormulirKunjunganAnakActivity.this);

		// check if GPS enabled
		if (gps.canGetLocation() && count < 7) {

			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(intent, 1);

			// }
			// else if (gps.canGetLocation())
			// {
			double latitude = gps.getLatitude();
			double longitude = gps.getLongitude();

			// \n is for new line
			Toast.makeText(
					getApplicationContext(),
					"Lokasi Anda - \nLatitude : " + latitude + "\nLongitude : "
							+ longitude, Toast.LENGTH_LONG).show();

		}

		else {
			gps.showSettingsAlert();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			if (requestCode == 1) {
				thumbnail = (Bitmap) data.getExtras().get("data");
				thumbnail = Bitmap
						.createScaledBitmap(thumbnail, 132, 105, true);
				thumbnail1 = (Bitmap) data.getExtras().get("data");
				thumbnail1 = Bitmap.createScaledBitmap(thumbnail, 258, 150,
						true);
				//
				ByteArrayOutputStream bytes = new ByteArrayOutputStream();
				thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
				thumbnail1.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

				File destination = new File(
						Environment.getExternalStorageDirectory(),
						System.currentTimeMillis() + ".jpg");
				FileOutputStream fo;
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

				final int[] tvBulan = { R.id.foto_anak1, R.id.foto_anak2,
						R.id.foto_anak3, R.id.foto_anak4, R.id.foto_anak5,
						R.id.foto_anak6 };
				ImageView iv;

				// looping imageview to set image bitmap
				for (int j = 0; j < count; j++) {
					iv = (ImageView) findViewById(tvBulan[j]);
					if (getDrawableImage(iv) == bitmap1) {
						iv.setImageBitmap(thumbnail);

					}
				}

				// Bitmap photo = null;
				// Bundle extras = data.getExtras();
				// if (extras != null) {
				// photo = extras.getParcelable("data");
				// }
				// Intent i = new Intent(this,DialogDeleteImage.class);
				// i.putExtra("image", photo);
				// //startActivity(i);

			}
		}
	}

	public void alertImage(final ImageView input) {
		final Dialog dialog = new Dialog(context);

		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.custom_dialog);
		ImageView imageDialog = (ImageView) dialog.findViewById(R.id.image);
		imageDialog.setImageBitmap(getDrawableImage(input).createScaledBitmap(
				getDrawableImage(input), 258, 150, true));
		LinearLayout linHapus = (LinearLayout) dialog
				.findViewById(R.id.btnHapus);
		final LinearLayout linYakin = (LinearLayout) dialog
				.findViewById(R.id.btnYakinHapus);
		final LinearLayout linKembali = (LinearLayout) dialog
				.findViewById(R.id.button_kembali);
		linHapus.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				linYakin.setVisibility(View.VISIBLE);
				linKembali.setVisibility(View.VISIBLE);

				linYakin.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						// TODO Auto-generated method stub
						input.setImageBitmap(bitmap1);
						dialog.dismiss();
					}
				});

			}
		});
		// input.setImageBitmap(thumbnail);
		dialog.show();
	}

	public Bitmap getDrawableImage(ImageView input) {
		input.buildDrawingCache(true);
		Bitmap bitmap = input.getDrawingCache(true);

		BitmapDrawable drawable = (BitmapDrawable) input.getDrawable();
		bitmap56 = drawable.getBitmap();

		return bitmap56;
	}

}
