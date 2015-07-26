package com.example.databaselap;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TM_Drug_Status {

	private static final String ROW_DRUG_STATUS_ID = "drug_status_id";
	private static final String ROW_DRUG_STATUS_DESCRIPTION = "drug_status_description";

	private static final String ROW_CREATED_BY = "created_by";
	private static final String ROW_CREATED_TIME = "created_time";
	private static final String ROW_UPDATE_BY = "update_by";
	private static final String ROW_UPDATE_TIME = "update_time";

	// mendeklarasikan NAMA_DB DAN TABLE DAN DATABASE VERSION
	private static final String NAMA_DB = "DB_LAP_DS";
	private static final String NAMA_TABEL = "TM_Drug_Status";
	private static final int DB_VERSION = 1;	

	// mendeklarasikan membuat CREATE_TABLE = MEMBUAT TABLE"
	private static final String CREATE_TABLE = 
			"create table "+NAMA_TABEL+" ( "+ROW_DRUG_STATUS_ID+" varchar PRIMARY KEY, "+ROW_DRUG_STATUS_DESCRIPTION+" text, "+ROW_CREATED_BY+" date, " +	
					" "+ROW_CREATED_TIME+" varchar, "+ROW_UPDATE_BY+" text, "+ROW_UPDATE_TIME+" varchar  ) ";
	

	// membuat mendeklarasikan Context itu adalah context
	private final Context context;
	// membuat mendeklarasikan DatabaseOpenHelper itu adalah dbhelper
	private DatabaseOpenHelper dbhelper;
	// membuat mendeklarasikan SQLiteDatabase itu adalah db
	private  SQLiteDatabase db;

	public TM_Drug_Status(Context ctx) {
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
				values.put(ROW_DRUG_STATUS_ID, "");
				values.put(ROW_DRUG_STATUS_DESCRIPTION, "-");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);
				
				values.put(ROW_DRUG_STATUS_DESCRIPTION, "Belum");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);

				values.put(ROW_DRUG_STATUS_DESCRIPTION, "Lini 1");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);
				
				values.put(ROW_DRUG_STATUS_DESCRIPTION, "Lini 2");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);

				values.put(ROW_DRUG_STATUS_DESCRIPTION, "Lini 3");
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

			String sql_insert = "INSERT INTO TM_Drug_Status(drug_status_description,created_by,created_time,update_by,update_time) VALUES (?,null,null,null,null)";
			for (String aa : data) {
				// cek the duplicate one
				String sql = "select * from " + NAMA_TABEL
						+ " WHERE drug_status_description = '" + aa + "'";
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
	// query data status
	public ArrayList<String> getAllData() {
		ArrayList<String> allData = new ArrayList<String>();
		Cursor cursor = null;

		cursor = db.query(NAMA_TABEL, new String[] { ROW_DRUG_STATUS_ID,
				ROW_DRUG_STATUS_DESCRIPTION, ROW_CREATED_BY, ROW_CREATED_TIME,
				ROW_UPDATE_BY, ROW_UPDATE_TIME }, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			allData.add(cursor.getString(1));

			cursor.moveToNext();
		}

		cursor.close();
		return allData;
	}

	public  void deleteAll() {
		db.delete(NAMA_TABEL, null, null);
	}

	public  void InsertData() {
		try {
			// hapus dari tabel kemudian update yang baru
			deleteAll();
			// masih data static
			ContentValues values = new ContentValues();
			values.put(ROW_DRUG_STATUS_DESCRIPTION, "Lengkap");
			values.put(ROW_CREATED_BY, "-");
			values.put(ROW_CREATED_TIME, "-");
			values.put(ROW_UPDATE_BY, "-");
			values.put(ROW_UPDATE_TIME, "-");
			db.insert(NAMA_TABEL, null, values);

			values.put(ROW_DRUG_STATUS_DESCRIPTION, "Tidak Lengkap");
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

	// get id status arv
	public String getIdStatusARV(String status_arv) {
		String id = "";
		Cursor mCursor = db.rawQuery(
				"SELECT  drug_status_id  FROM  TM_Drug_Status WHERE drug_status_description= '"
						+ status_arv + "'", null);
		if (mCursor != null) {
			mCursor.moveToFirst();
			id = mCursor.getString(0);
		}
		mCursor.close();
		return id;
	}

	// get arv status
	public String getNameStatusARV(String id) {
		String name = "";
		Cursor mCursor = db.rawQuery(
				"SELECT  drug_status_description  FROM  TM_Drug_Status WHERE drug_status_id= '"
						+ id + "'", null);
		if (mCursor != null) {
			mCursor.moveToFirst();
			name = mCursor.getString(mCursor.getColumnIndex(ROW_DRUG_STATUS_DESCRIPTION));
		}
		mCursor.close();
		return name;
	}
}
