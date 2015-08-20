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
import com.example.modellap.Complaint_Model;
import com.example.modellap.Environment_Model;
import com.example.modellap.Image_Model;
import com.example.modellap.Visit_Facility;
import com.example.modellap.Visit_Model;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class FormulirKunjunganRumahActivity extends Activity {

	// untuk foto rumah (kamera)
	private static final int Image_take = 1;

	Database db;
	LinearLayout btn_simpan;
	RadioGroup rg_ringkas, rg_rapi, rg_resik, rg_rawat, rg_rajin;
	RadioButton r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14,r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25;
	TextView textview_total, text_total;
	int sum1, sum2, sum3, sum4, sum5, sum;
	EditText editText_tinggibadan,editText_beratbadan;
	final Context context = this;
	ImageView ivrumah1, ivrumah2, ivrumah3;
	
	// environment model
	Environment_Model em1 = new Environment_Model();
	Environment_Model em2 = new Environment_Model();
	Environment_Model em3 = new Environment_Model();
	Environment_Model em4 = new Environment_Model();
	Environment_Model em5 = new Environment_Model();

	ArrayList<Environment_Model> environments = new ArrayList<Environment_Model>();
	// Kelas GPS Tracker
	GPSTracker gps;
	// id anak
	String id_child;
	Visit_Model model_visit,model_visit_temp;
	Environment_Model model_environment;

	
	ArrayList<Complaint_Model> complaints = new ArrayList<Complaint_Model>();
	ArrayList<Visit_Facility> facilities = new ArrayList<Visit_Facility>();
	ArrayList<Image_Model> foto_anak = new ArrayList<Image_Model>();
	ArrayList<Image_Model> foto_lingkungan = new ArrayList<Image_Model>();
	ArrayList<Image_Model> foto = new ArrayList<Image_Model>();
	Image_Model lingk1, lingk2, lingk3;

	ImageView image, btn_back, foto_rumah1, foto_rumah2, foto_rumah3;
	Button btn_foto_rumah1, btn_foto_rumah2, btn_foto_rumah3;
	Bitmap bitmap1, thumbnail, thumbnail1;
	Boolean foto1 = false, foto2 = false, foto3 = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		    
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_formulir_kunjungan_rumah);
		instansiasiProperties();
		
		/*****************************LISTENING INTENT**********************/
		Intent i = getIntent();
		Bundle extras = i.getExtras();
		if (extras.containsKey("id")) {
			// Do stuff because extra has been added
			id_child = extras.getString("id");
			model_visit = (Visit_Model) extras.getParcelable("Object_VisitModel");
			Log.e("[received from FKA]", "" + extras.getString("id"));
		} else if(extras.containsKey("id_update"))
		{
			//this is for updating
			Log.e("[Received from FKA] Updating", ""+extras.getString("id_update"));
			id_child = extras.getString("id_update");
			//this is for set environment score and also environment photo
			model_visit = (Visit_Model) extras.getParcelable("Object_VisitModel");
			Log.e("Model Visit Update Received", "tingii "+model_visit.GetHeight()+" weight "+model_visit.GetWeight()+" lila "+model_visit.GetLILA());
			
			
			model_visit_temp = db.GetSpesificVisit(id_child, model_visit.GetVisitDate());
			environments = model_visit_temp.GetEnvironment();
			
			foto = model_visit_temp.GetPhotos();
			if(foto.size() != 0)
			{
				for(Image_Model mdl_lingk : foto)
				{
					if(mdl_lingk.GetImage_type_id().equals("1"))
					{
						continue;
					}
					else
					{
						File image = new File(mdl_lingk.GetImage_path());
						BitmapFactory.Options bmOptions = new BitmapFactory.Options();
						Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(),bmOptions);
						bitmap = Bitmap.createScaledBitmap(bitmap, 132, 105, true);
						
						if (getDrawableImage(foto_rumah1) == bitmap1) {
							foto_rumah1.setImageBitmap(bitmap);
							foto1 = true;
							lingk1 = new Image_Model(mdl_lingk.GetImage_name(), mdl_lingk.GetImage_date(), mdl_lingk.GetImage_id(), mdl_lingk.GetImage_path(),mdl_lingk.GetImage_server_path(),mdl_lingk.GetImage_type_id(),mdl_lingk.GetImage_longitude(),mdl_lingk.GetImage_latitude());
						}
						else if(getDrawableImage(foto_rumah2) == bitmap1)
						{
							foto_rumah2.setImageBitmap(bitmap); 
							foto2 = true;
							lingk2 = new Image_Model(mdl_lingk.GetImage_name(), mdl_lingk.GetImage_date(), mdl_lingk.GetImage_id(), mdl_lingk.GetImage_path(),mdl_lingk.GetImage_server_path(),mdl_lingk.GetImage_type_id(),mdl_lingk.GetImage_longitude(),mdl_lingk.GetImage_latitude());
						}
						else if(getDrawableImage(foto_rumah3) == bitmap1)
						{
							foto_rumah3.setImageBitmap(bitmap); 
							foto3 = true;
							lingk3 = new Image_Model(mdl_lingk.GetImage_name(), mdl_lingk.GetImage_date(), mdl_lingk.GetImage_id(), mdl_lingk.GetImage_path(),mdl_lingk.GetImage_server_path(),mdl_lingk.GetImage_type_id(),mdl_lingk.GetImage_longitude(),mdl_lingk.GetImage_latitude());
						}
				}
			}
			
			
			}
		}
//		if(i.getStringExtra("id") != null)
//		{
//			id_child = i.getStringExtra("id");
//			Log.e("Intentku", "true " + i.getStringExtra("id")); 
//		}
//		else
//		{
//			Log.e("no", "");
//		}

		/*******************************ENVIRONMENT ***********************************/
		textview_total.setText("0");
		rg_ringkas
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						// TODO Auto-generated method stub

						int childCount = group.getChildCount();
						for (int i = 0; i < childCount; i++) {

							RadioButton r_btn = (RadioButton) group
									.getChildAt(i);
							if (r_btn.getId() == checkedId) {
								// do your stuf here
								sum1 = i + 1; // since position is from 0.
								
								em5.SetEnvironment_ID("ENV005");
								em5.SetEnvironment_Score("" + sum1);
								sum += sum1;
							}

						}

					}
				});

		rg_rapi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub

				int childCount = group.getChildCount();
				for (int i = 0; i < childCount; i++) {

					RadioButton r_btn = (RadioButton) group.getChildAt(i);
					if (r_btn.getId() == checkedId) {
						// do your stuf here
						sum2 = i + 1; // since position is from 0.
						em1.SetEnvironment_ID("ENV001");
						em1.SetEnvironment_Score("" + sum2);
						sum += sum2;
					}

				}

			}
		});

		rg_resik.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub

				int childCount = group.getChildCount();
				for (int i = 0; i < childCount; i++) {

					RadioButton r_btn = (RadioButton) group.getChildAt(i);
					if (r_btn.getId() == checkedId) {
						// do your stuf here
						sum3 = i + 1; // since position is from 0.
						em2.SetEnvironment_ID("ENV002");
						em2.SetEnvironment_Score("" + sum3);
						sum += sum3;
					}

				}

			}
		});

		rg_rawat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub

				int childCount = group.getChildCount();
				for (int i = 0; i < childCount; i++) {

					RadioButton r_btn = (RadioButton) group.getChildAt(i);
					if (r_btn.getId() == checkedId) {
						// do your stuf here
						sum4 = i + 1; // since position is from 0.
						em3.SetEnvironment_ID("ENV003");
						em3.SetEnvironment_Score("" + sum4);
						sum += sum4;

					}

				}

			}
		});

		rg_rajin.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub

				int childCount = group.getChildCount();
				for (int i = 0; i < childCount; i++) {

					RadioButton r_btn = (RadioButton) group.getChildAt(i);
					if (r_btn.getId() == checkedId) {
						// do your stuf here
						sum5 = i + 1; // since position is from 0.
						em4.SetEnvironment_ID("ENV004");
						em4.SetEnvironment_Score("" + sum5);
						sum += sum5;
						
					}

				}

			}
		});

		text_total.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// sum = rg1.getCheckedRadioButtonId()+
				// rg2.getCheckedRadioButtonId();
				// String total1 = ("Total : "+sum);
				// total.setText(""+sum);
				// total.setText(r1.getText());
				// total.setText(r2.getText());
				/*************************************/
				sum = sum1 + sum2 + sum3 + sum4 + sum5;
				textview_total.setText("" + sum);
			}
		});

		/***********************************FOTO RUMAH***************************************/
		
		btn_foto_rumah1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (getDrawableImage(foto_rumah1) == bitmap1) {
					image();
					foto1 = true;
					// foto_anak1.setImageBitmap(thumbnail);
				} else {
					alertImage(foto_rumah1,"1");
				}
			}
		});
		btn_foto_rumah2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (getDrawableImage(foto_rumah2) == bitmap1) {
					image();
					foto2 = true;
				} else {
					alertImage(foto_rumah2,"2");
				}
			}
		});
		btn_foto_rumah3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (getDrawableImage(foto_rumah3) == bitmap1) {
					image();
					foto3 = true;
				} else {
					alertImage(foto_rumah3,"3");
				}
			}
		});
		}

	
	
	public void instansiasiProperties()
	{
		db = new Database(this);
		btn_back = (ImageView) findViewById(R.id.btn_back);
		btn_simpan = (LinearLayout) findViewById(R.id.button_simpan);

		// bagian penilaian kondisi rumah (radiobutton)
		r1 = (RadioButton) findViewById(R.id.radioButton1);
		r2 = (RadioButton) findViewById(R.id.radioButton1);
		r3 = (RadioButton) findViewById(R.id.radioButton1);
		r4 = (RadioButton) findViewById(R.id.radioButton1);
		r5 = (RadioButton) findViewById(R.id.radioButton1);
		r6 = (RadioButton) findViewById(R.id.radioButton1);
		r7 = (RadioButton) findViewById(R.id.radioButton1);
		r8 = (RadioButton) findViewById(R.id.radioButton1);
		r9 = (RadioButton) findViewById(R.id.radioButton1);
		r10 = (RadioButton) findViewById(R.id.radioButton1);
		r11 = (RadioButton) findViewById(R.id.radioButton1);
		r12 = (RadioButton) findViewById(R.id.radioButton1);
		r13 = (RadioButton) findViewById(R.id.radioButton1);
		r14 = (RadioButton) findViewById(R.id.radioButton1);
		r15 = (RadioButton) findViewById(R.id.radioButton1);
		r16 = (RadioButton) findViewById(R.id.radioButton1);
		r17 = (RadioButton) findViewById(R.id.radioButton1);
		r18 = (RadioButton) findViewById(R.id.radioButton1);
		r19 = (RadioButton) findViewById(R.id.radioButton1);
		r20 = (RadioButton) findViewById(R.id.radioButton1);
		r21 = (RadioButton) findViewById(R.id.radioButton1);
		r22 = (RadioButton) findViewById(R.id.radioButton1);
		r23 = (RadioButton) findViewById(R.id.radioButton1);
		r24 = (RadioButton) findViewById(R.id.radioButton1);
		r25 = (RadioButton) findViewById(R.id.radioButton1);

		// 005
		rg_ringkas = (RadioGroup) findViewById(R.id.radioGroupRingkas);
		// 001
		rg_rapi = (RadioGroup) findViewById(R.id.radioGroupRapi);
		// 002
		rg_resik = (RadioGroup) findViewById(R.id.radioGroupResik);
		// 003
		rg_rawat = (RadioGroup) findViewById(R.id.radioGroupRawat);
		// 004
		rg_rajin = (RadioGroup) findViewById(R.id.radioGroupRajin);

		textview_total = (TextView) findViewById(R.id.textview_total);
		text_total = (TextView) findViewById(R.id.text_total);
		
		// foto rumah
				btn_foto_rumah1 = (Button) findViewById(R.id.btn_foto_rumah1);
				btn_foto_rumah2 = (Button) findViewById(R.id.btn_foto_rumah2);
				btn_foto_rumah3 = (Button) findViewById(R.id.btn_foto_rumah3);
				image = (ImageView) findViewById(R.id.image);

				// bagian foto rumah
				foto_rumah1 = (ImageView) findViewById(R.id.foto_rumah1);
				foto_rumah2 = (ImageView) findViewById(R.id.foto_rumah2);
				foto_rumah3 = (ImageView) findViewById(R.id.foto_rumah3);
				
				editText_tinggibadan = (EditText) findViewById(R.id.editText_tinggibadan);
				editText_beratbadan = (EditText) findViewById(R.id.editText_beratbadan);
				
				//model foto lingkungan
				lingk1 = new Image_Model();
				lingk2 = new Image_Model();
				lingk3 = new Image_Model();
				
				//environment photo
				ivrumah1 = (ImageView) findViewById(R.id.foto_rumah1);
				ivrumah2 = (ImageView) findViewById(R.id.foto_rumah2);
				ivrumah3 = (ImageView) findViewById(R.id.foto_rumah3);

				bitmap1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.new_button_camera);
				foto_rumah1.setImageBitmap(bitmap1);
				foto_rumah2.setImageBitmap(bitmap1);
				foto_rumah3.setImageBitmap(bitmap1);

	}

	public String CountBmiScore()
	{
	 
	  String weight = model_visit.GetWeight();
	  int w = Integer.parseInt(weight);
	  String height = model_visit.GetHeight();
	  int h = Integer.parseInt(height);
	  int bmi_score = (w*10000) / (h*h);
	  //int bmi_score = (w * 100000) / (h*h);
	  String total = ""+bmi_score; 
	  return total;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.formulir_kunjungan_rumah, menu);
		return true;
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn_back:
			super.onBackPressed();
//			Intent back_ka = new Intent(FormulirKunjunganRumahActivity.this,
//					FormulirKunjunganAnakActivity.class);
//			back_ka.putExtra("id_child_ka", id_child);
//			back_ka.putExtra("Object_VisitModel1", model_visit);
//			Log.e("Sending intent back into fk_anak", "" + id_child);
//			
//			startActivity(back_ka);
			break;

		case R.id.button_simpan:
			final Dialog dialog = new Dialog(context);
			dialog.setContentView(R.layout.activity_warning_dialog);

			LinearLayout btn_kembali = (LinearLayout) dialog.findViewById(R.id.button_kembali);
			LinearLayout btn_simpan2 = (LinearLayout) dialog.findViewById(R.id.button_simpan2);

			btn_kembali.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dialog.dismiss();

				}
			});

			btn_simpan2.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					// lakukan simpan ke tabel tr visit
					Log.e("Simpan data kunjungan Anak", "" + true);
					Intent i = getIntent();
					Bundle extras = i.getExtras();
						
					complaints = model_visit.GetComplaints();
						
					environments.add(em1);
					environments.add(em2);
					environments.add(em3);
					environments.add(em4);
					environments.add(em5);
					model_visit.setEnvironments(environments);
					
					facilities = model_visit.GetFasilitasKunjungan();
					
					foto_anak = model_visit.GetPhotos();
					
					if(lingk1 != null)
					{
						if(lingk1.GetImage_name() != null)
						{
							foto_lingkungan.add(lingk1);
						}
						
					}
					if(lingk2  != null)
					{
						if(lingk2.GetImage_name() != null)
						{
							foto_lingkungan.add(lingk2);
						}
					}
					if(lingk3 != null)
					{
						if(lingk3.GetImage_name() != null)
						{
							foto_lingkungan.add(lingk3);
						}
					}
					
					Log.e("size foto lingk", ""+foto_lingkungan.size());
					//----------------> tracing foto 
					/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
					/******************************UPDATE KUNJUNGAN***************************/
					/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
					if(extras.containsKey("id_update"))
					{
						try
						{
							Log.e("Update Kunjungan", "");
							/**********************UPDATE KUNJUNGAN**************************/
							//delete first
							//db.DeleteKunjunganAnak(id_child, model_visit.GetVisitDate(), model_visit_temp.GetComplaints());
							//insert the new visit
							for (Complaint_Model a : complaints) {
								
									db.UpdateToTableVisit(id_child, a.GetKeluhan(), a.GetStatusKeluhan(), model_visit.GetVisitDate(),model_visit.GetVisitTypeID() , model_visit.GetARVTaken(), model_visit.GetHeight(), model_visit.GetLILA(),  model_visit.GetWeight(), a.GetTindakan(), a.GetTindakan(), "R000", "Sartika",model_visit.GetVisitDate() , CountBmiScore(),"Sartika1",GetTimeNow());
									Log.e("Update ke tabel tr visit",
													"child_id " + id_child 
													+ "+ keluhan " + a.GetKeluhan()
													+ "status keluhan "
													+ a.GetStatusKeluhan() + " visit date "
													+ GetTimeNow() + "visit type id "
													+ model_visit.GetVisitTypeID()
													+ "arv taken "
													+ model_visit.GetARVTaken() + "height "
													+ model_visit.GetHeight() + "lila "
													+ model_visit.GetLILA() + "weight "
													+ model_visit.GetWeight() + "tindakan "
													+ a.GetTindakan() + "note "
													+ model_visit.GetNote()
													+ "reminder id R000");
							}
								
							/***************************UPDATE ENVIRONMENT SCORE ***************************/
							
							for (Environment_Model a : environments) {
								db.UpdateEnvironmentScore(id_child, model_visit.GetVisitDate(), a.GetEnvironmentID(),a.GetEnvironmentScore());
							}
							/***********************UPDATE VISIT FACILITY*************************************/
							for(Visit_Facility fc : facilities)
							{
								db.UpdateJumlahFasilitasKunjunganAnak(model_visit.GetVisitDate(), model_visit.GetChildID(), fc.getFacility_id(), fc.getFacility_cost_id(), fc.GetFacilityQty());
							}
							
							/***********************************UPDATE PHOTOS *******************************/
							//---> child photo
							//foto anak
							//delete the existing photo first and then insert the new one
							ArrayList<Image_Model> photos = new ArrayList<Image_Model>();
							photos = db.GetAllPathFoto(model_visit.GetVisitDate(),model_visit.GetChildID());
							db.DeletePhoto(photos);
							
							for(Image_Model fa : foto_anak)
							{
								if(fa != null)
								{
									db.UpdatePhoto(fa.GetImage_name(), model_visit.GetVisitDate(), id_child, fa.GetImage_path(), fa.GetImage_server_path(), fa.GetImage_type_id(), fa.GetImage_longitude(), fa.GetImage_latitude(), "Sartika1", GetTimeNow(),"Sartika",""+model_visit.GetVisitDate());
								}
								else
								{
									continue;
								}
								
							}
							
							//----> environment foto
							// save images
							for(Image_Model fa : foto_lingkungan)
							{
									//Log.e("Update Foto Lingkungan", "Nama foto : "+fa.GetImage_name()+" nama path : "+fa.GetImage_path()+" latitude : "+fa.GetImage_latitude()+" longitude : "+fa.GetImage_longitude());
									db.UpdatePhoto(fa.GetImage_name(), model_visit.GetVisitDate(), id_child, fa.GetImage_path(), fa.GetImage_server_path(), fa.GetImage_type_id(), fa.GetImage_longitude(), fa.GetImage_latitude(), "Sartika1", GetTimeNow(),"Sartika",""+model_visit.GetVisitDate());
								
							}
							
							Toast.makeText(getApplicationContext(),"Selamat Anda berhasil mengedit kunjungan Anak",Toast.LENGTH_SHORT).show();
							Intent intent3 = new Intent(getApplication(),KunjunganActivity.class);
							intent3.putExtra("child_id_fkra", id_child);
							startActivity(intent3);
							dialog.dismiss();
							finish();
						}catch(Exception e)
						{
							Log.e("Eror while update kunjungan",""+e.toString());
							Toast.makeText(getApplicationContext(),"Maaf,terjadi kesalahan dalam menambahkan kunjungan anak(1a)",Toast.LENGTH_SHORT).show();
						}
					}
					
					else
					{
						/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
						/******************************CREATE KUNJUNGAN***************************/
						/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
						
						try
						{
							/**********************Create KUNJUNGAN***********************************/
							for (Complaint_Model a : complaints) {
								//simpan kunjungan yang baru
								db.SimpanKunjunganAnak(id_child, a.GetKeluhan(), a.GetStatusKeluhan(), GetTimeNow(), model_visit.GetVisitTypeID(), model_visit.GetARVTaken(), model_visit.GetHeight(), model_visit.GetLILA(), model_visit.GetWeight(), a.GetTindakan(), model_visit.GetNote(), "R000", "Sartika", GetTimeNow(), CountBmiScore());
								Log.e("Insert ke tabel tr visit",
										"child_id " + id_child + "+ keluhan " + a.GetKeluhan()
												+ "status keluhan "
												+ a.GetStatusKeluhan() + " visit date "
												+ GetTimeNow() + "visit type id "
												+ model_visit.GetVisitTypeID()
												+ "arv taken "
												+ model_visit.GetARVTaken() + "height "
												+ model_visit.GetHeight() + "lila "
												+ model_visit.GetLILA() + "weight "
												+ model_visit.GetWeight() + "tindakan "
												+ a.GetTindakan() + "note "
												+ model_visit.GetNote()
												+ "reminder id R000");
							}
							
							/***************************SAVE ENVIRONMENT SCORE ***************************/
							for (Environment_Model a : environments) {
								db.InsertEnvironmentScore( GetTimeNow(), id_child, a.GetEnvironmentID(), a.GetEnvironmentScore());
								Log.e("Environments","ID Environment " + a.GetEnvironmentID()+ " Score " + a.GetEnvironmentScore());
							}
							
							/***********************SAVE VISIT FACILITY**********************************************/
							for(Visit_Facility fc : facilities)
							{
								db.InsertFasilitasKunjunganAnak(GetTimeNow(), model_visit.GetChildID(), fc.getFacility_id(), fc.getFacility_cost_id(), fc.GetFacilityQty());
								Log.e("Inserting to TM Visit Facilities", "true");
							}
							
							/***********************************SAVE PHOTOS *****************************************/
							//---> child photo
							//foto anak
							
							for(Image_Model fa : foto_anak)
							{
								if(fa != null)
								{
									db.InsertPhoto(fa.GetImage_name(), fa.GetImage_date(), id_child, fa.GetImage_path(), fa.GetImage_server_path(), fa.GetImage_type_id(), fa.GetImage_longitude(), fa.GetImage_latitude(), "Sartika", GetTimeNow());
								}
								else
								{
									continue;
								}
								
							}
							//----> environment foto
							// save images
							for(Image_Model fa : foto_lingkungan)
							{
								if(fa == null)
								{
									continue;
								}
								else
								{
									Log.e("Foto Lingkungan", "Nama foto : "+fa.GetImage_name()+" nama path : "+fa.GetImage_path()+" latitude : "+fa.GetImage_latitude()+" longitude : "+fa.GetImage_longitude());
									db.InsertPhoto(fa.GetImage_name(), fa.GetImage_date(), id_child, fa.GetImage_path(), fa.GetImage_server_path(), fa.GetImage_type_id(), fa.GetImage_longitude(), fa.GetImage_latitude(), "Sartika", GetTimeNow());
								}
							}
							
							Toast.makeText(getApplicationContext(),"Selamat Anda berhasil menambahkan kunjungan Anak",Toast.LENGTH_SHORT).show();
							Intent intent3 = new Intent(getApplication(),KunjunganActivity.class);
							intent3.putExtra("child_id_fkra", id_child);
							startActivity(intent3);
							dialog.dismiss();
							finish();
							
						}catch(Exception e)
						{
							Log.e("Eror while create kunjungan",""+e.toString());
							Toast.makeText(getApplicationContext(),"Maaf,terjadi kesalahan dalam menambahkan kunjungan anak(1b)",Toast.LENGTH_SHORT).show();
						}
					}
				
				//------------------------------------> validate
				
				
				}
			});	
			dialog.show();
			break;

		default:
			break;
		}
	}

	public String GetTimeNow() {
		Calendar c = Calendar.getInstance();

		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		return df.format(c.getTime());
	}

	public String image() {	  
		  // create class object
		  gps = new GPSTracker(FormulirKunjunganRumahActivity.this);
		  double latitude=0;
		  double longitude=0;
		  // check if GPS enabled
		  if (gps.canGetLocation()) {
			   Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			   startActivityForResult(intent, 1);
			   latitude = gps.getLatitude();
			   longitude = gps.getLongitude();
			   
			   Toast.makeText(getApplicationContext(),"Lokasi Anda - \nLatitude : " + latitude + "\nLongitude : "+ longitude, Toast.LENGTH_LONG).show();
			   
		  }else {
				gps.showSettingsAlert();
		  }
		  return Double.toString(longitude)+"~"+Double.toString(latitude);
	}

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
				
			    File destination = new File( Environment.getExternalStorageDirectory(),System.currentTimeMillis() + ".jpg");
			    FileOutputStream fo;
			    //Uri selectedImage = data.getData();
			    String[] filePathColumn = {MediaStore.Images.Media.DATA};
			    //Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
			    final String imageOrderBy = MediaStore.Images.Media._ID+ " DESC";
			    Cursor cursor = managedQuery( MediaStore.Images.Media.EXTERNAL_CONTENT_URI,filePathColumn, null, null, imageOrderBy);      
			    cursor.moveToFirst();
			    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			    String filePath = cursor.getString(columnIndex);
			    Log.v("log","filePath environtment is : "+filePath); 
		             
		    Toast.makeText(getApplicationContext(),"path " + filePath, Toast.LENGTH_LONG).show();
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
		    
		    //set displaying photo and model it
		    String[] split = image().split("~");
		    
		    if (getDrawableImage(ivrumah1) == bitmap1 && foto1 == true) {
		    	ivrumah1.setImageBitmap(thumbnail);
		    	lingk1 = new Image_Model(GetPhotoName(filePath), GetTimeNow(), id_child, filePath, "-", "2", split[0], split[1]);
		    	Log.e("Foto Lingkungan 1", "nama : "+GetPhotoName(filePath)+" file path "+filePath+" longitude "+split[0]+" latitude "+split[1]);
		    }

		    if (getDrawableImage(ivrumah2) == bitmap1 && foto2 == true) {
		    	ivrumah2.setImageBitmap(thumbnail);
		    	lingk2 = new Image_Model(GetPhotoName(filePath), GetTimeNow(), id_child, filePath, "-", "2", split[0], split[1]);
		    	Log.e("Foto Lingkungan 2", "nama : "+GetPhotoName(filePath)+" file path "+filePath+" longitude "+split[0]+" latitude "+split[1]);
		    }

		    if (getDrawableImage(ivrumah3) == bitmap1 && foto3 == true) {
		    	ivrumah3.setImageBitmap(thumbnail);
		    	lingk3 = new Image_Model(GetPhotoName(filePath), GetTimeNow(), id_child, filePath, "-", "2", split[0], split[1]);
		    	Log.e("Foto Lingkungan 3", "nama : "+GetPhotoName(filePath)+" file path "+filePath+" longitude "+split[0]+" latitude "+split[1]);
		    }
		   }
		  }
		 }
		 
		 public String getRealPathFromURI(Uri contentUri)
		 {
		      try
		      {
		          String[] proj = {MediaStore.Images.Media.DATA};
		          Cursor cursor = managedQuery(contentUri, proj, null, null, null);
		          int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		          cursor.moveToFirst();
		          return cursor.getString(column_index);
		      }
		      catch (Exception e)
		      {
		           return contentUri.getPath();
		      }
		  }

		 
		 //parameter nomor foto di maksudkan agar dapat mengambil path photo
		 public void alertImage(final ImageView input, final String nomor_foto) {
			 
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
								lingk1 = null;
//								File file = new File(lingk1.GetImage_path());
//								Log.e("delete foto rumah", "path "+lingk1.GetImage_path()+" "+file.delete());
//								Log.e("External Env",""+Environment.getExternalStorageDirectory().getPath());
//								file.delete();
								 
							 }
							 else if(nomor_foto.equals("2"))
							 {
								 lingk2 = null;
//								 File file = new File(lingk2.GetImage_path());
//								 Log.e("delete foto rumah", "path "+lingk2.GetImage_path()+" "+file.delete());
//								 Log.e("External Env",""+Environment.getExternalStorageDirectory().getPath());
//								 file.delete();
							 }
							 else if(nomor_foto.equals("3"))
							 {
								 lingk3 = null;
//								 File file = new File(lingk3.GetImage_path());
//								 Log.e("delete foto rumah", "path "+lingk3.GetImage_path()+" "+file.delete());
//								 Log.e("External Env",""+Environment.getExternalStorageDirectory().getPath());
//								 file.delete();
							 }
							 input.setImageBitmap(bitmap1);
		
							 dialogConfirm.dismiss();
							 dialog.dismiss();
						 }
					 });
		    
			    linKembali.setOnClickListener(new OnClickListener() {
			    	public void onClick(View v) {
			    		dialogConfirm.dismiss();
			    		dialog.dismiss();
			     }
			    });
			    
		    dialogConfirm.show();
		   }
		  });
		  // input.setImageBitmap(thumbnail);
		  dialog.show();
		 }

		 Bitmap bitmap56;

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
