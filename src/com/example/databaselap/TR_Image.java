package com.example.databaselap;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TR_Image {
	private static final String ROW_ID = "image_id";
	private static final String ROW_IMAGE_NAME = "image_name";
	private static final String ROW_DATE = "date";
	private static final String ROW_IMAGE_ID = "child_id";
	private static final String ROW_IMAGE_PATH = "image_path";
	private static final String ROW_IMAGE_SERVER_PATH = "image_server_path";
	private static final String ROW_IMAGE_TYPE_ID = "image_type_id";
	private static final String ROW_LONGITUDE = "longitude";
	private static final String ROW_LATITUDE = "latitude";
	
	private static final String ROW_CREATED_BY = "created_by";
	private static final String ROW_CREATED_TIME = "created_time";
	private static final String ROW_UPDATE_BY = "update_by";
	private static final String ROW_UPDATE_TIME = "update_time";
	private static final String ROW_DELETE_STATUS = "delete_status";
	
	//mendeklarasikan NAMA_DB DAN TABLE DAN DATABASE VERSION
		private static final String NAMA_DB ="DB_LAP";
		private static final String NAMA_TABEL="TR_IMAGE";
		private static final int DB_VERSION=1;
		
	//mendeklarasikan membuat CREATE_TABLE = MEMBUAT TABLE"
		private static final String CREATE_TABLE ="create table "+NAMA_TABEL+" ("+ROW_ID+" integer primary key autoincrement ,"+ ROW_IMAGE_NAME+" varchar ,"+ROW_DATE+" date," +
				""+ROW_IMAGE_ID+" varchar,"+ROW_IMAGE_PATH+" varchar,"+ROW_IMAGE_SERVER_PATH+" varchar," +
						""+ROW_IMAGE_TYPE_ID+" varchar ," +ROW_LONGITUDE+" varchar ,"+ROW_LATITUDE+" varchar ,"+
						""+ROW_CREATED_BY+" varchar,"+ROW_CREATED_TIME+" varchar,"+ROW_UPDATE_BY+" varchar,"+ROW_UPDATE_TIME+" varchar,"+ROW_DELETE_STATUS+" varchar )";
	
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
		
		
}
