package com.example.databaselap;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TM_Facility {

	private static final String ROW_FACILITY_ID = "facility_id";
	private static final String ROW_FACILITY_DESCRIPTION = "facility_description";

	private static final String ROW_CREATED_BY = "created_by";
	private static final String ROW_CREATED_TIME = "created_time";
	private static final String ROW_UPDATE_BY = "update_by";
	private static final String ROW_UPDATE_TIME = "update_time";

	// mendeklarasikan NAMA_DB DAN TABLE DAN DATABASE VERSION
	private static final String NAMA_DB = "DB_LAP_FC";
	private static final String NAMA_TABEL = "TM_Facility";
	private static final int DB_VERSION = 1;

	// mendeklarasikan membuat CREATE_TABLE = MEMBUAT TABLE"
	private static final String CREATE_TABLE = 
			"create table "+NAMA_TABEL+" ( "+ROW_FACILITY_ID+" integer PRIMARY KEY autoincrement, "+ROW_FACILITY_DESCRIPTION+" text, "+ROW_CREATED_BY+" date, " +	
					" "+ROW_CREATED_TIME+" varchar, "+ROW_UPDATE_BY+" text, "+ROW_UPDATE_TIME+" varchar  ) ";

	// membuat mendeklarasikan Context itu adalah context
	private final Context context;
	// membuat mendeklarasikan DatabaseOpenHelper itu adalah dbhelper
	private DatabaseOpenHelper dbhelper;
	// membuat mendeklarasikan SQLiteDatabase itu adalah db
	private SQLiteDatabase db;

	public TM_Facility(Context ctx) {
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
				final String[] fasilitas = { "Bebelac 4-Madu-800 gr",
						"Chilschool-4-soya-300 gr", "Dancow 1+-Vanilla-800 gr",
						"Dancow 5+-Vanilla-800 gr", "Dancow-1+-Madu-800 gr",
						"Dancow-1+-Vanilla-800 gr", "Dancow-3+-Madu-800 gr",
						"Dancow-5+-Madu-800 gr", "Dancow-5+-Madu-800 gr",
						"Dancow-Datita3/5-1000 gr", "Dancow-Fullcream-Choc-800 gr",
						"Dancow-Fullcream-Vanilla-800  gr",
						"Ensure-FOS-Chocolate-1000 gr","Frisian-Flag-123-800 gr","Frisian-Flag-123-Madu-800 gr","Frisian-Flag-123-Vanilla-800 gr","Frisian-Flag-456-800 gr","Nutrilon-Soya-4-400 gr","Pediasure-900 gr",
						"SGM-2-Vanilla-1000 gr","SGM-3-Madu-1000 gr","SGM-3-Vanilla-1000 gr","SGM-4-Madu-1000 gr",
						"SGM-4-Vanilla-1000 gr","SGM-LLM-400 gr","UHT-Choc 125ml","UHT-Straw 125ml ",
						"Vidorant",
						"Sangobion", "Diaper-Goon-pants-XXL38",
						"Diaper-Pampers-pants-L36", "Diaper-Pampers-pants-M42",
						"Diaper-Pampers-pants-XL32" };

				ContentValues values = new ContentValues();
				for (String a : fasilitas) {
					values.put(ROW_FACILITY_DESCRIPTION, a);
					values.put(ROW_CREATED_BY, "-");
					values.put(ROW_CREATED_TIME, "-");
					values.put(ROW_UPDATE_BY, "-");
					values.put(ROW_UPDATE_TIME, "-");
					db.insert(NAMA_TABEL, null, values);
				}

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

	public void SaveDataFromServer(String[] data) {
		try {
			// cek jika sudah ada di database

			String sql_insert = "INSERT INTO TM_Facility(facility_description,created_by,created_time,update_by,update_time) VALUES (?,null,null,null,null)";
			for (String aa : data) {
				// cek the duplicate one
				String sql = "select * from " + NAMA_TABEL
						+ " WHERE facility_description = '" + aa + "'";
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
	// menutup DatabaseOpenHelper
	public void close() {
		dbhelper.close();
	}

	// get all data facility
	public ArrayList<String> getAllData() {
		ArrayList<String> allData = new ArrayList<String>();
		Cursor cursor = null;

		cursor = db.query(NAMA_TABEL, new String[] { ROW_FACILITY_ID,
				ROW_FACILITY_DESCRIPTION, ROW_CREATED_BY,
				ROW_CREATED_TIME, ROW_UPDATE_BY, ROW_UPDATE_TIME }, null, null,
				null, null, null);

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

	public void InsertData() {
		try {
			// hapus dari tabel kemudian update yang baru
			deleteAll();
			// masih data static
			final String[] fasilitas = { "Bebelac 4-Madu-800 gr",
					"Chilschool-4-soya-300 gr", "Dancow 1+-Vanilla-800 gr",
					"Dancow 5+-Vanilla-800 gr", "Dancow-1+-Madu-800 gr",
					"Dancow-1+-Vanilla-800 gr", "Dancow-3+-Madu-800 gr",
					"Dancow-5+-Madu-800 gr", "Dancow-5+-Madu-800 gr",
					"Dancow-Datita3/5-1000 gr", "Dancow-Fullcream-Choc-800 gr",
					"Dancow-Fullcream-Vanilla-800  gr", "Vidorant",
					"Sangobion", "Diaper-Goon-pants-XXL38",
					"Diaper-Pampers-pants-L36", "Diaper-Pampers-pants-M42",
					"Diaper-Pampers-pants-XL32" };

			ContentValues values = new ContentValues();
			for (String a : fasilitas) {
				values.put(ROW_FACILITY_DESCRIPTION, a);
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.e("Gagal Insert Data", e.toString());
		}
	}

	// get id type arv
	public String getIdFacility(String facility) {
		String id = "";
		Cursor mCursor = db.rawQuery(
				"SELECT  facility_id  FROM  TM_Facility WHERE facility_description= '"
						+ facility + "'", null);
		if (mCursor != null) {
			mCursor.moveToFirst();
			id = mCursor.getString(mCursor.getColumnIndex(ROW_FACILITY_ID));
		}
		mCursor.close();
		return id;
	}

	// get name type arv
	public String getNameFacility(String id) {
		String name = "";
		Cursor mCursor = db.rawQuery(
				"SELECT  facility_description  FROM  TM_Facility WHERE facility_id= '"
						+ id + "'", null);
		if (mCursor != null) {
			mCursor.moveToFirst();
			name = mCursor.getString(mCursor.getColumnIndex(ROW_FACILITY_DESCRIPTION));
		}
		mCursor.close();
		return name;
	}

}