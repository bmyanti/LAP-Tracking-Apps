package com.example.laptrackingapps;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.example.databaselap.TR_Image;
import com.example.databaselap.TR_Visit;
import com.example.databaselap.TR_Visit_Environment;
import com.example.modellap.Complaint_Model;
import com.example.modellap.Environment_Model;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class FormulirKunjunganRumahActivity extends Activity {

	// untuk foto rumah (kamera)
	private static final int Image_take = 1;

	LinearLayout btn_simpan;
	RadioGroup rg_ringkas, rg_rapi, rg_resik, rg_rawat, rg_rajin;
	RadioButton r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14,
			r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25;
	TextView textview_total, text_total;
	int sum1, sum2, sum3, sum4, sum5, sum;

	final Context context = this;

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
	Visit_Model model_visit;
	Environment_Model model_environment;

	TR_Visit tabel_visit;
	TR_Visit_Environment tabel_visit_environment;
	TR_Image tabel_image;
	ArrayList<Complaint_Model> complaints = new ArrayList<Complaint_Model>();

	ImageView image, btn_back, foto_rumah1, foto_rumah2, foto_rumah3;
	Button btn_foto_rumah1, btn_foto_rumah2, btn_foto_rumah3;
	Bitmap bitmap1, thumbnail, thumbnail1;
	Boolean foto1 = false, foto2 = false, foto3 = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_formulir_kunjungan_rumah);

		tabel_visit = new TR_Visit(getApplicationContext());
		tabel_visit_environment = new TR_Visit_Environment(
				getApplicationContext());
		tabel_image = new TR_Image(getApplicationContext());

		// get intent
		Intent i = getIntent();
		Bundle extras = i.getExtras();
		if (extras.containsKey("id")) {
			// Do stuff because extra has been added
			id_child = extras.getString("id");
			model_visit = (Visit_Model) extras
					.getParcelable("Object_VisitModel");
			Log.e("Intentku", "true" + extras.getString("id"));
		} else {
			// imposibble hehehe
			/**************************/
		}

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

		// foto rumah
		btn_foto_rumah1 = (Button) findViewById(R.id.btn_foto_rumah1);
		btn_foto_rumah2 = (Button) findViewById(R.id.btn_foto_rumah2);
		btn_foto_rumah3 = (Button) findViewById(R.id.btn_foto_rumah3);
		image = (ImageView) findViewById(R.id.image);

		// bagian foto rumah
		foto_rumah1 = (ImageView) findViewById(R.id.foto_rumah1);
		foto_rumah2 = (ImageView) findViewById(R.id.foto_rumah2);
		foto_rumah3 = (ImageView) findViewById(R.id.foto_rumah3);

		bitmap1 = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.new_button_camera);
		foto_rumah1.setImageBitmap(bitmap1);
		foto_rumah2.setImageBitmap(bitmap1);
		foto_rumah3.setImageBitmap(bitmap1);

		btn_foto_rumah1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (getDrawableImage(foto_rumah1) == bitmap1) {

					image();
					foto1 = true;
					// foto_anak1.setImageBitmap(thumbnail);
				} else {
					alertImage(foto_rumah1);
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
					alertImage(foto_rumah2);
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
					alertImage(foto_rumah3);
				}
			}
		});
		// assign nilai environment kedalam objek model_visit
		environments.add(em1);
		environments.add(em2);
		environments.add(em3);
		environments.add(em4);
		environments.add(em5);

		model_visit.setEnvironments(environments);

	}

	// public void SetRadioButton(ArrayList<Environment_Model> model)
	// {
	//
	// }
	// bagian foto rumah

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.formulir_kunjungan_rumah, menu);
		return true;
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn_back:
			Intent back_ka = new Intent(FormulirKunjunganRumahActivity.this,
					FormulirKunjunganAnakActivity.class);
			back_ka.putExtra("id_child_ka", id_child);
			Log.e("Sending intent back into fk_anak", "" + id_child);
			back_ka.putExtra("Object_VisitModel1", model_visit);
			startActivity(back_ka);
			break;

		case R.id.button_simpan:
			final Dialog dialog = new Dialog(context);
			dialog.setContentView(R.layout.activity_warning_dialog);

			LinearLayout btn_kembali = (LinearLayout) dialog
					.findViewById(R.id.button_kembali);
			LinearLayout btn_simpan2 = (LinearLayout) dialog
					.findViewById(R.id.button_simpan2);

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
					complaints = model_visit.GetComplaints();
					for (Complaint_Model a : complaints) {
						// simpan ke tabel tr visit
						// tabel visit environment
						// tabel image
						// tabel child facility
						// dummy

						tabel_visit.InsertToTableVisit(id_child,
								a.GetKeluhan(), a.GetStatusKeluhan(),
								GetTimeNow(), model_visit.GetVisitTypeID(),
								model_visit.GetARVTaken(),
								model_visit.GetHeight(), model_visit.GetLILA(),
								model_visit.GetWeight(), a.GetTindakan(),
								model_visit.GetNote(), "0", "-", "-", "-", "-",
								"-");
						Log.e("Insert ke tabel tr visit",
								"child_id " + 1 + "+ keluhan " + a.GetKeluhan()
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
										+ "reminder id 0");
					}
					Log.e("Insert ke tabel Visit", "");

					// insert ke tabel tr visit

					for (Environment_Model a : environments) {
						tabel_visit_environment.InsertEnvironmentScore(
								GetTimeNow(), id_child, a.GetEnvironmentID(),
								a.GetEnvironmentScore());
						Log.e("Environments",
								"ID Environment " + a.GetEnvironmentID()
										+ "Score " + a.GetEnvironmentScore());
					}

					// simpan semua foto
					Toast.makeText(getApplicationContext(),
							"Selamat Anda berhasil menambahkan kunjungan Anak",
							Toast.LENGTH_SHORT).show();
					Intent intent3 = new Intent(getApplication(),
							KunjunganActivity.class);
					intent3.putExtra("child_id_fkra", id_child);
					startActivity(intent3);
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

	public void image() {
		// create class object
		gps = new GPSTracker(FormulirKunjunganRumahActivity.this);

		// check if GPS enabled
		if (gps.canGetLocation()) {

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
				thumbnail1 = Bitmap.createScaledBitmap(thumbnail1, 258, 150,
						true);
				//
				ByteArrayOutputStream bytes = new ByteArrayOutputStream();
				thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
				thumbnail1.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

				File destination = new File(
						Environment.getExternalStorageDirectory(),
						System.currentTimeMillis() + ".jpg");
				FileOutputStream fo;

				// final String[] imageColumns = { MediaStore.Images.Media._ID,
				// MediaStore.Images.Media.DATA };
				// final String imageOrderBy = MediaStore.Images.Media._ID
				// + " DESC";
				// Cursor imageCursor = managedQuery(
				// MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
				// imageColumns, null, null, imageOrderBy);
				// ArrayList<String> result = new ArrayList<String>(
				// imageCursor.getCount());
				// if (imageCursor.moveToFirst()) {
				// // int id = imageCursor.getInt(imageCursor
				// // .getColumnIndex(MediaStore.Images.Media._ID));
				// // Path = imageCursor.getString(imageCursor
				// // .getColumnIndex(MediaStore.Images.Media.DATA));
				// //
				// final int dataColumn = imageCursor
				// .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				// do {
				// final String data1 = imageCursor.getString(dataColumn);
				// result.add(data1);
				// } while (imageCursor.moveToNext());
				// Toast.makeText(getApplicationContext(),
				// "yuhuuuuu " + result, Toast.LENGTH_LONG).show();
				// Log.w("ini pathhhhhhhhhh", result.toString());
				//
				// }
				// imageCursor.close();

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

				// looping imageview to set image bitmap
				ImageView ivrumah1, ivrumah2, ivrumah3;

				ivrumah1 = (ImageView) findViewById(R.id.foto_rumah1);
				ivrumah2 = (ImageView) findViewById(R.id.foto_rumah2);
				ivrumah3 = (ImageView) findViewById(R.id.foto_rumah3);

				if (getDrawableImage(ivrumah1) == bitmap1 && foto1 == true) {

					ivrumah1.setImageBitmap(thumbnail);
				}

				if (getDrawableImage(ivrumah2) == bitmap1 && foto2 == true) {
					ivrumah2.setImageBitmap(thumbnail);
				}

				if (getDrawableImage(ivrumah3) == bitmap1 && foto3 == true) {
					ivrumah3.setImageBitmap(thumbnail);
				}
			}
		}
	}

	public void alertImage(final ImageView input) {
		final Dialog dialog = new Dialog(context);

		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.custom_dialog);

		final Dialog dialogConfirm = new Dialog(context);

		dialogConfirm.requestWindowFeature(Window.FEATURE_NO_TITLE);

		ImageView imageDialog = (ImageView) dialog.findViewById(R.id.image);
		imageDialog.setImageBitmap(getDrawableImage(input).createScaledBitmap(
				getDrawableImage(input), 258, 150, true));
		LinearLayout linHapus = (LinearLayout) dialog
				.findViewById(R.id.btnHapus);

		linHapus.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				dialogConfirm.setContentView(R.layout.confirm_delete);

				LinearLayout linYakin = (LinearLayout) dialogConfirm
						.findViewById(R.id.btnYakinHapus);
				LinearLayout linKembali = (LinearLayout) dialogConfirm
						.findViewById(R.id.button_kembali);

				linYakin.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						// TODO Auto-generated method stub
						input.setImageBitmap(bitmap1);
						foto1 = false;
						foto2 = false;
						foto3 = false;

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

}
