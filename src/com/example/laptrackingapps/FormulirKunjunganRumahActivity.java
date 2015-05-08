package com.example.laptrackingapps;

import java.io.File;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class FormulirKunjunganRumahActivity extends Activity {
	
	//untuk foto rumah (kamera)
	private static final int Image_take = 1;
	
	ImageView btn_back, foto_rumah1, foto_rumah2, foto_rumah3;
	LinearLayout btn_simpan;
	
	final Context context = this;
	
	//GPS Tracker class
	GPSTracker gps;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_formulir_kunjungan_rumah);
		
		btn_back = (ImageView)  findViewById(R.id.btn_back);
		btn_simpan = (LinearLayout) findViewById(R.id.button_simpan);
		
		//bagian foto rumah
		foto_rumah1 = (ImageView) findViewById(R.id.foto_rumah1);
		foto_rumah1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				// create class object
		        gps = new GPSTracker(FormulirKunjunganRumahActivity.this);
				// check if GPS enabled		
		        if(gps.canGetLocation()){
		        	Intent intent4 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					
					File file = new File(android.os.Environment.getExternalStorageDirectory(), "LAPTrackingApps/img_" + String.valueOf(System.currentTimeMillis())
							+ ".jpg");
					intent4.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
					startActivityForResult(intent4, Image_take);
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
		
		foto_rumah2 = (ImageView) findViewById(R.id.foto_rumah2);
		foto_rumah2.setOnClickListener(new OnClickListener() {
			
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
		
		foto_rumah3 = (ImageView) findViewById(R.id.foto_rumah3);
		foto_rumah3.setOnClickListener(new OnClickListener() {
			
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
	}
	
	//bagian foto rumah
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
        super.onActivityResult(requestCode, resultCode, data);
        //foto_rumah1
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
				foto_rumah1.setImageBitmap(bitmap);
				//String path = "";
				file.delete();
				
             } catch (Exception e) {
                 e.printStackTrace();
                 System.out.println(file.getAbsolutePath());
             		}
        		}
        	}
        //foto_rumah2
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
				foto_rumah2.setImageBitmap(bitmap2);
				//String path = "";
				file.delete();
				
             } catch (Exception e) {
                 e.printStackTrace();
                 System.out.println(file.getAbsolutePath());
             		}
        		}
        	}
        //foto_rumah3
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
				foto_rumah3.setImageBitmap(bitmap);
				//String path = "";
				file.delete();
				
             } catch (Exception e) {
                 e.printStackTrace();
                 System.out.println(file.getAbsolutePath());
             		}
        		}
        	}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.formulir_kunjungan_rumah, menu);
		return true;
	}
	
	public void onClick (View view){
		switch (view.getId()) {
		case R.id.btn_back:
			Intent intent = new Intent(FormulirKunjunganRumahActivity.this, FormulirKunjunganAnakActivity.class);
			startActivity(intent);
			break;
			
		case R.id.button_simpan:
			LayoutInflater layoutinflater = LayoutInflater.from(context);
			View promptView = layoutinflater.inflate(R.layout.activity_warning_dialog, null);
			
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
			alertDialogBuilder.setView(promptView);
			
			final LinearLayout btn_kembali = (LinearLayout) promptView.findViewById(R.id.button_kembali);
			final LinearLayout btn_simpan2 = (LinearLayout) promptView.findViewById(R.id.button_simpan2);
			
			btn_kembali.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent2  = new Intent(getApplication(),FormulirKunjunganRumahActivity.class);
					startActivity(intent2);
					
				}
			});
			
			btn_simpan2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent3 = new Intent(getApplication(), KunjunganActivity.class);
					startActivity(intent3);
				}
			});
			
			AlertDialog alertD = alertDialogBuilder.create();
			alertD.show();
			break;

		default:
			break;
		}
	}

}
