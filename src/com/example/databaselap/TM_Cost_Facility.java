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

	// mendeklarasikan NAMA_DB DAN TABLE DAN DATABASE VERSION
	private static final String NAMA_DB = "DB_LAP_CostFacilty";
	private static final String NAMA_TABEL = "TM_Cost_Facility";
	private static final int DB_VERSION = 1;

	// mendeklarasikan membuat CREATE_TABLE = MEMBUAT TABLE

	private static final String CREATE_TABLE = "create table " + NAMA_TABEL
			+ " ( " + ROW_FACILITY_COST_ID + " varchar PRIMARY KEY, "
			+ ROW_FACILITY_ID + " text, " + " " + ROW_FACILITY_BRAND_NAME
			+ " varchar, " + ROW_FACILITY_PRICE + " text, " + ROW_CREATED_BY
			+ " date, " + " " + ROW_CREATED_TIME + " varchar, " + ROW_UPDATE_BY
			+ " text, " + ROW_UPDATE_TIME + " varchar  ) ";

	// membuat mendeklarasikan Context itu adalah context
	private final Context context;
	// membuat mendeklarasikan DatabaseOpenHelper itu adalah dbhelper
	private DatabaseOpenHelper dbhelper;
	// membuat mendeklarasikan SQLiteDatabase itu adalah db
	private SQLiteDatabase db;

	public TM_Cost_Facility(Context ctx) {
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
				final String[] fasilitas = { "FA001", "FA002", "FA003" };

				final String[] fasilitas_susu = { "Bebelac 4-Madu-800 gr",
						"Chilschool-4-soya-300 gr", "Dancow 1+-Vanilla-800 gr",
						"Dancow 5+-Vanilla-800 gr", "Dancow-1+-Madu-800 gr",
						"Dancow-1+-Vanilla-800 gr", "Dancow-3+-Madu-800 gr",
						"Dancow-5+-Madu-800 gr", "Dancow-5+-Madu-800 gr",
						"Dancow-Datita3/5-1000 gr",
						"Dancow-Fullcream-Choc-800 gr",
						"Dancow-Fullcream-Vanilla-800  gr",
						"Ensure-FOS-Chocolate-1000 gr",
						"Frisian-Flag-123-800 gr",
						"Frisian-Flag-123-Madu-800 gr",
						"Frisian-Flag-123-Vanilla-800 gr",
						"Frisian-Flag-456-800 gr", "Nutrilon-Soya-4-400 gr",
						"Pediasure-900 gr", "SGM-2-Vanilla-1000 gr",
						"SGM-3-Madu-1000 gr", "SGM-3-Vanilla-1000 gr",
						"SGM-4-Madu-1000 gr", "SGM-4-Vanilla-1000 gr",
						"SGM-LLM-400 gr", "UHT-Choc 125ml", "UHT-Straw 125ml" };
				final String[] fasilitas_vitamin = { "Vidorant", "Sangobion" };
				final String[] fasilitas_popok = { "Diaper-Goon-pants-XXL38",
						"Diaper-Pampers-pants-L36", "Diaper-Pampers-pants-M42",
						"Diaper-Pampers-pants-XL32" };

				ContentValues values = new ContentValues();

				for (String facility : fasilitas) {
					if (facility.equals("FA001")) {
						values.put(ROW_FACILITY_COST_ID, "FC001");
						values.put(ROW_FACILITY_ID, "FA001");
						values.put(ROW_FACILITY_BRAND_NAME,
								"Bebelac 4-Madu-800 gr");
						values.put(ROW_FACILITY_PRICE, "0");
						values.put(ROW_CREATED_BY, "-");
						values.put(ROW_CREATED_TIME, "-");
						values.put(ROW_UPDATE_BY, "-");
						values.put(ROW_UPDATE_TIME, "-");
						db.insert(NAMA_TABEL, null, values);

						values.put(ROW_FACILITY_COST_ID, "FC002");
						values.put(ROW_FACILITY_ID, "FA001");
						values.put(ROW_FACILITY_BRAND_NAME,
								"Chilschool-4-soya-300 gr");
						values.put(ROW_FACILITY_PRICE, "0");
						values.put(ROW_CREATED_BY, "-");
						values.put(ROW_CREATED_TIME, "-");
						values.put(ROW_UPDATE_BY, "-");
						values.put(ROW_UPDATE_TIME, "-");
						db.insert(NAMA_TABEL, null, values);

						values.put(ROW_FACILITY_COST_ID, "FC003");
						values.put(ROW_FACILITY_ID, "FA001");
						values.put(ROW_FACILITY_BRAND_NAME,
								"Dancow 1+-Vanilla-800 gr");
						values.put(ROW_FACILITY_PRICE, "0");
						values.put(ROW_CREATED_BY, "-");
						values.put(ROW_CREATED_TIME, "-");
						values.put(ROW_UPDATE_BY, "-");
						values.put(ROW_UPDATE_TIME, "-");
						db.insert(NAMA_TABEL, null, values);

						values.put(ROW_FACILITY_COST_ID, "FC004");
						values.put(ROW_FACILITY_ID, "FA001");
						values.put(ROW_FACILITY_BRAND_NAME,
								"Ensure-FOS-Chocolate-1000 gr");
						values.put(ROW_FACILITY_PRICE, "0");
						values.put(ROW_CREATED_BY, "-");
						values.put(ROW_CREATED_TIME, "-");
						values.put(ROW_UPDATE_BY, "-");
						values.put(ROW_UPDATE_TIME, "-");
						db.insert(NAMA_TABEL, null, values);

						values.put(ROW_FACILITY_COST_ID, "FC005");
						values.put(ROW_FACILITY_ID, "FA001");
						values.put(ROW_FACILITY_BRAND_NAME, "Pediasure-900 gr");
						values.put(ROW_FACILITY_PRICE, "0");
						values.put(ROW_CREATED_BY, "-");
						values.put(ROW_CREATED_TIME, "-");
						values.put(ROW_UPDATE_BY, "-");
						values.put(ROW_UPDATE_TIME, "-");
						db.insert(NAMA_TABEL, null, values);

					} else if (facility.equals("FA002")) {

						values.put(ROW_FACILITY_COST_ID, "FC006");
						values.put(ROW_FACILITY_ID, "FA002");
						values.put(ROW_FACILITY_BRAND_NAME, "Vidorant");
						values.put(ROW_FACILITY_PRICE, "0");
						values.put(ROW_CREATED_BY, "-");
						values.put(ROW_CREATED_TIME, "-");
						values.put(ROW_UPDATE_BY, "-");
						values.put(ROW_UPDATE_TIME, "-");
						db.insert(NAMA_TABEL, null, values);

						values.put(ROW_FACILITY_COST_ID, "FC007");
						values.put(ROW_FACILITY_ID, "FA002");
						values.put(ROW_FACILITY_BRAND_NAME, "Sangobion");
						values.put(ROW_FACILITY_PRICE, "0");
						values.put(ROW_CREATED_BY, "-");
						values.put(ROW_CREATED_TIME, "-");
						values.put(ROW_UPDATE_BY, "-");
						values.put(ROW_UPDATE_TIME, "-");
						db.insert(NAMA_TABEL, null, values);

					} else {
						
						values.put(ROW_FACILITY_COST_ID, "FC008");
						values.put(ROW_FACILITY_ID, "FA003");
						values.put(ROW_FACILITY_BRAND_NAME, "Diaper-Goon-pants-XXL38");
						values.put(ROW_FACILITY_PRICE, "0");
						values.put(ROW_CREATED_BY, "-");
						values.put(ROW_CREATED_TIME, "-");
						values.put(ROW_UPDATE_BY, "-");
						values.put(ROW_UPDATE_TIME, "-");
						db.insert(NAMA_TABEL, null, values);
						
						values.put(ROW_FACILITY_COST_ID, "FC009");
						values.put(ROW_FACILITY_ID, "FA003");
						values.put(ROW_FACILITY_BRAND_NAME, "Diaper-Pampers-pants-L36");
						values.put(ROW_FACILITY_PRICE, "0");
						values.put(ROW_CREATED_BY, "-");
						values.put(ROW_CREATED_TIME, "-");
						values.put(ROW_UPDATE_BY, "-");
						values.put(ROW_UPDATE_TIME, "-");
						db.insert(NAMA_TABEL, null, values);
						
						values.put(ROW_FACILITY_COST_ID, "FC010");
						values.put(ROW_FACILITY_ID, "FA003");
						values.put(ROW_FACILITY_BRAND_NAME, "Diaper-Pampers-pants-M42");
						values.put(ROW_FACILITY_PRICE, "0");
						values.put(ROW_CREATED_BY, "-");
						values.put(ROW_CREATED_TIME, "-");
						values.put(ROW_UPDATE_BY, "-");
						values.put(ROW_UPDATE_TIME, "-");
						db.insert(NAMA_TABEL, null, values);
						
						values.put(ROW_FACILITY_COST_ID, "FC011");
						values.put(ROW_FACILITY_ID, "FA003");
						values.put(ROW_FACILITY_BRAND_NAME, "Diaper-Pampers-pants-XL32");
						values.put(ROW_FACILITY_PRICE, "0");
						values.put(ROW_CREATED_BY, "-");
						values.put(ROW_CREATED_TIME, "-");
						values.put(ROW_UPDATE_BY, "-");
						values.put(ROW_UPDATE_TIME, "-");
						db.insert(NAMA_TABEL, null, values);

					}
					Log.e("Database Cost Facility", "success inserting data");
				}
			} catch (Exception e) {
				Log.e("Database Cost Facility", "Eror inserting data");
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

	public CharSequence[] getAllBrandName(String id_facility) {
		ArrayList<String> data = new ArrayList<String>();
		CharSequence[] data_result;
		data_result = data.toArray(new CharSequence[data.size()]);
		try {
			Cursor mCursor = db.rawQuery(
					"SELECT  facility_brand_name  FROM  TM_Cost_Facility WHERE facility_id= '"
							+ id_facility + "'", null);
			if (mCursor != null) {
				mCursor.moveToFirst();
				data.add(mCursor.getString(0));
				while (mCursor.moveToNext()) {
					data.add(mCursor.getString(0));
				}
				data_result = data.toArray(new CharSequence[data.size()]);
			} else {
				Log.e("get all brand name", "no data");
			}
			mCursor.close();
		} catch (Exception e) {
			Log.e("get all brand name", "failed to retrieve");
		}
		return data_result;
	}

	// get id dosis arv
	public String getIdCostFacility(String nama) {
		String id = "";
		Cursor mCursor = db.rawQuery(
				"SELECT  facility_cost_id  FROM  TM_Cost_Facility WHERE facility_brand_name= '"
						+ nama + "'", null);
		if (mCursor != null) {
			mCursor.moveToFirst();
			id = mCursor.getString(0);
		}
		mCursor.close();
		return id;
	}

	// get dose name
	public String getNameCostFacility(String id) {
		String name = "";
		Cursor mCursor = db.rawQuery(
				"SELECT  facility_brand_name  FROM  TM_Cost_Facility WHERE facility_cost_id= '"
						+ id + "'", null);
		if (mCursor != null) {
			mCursor.moveToFirst();
			name = mCursor.getString(mCursor
					.getColumnIndex(ROW_FACILITY_BRAND_NAME));
		}
		mCursor.close();
		return name;
	}

	// parameter need to be sync into web :)
	public void SaveDataFromServer(String[] data) {
		// try {
		// // cek jika sudah ada di database
		//
		// String sql_insert =
		// "INSERT INTO TM_Cost_Facility(facility_id,facility_brand_name,facility_price,created_by,created_time,update_by,update_time) VALUES (?,?,?,null,null,null,null)";
		// for (String aa : data) {
		// // cek the duplicate one
		// String sql = "select * from " + NAMA_TABEL
		// + " WHERE facility_description = '" + aa + "'";
		// Cursor cursorinfo = null;
		// try {
		// cursorinfo = db.rawQuery(sql, null);
		// } catch (Exception ex) {
		// ex.printStackTrace();
		// }
		// //if data is not in database
		// if (!cursorinfo.moveToFirst()) {
		// db.execSQL(sql_insert, new String[] { aa });
		// Log.i("save", aa);
		// } else {
		// Log.i("data already in database", aa);
		// }
		//
		// }
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// Log.e("Failed to save", e.toString());
		// }

	}

	// method to insert
	// method to update
	// method delete
	// select

}