package com.example.databaselap;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TM_Subdistrict {
	private static final String ROW_SUBDISTRICT_ID = "subdistrict_id";
	private static final String ROW_DISTRICT_ID = "district_id";
	private static final String ROW_SUBDISTRICT_DESCRIPTION = "subdistrict_description";
	private static final String ROW_CREATED_BY = "created_by";
	private static final String ROW_CREATED_TIME = "created_time";
	private static final String ROW_UPDATE_BY = "update_by";
	private static final String ROW_UPDATE_TIME = "update_time";

	// mendeklarasikan NAMA_DB DAN TABLE DAN DATABASE VERSION
	private static final String NAMA_DB = "DB_LAP_TMS";
	private static final String NAMA_TABEL = "TM_SUBDISTRICT";
	private static final int DB_VERSION = 1;

	// mendeklarasikan membuat CREATE_TABLE = MEMBUAT TABLE"
	private static final String CREATE_TABLE = "create table " + NAMA_TABEL
			+ " ( " + ROW_SUBDISTRICT_ID
			+ " integer PRIMARY KEY autoincrement, " + ROW_DISTRICT_ID
			+ " varchar, " + ROW_SUBDISTRICT_DESCRIPTION
			+ " text, " + ROW_CREATED_BY + " date, " + " " + ROW_CREATED_TIME
			+ " varchar, " + ROW_UPDATE_BY + " text, " + ROW_UPDATE_TIME
			+ " varchar  ) ";
	// membuat mendeklarasikan Context itu adalah context
	private final Context context;
	// membuat mendeklarasikan DatabaseOpenHelper itu adalah dbhelper
	private DatabaseOpenHelper dbhelper;
	// membuat mendeklarasikan SQLiteDatabase itu adalah db
	private SQLiteDatabase db;

	public TM_Subdistrict(Context ctx) {
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
				String district_id[] = { "1", "2", "3", "4", "5","6" };
				
				String subdistrict_jakpus[] = { "Gambir", "Tanah Abang",
						"Menteng", "Senen", "Cempaka Putih", "Johar Baru",
						"Kemayoran", "Sawah Besar" };
				String subdistrict_jaksel[] = { "Kebayoran Baru",
						"Kebayoran Lama", "Pesanggrahan", "Cilandak",
						"Pasar Minggu", "Jagakarsa", "Mampang Prapatan",
						"Pancoran", "Tebet", "Setiabudi" };
				String subdistrict_jakut[] = { "Koja", "Kelapa Gading",
						"Tanjung Priok", "Pademangan", "Penjaringan",
						"Cilincing" };
				String subdistrict_jaktim[] = { "Matraman", "Pulo Gadung",
						"Jatinegara", "Duren Sawit", "Kramat Jati", "Makasar",
						"Pasar Rebo", "Ciracas", "Cipayung", "Cakung" };
				String subdistrict_jakbar[] = { "Cengkareng",
						"Grogol Petamburan", "Kalideres", "Kebon Jeruk",
						"Kembangan", "Palmerah", "Taman Sari", "Tambora" };

				
				
				for (String district : district_id) {
					if (district.equals("2")) {
						for (String sub_jakpus : subdistrict_jakpus) {

							values.put(ROW_DISTRICT_ID, "2");
							values.put(ROW_SUBDISTRICT_DESCRIPTION, sub_jakpus);
							values.put(ROW_CREATED_BY, "-");
							values.put(ROW_CREATED_TIME, "-");
							values.put(ROW_UPDATE_BY, "-");
							values.put(ROW_UPDATE_TIME, "-");
							db.insert(NAMA_TABEL, null, values);
						}
					} else if (district.equals("3")) {
						for (String sub_jakbar : subdistrict_jakbar) {

							values.put(ROW_DISTRICT_ID, "3");
							values.put(ROW_SUBDISTRICT_DESCRIPTION, sub_jakbar);
							values.put(ROW_CREATED_BY, "-");
							values.put(ROW_CREATED_TIME, "-");
							values.put(ROW_UPDATE_BY, "-");
							values.put(ROW_UPDATE_TIME, "-");
							db.insert(NAMA_TABEL, null, values);
						}
					} else if (district.equals("4")) {
						for (String sub_jaksel : subdistrict_jaksel) {

							values.put(ROW_DISTRICT_ID, "4");
							values.put(ROW_SUBDISTRICT_DESCRIPTION, sub_jaksel);
							values.put(ROW_CREATED_BY, "-");
							values.put(ROW_CREATED_TIME, "-");
							values.put(ROW_UPDATE_BY, "-");
							values.put(ROW_UPDATE_TIME, "-");
							db.insert(NAMA_TABEL, null, values);
						}
					} else if (district.equals("5")) {
						for (String sub_jaktim : subdistrict_jaktim) {

							values.put(ROW_DISTRICT_ID, "5");
							values.put(ROW_SUBDISTRICT_DESCRIPTION, sub_jaktim);
							values.put(ROW_CREATED_BY, "-");
							values.put(ROW_CREATED_TIME, "-");
							values.put(ROW_UPDATE_BY, "-");
							values.put(ROW_UPDATE_TIME, "-");
							db.insert(NAMA_TABEL, null, values);
						}
					} else if (district.equals("6")) {
						for (String sub_jakut : subdistrict_jakut) {

							values.put(ROW_DISTRICT_ID, "6");
							values.put(ROW_SUBDISTRICT_DESCRIPTION, sub_jakut);
							values.put(ROW_CREATED_BY, "-");
							values.put(ROW_CREATED_TIME, "-");
							values.put(ROW_UPDATE_BY, "-");
							values.put(ROW_UPDATE_TIME, "-");
							db.insert(NAMA_TABEL, null, values);
						}
					} else {
						values.put(ROW_DISTRICT_ID, "1");
						values.put(ROW_SUBDISTRICT_DESCRIPTION, "-");
						values.put(ROW_CREATED_BY, "-");
						values.put(ROW_CREATED_TIME, "-");
						values.put(ROW_UPDATE_BY, "-");
						values.put(ROW_UPDATE_TIME, "-");
						db.insert(NAMA_TABEL, null, values);
						
						
					}
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

	// menutup DatabaseOpenHelper
	public void close() {
		dbhelper.close();
	}

	public void addRow(String subdistrict_id, String district_id,
			String subdistrict_description, String created_by,
			String created_time, String update_by, String update_time) {
		ContentValues values = new ContentValues();

		values.put(ROW_SUBDISTRICT_ID, subdistrict_id);
		values.put(ROW_DISTRICT_ID, district_id);
		values.put(ROW_SUBDISTRICT_DESCRIPTION, subdistrict_description);
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
			cur = db.query(NAMA_TABEL, new String[] { ROW_SUBDISTRICT_ID,
					ROW_DISTRICT_ID, ROW_SUBDISTRICT_DESCRIPTION,
					ROW_CREATED_BY, ROW_CREATED_TIME, ROW_UPDATE_BY,
					ROW_UPDATE_TIME, }, null, null, null, null, null);
			cur.moveToFirst();
			if (!cur.isAfterLast()) {
				do {
					ArrayList<Object> dataList = new ArrayList<Object>();
					dataList.add(cur.getLong(0));
					dataList.add(cur.getString(1));

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

	// get id dosis arv
	public String getIdSubDistrict(String subdistrict) {
		String id = "";
		Cursor mCursor = db.rawQuery(
				"SELECT  subdistrict_id  FROM  TM_SUBDISTRICT WHERE subdistrict_description= '"
						+ subdistrict + "'", null);
		if (mCursor != null) {
			mCursor.moveToFirst();
			id = mCursor.getString(0);
		}
		mCursor.close();
		return id;
	}

	// get all data
	public ArrayList<String> getAllData() {
		ArrayList<String> allData = new ArrayList<String>();
		Cursor cursor = null;

		cursor = db.query(NAMA_TABEL, new String[] { ROW_DISTRICT_ID,
				ROW_SUBDISTRICT_DESCRIPTION, ROW_CREATED_BY, ROW_CREATED_TIME,
				ROW_UPDATE_BY, ROW_UPDATE_TIME }, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			allData.add(cursor.getString(1));
			cursor.moveToNext();
		}
		cursor.close();
		return allData;
	}

	// get caregiver
	public String getNameSubDistrict(String id) {
		String name = "";
		Cursor mCursor = db.rawQuery(
				"SELECT  subdistrict_description  FROM  TM_SUBDISTRICT WHERE subdistrict_id= '"
						+ id + "'", null);
		if (mCursor != null) {
			mCursor.moveToFirst();
			name = mCursor.getString(mCursor
					.getColumnIndex(ROW_SUBDISTRICT_DESCRIPTION));
		}
		mCursor.close();
		return name;
	}
	
	//get id of district
	public String getIDDistrict(String id_subdistrict) {
		String name = "";
		Cursor mCursor = db.rawQuery(
				"SELECT  district_id  FROM  TM_SUBDISTRICT WHERE subdistrict_id= '"
						+ id_subdistrict + "'", null);
		if (mCursor != null) {
			mCursor.moveToFirst();
			name = mCursor.getString(mCursor
					.getColumnIndex(ROW_DISTRICT_ID));
		}
		mCursor.close();
		return name;
	}

	

	public ArrayList<String> getAllNameDistrict(String id) {
		ArrayList<String> district =  new ArrayList<String>();
		try {
			Cursor mCursor = db.rawQuery(
					"SELECT  subdistrict_description  FROM  TM_SUBDISTRICT WHERE district_id= '"
			
							+ id + "'", null);
			
			mCursor.moveToFirst();
			while (!mCursor.isAfterLast()) {
				district.add(mCursor.getString(mCursor.getColumnIndex(ROW_SUBDISTRICT_DESCRIPTION)));
				mCursor.moveToNext();
			}
			mCursor.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("Failed to find all name district", e.toString());
		}
		
		return district;
	}
	
	public ArrayList<String> getAllDistrict() {
		ArrayList<String> district =  new ArrayList<String>();
		try {
			Cursor mCursor = db.rawQuery(
					"SELECT  subdistrict_description  FROM  TM_SUBDISTRICT ", null);
			
			mCursor.moveToFirst();
			while (!mCursor.isAfterLast()) {
				district.add(mCursor.getString(mCursor.getColumnIndex(ROW_SUBDISTRICT_DESCRIPTION)));
				mCursor.moveToNext();
			}
			mCursor.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("Failed to find all name district", e.toString());
		}
		
		return district;
	}
	
	
	public void SaveDataFromServer(String[] data) {
		try {
			// cek jika sudah ada di database

			String sql_insert = "INSERT INTO TM_SUBDISTRICT(subdistrict_description,created_by,created_time,update_by,update_time) VALUES (?,null,null,null,null)";
			for (String aa : data) {
				// cek the duplicate one
				String sql = "select * from " + NAMA_TABEL
						+ " WHERE subdistrict_description = '" + aa + "'";
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

}
