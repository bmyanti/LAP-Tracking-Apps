package com.example.laptrackingapps;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
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


import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class ProfilAnakActivity extends Activity implements OnItemSelectedListener {
	
	final Context context = this;
	ImageView back, tambah_foto;
	LinearLayout simpan_profil;
	EditText txtName;
	Button susu, vitamin, popok;
	
	//untuk kamera
	private static final int Image_take = 1;
	private static final int Image_pick = 2;
	
	//untuk tanggal lahir
	int hour, minute, mYear, mMonth, mDay;
	static final int TIME_DIALOG_ID = 0;
	static final int DATE_DIALOG_ID = 1;
	private EditText txtDate;
	private String[] arrMonth = { "Januari", "Februari", "Maret", "April", "Mei", "Juni",
			"Juli", "Agustus", "September", "Oktober", "November", "Desember" };
	
	//spinner golongan darah
	String item_golongandarah[] = { "A", "B", "O", "AB" };
	Spinner golongan_darah;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_profil_anak);
		
		txtName = (EditText) findViewById(R.id.edit_nama_anak);
		txtDate = (EditText) findViewById(R.id.editTextTtl);
		back = (ImageView) findViewById(R.id.btn_back);
		simpan_profil = (LinearLayout) findViewById(R.id.button_simpanprofil);
		golongan_darah = (Spinner) findViewById(R.id.spinner_golongandarah);
		tambah_foto = (ImageView) findViewById(R.id.tambahfoto);
		susu = (Button) findViewById(R.id.button_susu);
		vitamin = (Button) findViewById(R.id.button_vitamin);
		popok = (Button) findViewById(R.id.button_popok);
		
		
		Spinner spinner1 = (Spinner) findViewById(R.id.spinner_golongandarah);
		spinner1.setOnItemSelectedListener(this);
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, item_golongandarah);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner1.setAdapter(adapter1);
		
		//btn simpan profil anak
		simpan_profil.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				int TIMEOUT_MILLISEC = 10000; 
				HttpParams httpParams = new BasicHttpParams();
				HttpConnectionParams.setConnectionTimeout(httpParams, TIMEOUT_MILLISEC);
				HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_MILLISEC);
				HttpClient client = new DefaultHttpClient(httpParams);
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3); 
				nameValuePairs.add(new BasicNameValuePair("nama", txtName.getText().toString()));
				nameValuePairs.add(new BasicNameValuePair("date", txtDate.getText().toString()));
				nameValuePairs.add(new BasicNameValuePair("goldar", golongan_darah.toString()));
				
				try {
			    	HttpPost request = new HttpPost("http://192.168.1.171/jsonn/addkota.php");
			    	request.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					
					HttpResponse response = client.execute(request);
				}
				catch(Exception e){
					Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
				Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
				ListAnakActivity.la.RefreshList();
				finish();
			}
		});
		
		tambah_foto.setOnClickListener(new View.OnClickListener() {
			//button tambah foto
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LayoutInflater layoutinflater = LayoutInflater.from(context);
				View promptView = layoutinflater.inflate(R.layout.activity_foto_dialog, null);
				
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
				alertDialogBuilder.setView(promptView);
				
				final Button btn_kamera = (Button) promptView.findViewById(R.id.button_kamera);
				final Button btn_galeri = (Button) promptView.findViewById(R.id.button_galeri);
				
				btn_kamera.setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
						
						File file = new File(android.os.Environment.getExternalStorageDirectory(), "LAPTrackingApps/img_" + String.valueOf(System.currentTimeMillis())
								+ ".jpg");
						intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
						startActivityForResult(intent2, Image_take);
					}
				});
				
				btn_galeri.setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent3 = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
						startActivityForResult(intent3, Image_pick);
						
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
	
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
        super.onActivityResult(requestCode, resultCode, data);
        
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
				tambah_foto.setImageBitmap(bitmap);

				//String path = "";
				file.delete();
				
				/*OutputStream outFile = null;
				File file1 = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
				
				try {
					outFile = new FileOutputStream(file1);
					bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
					outFile.flush();
					outFile.close();
                 } catch (FileNotFoundException e) {
                     e.printStackTrace();
                 } catch (IOException e) {
                     e.printStackTrace();
                 } catch (Exception e) {
                     e.printStackTrace();
                 }*/
             } catch (Exception e) {
                 e.printStackTrace();
                 System.out.println(file.getAbsolutePath());
             		}
        		}
        	}
        	
        	else if (requestCode == Image_pick) {
        		Uri uri = data.getData();
				String[] filePath = {MediaStore.Images.Media.DATA};
				Cursor cursor = getContentResolver().query(uri, filePath, null, null, null);
				cursor.moveToFirst();
				int columnIndex = cursor.getColumnIndex(filePath[0]);
				String picturePath = cursor.getString(columnIndex);
				cursor.close();
				tambah_foto.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            }
        }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profil_anak, menu);
		return true;
	}
	
	
	
	public void onClick (View view){
		switch (view.getId()) {
		case R.id.btn_back:
			Intent intent = new Intent(ProfilAnakActivity.this, ListAnakActivity.class);
			startActivity(intent);
			break;
			
		/*case R.id.button_simpanprofil:
			Intent intent4 = new Intent(ProfilAnakActivity.this, ListAnakActivity.class);
			startActivity(intent4);
			break;*/
			
		case R.id.button_susu :
			LayoutInflater layoutinflater = LayoutInflater.from(context);
			View promptView = layoutinflater.inflate(R.layout.activity_fasilitas_susu, null);
			
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
			alertDialogBuilder.setView(promptView);
			
			final LinearLayout btn_simpan = (LinearLayout) promptView.findViewById(R.id.btn_simpan);
			btn_simpan.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent simpan  = new Intent(getApplication(),ProfilAnakActivity.class);
					startActivity(simpan);
					
				}
			});
			AlertDialog alertD = alertDialogBuilder.create();
			alertD.show();
			break;
			
		case R.id.button_vitamin :
			LayoutInflater layoutinflater1 = LayoutInflater.from(context);
			View promptView1 = layoutinflater1.inflate(R.layout.activity_fasilitas_vitamin, null);
			
			AlertDialog.Builder alertDialogBuilder1 = new AlertDialog.Builder(context);
			alertDialogBuilder1.setView(promptView1);
			
			final LinearLayout btn_simpan1 = (LinearLayout) promptView1.findViewById(R.id.btn_simpan1);
			
			btn_simpan1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent simpan1  = new Intent(getApplication(),ProfilAnakActivity.class);
					startActivity(simpan1);
					
				}
			});
			AlertDialog alert = alertDialogBuilder1.create();
			alert.show();
			break;
			
		case R.id.button_popok :
			LayoutInflater layoutinflater2 = LayoutInflater.from(context);
			View promptView2 = layoutinflater2.inflate(R.layout.activity_fasilitas_popok, null);
			
			AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(context);
			alertDialogBuilder2.setView(promptView2);
			
			final LinearLayout btn_simpan2 = (LinearLayout) promptView2.findViewById(R.id.btn_simpan2);
			
			btn_simpan2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent simpan2 = new Intent(getApplication(),ProfilAnakActivity.class);
					startActivity(simpan2);
					
				}
			});
			AlertDialog alert1 = alertDialogBuilder2.create();
			alert1.show();
			break;

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
	
	//untuk tanggal lahir
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
	
	
	//untuk tanggal lahir
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
