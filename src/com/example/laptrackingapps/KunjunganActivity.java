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

import com.example.databaselap.Database;
import com.example.modellap.ChildARV_Model;
import com.example.modellap.ChildFacility_Model;
import com.example.modellap.Child_Model;
import com.example.modellap.Complaint_Model;
import com.example.modellap.Image_Model;
import com.example.modellap.Visit_Facility;
import com.example.modellap.Visit_Model;

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
import android.widget.EditText;
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
	TextView txt_Nama_Anak, txt_BOD_Anak, txt_Umur_Anak, txt_JK_Anak,txt_GOLDA_Anak, txt_ALAMAT_Anak, txt_StatusARV, txt_JenisARV,
			txt_DosisARV, txt_NamaAyah, txt_StatusAyah, txt_NamaIbu,txt_StatusIbu, txt_NamaPengasuh, Pengasuh, txt_NomorTelepon,txt_Sekolah, txt_Kelas, txt_Fasilitas,txt_ARV;
	
	TextView tb, bb, lila,visit_date1a, visit_date1b, bmi_sekarang,grade, bmi_lalu, visit_date_lalu,keluhan1, keluhan2, keluhan3,status1, status2, status3, tindakan1, tindakan2, tindakan3, catatan, antar_susu, antar_vitamin, antar_popok, arv_taken, skor_rumah;
	ImageView foto_anak, foto_rumah;
	
	EditText editText_tinggibadan,editText_beratbadan;
	String facility_name = "";
	String id_child, id_child_l, id_child_k,child_id_fkra;
	ArrayList<ChildARV_Model> arv_anak = new ArrayList<ChildARV_Model>();
	
	
	Visit_Model visit_model;
	Child_Model model_anak;
	TextView ivTgl1, ivTgl2, ivTgl3, ivTgl4, ivTgl5, ivTgl6, ivTgl7, ivTgl8,ivTgl1a, ivTgl2a, ivTgl3a, ivTgl4a, ivTgl5a, ivTgl6a, ivTgl7a,ivTgl8a;
	RelativeLayout RLtgl1, RLtgl2, RLtgl3, RLtgl4, RLtgl5, RLtgl6, RLtgl7,RLtgl8;

	final Context context = this;
	ImageView image_anak;
	SearchView searchView1;
	Database db;
	Intent FKA;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_kunjungan);
		
		/************************Instansiasi Properti ****************************/
		InstansiasiProperties();
		
		/********************listening into intent*********************************/
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
		
		/************************TAMPILAN IDENTITAS ANAK********************************/
		SetTampilanIdentitasAnak();
		
		/************************TAMPILAN KUNJUNGAN ANAK*******************************/
		setTampilanKunjunganAnak();
		
		/**********************TAMPILAN 8 KUNJUNGAN ************************************/
		
		setDelapanKunjugan();
		
		/************************SEARCH KELUHAN **************************************/
		  searchView1.setOnQueryTextListener(new OnQueryTextListener() {

		   @Override
		   public boolean onQueryTextSubmit(String query) {
		    // TODO Auto-generated method stub

		    // setContentView(R.layout.pencarian);

		    // String input = etCari.getText().toString();

		    Intent intent = new Intent(KunjunganActivity.this,
		      Pencarian.class);
		    intent.putExtra("id_anak", "" + id_child);
		    intent.putExtra("input", "" + query);
		    startActivity(intent);
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
		/************************LIHAT SELENGKAPNYA ******************************/
		
		lihat_selengkapnya.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(lihat_selengkapnya.getText().toString().equals("lihat selengkapnya"))
				{
					lihatselengkapnya.setVisibility(View.VISIBLE);
					lihat_selengkapnya.setText("lihat sedikit");
				}
				else
				{
					lihatselengkapnya.setVisibility(View.GONE);
					lihat_selengkapnya.setText("lihat selengkapnya");
				}
			}
		});
	}
	
	public void InstansiasiProperties()
	{
		db = new Database(this);
		
		txt_Nama_Anak = (TextView) findViewById(R.id.viewNamaAnak);
		txt_BOD_Anak = (TextView) findViewById(R.id.viewBODAnak);
		txt_Umur_Anak = (TextView) findViewById(R.id.viewUMURAnak);
		txt_JK_Anak = (TextView) findViewById(R.id.viewJKAnak);
		txt_GOLDA_Anak = (TextView) findViewById(R.id.viewGOLDAAnak);
		txt_ALAMAT_Anak = (TextView) findViewById(R.id.viewALAMATAnak);
		txt_StatusARV = (TextView) findViewById(R.id.viewStatusARV);
		txt_ARV = (TextView) findViewById(R.id.viewARV);
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
		
		model_anak = new Child_Model();
		visit_model = new Visit_Model();
		image_anak = (ImageView) findViewById(R.id.ij3);
		searchView1 = (SearchView) findViewById(R.id.svKunjungan);
		
		back = (ImageView) findViewById(R.id.btn_back);
		tambah_kunjungan = (LinearLayout) findViewById(R.id.button_tambahkunjungan);
		edit_infoanak = (ImageView) findViewById(R.id.edit_infoanak);
		edit_kunjungan = (ImageView) findViewById(R.id.edit_kunjungan);
		lihatselengkapnya = (LinearLayout) findViewById(R.id.layout_lihatselengkapnya);
		lihat_selengkapnya = (TextView) findViewById(R.id.textview_lihat_selengkapnya);
		
		//inisialisasi kunjungan
		editText_tinggibadan = (EditText) findViewById(R.id.editText_tinggibadan);
		editText_beratbadan = (EditText) findViewById(R.id.editText_beratbadan);
		
		tb = (TextView) findViewById(R.id.tba);
		bb = (TextView) findViewById(R.id.bba);
		lila = (TextView) findViewById(R.id.lilaa);
		visit_date1a = (TextView) findViewById(R.id.ivTglNow);
		visit_date1b = (TextView) findViewById(R.id.ivTglNowa);
		bmi_sekarang = (TextView) findViewById(R.id.BMI3);
		grade = (TextView) findViewById(R.id.BMI2);
		bmi_lalu = (TextView) findViewById(R.id.BMI1);
		visit_date_lalu = (TextView) findViewById(R.id.BMI1a);
		keluhan1 = (TextView) findViewById(R.id.tvKeluhan1a);
		keluhan2 = (TextView) findViewById(R.id.tvKeluhan2a);
		keluhan3  = (TextView) findViewById(R.id.tvKeluhan3a);
		status1 =(TextView) findViewById(R.id.tvStatus1a);
		status2 = (TextView) findViewById(R.id.tvStatus2a);
		status3 = (TextView) findViewById(R.id.tvStatus3aa);
		tindakan1 = (TextView) findViewById(R.id.tvTindakan1a);
		tindakan2 = (TextView) findViewById(R.id.tvTindakan2a);
		tindakan3 = (TextView) findViewById(R.id.tvTindakan3a);
		catatan = (TextView) findViewById(R.id.isiCatatan);
		antar_susu = (TextView) findViewById(R.id.isiAntarSusu);
		antar_vitamin = (TextView) findViewById(R.id.isiAntarVitamin);
		antar_popok = (TextView) findViewById(R.id.isiAntarPopok);
		arv_taken = (TextView) findViewById(R.id.isiAmbilObat);
		skor_rumah = (TextView) findViewById(R.id.skor);	
		foto_anak = (ImageView) findViewById(R.id.ij3);
		foto_rumah = (ImageView) findViewById(R.id.imageRumah);
		FKA = new Intent(KunjunganActivity.this,FormulirKunjunganAnakActivity.class);
		
	}
	
	public void SetTampilanIdentitasAnak()
	{
		
		model_anak = db.getChildIdentityById(id_child);
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
		txt_StatusARV.setText(db.getNameStatusARV(model_anak.getDrug_status_id()));
		//display arv anak
		arv_anak = db.getSemuaARVAnak(id_child);
		String hasil = "";
		//inisialisasi object 
		ChildARV_Model arv = new ChildARV_Model(); 
		arv.SetARVTypeID("-");arv.SetARVDoseID("-");arv.SetARVDoseID1("-");
		for(ChildARV_Model mod_arv : arv_anak)
		{
			//combine two rows into one object of model_arv -> id here is not id but name
			if(arv.GetDrugTypeID().equals("-"))
			{
				arv.SetARVTypeID(db.getNameTypeARV(mod_arv.GetDrugTypeID()));
				arv.SetARVDoseID(db.getNameDose(mod_arv.GetDrugDoseID()));
				continue;
			}
			else
			{
				arv.SetARVDoseID1(db.getNameDose(mod_arv.GetDrugDoseID()));
				if(arv.GetDrugDoseID().equals("-") && arv.GetDrugDoseID1().equals("-"))
				{
					hasil +=""+arv.GetDrugTypeID()+" dosis kosong";
				}
				else if(!arv.GetDrugDoseID().equals("-") && arv.GetDrugDoseID1().equals("-"))
				{
					hasil +=""+arv.GetDrugTypeID()+", "+arv.GetDrugDoseID()+" pagi\n";
				}
				else if(arv.GetDrugDoseID().equals("-") && !arv.GetDrugDoseID1().equals("-"))
				{
					hasil +=""+arv.GetDrugTypeID()+", "+arv.GetDrugDoseID1()+" malam\n";
				}
				else
				{
					hasil +=""+arv.GetDrugTypeID()+", "+arv.GetDrugDoseID()+" pagi, "+arv.GetDrugDoseID1()+" malam\n";
				}
				//set kembali
				arv.SetARVTypeID("-");arv.SetARVDoseID("-");arv.SetARVDoseID1("-");
			}
		}
		
		txt_ARV.setText(hasil);
		txt_NamaAyah.setText(model_anak.getFather_name());
		txt_StatusAyah.setText(db.getNameStatusParent(model_anak.getDad_status_id()));
		txt_NamaIbu.setText(model_anak.getMother_name());
		txt_StatusIbu.setText(db.getNameStatusParent(model_anak.getMom_status_id()));
		txt_NamaPengasuh.setText(model_anak.getCaregiver_name());
		Pengasuh.setText(db.getNameCaregiver(model_anak.getCaregiver_id()));
		txt_NomorTelepon.setText(model_anak.getCaregiver_phone());
		txt_Sekolah.setText(model_anak.getSchool_name());
		txt_Kelas.setText(db.getNameKelas(model_anak.getClass_id()));
		getChildFacilities(id_child);
		txt_Fasilitas.setText(facility_name);
	}
	
	public void setTampilanKunjunganAnak()
	{
		//---->test
		Log.e("Size kunjunganAnak", ""+db.CountAllVisit(id_child));
		
		//----->masi kunj terakhir, untuk case kunjungan 2/3 atau lebih masih belum ditangani :)
		visit_model = db.GetLatestVisit(id_child);
		
		//set photo anak
		if(visit_model.GetVisitDate().equals("00-00-00"))
		{
			Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon);
			image_anak.setImageBitmap(bitmap1);
			foto_rumah.setImageBitmap(bitmap1);
		}
		else
		{
			ArrayList<Image_Model> fotos = new ArrayList<Image_Model>();
			fotos = visit_model.GetPhotos();
			
			File image = new File(visit_model.GetPhotos().get(0).GetImage_path());
			BitmapFactory.Options bmOptions = new BitmapFactory.Options();
			Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(),bmOptions);
			bitmap = Bitmap.createScaledBitmap(bitmap, 120, 120, true);
			image_anak.setImageBitmap(bitmap);
			
			//foto rumah
			for(Image_Model mdl : fotos)
			{
				if(mdl.GetImage_type_id().equals("2"))
				{
					File image_r = new File(mdl.GetImage_path());
					BitmapFactory.Options bmOptions_r = new BitmapFactory.Options();
					Bitmap bitmap_r = BitmapFactory.decodeFile(image_r.getAbsolutePath(),bmOptions_r);
					bitmap_r = Bitmap.createScaledBitmap(bitmap_r, 120, 120, true);
					foto_rumah.setImageBitmap(bitmap_r);
					break;
				}
				continue;
			}
		}
		
		
		tb.setText(visit_model.GetHeight());
		bb.setText(visit_model.GetWeight());
		lila.setText(visit_model.GetLILA());
		//costumize tgl
		String[] tanggal = CustomizeTanggal(visit_model.GetVisitDate());
		visit_date1a.setText(tanggal[0]);
		visit_date1b.setText(tanggal[1]+"."+set2Digit(Integer.parseInt(tanggal[2])));
		bmi_sekarang.setText("BMI: "+visit_model.GetBMIScore());
		//belum bmi dua kunjungan id
		/**********************************/
		
		bmi_lalu.setText("BMI: "+db.getBMITwoLatestVisit(visit_model.GetChildID()).GetBMIScore());
		visit_date_lalu.setText(db.getBMITwoLatestVisit(visit_model.GetChildID()).GetVisitDate());
		//grade belum
		grade.setText("-");
		/**********************************/
		//set keluhan dkk
		ArrayList<Complaint_Model> arr = new ArrayList<Complaint_Model>();
		arr = visit_model.GetComplaints();
		for(Complaint_Model cm : arr)
		{
			if(keluhan1.getText().toString().equals("-"))
			{
				keluhan1.setText(db.getNameComplaint(cm.GetKeluhan()));
				status1.setText(db.getNameComplaintStatus(cm.GetStatusKeluhan()));
				tindakan1.setText(cm.GetTindakan());
			}else if(keluhan2.getText().toString().equals("-"))
			{
				keluhan2.setText(db.getNameComplaint(arr.get(1).GetKeluhan()));
				status2.setText(db.getNameComplaintStatus(arr.get(1).GetStatusKeluhan()));
				tindakan2.setText(arr.get(1).GetTindakan());
			}else if(keluhan3.getText().toString().equals("-"))
			{
				keluhan3.setText(db.getNameComplaint(arr.get(2).GetKeluhan()));
				status3.setText(db.getNameComplaintStatus(arr.get(2).GetStatusKeluhan()));
				tindakan3.setText(arr.get(2).GetTindakan());
			}else {}
		}
		catatan.setText(visit_model.GetNote());
		arv_taken.setText(visit_model.GetARVTaken());
		//score rumah
		skor_rumah.setText(""+db.CountALLEnvironmentScore(visit_model.GetChildID(), visit_model.GetVisitDate())+"/25");
			
		//ambil fasilitas
		ArrayList<Visit_Facility> arr_fa = new ArrayList<Visit_Facility>();
		String antar_susu_="", antar_vitamin_="", antar_popok_="";
		arr_fa = visit_model.GetFasilitasKunjungan();
		if(arr_fa != null)
		{
			for(Visit_Facility i : arr_fa)
			{
				if(i.getFacility_id().equals("FA001"))
				{
					antar_susu_ +="("+i.GetFacilityQty()+") "+db.getNameCostFacility(i.getFacility_cost_id())+",\n";
				}else if(i.getFacility_id().equals("FA002"))
				{
					antar_vitamin_ +="("+i.GetFacilityQty()+") "+db.getNameCostFacility(i.getFacility_cost_id())+",\n ";
				}else if(i.getFacility_id().equals("FA003"))
				{
					antar_popok_ = "("+i.GetFacilityQty()+") "+db.getNameCostFacility(i.getFacility_cost_id())+", \n";
				}
			}
			//set nama fasilitas
			antar_susu.setText(antar_susu_);
			antar_vitamin.setText(antar_vitamin_);
			antar_popok.setText(antar_popok_);
		}else
		{
			bmi_lalu.setText("BMI: -");
			visit_date_lalu.setText("-");
			//jika belum melakukan kunjungan sama sekali
			antar_susu.setText("-");
			antar_vitamin.setText("-");
			antar_popok.setText("-");
			//display score rumah
			skor_rumah.setText("0/25");
		}
			
		
	}
	
	ArrayList<Visit_Model> aLIst = new ArrayList<Visit_Model>(); 
	
	public void setDelapanKunjugan() {
		  final int[] tvBulan = { R.id.ivTgl1, R.id.ivTgl2, R.id.ivTgl3,
		    R.id.ivTgl4, R.id.ivTgl5, R.id.ivTgl6, R.id.ivTgl7, R.id.ivTgl8 };

		  final int[] tvTahun = { R.id.ivTgl1a, R.id.ivTgl2a, R.id.ivTgl3a,
		    R.id.ivTgl4a, R.id.ivTgl5a, R.id.ivTgl6a, R.id.ivTgl7a,
		    R.id.ivTgl8a };

		  final int[] tvRl = { R.id.rl1, R.id.rl2, R.id.rl3, R.id.rl4, R.id.rl5,
		    R.id.rl6, R.id.rl7, R.id.rl8 };
		  
		  ArrayList<Visit_Model> aList = new ArrayList<Visit_Model>();
		  aList = db.get8VisitDate(id_child);

		  int listSize = aList.size();

		  Collections.reverse(aList);

		  for (int j = 0; j < aList.size(); j++) {
		   RelativeLayout rl = (RelativeLayout) findViewById(tvRl[j]);
		   String[] splitedValue = aList.get(j).GetVisitDate().split("-");
		   String gabung = aList.get(j).GetVisitDate();
		   if (aList.size() > 8) {
		    int lastIndex = aList.size() - 1;
		    aList.remove(lastIndex);
		   }
		   TextView tv = (TextView) findViewById(tvBulan[j]);

		   rl.setVisibility(View.VISIBLE);

		   if (aList.get(j).GetVisitTypeID().equals("VT002")) {
		    tv.setBackgroundDrawable(getResources().getDrawable(
		      R.drawable.lila));
		    tv.setText(splitedValue[0]);
		   } else {
		    tv.setBackgroundDrawable(getResources().getDrawable(
		      R.drawable.kotak_tanggal));
		   }

		   tv.setText(splitedValue[0]);

		   TextView tvYear = (TextView) findViewById(tvTahun[j]);
		   int df = Integer.parseInt(splitedValue[2]);
		   tvYear.setText(splitedValue[1] + "." + set2Digit(df));
		   tvYear.setGravity(View.TEXT_ALIGNMENT_CENTER);

		  }

		  
		 }
	public String[] CustomizeTanggal(String tanggal)
	{
		String[] result;
		result = tanggal.split("-");
		return result;
	}
	
	public String GetTimeNow() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		return df.format(c.getTime());
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

	public String formatDigit(String input) {
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
		arr = db.getSemuaFasilitasAnak(id_child);
		for(ChildFacility_Model a: arr)
		{
			facility_name += db.getNameCostFacility(a.getFacility_cost_id()) +"\n";
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
			Intent intent2 = new Intent(KunjunganActivity.this,FormulirKunjunganAnakActivity.class);
			//make validation
			visit_model = db.GetLatestVisit(id_child);
			String date_visit = visit_model.GetVisitDate();
			String date_now = GetTimeNow();
			if(date_visit.equals(date_now))
			{
				Toast.makeText(getApplicationContext(), "Anda sudah melakukan kunjungan anak pada tanggal "+date_now, Toast.LENGTH_SHORT).show();
				Toast.makeText(getApplicationContext(), "Klik edit Kunjungan untuk mengedit data ", Toast.LENGTH_SHORT).show();
				
			}
			else
			{
				intent2.putExtra("id_anak_fka", id_child);
				startActivity(intent2);
				finish();
				Log.i("[sending to FKA]", " " + id_child);
			}
			
			break;

		case R.id.edit_infoanak:
			//String id_child = intent_.getStringExtra("id_child");
			Intent intent3 = new Intent(KunjunganActivity.this,ProfilAnakActivity.class);
			intent3.putExtra("id_anak", id_child);
			startActivity(intent3);
			break;

		case R.id.edit_kunjungan:
			
			//check apakah udah melakukan kunjugan utk pertma kali
			if(visit_model.GetVisitDate().equals("00-00-00"))
			{
				Toast.makeText(getApplicationContext(), "Edit kunjungan dapat dilakukan jika sudah melakukan kunjungan", Toast.LENGTH_SHORT).show();
				Toast.makeText(getApplicationContext(), "Klik menu Tambah Kunjungan", Toast.LENGTH_SHORT).show();
			}
			else
			{
				Intent intent4 = new Intent(KunjunganActivity.this,FormulirKunjunganAnakActivity.class);
				intent4.putExtra("id_anak_fka1", ""+visit_model.GetChildID());
				intent4.putExtra("Visit_Model_Update", visit_model);
				startActivity(intent4);
				Log.e("[Sending] FKA", "id anak "+visit_model.GetChildID()+" visit type "+visit_model.GetVisitTypeID()+" tb "+visit_model.GetHeight()+" bb "+visit_model.GetWeight()+" lila "+visit_model.GetLILA());
			}
			
			break;

		default:
			break;
		}
	}

}
