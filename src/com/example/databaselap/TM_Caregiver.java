package com.example.databaselap;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TM_Caregiver {
	private static final String ROW_CAREGIVER_ID = "caregiver_id";
	private static final String ROW_CAREGIVER_DESCRIPTION = "caregiver_description";
	private static final String ROW_CREATED_BY = "created_by";
	private static final String ROW_CREATED_TIME = "created_time";
	private static final String ROW_UPDATE_BY = "update_by";
	private static final String ROW_UPDATE_TIME = "update_time";

	// mendeklarasikan NAMA_DB DAN TABLE DAN DATABASE VERSION
	private static final String NAMA_DB = "DB_LAP_CG";
	private static final String NAMA_TABEL = "TM_CAREGIVER";
	private static final int DB_VERSION = 1;

	// mendeklarasikan membuat CREATE_TABLE = MEMBUAT TABLE
	private static final String CREATE_TABLE = 
			"create table "+NAMA_TABEL+" ( "+ROW_CAREGIVER_ID+" varchar PRIMARY KEY , "+ROW_CAREGIVER_DESCRIPTION+" text, "+ROW_CREATED_BY+" date, " +	
					" "+ROW_CREATED_TIME+" varchar, "+ROW_UPDATE_BY+" text, "+ROW_UPDATE_TIME+" varchar  ) ";
	

	// membuat mendeklarasikan Context itu adalah context
	private final Context context;
	// membuat mendeklarasikan DatabaseOpenHelper itu adalah dbhelper
	private DatabaseOpenHelper dbhelper;
	// membuat mendeklarasikan SQLiteDatabase itu adalah db
	private SQLiteDatabase db;

	public TM_Caregiver(Context ctx) {
		// mendeklarasikan ctx adalah context ( context context di ganti ctx )
		this.context = ctx;
		// membuat DatabaseOpenHelper
		dbhelper = new DatabaseOpenHelper(context);
		// menuliskan DatabaseOpenHelper = SQLiteDatabase
		db = dbhelper.getWritableDatabase();
	}

	private static class DatabaseOpenHelper extends SQLiteOpenHelper {

		// membuat database
		public DatabaseOpenHelper(Context context) {
			super(context, NAMA_DB, null, DB_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(CREATE_TABLE);
			try {
				// hapus dari tabel kemudian update yang baru
				
				// masih data static
				ContentValues values = new ContentValues();
				
				values.put(ROW_CAREGIVER_ID, "CA000");
				values.put(ROW_CAREGIVER_DESCRIPTION, "-");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);
				
				values.put(ROW_CAREGIVER_ID, "CA001");
				values.put(ROW_CAREGIVER_DESCRIPTION, "Kakek");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);

				values.put(ROW_CAREGIVER_ID, "CA002");
				values.put(ROW_CAREGIVER_DESCRIPTION, "Nenek");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);

				values.put(ROW_CAREGIVER_ID, "CA003");
				values.put(ROW_CAREGIVER_DESCRIPTION, "Orang Tua");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);

				values.put(ROW_CAREGIVER_ID, "CA004");
				values.put(ROW_CAREGIVER_DESCRIPTION, "Paman");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);

				values.put(ROW_CAREGIVER_ID, "CA005");
				values.put(ROW_CAREGIVER_DESCRIPTION, "Bibi");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);
				
				values.put(ROW_CAREGIVER_ID, "CA006");
				values.put(ROW_CAREGIVER_DESCRIPTION, "Tetangga");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);

			} catch (Exception e) {
				e.printStackTrace();
				Log.e("Gagal Insert Data", e.toString());
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + NAMA_DB);
			onCreate(db);
			
			

		}

	}

	// menutup DatabaseOpenHelper
	public void close() {
		dbhelper.close();
	}

	public void SaveDataFromServer(String[] data) {
		try {
			// cek jika sudah ada di database

			String sql_insert = "INSERT INTO TM_CAREGIVER(caregiver_description,created_by,created_time,update_by,update_time) VALUES (?,null,null,null,null)";
			for (String aa : data) {
				// cek the duplicate one
				String sql = "select * from " + NAMA_TABEL
						+ " WHERE caregiver_description = '" + aa + "'";
				Cursor cursorinfo = null;
				try {
					cursorinfo = db.rawQuery(sql, null);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				//if data is not in database
				if (!cursorinfo.moveToFirst()) {
					db.execSQL(sql_insert, new String[] { aa });
					Log.i("save", aa);
				} else {
					Log.i("data already in database", aa);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.e("Failed to save", e.toString());
		}

	}
	// get all data facility
	public ArrayList<String> getAllData() {
		ArrayList<String> allData = new ArrayList<String>();
		Cursor cursor = null;

		cursor = db.query(NAMA_TABEL, new String[] { ROW_CAREGIVER_ID,
				ROW_CAREGIVER_DESCRIPTION, ROW_CREATED_BY, ROW_CREATED_TIME,
				ROW_UPDATE_BY, ROW_UPDATE_TIME }, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			allData.add(cursor.getString(1));

			cursor.moveToNext();
		}

		cursor.close();
		return allData;
	}

	public void deleteAll() {
		db.delete(NAMA_TABEL, null, null);
	}

	// get id dosis arv
	public String getIdCaregiver(String caregiver) {
		String id = "";
		Cursor mCursor = db.rawQuery(
				"SELECT  caregiver_id  FROM  TM_CAREGIVER WHERE caregiver_description= '"
						+ caregiver + "'", null);
		if (mCursor != null) {
			mCursor.moveToFirst();
			id = mCursor.getString(0);
		}
		mCursor.close();
		return id;
	}

	// get caregiver
	public String getNameCaregiver(String id) {
		String name = "";
		Cursor mCursor = db.rawQuery(
				"SELECT  caregiver_description  FROM  TM_CAREGIVER WHERE caregiver_id= '"
						+ id + "'", null);
		if (mCursor != null) {
			mCursor.moveToFirst();
			name = mCursor.getString(mCursor.getColumnIndex(ROW_CAREGIVER_DESCRIPTION));
		}
		mCursor.close();
		return name;
	}

}
