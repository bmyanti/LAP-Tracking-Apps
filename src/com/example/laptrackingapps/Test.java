//package com.example.laptrackingapps;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.example.databaselap.TM_Child;
//import com.example.databaselap.TM_Class;
//import com.example.databaselap.TM_Drug_Dose;
//import com.example.databaselap.TM_Drug_Status;
//import com.example.databaselap.TM_Drug_Type;
//
//import android.app.Activity;
//import android.content.Context;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.EditText;
//import android.widget.ListView;
//import android.widget.TextView;
//
//public class Test extends Activity {
//
//	TextView tt;
//	ListView grdData;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_test);
//		tt = (TextView) findViewById(R.id.textTest);
//		// show in list view
//		String result = " ";
//
//		TM_Child table_anak = new TM_Child(getApplicationContext());
//
//		ArrayList<ArrayList<Object>> data_anak = new ArrayList<ArrayList<Object>>();
//		data_anak = table_anak.ambilSemuaBaris();
//
//		for (ArrayList a : data_anak) {
//			for (Object o : a) {
//
//				result += " " + o.toString();
//				Log.i("data retrieve", o.toString());
//			}
//			result += "~";
//		}
//
//		result += "++++++++++++++++++++++++++++++++++++++++status";
//
//		TM_Drug_Status data = new TM_Drug_Status(getApplicationContext());
//		data.getAllData();
//		for (String o : data.getAllData()) {
//
//			result += " " + o.toString();
//			Log.i("data retrieve", o.toString());
//
//			result += "~";
//		}
//
//		result += "-------------------------------------------dosis";
//
//		TM_Drug_Dose d = new TM_Drug_Dose(getApplicationContext());
//		data.getAllData();
//		for (String o : d.getAllData()) {
//
//			result += " " + o.toString();
//			Log.i("data retrieve", o.toString());
//
//			result += "~";
//		}
//
//		result += "-------------------------------------------type";
//
//		TM_Drug_Type type = new TM_Drug_Type(getApplicationContext());
//		
//		for (String o : type.getAllData()) {
//
//			result += " " + o.toString();
//			Log.i("data retrieve", o.toString());
//
//			result += "~";
//		}
//		
//		result += "-------------------------------------------kelas";
//
//		TM_Class kelas = new TM_Class(getApplicationContext());
//		
//		for (String o : kelas.getDataKelas()) {
//
//			result += " " + o.toString();
//			Log.i("data retrieve", o.toString());
//
//			result += "~";
//		}
//
//		tt.setText(result);
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.test, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
//}
