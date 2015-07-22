package com.example.databaselap;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TM_District {
	private static final String ROW_DISTRICT_ID = "district_id";
	private static final String ROW_DISTRICT_DESCRIPTION = "district_description";
	private static final String ROW_CREATED_BY = "created_by";
	private static final String ROW_CREATED_TIME = "created_time";
	private static final String ROW_UPDATE_BY = "update_by";
	private static final String ROW_UPDATE_TIME = "update_time";

	// mendeklarasikan NAMA_DB DAN TABLE DAN DATABASE VERSION
	private static final String NAMA_DB = "DB_LAP_TMD";
	private static final String NAMA_TABEL = "TM_DISTRICT";
	private static final int DB_VERSION = 1;

	// mendeklarasikan membuat CREATE_TABLE = MEMBUAT TABLE"
	private static final String CREATE_TABLE = 
			"create table "+NAMA_TABEL+" ( "+ROW_DISTRICT_ID+" integer PRIMARY KEY autoincrement, "+ROW_DISTRICT_DESCRIPTION+" text, "+ROW_CREATED_BY+" date, " +	
					" "+ROW_CREATED_TIME+" varchar, "+ROW_UPDATE_BY+" text, "+ROW_UPDATE_TIME+" varchar  ) ";
	// membuat mendeklarasikan Context itu adalah context
	private final Context context;
	// membuat mendeklarasikan DatabaseOpenHelper itu adalah dbhelper
	private DatabaseOpenHelper dbhelper;
	// membuat mendeklarasikan SQLiteDatabase itu adalah db
	private SQLiteDatabase db;

	public TM_District(Context ctx) {
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
				ContentValues values = new ContentValues();
				
				values.put(ROW_DISTRICT_DESCRIPTION, "-");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);
				
				values.put(ROW_DISTRICT_DESCRIPTION, "Jakarta Pusat");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);

				values.put(ROW_DISTRICT_DESCRIPTION, "Jakarta Barat");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);

				values.put(ROW_DISTRICT_DESCRIPTION, "Jakarta Selatan");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);

				values.put(ROW_DISTRICT_DESCRIPTION, "Jakarta Timur");
				values.put(ROW_CREATED_BY, "-");
				values.put(ROW_CREATED_TIME, "-");
				values.put(ROW_UPDATE_BY, "-");
				values.put(ROW_UPDATE_TIME, "-");
				db.insert(NAMA_TABEL, null, values);

				values.put(ROW_DISTRICT_DESCRIPTION, "Jakarta Utara");
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

			String sql_insert = "INSERT INTO TM_DISTRICT(district_description,created_by,created_time,update_by,update_time) VALUES (?,null,null,null,null)";
			for (String aa : data) {
				// cek the duplicate one
				String sql = "select * from " + NAMA_TABEL
						+ " WHERE district_description = '" + aa + "'";
				Cursor cursorinfo = null;
				try {
					cursorinfo = db.rawQuery(sql, null);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				// if data is not in database
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

	public void addRow(String district_id, String district_description,
			String created_by, String created_time, String update_by,
			String update_time) {
		ContentValues values = new ContentValues();

		values.put(ROW_DISTRICT_ID, district_id);

		values.put(ROW_DISTRICT_DESCRIPTION, district_description);
		values.put(ROW_CREATED_BY, created_by);
		values.put(ROW_CREATED_TIME, created_time);
		values.put(ROW_UPDATE_BY, update_by);
		values.put(ROW_UPDATE_TIME, update_time);

		try {
			// menambahkan nama tabel bila tidak akan error
			// db.delete(NAMA_TABEL, null, null);
			db.insert(NAMA_TABEL, null, values);

		} catch (Exception e) {
			// TODO: handle exception
			Log.e("DB ERROR", e.toString());
			e.printStackTrace();

		}
	}

	// membuat array pada table layout
	public ArrayList<ArrayList<Object>> ambilSemuaBaris() {
		ArrayList<ArrayList<Object>> dataArray = new ArrayList<ArrayList<Object>>();
		Cursor cur;

		try {
			cur = db.query(NAMA_TABEL, new String[] { ROW_DISTRICT_ID,
					ROW_DISTRICT_DESCRIPTION, ROW_CREATED_BY, ROW_CREATED_TIME,
					ROW_UPDATE_BY, ROW_UPDATE_TIME }, null, null, null, null,
					null);
			cur.moveToFirst();
			if (!cur.isAfterLast()) {
				do {
					ArrayList<Object> dataList = new ArrayList<Object>();
					dataList.add(cur.getLong(0));
					dataList.add(cur.getString(1));
					dataList.add(cur.getString(2));

					dataArray.add(dataList);
				} while (cur.moveToNext());
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Log.e("DEBE ERROR", e.toString());
		}
		return dataArray;
	}

	public void deleteAll() {
		db.delete(NAMA_TABEL, null, null);
	}

	// get all data
	public ArrayList<String> getAllData() {
		ArrayList<String> allData = new ArrayList<String>();
		Cursor cursor = null;

		cursor = db.query(NAMA_TABEL, new String[] { ROW_DISTRICT_ID,
				ROW_DISTRICT_DESCRIPTION, ROW_CREATED_BY, ROW_CREATED_TIME,
				ROW_UPDATE_BY, ROW_UPDATE_TIME }, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			allData.add(cursor.getString(1));

			cursor.moveToNext();
		}

		cursor.close();
		return allData;
	}

	// get id dosis arv
	public String getIdDistrict(String district) {
		String id = "";
		try{
		Cursor mCursor = db.rawQuery(
				"SELECT  district_id  FROM  TM_DISTRICT WHERE district_description= '"
						+ district + "'", null);
		if (mCursor != null) {
			mCursor.moveToFirst();
			id = mCursor.getString(mCursor.getColumnIndex(ROW_DISTRICT_ID));
		}
		mCursor.close();
		}catch(Exception e)
		{
			e.printStackTrace();
			Log.e("id failed", e.toString());
		}
		return id;
	}

	// get caregiver
	public String getNameDistrict(String id) {
		String name = "";
		Cursor mCursor = db.rawQuery(
				"SELECT  district_description  FROM  TM_DISTRICT WHERE district_id= '"
						+ id + "'", null);
		if (mCursor != null) {
			mCursor.moveToFirst();
			name = mCursor.getString(mCursor
					.getColumnIndex(ROW_DISTRICT_DESCRIPTION));
		}
		mCursor.close();
		return name;
	}
}
