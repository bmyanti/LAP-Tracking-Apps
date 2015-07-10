package com.example.laptrackingapps;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

public class Checkbox extends ListActivity {

	String[] susu= { "Bebelac 4-Madu-800 gr", "Chilschool-4-soya-300 gr", "Dancow 1+-Vanilla-800 gr", "Dancow 5+-Vanilla-800 gr", "Dancow-1+-Madu-800 gr",
			"Dancow-1+-Vanilla-800 gr", "Dancow-3+-Madu-800 gr", "Dancow-5+-Madu-800 gr", "Dancow-5+-Madu-800 gr", "Dancow-Datita3/5-1000 gr",
			"Dancow-Fullcream-Choc-800 gr", "Dancow-Fullcream-Vanilla-800  gr" };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checkbox);
		
		// -- Display mode of the ListView
		
		ListView listview= getListView();
	//	listview.setChoiceMode(listview.CHOICE_MODE_NONE);
	//	listview.setChoiceMode(listview.CHOICE_MODE_SINGLE);
		listview.setChoiceMode(listview.CHOICE_MODE_MULTIPLE);
		
		//--	text filtering
		listview.setTextFilterEnabled(true);
			
			setListAdapter(new ArrayAdapter<String>(this, 
					android.R.layout.simple_list_item_multiple_choice,susu));
	}
	
	public void onListItemClick(ListView parent, View v,int position,long id){
		CheckedTextView item = (CheckedTextView) v;
		Toast.makeText(this, susu[position] + " checked : " +
		item.isChecked(), Toast.LENGTH_SHORT).show();
	}
}