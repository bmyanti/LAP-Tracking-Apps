package com.example.laptrackingapps;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.databaselap.TM_Child;
import com.example.modellap.TM_Child_Model;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import learn2crack.asynctask.library.JSONParser;

public class ListAnakActivity extends Activity implements OnQueryTextListener {

	//search
	ListView lv;
    SearchView search_view;
    private List<TM_Child_Model> nameChildlist = null;
   // ArrayList<itemSearch> listArray = new ArrayList<itemSearch>();
    ArrayList<String> arrSt = new ArrayList<String>();
   ArrayList<String> arr6 = new ArrayList<String>();

    ArrayList<String> countries;
    ArrayAdapter<String> adapter;
    
	ImageView btn_back, reminder_lab, reminder_obat;
	LinearLayout tambah_profil, daftar_anak1;
	GridView gridView;
	ArrayList<Item> gridArray = new ArrayList<Item>();
	CustomGridViewAdapter customGridAdapter;

	final Context context = this;

	private JSONObject jObject;
	private JSONArray jArray;
	private String jsonResult = "";
	private String url = "http://192.168.1.171/jsonn/daftarkota.php";
	private String url2 = "http://192.168.1.171/jsonn/delkota.php";
	private String[] daftarid;
	private String[] daftarnama;
	private String[] daftardate;
	private String[] daftargoldar;
	Menu menu;
	public static ListAnakActivity la;

	public String[] getDaftarnama() {
		return daftarnama;
	}

	public void setDaftarnama(String[] daftarnama) {
		this.daftarnama = daftarnama;
		Log.d("setDaftarnama", daftarnama.toString());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_list_anak);

		lv = (ListView) findViewById(R.id.list_view);
        search_view = (SearchView) findViewById(R.id.search_view);

		
        String[] locales = Locale.getISOCountries();      
        countries = new ArrayList<String>();
        TM_Child table_anak = new TM_Child(getApplicationContext());
		ArrayList<TM_Child_Model> arr = new ArrayList<TM_Child_Model>();
		arr = table_anak.getAllData();
		
		
		arr6 = table_anak.getAllChildName();
		Toast.makeText(getApplicationContext(), ""+arr6.toString(),
				Toast.LENGTH_LONG).show();
		//arrSt=arr6;
		
		
		
		for (int i =0;i<=arr6.size();i++) {
			
			
//       	listArray.add(new itemSearch(tc.getChild_id(), tc.getChild_name()));
//        	Toast.makeText(getApplicationContext(), ""+tc.getChild_name(),
//    				Toast.LENGTH_LONG).show();
			arrSt=arr6;
//			Toast.makeText(getApplicationContext(), "ini"+arrSt.get(0),
//					Toast.LENGTH_LONG).show();
        	
        	 
        }
		
		adapter = new ArrayAdapter(getApplicationContext(),R.layout.list_items,R.id.name, arrSt);
		
		
		
        lv.setAdapter(adapter);
//		CustomSearchAdapter = new CustomSearchAdapter(this, R.layout.list_items,
//				listArray);
//		lv.setAdapter(CustomSearchAdapter);

        search_view.setOnQueryTextListener(this);
        
        lv.setOnItemClickListener(new OnItemClickListener() {

        	
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,final int arg2,
                    long arg3) {
            	TM_Child tabel_anak;
            	String id_child;
    			TM_Child_Model model_anak = new TM_Child_Model();
    			tabel_anak = new TM_Child(getApplicationContext());
        		       		
            	 // TODO Auto-generated method stub
            	final View selectedView  = arg1 ; 
            	String str=lv.getItemAtPosition(arg2).toString();
            	
            	String iniId = tabel_anak.SearchChild(str).getChild_id().toString();
            	
            	Toast.makeText(getApplicationContext(), ""+str,
        				Toast.LENGTH_LONG).show();            	
            	Intent i = new Intent(getApplicationContext(), KunjunganActivity.class);
                i.putExtra("id_child", ""+iniId);
                startActivity(i);
            }
        });    
        
        
		// set grid view item
		
		for (TM_Child_Model tc : arr) {
			String as = "kosong";
			if (tc.getImage_path().equals(as)) {
				Bitmap bitmap1 = BitmapFactory.decodeResource(
						context.getResources(), R.drawable.icon);
				gridArray.add(new Item(tc.getChild_id(), tc.getChild_name(), bitmap1
						.createScaledBitmap(bitmap1, 120, 120, false)));
			}
			else
			{
				File image = new File(tc.getImage_path());

				BitmapFactory.Options bmOptions = new BitmapFactory.Options();

				Bitmap bitmap = BitmapFactory.decodeFile(
						image.getAbsolutePath(), bmOptions);

				bitmap = Bitmap.createScaledBitmap(bitmap, 120, 120, true);

				gridArray.add(new Item(tc.getChild_id(), tc.getChild_name(), bitmap
						.createScaledBitmap(bitmap, 120, 120, false)));
			}
			

		}

		

		gridView = (GridView) findViewById(R.id.gridView1);
		customGridAdapter = new CustomGridViewAdapter(this, R.layout.row_grid,
				gridArray);
		gridView.setAdapter(customGridAdapter);

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long arg3) {
				Intent intent3 = new Intent(ListAnakActivity.this,
						KunjunganActivity.class);
				// kirim id anak ke aktivity kunjungan anak
				
				intent3.putExtra("id_child", gridArray.get(position).getId());
				startActivity(intent3);
				
			}
		});

		// la=this;
		// RefreshList();

		btn_back = (ImageView) findViewById(R.id.btn_back);
		tambah_profil = (LinearLayout) findViewById(R.id.button_tambahprofil);
		reminder_lab = (ImageView) findViewById(R.id.reminder_lab);
		reminder_obat = (ImageView) findViewById(R.id.reminder_obat);

	}
	
	
	

	public void RefreshList() {
		JSONParse jp = new JSONParse();
		try {
			jp.setParent(this);
			JSONObject json = jp.execute().get();

			try {
				String anak;
				// Toast.makeText(getApplicationContext(), ""+json.toString(),
				// Toast.LENGTH_LONG).show();
				// Getting JSON Array
				// JSONObject c = json.getJSONObject("");
				jArray = json.getJSONArray("anak");

				// Storing JSON item in a Variable
				daftarid = new String[jArray.length()];
				daftarnama = new String[jArray.length()];
				daftardate = new String[jArray.length()];
				daftargoldar = new String[jArray.length()];

				// Toast.makeText(getApplicationContext(), ""+la.daftarnama,
				// Toast.LENGTH_LONG).show();

				for (int i = 0; i < jArray.length(); i++) {
					daftarid[i] = jArray.getJSONObject(i).getString("id")
							.toString();
					daftarnama[i] = jArray.getJSONObject(i).getString("nama")
							.toString();
					daftardate[i] = jArray.getJSONObject(i).getString("tgl")
							.toString();
					daftargoldar[i] = jArray.getJSONObject(i)
							.getString("goldar").toString();
				}

				// Set JSON Data in TextView
				// uid.setText(id);
				// name1.setText(name);
				// email1.setText(email);
				// ListView01.setAdapter(new ArrayAdapter<String>(this,
				// android.R.layout.simple_list_item_1, daftarnama));

			} catch (JSONException e) {
				e.printStackTrace();
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Toast.makeText(getApplicationContext(), "" + daftarnama,
				Toast.LENGTH_LONG).show();

		ListView ListView01 = (ListView) findViewById(R.id.ListView01);
		ListView01.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, daftarnama));

		ListView01.setSelected(true);
		ListView01.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				final String selectionid = daftarid[arg2];
				final String selectionnama = daftarnama[arg2];
				final String selectiondate = daftardate[arg2];
				final String selectiongoldar = daftargoldar[arg2];
				final CharSequence[] dialogitem = { "Edit", "Delete" };
				AlertDialog.Builder builder = new AlertDialog.Builder(
						ListAnakActivity.this);
				builder.setTitle("Pilih ?");
				builder.setItems(dialogitem,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int item) {
								switch (item) {
								case 0:
									Intent i = new Intent(
											getApplicationContext(),
											ProfilAnakActivity.class);
									i.putExtra("id", selectionid);
									i.putExtra("nama", selectionnama);
									i.putExtra("date", selectiondate);
									i.putExtra("goldar", selectiongoldar);
									startActivity(i);

									break;
								case 1:
									getRequest(url2 + "?id=" + selectionid);
									RefreshList();

									break;
								}
							}

							private void getRequest(String string) {
								// TODO Auto-generated method stub

							}
						});
				builder.create().show();
			}
		});

		// ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();

	}

	private String getRequest(String url3) {
		// TODO Auto-generated method stub
		return null;
	}

	private class JSONParse extends AsyncTask<String, String, JSONObject> {
		private ProgressDialog pDialog;
		private ListAnakActivity la;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			ListView ListView01 = (ListView) findViewById(R.id.ListView01);
			// ListView01.setAdapter(new ArrayAdapter<String>(this,
			// android.R.layout.simple_list_item_1, daftarnama));
			pDialog = new ProgressDialog(ListAnakActivity.this);
			pDialog.setMessage("Getting Data ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();

		}

		public void setParent(ListAnakActivity listAnakActivity) {
			// TODO Auto-generated method stub
			la = listAnakActivity;
		}

		protected JSONObject doInBackground(String... args) {
			JSONParser jParser = new JSONParser();

			// Getting JSON from URL
			JSONObject json = jParser.getJSONFromUrl(url);
			return json;
		}

		protected void onPostExecute(JSONObject json) {
			pDialog.dismiss();

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.menu = menu;

		menu.add(0, 1, 0, "Tambah").setIcon(android.R.drawable.btn_plus);
		menu.add(0, 2, 0, "Refresh").setIcon(android.R.drawable.ic_menu_rotate);
		menu.add(0, 3, 0, "Exit").setIcon(
				android.R.drawable.ic_menu_close_clear_cancel);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			Intent i = new Intent(ListAnakActivity.this,
					ProfilAnakActivity.class);
			startActivity(i);
			return true;
		case 2:
			RefreshList();
			return true;
		case 3:
			finish();
			return true;
		}
		return false;
	}
	
	ArrayList<Integer> selList = new ArrayList();
	String msg = "";
	private void viewReminder(final String[] Reminder)

	{

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("Notification");

		builder.setMultiChoiceItems(Reminder, null,
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

		builder.setPositiveButton("SIMPAN", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				msg = "";
				String am="";

				for (int i = 0; i < selList.size(); i++) {
					am=Reminder[selList.get(i)];
					msg = msg + Reminder[selList.get(i)] + "\n";
					
				}
//				tv.setText(msg);
				  final List<String> list =  new ArrayList<String>();
			      Collections.addAll(list, Reminder); 
			      list.remove("a");
				
			      Toast.makeText(getApplicationContext(), "ini message"+list.remove(am),
							Toast.LENGTH_LONG).show();
			      //Reminder = list.toArray(new String[list.size()]);

			}
		});

		AlertDialog alert = builder.create();
		alert.show();

	}

	
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn_back:
			Intent intent = new Intent(ListAnakActivity.this,
					LoginActivity.class);
			startActivity(intent);
			break;

		case R.id.button_tambahprofil:
			Intent intent2 = new Intent(ListAnakActivity.this,
					ProfilAnakActivity.class);
			startActivity(intent2);
			break;

		// case R.id.daftar_anak1:
		// // Intent intent3 = new Intent(ListAnakActivity.this,
		// KunjunganActivity.class);
		// // startActivity(intent3);
		// break;

		case R.id.reminder_lab:
			selList.clear();
			final String[] nama = { "a",
					"b", "c",
					"d", "e",
					"f" };
			viewReminder(nama);
			 break;

		case R.id.reminder_obat:
			selList.clear();
			final String[] namaObat = { "a",
					"b", "c",
					"d", "e",
					"f" };
			viewReminder(namaObat);
			 break;

						

						

		default:
			break;
		}

	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		// TODO Auto-generated method stub
		if (newText.length() > 0) {
            // Search
			lv.setVisibility(View.VISIBLE);
			adapter.getFilter().filter(newText);
        } else {
            // Do something when there's no input
        	lv.setVisibility(View.INVISIBLE);
        }
		
		return true;
	}

}
