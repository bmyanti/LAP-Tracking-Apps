package com.example.databaselap;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TR_Image {
	private static final String ROW_IMAGE_NAME = "image_name";
	private static final String ROW_DATE = "date";
	private static final String ROW_CHILD_ID = "child_id";
	private static final String ROW_IMAGE_PATH = "image_path";
	private static final String ROW_IMAGE_SERVER_PATH = "image_server_path";
	private static final String ROW_IMAGE_TYPE_ID = "image_type_id";
	private static final String ROW_IMAGE_EVENT_NOTE = "image_event_note";
	private static final String ROW_CREATED_BY = "created_by";
	private static final String ROW_CREATED_TIME = "created_time";
	private static final String ROW_UPDATE_BY = "update_by";
	private static final String ROW_UPDATE_TIME = "update_time";
	
	//mendeklarasikan NAMA_DB DAN TABLE DAN DATABASE VERSION
		private static final String NAMA_DB ="DB_LAP";
		private static final String NAMA_TABEL="TR_IMAGE";
		private static final int DB_VERSION=1;
		
	//mendeklarasikan membuat CREATE_TABLE = MEMBUAT TABLE"
		private static final String CREATE_TABLE ="create table "+NAMA_TABEL+" ("+ROW_IMAGE_NAME+" varchar PRIMARY KEY,"+ROW_DATE+" date PRIMARY KEY," +
				""+ROW_CHILD_ID+"varchar PRIMARY KEY autoincrement ,"+ROW_IMAGE_PATH+"varchar,"+ROW_IMAGE_SERVER_PATH+"varchar," +
						""+ROW_IMAGE_TYPE_ID+"varchar PRIMARY KEY,"+ROW_IMAGE_EVENT_NOTE+"text," +
						""+ROW_CREATED_BY+"varchar,"+ROW_CREATED_TIME+"time,"+ROW_UPDATE_BY+"varchar,"+ROW_UPDATE_TIME+"time,)";
	
		//membuat mendeklarasikan Context itu adalah context
		private final Context context;
		//membuat mendeklarasikan DatabaseOpenHelper itu adalah dbhelper
		private DatabaseOpenHelper dbhelper;
		//membuat mendeklarasikan SQLiteDatabase itu adalah db
		private SQLiteDatabase db;
		
		public TR_Image(Context ctx)
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
		
		public void addRow( String child_id , String date , 
				String image_name , String image_path , String image_server_path ,
				String image_type_id , String image_event_note,
				String created_by , String created_time ,
				String update_by , String update_time 
				)
		{
			ContentValues values = new ContentValues();
			
			values.put(ROW_IMAGE_NAME, image_name);
			values.put(ROW_DATE, date);
			values.put(ROW_CHILD_ID, child_id);
			values.put(ROW_IMAGE_PATH, image_path);
			values.put(ROW_IMAGE_SERVER_PATH, image_server_path);
			values.put(ROW_IMAGE_TYPE_ID, image_type_id);
			values.put(ROW_IMAGE_EVENT_NOTE, image_event_note);
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
						new String[]{ ROW_IMAGE_NAME, ROW_DATE,ROW_CHILD_ID, 
						ROW_IMAGE_PATH, ROW_IMAGE_SERVER_PATH, ROW_IMAGE_TYPE_ID,ROW_IMAGE_EVENT_NOTE,
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
