package com.example.databaselap;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TM_Complaint_Status {

	private static final String ROW_COMPLAINT_STATUS_ID = "complaint_status_id";
	private static final String ROW_COMPLAINT_STATUS_DESCRIPTION = "complaint_status_description";


	private static final String ROW_CREATED_BY = "created_by";
	private static final String ROW_CREATED_TIME = "created_time";
	private static final String ROW_UPDATE_BY = "update_by";
	private static final String ROW_UPDATE_TIME = "update_time";
	
	//mendeklarasikan NAMA_DB DAN TABLE DAN DATABASE VERSION
		private static final String NAMA_DB ="DB_LAP_COMSTAT";
		private static final String NAMA_TABEL="TM_Complaint_Status";
		private static final int DB_VERSION=1;
		
	//mendeklarasikan membuat CREATE_TABLE = MEMBUAT TABLE"
		private static final String CREATE_TABLE = "create table " + NAMA_TABEL
				+ " ( " + ROW_COMPLAINT_STATUS_ID + " varchar PRIMARY KEY, "
				+ ROW_COMPLAINT_STATUS_DESCRIPTION + " text, " + ROW_CREATED_BY + " date, "
				+ " " + ROW_CREATED_TIME + " varchar, " + ROW_UPDATE_BY + " text, "
				+ ROW_UPDATE_TIME + " varchar  ) ";
	
		//membuat mendeklarasikan Context itu adalah context
		private final Context context;
		//membuat mendeklarasikan DatabaseOpenHelper itu adalah dbhelper
		private DatabaseOpenHelper dbhelper;
		//membuat mendeklarasikan SQLiteDatabase itu adalah db
		private SQLiteDatabase db;
		
		public TM_Complaint_Status(Context ctx)
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
						try
						{
							
							ContentValues values = new ContentValues();
							
							values.put(ROW_COMPLAINT_STATUS_ID, "CS000");
							values.put(ROW_COMPLAINT_STATUS_DESCRIPTION, "-");
							values.put(ROW_CREATED_BY, "-");
							values.put(ROW_CREATED_TIME, "-");
							values.put(ROW_UPDATE_BY, "-");
							values.put(ROW_UPDATE_TIME, "-");
							db.insert(NAMA_TABEL, null, values);
							
							values.put(ROW_COMPLAINT_STATUS_ID, "CS001");
							values.put(ROW_COMPLAINT_STATUS_DESCRIPTION, "Sudah Tertangani");
							values.put(ROW_CREATED_BY, "-");
							values.put(ROW_CREATED_TIME, "-");
							values.put(ROW_UPDATE_BY, "-");
							values.put(ROW_UPDATE_TIME, "-");
							db.insert(NAMA_TABEL, null, values);
							
							values.put(ROW_COMPLAINT_STATUS_ID, "CS002");
							values.put(ROW_COMPLAINT_STATUS_DESCRIPTION, "Belum Tertangani");
							values.put(ROW_CREATED_BY, "-");
							values.put(ROW_CREATED_TIME, "-");
							values.put(ROW_UPDATE_BY, "-");
							values.put(ROW_UPDATE_TIME, "-");
							db.insert(NAMA_TABEL, null, values);

							values.put(ROW_COMPLAINT_STATUS_ID, "CS003");
							values.put(ROW_COMPLAINT_STATUS_DESCRIPTION, "Sembuh");
							values.put(ROW_CREATED_BY, "-");
							values.put(ROW_CREATED_TIME, "-");
							values.put(ROW_UPDATE_BY, "-");
							values.put(ROW_UPDATE_TIME, "-");
							db.insert(NAMA_TABEL, null, values);

							values.put(ROW_COMPLAINT_STATUS_ID, "CS004");
							values.put(ROW_COMPLAINT_STATUS_DESCRIPTION, "Putus Obat");
							values.put(ROW_CREATED_BY, "-");
							values.put(ROW_CREATED_TIME, "-");
							values.put(ROW_UPDATE_BY, "-");
							values.put(ROW_UPDATE_TIME, "-");
							db.insert(NAMA_TABEL, null, values);


							

						} catch (Exception e) {
							// TODO: handle exception
							Log.e("DB ERROR Complaint", e.toString());
							e.printStackTrace();

						}

				
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
		
		public ArrayList<String> getDataComplaintStatus() {
			ArrayList<String> allData = new ArrayList<String>();
			Cursor cursor = null;

			cursor = db.query(NAMA_TABEL, new String[] { ROW_COMPLAINT_STATUS_ID,
					ROW_COMPLAINT_STATUS_DESCRIPTION, ROW_CREATED_BY, ROW_CREATED_TIME,
					ROW_UPDATE_BY, ROW_UPDATE_TIME }, null, null, null, null, null);

			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				allData.add(cursor.getString(1));
				cursor.moveToNext();
			}

			cursor.close();
			return allData;
		}

		
		//membuat array pada table layout
		// get id kelas
		public String getIdComplaintStatus(String name) {
			String id = "";
			Cursor mCursor = db.rawQuery(
					"SELECT  complaint_status_id  FROM  TM_Complaint_Status WHERE complaint_status_description= '"
							+ name + "'", null);
			if (mCursor != null) {
				mCursor.moveToFirst();
				id = mCursor.getString(0);
			}
			mCursor.close();
			return id;
		}

		// get name kelas
		public String getNameComplaintStatus(String id) {
			String name = "";
			Cursor mCursor = db
					.rawQuery(
							"SELECT  complaint_status_description  FROM  TM_Complaint_Status WHERE complaint_status_id= '"
									+ id + "'", null);
			if (mCursor != null) {
				mCursor.moveToFirst();
				name = mCursor.getString(mCursor.getColumnIndex(ROW_COMPLAINT_STATUS_DESCRIPTION));
			}
			mCursor.close();
			return name;
		}
		
}