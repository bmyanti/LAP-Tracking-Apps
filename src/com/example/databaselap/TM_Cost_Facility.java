package com.example.databaselap;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TM_Cost_Facility {

	private static final String ROW_FACILITY_COST_ID = "facility_cost_id";
	private static final String ROW_FACILITY_ID = "facility_id";
	private static final String ROW_FACILITY_BRAND_NAME = "facility_brand_name";
	private static final String ROW_FACILITY_PRICE = "facility_price";


	private static final String ROW_CREATED_BY = "created_by";
	private static final String ROW_CREATED_TIME = "created_time";
	private static final String ROW_UPDATE_BY = "update_by";
	private static final String ROW_UPDATE_TIME = "update_time";
	
	//mendeklarasikan NAMA_DB DAN TABLE DAN DATABASE VERSION
		private static final String NAMA_DB ="DB_LAP";
		private static final String NAMA_TABEL="TM_Cost_Facility";
		private static final int DB_VERSION=1;
		
	//mendeklarasikan membuat CREATE_TABLE = MEMBUAT TABLE"
		private static final String CREATE_TABLE ="create table "+NAMA_TABEL+" ("+ROW_FACILITY_COST_ID+"varchar PRIMARY KEY autoincrement,"+
				""+ROW_FACILITY_ID+"varchar PRIMARY KEY ,"+ROW_FACILITY_BRAND_NAME+"varchar,"+ROW_FACILITY_PRICE+"varchar ," +
						""+ROW_CREATED_BY+"varchar,"+ROW_CREATED_TIME+"time,"+ROW_UPDATE_BY+"varchar,"+ROW_UPDATE_TIME+"time,)";
	
		//membuat mendeklarasikan Context itu adalah context
		private final Context context;
		//membuat mendeklarasikan DatabaseOpenHelper itu adalah dbhelper
		private DatabaseOpenHelper dbhelper;
		//membuat mendeklarasikan SQLiteDatabase itu adalah db
		private SQLiteDatabase db;
		
		public TM_Cost_Facility(Context ctx)
		{
			//mendeklarasikan ctx adalah context ( context context di ganti ctx )
					this.context = ctx;
					// membuat DatabaseOpenHelper 
					dbhelper = new DatabaseOpenHelper(context);
					//menuliskan DatabaseOpenHelper = SQLiteDatabase
					db = dbhelper.getWritableDatabase();
		}
		
		private static class DatabaseOpenHelper extends SQLiteOpenHelper
		{

			//membuat database
					public DatabaseOpenHelper(Context context) {
						super(context, NAMA_DB, null, DB_VERSION);
						// TODO Auto-generated constructor stub
			}

					@Override
					public void onCreate(SQLiteDatabase db) {
						// TODO Auto-generated method stub
						db.execSQL(CREATE_TABLE);
				
			}

			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
				// TODO Auto-generated method stub
							db.execSQL("DROP TABLE IF EXISTS "+NAMA_DB);
							onCreate(db);
				
			}
			
		}
		
		//menutup DatabaseOpenHelper
		public void close() {
			dbhelper.close();
		}
		
		public void addRow(  String facility_cost_id ,
				String facility_id , String facility_brand_name , String facility_price,
				String created_by , String created_time ,
				String update_by , String update_time 
				)
		{
			ContentValues values = new ContentValues();
			
			values.put(ROW_FACILITY_COST_ID, facility_cost_id);
			values.put(ROW_FACILITY_ID, facility_id);
			values.put(ROW_FACILITY_BRAND_NAME, facility_brand_name);
			values.put(ROW_FACILITY_PRICE, facility_price);
			values.put(ROW_CREATED_BY, created_by);
			values.put(ROW_CREATED_TIME, created_time);
			values.put(ROW_UPDATE_BY, update_by);
			values.put(ROW_UPDATE_TIME, update_time);
			
			
			try {
				//menambahkan nama tabel bila tidak akan error
				//	db.delete(NAMA_TABEL, null, null);
					db.insert(NAMA_TABEL, null, values);
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.e("DB ERROR", e.toString());
				e.printStackTrace();
				
			}
		}
		//membuat array pada table layout
		public ArrayList<ArrayList<Object>> ambilSemuaBaris()
		{
			ArrayList<ArrayList<Object>> dataArray = new ArrayList<ArrayList<Object>>();
			Cursor cur;
			
			try {
				cur = db.query(NAMA_TABEL, 
						new String[]{ ROW_FACILITY_COST_ID,ROW_FACILITY_ID, 
						ROW_FACILITY_BRAND_NAME,ROW_FACILITY_PRICE,
						ROW_CREATED_BY,ROW_CREATED_TIME, ROW_UPDATE_BY, ROW_UPDATE_TIME,
						}, null, null,
						null, null, null);
				cur.moveToFirst();
				if(!cur.isAfterLast())
				{
					do
					{
						ArrayList<Object> dataList = new ArrayList<Object>();
						dataList.add(cur.getLong(0));
						dataList.add(cur.getString(1));
						dataList.add(cur.getString(2));
						dataList.add(cur.getString(3));
						dataList.add(cur.getString(4));
						dataList.add(cur.getString(5));
						dataList.add(cur.getString(6));
						dataList.add(cur.getString(7));
						dataList.add(cur.getString(8));
						dataList.add(cur.getString(9));
						dataList.add(cur.getString(10));
						dataList.add(cur.getString(11));
						
						
						dataArray.add(dataList);
					}while (cur.moveToNext());
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				Log.e("DEBE ERROR", e.toString());
			}
			return dataArray;
		}
		
}