package com.example.laptrackingapps;




import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
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

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import learn2crack.asynctask.library.JSONParser;

public class ListAnakActivity extends Activity {
	
	
	ImageView btn_back, reminder_lab, reminder_obat;
	LinearLayout tambah_profil, daftar_anak1;
	
	
	final Context context = this;
	
	private JSONObject jObject;
	private JSONArray jArray;
	private String jsonResult ="";
	private String url = "http://192.168.1.173/jsonn/daftarkota.php";
	private String url2 = "http://192.168.1.173/jsonn/delkota.php";
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
		
		la=this;
		RefreshList();
		
		btn_back = (ImageView) findViewById(R.id.btn_back);
		tambah_profil  = (LinearLayout) findViewById(R.id.button_tambahprofil);
		reminder_lab = (ImageView) findViewById(R.id.reminder_lab);
		reminder_obat = (ImageView) findViewById(R.id.reminder_obat);
		daftar_anak1 = (LinearLayout) findViewById(R.id.daftar_anak1);
		
	}
	
	public void RefreshList() {
    	JSONParse jp = new JSONParse();
		try {
			jp.setParent(this);
			JSONObject json = jp.execute().get();
			
			try {
	            String anak;
	            //Toast.makeText(getApplicationContext(), ""+json.toString(), Toast.LENGTH_LONG).show();
				// Getting JSON Array
	             //JSONObject c = json.getJSONObject("");
	            jArray = json.getJSONArray("anak");
	 
	            // Storing  JSON item in a Variable
	            daftarid = new String[jArray.length()];
	            daftarnama = new String[jArray.length()];
	 			daftardate = new String[jArray.length()];
	 			daftargoldar = new String[jArray.length()];
	 			
	 			//Toast.makeText(getApplicationContext(), ""+la.daftarnama, Toast.LENGTH_LONG).show();

	 			for (int i = 0; i < jArray.length(); i++)
	 			{
	 				daftarid[i] = jArray.getJSONObject(i).getString("id").toString();
	 				daftarnama[i] = jArray.getJSONObject(i).getString("nama").toString();
	 				daftardate[i] = jArray.getJSONObject(i).getString("tgl").toString();
	 				daftargoldar[i] = jArray.getJSONObject(i).getString("goldar").toString();
	 			}
	 				 
	            //Set JSON Data in TextView
	            //uid.setText(id);
	            //name1.setText(name);
	            //email1.setText(email);
	            //ListView01.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, daftarnama));
	 
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
		Toast.makeText(getApplicationContext(), ""+daftarnama, Toast.LENGTH_LONG).show();
		
		ListView ListView01 = (ListView)findViewById(R.id.ListView01);
        ListView01.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, daftarnama));
        
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				final String selectionid = daftarid[arg2]; 
				final String selectionnama = daftarnama[arg2]; 
				final String selectiondate = daftardate[arg2]; 
				final String selectiongoldar = daftargoldar[arg2]; 
		    	final CharSequence[] dialogitem = {"Edit", "Delete"};
		    	AlertDialog.Builder builder = new AlertDialog.Builder(ListAnakActivity.this);
		        builder.setTitle("Pilih ?");
		        builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						switch(item){
						case 0 :
							Intent i = new Intent(getApplicationContext(), ProfilAnakActivity.class);  
							i.putExtra("id", selectionid);
							i.putExtra("nama", selectionnama);
							i.putExtra("date", selectiondate);
							i.putExtra("goldar", selectiongoldar);
					    	startActivity(i);
							
							break;
						case 1 :
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
			}});

        //((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();
        
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
	            ListView ListView01 = (ListView)findViewById(R.id.ListView01);
	            //ListView01.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, daftarnama));
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
        menu.add(0, 3, 0, "Exit").setIcon(android.R.drawable.ic_menu_close_clear_cancel);
    	return true;
    }
	
	public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case 1:
        	Intent i = new Intent(ListAnakActivity.this, ProfilAnakActivity.class);
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


	
	
	public void onClick (View view){
		switch (view.getId()) {
		case R.id.btn_back:
			Intent intent = new Intent(ListAnakActivity.this, LoginActivity.class);
			startActivity(intent);
			break;
		
		case R.id.button_tambahprofil:
			Intent intent2 = new Intent(ListAnakActivity.this, ProfilAnakActivity.class);
			startActivity(intent2);
			break;
			
		case R.id.daftar_anak1:
			Intent intent3 = new Intent(ListAnakActivity.this, KunjunganActivity.class);
			startActivity(intent3);
			break;
			
		case R.id.reminder_lab:
			LayoutInflater layoutinflater = LayoutInflater.from(context);
			View promptView = layoutinflater.inflate(R.layout.activity_reminder_lab, null);
			
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
			alertDialogBuilder.setView(promptView);
			
			AlertDialog alertD = alertDialogBuilder.create();
			alertD.show();
			
			break;
			
		case R.id.reminder_obat:
			LayoutInflater lf = LayoutInflater.from(context);
			View pv = lf.inflate(R.layout.activity_reminder_obat, null);
			
			AlertDialog.Builder alert = new AlertDialog.Builder(context);
			alert.setView(pv);
			
			AlertDialog alertd = alert.create();
			alertd.show();
			
			break;

		default:
			break;
		}
		
	}


}
