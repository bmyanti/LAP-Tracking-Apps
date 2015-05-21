package com.example.laptrackingapps;

import java.io.File;
import java.util.Calendar;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class FormulirKunjunganAnakActivity extends Activity {
	
	//untuk foto anak (kamera)
	private static final int Image_take = 1;
	
	ImageView back, foto_anak1, foto_anak2, foto_anak3, foto_anak4, foto_anak5, foto_anak6;
	Button tambah_keluhan;
	LinearLayout layout_tambahkeluhan, layout_keluhan, layout_status, layout_tindakan;
	LinearLayout button_lanjut;
	
	// GPSTracker class
		GPSTracker gps;
		
	//untuk tanggal ambil arv
		int hour, minute, mYear, mMonth, mDay;
		static final int TIME_DIALOG_ID = 0;
		static final int DATE_DIALOG_ID = 1;
		private EditText txtDate;
		private String[] arrMonth = { "Januari", "Februari", "Maret", "April", "Mei", "Juni",
				"Juli", "Agustus", "September", "Oktober", "November", "Desember" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_formulir_kunjungan_anak);
		
		txtDate = (EditText) findViewById(R.id.edittext_ttl);
		button_lanjut  =(LinearLayout) findViewById(R.id.button_lanjut);
		back = (ImageView) findViewById(R.id.btn_back);
		layout_tambahkeluhan = (LinearLayout) findViewById(R.id.linearlayout_tambahkeluhan);
		tambah_keluhan = (Button) findViewById(R.id.button_tambahkeluhan);
		tambah_keluhan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//TextView tv1 = new TextView(v.getContext());
	            //tv1.setText("TAMBAH KELUHAN");
	            //layout_tambahkeluhan.addView(tv1);
				//((LinearLayout)findViewById(R.id.linearlayout_tambahkeluhan)).addView(layout_tambahkeluhan);
				LinearLayout layout_tambahkeluhan = (LinearLayout) findViewById(R.id.linearlayout_tambahkeluhan);
				LinearLayout tambah_keluhan = new LinearLayout(v.getContext());
				//tambah_keluhan.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
				layout_tambahkeluhan.addView(tambah_keluhan);
			    //getLayoutInflater().inflate(R.id.tambah_keluhan, layout_tambahkeluhan);
				//layout_tambahkeluhan.setVisibility(View.VISIBLE);
			}
		});
		
		//bagian foto anak
		foto_anak1 = (ImageView) findViewById(R.id.foto_anak1);
		foto_anak1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				
				// create class object
		        gps = new GPSTracker(FormulirKunjunganAnakActivity.this);

				// check if GPS enabled		
		        if(gps.canGetLocation()){
		        	
		        	Intent intent3 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					
					File file = new File(android.os.Environment.getExternalStorageDirectory(), "LAPTrackingApps/img_" + String.valueOf(System.currentTimeMillis())
							+ ".jpg");
					intent3.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
					startActivityForResult(intent3, Image_take);
		        	
		        	
		        	
		        	
		        //}
		        //else if (gps.canGetLocation())
		        //{
		        	double latitude = gps.getLatitude();
		        	double longitude = gps.getLongitude();
		        	
		        	// \n is for new line
		        	Toast.makeText(getApplicationContext(), "Lokasi Anda - \nLatitude : " + latitude + "\nLongitude : " + longitude, Toast.LENGTH_LONG).show();	
		        	
		        }

		        else {
		        	// can't get location
		        	// GPS or Network is not enabled
		        	// Ask user to enable GPS/network in settings
		        	gps.showSettingsAlert();
		        }
		        
		        
				
			}
		});
		
		foto_anak2 = (ImageView) findViewById(R.id.foto_anak2);
		foto_anak2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				Intent intent4 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				
				File file = new File(android.os.Environment.getExternalStorageDirectory(), "LAPTrackingApps/img_" + String.valueOf(System.currentTimeMillis())
						+ ".jpg");
				intent4.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
				startActivityForResult(intent4, Image_take);
				
			}
		});
		
		foto_anak3 = (ImageView) findViewById(R.id.foto_anak3);
		foto_anak3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				Intent intent5 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				
				File file = new File(android.os.Environment.getExternalStorageDirectory(), "LAPTrackingApps/img_" + String.valueOf(System.currentTimeMillis())
						+ ".jpg");
				intent5.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
				startActivityForResult(intent5, Image_take);
				
			}
		});
		
		foto_anak4 = (ImageView) findViewById(R.id.foto_anak4);
		foto_anak4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				Intent intent6 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				
				File file = new File(android.os.Environment.getExternalStorageDirectory(), "LAPTrackingApps/img_" + String.valueOf(System.currentTimeMillis())
						+ ".jpg");
				intent6.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
				startActivityForResult(intent6, Image_take);
				
			}
		});
		
		foto_anak5 = (ImageView) findViewById(R.id.foto_anak5);
		foto_anak5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				Intent intent7 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				
				File file = new File(android.os.Environment.getExternalStorageDirectory(), "LAPTrackingApps/img_" + String.valueOf(System.currentTimeMillis())
						+ ".jpg");
				intent7.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
				startActivityForResult(intent7, Image_take);
				
			}
		});
		
		foto_anak6 = (ImageView) findViewById(R.id.foto_anak6);
		foto_anak6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				Intent intent8 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				
				File file = new File(android.os.Environment.getExternalStorageDirectory(), "LAPTrackingApps/img_" + String.valueOf(System.currentTimeMillis())
						+ ".jpg");
				intent8.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
				startActivityForResult(intent8, Image_take);
				
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

		int years = now.get(Calendar.YEAR)
				- tanggallahir.get(Calendar.YEAR);
		int months = now.get(Calendar.MONTH)
				- tanggallahir.get(Calendar.MONTH);
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
		String umur = years + " tahun " + months + " bulan " + days
				+ " hari";
	}
	
	//foto anak
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
        super.onActivityResult(requestCode, resultCode, data);
        //foto_anak1
        if(resultCode == RESULT_OK){
        	if(requestCode == Image_take){
        		/*Bitmap photo = (Bitmap) data.getExtras().get("data"); 
                tambahfoto.setImageBitmap(photo);*/
        		File file = new File(Environment.getExternalStorageDirectory(), "LAPTrackingApps");
        		for (File temp : file.listFiles()) {
//					if(temp.getName().equals("LAPTrackingApps.jpg")){
//						file = temp;
//						System.out.println(file.getAbsolutePath());
//						break;
//					}
					file = temp;
				}
        	try {
				Bitmap bitmap;
				BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
				
				bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), bitmapOptions); 
				foto_anak1.setImageBitmap(bitmap);
				//String path = "";
				file.delete();
				
             } catch (Exception e) {
                 e.printStackTrace();
                 System.out.println(file.getAbsolutePath());
             		}
        		}
        	}
        //foto_anak2
        else if (resultCode == RESULT_OK) {
        	if(requestCode == Image_take){
        		/*Bitmap photo = (Bitmap) data.getExtras().get("data"); 
                tambahfoto.setImageBitmap(photo);*/
        		File file = new File(Environment.getExternalStorageDirectory(), "LAPTrackingApps");
        		for (File temp : file.listFiles()) {
//					if(temp.getName().equals("LAPTrackingApps.jpg")){
//						file = temp;
//						System.out.println(file.getAbsolutePath());
//						break;
//					}
					file = temp;
				}
        	try {
				Bitmap bitmap2;
				BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
				
				bitmap2 = BitmapFactory.decodeFile(file.getAbsolutePath(), bitmapOptions); 
				foto_anak2.setImageBitmap(bitmap2);
				//String path = "";
				file.delete();
				
             } catch (Exception e) {
                 e.printStackTrace();
                 System.out.println(file.getAbsolutePath());
             		}
        		}
        	}
        //foto_anak3
        else if (resultCode == RESULT_OK) {
        	if(requestCode == Image_take){
        		/*Bitmap photo = (Bitmap) data.getExtras().get("data"); 
                tambahfoto.setImageBitmap(photo);*/
        		File file = new File(Environment.getExternalStorageDirectory(), "LAPTrackingApps");
        		for (File temp : file.listFiles()) {
//					if(temp.getName().equals("LAPTrackingApps.jpg")){
//						file = temp;
//						System.out.println(file.getAbsolutePath());
//						break;
//					}
					file = temp;
				}
        	try {
				Bitmap bitmap;
				BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
				
				bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), bitmapOptions); 
				foto_anak3.setImageBitmap(bitmap);
				//String path = "";
				file.delete();
				
             } catch (Exception e) {
                 e.printStackTrace();
                 System.out.println(file.getAbsolutePath());
             		}
        		}
        	}
        //foto_anak4
        else if (resultCode == RESULT_OK) {
        	if(requestCode == Image_take){
        		/*Bitmap photo = (Bitmap) data.getExtras().get("data"); 
                tambahfoto.setImageBitmap(photo);*/
        		File file = new File(Environment.getExternalStorageDirectory(), "LAPTrackingApps");
        		for (File temp : file.listFiles()) {
//					if(temp.getName().equals("LAPTrackingApps.jpg")){
//						file = temp;
//						System.out.println(file.getAbsolutePath());
//						break;
//					}
					file = temp;
				}
        	try {
				Bitmap bitmap;
				BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
				
				bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), bitmapOptions); 
				foto_anak4.setImageBitmap(bitmap);
				//String path = "";
				file.delete();
				
             } catch (Exception e) {
                 e.printStackTrace();
                 System.out.println(file.getAbsolutePath());
             		}
        		}
        	}
        //foto_anak5
        else if (resultCode == RESULT_OK) {
        	if(requestCode == Image_take){
        		/*Bitmap photo = (Bitmap) data.getExtras().get("data"); 
                tambahfoto.setImageBitmap(photo);*/
        		File file = new File(Environment.getExternalStorageDirectory(), "LAPTrackingApps");
        		for (File temp : file.listFiles()) {
//					if(temp.getName().equals("LAPTrackingApps.jpg")){
//						file = temp;
//						System.out.println(file.getAbsolutePath());
//						break;
//					}
					file = temp;
				}
        	try {
				Bitmap bitmap;
				BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
				
				bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), bitmapOptions); 
				foto_anak5.setImageBitmap(bitmap);
				//String path = "";
				file.delete();
				
             } catch (Exception e) {
                 e.printStackTrace();
                 System.out.println(file.getAbsolutePath());
             		}
        		}
        	}
        //foto_anak6
        else if(resultCode == RESULT_OK){
        	if(requestCode == Image_take){
        		/*Bitmap photo = (Bitmap) data.getExtras().get("data"); 
                tambahfoto.setImageBitmap(photo);*/
        		File file = new File(Environment.getExternalStorageDirectory(), "LAPTrackingApps");
        		for (File temp : file.listFiles()) {
//					if(temp.getName().equals("LAPTrackingApps.jpg")){
//						file = temp;
//						System.out.println(file.getAbsolutePath());
//						break;
//					}
					file = temp;
				}
        	try {
				Bitmap bitmap;
				BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
				
				bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), bitmapOptions); 
				foto_anak6.setImageBitmap(bitmap);
				//String path = "";
				file.delete();
				
             } catch (Exception e) {
                 e.printStackTrace();
                 System.out.println(file.getAbsolutePath());
             		}
        		}
        	}
	}
	
	/*private void layout_tambahkeluhan(Button tambah_keluhan) {
		// TODO Auto-generated method stub
		layout_keluhan = (LinearLayout) findViewById(R.id.layout_keluhan);
		layout_status = (LinearLayout) findViewById(R.id.layout_status);
		layout_tindakan = (LinearLayout) findViewById(R.id.layout_tindakan);
		ImageView image = new ImageView(null);
		image.setImageResource(R.drawable.lap_logo);
		layout_tambahkeluhan.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		layout_tambahkeluhan.addView(layout_tambahkeluhan);
		
	}*/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.formulir_kunjungan_anak, menu);
		return true;
	}
	
	public void onClick (View view){
		switch (view.getId()) {
		case R.id.btn_back:
			Intent intent = new Intent(FormulirKunjunganAnakActivity.this, KunjunganActivity.class);
			startActivity(intent);
			break;
			
		case R.id.button_lanjut :
			Intent intent2 = new Intent(FormulirKunjunganAnakActivity.this, FormulirKunjunganRumahActivity.class);
			startActivity(intent2);
			
		//case R.id.button_tambahkeluhan:
			//Intent intent2 = new Intent(FormulirKunjunganAnakActivity.this, ProfilAnakActivity.class);
			//startActivity(intent2);
			//layout_tambahkeluhan = (LinearLayout) findViewById(R.id.linearlayout_tambahkeluhan);
			//tambah_keluhan.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	        //layout_tambahkeluhan.addView(tambah_keluhan);
			/*for (int i = 1; i < 10; i++) {
		        tambah_keluhan.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		        tambah_keluhan.setText("" + i);
		        tambah_keluhan.setId(i);
		        layout_tambahkeluhan.addView(tambah_keluhan);
		        //((Button) findViewById(i)).setOnClickListener(this);
		    }*/
			//tambah_keluhan.setVisibility(View.VISIBLE);
			//LayoutInflater layoutinflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			//View vi = layoutinflater.inflate(R.layout.activity_formulir_kunjungan_anak, null);
			
			//layout_tambahkeluhan = (LinearLayout) vi.findViewById(R.id.linearlayout_tambahkeluhan);
			
			//ViewGroup vg = (ViewGroup) findViewById(R.id.button_tambahkeluhan);
			//vg.addView(vi, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			//break;

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
	
	//untuk tanggal ambil arv
	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			//String sdate = arrMonth[mMonth] + " " + LPad(mDay + "", "0", 2) + ", " + mYear;
			//String sdate =  ""+year+"/"+(monthOfYear + 1 )+"/"+ LPad(mDay + "", "0",2) ;
			String sdate = LPad(mDay + "", "0", 2) + " " +  arrMonth[mMonth] + " " + mYear;
			txtDate.setText(sdate);
			// TODO Auto-generated method stub

		}
	};
	
	
	//untuk tanggal ambil arv
	private static String LPad(String schar, String spad, int len) {
		String sret = schar;
		for (int i = sret.length(); i < len; i++) {
			sret = spad + sret;
		}
		return new String(sret);
	}

}
